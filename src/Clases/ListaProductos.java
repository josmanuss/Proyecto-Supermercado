
package Clases;


import static Clases.Producto.DateAString;
import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ListaProductos {
    private NodoProducto cab;
    private int nProd;
    
    static String nombreArchivoProducto = "src/Archivos de texto/productos.txt";
    
    
    public ListaProductos() {
        this.cab = null;
        this.nProd = 0;
    }

    public NodoProducto getCab() {
        return cab;
    }

    public void setCab(NodoProducto cab) {
        this.cab = cab;
    }

    public int getnProd() {
        return nProd;
    }

    public void setnProd(int nProd) {
        this.nProd = nProd;
    }
    
    public Producto buscarProducto(String codigo){
        if ( this.cab != null ){
            NodoProducto aux = this.getCab();
            while ( aux != null ){
                if ( codigo.equals(aux.getPro().getCodigo())){
                    return aux.getPro();
                }
                aux = aux.getSgte();
            }
        }
        return null;
    }
    
    public static Date convertStringToDate(String fechaString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // En caso de que ocurra un error, retornamos null o podemos lanzar una excepción personalizada.
        }
    }
    
    
    public Producto buscarNombre(String nombres){
        if ( this.cab != null ){
            NodoProducto aux = this.getCab();
            while ( aux != null ){
                if ( nombres.equals(aux.getPro().getNombre())){
                    return aux.getPro();
                }
                aux = aux.getSgte();
            }
        }
        return null;
    }
    
    public void insertarProducto( Producto pro ){
        NodoProducto aux = new NodoProducto();
        NodoProducto temp = new NodoProducto();
        temp.setPro(pro);
        temp.setSgte(null);
        if ( this.cab == null ){
            this.cab = temp;
        }
        else{
            aux = this.cab;
            while ( aux.getSgte() != null ){
                aux = aux.getSgte();
            }
            aux.setSgte(temp);
        }
        this.nProd++;
    }
    
    public void cambiarValores( Producto pro ){
        if ( this.cab != null ){
            NodoProducto aux = this.getCab();
            while ( aux != null ){
                if ( pro.getCodigo().equals(aux.getPro().getCodigo())){
                    aux.setPro(pro);
                }
                aux = aux.getSgte();
            }
        }
        return;
    }
    
    public void recuperarProducto() throws IOException {
        String dato, codigo, nombre,precio, categoria, marca,stock,fechaV;
        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivoProducto))) {

            while ((dato = archivo.readLine()) != null) {
                codigo = "P00"+String.valueOf(this.nProd+1);
                String[] datos = dato.split("_");
                nombre = datos[0];
                precio = datos[1];
                categoria = datos[2];
                marca = datos[3];
                stock = datos[4];
                fechaV = datos[5];
     
                Producto pro = new Producto(codigo,nombre,Double.parseDouble(precio),categoria,marca,
                        Integer.parseInt(stock),convertStringToDate(fechaV));
                this.insertarProducto(pro);
                
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos. El programa se cerrará.");
            throw e;
        }
    }
    
    public void guardarProductoEnArchivo(Producto pro){
        try (PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivoProducto, true)))) {
            archivo.println(pro.getNombre()+ "_" + String.valueOf(pro.getPrecio()) + "_" + pro.getCategoria()+ "_" + pro.getMarca()
                    + "_" + String.valueOf(pro.getStock()) + "_" + DateAString(pro.getFechaV()));
            JOptionPane.showMessageDialog(null, "Datos guardados en archivo");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }
    
    public void actualizarArchivo() {
        try (PrintWriter archivo2 = new PrintWriter(new BufferedWriter(new FileWriter("src/Archivos de texto/auxiliar.txt")))) {
            NodoProducto aux = this.cab;
            while (aux != null) {
                archivo2.println(aux.getPro().getNombre()+ "_" + String.valueOf(aux.getPro().getPrecio()) + "_" + aux.getPro().getCategoria()+ "_" + aux.getPro().getMarca()
                    + "_" + String.valueOf(aux.getPro().getStock()) + "_" + DateAString(aux.getPro().getFechaV()));
                aux = aux.getSgte();
            }
            archivo2.close();
            File archivoTrabajador = new File(nombreArchivoProducto);
            if (archivoTrabajador.delete() && new File("src/Archivos de texto/auxiliar.txt").renameTo(archivoTrabajador)) {
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos del archivo");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo");
        }
    }

}
