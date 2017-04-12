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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 7fbd06
 */
public class Trabajador {
    
    private int id_trabajador;
    private String dni;
    private String nombre;
    private String ap1;
    private String ap2;
    private String direccion;
    private String telf_empresa;
    private String telf_personal;
    private Categoria categoria;
    private double salario;
    private java.sql.Date fechanac;
    
    private Centro centro;
    private List<Parte> parteList=new ArrayList<Parte>();
    private List<Aviso> avisosList=new ArrayList<>();
    private Usuario usuario;
    

    public Trabajador() {
    }

    public Trabajador( String dni, String nombre, String ap1, String ap2, 
            String direccion, String telf_empresa, String telf_personal, 
            Categoria categoria, double salario, java.sql.Date fechanac) {
        this.dni = dni;
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.direccion = direccion;
        this.telf_empresa = telf_empresa;
        this.telf_personal = telf_personal;
        this.categoria = categoria;
        this.salario = salario;
        this.fechanac = fechanac;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelf_empresa() {
        return telf_empresa;
    }

    public void setTelf_empresa(String telf_empresa) {
        this.telf_empresa = telf_empresa;
    }

    public String getTelf_personal() {
        return telf_personal;
    }

    public void setTelf_personal(String telf_personal) {
        this.telf_personal = telf_personal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(java.sql.Date fechanac) {
        this.fechanac = fechanac;
    }

    public void añadirCentro(Centro centro) {
        this.centro = centro;
        centro.añadirTrabajador(this);
    }
    
    public void añadirUsuario(Usuario usuario){
        this.usuario = usuario;
        usuario.añadirTrabajador(this);
    }
    
    public boolean guardarTrabajador(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("INSERT INTO TRABAJADOR(DNI, NOMBRE, AP1, AP2, DIRECCION, TELF_EMPRESA, TELF_PERSONAL," +
                            "CATEGORIA, SALARIO, FECHANAC) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,dni);
            ps.setString(2, nombre);
            ps.setString(3, ap1);
            ps.setString(4, ap2);
            ps.setString(5, direccion);
            ps.setString(6, telf_empresa);
            ps.setString(7, telf_personal);
            ps.setString(8, categoria.toString());
            ps.setDouble(9, salario);
            java.sql.Date f = new java.sql.Date(fechanac.getTime());
            ps.setDate(10, f);                   
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }

    }
    
    public boolean modificarTrabajador(Trabajador t) {
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("UPDATE TRABAJADOR SET NOMBRE=?,AP1=?,"
                            + "AP2=?,DIRECCION=?,TELF_EMPRESA=?,TELF_PERSONAL=?,"
                            + "CATEGORIA=?, SALARIO=?, FECHANAC=?, DNI=? "
                            + "WHERE ID_TRABAJADOR=?");
            ps.setString(1, nombre);
            ps.setString(2, ap1);
            ps.setString(3, ap2);
            ps.setString(4, direccion);
            ps.setString(5, telf_empresa);
            ps.setString(6, telf_personal);
            ps.setString(7, categoria.toString());
            ps.setDouble(8, salario);
            ps.setDate(9, fechanac);
            ps.setString(10, dni);
            ps.setInt(11, id_trabajador);
            ps.executeUpdate();
            ControladorBaseDatos.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
            return false;
        }
    }
    
    public void borrarTrabajador(){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR=?");
            ps.setInt(1, id_trabajador);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
    }
    
    public List<Trabajador> listarTrabajadores(){
        List<Trabajador> trabajadores = new ArrayList<>();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRABAJADORES(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                Trabajador t= new Trabajador();
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setDireccion(rs.getString("DIRECCION"));
                t.setTelf_empresa(rs.getString("TELF_EMPRESA"));
                t.setTelf_personal(rs.getString("TELF_PERSONAL"));
                t.setSalario(rs.getDouble("SALARIO"));
                t.setFechanac(rs.getDate("FECHANAC"));
                String categoria =  rs.getString("CATEGORIA");
                Categoria c = null;
                switch(categoria){
                    case "Administrativo":
                        c = Categoria.Administrativo;
                        break;
                    case "Transportista":
                        c = Categoria.Transportista;
                }
                t.setCategoria(c);
                trabajadores.add(t);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage()); 
        }
        return trabajadores;
        
    }
    
    public Trabajador verTrabajador(int id){
        Trabajador t= new Trabajador();
        try {
            ControladorBaseDatos.conectar();
            CallableStatement cs = ControladorBaseDatos.getConexion().
                    prepareCall("{call TRABAJADORES.CONSULTA_TRABAJADOR(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            while (rs.next()) {
                t.setDni(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setAp1(rs.getString("AP1"));
                t.setAp2(rs.getString("AP2"));
                t.setDireccion(rs.getString("DIRECCION"));
                t.setTelf_empresa(rs.getString("TELF_EMPRESA"));
                t.setTelf_personal(rs.getString("TELF_PERSONAL"));
                t.setSalario(rs.getDouble("SALARIO"));
                t.setFechanac(rs.getDate("FECHANAC"));
                String categoria =  rs.getString("CATEGORIA");
                Categoria c = null;
                switch(categoria){
                    case "Administrativo":
                        c = Categoria.Administrativo;
                        break;
                    case "Transportista":
                        c = Categoria.Transportista;
                }
                t.setCategoria(c);
            }
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema \n"+ex.getMessage());
        }
        return t;
        
    }


}
