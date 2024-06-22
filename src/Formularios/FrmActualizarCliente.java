/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Cliente;
import Clases.ListaClientes;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Manuel
 */
public class FrmActualizarCliente extends javax.swing.JFrame {

    
    private ListaClientes lC;
    private Cliente cli;
    
    public ListaClientes getlC() {
        return lC;
    }

    public void setlC(ListaClientes lC) {
        this.lC = lC;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    /**
     * Creates new form FrmActualizarCliente
     */
    public FrmActualizarCliente() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
    }
    
    public void asignarValores(){
        tFNombreCliente.setText(this.cli.getNombre());
        tFApePaCliente.setText(this.cli.getApellidoPaterno());
        tFApeMaCliente.setText(this.cli.getApellidoMaterno());
        tFEdadCliente.setText(String.valueOf(this.cli.getEdad()));
        tFDireccionCliente.setText(this.cli.getDireccion());
        tFDNICliente.setText(this.cli.getDNI());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        tFNombreCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tFApePaCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tFApeMaCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tFEdadCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tFDireccionCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tFDNICliente = new javax.swing.JTextField();
        jBRegistroCliente = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jBCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tFNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 78, 179, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombres");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 78, 156, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido paterno");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 119, 156, 30));

        tFApePaCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFApePaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 119, 179, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Apellido materno");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 155, 156, 30));

        tFApeMaCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFApeMaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 156, 179, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Edad");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 197, 156, 30));

        tFEdadCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFEdadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 197, 179, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Direccion");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 238, 156, 30));

        tFDireccionCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 239, 179, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DNI");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 275, 156, 30));

        tFDNICliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFDNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 276, 179, 30));

        jBRegistroCliente.setBackground(new java.awt.Color(0, 255, 51));
        jBRegistroCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBRegistroCliente.setText("ACTUALIZAR");
        jBRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistroClienteActionPerformed(evt);
            }
        });
        jPanel6.add(jBRegistroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 338, 179, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("CLIENTES");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 163, 49));

        jBCerrar.setBackground(new java.awt.Color(0, 255, 51));
        jBCerrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBCerrar.setText("CERRAR");
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });
        jPanel6.add(jBCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 338, 179, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistroClienteActionPerformed
        if ( this.tFNombreCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta nombres","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFApePaCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido paterno","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFApeMaCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido materno","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFEdadCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta edad","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFDireccionCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta direccion","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFDNICliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta DNI","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Cliente newCliente = new Cliente(this.cli.getCodigo(),tFNombreCliente.getText(),tFApePaCliente.getText(),tFApeMaCliente.getText(),Integer.parseInt(tFEdadCliente.getText()),tFDireccionCliente.getText(),tFDNICliente.getText());
        this.lC.modificarCliente(newCliente);
        this.lC.actualizarArchivo();
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_jBRegistroClienteActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmActualizarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBRegistroCliente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField tFApeMaCliente;
    private javax.swing.JTextField tFApePaCliente;
    private javax.swing.JTextField tFDNICliente;
    private javax.swing.JTextField tFDireccionCliente;
    private javax.swing.JTextField tFEdadCliente;
    private javax.swing.JTextField tFNombreCliente;
    // End of variables declaration//GEN-END:variables
}
