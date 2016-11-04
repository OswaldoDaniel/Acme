/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import models.*;
import views.*;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerMain implements ActionListener {

    private ModelMain modelMain;
    private ViewMain viewMain;
    
    
    ModelClientes modelClientes;
    ViewClientes viewClientes;
    
    ModelCompras modelCompras;
    viewCompras viewCompras;
    
    ModelProductos modelProductos;
    ViewProductos viewProductos;
    
    ModelLogin modelLogin;
    ViewIniciarSecion viewIniciarSecion;
    
    ModelVentas modelVentas;
    ViewVentas viewVentas;
    
    ModelUsuario modelUsuario;
    ViewUsuario viewUsuario;
    
    public ControllerMain(ModelMain modelMain, ModelClientes modelClientes, ModelCompras modelCompras,ModelProductos modelProductos,ModelLogin modelLogin, ModelVentas modelVentas,ModelUsuario modelUsuario, ViewMain viewMain,ViewClientes viewClientes,viewCompras viewCompras,ViewProductos viewProductos, ViewIniciarSecion viewIniciarSecion, ViewVentas viewVentas, ViewUsuario viewUsuario) {
        this.viewMain = viewMain;
        this.modelMain = modelMain;
        this.modelProductos = modelProductos;
        this.viewProductos = viewProductos;
        this.modelLogin = modelLogin;
        this.viewIniciarSecion = viewIniciarSecion;
        this.modelVentas = modelVentas;
        this.viewVentas = viewVentas;
        this.modelUsuario = modelUsuario;
        this.viewUsuario = viewUsuario;
        this.viewMain.jMenuItemProductos.addActionListener(this);
        this.viewMain.jMenuItemIniciar.addActionListener(this);
        this.viewMain.jMenuItemUsuario.addActionListener(this);
        this.viewMain.jmiCliente.addActionListener(this);
        this.viewMain.jmiCompras.addActionListener(this);
        this.viewMain.jmiVentas.addActionListener(this);

    }

    public void init_view() {
        this.viewMain.setTitle("Acme");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);

    }

    public void actionPerfomedProductos() {
        this.viewMain.setContentPane(viewProductos);
        //cc.viewClientes.setVisible(true);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedLogin() {
        this.viewMain.setContentPane(viewIniciarSecion);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedUsuario() {
        this.viewMain.setContentPane(viewUsuario);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedCliente() {
        this.viewMain.setContentPane(this.viewClientes);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedCompras() {
        this.viewMain.setContentPane(viewCompras);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedVentas() {
        this.viewMain.setContentPane(viewVentas);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewMain.jMenuItemProductos) {
            actionPerfomedProductos();
        } else if (ae.getSource() == this.viewMain.jMenuItemIniciar) {
            actionPerfomedLogin();
        } else if (ae.getSource() == this.viewMain.jMenuItemUsuario) {
            actionPerfomedUsuario();
        } else if (ae.getSource() == this.viewMain.jmiCliente){
            actionPerfomedCliente();
        } else if (ae.getSource()== this.viewMain.jmiCompras){
            actionPerfomedCompras();
        } else if (ae.getSource()== this.viewMain.jmiVentas){
            actionPerfomedVentas();
        }
    }
}
