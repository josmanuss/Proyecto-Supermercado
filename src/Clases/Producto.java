
package Clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;
    private String marca;
    private int stock;
    private Date fechaV;

    
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    

    
    public Date getFechaV() {
        return fechaV;
    }

    public void setFechaV(Date fechaV) {
        this.fechaV = fechaV;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static String DateAString(Date fecha){
        return sdf.format(fecha);
    }
    
    public Producto(String codigo, String nombre, double precio, String categoria,String marca, int stock,Date fechaV) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.stock = stock;
        this.fechaV = fechaV;
    }
    
    public Producto(){
        this.precio = 0.0;
        this.stock = 0;
    }
    
    public Object[] mostrarProducto(){
        Object[] fila = new Object[7];
        fila[0] = this.codigo;
        fila[1] = this.nombre;
        fila[2] = String.valueOf(this.precio);
        fila[3] = this.categoria;
        fila[4] = this.marca;
        fila[5] = this.stock;
        
        if ( DateAString(fechaV).equals("")){
            fila[6] = "NO TIENE";
        }
        else{
            fila[6] = DateAString(fechaV);
        }
        
        
        return fila;
    }
    
}
