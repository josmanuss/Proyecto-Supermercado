/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categorias_Memento;

/**
 *
 * @author Jose Manuel
 */
public class OriginatorCategoria {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public void setState( String nombre ){
        this.nombre = nombre;
    }
    
    
    public MementoCategoria guardar(){
        return new MementoCategoria(nombre);
    }
    
    public void restore(MementoCategoria mC){
        nombre = mC.getNombre();
    }
}
