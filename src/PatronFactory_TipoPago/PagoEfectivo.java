/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronFactory_TipoPago;

import javax.swing.JOptionPane;

/**
 *
 * @author Jose Manuel
 */
public class PagoEfectivo implements Pago{

    @Override
    public void crearPago() {
        JOptionPane.showMessageDialog(null,"Se ha procesado un pago en efectivo");      
    }
    
}
