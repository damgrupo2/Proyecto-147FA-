package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo 2 (Jose, Usue, David)
 */
public class Vehiculo {
    //variables
    private int idVehiculo;
    private String matricula;
    private String modelo;
    private String marca;
    
    //rellaciones
    private List<Parte> partes=new ArrayList<>();

    //constructores
    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String matricula, String modelo, String marca) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Vehiculo(String matricula, String modelo, String marca) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
    }

    //getter y setter
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
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

    //métodos

    /**
     * Método que conecta con la base de datos para guardar un vehículo mediante una sentencia preparada
     * @return true si ha conseguido guardar el vehículo
     */
        public boolean guardarVehiculo (){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("INSERT INTO VEHICULO(MATRICULA, MODELO, MARCA) VALUES(?,?,?)");
            ps.setString(1, matricula);
            ps.setString(2, modelo);
            ps.setString(3, marca);
            ps.execute();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }
    
    /**
     * Método que conecta con la base de datos para modificar un vehículo mediante una sentencia preparada
     * @return true si ha conseguido modificar el vehículo
     */
    public boolean modificarVehiculo() {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE VEHICULO SET MATRICULA=?,MODELO=?,"
                            + "MARCA=? WHERE ID_VEHICULO=?");
            ps.setString(1, matricula);
            ps.setString(2, modelo);
            ps.setString(3, marca);
            ps.setInt(4, idVehiculo);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }
    
    /**
     * Método que conecta con la base de datos para ver un vehículo mediante una sentencia preparada
     * @param id
     * @return un objeto del tipo Vehiculo con matrícula, modelo y marca
     */
    public static Vehiculo verVehiculo(int id){
        Vehiculo v= new Vehiculo();
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareCall("SELECT * FROM VEHICULO WHERE ID_VEHICULO = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v.setMatricula(rs.getString("MATRICULA"));
                v.setModelo(rs.getString("MODELO"));
                v.setMarca(rs.getString("MARCA"));
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return v;
    }
    
    /**
     * Método que conecta con la base de datos para ver todos los vehículos mediante una sentencia preparada
     * @return lista de vehiculos con matrícula, modelo y marca
     */
    public static List<Vehiculo> listarVehiculos(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().
                    prepareCall("SELECT * FROM VEHICULO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo v= new Vehiculo();
                v.setMatricula(rs.getString("MATRICULA"));
                v.setModelo(rs.getString("MODELO"));
                v.setMarca(rs.getString("MARCA"));
                v.setIdVehiculo(rs.getInt("ID_VEHICULO"));
                vehiculos.add(v);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return vehiculos;
    }
    
    /**
     * Método que conecta con la base de datos para borrar un vehículo mediante una sentencia preparada
     */
    public void borrarVehiculo(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM VEHICULO WHERE ID_VEHICULO=?");
            ps.setInt(1, idVehiculo);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }
    
    @Override
    public String toString() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + '}';
    }
}
