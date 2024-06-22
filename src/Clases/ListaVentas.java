/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import static Clases.ListaProductos.convertStringToDate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
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
public class ListaVentas {
    private NodoVentas cab;
    private int nVen;
    
    static String nombreArchivoVenta = "src/Archivos de texto/ventas.txt";
    static String limites_V = "_";
    static String limites_P = "__";
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
    public static String Fecha_A_String(Date fecha){
        return sdf.format(fecha);
    }
    
    public ListaVentas(){
        this.cab = null;
        this.nVen = 0;
    }

    public NodoVentas getCab() {
        return cab;
    }

    public void setCab(NodoVentas cab) {
        this.cab = cab;
    }

    public int getnVen() {
        return nVen;
    }

    public void setnVen(int nVen) {
        this.nVen = nVen;
    }
    
    public void insertarVenta( Ventas ven ){
        NodoVentas aux = new NodoVentas();
        NodoVentas temp = new NodoVentas();
        aux = this.cab;
        temp.setVen(ven);
        if ( aux != null ){
            while ( aux.getSgte() != null ){
                aux = aux.getSgte();
            }
            temp.setSgte(aux.getSgte());
            aux.setSgte(temp);
            temp.setAnt(aux);
        }
        else{
            temp.setSgte(aux);
            temp.setAnt(null);
            this.cab = temp;
        }
        this.nVen++;
    }
    
    public Ventas buscarVenta(String codigo){
        if ( this.cab != null ){
            NodoVentas nodo = this.cab;
            while ( nodo != null ){
                if ( codigo.equals(nodo.getVen().getCodigo())){
                    return nodo.getVen();
                }
                nodo = nodo.getSgte();
            }
        }
        return null;
    }
 
    public void guardarVentasEnArchivo(Ventas ven) {
        try (PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivoVenta, true)))) {
            archivo.println(ven.getCli().getCodigo()+limites_V+ven.getTraVen().getCodigo()+limites_V
                    +String.valueOf(ven.getMontoPago())+limites_V
                    +String.valueOf(ven.getVuelto())+limites_V+ven.getTipoDePago()
                    +limites_P+obtenerListaProductosComoString(ven.getlP())
                    +limites_P+ven.getFechaVenta());
            JOptionPane.showMessageDialog(null, "Datos guardados en archivo");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }
    
    private String obtenerListaProductosComoString(ListaProductos listaProductos) {
        StringBuilder sb = new StringBuilder();
        NodoProducto aux = listaProductos.getCab();
        boolean firstProduct = true;
        while (aux != null) {
            if (!firstProduct) {
                sb.append(" -- ");
            } else {
                firstProduct = false;
            }
            sb.append(aux.getPro().getCodigo()).append(limites_V)
                                            .append(aux.getPro().getNombre())
                                            .append(limites_V)
                                            .append(String.valueOf(aux.getPro().getPrecio()))
                                            .append(limites_V).append(aux.getPro().getCategoria())
                                            .append(limites_V).append(aux.getPro().getMarca())
                                            .append(limites_V).append(String.valueOf(aux.getPro().getStock()))
                                            .append(limites_V).append(Fecha_A_String(aux.getPro().getFechaV()));
           
            aux = aux.getSgte();
        }
        return sb.toString();
    }
    
    public void recuperarVenta(ListaClientes LC, ListaTrabajadores LT) throws IOException {
        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivoVenta))) {
            String linea;
            while ((linea = archivo.readLine()) != null) {
                Ventas ven = procesarLineaVenta(linea, LC, LT);
                if (ven != null) {
                    this.insertarVenta(ven);
                }
            }
            System.out.println("Datos cargados con éxito");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos. El programa se cerrará.");
            throw e;
        }
    }

    private Ventas procesarLineaVenta(String linea, ListaClientes LC, ListaTrabajadores LT) {
        String codigo;
        String detalleVentaStr, productosStr, fechaVenta;
        double total, vuelto;
        String tipoPago;
        codigo = "V00" + String.valueOf(nVen + 1);
        
        // Separar la lista de productos del detalle de venta
        String[] partesVenta = linea.split("__");
        detalleVentaStr = partesVenta[0];
        productosStr = partesVenta[1];
        fechaVenta = partesVenta[2];

        // Procesar el detalle de venta
        String[] detalleVentaArr = detalleVentaStr.split("_");
        Cliente cliente = LC.buscarCliente(detalleVentaArr[0]);
        Trabajador trabajador = LT.buscarTrabajador(detalleVentaArr[1]);
        total = Double.parseDouble(detalleVentaArr[2]);
        vuelto = Double.parseDouble(detalleVentaArr[3]);
        tipoPago = detalleVentaArr[4];

        // Procesar la lista de productos
        ListaProductos listaProductos = procesarProductos(productosStr);

        return new Ventas(codigo, cliente, trabajador, listaProductos, total, vuelto, tipoPago, fechaVenta);
    }

    private ListaProductos procesarProductos(String productosStr) {
        ListaProductos listaProductos = new ListaProductos();
        String[] productosArr = productosStr.split("--");

        for (String productoStr : productosArr) {
            String[] detalleProductos = productoStr.split("_");
            String codigo = detalleProductos[0];
            String nombre = detalleProductos[1];
            double precio = Double.parseDouble(detalleProductos[2]);
            String categoria = detalleProductos[3];
            String marca = detalleProductos[4];
            int stock = Integer.parseInt(detalleProductos[5]);
            String fechaV = detalleProductos[6];

            Producto producto = new Producto(codigo, nombre, precio, categoria, marca, stock, convertStringToDate(fechaV));
            listaProductos.insertarProducto(producto);
        }

        return listaProductos;
    }

    public void generarReportePDF() {
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
            
            NodoVentas nodo = this.cab;
            
            while ( nodo != null ){
                nodo.getVen().agregarDatosCliente(doc);
                double doubleTotalVenta = nodo.getVen().agregarProductosVendidos(doc);
                nodo.getVen().agregarMontoPagado(doc);
                nodo.getVen().agregarSubtotalTotal(doc,doubleTotalVenta);
                nodo.getVen().agregarVuelto(doc);
                nodo = nodo.getSgte();
            }
            
            this.cab.getVen().agregarDetalleVenta(doc);        
            doc.close(); 
            this.mostrarMensajeExito(arch);
            this.abrirArchivo(arch);
            
            
        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void agregarEncabezado(Document doc) {
        try {
                        // Datos empresa
            Image img = Image.getInstance("src/Imagenes/logoMetro.png");
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
       
            doc.add(Encabezado);
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    private String generarNombreArchivo(int contador) {
        String nombreArchivoPDF = "src/pdf/Reportes venta/Reporte de ventas" + "_" + String.valueOf(contador + 1) + ".pdf";
        if (new File(nombreArchivoPDF).exists()) {
            return generarNombreArchivo(contador + 1);
        } else {
            return nombreArchivoPDF;
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
