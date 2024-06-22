
package Clases;


public class Sesion {
    public String usuario, contrasenia;
    private static Sesion sesion;
    
    private Sesion(){}
    
    public synchronized static Sesion getInstance(){
        if( sesion == null ){
            sesion = new Sesion();
        }
        return sesion;
    }
    
}
