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
public class MementoCategoria {
    private String nombre;
    
    public MementoCategoria(){
        
    }
    
    public MementoCategoria(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
