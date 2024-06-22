/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Jose Manuel
 */
public class Cliente extends Persona{
    private String Codigo;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public Cliente(String Codigo, String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String direccion, String DNI) {
        super(nombre, apellidoPaterno, apellidoMaterno, edad, direccion, DNI);
        this.Codigo = Codigo;
    }
    
    public Cliente(){
        super();
    }

    @Override
    public Object[] mostrar() {
        Object[] fila = new Object[7];
        fila[0] = this.Codigo;
        fila[1] = this.nombre;
        fila[2] = this.apellidoPaterno;
        fila[3] = this.apellidoMaterno;
        fila[4] = this.edad;
        fila[5] = this.direccion;
        fila[6] = this.DNI;
        return fila;
    }
}
