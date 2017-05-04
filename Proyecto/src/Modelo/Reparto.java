
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author 7fbd06
 */
public class Reparto {
    private Date fecha;
    private String albaran;
    private Date horaInicio;
    private Date horaFin;
    
    private static Parte parte;
    private Trabajador trabajador;

    public Reparto(Date fecha, String albaran, Date horaInicio, Date horaFin) {
        this.fecha = fecha;
        this.albaran = albaran;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAlbaran() {
        return albaran;
    }

    public void setAlbaran(String albaran) {
        this.albaran = albaran;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        Reparto.parte = parte;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @Override
    public String toString() {
        return "Reparto{" + "fecha=" + fecha + ", albaran=" + albaran + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", trabajador=" + trabajador + '}';
    }
    
    
}

