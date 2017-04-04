package Modelo;


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
    private int cp;
    private String provincia;
    private String telf;
    
    /* relación con trabajador*/
    
    private List<Trabajador>trabajadoresList = new ArrayList<>();

    public Centro() {
    }

    public Centro(int id_centro, String nombre, String direccion, int cp, String provincia, String telf) {
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

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
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

   
    
    
    
    
    
    
    
    
    
    
    
    
}
