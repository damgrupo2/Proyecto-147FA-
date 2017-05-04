/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 7fbd06
 */
public class Parte {
    
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
    private static List<Reparto> repartos = new ArrayList<>();
    private Vehiculo vehiculo;
    private Aviso aviso;

    public Parte() {
    }

    public Parte(Date fecha, double kmInicio, double kmFin, double gasoil, double autopista, double dietas, double otrosGastos, String incidencias) {
        this.fecha = fecha;
        this.kmInicio = kmInicio;
        this.kmFin = kmFin;
        this.gasoil = gasoil;
        this.autopista = autopista;
        this.dietas = dietas;
        this.otrosGastos = otrosGastos;
        this.incidencias = incidencias;
        
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
        return "Parte{" + ", fecha=" + fecha + ", kmInicio=" + kmInicio + ", kmFin=" + kmFin + ", gasoil=" + gasoil + ", autopista=" + autopista + ", dietas=" + dietas + ", otrosGastos=" + otrosGastos + ", incidencias=" + incidencias + ", abierto=" + abierto + ", excesoHoras=" + excesoHoras + ", validado=" + validado + '}';
    }

    //BORRAR DESPUES DE LAS PRUEBAS
    public static List<Reparto> getRepartos() {
        return repartos;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }
    
    public void añadirReparto(Reparto reparto){
        repartos.add(reparto);
        reparto.setParte(this);
    }
    
    //Hacer esta consulta en el login
    /*public static Parte parteAbierto(int idTrabajador){
        Parte p=null;
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call ABIERTO(?,?)}");
            cs.setInt(1, idTrabajador);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs=(ResultSet) cs.getObject(2);
            while(rs.next()){
                p = verParte(rs.getDate("FECHA"), idTrabajador);
            }
            ControladorBaseDatos.desconectar();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(Parte.class.getName()).log(Level.SEVERE, null, ex);
            return p;
        }
    }*/
    
    public static Parte verParte(Date fecha, int idTrabajador){
        Parte p = new Parte();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PARTES.UNO(?,?,?,?)}");
            cs.setInt(1, idTrabajador);
            java.sql.Date f = new java.sql.Date(fecha.getTime());
            cs.setDate(2, f);
            cs.registerOutParameter(3, OracleTypes.CURSOR);     //Cursor parte
            cs.registerOutParameter(4, OracleTypes.CURSOR);     //Cursor repartos
            cs.execute();
            ResultSet rsp = (ResultSet)cs.getObject(3);         //Cursor parte
            while (rsp.next()) {
                p.kmInicio = rsp.getDouble("KM_INICIO");
                p.kmFin = rsp.getDouble("KM_FIN");
                p.gasoil = rsp.getDouble("GASOIL");
                p.autopista = rsp.getDouble("AUTOPISTA");
                p.dietas = rsp.getDouble("DIETAS");
                p.otrosGastos = rsp.getDouble("OTROS_GASTOS");
                p.incidencias = rsp.getString("INCIDENCIAS");
                p.abierto = rsp.getBoolean("ABIERTO");
                p.validado = rsp.getBoolean("VALIDADO");
                p.excesoHoras = rsp.getDouble("EXCESO_HORAS");
            }
            ResultSet rsr = (ResultSet)cs.getObject(4);         //Cursor repartos
            while (rsr.next()) {
                java.util.Date fechaR = rsr.getDate("FECHA");
                String albaran = rsr.getString("ALBARAN");
                java.util.Date horaIni = rsr.getTimestamp("HORA_INICIO");
                java.util.Date horaFin = rsr.getTimestamp("HORA_FIN");
                Reparto r = new Reparto(fecha, albaran, horaIni, horaFin);
                repartos.add(r);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return p;
    }
    
    //Antes de llamar a esta función hay que haber asignado vehiculo,trabajador y repartos al parte
    public boolean guardarParte(int id_trabajador, int id_vehiculo){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("INSERT INTO PARTE(FECHA,ID_TRABAJADOR,"
                            + "KM_INICIO,KM_FIN,GASOIL,AUTOPISTA,DIETAS,OTROS_GASTOS,"
                            + "INCIDENCIAS,ABIERTO,ID_VEHICULO,VALIDADO) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            java.sql.Date sql = new java.sql.Date(fecha.getTime());
            ps.setDate(1, sql);
            ps.setInt(2, id_trabajador);
            ps.setDouble(3, kmInicio);
            ps.setDouble(4, kmFin);
            ps.setDouble(5, gasoil);
            ps.setDouble(6, autopista);
            ps.setDouble(7, dietas);
            ps.setDouble(8, otrosGastos);
            ps.setString(9, incidencias);
            ps.setBoolean(10, true);
            ps.setInt(11, id_vehiculo);
            ps.setBoolean(12, false);
            ps.execute();
            
            //Guardar repartos
            for(Reparto r:repartos){
                PreparedStatement psr = ControladorBaseDatos.getConexion()
                        .prepareStatement("INSERT INTO REPARTO VALUES(?,?,?,?,?)");
                java.sql.Date sqlr = new java.sql.Date(fecha.getTime());
                psr.setDate(1, sqlr);
                psr.setInt(2, id_trabajador);
                psr.setString(3, r.getAlbaran());
                psr.setTimestamp(4, (Timestamp) r.getHoraInicio());
                psr.setTimestamp(5, (Timestamp) r.getHoraFin());
                psr.execute();
            }
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Parte.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
