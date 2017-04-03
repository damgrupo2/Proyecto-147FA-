
import java.sql.PreparedStatement;
import java.util.Date;

public class General {

    public static void main(String[] args) throws ClassNotFoundException {
       
   ControladorBaseDatos cbd=new ControladorBaseDatos();
       cbd.crearConexion();
        
       Date date=new Date("2017/12/31");
       Trabajador t=new Trabajador();
       t.setFechanac(date);
    
       
    
    
    
    
    
    
    }
 
}
