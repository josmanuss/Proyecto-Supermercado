
package Clases;

import Formularios.*;
import java.io.IOException;
import java.text.ParseException;

public class Principal {
    public static void main( String[] args) throws ParseException, IOException{
        SuperMercado sup = new SuperMercado();
        sup.cargaPrincipalDatos();
        FrmLogin frLog = new FrmLogin();
        frLog.setSupe(sup);
        frLog.show();   
    }
}
