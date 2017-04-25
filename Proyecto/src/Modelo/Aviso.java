/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.sun.corba.se.spi.logging.CORBALogDomains;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Jose
 */
public class Aviso {

    private int idAviso;
    private String texto;
    private int idTrabajadorE;
    private int idTrabajadorR;
    private java.util.Date parteFecha;

    //conexion
    private Trabajador trabajador;

    public Aviso() {
    }

    public Aviso(int idAviso, String texto, int idTrabajadorE,
            int idTrabajadorR, java.util.Date parteFecha, Trabajador trabajador) {
        this.idAviso = idAviso;
        this.texto = texto;
        this.idTrabajadorE = idTrabajadorE;
        this.idTrabajadorR = idTrabajadorR;
        this.parteFecha = parteFecha;
        this.trabajador = trabajador;
    }

    private Aviso(int idAviso, String texto, int idTrabajadorE, int idTrabajadorR, java.util.Date parteFecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Integer idAviso) {
        this.idAviso = idAviso;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getIdTrabajadorE() {
        return idTrabajadorE;
    }

    public void setIdTrabajadorE(Integer idTrabajadorE) {
        this.idTrabajadorE = idTrabajadorE;
    }

    public Integer getIdTrabajadorR() {
        return idTrabajadorR;
    }

    public void setIdTrabajadorR(Integer idTrabajadorR) {
        this.idTrabajadorR = idTrabajadorR;
    }

    public java.util.Date getParteFecha() {
        return parteFecha;
    }

    public void setParteFecha(Date parteFecha) {
        this.parteFecha = parteFecha;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    /* métodos a utilizar */
    public void añadirAviso(Aviso aviso) {
        aviso.add(aviso);
    }

    public static Aviso verAviso(int id) {
        Aviso a = null;
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareCall("SELECT * FROM AVISO WHERE ID_AVISO = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAviso = rs.getInt("id_aviso");
                String texto = rs.getString("texto");
                int idTrabajadorE = rs.getInt("id_trabajador_e");
                int idTrabajadorR = rs.getInt("id_trabajador_r");
                java.util.Date parteFecha = rs.getDate("parte_fecha");
                a = new Aviso(idAviso, texto, idTrabajadorE,
                        idTrabajadorR, parteFecha);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return a;
    }

    public static Aviso listarAvisos() {
        Aviso a = null;
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareCall("SELECT * FROM AVISO");
            ps.execute();
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                int idAviso = rs.getInt("id_aviso");
                String texto = rs.getString("texto");
                int idTrabajadorE = rs.getInt("id_trabajador_e");
                int idTrabajadorR = rs.getInt("id_trabajador_r");
                java.util.Date parteFecha = rs.getDate("parte_fecha");
                a = new Aviso(idAviso, texto, idTrabajadorE,
                        idTrabajadorR, parteFecha);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return a;
    }

    public boolean guardarAviso() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareStatement("INSERT INTO AVISO (ID_AVISO,TEXTO,ID_TRABAJDOR_E,"
                            + "ID_TRABAJADOR_R,PARTE_FECHA) VALUES(?,?,?,?,?,?) ");
            ps.setInt(1, idAviso);
            ps.setString(2, texto);
            ps.setInt(3, idTrabajadorE);
            ps.setInt(4, idTrabajadorR);
            ps.setDate(5, (Date) parteFecha);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n"
                    + ex.getMessage());
            return false;
        }
    }

    public void borrarAviso(int id) {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM AVISO WHERE ID_AVISO=?");
            ps.setInt(1, id);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
    }

    private void add(Aviso aviso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
