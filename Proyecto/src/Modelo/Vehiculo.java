/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jose
 */
public class Vehiculo {
    
    private Integer idVehiculo;
    private String matricula;
    private String modelo;
    private String marca;
    
    //conexion " preguntar si tiene conexion con parte "

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo, String matricula, String modelo, String marca) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
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
    
    
    
}
