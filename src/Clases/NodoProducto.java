
package Clases;

public class NodoProducto {
    private Producto pro;
    private NodoProducto sgte;

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public NodoProducto getSgte() {
        return sgte;
    }

    public void setSgte(NodoProducto sgte) {
        this.sgte = sgte;
    }
    
    public NodoProducto(){
        
    }
    
}
