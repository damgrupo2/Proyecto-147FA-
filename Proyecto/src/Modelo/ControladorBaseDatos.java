package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControladorBaseDatos {
    
    private Connection conexion;
    
   

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public ControladorBaseDatos() {
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion= DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102","Aplicacion", "a12345Abcde");
        } catch (SQLException ex) {
            Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        catch (ClassNotFoundException ex) {
           /* JOptionPane.showMessageDialog(, ex, "Error de conexion", 0);*/
       
    }
    
    
}
}

