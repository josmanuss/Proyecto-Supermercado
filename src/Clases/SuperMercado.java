/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Categorias_Memento.*;

/**
 *
 * @author Jose Manuel
 */
public class SuperMercado {
    private ListaTrabajadores lT;
    private ListaProductos lP;
    private ListaClientes lC;
    private ListaVentas lV;
    
    private CaretakerCategorias caretaker;
   

    public String [] categorias;

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }
    
    public SuperMercado(){
        this.lT = new ListaTrabajadores();
        this.lP = new ListaProductos();
        this.lC = new ListaClientes();
        this.lV = new ListaVentas();
        this.caretaker = new CaretakerCategorias();
    }

    public CaretakerCategorias getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(CaretakerCategorias caretaker) {
        this.caretaker = caretaker;
    }

    public ListaTrabajadores getlT() {
        return lT;
    }

    public void setlT(ListaTrabajadores lT) {
        this.lT = lT;
    }

    public ListaClientes getlC() {
        return lC;
    }

    public void setlC(ListaClientes lC) {
        this.lC = lC;
    }

    public ListaProductos getlP() {
        return lP;
    }

    public void setlP(ListaProductos lP) {
        this.lP = lP;
    }
    
    public ListaVentas getlV() {
        return lV;
    }

    public void setlV(ListaVentas lV) {
        this.lV = lV;
    }
    
    public void setListaTrabajador( Trabajador tra ){
        this.lT.insertarTrabajador(tra);
    }
    
    public void setListaProducto ( Producto pro ){
        this.lP.insertarProducto(pro);
    }
    
    public void setListaCliente ( Cliente clien ){
        this.lC.insertarCliente(clien);
    }
    
    public void setListaVenta ( Ventas ven ){
        this.lV.insertarVenta(ven);
    }
    
    public void cargaPrincipalDatos() throws ParseException, IOException{
        this.getlT().recuperarTrabajador();
        this.getlP().recuperarProducto();
        this.getlC().recuperarCliente();
        this.getlV().recuperarVenta(lC, lT);
        this.getCaretaker().cargarCategoriasDesdeArchivo();
        this.setCategorias(this.getCaretaker().obtenerCategorias());
        
//        this.caretaker.cargarCategoriasDesdeArchivo();
//        categorias = caretaker.obtenerCategorias();
    }
    
}


