/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.awt.HeadlessException;



/**
 *
 * @author Jose Manuel
 */
public class Ventas {
    private String codigo;
    private Cliente cli;
    private Trabajador traVen;
    private ListaProductos lP;
    private double montoPago;
    private double vuelto;
    private String tipoDePago;
    private String fechaVenta;
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }
		
    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Trabajador getTraVen() {
        return traVen;
    }

    public void setTraVen(Trabajador traVen) {
        this.traVen = traVen;
    }

    public ListaProductos getlP() {
        return lP;
    }

    public void setlP(ListaProductos lP) {
        this.lP = lP;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Ventas(){
        
    }
    
    public Ventas(String codigo,Cliente cli,Trabajador traVen,ListaProductos lP,double montoPago,double vuelto,String tipoPago,String fechaVenta){
        this.codigo = codigo;
        this.cli = cli;
        this.traVen = traVen;
        this.lP = lP;
        this.montoPago = montoPago;
        this.vuelto = vuelto;
        this.tipoDePago = tipoPago;
        this.fechaVenta = fechaVenta;
    }
    
    
    public Object[] mostrarVenta(){
        Object[] fila = new Object[5];
        fila[0] = this.codigo;
        fila[1] = this.cli.getCodigo();
        fila[2] = this.montoPago;
        fila[3] = this.vuelto;
        fila[4] = this.traVen.getCodigo();
        return fila;
    }

    public static String DateAString(Date fecha){
        return sdf.format(fecha);
    }
    
    
    public void generarPDF() {
        try {
            int contador = 0;
            FileOutputStream archivo;
            String nombreArchivoPDF = this.generarNombreArchivo(contador);
            File arch = new File(nombreArchivoPDF);
            archivo = new FileOutputStream(arch);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            agregarEncabezado(doc);
            agregarDatosCliente(doc);
            double doubleTotalVenta = agregarProductosVendidos(doc);
            agregarMontoPagado(doc);
            agregarSubtotalTotal(doc,doubleTotalVenta);
            agregarVuelto(doc);
            agregarDetalleVenta(doc);
            doc.close();

            mostrarMensajeExito(arch);
            abrirArchivo(arch);

        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private String generarNombreArchivo(int contador) {
        String nombreArchivoPDF = "src/pdf/Detalle_Venta_" + this.getCodigo() + "_" + String.valueOf(contador + 1) + ".pdf";
        if (new File(nombreArchivoPDF).exists()) {
            return generarNombreArchivo(contador + 1);
        } else {
            return nombreArchivoPDF;
        }
    }
    
    
    private void agregarEncabezado(Document doc) {
        try {
                        // Datos empresa
            Image img = Image.getInstance("src/Imagenes/logoMetro.png");
            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE);
            fecha.add("Fecha: " + this.getFechaVenta());
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);

            String ruc = "123456789";
            String nom = "Metro Luis Gonzales";
            String tel = "999999999";
            String dir = "Lima";
            String ra = "JMDL";

            Encabezado.addCell(" ");
            Encabezado.addCell("RUC: " + ruc + "\nNOMBRE: " + nom + "\nTELEFONO: " + tel + "\nDIRECCION: " + dir + "\nRAZON SOCIAL: " + ra);
            Encabezado.addCell(fecha);
            
            doc.add(Encabezado);
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarDatosCliente(Document doc) {
        try {
            // CLIENTE
            Paragraph datosCliente = new Paragraph();
            datosCliente.add(Chunk.NEWLINE);
            datosCliente.add("DATOS DEL CLIENTE\n\n");
            doc.add(datosCliente);

            PdfPTable proveedor = new PdfPTable(2);
            proveedor.setWidthPercentage(100);
            proveedor.setWidths(new int[]{40, 60});

            proveedor.addCell(new PdfPCell(new Phrase("Nombre", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            proveedor.addCell(this.getCli().getNombre());

            proveedor.addCell(new PdfPCell(new Phrase("Apellido paterno", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            proveedor.addCell(this.getCli().getApellidoPaterno());

            proveedor.addCell(new PdfPCell(new Phrase("Apellido materno", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            proveedor.addCell(this.getCli().getApellidoMaterno());

            proveedor.addCell(new PdfPCell(new Phrase("DNI", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            proveedor.addCell(this.getCli().getDNI());

            doc.add(proveedor);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public double agregarProductosVendidos(Document doc) throws DocumentException {
            double totalVenta = 0.0; // Variable para acumular el subtotal
            // PRODUCTOS VENDIDOS
            Paragraph pro = new Paragraph();
            pro.add(Chunk.NEWLINE);
            pro.add("Productos vendidos: " + "\n\n");
            doc.add(pro);
            
            PdfPTable tablaPro = new PdfPTable(5); // Ajustamos el número de columnas ya que el subtotal estará en una fila adicional
            tablaPro.setWidthPercentage(100);
            tablaPro.setWidths(new int[]{20, 30, 30, 30, 30}); // Ajusta los tamaños de las columnas

            tablaPro.addCell(new PdfPCell(new Phrase("NOMBRE", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaPro.addCell(new PdfPCell(new Phrase("PRECIO", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaPro.addCell(new PdfPCell(new Phrase("CATEGORIA", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaPro.addCell(new PdfPCell(new Phrase("MARCA", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaPro.addCell(new PdfPCell(new Phrase("CANTIDAD", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));

            NodoProducto nPro = this.getlP().getCab();


            while (nPro != null) {
                tablaPro.addCell(nPro.getPro().getNombre());
                tablaPro.addCell(String.valueOf(nPro.getPro().getPrecio()));
                tablaPro.addCell(nPro.getPro().getCategoria());
                tablaPro.addCell(nPro.getPro().getMarca());
                tablaPro.addCell(String.valueOf(nPro.getPro().getStock()));

                double subtotal = nPro.getPro().getPrecio() * nPro.getPro().getStock();
                totalVenta += subtotal; // Acumulamos el subtotal en el total de venta

                nPro = nPro.getSgte();
            }
            
            doc.add(tablaPro);
            
        return totalVenta;
    }

    public void agregarMontoPagado(Document doc) {
        try {
            // Agregar fila con el monto pagado
            PdfPTable tablaMontoPagado = new PdfPTable(2);
            tablaMontoPagado.setWidthPercentage(100);
            tablaMontoPagado.setWidths(new int[]{70, 30});

            tablaMontoPagado.addCell(new PdfPCell(new Phrase("Monto Pagado:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaMontoPagado.addCell(new PdfPCell(new Phrase(String.valueOf(this.getMontoPago()), new Font(Font.FontFamily.HELVETICA, 12))));
            
            doc.add(tablaMontoPagado);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void agregarSubtotalTotal(Document doc,double totalVenta) {
        try {
              // Agregar la fila con el subtotal total
            PdfPTable tablaSubtotal = new PdfPTable(2);
            tablaSubtotal.setWidthPercentage(100);
            tablaSubtotal.setWidths(new int[]{70, 30});

            tablaSubtotal.addCell(new PdfPCell(new Phrase("Subtotal Total:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaSubtotal.addCell(new PdfPCell(new Phrase(String.valueOf(totalVenta), new Font(Font.FontFamily.HELVETICA, 12))));
            
            doc.add(tablaSubtotal);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void agregarVuelto(Document doc) {
        try {
            // Agregar fila con el vuelto
            PdfPTable tablaVuelto = new PdfPTable(2);
            tablaVuelto.setWidthPercentage(100);
            tablaVuelto.setWidths(new int[]{70, 30});

            tablaVuelto.addCell(new PdfPCell(new Phrase("Vuelto:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tablaVuelto.addCell(new PdfPCell(new Phrase(String.valueOf(this.getVuelto()), new Font(Font.FontFamily.HELVETICA, 12))));
   
            doc.add(tablaVuelto); // Agregar la tabla de vuelto debajo del subtotal
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void agregarDetalleVenta(Document doc) {
        try {
            Paragraph dt = new Paragraph();
            dt.add(Chunk.NEWLINE);
            dt.add("Detalle de venta impreso el: "+DateAString(new Date()));
            
            doc.add(dt);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensajeExito(File arch) {
        try {
            JOptionPane.showMessageDialog(null, "Archivo " + arch.getName() + " creado con éxito");
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }

    private void abrirArchivo(File arch) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(arch);
            } else {
                JOptionPane.showMessageDialog(null, "La apertura del archivo no es compatible con el entorno actual.");
            }
        } catch (HeadlessException | IOException e) {
            e.printStackTrace();
        }
    }    
}

