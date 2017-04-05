package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladorBaseDatos {
    
    private Connection conexion;
    
    public void crearConexion() throws ClassNotFoundException{
   
       Class.forName("oracle.jdbc.driver.OracleDriver");
        try {   
            conexion= DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102","Aplicacion", "a12345Abcde");
        } catch (SQLException ex) {
            Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
        }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}

