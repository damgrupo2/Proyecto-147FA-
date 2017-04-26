/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Categoria.Transportista;
import Ventanas.CentroFormulario;
import Ventanas.Login;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.print.attribute.Size2DSyntax.MM;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 *
 * @author TeHenua
 */
public class MainTemporal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        //Login login =new Login();
        //login.setVisible(true);
        ControladorBaseDatos.conectar();
        CallableStatement cs= ControladorBaseDatos.getConexion().prepareCall("{call TODOS_CERRADOS_FECHAS (?,?,?)}");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse("20170425");
       java.sql.Date sql = new java.sql.Date(parsed.getTime());
       Date parsed2 = format.parse("20170426");
       java.sql.Date sql2 = new java.sql.Date(parsed.getTime());
        cs.setDate(1, sql);
        cs.setDate(2, sql2);
        cs.registerOutParameter(3, OracleTypes.CURSOR);
        cs.execute();
       ResultSet rs = (ResultSet) cs.getObject(3);
       while(rs.next()){
           System.out.println(rs.getInt("ID_TRABAJADOR"));
           System.out.println(rs.getDate("FECHA_PARTE"));
           System.out.println(rs.getDouble("HORAS_TOTALES"));
           System.out.println(rs.getInt("ABIERTO"));
       }
    }
    
}
