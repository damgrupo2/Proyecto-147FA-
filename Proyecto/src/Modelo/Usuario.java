/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 7fbd06
 */
public class Usuario {
    private String usuario;
    private String contraseña;
    
    private Trabajador trabajador;

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public void añadirTrabajador(Trabajador trabajador){
        this.trabajador = trabajador;
    }
    
    public void guardarUsuario(){
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_NUEVO(?,?)}");
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            cs.execute();
            
            /*PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("INSERT INTO USUARIO VALUES(?,ORA_HASH(?))");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.execute();*/
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());        
        }
    }
    
    public void modificarUsuario(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("UPDATE USUARIO SET ID_USUARIO=?,CONTRASEÑA=ORA_HASH(?)");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarUsuario(){
        
    }
    
    public Categoria hacerLogin(){
        Categoria c = null;
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().prepareCall("{call USUARIOS.USUARIO_LOGIN(?,?,?)}");
            cs.setString(1, usuario);
            cs.setNString(2, contraseña);
            cs.registerOutParameter(3, OracleTypes.VARCHAR);
            cs.execute();
            String categoria =cs.getString(3);
            switch(categoria){
                case "Transportista":
                    c = Categoria.Transportista;
                    break;
                case "Administrativo":
                    c = Categoria.Administrativo;
                    break;
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());       
        }
        return c;
    }
}
