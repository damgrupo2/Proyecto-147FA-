/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Categoria.Transportista;
import Ventanas.CentroFormulario;
import Ventanas.Login;
import java.util.Date;

/**
 *
 * @author TeHenua
 */
public class MainTemporal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Centro c = new Centro("centro", "calle numero piso", "01001", "localidad", "provincia", "945945945");
        c.setId_centro(2);
        Categoria ca = Transportista;
        java.util.Date fecha = new Date("01/01/1990");
        Trabajador t = new Trabajador("dni","nombre","ap1","ap2","direccion","telf_empresa","telf_personal", ca, 1200,fecha);
        t.guardarTrabajador("centro");*/
        //Usuario u = new Usuario("TeHenua", "aaaaa");
        //Categoria c =u.hacerLogin();
        //System.out.println(c);
        Login login =new Login();
        login.setVisible(true);
        
    }
    
}
