/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 7fbd06
 */
public class Vehiculo {
    
    private int idVehiculo;
    private String matricula;
    private String modelo;
    private String marca;
    
    private List<Parte> partes=new ArrayList<>();

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String matricula, String modelo, String marca) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Vehiculo(String matricula, String modelo, String marca) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + '}';
    }
    
    
    
}
