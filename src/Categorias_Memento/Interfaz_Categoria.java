/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categorias_Memento;



import Clases.SuperMercado;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 *
 * @author Jose Manuel
 */
public class Interfaz_Categoria extends JFrame{
    JLabel labelNombre = new JLabel();
    JPanel panelPrincipal = new JPanel();
    JTextField textoNombre = new JTextField();
    JButton boton1 = new JButton(); //izquierda
    JButton boton2 = new JButton(); // derecha
    JButton boton3 = new JButton(); // guardar
    JButton boton4 = new JButton(); // actualizar
    
    private CaretakerCategorias caretaker; 
    private OriginatorCategoria originator;
    private int currentStateIndex = -1;
    
    SuperMercado sup;

    public SuperMercado getSup() {
        return sup;
    }

    public void setSup(SuperMercado sup) {
        this.sup = sup;
    }

    
    public Interfaz_Categoria(){
        inicializarComponentes();
        funcionamientoGUI();
        caretaker = new CaretakerCategorias();
        originator = new OriginatorCategoria();
    }


    public void inicializarComponentes()
    {
        panelPrincipal.setSize(500,250);
        panelPrincipal.setLayout(null);
        
        labelNombre.setText("Nombre");
        labelNombre.setLayout(null);
        labelNombre.setLocation(80,70);
        labelNombre.setSize(100,30);
        labelNombre.setVisible(true);
        panelPrincipal.add(labelNombre);
        
        textoNombre.setSize(300,30);
        textoNombre.setLocation(150, 70);
        textoNombre.setEditable(true);
        textoNombre.setVisible(true);
        panelPrincipal.add(textoNombre);

        boton1.setSize(65,25);
        boton1.setText("<");
        boton1.setLocation(80, 150);
        boton1.setVisible(true);
        panelPrincipal.add(boton1);
        
        boton2.setSize(65,25);
        boton2.setText(">");
        boton2.setLocation(150, 150);
        boton2.setVisible(true);
        panelPrincipal.add(boton2);
  
        boton3.setSize(80,25);
        boton3.setText("Guardar");
        boton3.setLocation(250, 150);
        boton3.setVisible(true);
        panelPrincipal.add(boton3);
       
        boton4.setSize(110,25);
        boton4.setText("Actualizar");
        boton4.setLocation(350, 150);
        boton4.setVisible(true);
        panelPrincipal.add(boton4);
        
        this.setTitle("AGREGAR CATEGORIA");
        this.requestFocus();

        this.setSize(500,250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(panelPrincipal);  
    }
    
    public void funcionamientoGUI(){
        
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStateIndex > 0) {
                    textoNombre.setEditable(false);
                    currentStateIndex--;
                    MementoCategoria memento = caretaker.getMementoo().get(currentStateIndex);
                    originator.restore(memento);
                    textoNombre.setText(originator.getNombre());
                } else {
                    JOptionPane.showMessageDialog(
                        Interfaz_Categoria.this,
                        "No hay estados anteriores para restaurar.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MementoCategoria> mementos = caretaker.getMementoo();
                int numMementos = mementos.size();
                if (currentStateIndex < numMementos - 1) {
                    textoNombre.setEditable(false);
                    currentStateIndex++;
                    MementoCategoria memento = mementos.get(currentStateIndex);
                    originator.restore(memento);
                    textoNombre.setText(originator.getNombre());
                } else {
                    JOptionPane.showMessageDialog(
                        Interfaz_Categoria.this,
                        "No hay estados posteriores para restaurar.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                    );
                    textoNombre.setText("");
                    textoNombre.setEditable(true);
                }
            }
        });
        
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textoNombre.getText();
                if ( nombre.isEmpty() ){
                    JOptionPane.showMessageDialog(null, "Ingrese una categoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                originator.setState(nombre);
                MementoCategoria memento = originator.guardar();
                caretaker.addMemento(memento);
                caretaker.guardarDatos(nombre);
                JOptionPane.showMessageDialog(null, "Estado guardado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                currentStateIndex = caretaker.getMementoo().size()-1;
                sup.setCategorias(caretaker.obtenerCategorias());
                dispose();
            }
        });
        
        
        
        boton4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String nombre = textoNombre.getText();
                MementoCategoria mem = caretaker.buscarCategoria(nombre);
                if ( mem == null || mem.getNombre().equals("") ){
                    JOptionPane.showMessageDialog(null,"No se ha encontrado la categoria");
                    return;
                }
                String reemplazar = LeerTexto("¿Cual es el nuevo nombre que desea colocar a esta categoria?");
                if ( reemplazar.isEmpty() ){
                    JOptionPane.showMessageDialog(null,"Ingrese un nombre...");
                    return;
                }
                caretaker.modificarNombre(nombre, reemplazar);
                caretaker.actualizarArchivo();
                JOptionPane.showMessageDialog(null,"Se ha modificado la categoria");
                sup.setCategorias(caretaker.obtenerCategorias());
                dispose();
            }
        });
    }
    
    
    public CaretakerCategorias getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(CaretakerCategorias caretaker) {
        this.caretaker = caretaker;
    }

    public OriginatorCategoria getOriginator() {
        return originator;
    }

    public void setOriginator(OriginatorCategoria originator) {
        this.originator = originator;
    }
    
    public static String LeerTexto(String mensaje){
        String valor;
        valor = JOptionPane.showInputDialog(mensaje);
        return valor;
    }
    
}
