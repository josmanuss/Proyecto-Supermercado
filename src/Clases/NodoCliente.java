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
public class NodoCliente {
    private Cliente cli;
    private NodoCliente sgte;
    
    public NodoCliente(){}

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public NodoCliente getSgte() {
        return sgte;
    }

    public void setSgte(NodoCliente sgte) {
        this.sgte = sgte;
    }


    
}

