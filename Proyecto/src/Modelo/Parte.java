package Modelo;

import Ventanas.TodosLosPartes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author Grupo 2 (Jose, Usue, David)
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
    private ArrayList<Reparto> repartos = new ArrayList<>();
    private static  ArrayList<Reparto> repartosSt;
    private Vehiculo vehiculo;
    private static Vehiculo vehiculoSt; 
    private Aviso aviso;
    private Ventanas.TodosLosPartes tlp;

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

    public void setTlp(TodosLosPartes tlp) {
        this.tlp = tlp;
    }
    
    

    public static ArrayList<Reparto> getRepartosSt() {
        return repartosSt;
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


  
    public List<Reparto> getRepartos() {
        return repartos;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public  Vehiculo getVehiculo() {
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

    public static Vehiculo getVehiculoSt() {
        return vehiculoSt;
    }
     
    //métodos

    /**
     * Método que muestra un parte completo.
     * @param fecha
     * @param idTrabajador
     * @return Un objeto Parte
     */
        public static Parte verParte(Date fecha, int idTrabajador){
        Parte p = new Parte();
        repartosSt = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PARTES.UNO(?,?,?,?)}");
            cs.setInt(1, idTrabajador);
            java.sql.Date f = new java.sql.Date(fecha.getTime());
            cs.setDate(2, f);
            cs.registerOutParameter(3, OracleTypes.CURSOR);     
            cs.registerOutParameter(4, OracleTypes.CURSOR);    
            cs.execute();
            ResultSet rsp = (ResultSet)cs.getObject(3);         
            while (rsp.next()) {
                p.fecha = rsp.getDate("FECHA");
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
                vehiculoSt= new Vehiculo();
                vehiculoSt.setIdVehiculo(rsp.getInt("ID_VEHICULO"));
                
            }
            ResultSet rsr = (ResultSet)cs.getObject(4);         
            while (rsr.next()) {
                java.util.Date fechaR = rsr.getDate("FECHA");
                String albaran = rsr.getString("ALBARAN");
                java.util.Date horaIni = rsr.getTimestamp("HORA_INICIO");
                java.util.Date horaFin = rsr.getTimestamp("HORA_FIN");
                Reparto r = new Reparto(fecha, albaran, horaIni, horaFin);
                p.añadirReparto(r);
            }
            p.setVehiculo(null);
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return p;
    }
    
    

    /**
     * Método que guarda un parte en la base de datos mediante una sentencia preparada.
     * Antes de llamar a esta función hay que haber asignado vehiculo,trabajador y repartos al parte
     * @param id_trabajador
     * @param id_vehiculo
     * @return true si el guardar se ha realizado correctamente.
     * @throws SQLException
     */
        public boolean guardarParte(int id_trabajador, int id_vehiculo) throws SQLException{
        ControladorBaseDatos.conectar();
        Connection con= ControladorBaseDatos.getConexion();
        try {            
            PreparedStatement ps = con
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
            
            
            for(Reparto r:repartos){
                PreparedStatement psr = con
                        .prepareStatement("INSERT INTO REPARTO VALUES(?,?,?,?,?)");
                psr.setDate(1, sql);
                psr.setInt(2, id_trabajador);
                psr.setString(3, r.getAlbaran());
                psr.setTimestamp(4, new java.sql.Timestamp(r.getHoraInicio().getTime()));
                psr.setTimestamp(5, new java.sql.Timestamp(r.getHoraFin().getTime()));
                psr.execute();
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
                con.close();
            }
            return false;
        }
        
    }
    
    /**
     * Método que actualiza el parte seleccionado en la ventana mediante una sentencia preparada.
     * @param id_trabajador
     * @param id_vehiculo
     * @return true si la actualización ha sido realizada correctamente
     * @throws SQLException
     */
    public boolean actualizarParte(int id_trabajador, int id_vehiculo) throws SQLException{
            ControladorBaseDatos.conectar();
            Connection con = ControladorBaseDatos.getConexion();
        try {
            PreparedStatement ps = con
                    .prepareStatement("UPDATE PARTE SET "
                            + "KM_INICIO=?, KM_FIN=?, GASOIL=?, AUTOPISTA=?, DIETAS=?,"
                            + "OTROS_GASTOS=?,INCIDENCIAS=?, ID_VEHICULO=? WHERE FECHA =? AND ID_TRABAJADOR =?" );
            java.sql.Date sql = new java.sql.Date(fecha.getTime());
            ps.setDouble(1, kmInicio);
            ps.setDouble(2, kmFin);
            ps.setDouble(3, gasoil);
            ps.setDouble(4, autopista);
            ps.setDouble(5, dietas);
            ps.setDouble(6, otrosGastos);
            ps.setString(7, incidencias);
            ps.setInt(8, id_vehiculo);
            ps.setDate(9, sql);
            ps.setInt(10, id_trabajador);
            ps.executeUpdate();
            
           
            for(Reparto r:repartos){
                PreparedStatement psp = con
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
                    psr.setDate(1, sql);
                    psr.setInt(2, id_trabajador);
                    psr.setString(3, r.getAlbaran());
                    psr.setTimestamp(4, new java.sql.Timestamp(r.getHoraInicio().getTime()));
                    psr.setTimestamp(5, new java.sql.Timestamp(r.getHoraFin().getTime()));
                    psr.execute();
                }
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
                con.close();
            }
            return false;
        }
    }
    
    /**
     * Método que cierra un parte.
     * @return true si el parte se ha cerrado correctamente.
     */
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
    
    /**
     * Método que conecta con la base de datos para obtener todos los partes
     * @return lista de partes con id del trabajador, nombre, apellidos, fecha del parte, horas totales y si está abierto o no 
     */
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
                t.setAp1(rs.getString("AP1"));
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
    
    /**
     * Método que conecta con la base de datos para obtener una lista de partes en un rango de fechas
     * @param fechaIni
     * @param fechaFin
     * @return lista de partes segun un rango de fechas
     */
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
                t.setAp1(rs.getString("AP1"));
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
    
    /**
     * Método que conecta con la base de datos para obtener una lista de partes en un rango de fechas y según un trabajador
     * @param fechaIni
     * @param fechaFin
     * @param id
     * @return lista de partes segun un rango de fechas y trabajador
     */
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
    
    /**
     * Método que el administrativo utiliza para validar los partes
     * @return true si ha conseguido validar el parte
     */
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
    
    /**
     * Método que borra un parte 
     * @param id_trabajador
     * @param fecha
     * @return true si ha conseguido borrar el parte
     */
    public static boolean borrarParte(int id_trabajador,Date fecha){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM PARTE WHERE ID_TRABAJADOR=?,"
                            + "AND FECHA=?");
            ps.setInt(1, id_trabajador);
            java.sql.Date sql = new java.sql.Date(fecha.getTime());
            ps.setDate(2, sql);
            ps.execute();
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
