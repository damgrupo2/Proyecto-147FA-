package Modelo;


import Modelo.ControladorBaseDatos;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import oracle.jdbc.OracleTypes;

public class General {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       
   ControladorBaseDatos cbd=new ControladorBaseDatos();
       cbd.crearConexion();
        
        ResultSet rs= null;
        String sql="{call MOSTRARTRABAJADORES(?)}";
       
        CallableStatement cs = cbd.getConexion().prepareCall(sql);
             cs.registerOutParameter(1, OracleTypes.CURSOR);
             cs.execute();
             rs=(ResultSet)cs.getObject(1);
            
             rs.next();
        rs.getArray(1);
        System.out.println(rs.getString(sql)));
    
       
    
    
    
    
    
    
    }
 
}
