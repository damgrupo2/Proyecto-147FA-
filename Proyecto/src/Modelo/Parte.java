package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    //variables
    private java.util.Date fecha;
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
    private int horasTotales;
    
    //relaciones
    private Trabajador trabajador;
    private static List<Reparto> repartos = new ArrayList<>();
    private Vehiculo vehiculo;
    private Aviso aviso;

    //constructores
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
        this.repartos=new ArrayList<>();
    }

    //getter y setter
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


    //BORRAR DESPUES DE LAS PRUEBAS
    public List<Reparto> getRepartos() {
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

    public int getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(int horasTotales) {
        this.horasTotales = horasTotales;
    }
 
    //métodos
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
            for(Reparto r:Parte.repartos){
                PreparedStatement psr = ControladorBaseDatos.getConexion()
                        .prepareStatement("INSERT INTO REPARTO VALUES(?,?,?,?,?)");
                java.sql.Date sqlr = new java.sql.Date(fecha.getTime());
                psr.setDate(1, sqlr);
                psr.setInt(2, id_trabajador);
                psr.setString(3, r.getAlbaran());
                psr.setTimestamp(4, new java.sql.Timestamp(r.getHoraInicio().getTime()));
                psr.setTimestamp(5, new java.sql.Timestamp(r.getHoraFin().getTime()));
                psr.execute();
            }
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
            return false;
        }
    }
    
        public boolean actualizarParte(int id_trabajador, int id_vehiculo){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE PARTE SET FECHA=?, ID_TRABAJADOR=?,"
                            + "KM_INICIO=?, KM_FIN=?, GASOIL=?, AUTOPISTA=?, DIETAS=?,"
                            + "OTROS_GASTOS=?,INCIDENCIAS=?, ID_VEHICULO=?");
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
            ps.setInt(10, id_vehiculo);
            ps.executeUpdate();
            
            //Guardar repartos
            for(Reparto r:Parte.repartos){
                PreparedStatement psp = ControladorBaseDatos.getConexion()
                        .prepareStatement("SELECT ALBARAN FROM REPARTO WHERE ALBARAN=?");
                psp.setString(1, r.getAlbaran());
                ResultSet rsp = psp.executeQuery();
                if(rsp.next()){
                    PreparedStatement psp2 = ControladorBaseDatos.getConexion()
                            .prepareStatement("UPDATE ALBARAN SET HORA_INICIO=?,"
                                    + "SET HORA_FIN=?");
                    psp2.setTimestamp(1, new java.sql.Timestamp(r.getHoraInicio().getTime()));
                    psp2.setTimestamp(1, new java.sql.Timestamp(r.getHoraFin().getTime()));
                }else{
                    PreparedStatement psr = ControladorBaseDatos.getConexion()
                        .prepareStatement("INSERT INTO REPARTO VALUES(?,?,?,?,?)");
                    java.sql.Date sqlr = new java.sql.Date(fecha.getTime());
                    psr.setDate(1, sqlr);
                    psr.setInt(2, id_trabajador);
                    psr.setString(3, r.getAlbaran());
                    psr.setTimestamp(4, new java.sql.Timestamp(r.getHoraInicio().getTime()));
                    psr.setTimestamp(5, new java.sql.Timestamp(r.getHoraFin().getTime()));
                    psr.execute();
                }
            }
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
            return false;
        }
    }
    
    public boolean cerrarParte(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE PARTE SET ABIERTO=0 WHERE FECHA=? AND ID_TRABAJADOR =?");
            java.sql.Date sqlr = new java.sql.Date(fecha.getTime());
            ps.setDate(1, sqlr);
            ps.setInt(2, trabajador.getId_trabajador());
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
            return false;
        }
    }
    
    public static ArrayList<Parte> todosPartes(){
        ArrayList<Parte> partes = new ArrayList<>();
        Parte p ;
        Trabajador t = new Trabajador();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion()
                    .prepareCall("{call PARTES.TODOS(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while(rs.next()){
                p= new Parte();
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1("AP1");
                p.setTrabajador(t);
                p.setFecha(rs.getDate("FECHA_PARTE"));
                p.setHorasTotales(rs.getInt("HORAS_TOTALES"));
                p.setAbierto(rs.getBoolean("ABIERTO"));
                partes.add(p);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return partes;
    }
    
    public static ArrayList<Parte> todosPartesFecha(java.util.Date fechaIni,java.util.Date fechaFin){
        ArrayList<Parte> partes = new ArrayList<>();
        Parte p ;
        Trabajador t = new Trabajador();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion()
                    .prepareCall("{call PARTES.TODOS_FECHA(?,?,?)}");
            java.sql.Date sqlIni = new java.sql.Date(fechaIni.getTime());
            cs.setDate(1, sqlIni);
            java.sql.Date sqlFin = new java.sql.Date(fechaFin.getTime());
            cs.setDate(2, sqlFin);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);
            while(rs.next()){
                p= new Parte();
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1("AP1");
                p.setTrabajador(t);
                p.setFecha(rs.getDate("FECHA_PARTE"));
                p.setHorasTotales(rs.getInt("HORAS_TOTALES"));
                p.setAbierto(rs.getBoolean("ABIERTO"));
                partes.add(p);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return partes;
    }
    
    public static ArrayList<Parte> todosPartesFechaTra(java.util.Date fechaIni,java.util.Date fechaFin,int id){
        ArrayList<Parte> partes = new ArrayList<>();
        Parte p ;
        Trabajador t = new Trabajador();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion()
                    .prepareCall("{call PARTES.TODOS_FECHA_TRABAJADOR(?,?,?,?)}");
            java.sql.Date sqlIni = new java.sql.Date(fechaIni.getTime());
            cs.setDate(1, sqlIni);
            java.sql.Date sqlFin = new java.sql.Date(fechaFin.getTime());
            cs.setDate(2, sqlFin);
            cs.setInt(3, id);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(4);
            while(rs.next()){
                p= new Parte();
                t.setId_trabajador(rs.getInt("ID_TRABAJADOR"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1("AP1");
                p.setTrabajador(t);
                p.setFecha(rs.getDate("FECHA_PARTE"));
                p.setHorasTotales(rs.getInt("HORAS_TOTALES"));
                p.setAbierto(rs.getBoolean("ABIERTO"));
                partes.add(p);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return partes;
    }
    
    public boolean validarParte(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE PARTE SET VALIDADO=1 WHERE ID_TRABAJADOR=? AND FECHA=?");
            ps.setInt(1, trabajador.getId_trabajador());
            java.sql.Date sql = new java.sql.Date(fecha.getTime());
            ps.setDate(2, sql);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Parte{" + ", fecha=" + fecha + ", kmInicio=" + kmInicio + ", kmFin=" + kmFin + ", gasoil=" + gasoil + ", autopista=" + autopista + ", dietas=" + dietas + ", otrosGastos=" + otrosGastos + ", incidencias=" + incidencias + ", abierto=" + abierto + ", excesoHoras=" + excesoHoras + ", validado=" + validado + '}';
    }
}
