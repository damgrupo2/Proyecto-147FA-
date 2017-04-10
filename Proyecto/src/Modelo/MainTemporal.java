/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author TeHenua
 */
public class MainTemporal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Centro c = new Centro(1, "centro", "calle numero piso", "01001", "provincia", "945945945");
        c.guardarCentro();
    }
    
}
