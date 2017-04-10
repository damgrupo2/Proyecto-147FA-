/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Date fechanac;
    
    private Centro centro;
    private List<Parte> parteList=new ArrayList<Parte>();
    private List<Aviso> avisosList=new ArrayList<>();
    private Usuario usuario;
    

    public Trabajador() {
    }

    public Trabajador( String dni, String nombre, String ap1, String ap2, String direccion, String telf_empresa, String telf_personal, Categoria categoria, double salario, Date fechanac) {
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

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    void añadirCentro(Centro centro) {
        this.centro = centro;
        centro.añadirTrabajador(this);
    }

    public void guardarTrabajador(String nombreCentro){
        try {
            ControladorBaseDatos.conectar();
            PreparedStatement ps = ControladorBaseDatos.getConexion().prepareStatement("SELECT ID_CENTRO FROM CENTRO WHERE NOMBRE = ?");
            ps.setString(1,nombreCentro);
            ResultSet rs = ps.executeQuery();
            int idCentro=0;
            while(rs.next()){
                idCentro = rs.getInt("ID_CENTRO");
            }
            ps = ControladorBaseDatos.getConexion()
                    .prepareStatement("INSERT INTO TRABAJADOR(DNI, NOMBRE, AP1, AP2, DIRECCION, TELF_EMPRESA, TELF_PERSONAL," +
                            "CATEGORIA, SALARIO, FECHANAC, ID_CENTRO) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,dni);
            ps.setString(2, nombre);
            ps.setString(3, ap1);
            ps.setString(4, ap2);
            ps.setString(5, direccion);
            ps.setString(6, telf_empresa);
            ps.setString(7, telf_personal);
            ps.setObject(8, categoria);
            ps.setDouble(9, salario);
            java.sql.Date f = new java.sql.Date(fechanac.getTime());
            ps.setDate(10, f);
            ps.setInt(11, idCentro);                    
            ControladorBaseDatos.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Trabajador> listarTrabajadores(){
        return null;
        
    }
    
     public void modificarTrabajador(Trabajador t) {

    }
                        
     public void borrarTrabajador(){
        
    }
     
      public Trabajador verTrabajador(){
        return null;
        
    }


}
