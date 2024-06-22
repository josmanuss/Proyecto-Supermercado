 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronFactory_TipoPago;
import java.util.*;
/**
 *
 * @author Jose Manuel
 */
public class PagoFactory {
    private final static Map<TipoDePago,Pago> pagos = new HashMap<>(){{
        put(TipoDePago.YAPE,new PagoYape());
        put(TipoDePago.APPLE_PAY,new PagoApplePay());
        put(TipoDePago.BCP, new PagoBCP());
        put(TipoDePago.EFECTIVO, new PagoEfectivo());
        put(TipoDePago.GOOGLE_PAY, new PagoGooglePay());
        put(TipoDePago.MASTER_CARD, new PagoMasterCard());
        put(TipoDePago.PAYPAL, new PagoPaypal());
        put(TipoDePago.SCOTIABANK, new PagoScotiabank());
        put(TipoDePago.PLIN, new PagoPlin());
    }};
    
    public Pago ObtenerPago(TipoDePago tipoDePago){
        return pagos.get(tipoDePago);
    }
    
}
