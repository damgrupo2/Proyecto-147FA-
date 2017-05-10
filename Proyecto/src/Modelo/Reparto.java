package Modelo;

import java.util.Date;

/**
 *
 * @author Grupo 2 (Jose, Usue, David)
 */
public class Reparto {
    //variables
    private Date fecha;
    private String albaran;
    private Date horaInicio;
    private Date horaFin;
    
    //relaciones
    private static Parte parte;
    private Trabajador trabajador;

    //constructores
    public Reparto(Date fecha, String albaran, Date horaInicio, Date horaFin) {
        this.fecha = fecha;
        this.albaran = albaran;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    //getter y setter
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

