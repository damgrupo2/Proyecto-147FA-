/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 7fbd06
 */
public class Parte {
    
    private int idParte;
    private Date fecha;
    private double kmInicio;
    private double kmFin;
    private double gasoil;
    private double autopista;
    private double dietas;
    private double otrosGastos;
    private String incidencias;
    private boolean abierto;
    private double excesoHoras;
    private boolean validado;
    
    private Trabajador trabajador;
    private List<Reparto> repartos;
    private Vehiculo vehiculo;
    private Aviso aviso;

    public Parte() {
    }

    public Parte(int idParte, Date fecha, double kmInicio, double kmFin, double gasoil, double autopista, double dietas, double otrosGastos, String incidencias, boolean abierto, double excesoHoras, boolean validado) {
        this.idParte = idParte;
        this.fecha = fecha;
        this.kmInicio = kmInicio;
        this.kmFin = kmFin;
        this.gasoil = gasoil;
        this.autopista = autopista;
        this.dietas = dietas;
        this.otrosGastos = otrosGastos;
        this.incidencias = incidencias;
        this.abierto = abierto;
        this.excesoHoras = excesoHoras;
        this.validado = validado;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getKmInicio() {
        return kmInicio;
    }

    public void setKmInicio(double kmInicio) {
        this.kmInicio = kmInicio;
    }

    public double getKmFin() {
        return kmFin;
    }

    public void setKmFin(double kmFin) {
        this.kmFin = kmFin;
    }

    public double getGasoil() {
        return gasoil;
    }

    public void setGasoil(double gasoil) {
        this.gasoil = gasoil;
    }

    public double getAutopista() {
        return autopista;
    }

    public void setAutopista(double autopista) {
        this.autopista = autopista;
    }

    public double getDietas() {
        return dietas;
    }

    public void setDietas(double dietas) {
        this.dietas = dietas;
    }

    public double getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(double otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public String getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(String incidencias) {
        this.incidencias = incidencias;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public double getExcesoHoras() {
        return excesoHoras;
    }

    public void setExcesoHoras(double excesoHoras) {
        this.excesoHoras = excesoHoras;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    @Override
    public String toString() {
        return "Parte{" + "idParte=" + idParte + ", fecha=" + fecha + ", kmInicio=" + kmInicio + ", kmFin=" + kmFin + ", gasoil=" + gasoil + ", autopista=" + autopista + ", dietas=" + dietas + ", otrosGastos=" + otrosGastos + ", incidencias=" + incidencias + ", abierto=" + abierto + ", excesoHoras=" + excesoHoras + ", validado=" + validado + '}';
    }
    
    
    
}
