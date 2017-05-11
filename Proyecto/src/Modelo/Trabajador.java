package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Grupo 2 (Jose, Usue, David)
 */
public class Trabajador {
    //variables
    private int id_trabajador;
    private String dni;
    private String nombre;
    private String ap1;
    private String ap2;
    private String direccion;
    private String telf_empresa;
    private String telf_personal;
    private Categoria categoria;
    private double salario;
    private String fechanac;
    
    //relaciones
    private Centro centro;
    private List<Parte> parteList=new ArrayList<Parte>();
    private List<Aviso> avisosList=new ArrayList<>();
    private Usuario usuario;
    
    //constructores
    public Trabajador() {
    }

    public Trabajador( String dni, String nombre, String ap1, String ap2, 
            String direccion, String telf_empresa, String telf_personal, 
            Categoria categoria, double salario, String fechanac) {
        this.dni = dni;
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.direccion = direccion;
        this.telf_empresa = telf_empresa;
        this.telf_personal = telf_personal;
        this.categoria = categoria;
        this.salario = salario;
        this.fechanac = fechanac;
    }

    //getter y setter
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Centro getCentro() {
        return centro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelf_empresa() {
        return telf_empresa;
    }

    public void setTelf_empresa(String telf_empresa) {
        this.telf_empresa = telf_empresa;
    }

    public String getTelf_personal() {
        return telf_personal;
    }

    public void setTelf_personal(String telf_personal) {
        this.telf_personal = telf_personal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public void añadirCentro(Centro centro) {
        this.centro = centro;
        centro.añadirTrabajador(this);
    }
    
    public void añadirUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.setT(this);
    }
    
    public void añadirParte(Parte parte){
        parteList.add(parte);
        parte.setTrabajador(this);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    //métodos

    /**
     * Método que conecta con la base de datos para guardar un trabajador mediante una sentencia preparada
     * @return true si ha conseguido guardar el parte
     */
        public boolean guardarTrabajador(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("INSERT INTO TRABAJADOR(DNI, NOMBRE, AP1, AP2, DIRECCION, TELF_EMPRESA, TELF_PERSONAL," +
                            "CATEGORIA, SALARIO, FECHANAC,ID_CENTRO, ID_USUARIO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,dni);
            ps.setString(2, nombre);
            ps.setString(3, ap1);
            ps.setString(4, ap2);
            ps.setString(5, direccion);
            ps.setString(6, telf_empresa);
            ps.setString(7, telf_personal);
            ps.setString(8, categoria.toString());
            ps.setDouble(9, salario);
            ps.setString(10, fechanac);  
            ps.setInt(11, centro.getId_centro());
            ps.setString(12, usuario.getUsuario());
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }
    
    /**
     * Método que conecta con la base de datos para modificar un trabajador mediante una sentencia preparada
     * @return true si ha conseguido modificar el trabajador
     */
    public boolean modificarTrabajador() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE TRABAJADOR SET NOMBRE=?,AP1=?,"
                            + "AP2=?,DIRECCION=?,TELF_EMPRESA=?,TELF_PERSONAL=?,"
                            + "CATEGORIA=?, SALARIO=?, FECHANAC=?, DNI=? "
                            + "WHERE ID_TRABAJADOR=?");
            ps.setString(1, nombre);
            ps.setString(2, ap1);
            ps.setString(3, ap2);
            ps.setString(4, direccion);
            ps.setString(5, telf_empresa);
            ps.setString(6, telf_personal);
            ps.setString(7, categoria.toString());
            ps.setDouble(8, salario);
            ps.setString(9, fechanac);
            ps.setString(10, dni);
            ps.setInt(11, id_trabajador);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }
    
    /**
     * Método que conecta con la base de datos para borrar un trabajador mediante una sentencia preparada
     */
    public void borrarTrabajador(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR=?");
            ps.setInt(1, id_trabajador);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }
    
    /**
     * Método que conecta con la base de datos para listar trabajadores mediante llamada a un procedimiento dentro de un paquete
     * @return lista de trabajadores con dni, nombre, apellidos e id
     */
    public static List<Trabajador> listarTrabajadores(){
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRABAJADORES(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Trabajador t= new Trabajador();
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));

                Usuario u = new Usuario();
                u.setUsuario(rs.getString("ID_USUARIO"));
                t.setUsuario(u);
                Centro c = new Centro();
                c.setId_centro(rs.getInt("ID_CENTRO"));
                t.setCentro(c);

                trabajadores.add(t);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return trabajadores;
    }
    
    /**
     * Método que conecta con la base de datos para listar trabajadores transportistas mediante llamada a un procedimiento dentro de un paquete
     * @return lista de transportistas con dni, nombre, apellidos e id
     */
    public static List<Trabajador> listarTrabajadoresTrans(){
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRANSPORTISTAS(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Trabajador t= new Trabajador();
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));
                trabajadores.add(t);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return trabajadores;
    }
    
    /**
     * Método que conecta con la base de datos para listar trabajadores según su centro mediante llamada a un procedimiento dentro de un paquete
     * @param id_centro
     * @return lista de trabajadores con dni, nombre, apellidos e id según su centro
     */
    public static List<Trabajador> listarTrabajadoresCentro(int id_centro){
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRABAJADORES_CENTRO(?,?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setInt(2, id_centro);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Trabajador t= new Trabajador();
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));
                trabajadores.add(t);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return trabajadores;
    }
    
    /**
     * Método que conecta con la base de datos para mostrar un trabajador mediante llamada a un procedimiento dentro de un paquete
     * @param id
     * @return un objeto trabajador
     */
    public static Trabajador verTrabajador(int id){
        Trabajador t= new Trabajador();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRABAJADOR(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            while (rs.next()) {
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setDireccion(rs.getString("DIRECCION"));
                t.setTelf_empresa(rs.getString("TELF_EMPRESA"));
                t.setTelf_personal(rs.getString("TELF_PERSONAL"));
                t.setSalario(rs.getDouble("SALARIO"));
                t.setFechanac(rs.getString("FECHANAC"));
                String categoria =  rs.getString("CATEGORIA");
                Usuario u = new Usuario();
                u.setUsuario(rs.getString("ID_USUARIO"));
                t.setUsuario(u);
                Centro ce = new Centro();
                ce.setId_centro(rs.getInt("ID_CENTRO"));
                t.setCentro(ce);
                Categoria c = null;
                switch(categoria){
                    case "Administrativo":
                        c = Categoria.Administrativo;
                        break;
                    case "Transportista":
                        c = Categoria.Transportista;
                }
                t.setCategoria(c);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return t;
    }
}
