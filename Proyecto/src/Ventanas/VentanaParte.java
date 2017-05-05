/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Modelo.Centro;
import Modelo.Parte;
import Modelo.Trabajador;
import Modelo.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose
 */
public class VentanaParte extends javax.swing.JFrame {   
    private VentanaReparto vr;
    private static Trabajador t=Modelo.Usuario.getT();
    private static Parte p=Modelo.Usuario.getP();
    private static Vehiculo v=Modelo.Usuario.getV();
    private DefaultTableModel model;
    private BuscarVehiculo bv;
    private static int id;

    public void setJtVehiculo(String id) {
        this.jtVehiculo.setText(id);
    }
    
    public static void setVehiculo(Vehiculo v) {
        VentanaParte.v=v;
    }

    public void recargarTabla() {
        DefaultTableModel model= (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        List<Modelo.Reparto> repartos = p.getRepartos();
        for(Modelo.Reparto r:repartos){
            model.insertRow(model.getRowCount(), new Object[]{r.getAlbaran(),r.getHoraInicio(),r.getHoraFin()});
        }
    }
    
    public static void añadeReparto(Modelo.Reparto r){
        p.getRepartos().add(r);
    }

    /**
     * Creates new form VentanaParte
     */
    
    public static void setId(int id) {
        
        VentanaParte.id = id;
    }
    
    public static void configurarVehiculoVentana(int id){
        //jtV
    }

    public VentanaParte() {
        initComponents();
        if(p.getFecha()!=null){
            Double kmINI = p.getKmInicio();
            String kmINi = kmINI.toString();
            jtKmIni.setText(kmINi);
            Double kmFIN = p.getKmFin();
            String kmFin = kmFIN.toString();
            jtKmFin.setText(kmFin);
            Double gD = p.getGasoil();
            String g = gD.toString();
            jtGasoil.setText(g);
            Double aD = p.getAutopista();
            String a= aD.toString();
            jtAutopista.setText(a);
            Double dd = p.getDietas();
            String d = dd.toString();
            jtDietas.setText(d);
            Double od = p.getOtrosGastos();
            String o = od.toString();
            jtOtros.setText(o);
            jtIncidencias.setText(p.getIncidencias());
            Integer idv=p.getVehiculo().getIdVehiculo();
            String idVe=idv.toString();
            jtVehiculo.setText(idVe);
            recargarTabla();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtKmIni = new javax.swing.JTextField();
        jtKmFin = new javax.swing.JTextField();
        jtGasoil = new javax.swing.JTextField();
        jtAutopista = new javax.swing.JTextField();
        jtDietas = new javax.swing.JTextField();
        jtOtros = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtVehiculo = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtIncidencias = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbGuardar = new javax.swing.JButton();
        jbCerrar = new javax.swing.JButton();
        jlAviso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("AÑADIR REPARTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("KM INICIALES");

        jLabel2.setText("KM FINALES");

        jLabel3.setText("GASOIL");

        jLabel4.setText("AUTOPISTA");

        jLabel5.setText("DIETAS");

        jLabel6.setText("OTROS GASTOS");

        jLabel7.setText("INCIDENCIAS");

        jLabel9.setText("PARTE");

        jtKmFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtKmFinActionPerformed(evt);
            }
        });

        jtAutopista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtAutopistaActionPerformed(evt);
            }
        });

        jLabel10.setText("VEHICULO");

        jtVehiculo.setEditable(false);
        jtVehiculo.setEnabled(false);
        jtVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtVehiculoActionPerformed(evt);
            }
        });

        jbBuscar.setText("BUSCAR");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jtIncidencias.setColumns(20);
        jtIncidencias.setRows(5);
        jScrollPane1.setViewportView(jtIncidencias);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ALBARÁN", "HORA INICIO", "HORA FIN"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        jbCerrar.setText("Cerrar");
        jbCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCerrarActionPerformed(evt);
            }
        });

        jlAviso.setForeground(new java.awt.Color(51, 255, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAviso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbGuardar)
                        .addGap(56, 56, 56)
                        .addComponent(jbCerrar)
                        .addGap(184, 184, 184))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(119, 119, 119))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbBuscar)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtGasoil, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtKmIni)
                                    .addComponent(jtAutopista)
                                    .addComponent(jtDietas)
                                    .addComponent(jtOtros)
                                    .addComponent(jtKmFin)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jtVehiculo))))
                        .addGap(89, 89, 89))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtKmIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtKmFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtGasoil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtAutopista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtDietas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar)
                    .addComponent(jLabel10))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton1)))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGuardar)
                    .addComponent(jbCerrar)
                    .addComponent(jlAviso))
                .addGap(332, 332, 332))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        bv=new BuscarVehiculo();
        bv.setVp(this);
        bv.setVisible(true);
       
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jtAutopistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtAutopistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtAutopistaActionPerformed

    private void jtKmFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtKmFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtKmFinActionPerformed

    private void jtVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtVehiculoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        vr=new VentanaReparto();
        vr.setVp(this);
        vr.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        p.setKmInicio(Double.parseDouble(jtKmIni.getText()));
        p.setKmFin(Double.parseDouble(jtKmFin.getText()));
        p.setGasoil(Double.parseDouble(jtGasoil.getText()));
        p.setAutopista(Double.parseDouble(jtAutopista.getText()));
        p.setDietas(Double.parseDouble(jtDietas.getText()));
        p.setOtrosGastos(Double.parseDouble(jtOtros.getText()));
        p.setIncidencias(jtIncidencias.getText());
        Date fecha= new Date();
        p.setFecha(fecha);
        boolean correcto = p.guardarParte(t.getId_trabajador(),v.getIdVehiculo());
        if(correcto){
            jlAviso.setText("Guardado correctamente");
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCerrarActionPerformed
        boolean correcto = p.cerrarParte();
        if(correcto){
            jlAviso.setText("Parte cerrado correctamente");
        }
    }//GEN-LAST:event_jbCerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaParte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaParte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaParte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaParte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
          t=Modelo.Usuario.getT();
          p=Modelo.Usuario.getP();
          v=Modelo.Usuario.getV();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaParte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbCerrar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JLabel jlAviso;
    private javax.swing.JTextField jtAutopista;
    private javax.swing.JTextField jtDietas;
    private javax.swing.JTextField jtGasoil;
    private javax.swing.JTextArea jtIncidencias;
    private javax.swing.JTextField jtKmFin;
    private javax.swing.JTextField jtKmIni;
    private javax.swing.JTextField jtOtros;
    private javax.swing.JTextField jtVehiculo;
    // End of variables declaration//GEN-END:variables
}
