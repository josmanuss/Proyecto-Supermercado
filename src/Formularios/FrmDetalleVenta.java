/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;


import Clases.*;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Jose Manuel
 */
public class FrmDetalleVenta extends javax.swing.JFrame {
    private Ventas ven;
    DefaultTableModel modelo;

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

    LocalDateTime fechaHoraActual = LocalDateTime.now();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
    String fechaHoraFormateada = fechaHoraActual.format(formateador);
    
    
    
    public Ventas getVen() {
        return ven;
    }

    public void setVen(Ventas ven) {
        this.ven = ven;
    }
    
    /**
     * Creates new form FrmDetalleVenta
     */
    public FrmDetalleVenta() {
        initComponents();
    }
    
    public FrmDetalleVenta(Cliente clien) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jLabel4.setText(clien.getNombre()+" "+clien.getApellidoPaterno()+" "+clien.getApellidoMaterno());
    }
    
    public void listarDetalle( ListaProductos lP){
        modelo = (DefaultTableModel) jTDetalleVenta.getModel();
        modelo.setRowCount(0);
        NodoProducto aux = lP.getCab();
        while ( aux != null ){
            modelo.addRow(aux.getPro().mostrarProducto());
            aux = aux.getSgte();
        }
        jTDetalleVenta.setModel(modelo);
    }


    public static String DateAString(Date fecha){
        return sdf.format(fecha);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTDetalleVenta = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jbImprimirDetalle = new javax.swing.JButton();

        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "PRECIO", "CATEGORIA", "MARCA", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTDetalleVenta);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 680, 230));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 210, 60));

        jbImprimirDetalle.setText("IMPRIMIR DETALLE");
        jbImprimirDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImprimirDetalleActionPerformed(evt);
            }
        });
        jPanel1.add(jbImprimirDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbImprimirDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImprimirDetalleActionPerformed
        try{
                FileOutputStream archivo;
                String nombreBase = "Detalle_Venta_" + ven.getCodigo()+"_"+DateAString(new Date());
                String limite = "_";
                String extension = ".pdf";
                int contador = 0;
                String nombreArchivo;
                nombreArchivo = nombreBase + limite + String.valueOf(contador+1) + extension;
                while (new File("src/pdf/" + nombreArchivo).exists()) {
                    contador++;
                    nombreArchivo = nombreBase + limite + String.valueOf(contador+1) + extension;
                } 
                File arch = new File("src/pdf/" + nombreArchivo);
                archivo = new FileOutputStream(arch);
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);
                doc.open();
                Image img = Image.getInstance("src/Imagenes/logoMetro.png");
                //Datos empresa
                Paragraph fecha = new Paragraph();
                fecha.add(Chunk.NEWLINE);
                fecha.add("Fecha: "+DateAString(new Date()));
                PdfPTable Encabezado = new PdfPTable(4);
                Encabezado.setWidthPercentage(100);
                Encabezado.getDefaultCell().setBorder(0);
                float[] ColumnaEncabezado = new float[]{20f,30f,70f,40f};
                Encabezado.setWidths(ColumnaEncabezado);
                Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
                Encabezado.addCell(img);
                String ruc = "123456789";
                String nom = "Metro Luis Gonzales";
                String tel = "999999999";
                String dir = "Lima";
                String ra = "JMDL";
                Encabezado.addCell(" ");
                Encabezado.addCell("RUC: "+ruc+"\nNOMBRE: "+nom+"\nTELEFONO: "+tel+"\nDIRECCION: "+dir+"\nRAZON SOCIAL: "+ra);
                Encabezado.addCell(fecha);
                doc.add(Encabezado);
                //CLIENTE                
                Paragraph cli = new Paragraph();
                cli.add(Chunk.NEWLINE);
                cli.add("DATOS DEL CLIENTE" + "\n\n");
                doc.add(cli);
                PdfPTable proveedor = new PdfPTable(4);
                proveedor.setWidthPercentage(100);
                proveedor.getDefaultCell().setBorder(0);
                float[] columnWidthsCliente = new float[]{20f, 50f, 30f,40f};
                proveedor.setWidths(columnWidthsCliente);
                proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cliNom = new PdfPCell(new Phrase("Nombre"));
                PdfPCell cliApePa = new PdfPCell(new Phrase("Apellido paterno"));
                PdfPCell cliApeMa = new PdfPCell(new Phrase("Apellido materno"));
                PdfPCell cliDni = new PdfPCell(new Phrase("DNI"));
                proveedor.addCell(cliNom);
                proveedor.addCell(cliApePa);
                proveedor.addCell(cliApeMa);                
                proveedor.addCell(cliDni);
                cliNom.setBorder(0);
                cliApePa.setBorder(0);
                cliApeMa.setBorder(0);
                cliDni.setBorder(0);
                proveedor.addCell(ven.getCli().getNombre());
                proveedor.addCell(ven.getCli().getApellidoPaterno());               
                proveedor.addCell(ven.getCli().getApellidoMaterno());
                proveedor.addCell(ven.getCli().getDNI());   
                cliNom.setBorder(0);
                cliApePa.setBorder(0);
                cliApeMa.setBorder(0);
                cliDni.setBorder(0);
                doc.add(proveedor);
                //PRODCUTOS VENDIDOS
                Paragraph pro = new Paragraph();
                pro.add(Chunk.NEWLINE);
                pro.add("Productos vendidos: "+"\n\n");
                doc.add(pro);
                PdfPTable tablaPro = new PdfPTable(5);
                tablaPro.setWidthPercentage(100);
                tablaPro.getDefaultCell().setBorder(0);
                float[] ColumnaPro = new float[]{20f,30f,30f,30f,30f};
                tablaPro.setWidths(ColumnaPro);
                tablaPro.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell pro1 = new PdfPCell(new Phrase("NOMBRE"));
                PdfPCell pro2 = new PdfPCell(new Phrase("PRECIO"));
                PdfPCell pro3 = new PdfPCell(new Phrase("CATEGORIA"));
                PdfPCell pro4 = new PdfPCell(new Phrase("MARCA"));
                PdfPCell pro5 = new PdfPCell(new Phrase("CANTIDAD"));            
                tablaPro.addCell(pro1);
                tablaPro.addCell(pro2);
                tablaPro.addCell(pro3);
                tablaPro.addCell(pro4);
                tablaPro.addCell(pro5);
                pro1.setBorder(0);
                pro2.setBorder(0);
                pro3.setBorder(0);
                pro4.setBorder(0);
                pro5.setBorder(0);
                NodoProducto nPro = ven.getlP().getCab();
                while ( nPro != null ){
                    tablaPro.addCell(nPro.getPro().getNombre());
                    tablaPro.addCell(String.valueOf(nPro.getPro().getPrecio()));
                    tablaPro.addCell(nPro.getPro().getCategoria());
                    tablaPro.addCell(nPro.getPro().getMarca());
                    tablaPro.addCell(String.valueOf(nPro.getPro().getStock()));
                    pro1.setBorder(0);
                    pro2.setBorder(0);
                    pro3.setBorder(0);
                    pro4.setBorder(0);
                    pro5.setBorder(0);
                    nPro = nPro.getSgte();
                }
                doc.add(tablaPro);
                doc.close();
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    Desktop.getDesktop().open(arch.getParentFile());
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el directorio");
                }
                JOptionPane.showMessageDialog(null, "Archivo "+nombreArchivo+" creado con exito");
                this.dispose();
            }catch(DocumentException | HeadlessException | IOException e){
                 JOptionPane.showMessageDialog(null, "error de creacion del archivo"); 
            }    
    }//GEN-LAST:event_jbImprimirDetalleActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDetalleVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTDetalleVenta;
    private javax.swing.JButton jbImprimirDetalle;
    // End of variables declaration//GEN-END:variables
}
