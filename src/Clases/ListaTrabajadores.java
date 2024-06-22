/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Manuel
 */
public class ListaTrabajadores {
    private NodoTrabajador cab;
    private int nTra;
    
    static String nombreArchivoTrabajador = "src/Archivos de texto/trabajadores.txt";
    static String limite = "_";
    
    public ListaTrabajadores(){
        this.cab = null;
        this.nTra = 0;
    }
    
    public NodoTrabajador getCab() {
        return cab;
    }

    public void setCab(NodoTrabajador cab) {
        this.cab = cab;
    }

    public int getnTra() {
        return nTra;
    }

    public void setnTra(int nTra) {
        this.nTra = nTra;
    }
    
    public void insertarTrabajador( Trabajador tra ){
        NodoTrabajador aux = new NodoTrabajador();
        NodoTrabajador temp = new NodoTrabajador();
        temp.setTra(tra);
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
        this.nTra++;
    }
    
    public Trabajador buscarUsuarioContrasenia( String usuario, String contrasenia ){
        if ( this.cab != null ){
            NodoTrabajador aux = this.cab;
            while ( aux != null ){
                if ( usuario.equals(aux.getTra().getUsuario()) && contrasenia.equals(aux.getTra().getContrasenia())){
                    return aux.getTra();
                }
                aux = aux.getSgte();
            }
        }
        return null;
    }
    
    public Trabajador buscarTrabajador( String codigo ){
        if ( this.cab != null ){
            NodoTrabajador aux = this.cab;
            while ( aux != null ){
                if ( codigo.equals(aux.getTra().getCodigo())){
                    return aux.getTra();
                }
                aux = aux.getSgte();
            }
        }
        return null;
    }
    
    public Trabajador buscarDni( String dni ){
        if ( this.cab != null ){
            NodoTrabajador aux = this.cab;
            while ( aux != null ){
                if ( dni.equals(aux.getTra().getDNI())){
                    return aux.getTra();
                }
                aux = aux.getSgte();
            }
        }
        return null;
    }    
    
    public void modificarDatos( Trabajador tra){
        if ( this.cab == null ){
            return;
        }
        NodoTrabajador aux = this.cab;
        while ( aux != null ){
            if ( tra.getCodigo().equals(aux.getTra().getCodigo())){
                aux.setTra(tra);
            }                
            aux = aux.getSgte();
        }
    }
    
    public void recuperarTrabajador() throws IOException {
        String dato, codigo, nombre, apellidoPaterno, apellidoMaterno, edad, direccion, DNI, tipo;
        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivoTrabajador))) {

            while ((dato = archivo.readLine()) != null) {
                codigo = "T00"+String.valueOf(nTra+1);
                String[] datos = dato.split(limite);
                nombre= datos[0];
                apellidoPaterno = datos[1];
                apellidoMaterno = datos[2];
                edad = datos[3];
                direccion = datos[4];
                DNI = datos[5];
                tipo = datos[6];

                Trabajador tra = new Trabajador(codigo,nombre,apellidoPaterno,apellidoMaterno,
                        Integer.parseInt(edad),direccion,DNI,Boolean.parseBoolean(tipo));
                this.insertarTrabajador(tra);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos. El programa se cerrará.");
            throw e;
        }
    }
    
    public void guardarDatosTrabajador(Trabajador tra) {
        try (PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivoTrabajador, true)))) {
            archivo.println(tra.getNombre() + limite + tra.getApellidoPaterno() + limite + tra.getApellidoMaterno() + limite + String.valueOf(tra.getEdad())
                    + limite + tra.getDireccion() + limite + tra.getDNI() + limite + String.valueOf(tra.isTipo()));
            JOptionPane.showMessageDialog(null, "Datos guardados en archivo");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
    }

    
     public void actualizarArchivo() {
        try (PrintWriter archivo2 = new PrintWriter(new BufferedWriter(new FileWriter("src/Archivos de texto/auxiliar.txt")))) {
            NodoTrabajador aux = cab;
            while (aux != null) {
                archivo2.println(aux.getTra().getNombre() + limite + aux.getTra().getApellidoPaterno() + limite + aux.getTra().getApellidoMaterno() + limite + String.valueOf(aux.getTra().getEdad())
                    + limite + aux.getTra().getDireccion() + limite + aux.getTra().getDNI() + limite + String.valueOf(aux.getTra().isTipo()));
                aux = aux.getSgte();
            }
            archivo2.close();
            File archivoTrabajador = new File(nombreArchivoTrabajador);
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
