package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControladorBaseDatos {
    
    private static Connection conexion;
    
<<<<<<< HEAD
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
           /*JOptionPane.showMessageDialog(, ex, "Error de conexion", 0);*/
       
=======
   public static void conectar(){ 
        try{ 
            Class.forName("oracle.jdbc.OracleDriver"); 
            //String login = "Aplicacion"; 
            //String pass = "a12345Abcde"; 
            //String url = "jdbc:oracle:thin:@10.10.10.9:1521:db12102";             
            String login = "noc05";
            String pass = "noc05";
            String url = "jdbc:oracle:thin:@SrvOracle:1521:orcl";
            conexion = DriverManager.getConnection(url,login,pass); 
            conexion.setAutoCommit(true); 
        }catch(ClassNotFoundException | SQLException ex){ 
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }  
>>>>>>> master
    }
   
   public static void desconectar() throws SQLException{ 
        conexion.close(); 
    } 
     
    public static Connection getConexion(){ 
        return conexion; 
    } 
}

