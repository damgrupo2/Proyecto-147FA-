package Modelo;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose
 */
public class Centro {
    
    private static int id_centro;
    private String nombre;
    private String direccion;
    private String cp;
    private String loc;
    private String provincia;
    private String telf;
    
    /* relación con trabajador*/
    
    private List<Trabajador>trabajadores = new ArrayList<>();

    public Centro() {
    }

    public Centro( String nombre, String direccion, String cp, String loc , String provincia, String telf) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cp = cp;
        this.loc = loc;
        this.provincia = provincia;
        this.telf = telf;
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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
    
    /* métodos a utilizar */

    public void añadirTrabajador(Trabajador trabajador){
        trabajadores.add(trabajador);
    }

    public static Centro verCentro(int id){
        Centro c= null;
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PAQ_CENTROS.CONSULTA_CENTRO(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                String loc = rs.getString("LOC");
                String provincia = rs.getString("PROVINCIA");
                String cp = rs.getString("CP");
                String telf = rs.getString("TELF");
                c = new Centro(nombre, direccion, cp, loc, provincia, telf);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return c;
    }
    
    public static List<Centro> listarCentros(){
        List<Centro> centros = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PAQ_CENTROS.CONSULTA_CENTROS(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Centro c = new Centro();
                c.setNombre(rs.getString("NOMBRE"));
                c.setId_centro(rs.getInt("ID_CENTRO"));
                c.setLoc(rs.getString("LOC"));
                centros.add(c);
                System.out.println(c);
                //TODO error ids
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return centros;
    }


    public boolean guardarCentro(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareStatement("INSERT INTO CENTRO(NOMBRE,DIRECCION,CP,"
                            + "LOC,PROVINCIA,TELF) VALUES(?,?,?,?,?,?) ");
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, cp);
            ps.setString(4, loc);
            ps.setString(5, provincia);
            ps.setString(6, telf);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"
                    +ex.getMessage());
            return false;
        }
    }

    public boolean modificarCentro(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE CENTRO SET NOMBRE=?,DIRECCION=?,"
                            + "LOC=?,PROVINCIA=?,CP=?,TELF=? WHERE ID_CENTRO=?");
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, loc);
            ps.setString(4, provincia);
            ps.setString(5, cp);
            ps.setString(6, telf);
            ps.setInt(7, id_centro);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }

    public void borrarCentro(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM CENTRO WHERE ID_CENTRO=?");
            ps.setInt(1, id_centro);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Centro{" + "id_centro=" + id_centro + ", nombre=" + nombre + ", direccion=" + direccion + ", cp=" + cp + ", loc=" + loc + ", provincia=" + provincia + ", telf=" + telf + '}';
    }  
}
