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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private String fechanac;
    
    private Centro centro;
    private List<Parte> parteList=new ArrayList<Parte>();
    private List<Aviso> avisosList=new ArrayList<>();
    private Usuario usuario;
    

    public Trabajador() {
    }

    public Trabajador( String dni, String nombre, String ap1, String ap2, String direccion, String telf_empresa, String telf_personal, Categoria categoria, double salario, String fechanac) {
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

    public Trabajador(String nombre, String ap1, String ap2) {
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
    }
    
    

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }
    
    public void guardarTrabajador(String centro,Trabajador t){
         int id_centro=0;
        try {
            ControladorBaseDatos.conectar();
        String query="{call ID_CENTRO_NOMBRE (?,?)}";
        CallableStatement cst=ControladorBaseDatos.getConexion().prepareCall(query);
        cst.setString(1, centro);
        cst.registerOutParameter(2, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs= (ResultSet)cst.getObject(1);
        while(rs.next()){
           
             id_centro=rs.getInt("ID_CENTRO");
            
        }
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
       
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("INSERT INTO TRABAJADOR (DNI,NOMBRE,AP1,AP2,DIRECCION,TELF_EMPRESA, TELF_PERSONAL,CATEGORIA, "
                                        + "SALARIO, FECHANAC, ID_CENTRO) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, t.dni);
            ps.setString(2, t.nombre);
            ps.setString(3, t.ap1);
            ps.setString(4, t.ap2);
            ps.setString(5, t.direccion);
            ps.setString(6, t.telf_empresa);
            ps.setString(7, t.telf_personal);
            ps.setString(8, t.categoria.toString());
            ps.setDouble(9, t.salario);
            ps.setString(10, t.fechanac);
            ps.setInt(11, id_centro);
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
      
    }
    
    public List<Trabajador> listarTrabajadores() throws SQLException{
        ControladorBaseDatos.conectar();
        String query="{call TODOS_TRABAJADORES (?)}";
        CallableStatement cst=ControladorBaseDatos.getConexion().prepareCall(query);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs= (ResultSet)cst.getObject(1);
        List<Trabajador> trabajadores=new ArrayList<>();
        while(rs.next()){
           
             String nombre=rs.getString("NOMBRE");
             
             String ap1=rs.getString("AP1");
             
             String ap2=rs.getString("AP2");
           
             Trabajador t=new Trabajador(nombre, ap1,ap2);
             trabajadores.add(t);
            
        }
        
        ControladorBaseDatos.desconectar();
        return trabajadores;
        
    }
    
     public void modificarTrabajador(Trabajador t) {
         try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("UPDATE CENTRO SET NOMBRE=?, AP1=?, AP2=?, LOC=?, PROVINCIA=?, TELF=? "
                                        + "WHERE ID_CENTRO=?");
            ps.setString(1, t.dni );
            ps.setString(2, t.nombre);
            ps.setString(3, t.ap1);
            ps.setString(4, t.ap2);
            ps.setString(5, t.direccion);
            ps.setString(6, t.telf_empresa);
            ps.setString(7, t.telf_personal);
            ps.setString(8, t.categoria.toString());
            ps.setDouble(9, t.salario);
            ps.setString(10, t.fechanac);
            
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
     
    }
     public List<Trabajador> verTrabajador() throws SQLException{
            ControladorBaseDatos.conectar();
        String query="{call UN_TRABAJADOR (?,?)}";
        CallableStatement cst=ControladorBaseDatos.getConexion().prepareCall(query);
        cst.setInt(1, 1);
        cst.registerOutParameter(2, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs= (ResultSet)cst.getObject(2);
        List<Trabajador> trabajadores=new ArrayList<>();
        while(rs.next()){
             int id_trabajador=rs.getInt("ID_TRABAJADOR");
             String dni=rs.getString("DNI");
             String nombre=rs.getString("NOMBRE");
             String ap1=rs.getString("AP1");
             String ap2=rs.getString("AP2");
             String direccion =rs.getString("DIRECCION");
             String telf_empresa=rs.getString("TELF_EMPRESA");
             String telf_personal=rs.getString("TELF_PERSONAL");
             String categoria =rs.getString("CATEGORIA");
             double salario =rs.getDouble("SALARIO");
             String fechanac= rs.getString("FECHANAC");
             
             Trabajador t=new Trabajador();
             trabajadores.add(t);
        }
        
        ControladorBaseDatos.desconectar();
        return trabajadores;
    }
                        
     public void borrarTrabajador(){
          try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = Modelo.ControladorBaseDatos.getConexion().prepareStatement
                    ("DELETE TRABAJADOR WHERE ID_TRABAJADOR=?");
            ps.setInt(1, id_trabajador);
            
 
            ps.execute();
            ControladorBaseDatos.desconectar();
        } catch (Exception e) {
           
        }
        
    }
     
     
}
