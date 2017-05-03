


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Jose
 */
public class Reparto {
    
    private Date fecha;
    private Integer idTrabajador;
    private String albaran;
    private Time horaInicio;
    private Time horaFin;
    
    // conexion
    private Parte parte;

    public Reparto() {
    }

    public Reparto(Date fecha, Integer idTrabajador, String albaran,
            Time horaInicio, Time horaFin, Parte parte) {
        this.fecha = fecha;
        this.idTrabajador = idTrabajador;
        this.albaran = albaran;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.parte = parte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getAlbaran() {
        return albaran;
    }

    public void setAlbaran(String albaran) {
        this.albaran = albaran;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    
    /*----- métodos a utilizar ------*/
    
    public void añadirTrabajador(Trabajador trabajador){
        trabajadores.add(trabajador);
    }

    public static Reparto verReparto(int id){
        Reparto r= null;
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PAQ_CENTROS.CONSULTA_CENTRO(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                String loc = rs.getString("LOC");
                String provincia = rs.getString("PROVINCIA");
                String cp = rs.getString("CP");
                String telf = rs.getString("TELF");
                c = new Centro(nombre, direccion, cp, loc, provincia, telf);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return r;
    }
    
    public static List<Reparto> listarRepartos(){
        List<Reparto> repartos = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call PAQ_CENTROS.CONSULTA_CENTROS(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Centro c = new Centro();
                c.setNombre(rs.getString("NOMBRE"));
                c.setId_centro(rs.getInt("ID_CENTRO"));
                c.setLoc(rs.getString("LOC"));
                centros.add(c);
                System.out.println(c);
                //TODO error ids
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return repartos;
    }

    public boolean guardarReparto(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareStatement("INSERT INTO CENTRO(NOMBRE,DIRECCION,CP,"
                            + "LOC,PROVINCIA,TELF) VALUES(?,?,?,?,?,?) ");
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, cp);
            ps.setString(4, loc);
            ps.setString(5, provincia);
            ps.setString(6, telf);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"
                    +ex.getMessage());
            return false;
        }
    }

    
    
     public boolean modificarReparto() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE REPARTO SET NOMBRE=?,AP1=?,"
                            + "AP2=?,DIRECCION=?,TELF_EMPRESA=?,TELF_PERSONAL=?,"
                            + "CATEGORIA=?, SALARIO=?, FECHANAC=?, DNI=? "
                            + "WHERE ID_TRABAJADOR=?");
            ps.setString(1, nombre);
            ps.setString(2, ap1);
            ps.setString(3, ap2);
            ps.setString(4, direccion);
            ps.setString(5, telf_empresa);
            ps.setString(6, telf_personal);
            ps.setString(7, categoria.toString());
            ps.setDouble(8, salario);
            ps.setDate(9, fechanac);
            ps.setString(10, dni);
            ps.setInt(11, id_trabajador);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }

    public void borrarReparto(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM REPARTO WHERE ID_REPARTO=?");
            ps.setInt(1, id_);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }
    
    
    
    
}


