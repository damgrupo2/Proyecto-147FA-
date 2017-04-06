package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControladorBaseDatos {
    
    private static Connection conexion;
    
   

    public static Connection getConexion() {
        return conexion;
    }

    public static void conectar() {
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String login="Aplicacion";
            String pass="a12345Abcde";
            String url="jdbc:oracle:thin:@10.10.10.9:1521:db12102";
            conexion= DriverManager.getConnection(url, login, pass);
            conexion.setAutoCommit(true);
        } catch (SQLException  | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema \n"+ex.getMessage()); 
        }
    }
    
    public static void desconectar() throws SQLException{
        conexion.close();
        
    
}
}

