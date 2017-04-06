package Modelo;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


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
    
    private List<Trabajador>trabajadoresList = new ArrayList<>();

    public Centro() {
    }

    public Centro(String nombre, String direccion, String cp, String loc, String provincia, String telf) {
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
                    ("INSERT INTO CENTROS (NOMBRE,DIRECCION,CP, LOC, PROVINCIA,TELF) VALUES (?,?,?,?,?,?)");
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
    
    public List<Centro> listarCentros(){
       List<Centro> centros = new ArrayList<>();
       
       return centros;
    }
    
    public void modificarCentro(){
        
    }
    
    public Centro verCentro(){
        Centro c = new Centro();
        return c;
    }
    
    public void borrarCentro(){
        
    }
    
    public void añadeTrabajador(Trabajador t){
        trabajadoresList.add(t);
       
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
}
