/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Manuel
 */
public class ListaClientes {
    private NodoCliente primero;
    private int nCli;
    
    static String nombreArchivoCliente = "src/Archivos de texto/clientes.txt";
    static String limites_C = "_";
    
    public ListaClientes(){
        this.primero = null;
        this.nCli = 0;
    }

    public NodoCliente getPrimero() {
        return primero;
    }

    public void setPrimero(NodoCliente primero) {
        this.primero = primero;
    }


    public int getnCli() {
        return nCli;
    }

    public void setnCli(int nCli) {
        this.nCli = nCli;
    }
    
    public void insertarCliente( Cliente clien ){
        NodoCliente nuevo = new NodoCliente();
        NodoCliente temp = new NodoCliente();
        temp.setCli(clien);
        temp.setSgte(null);
        if ( this.primero == null ){
            this.primero = temp;
        }
        else{
            nuevo = this.primero;
            while ( nuevo.getSgte() != null ){
                nuevo = nuevo.getSgte();
            }
            nuevo.setSgte(temp);
        }
        this.nCli++;
    }
    
    public Cliente buscarCliente( String codigo ){
        if ( primero != null){
            NodoCliente actual = this.primero;
            while ( actual != null ) {
                if ( codigo.equals(actual.getCli().getCodigo())){
                    return actual.getCli();
                }
                actual = actual.getSgte();
            }
        }
        return null;
    }
    
    public Cliente buscarClienteDNI ( String DNI ){
        if ( primero != null){
            NodoCliente actual = this.primero;
            while ( actual != null ) {
                if ( DNI.equals(actual.getCli().getDNI())){
                    return actual.getCli();
                }
                actual = actual.getSgte();
            }
        }
        return null;
    }
    
    
    public void modificarCliente( Cliente cli ){
        if ( primero != null ) {
            NodoCliente actual = this.primero;
            while ( actual != null ){
                if ( cli.getCodigo().equals(actual.getCli().getCodigo())){
                    actual.setCli(cli);
                }
                actual = actual.getSgte();
            }
        }
        return;
    }
    
     public void recuperarCliente() throws IOException {
        String dato, codigo, nombre, apellidoPaterno, apellidoMaterno, edad, direccion, DNI;
        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivoCliente))) {

            while ((dato = archivo.readLine()) != null) {
                codigo = "C00"+String.valueOf(nCli+1);
                String[] datos = dato.split(limites_C);
                nombre= datos[0];
                apellidoPaterno = datos[1];
                apellidoMaterno = datos[2];
                edad = datos[3];
                direccion = datos[4];
                DNI = datos[5];

                Cliente clien  = new Cliente(codigo,nombre,apellidoPaterno,apellidoMaterno,
                        Integer.parseInt(edad),direccion,DNI);
                this.insertarCliente(clien);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos. El programa se cerrará.");
            throw e;
        }
    }
    
    public void guardarDatosCliente(Cliente clien) {
        try (PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivoCliente, true)))) {
            archivo.println(clien.getNombre() + limites_C + clien.getApellidoPaterno() + limites_C + clien.getApellidoMaterno() + limites_C + String.valueOf(clien.getEdad())
                    + limites_C + clien.getDireccion() + limites_C + clien.getDNI());
            JOptionPane.showMessageDialog(null, "Datos guardados en archivo");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }

    
     public void actualizarArchivo() {
        try (PrintWriter archivo2 = new PrintWriter(new BufferedWriter(new FileWriter("src/Archivos de texto/auxiliar.txt")))) {
            NodoCliente aux = this.primero;
            while (aux != null) {
                archivo2.println(aux.getCli().getNombre() + limites_C + aux.getCli().getApellidoPaterno() + limites_C + aux.getCli().getApellidoMaterno() + limites_C + String.valueOf(aux.getCli().getEdad())
                    + limites_C + aux.getCli().getDireccion() + limites_C + aux.getCli().getDNI());
                aux = aux.getSgte();
            }
            archivo2.close();
            File archivoCliente = new File(nombreArchivoCliente);
            if (archivoCliente.delete() && new File("src/Archivos de texto/auxiliar.txt").renameTo(archivoCliente)) {
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos del archivo");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo");
        }
    }
    
}
