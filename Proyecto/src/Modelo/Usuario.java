/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 7fbd06
 */
public class Usuario {

    private String usuario;
    private String contraseña;

    private static Trabajador t=new Trabajador();
    private static Parte p = new Parte();
    private static Vehiculo v = new Vehiculo();
    

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

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

    

    public void guardarUsuario() {
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_NUEVO(?,?)}");
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            cs.execute();

            /*PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("INSERT INTO USUARIO VALUES(?,ORA_HASH(?))");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.execute();*/
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
    }

    public void modificarUsuario() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("UPDATE USUARIO SET ID_USUARIO=?,CONTRASEÑA=ORA_HASH(?)");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarUsuario() {

    }

    public Categoria hacerLogin() {
        Categoria c = null;
        
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_LOGIN(?,?,?,?,?,?,?)}");
            cs.setString(1, usuario);
            cs.setNString(2, contraseña);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            cs.registerOutParameter(7, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rst = (ResultSet) cs.getObject(3);
            ResultSet rsp = (ResultSet) cs.getObject(4);
            ResultSet rsr = (ResultSet) cs.getObject(5);
            ResultSet rsa = (ResultSet) cs.getObject(6);
            ResultSet rsv = (ResultSet) cs.getObject(7);
            while (rst.next()) {
                t.setDni(rst.getString("DNI"));
                t.setNombre(rst.getString("NOMBRE"));
                t.setAp1(rst.getString("AP1"));
                t.setAp2(rst.getString("AP2"));
                t.setDireccion(rst.getString("DIRECCION"));
                t.setTelf_empresa(rst.getString("TELF_EMPRESA"));
                t.setTelf_personal(rst.getString("TELF_PERSONAL"));
                t.setSalario(rst.getDouble("SALARIO"));
                t.setFechanac(rst.getDate("FECHANAC"));
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

                    while (rsr.next()) {
                        java.util.Date fechaR = rsr.getDate("FECHA");
                        String albaran = rsr.getString("ALBARAN");
                        java.util.Date horaIni = rsr.getTimestamp("HORA_INICIO");
                        java.util.Date horaFin = rsr.getTimestamp("HORA_FIN");
                        Reparto r = new Reparto(fechaR, albaran, horaIni, horaFin);
                        p.añadirReparto(r);

                        while (rsv.next()) {
                            
                            v.setMatricula(rsv.getString("MATRICULA"));
                            v.setModelo(rsv.getString("MODELO"));
                            v.setMarca(rsv.getString("MARCA"));
                            p.setVehiculo(v);
                        }
                    }

                }
                t.añadirParte(p);

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
}
