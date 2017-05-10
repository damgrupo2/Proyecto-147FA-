/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Modelo.Categoria;
import Modelo.Centro;
import Modelo.Trabajador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 7fbd06
 */
public class TrabajadorFormularioEdit extends javax.swing.JFrame {

    private List<Centro> centros;
    private Trabajador tra;
    private BuscarCentro bc;
    private BuscarUsuario bu;
    
    /**
     * Creates new form TrabajadorFormulario
     */
    public TrabajadorFormularioEdit(int id) {
        initComponents();
        tra = Trabajador.verTrabajador(id);
        jtNombre.setText(tra.getNombre());
        jtAp1.setText(tra.getAp1());
        jtAp2.setText(tra.getAp2());
        jtDireccion.setText(tra.getDireccion());
        jtDni.setText(tra.getDni());
        Double salario = tra.getSalario();
        String salarioS = salario.toString();
        jtSalario.setText(salarioS);
        jtTelEmp.setText(tra.getTelf_empresa());
        jtTelPer.setText(tra.getTelf_personal());
        jtfechanacimiento.setText(tra.getFechanac());
        jcCategoria.addItem(Categoria.Administrativo);
        jcCategoria.addItem(Categoria.Transportista);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jltitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtAp1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtAp2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtTelEmp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtTelPer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jcCategoria = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jtSalario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jlAviso = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtDni = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jbuscarboton = new javax.swing.JButton();
        jidcentro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jusuario = new javax.swing.JTextField();
        jbuscarboton1 = new javax.swing.JButton();
        jtfechanacimiento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jltitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jltitulo.setText("Editar trabajador");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nombre");

        jtNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Primer apellido");

        jtAp1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Segundo apellido");

        jtAp2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Dirección");

        jtDireccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Teléfono empresa");

        jtTelEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Teléfono personal");

        jtTelPer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Categoria");

        jcCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCategoriaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Salario");

        jtSalario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Fecha de nacimiento");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlAviso.setForeground(new java.awt.Color(51, 153, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("DNI");

        jtDni.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Centro al que pertenece");

        jbuscarboton.setText("Buscar");
        jbuscarboton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuscarbotonActionPerformed(evt);
            }
        });

        jidcentro.setEditable(false);
        jidcentro.setEnabled(false);
        jidcentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jidcentroActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Usuario");

        jusuario.setEditable(false);
        jusuario.setEnabled(false);
        jusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jusuarioActionPerformed(evt);
            }
        });

        jbuscarboton1.setText("Buscar");
        jbuscarboton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuscarboton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(jltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(133, 133, 133)
                                .addComponent(jtSalario))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtAp2)
                                    .addComponent(jtAp1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(114, 114, 114)
                                .addComponent(jtDireccion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(32, 32, 32)
                                .addComponent(jtfechanacimiento)
                                .addGap(695, 695, 695))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtTelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtTelPer, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(172, 172, 172)
                                .addComponent(jusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(43, 43, 43)
                                .addComponent(jidcentro, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbuscarboton)
                            .addComponent(jbuscarboton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jButton1)
                        .addGap(107, 107, 107))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtAp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtTelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jtTelPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtfechanacimiento))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAviso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jidcentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuscarboton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuscarboton1))))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre = jtNombre.getText();
        String ap1 = jtAp1.getText();
        String ap2 = jtAp2.getText();
        String direccion = jtDireccion.getText();
        String telEmp = jtTelEmp.getText();
        String telPer = jtTelPer.getText();
        String categoria = jcCategoria.getSelectedItem().toString();
        String dni = jtDni.getText();
        double salario=0;
        if(jtSalario.getText().compareTo("")!=0){
            salario = Double.parseDouble(jtSalario.getText());
        }
        String fechanac=jtfechanacimiento.getText();
        Trabajador t = new Trabajador(dni ,nombre, ap1, ap2, direccion, telEmp, telPer, Categoria.Transportista, salario, fechanac);
        boolean correcto = t.modificarTrabajador();
        if(correcto){
            jlAviso.setText("Guardado correctamente");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCategoriaActionPerformed
        
    }//GEN-LAST:event_jcCategoriaActionPerformed

    private void jbuscarbotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuscarbotonActionPerformed
        bc=new BuscarCentro();
        bc.setTfe(this);
        bc.setVisible(true);
    }//GEN-LAST:event_jbuscarbotonActionPerformed

    private void jidcentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jidcentroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jidcentroActionPerformed

    private void jusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jusuarioActionPerformed

    private void jbuscarboton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuscarboton1ActionPerformed
        bu=new BuscarUsuario();
        bu.setTfe(this);
        bu.setVisible(true);
    }//GEN-LAST:event_jbuscarboton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TrabajadorFormularioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrabajadorFormularioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrabajadorFormularioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrabajadorFormularioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        int id = TrabajadorListado.getId();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrabajadorFormularioEdit(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbuscarboton;
    private javax.swing.JButton jbuscarboton1;
    private javax.swing.JComboBox jcCategoria;
    private javax.swing.JTextField jidcentro;
    private javax.swing.JLabel jlAviso;
    private javax.swing.JLabel jltitulo;
    private javax.swing.JTextField jtAp1;
    private javax.swing.JTextField jtAp2;
    private javax.swing.JTextField jtDireccion;
    private javax.swing.JTextField jtDni;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JTextField jtSalario;
    private javax.swing.JTextField jtTelEmp;
    private javax.swing.JTextField jtTelPer;
    private javax.swing.JTextField jtfechanacimiento;
    private javax.swing.JTextField jusuario;
    // End of variables declaration//GEN-END:variables
}
