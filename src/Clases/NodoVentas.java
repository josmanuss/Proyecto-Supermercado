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
public class NodoVentas {
    private Ventas ven;
    private NodoVentas sgte;
    private NodoVentas ant;
    
    public NodoVentas(){}

    public Ventas getVen() {
        return ven;
    }

    public void setVen(Ventas ven) {
        this.ven = ven;
    }

    public NodoVentas getSgte() {
        return sgte;
    }

    public void setSgte(NodoVentas sgte) {
        this.sgte = sgte;
    }

    public NodoVentas getAnt() {
        return ant;
    }

    public void setAnt(NodoVentas ant) {
        this.ant = ant;
    }
    
    
    
}
