/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categorias_Memento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Manuel
 */
public class CaretakerCategorias {
    private final ArrayList<MementoCategoria> memento = new ArrayList<>();
    
    static String categoriaArchivo = "src/Archivos de texto/categorias.txt";
    
    public void addMemento(MementoCategoria mC){
        memento.add(mC);
    }
    
    public ArrayList<MementoCategoria> getMementoo(){
        return memento;
    }
    
    
    public String[] obtenerCategorias(){
        String[] nombreCategoria = new String[memento.size()];
        for (int i = 0; i < memento.size(); i++) {
            nombreCategoria[i] = memento.get(i).getNombre();
        }  
        return nombreCategoria;
    }
    

    
    public MementoCategoria buscarCategoria( String nombre ){
        if ( !memento.isEmpty() ){
            for ( int i = 0; i < memento.size(); i++ ){
                if ( nombre.equals(memento.get(i).getNombre()) ){
                    return memento.get(i);
                }
            }
        }
        return null;
    }
    
    public void modificarNombre(String nombreModificar, String nuevoNombre) {
        for ( int i = 0; i < memento.size(); i++ ){
            if ( memento.get(i).getNombre().equals(nombreModificar)){
                memento.get(i).setNombre(nuevoNombre);
                break;
            }
        }
    }
    
    public void cargarCategoriasDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(categoriaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                MementoCategoria me = new MementoCategoria(linea);
                this.addMemento(me);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
    
    public void guardarDatos(String nombre){
        try (PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter(categoriaArchivo, true)))){
            archivo.println(nombre);
          JOptionPane.showMessageDialog(null, "Estado guardado exitosamente en archivo "+categoriaArchivo, "Informacion", JOptionPane.INFORMATION_MESSAGE);  
        } catch (IOException ex) {
            Logger.getLogger(CaretakerCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarArchivo() {
        try (PrintWriter br = new PrintWriter(new BufferedWriter(new FileWriter("src/Archivos de texto/auxiliar.txt"))))  {
            for ( int i = 0; i < memento.size(); i++ ){
                br.println(memento.get(i).getNombre());
            }
            br.close();
            File archivoCategoria = new File(categoriaArchivo);
            if ( archivoCategoria.delete() && new File("src/Archivos de texto/auxiliar.txt").renameTo(archivoCategoria)){
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos del archivo");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
    
}
