package Modelo;


import Ventanas.CentroDetalle;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;



public class Centro {
    
    private int id_centro;
    private String nombre;
    private String direccion;
    private String cp;
    private String loc;
    private String provincia;
    private String telf;
    
    /* relación con trabajador*/
    
    private List<Trabajador>trabajadoresList = new ArrayList<>();
    

    public Centro() {
    }

    public Centro(int id_centro, String nombre, String direccion, String cp, String loc, String provincia, String telf) {
        this.id_centro = id_centro;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cp = cp;
        this.loc = loc;
        this.provincia = provincia;
        this.telf = telf;
    }

    
    
    public Centro(String nombre, String direccion, String cp, String loc, String provincia, String telf) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cp = cp;
        this.loc = loc;
        this.provincia = provincia;
        this.telf = telf;
    }

    public Centro(String nombre, String loc) {
        this.nombre = nombre;
        this.loc = loc;
    }
    
   

    

  

    public int getId_centro() {
        return id_centro;
    }

    public void setId_centro(int id_centro) {
        this.id_centro = id_centro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public List<Trabajador> getTrabajadoresList() {
        return trabajadoresList;
    }

    
    public void setTrabajadoresList(List<Trabajador> trabajadoresList) {
        this.trabajadoresList = trabajadoresList;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
    
    
    
    /* métodos a utilizar */
    
    public void guardarCentro(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("INSERT INTO CENTRO (NOMBRE,DIRECCION,CP, LOC, PROVINCIA,TELF) VALUES (?,?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, cp);
            ps.setString(4, loc);
            ps.setString(5, provincia);
            ps.setString(6, telf);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
        
        
    }
    
    public List<Centro> listarCentros() throws SQLException{
      ControladorBaseDatos.conectar();
        String query="{call VISTAS_CENTROS.TODOS_CENTROS (?)}";
        CallableStatement cst=ControladorBaseDatos.getConexion().prepareCall(query);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs= (ResultSet)cst.getObject(1);
        List<Centro> centros=new ArrayList<>();
        while(rs.next()){
           
             String nombre=rs.getString("NOMBRE");
             
             String loc=rs.getString("LOC");
           
             Centro c=new Centro(nombre, loc);
             centros.add(c);
            
        }
        
        ControladorBaseDatos.desconectar();
    
     return centros ;
    }
    
    public void modificarCentro(){
         try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("UPDATE CENTRO SET NOMBRE=?, DIRECCION=?, CP=?, LOC=?, PROVINCIA=?, TELF=? "
                                        + "WHERE ID_CENTRO=?");
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, cp);
            ps.setString(4, loc);
            ps.setString(5, provincia);
            ps.setString(6, telf);
            ps.setInt(7, id_centro);
            
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
    }
    
    public List<Centro> verCentro() throws SQLException{
            ControladorBaseDatos.conectar();
        String query="{call VISTAS_CENTROS.UN_CENTRO (?,?)}";
        CallableStatement cst=ControladorBaseDatos.getConexion().prepareCall(query);
        cst.setInt(1, 1);
        cst.registerOutParameter(2, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs= (ResultSet)cst.getObject(2);
        List<Centro> centros=new ArrayList<>();
        while(rs.next()){
             int id_centro=rs.getInt("ID_CENTRO");
             String nombre=rs.getString("NOMBRE");
             String direccion =rs.getString("DIRECCION");
             String cp=rs.getString("CP");
             String loc=rs.getString("LOC");
             String provincia =rs.getString("PROVINCIA");
             String telf=rs.getString("TELF");
             
             Centro c=new Centro(id_centro,nombre,direccion,cp,loc,provincia,telf);
             centros.add(c);
        }
        
        ControladorBaseDatos.desconectar();
        return centros;
    }

    
    public void borrarCentro(int id_centro){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("DELETE CENTRO WHERE ID_CENTRO=?");
            ps.setInt(1, id_centro);
            
 
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
    }
    
    public void añadeTrabajador(Trabajador t){
        trabajadoresList.add(t);
       
    }

    @Override
    public String toString() {
        return "Centro{" + "id_centro=" + id_centro + ", nombre=" + nombre + ", direccion=" + direccion + ", cp=" + cp + ", loc=" + loc + ", provincia=" + provincia + ", telf=" + telf + ", trabajadoresList=" + trabajadoresList + '}';
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
}
