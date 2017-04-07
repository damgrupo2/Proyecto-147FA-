package Modelo;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    
    private int id_centro;
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

    public Centro(int id_centro, String nombre, String direccion, String cp, String provincia, String telf) {
        this.id_centro = id_centro;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cp = cp;
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

    public List<Trabajador> getTrabajadoresList() {
        return trabajadores;
    }

    
    public void setTrabajadoresList(List<Trabajador> trabajadoresList) {
        this.trabajadores = trabajadoresList;
    }
    
    /* métodos a utilizar */
    
    public void guardarCentro(){
 
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
 
            boolean correcto = ps.execute();
 
            ControladorBaseDatos.desconectar();
 
        } catch (SQLException ex) {
 
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"
                    +ex.getMessage());
        }
    }
 
    
    public List<Centro> listarCentros(){
        List<Centro> centros = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PAQ_CENTROS.CONSULTA_CENTROS(?)}");
            cs.set
        } catch (SQLException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centros;
    }
    
    public void modificarCentro(){
        
    }
    
    public Centro verCentro(){
        Centro c = new Centro();
        return c;
    }
    
    public void listarTrabajadoresCentro(){
        
    }
    
    public void borrarCentro(){
        
    }

    public void añadirTrabajador(Trabajador trabajador){
        trabajadores.add(trabajador);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
