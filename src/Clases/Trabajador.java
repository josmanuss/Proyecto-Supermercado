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
public class Trabajador extends Persona{
    
    private String codigo;
    private String Usuario;
    private String Contrasenia;
    private boolean activo, tipo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public Trabajador(String codigo, String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String direccion, String DNI, boolean tipo) {
        super(nombre, apellidoPaterno, apellidoMaterno, edad, direccion, DNI);
        this.codigo = codigo;
        String usuario = "";
        usuario += apellidoPaterno.charAt(0)+ apellidoMaterno + nombre;
        this.Usuario = usuario.toUpperCase();
        this.Contrasenia = usuario.toLowerCase();
        this.tipo = tipo;
        this.activo = true;
    }
    
    public Trabajador(){
        super();
        this.edad = 0;
        if ( this.apellidoPaterno.isEmpty()){
            String usuario = "";
            usuario += this.apellidoPaterno.charAt(0)+this.apellidoMaterno+this.nombre;
            this.Usuario = usuario.toUpperCase();
            this.Contrasenia = usuario.toLowerCase();
            this.activo = true;
        }
    }
    
    @Override
    public Object[] mostrar() {
        Object[] fila = new Object[7];
        fila[0] = this.codigo;
        fila[1] = this.nombre;
        fila[2] = this.apellidoPaterno;
        fila[3] = this.apellidoMaterno;
        fila[4] = this.DNI;
        fila[5] = this.edad;
        fila[6] = this.direccion;
        return fila;
    }
}
