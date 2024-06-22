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
public class NodoTrabajador {
    private Trabajador tra;
    private NodoTrabajador sgte;

    public NodoTrabajador(){}
    
    public Trabajador getTra() {
        return tra;
    }

    public void setTra(Trabajador tra) {
        this.tra = tra;
    }

    public NodoTrabajador getSgte() {
        return sgte;
    }

    public void setSgte(NodoTrabajador sgte) {
        this.sgte = sgte;
    }
}
