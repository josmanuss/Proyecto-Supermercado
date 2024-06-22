/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.*;
import PatronFactory_TipoPago.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import Categorias_Memento.*;
import static Clases.Ventas.DateAString;
import java.awt.Color;
import java.util.Date;
/**
 *
 * @author Jose Manuel
 */
public class FrmVentanaPrincipal extends javax.swing.JFrame {
    
    private SuperMercado sup;
    private ListaProductos lPVen;
    DefaultTableModel modelo;
    DefaultComboBoxModel modeloComboBox;
    
    PagoFactory pagoFactory = new PagoFactory();
    Pago pago;
    
    String[] tiposPago = {"EFECTIVO","YAPE","PLIN","BCP","MASTER CARD","PAYPAL","BITCOIN" };
    
    public SuperMercado getSup() {
        return sup;
    }

    public ListaProductos getlPVen() {
        return lPVen;
    }

    public void setlPVen(ListaProductos lPVen) {
        this.lPVen = lPVen;
    }
    

    public void setSup(SuperMercado sup) {
        this.sup = sup;
    }

    /**
     * Creates new form FrmVentanaPrincipal
     */
    public FrmVentanaPrincipal() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    public FrmVentanaPrincipal(Trabajador tra) {        
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        
        
        jLCodigoTrabajador1.setVisible(false);
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);        
        jTabbedPane1.setEnabledAt(4, false); 
        tFTotalPago.setEditable(false);
        tFCodigoTrabajadorInicio.setVisible(false);
        tFCodigoTrabajadorInicio.setText(tra.getCodigo());
        jLCodigoTrabajador1.setText(tFCodigoTrabajadorInicio.getText());
        this.jbRegistrarVenta.setEnabled(false);
        if ( tra.isTipo() ){
            this.jBClientes.setLocation(22, 107);
            this.jBTrabajador.setLocation(22, 202);
            this.jBProducto.setLocation(22, 300);
            this.jBVentas.setLocation(22, 391);
            this.jBCerrarSesion.setLocation(22, 486);
            this.jBTrabajador.setVisible(true);
        }
        else{
            this.jBTrabajador.setVisible(false);
            this.jBClientes.setLocation(22, 107);
            this.jBProducto.setLocation(22, 202);
            this.jBVentas.setLocation(22, 300);
            this.jBCerrarSesion.setLocation(22, 391);    
        }
    }
    
    
    public void llenarComboBox(){
        jCCategoriaProducto.removeAllItems();
        modeloComboBox = (DefaultComboBoxModel) this.jCCategoriaProducto.getModel();
        for (String categoria : sup.categorias) {
            modeloComboBox.addElement(categoria);
        }
        this.jCCategoriaProducto.setModel(modeloComboBox);
    }
    

    public void llenarComboBoxTipoPago(){
        this.jComboBox1.removeAllItems();
        this.jComboBox1.addItem("SELECCIONE TIPO DE PAGO");
        modeloComboBox = (DefaultComboBoxModel) this.jComboBox1.getModel();
        for (String tiposPago1 : tiposPago) {
            modeloComboBox.addElement(tiposPago1);
        }
        this.jComboBox1.setModel(modeloComboBox);
    }
    
    public void mostrarTrabajador( Trabajador tra, int pos ){
        
        this.jTListaTrabajador.setValueAt(tra.getNombre(), pos, 6);
        this.jTListaTrabajador.setValueAt(tra.getNombre(), pos, 0);
        this.jTListaTrabajador.setValueAt(tra.getApellidoPaterno(), pos, 1);
        this.jTListaTrabajador.setValueAt(tra.getApellidoMaterno(), pos, 2);
        this.jTListaTrabajador.setValueAt(tra.getDNI(), pos, 3);
        this.jTListaTrabajador.setValueAt(tra.getEdad(), pos, 4);
        this.jTListaTrabajador.setValueAt(tra.getDireccion(), pos, 5);

    }
    
    public void LimpiarTrabajador(){
        tFNombresTrabajador.setText("");
        tFApePaTrabajador.setText("");
        tFApeMaTrabajador.setText("");
        tFEdadTrabajador.setText("");
        tFDireccionTrabajador.setText("");
        tFDNITrabajador.setText("");
        chkAdministrador.setSelected(false);
    }
    
    public void LimpiarCliente(){
        tFNombreCliente.setText("");
        tFApePaCliente.setText("");
        tFApeMaCliente.setText("");
        tFEdadCliente.setText("");
        tFDireccionCliente.setText("");
        tFDNICliente.setText("");
    }
    
    public void LimpiarProducto(){
        tFNombreProducto.setText("");
        tFPrecioProducto.setText("");
        jCCategoriaProducto.setSelectedIndex(0);
        tFMarcaProducto.setText("");
        jSpStockProducto.setValue(0);
    }
    
    
    public void ListarClientes( ListaClientes lC ){
        modelo = (DefaultTableModel) this.jTListaCliente.getModel();
        modelo.setRowCount(0);
        NodoCliente aux = lC.getPrimero();
        while ( aux != null ){
            modelo.addRow(aux.getCli().mostrar());
            aux = aux.getSgte();
        }
        this.jTListaCliente.setModel(modelo);
    }
    
   public void ListarTrabajadores ( ListaTrabajadores lT ){
       modelo = (DefaultTableModel) this.jTListaTrabajador.getModel();
       modelo.setRowCount(0);
       NodoTrabajador aux = lT.getCab();
       while ( aux != null ){
           modelo.addRow(aux.getTra().mostrar());
           aux = aux.getSgte();
       }
       this.jTListaTrabajador.setModel(modelo);
   }
   
   public void ListarProductos ( ListaProductos lP ){
       modelo = (DefaultTableModel) this.jTListaProductos.getModel();
       modelo.setRowCount(0);
       NodoProducto aux = lP.getCab();
       while ( aux != null ){
           modelo.addRow(aux.getPro().mostrarProducto());
           aux = aux.getSgte();
       }
       this.jTListaProductos.setModel(modelo);
   }
   
    public void ListarProductosVendidos ( ListaProductos lP ){
       modelo = (DefaultTableModel) this.jTListaProductosVendidos1.getModel();
       modelo.setRowCount(0);
       NodoProducto aux = lP.getCab();
       while ( aux != null ){
           modelo.addRow(aux.getPro().mostrarProducto());
           aux = aux.getSgte();
       }
       this.jTListaProductosVendidos1.setModel(modelo);
    }
    
    public void ListarVentas ( ListaVentas lV ){
       modelo = (DefaultTableModel) this.jTListaVentas.getModel();
       modelo.setRowCount(0);
       NodoVentas aux = lV.getCab();
       while ( aux != null ){
           modelo.addRow(aux.getVen().mostrarVenta());
           aux = aux.getSgte();
       }
       this.jTListaVentas.setModel(modelo);
    }
    
    public double montoPago( ){
        double montoInicial = 0.0, montoFinal = 0.0;
        NodoProducto aux = this.lPVen.getCab();
        while ( aux != null ){
            montoInicial = aux.getPro().getPrecio() * aux.getPro().getStock();
            montoFinal += montoInicial;
            aux = aux.getSgte();
        }
        return montoFinal;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBClientes = new javax.swing.JButton();
        jBTrabajador = new javax.swing.JButton();
        jBProducto = new javax.swing.JButton();
        jBVentas = new javax.swing.JButton();
        jBCerrarSesion = new javax.swing.JButton();
        tFCodigoTrabajadorInicio = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        buttonExit = new javax.swing.JPanel();
        botonCerrar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPCliente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListaCliente = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        tFNombreCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tFApePaCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tFApeMaCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tFEdadCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tFDireccionCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tFDNICliente = new javax.swing.JTextField();
        jBRegistroCliente = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jBBuscarCliente = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBActualizarCliente = new javax.swing.JButton();
        jPTrabajador = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tFNombresTrabajador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tFApePaTrabajador = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tFApeMaTrabajador = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tFEdadTrabajador = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tFDireccionTrabajador = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tFDNITrabajador = new javax.swing.JTextField();
        jBRegistroTrabajador = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        chkAdministrador = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        txtTrabajador = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTListaTrabajador = new javax.swing.JTable();
        jBBuscarTrabajador = new javax.swing.JButton();
        jBActualizarTrabajador = new javax.swing.JButton();
        jPProducto = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        tFNombreProducto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tFPrecioProducto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tFMarcaProducto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jBRegistroProducto = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jCCategoriaProducto = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jSpStockProducto = new javax.swing.JSpinner();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTListaProductos = new javax.swing.JTable();
        jBBuscarProducto = new javax.swing.JButton();
        jBActualizarProducto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPVentas = new javax.swing.JPanel();
        jPVenta1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTListaProductosVendidos1 = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLCodigoTrabajador1 = new javax.swing.JLabel();
        jSpCantidad = new javax.swing.JSpinner();
        jLabel44 = new javax.swing.JLabel();
        jbAgregarATabla = new javax.swing.JButton();
        jbRegistrarVenta = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tFCodigoClienteBuscar = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jSpMontoPagar = new javax.swing.JSpinner();
        tFTotalPago = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        tFCodigoProductoBuscar = new javax.swing.JTextField();
        jbBuscarProducto = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPRegistroVenta = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JTextField();
        jBBuscarVenta = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTListaVentas = new javax.swing.JTable();
        jbNuevaVenta = new javax.swing.JButton();
        jBTotalGanancia = new javax.swing.JButton();
        jBDetalleVenta = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        botonReporte = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(1310, 640));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBClientes.setBackground(new java.awt.Color(255, 255, 255));
        jBClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconCliente.png"))); // NOI18N
        jBClientes.setText("Clientes");
        jBClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBClientesActionPerformed(evt);
            }
        });
        jPanel2.add(jBClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 179, 60));

        jBTrabajador.setBackground(new java.awt.Color(255, 255, 255));
        jBTrabajador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconTrabajador.png"))); // NOI18N
        jBTrabajador.setText("Trabajadores");
        jBTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTrabajadorActionPerformed(evt);
            }
        });
        jPanel2.add(jBTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 275, -1, 59));

        jBProducto.setBackground(new java.awt.Color(255, 255, 255));
        jBProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1312307.png"))); // NOI18N
        jBProducto.setText("Productos");
        jBProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProductoActionPerformed(evt);
            }
        });
        jPanel2.add(jBProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 179, 60));

        jBVentas.setBackground(new java.awt.Color(255, 255, 255));
        jBVentas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Compra.png"))); // NOI18N
        jBVentas.setText("Ventas");
        jBVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVentasActionPerformed(evt);
            }
        });
        jPanel2.add(jBVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 465, 179, 60));

        jBCerrarSesion.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrarSesion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBCerrarSesion.setText("Cerrar sesion");
        jBCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarSesionActionPerformed(evt);
            }
        });
        jPanel2.add(jBCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 179, 60));
        jPanel2.add(tFCodigoTrabajadorInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 584, -1, -1));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo_Metro_Cencosud.png"))); // NOI18N
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 223, 170));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 224, 640));

        jPanel4.setBackground(new java.awt.Color(255, 204, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonExit.setBackground(new java.awt.Color(255, 255, 51));

        botonCerrar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonCerrar.setForeground(new java.awt.Color(255, 0, 0));
        botonCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonCerrar.setText("X");
        botonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCerrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout buttonExitLayout = new javax.swing.GroupLayout(buttonExit);
        buttonExit.setLayout(buttonExitLayout);
        buttonExitLayout.setHorizontalGroup(
            buttonExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonExitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        buttonExitLayout.setVerticalGroup(
            buttonExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonExitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Rockwell", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenidos a supermercado Metro");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1060, 120));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 0, 1084, 128));

        jPCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTListaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "DNI", "EDAD", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTListaCliente);
        if (jTListaCliente.getColumnModel().getColumnCount() > 0) {
            jTListaCliente.getColumnModel().getColumn(0).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTListaCliente.getColumnModel().getColumn(1).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(2).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTListaCliente.getColumnModel().getColumn(3).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTListaCliente.getColumnModel().getColumn(4).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(5).setResizable(false);
            jTListaCliente.getColumnModel().getColumn(6).setResizable(false);
        }

        jPCliente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 107, 613, 302));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tFNombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 78, 179, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombres");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 78, 143, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido paterno");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 119, 143, 30));

        tFApePaCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFApePaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 119, 179, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Apellido materno");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 155, 143, 30));

        tFApeMaCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFApeMaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 156, 179, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Edad");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 197, 143, 30));

        tFEdadCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFEdadCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFEdadClienteKeyTyped(evt);
            }
        });
        jPanel6.add(tFEdadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 197, 179, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Direccion");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 238, 143, 30));

        tFDireccionCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(tFDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 239, 179, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DNI");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 275, 143, 30));

        tFDNICliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFDNICliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFDNIClienteKeyTyped(evt);
            }
        });
        jPanel6.add(tFDNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 276, 179, 30));

        jBRegistroCliente.setBackground(new java.awt.Color(0, 255, 51));
        jBRegistroCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBRegistroCliente.setText("REGISTRAR");
        jBRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistroClienteActionPerformed(evt);
            }
        });
        jPanel6.add(jBRegistroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 338, 179, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("CLIENTES");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 0, 163, 49));

        jPCliente.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 390, 440));

        jBBuscarCliente.setText("BUSCAR");
        jBBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarClienteActionPerformed(evt);
            }
        });
        jPCliente.add(jBBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 40, 163, 40));

        txtCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPCliente.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 40, 187, 40));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CODIGO:");
        jPCliente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 30, 110, 59));

        jBActualizarCliente.setText("ACTUALIZAR CLIENTE");
        jBActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarClienteActionPerformed(evt);
            }
        });
        jPCliente.add(jBActualizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 435, 227, 39));

        jTabbedPane1.addTab("", jPCliente);

        jPTrabajador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tFNombresTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(tFNombresTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 78, 179, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nombres");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 78, 143, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Apellido paterno");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 119, 143, 30));

        tFApePaTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(tFApePaTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 119, 179, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Apellido materno");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 155, 143, 30));

        tFApeMaTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(tFApeMaTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 156, 179, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Edad");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 197, 143, 30));

        tFEdadTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFEdadTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFEdadTrabajadorKeyTyped(evt);
            }
        });
        jPanel7.add(tFEdadTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 197, 179, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Direccion");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 238, 143, 30));

        tFDireccionTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel7.add(tFDireccionTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 239, 179, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DNI");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 275, 143, 30));

        tFDNITrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFDNITrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFDNITrabajadorKeyTyped(evt);
            }
        });
        jPanel7.add(tFDNITrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 276, 179, 30));

        jBRegistroTrabajador.setBackground(new java.awt.Color(0, 255, 51));
        jBRegistroTrabajador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBRegistroTrabajador.setText("REGISTRAR");
        jBRegistroTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistroTrabajadorActionPerformed(evt);
            }
        });
        jPanel7.add(jBRegistroTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 338, 179, 40));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("TRABAJADOR");
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 0, 163, 50));

        chkAdministrador.setText("Administrador");
        jPanel7.add(chkAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 338, 116, 40));

        jPTrabajador.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 479));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("CODIGO:");
        jPTrabajador.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 30, 110, 75));

        txtTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPTrabajador.add(txtTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 40, 187, 50));

        jTListaTrabajador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "DNI", "EDAD", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTListaTrabajador);
        if (jTListaTrabajador.getColumnModel().getColumnCount() > 0) {
            jTListaTrabajador.getColumnModel().getColumn(0).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTListaTrabajador.getColumnModel().getColumn(1).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(2).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTListaTrabajador.getColumnModel().getColumn(3).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTListaTrabajador.getColumnModel().getColumn(4).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(5).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(6).setResizable(false);
            jTListaTrabajador.getColumnModel().getColumn(6).setHeaderValue("DIRECCION");
        }

        jPTrabajador.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 123, 613, 302));

        jBBuscarTrabajador.setText("BUSCAR");
        jBBuscarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarTrabajadorActionPerformed(evt);
            }
        });
        jPTrabajador.add(jBBuscarTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 163, 50));

        jBActualizarTrabajador.setText("ACTUALIZAR TRABAJADOR");
        jBActualizarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarTrabajadorActionPerformed(evt);
            }
        });
        jPTrabajador.add(jBActualizarTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 436, 227, 39));

        jTabbedPane1.addTab("", jPTrabajador);

        jPProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tFNombreProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel10.add(tFNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 78, 179, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Nombre");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 78, 153, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Precio");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 120, 153, 30));

        tFPrecioProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tFPrecioProductoKeyTyped(evt);
            }
        });
        jPanel10.add(tFPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 179, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Categoria");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 156, 153, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Marca");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 198, 153, 30));

        tFMarcaProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel10.add(tFMarcaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 198, 179, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Stock");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 240, 153, 30));

        jBRegistroProducto.setBackground(new java.awt.Color(0, 255, 51));
        jBRegistroProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBRegistroProducto.setText("REGISTRAR");
        jBRegistroProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistroProductoActionPerformed(evt);
            }
        });
        jPanel10.add(jBRegistroProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 344, 179, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("PRODUCTOS");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 0, 142, 50));
        jPanel10.add(jCCategoriaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 156, 179, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Fecha de vencimiento");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 288, 153, 30));
        jPanel10.add(jSpStockProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 179, 30));

        jdcFecha.setDateFormatString("dd/MM/yyyy");
        jPanel10.add(jdcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 180, 30));

        jPProducto.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 399, 479));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CODIGO:");
        jPProducto.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 41, 110, 46));

        txtProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPProducto.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 41, 187, 46));

        jTListaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "PRECIO", "CATEGORIA", "MARCA", "STOCK", "FECHA VEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTListaProductos);
        if (jTListaProductos.getColumnModel().getColumnCount() > 0) {
            jTListaProductos.getColumnModel().getColumn(0).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTListaProductos.getColumnModel().getColumn(1).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(1).setHeaderValue("NOMBRE");
            jTListaProductos.getColumnModel().getColumn(2).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTListaProductos.getColumnModel().getColumn(2).setHeaderValue("PRECIO");
            jTListaProductos.getColumnModel().getColumn(3).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTListaProductos.getColumnModel().getColumn(3).setHeaderValue("CATEGORIA");
            jTListaProductos.getColumnModel().getColumn(4).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(4).setHeaderValue("MARCA");
            jTListaProductos.getColumnModel().getColumn(5).setResizable(false);
            jTListaProductos.getColumnModel().getColumn(5).setHeaderValue("STOCK");
            jTListaProductos.getColumnModel().getColumn(6).setHeaderValue("FECHA VEN");
        }

        jPProducto.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 123, 613, 302));

        jBBuscarProducto.setText("BUSCAR");
        jBBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarProductoActionPerformed(evt);
            }
        });
        jPProducto.add(jBBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 41, 163, 46));

        jBActualizarProducto.setText("ACTUALIZAR PRODUCTO");
        jBActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarProductoActionPerformed(evt);
            }
        });
        jPProducto.add(jBActualizarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 443, 227, 39));

        jButton1.setText("REGISTRAR CATEGORIA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPProducto.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 443, 234, 39));

        jTabbedPane1.addTab("", jPProducto);

        jPVenta1.setBackground(new java.awt.Color(255, 255, 255));
        jPVenta1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTListaProductosVendidos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "PRECIO", "CATEGORIA", "MARCA", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTListaProductosVendidos1);
        if (jTListaProductosVendidos1.getColumnModel().getColumnCount() > 0) {
            jTListaProductosVendidos1.getColumnModel().getColumn(0).setResizable(false);
            jTListaProductosVendidos1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTListaProductosVendidos1.getColumnModel().getColumn(1).setResizable(false);
            jTListaProductosVendidos1.getColumnModel().getColumn(2).setResizable(false);
            jTListaProductosVendidos1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTListaProductosVendidos1.getColumnModel().getColumn(3).setResizable(false);
            jTListaProductosVendidos1.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTListaProductosVendidos1.getColumnModel().getColumn(4).setResizable(false);
            jTListaProductosVendidos1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPVenta1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 970, 170));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Codigo:");
        jPVenta1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 60, 130, 39));

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Categoria");
        jPVenta1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 76, 25));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Nombre");
        jPVenta1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 76, 25));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Marca");
        jPVenta1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 76, 25));

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Precio");
        jPVenta1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, 76, 25));

        jLabel40.setBackground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 76, 25));

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 76, 25));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 76, 25));

        jLabel43.setBackground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 105, 25));

        jLCodigoTrabajador1.setText("     ");
        jPVenta1.add(jLCodigoTrabajador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(959, 453, -1, -1));

        jSpCantidad.setModel(new javax.swing.SpinnerNumberModel());
        jSpCantidad.setPreferredSize(new java.awt.Dimension(30, 26));
        jPVenta1.add(jSpCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 87, 40));

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Cantidad");
        jPVenta1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 76, 40));

        jbAgregarATabla.setText("Agregar a la tabla");
        jbAgregarATabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAgregarATablaActionPerformed(evt);
            }
        });
        jPVenta1.add(jbAgregarATabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 200, 180, 40));

        jbRegistrarVenta.setBackground(new java.awt.Color(0, 255, 255));
        jbRegistrarVenta.setText("REGISTRAR VENTA");
        jbRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegistrarVentaActionPerformed(evt);
            }
        });
        jPVenta1.add(jbRegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 200, 41));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("REGISTRAR VENTA");
        jPVenta1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 451, 50));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("DNI:");
        jPVenta1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 60, 39));

        tFCodigoClienteBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tFCodigoClienteBuscar.setToolTipText("");
        jPVenta1.add(tFCodigoClienteBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 179, 39));

        btnBuscarCliente.setText("BUSCAR CLIENTE");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPVenta1.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 131, 41));

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Monto a pagar");
        jPVenta1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 102, 42));

        jSpMontoPagar.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpMontoPagar.setPreferredSize(new java.awt.Dimension(30, 26));
        jPVenta1.add(jSpMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 100, 40));
        jPVenta1.add(tFTotalPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 129, 39));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("TOTAL A PAGAR");
        jPVenta1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 123, 39));

        jComboBox1.setBackground(new java.awt.Color(0, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE TIPO DE PAGO" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPVenta1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 200, 41));

        tFCodigoProductoBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPVenta1.add(tFCodigoProductoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 170, 39));

        jbBuscarProducto.setText("BUSCAR PRODUCTO");
        jbBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarProductoActionPerformed(evt);
            }
        });
        jPVenta1.add(jbBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(843, 60, 170, 40));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Nombre");
        jPVenta1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 76, 25));

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Codigo");
        jPVenta1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 76, 25));

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 76, 25));

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 76, 25));

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 76, 25));

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPVenta1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 105, 25));

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("DNI");
        jPVenta1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 76, 25));

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Apellidos");
        jPVenta1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 76, 25));

        javax.swing.GroupLayout jPVentasLayout = new javax.swing.GroupLayout(jPVentas);
        jPVentas.setLayout(jPVentasLayout);
        jPVentasLayout.setHorizontalGroup(
            jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentasLayout.createSequentialGroup()
                .addComponent(jPVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPVentasLayout.setVerticalGroup(
            jPVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", jPVentas);

        jPRegistroVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("CODIGO:");
        jPRegistroVenta.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 110, 50));

        txtVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPRegistroVenta.add(txtVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 187, 50));

        jBBuscarVenta.setText("BUSCAR");
        jBBuscarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarVentaActionPerformed(evt);
            }
        });
        jPRegistroVenta.add(jBBuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 163, 50));

        jTListaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CODIGO CLIENTE", "MONTO PAGO", "VUELTO", "CODIGO TRABAJADOR VENTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTListaVentas);
        if (jTListaVentas.getColumnModel().getColumnCount() > 0) {
            jTListaVentas.getColumnModel().getColumn(0).setResizable(false);
            jTListaVentas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTListaVentas.getColumnModel().getColumn(1).setResizable(false);
            jTListaVentas.getColumnModel().getColumn(2).setResizable(false);
            jTListaVentas.getColumnModel().getColumn(3).setResizable(false);
            jTListaVentas.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTListaVentas.getColumnModel().getColumn(4).setResizable(false);
        }

        jPRegistroVenta.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 110, 776, 300));

        jbNuevaVenta.setText("NUEVA VENTA");
        jbNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevaVentaActionPerformed(evt);
            }
        });
        jPRegistroVenta.add(jbNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 145, 33));

        jBTotalGanancia.setText("TOTAL DE GANANCIAS");
        jBTotalGanancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTotalGananciaActionPerformed(evt);
            }
        });
        jPRegistroVenta.add(jBTotalGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, 195, 33));

        jBDetalleVenta.setText("DETALLE DE VENTA");
        jBDetalleVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDetalleVentaActionPerformed(evt);
            }
        });
        jPRegistroVenta.add(jBDetalleVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 164, 33));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("VENTA");
        jPRegistroVenta.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 210, 32));

        botonReporte.setText("REPORTE");
        botonReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonReporteMouseClicked(evt);
            }
        });
        jPRegistroVenta.add(botonReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 440, 140, 30));

        jTabbedPane1.addTab("", jPRegistroVenta);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 106, 1070, 523));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBClientesActionPerformed
        this.ListarClientes(this.sup.getlC());
        this.jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jBClientesActionPerformed

    private void jBProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProductoActionPerformed
        this.ListarProductos(this.sup.getlP());
        this.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jBProductoActionPerformed

    private void jBBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarClienteActionPerformed
        modelo = (DefaultTableModel) this.jTListaCliente.getModel();
        modelo.setRowCount(0);
        NodoCliente aux = this.sup.getlC().getPrimero();
        while ( aux != null ){
            if ( txtCliente.getText().equals(aux.getCli().getCodigo())){
                modelo.addRow(aux.getCli().mostrar());
            }
            aux = aux.getSgte();
        }
        this.jTListaCliente.setModel(modelo);
    }//GEN-LAST:event_jBBuscarClienteActionPerformed

    private void jBBuscarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarTrabajadorActionPerformed
       modelo = (DefaultTableModel) this.jTListaTrabajador.getModel();
       modelo.setRowCount(0);
       NodoTrabajador aux = this.sup.getlT().getCab();
       while ( aux != null ){
           if ( txtTrabajador.getText().equals(aux.getTra().getCodigo())){
                modelo.addRow(aux.getTra().mostrar());
           }
           aux = aux.getSgte();
       }
       this.jTListaTrabajador.setModel(modelo);
    }//GEN-LAST:event_jBBuscarTrabajadorActionPerformed

    private void jBTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTrabajadorActionPerformed
        this.ListarTrabajadores(this.sup.getlT());
        this.jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jBTrabajadorActionPerformed

    private void jBRegistroTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistroTrabajadorActionPerformed
        if ( this.tFNombresTrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta nombres","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFApePaTrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido paterno","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFApeMaTrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido materno","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFEdadTrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta edad","Error",JOptionPane.ERROR_MESSAGE);
            return;        
        }
        if ( this.tFDireccionTrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta direccion","Error",JOptionPane.ERROR_MESSAGE);
        }
        if ( this.tFDNITrabajador.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta DNI","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String codigo = "T00"+String.valueOf(this.sup.getlT().getnTra()+1);
        
        Trabajador newTra = new Trabajador(codigo,tFNombresTrabajador.getText(),tFApePaTrabajador.getText(),
                            tFApeMaTrabajador.getText(),Integer.parseInt(tFEdadTrabajador.getText()),tFDireccionTrabajador.getText(),
                            tFDNITrabajador.getText(),chkAdministrador.isSelected());
        this.sup.setListaTrabajador(newTra);
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        LimpiarTrabajador();
        this.ListarTrabajadores(sup.getlT());
    }//GEN-LAST:event_jBRegistroTrabajadorActionPerformed

    private void jBRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistroClienteActionPerformed
        if ( this.tFNombreCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta nombres","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( this.tFApePaCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido paterno","Error",JOptionPane.ERROR_MESSAGE);
            return;        
        }
        if ( this.tFApeMaCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta apellido materno","Error",JOptionPane.ERROR_MESSAGE);
            return;      
        }
        if ( this.tFEdadCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta edad","Error",JOptionPane.ERROR_MESSAGE);
            return;        
        }
        if ( this.tFDireccionCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta direccion","Error",JOptionPane.ERROR_MESSAGE);
            return;        
        }
        if ( this.tFDNICliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta DNI","Error",JOptionPane.ERROR_MESSAGE);
            return;        
        }
        String codigo = "C00"+String.valueOf(this.sup.getlC().getnCli()+1);
        Cliente newCliente = new Cliente(codigo,tFNombreCliente.getText(),tFApePaCliente.getText(),tFApeMaCliente.getText(),Integer.parseInt(tFEdadCliente.getText()),tFDireccionCliente.getText(),tFDNICliente.getText());
        this.sup.setListaCliente(newCliente);
        this.sup.getlC().guardarDatosCliente(newCliente);
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        LimpiarCliente();
        this.ListarClientes(this.sup.getlC());
    }//GEN-LAST:event_jBRegistroClienteActionPerformed

    private void jBCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarSesionActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(this, "¿Deseas salir?");
        if ( JOptionPane.OK_OPTION == confirmado ){
            this.dispose();
            FrmLogin frLog = new FrmLogin();
            frLog.setSupe(sup);
            frLog.setVisible(true);
        }
        else{
            return;
        }
    }//GEN-LAST:event_jBCerrarSesionActionPerformed
    
    public boolean productoExistente(String valor){
        if ( this.lPVen.getCab() != null ){
            NodoProducto aux = this.lPVen.getCab();
            while ( aux != null ){
                if ( valor.equals(aux.getPro().getCodigo())){
                    return true;
                }
                aux = aux.getSgte();
            }
        } 
        return false;
    }
    
    private void jBRegistroProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistroProductoActionPerformed
        if ( tFNombreProducto.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta nombres","Error",JOptionPane.ERROR_MESSAGE);
            return;         
        }
        if ( tFPrecioProducto.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Falta precio del producto","Error",JOptionPane.ERROR_MESSAGE);
            return;    
        }
        if ( tFMarcaProducto.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Falta marca del producto", "Error",JOptionPane.ERROR_MESSAGE);
            return;    
        }
        if ( jSpStockProducto.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Falta stock del producto", "Error",JOptionPane.ERROR_MESSAGE);
            return;    
        }
        String codigo = "P00"+String.valueOf(this.sup.getlP().getnProd()+1);
        String categoria = jCCategoriaProducto.getItemAt(jCCategoriaProducto.getSelectedIndex());
        
        Producto newPro = new Producto(codigo,tFNombreProducto.getText(),Double.parseDouble(tFPrecioProducto.getText()),categoria,tFMarcaProducto.getText(),Integer.parseInt(jSpStockProducto.getValue().toString()),jdcFecha.getDate());
        this.sup.getlP().insertarProducto(newPro);
        this.sup.getlP().guardarProductoEnArchivo(newPro);
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        LimpiarProducto();
        this.ListarProductos(this.sup.getlP());
    }//GEN-LAST:event_jBRegistroProductoActionPerformed

    private void jBBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarProductoActionPerformed
       modelo = (DefaultTableModel) this.jTListaProductos.getModel();
       modelo.setRowCount(0);
       NodoProducto aux = this.sup.getlP().getCab();
       while ( aux != null ){
           if ( txtProducto.getText().equals(aux.getPro().getCodigo())){
                modelo.addRow(aux.getPro().mostrarProducto());
           }
           aux = aux.getSgte();
       }
       this.jTListaProductos.setModel(modelo);
    }//GEN-LAST:event_jBBuscarProductoActionPerformed

    private void jBVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVentasActionPerformed
        this.ListarVentas(this.sup.getlV());
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jBVentasActionPerformed

    private void jbAgregarATablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAgregarATablaActionPerformed
        if ( jSpCantidad.getValue().equals(0) ){
            JOptionPane.showMessageDialog(this, "AGREGUE UNA CANTIDAD","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Producto busqueda = this.sup.getlP().buscarProducto(tFCodigoProductoBuscar.getText());
        if ( busqueda == null ){
            JOptionPane.showMessageDialog(this, "Producto no encontrado","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ( this.lPVen.getCab() != null ){
            if (productoExistente(busqueda.getCodigo()) == true){
                JOptionPane.showMessageDialog(this, "Producto ya elegido","ERROR",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Producto pro = this.sup.getlP().buscarNombre(jLabel40.getText());
        int cantidad = Integer.parseInt(jSpCantidad.getValue().toString())   ;
        Producto newProTabla = new Producto(pro.getCodigo(),pro.getNombre(),pro.getPrecio(),pro.getCategoria(),pro.getMarca(),cantidad,pro.getFechaV());
        this.lPVen.insertarProducto(newProTabla);
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        double montoPago = this.montoPago();
        jLabel40.setText("");
        jLabel41.setText("");
        jLabel42.setText("");
        jLabel43.setText("");
        jSpCantidad.setValue(0);
        tFTotalPago.setText(String.valueOf(montoPago));
        this.ListarProductosVendidos(this.lPVen);
        this.jbRegistrarVenta.setEnabled(true);
    }//GEN-LAST:event_jbAgregarATablaActionPerformed

    private void limpiarCamposVenta(){
        tFCodigoClienteBuscar.setText("");
        tFCodigoProductoBuscar.setText("");
        jLabel49.setText("");
        jLabel50.setText("");
        jLabel51.setText("");
        jLabel52.setText("");
        modelo = (DefaultTableModel) jTListaProductosVendidos1.getModel();
        modelo.setRowCount(0);
        jTListaProductosVendidos1.setModel(modelo);
        tFTotalPago.setText("");
        jSpMontoPagar.setValue(0);
        jSpCantidad.setValue(0);
        jBClientes.setVisible(true);
        jBProducto.setVisible(true);
        jBVentas.setVisible(true);
        jBCerrarSesion.setVisible(true);
        this.tFCodigoClienteBuscar.setEditable(true);
        this.jbRegistrarVenta.setEnabled(false);
    }
   
    
    private void jbRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegistrarVentaActionPerformed
        Cliente clien = this.sup.getlC().buscarCliente(jLabel49.getText());
        Trabajador tra = this.sup.getlT().buscarTrabajador(this.jLCodigoTrabajador1.getText());
        if ( tra == null ){
            JOptionPane.showMessageDialog(this, "TRABAJADOR NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        double totalPago = 0.0, montoPagar = 0.0, vuelto = 0.0;
        montoPagar = Double.parseDouble(tFTotalPago.getText());
        totalPago = Double.parseDouble(jSpMontoPagar.getValue().toString());
        if ( totalPago < montoPagar ){
            JOptionPane.showMessageDialog(this, "MONTO DE PAGO INSUFICIENTE","ERROR",JOptionPane.ERROR_MESSAGE);
            return;    
        }
        vuelto = totalPago - montoPagar;
        
        int indiceSeleccionado = this.jComboBox1.getSelectedIndex();

        switch ( indiceSeleccionado ){
            case 1 -> pago = pagoFactory.ObtenerPago(TipoDePago.EFECTIVO);
            case 2 -> pago = pagoFactory.ObtenerPago(TipoDePago.YAPE);
            case 3 -> pago = pagoFactory.ObtenerPago(TipoDePago.PLIN);
            case 4 -> pago = pagoFactory.ObtenerPago(TipoDePago.BCP);
            case 5 -> pago = pagoFactory.ObtenerPago(TipoDePago.MASTER_CARD);
            case 6 -> pago = pagoFactory.ObtenerPago(TipoDePago.PAYPAL);
            case 7 -> pago = pagoFactory.ObtenerPago(TipoDePago.BITCOIN);
            default -> {
                JOptionPane.showMessageDialog(null, "Seleccione una opcion valida");
                return;
            }
        }
        
        pago.crearPago();
        Ventas ven = new Ventas("V00"+String.valueOf(this.sup.getlV().getnVen()+1),clien,tra,
                this.lPVen,totalPago,vuelto,this.jComboBox1.getItemAt(this.jComboBox1.getSelectedIndex()),DateAString(new Date()));
        this.sup.setListaVenta(ven);
        this.sup.getlV().guardarVentasEnArchivo(ven);
        JOptionPane.showMessageDialog(this, "REGISTRO EXITOSO","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
        this.limpiarCamposVenta();
        if ( tra.isTipo() ){
            this.jBClientes.setLocation(22, 107);
            this.jBTrabajador.setLocation(22, 202);
            this.jBProducto.setLocation(22, 300);
            this.jBVentas.setLocation(22, 391);
            this.jBCerrarSesion.setLocation(22, 486);
            this.jBTrabajador.setVisible(true);
        }
        else{
            this.jBClientes.setLocation(22, 107);
            this.jBProducto.setLocation(22, 202);
            this.jBVentas.setLocation(22, 300);
            this.jBCerrarSesion.setLocation(22, 391);    
        }
        jTabbedPane1.setSelectedIndex(4);        
        this.ListarVentas(sup.getlV());
    }//GEN-LAST:event_jbRegistrarVentaActionPerformed

    private void jBBuscarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarVentaActionPerformed
        if ( this.sup.getlV().getCab() != null ){
            modelo = (DefaultTableModel) this.jTListaVentas.getModel();
            modelo.setRowCount(0);    
            Ventas ven = this.sup.getlV().buscarVenta(txtVenta.getText());
            if ( ven == null ){
                JOptionPane.showMessageDialog(this, "VENTA NO ENCONTRADA","ERROR",JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.addRow(ven.mostrarVenta());
            this.jTListaVentas.setModel(modelo);
        }
        else{
            JOptionPane.showMessageDialog(this, "Lista de ventas vacia","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jBBuscarVentaActionPerformed

    private void jbNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevaVentaActionPerformed
        if ( sup.getlC().getPrimero() == null ){
            JOptionPane.showMessageDialog(this, "Primero registre cliente","ERROR",JOptionPane.ERROR_MESSAGE);
            jTabbedPane1.setSelectedIndex(0);
            tFNombreCliente.requestFocus();
        }
        else{
            this.lPVen = new ListaProductos();
            lPVen.setCab(null);
            lPVen.setnProd(0);
            jBClientes.setVisible(false);
            jBTrabajador.setVisible(false);
            jBProducto.setVisible(false);
            jBVentas.setVisible(false);
            jBCerrarSesion.setVisible(false);
            jTabbedPane1.setSelectedIndex(3);
        }
    }//GEN-LAST:event_jbNuevaVentaActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        Cliente cli = this.sup.getlC().buscarClienteDNI(tFCodigoClienteBuscar.getText());
        if ( cli == null ){
            JOptionPane.showMessageDialog(this, "CLIENTE NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        jLabel49.setText(cli.getCodigo());
        jLabel50.setText(cli.getNombre());
        jLabel51.setText(cli.getApellidoPaterno());
        jLabel52.setText(cli.getDNI());
        tFCodigoClienteBuscar.setText("");
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void jBActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarProductoActionPerformed
        if ( this.sup.getlT().getCab() == null){
            JOptionPane.showMessageDialog(this, "LISTA VACIA","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if ( jTListaProductos.getSelectedRowCount() == 1){
                Producto pro = this.sup.getlP().buscarProducto(jTListaProductos.getValueAt(jTListaProductos.getSelectedRow(), 0).toString());
                FrmActualizarProducto frAP = new FrmActualizarProducto();
                frAP.llenarCategorias(sup.categorias);
                frAP.setlP(this.sup.getlP());
                frAP.setPro(pro);
                frAP.asignarValores();
                frAP.show();
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione un elemento de la tabla");
            }
        }
    }//GEN-LAST:event_jBActualizarProductoActionPerformed

    private void jBActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarClienteActionPerformed
        if ( this.sup.getlC().getPrimero() == null ){
            JOptionPane.showMessageDialog(this, "LISTA VACIA","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if ( jTListaCliente.getSelectedRowCount() == 1 ){
                Cliente clien = this.sup.getlC().buscarCliente(jTListaCliente.getValueAt(jTListaCliente.getSelectedRow(),0).toString());
                FrmActualizarCliente frAC = new FrmActualizarCliente();
                frAC.setlC(this.sup.getlC());
                frAC.setCli(clien);
                frAC.asignarValores();
                frAC.show();
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione un elemento de la tabla");
            }
        }
    }//GEN-LAST:event_jBActualizarClienteActionPerformed

    private void jBDetalleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDetalleVentaActionPerformed
        if ( this.sup.getlV().getCab() == null ){
            JOptionPane.showMessageDialog(this, "LISTA VACIA","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if ( jTListaVentas.getSelectedRowCount() == 1 ){
                Ventas ven = this.sup.getlV().buscarVenta(jTListaVentas.getValueAt(jTListaVentas.getSelectedRow(), 0).toString());
                if ( ven == null ){
                    JOptionPane.showMessageDialog(this,"Venta no encontrada");
                    return;
                }
                ven.generarPDF();
                
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione un elemento de la tabla");
            }
        }

    }//GEN-LAST:event_jBDetalleVentaActionPerformed

    private void jBActualizarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarTrabajadorActionPerformed
        if ( this.sup.getlT().getCab() == null ){
            JOptionPane.showMessageDialog(this, "LISTA VACIA","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if (jTListaTrabajador.getSelectedRowCount() == 1){
                Trabajador tra = this.sup.getlT().buscarTrabajador(jTListaTrabajador.getValueAt(jTListaTrabajador.getSelectedRow(),0).toString());
                FrmActualizarTrabajador frAT = new FrmActualizarTrabajador();
                frAT.setlT(sup.getlT());
                frAT.setTra(tra);
                frAT.asignarValores();
                frAT.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this,"Seleccione un elemento de la tabla");
            }
        }
    }//GEN-LAST:event_jBActualizarTrabajadorActionPerformed

    private void limpiarLabels(){
        jLabel40.setText("");
        jLabel41.setText("");
        jLabel42.setText("");
        jLabel43.setText("");
    }
    
    private void tFDNIClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFDNIClienteKeyTyped
        char c = evt.getKeyChar();
        if ( c < '0' || c > '9' ){
            evt.consume();
        }
    }//GEN-LAST:event_tFDNIClienteKeyTyped

    private void tFEdadClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFEdadClienteKeyTyped
        char c = evt.getKeyChar();
        if ( c < '0' || c > '9' ){
            evt.consume();
        }
    }//GEN-LAST:event_tFEdadClienteKeyTyped

    private void tFEdadTrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFEdadTrabajadorKeyTyped
        char c = evt.getKeyChar();
        if ( c < '0' || c > '9' ){
            evt.consume();
        }
    }//GEN-LAST:event_tFEdadTrabajadorKeyTyped

    private void tFDNITrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFDNITrabajadorKeyTyped
         char c = evt.getKeyChar();
        if ( c < '0' || c > '9' ){
            evt.consume();
        }
    }//GEN-LAST:event_tFDNITrabajadorKeyTyped

    private void tFPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tFPrecioProductoKeyTyped
        char c = evt.getKeyChar();
        if ( c < '0' || c > '9' ){
            evt.consume();
        }
    }//GEN-LAST:event_tFPrecioProductoKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.llenarComboBox();
        this.llenarComboBoxTipoPago();
        this.ListarClientes(sup.getlC());
        this.ListarProductos(sup.getlP());
        this.ListarTrabajadores(sup.getlT());
        this.ListarVentas(sup.getlV());
    }//GEN-LAST:event_formWindowActivated

    
    public double totalGanancia(){
        double cantidad = 0;
        /*NodoVentas aux = sup.getlV().getCab();
        while ( aux != null ){
            cantidad += aux.getVen().getMontoPago();
        }
        */
        for ( int i = 0; i < jTListaVentas.getRowCount(); i++ ){
            cantidad += Double.parseDouble(jTListaVentas.getValueAt(i, 2).toString());
        }
        return cantidad;
    }
    
    private void jBTotalGananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTotalGananciaActionPerformed
        double ganancias = totalGanancia();
        JOptionPane.showMessageDialog(this,"Total de ganancias: S/."+ganancias);
    }//GEN-LAST:event_jBTotalGananciaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Interfaz_Categoria cat = new Interfaz_Categoria();
                cat.setCaretaker(sup.getCaretaker());
                cat.setSup(sup);
                cat.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarProductoActionPerformed
        Producto pro = this.sup.getlP().buscarProducto(tFCodigoProductoBuscar.getText());
        if ( pro == null ){
            JOptionPane.showMessageDialog(this,"Producto no encontrado");
            return;
        }
        jLabel40.setText(pro.getNombre());
        jLabel41.setText(pro.getCategoria());
        jLabel42.setText(pro.getMarca());
        jLabel43.setText(String.valueOf(pro.getPrecio()));
    }//GEN-LAST:event_jbBuscarProductoActionPerformed

    private void botonCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseEntered
        buttonExit.setBackground(new Color(0,255,255));
        botonCerrar.setBackground(new Color(0,0,153));
    }//GEN-LAST:event_botonCerrarMouseEntered

    private void botonCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseExited
        buttonExit.setBackground(new Color(240,240,240));
        botonCerrar.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_botonCerrarMouseExited

    private void botonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseClicked
        int confirmado = JOptionPane.showConfirmDialog(this, "¿Deseas salir?");
        if ( JOptionPane.OK_OPTION == confirmado ){
            System.exit(0);
        }
        else{
            return;
        }
    }//GEN-LAST:event_botonCerrarMouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        int estado = jComboBox1.getSelectedIndex();
        
        if ( estado <= 0 ){
            this.jSpMontoPagar.setEnabled(false);
            this.jbRegistrarVenta.setEnabled(false);
        }
        else{
            this.jSpMontoPagar.setEnabled(true);
            this.jbRegistrarVenta.setEnabled(true);    
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void botonReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonReporteMouseClicked
        if ( sup.getlV().getCab() == null ){
            JOptionPane.showMessageDialog(null, "LISTA DE VENTAS VACIA", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.sup.getlV().generarReportePDF();
    }//GEN-LAST:event_botonReporteMouseClicked

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botonCerrar;
    private javax.swing.JButton botonReporte;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JPanel buttonExit;
    private javax.swing.JCheckBox chkAdministrador;
    private javax.swing.JButton jBActualizarCliente;
    private javax.swing.JButton jBActualizarProducto;
    private javax.swing.JButton jBActualizarTrabajador;
    private javax.swing.JButton jBBuscarCliente;
    private javax.swing.JButton jBBuscarProducto;
    private javax.swing.JButton jBBuscarTrabajador;
    private javax.swing.JButton jBBuscarVenta;
    private javax.swing.JButton jBCerrarSesion;
    private javax.swing.JButton jBClientes;
    private javax.swing.JButton jBDetalleVenta;
    private javax.swing.JButton jBProducto;
    private javax.swing.JButton jBRegistroCliente;
    private javax.swing.JButton jBRegistroProducto;
    private javax.swing.JButton jBRegistroTrabajador;
    private javax.swing.JButton jBTotalGanancia;
    private javax.swing.JButton jBTrabajador;
    private javax.swing.JButton jBVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCCategoriaProducto;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLCodigoTrabajador1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPCliente;
    private javax.swing.JPanel jPProducto;
    private javax.swing.JPanel jPRegistroVenta;
    private javax.swing.JPanel jPTrabajador;
    private javax.swing.JPanel jPVenta1;
    private javax.swing.JPanel jPVentas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpCantidad;
    private javax.swing.JSpinner jSpMontoPagar;
    private javax.swing.JSpinner jSpStockProducto;
    private javax.swing.JTable jTListaCliente;
    private javax.swing.JTable jTListaProductos;
    private javax.swing.JTable jTListaProductosVendidos1;
    private javax.swing.JTable jTListaTrabajador;
    private javax.swing.JTable jTListaVentas;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbAgregarATabla;
    private javax.swing.JButton jbBuscarProducto;
    private javax.swing.JButton jbNuevaVenta;
    private javax.swing.JButton jbRegistrarVenta;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTextField tFApeMaCliente;
    private javax.swing.JTextField tFApeMaTrabajador;
    private javax.swing.JTextField tFApePaCliente;
    private javax.swing.JTextField tFApePaTrabajador;
    private javax.swing.JTextField tFCodigoClienteBuscar;
    private javax.swing.JTextField tFCodigoProductoBuscar;
    private javax.swing.JLabel tFCodigoTrabajadorInicio;
    private javax.swing.JTextField tFDNICliente;
    private javax.swing.JTextField tFDNITrabajador;
    private javax.swing.JTextField tFDireccionCliente;
    private javax.swing.JTextField tFDireccionTrabajador;
    private javax.swing.JTextField tFEdadCliente;
    private javax.swing.JTextField tFEdadTrabajador;
    private javax.swing.JTextField tFMarcaProducto;
    private javax.swing.JTextField tFNombreCliente;
    private javax.swing.JTextField tFNombreProducto;
    private javax.swing.JTextField tFNombresTrabajador;
    private javax.swing.JTextField tFPrecioProducto;
    private javax.swing.JTextField tFTotalPago;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtTrabajador;
    private javax.swing.JTextField txtVenta;
    // End of variables declaration//GEN-END:variables
}
