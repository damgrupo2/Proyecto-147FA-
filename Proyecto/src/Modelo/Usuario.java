package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Grupo 2 (Jose, Usue, David)
 */
public class Usuario {
    //variables
    private String usuario;
    private String contraseña;

    //relaciones
    private static Trabajador t=new Trabajador();
    private static Parte p = new Parte();
    private static Vehiculo v = new Vehiculo();
    private Ventanas.VentanaParte vp;
    
    //constructores
    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Usuario() {
    }
    
    //getter y setter
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public static Trabajador getT() {
        return t;
    }

    public static void setT(Trabajador t) {
        Usuario.t = t;
    }

    public static Parte getP() {
        return p;
    }

    public static Vehiculo getV() {
        return v;
    }

    //métodos

    /**
     * Método que conecta con la base de datos para guardar un usuario mediante llamada a un procedimiento dentro de un paquete
     * @return true si ha conseguido guardar un usuario
     */
        public boolean guardarUsuario() {
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_NUEVO(?,?)}");
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            cs.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
            return false;
        }
    }

    /**
     * Método que conecta con la base de datos para modificar un usuario mediante una sentencia preparada
     */
    public void modificarUsuario() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("UPDATE USUARIO SET ID_USUARIO=?,CONTRASEÑA=ORA_HASH(?)");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
    }

    /**
     * Método que conecta con la base de datos para borrar un usuario mediante una sentencia preparada
     */
    public void borrarUsuario() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM VEHICULO WHERE ID_USUARIO=?");
            ps.setString(1, usuario);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }

    /**
     * Método que conecta con la base de datos para conectarse a la aplicación mediante una llamada a un procedimiento en un paquete
     * A su vez obtiene todos los datos referentes a ese usuario, desde partes, vehiculo, repartos, ...
     * @return una categoria enumerada, ya sea administrativo o transportista
     */
    public Categoria hacerLogin() {
        Categoria c = null;
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_LOGIN(?,?,?,?,?,?,?,?)}");
            cs.setString(1, usuario);
            cs.setNString(2, contraseña);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            cs.registerOutParameter(7, OracleTypes.CURSOR);
             cs.registerOutParameter(8, OracleTypes.NUMBER);
            cs.execute();
            ResultSet rst = (ResultSet) cs.getObject(3);
            ResultSet rsp = (ResultSet) cs.getObject(4);
            ResultSet rsr = (ResultSet) cs.getObject(5);
            ResultSet rsa = (ResultSet) cs.getObject(6);
            ResultSet rsv = (ResultSet) cs.getObject(7);
            int contador= cs.getInt(8);
            while (rst.next()) {
                t.setId_trabajador(rst.getInt("ID_TRABAJADOR"));
                t.setDni(rst.getString("DNI"));
                t.setNombre(rst.getString("NOMBRE"));
                t.setAp1(rst.getString("AP1"));
                t.setAp2(rst.getString("AP2"));
                t.setDireccion(rst.getString("DIRECCION"));
                t.setTelf_empresa(rst.getString("TELF_EMPRESA"));
                t.setTelf_personal(rst.getString("TELF_PERSONAL"));
                t.setSalario(rst.getDouble("SALARIO"));
                t.setFechanac(rst.getString("FECHANAC"));
                String categoria = rst.getString("CATEGORIA");
                c = null;
                switch (categoria) {
                    case "Administrativo":
                        c = Categoria.Administrativo;
                        break;
                    case "Transportista":
                        c = Categoria.Transportista;
                }
                t.setCategoria(c);
            }

            if (t.getCategoria() == Categoria.Transportista) {
                if(contador==1){
                    while (rsp.next()) {
                        p.setFecha(rsp.getDate("FECHA"));
                        p.setKmInicio(rsp.getDouble("KM_INICIO"));
                        p.setKmFin(rsp.getDouble("KM_FIN"));
                        p.setGasoil(rsp.getDouble("GASOIL"));
                        p.setAutopista(rsp.getDouble("AUTOPISTA"));
                        p.setDietas(rsp.getDouble("DIETAS"));
                        p.setOtrosGastos(rsp.getDouble("OTROS_GASTOS"));
                        p.setIncidencias(rsp.getString("INCIDENCIAS"));
                        p.setAbierto(rsp.getBoolean("ABIERTO"));
                        p.setValidado(rsp.getBoolean("VALIDADO"));
                        p.setExcesoHoras(rsp.getDouble("EXCESO_HORAS"));
                        
                        while (rsv.next()) {
                                v.setIdVehiculo(rsv.getInt("ID_VEHICULO"));
                                v.setMatricula(rsv.getString("MATRICULA"));
                                v.setModelo(rsv.getString("MODELO"));
                                v.setMarca(rsv.getString("MARCA"));
                                p.setVehiculo(v);
                            }
                        while (rsr.next()) {
                            java.util.Date fechaR = rsr.getDate("FECHA");
                            String albaran = rsr.getString("ALBARAN");
                            java.util.Date horaIni = rsr.getTimestamp("HORA_INICIO");
                            java.util.Date horaFin = rsr.getTimestamp("HORA_FIN");
                            Reparto r = new Reparto(fechaR, albaran, horaIni, horaFin);
                            p.añadirReparto(r);
                        }
                    }
                    t.añadirParte(p);
                }
            } else {
                while (rsa.next()) {
                    Aviso a = null;
                    int idAviso = rsa.getInt("id_aviso");
                    String texto = rsa.getString("texto");
                    int idTrabajadorE = rsa.getInt("id_trabajador_e");
                    int idTrabajadorR = rsa.getInt("id_trabajador_r");
                    java.util.Date parteFecha = rsa.getDate("parte_fecha");
                    a = new Aviso(idAviso, texto, idTrabajadorE,
                            idTrabajadorR, parteFecha);
                }
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return c;
    }
    
    /**
     * Método que conecta con la base de datos para listar todos los usuarios mediante una sentencia preparada
     * @return lista de usuarios con su id
     */
    public static List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareStatement("SELECT ID_USUARIO FROM USUARIO");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Modelo.Usuario u = new Modelo.Usuario();
                u.setUsuario(rs.getString("ID_USUARIO"));
                usuarios.add(u);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return usuarios;
    }
}
