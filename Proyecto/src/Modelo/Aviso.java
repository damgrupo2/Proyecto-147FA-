
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Jose
 */
public class Aviso {
    //variables
    private int idAviso;
    private String texto;
    private int idTrabajadorE;
    private int idTrabajadorR;
    private java.util.Date parteFecha;
    private String nombreE;

    //relaciones
    private Trabajador trabajador;

    //constructores
    public Aviso() {
    }
    
    public Aviso(int idAviso, String texto, int idTrabajadorE, int idTrabajadorR, java.util.Date parteFecha) {
        this.idAviso = idAviso;
        this.texto = texto;
        this.idTrabajadorE = idTrabajadorE;
        this.idTrabajadorR = idTrabajadorR;
        this.parteFecha = parteFecha;
    }

    //getter y setter
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

    public void setParteFecha(java.util.Date parteFecha) {
        this.parteFecha = parteFecha;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    //métodos

    /**
     * Consultar el detalle de un aviso
     * @param id int
     * @return aviso Aviso
     */
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

     /**
     * Consultar todos los avisos correspondientes a ese trabajador
     * @param id int
     * @return avisos ArrayList de Avisos
     */
    public static ArrayList<Aviso> listarAvisos(int id) {
        ArrayList<Aviso> avisos = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareCall("SELECT ID_AVISO, A.TEXTO,T.NOMBRE,T.AP1 FROM AVISO A, TRABAJADOR T WHERE A.ID_TRABAJADOR_R=?"
                            + "AND A.ID_TRABAJADOR_R= T.ID_TRABAJADOR");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aviso a = new Aviso();
                a.idAviso= rs.getInt("ID_AVISO");
                a.texto = rs.getString("TEXTO");
                String nombre = rs.getString("NOMBRE");
                a.nombreE = nombre + " " + rs.getString("AP1");
                avisos.add(a);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" + ex.getMessage());
        }
        return avisos;
    }

    /**
     *  Guarda en la base de datos el aviso
     * @return boolean la inserción se ha completado
     */
    public boolean guardarAviso() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareStatement("INSERT INTO AVISO (TEXTO,ID_TRABAJADOR_E,"
                            + "ID_TRABAJADOR_R,PARTE_FECHA,ID_TRABAJADOR) VALUES(?,?,?,?,?) ");
            ps.setString(1, texto);
            ps.setInt(2, idTrabajadorE);
            ps.setInt(3, idTrabajadorR);
            java.sql.Date fecha = new java.sql.Date(parteFecha.getTime());
            ps.setDate(4, fecha);
            ps.setInt(5, idTrabajadorR);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n"
                    + ex.getMessage());
            return false;
        }
    }

    /**
     *  Borra un aviso
     * @return boolean la inserción se ha completado
     */
    public boolean borrarAviso() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM AVISO WHERE ID_AVISO=?");
            ps.setInt(1, idAviso);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n" 
                    + ex.getMessage());
            return false;
        }
    }
}

