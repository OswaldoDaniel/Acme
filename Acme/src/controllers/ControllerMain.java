/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelMain;
import sax.DBConnection;
import views.ViewMain;
/**
 *
 * @author L.A.M.M#13
 */
public class ControllerMain implements ActionListener {
   
    private ModelMain modelMain;
    private ViewMain viewMain;
    Object modules[];

    ControllerProductos controllerProducto;
    ControllerLogin controllerLogin;
    ControllerUsuario controllerUsuario;
    ControllerVentas controllerVenta;
    ControllerClientes controllerCliente;
    ControllerCompras controllerCompra;
    ControllerProveedores controllerProveedores;

    public ControllerMain(ModelMain modelMain, ViewMain viewMain, Object modules[] ){
        this.viewMain = viewMain;
        this.modelMain = modelMain;
        this.modules = modules;
        controllerProducto = (ControllerProductos) modules[0];
        controllerUsuario = (ControllerUsuario) modules[1];
        controllerLogin = (ControllerLogin) modules[2];
        controllerVenta = (ControllerVentas) modules[3];
        controllerCliente = (ControllerClientes) modules[4];
        controllerCompra = (ControllerCompras) modules[5];
        controllerProveedores = (ControllerProveedores) modules[6];
        this.controllerLogin.viewIniciarSesion.jbtnIngresar.addActionListener(this);
        this.viewMain.jMenuItemProductos.addActionListener(this);
        this.viewMain.jMenuItemIniciar.addActionListener(this);
        this.viewMain.jMenuItemUsuario.addActionListener(this);
        this.viewMain.jmiVentas.addActionListener(this);
        this.viewMain.jmiCliente.addActionListener(this);
        this.viewMain.jmiCompras.addActionListener(this);
        this.viewMain.jmiProveedores.addActionListener(this);
        init_view();
    }

    public void init_view() {
        this.viewMain.setTitle("Acme");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
        modificarSesion();
    }

    public void jmiProductosActionPerfomed() {
        this.viewMain.setContentPane(controllerProducto.viewProductos);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void jmiUsuarioActionPerfomed() {
        this.viewMain.setContentPane(controllerUsuario.viewUsuario);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void jmiVentaActionPerfomed() {
        this.viewMain.setContentPane(controllerVenta.viewVentas);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void jmiCompraActionPerfomed() {
        this.viewMain.setContentPane(controllerCompra.viewCompras);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void jmiClienteActionPerfomed() {
        this.viewMain.setContentPane(controllerCliente.viewClientes);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void jmiProveedoresActionPerformed(){
        this.viewMain.setContentPane(controllerProveedores.viewProveedores);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    public void jmiInicioSesionActionPerformed(){
        this.viewMain.setContentPane(controllerLogin.viewIniciarSesion);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    
    public void modificarSesion() {
        if (this.controllerLogin.modelLogin.inicio == true && this.controllerLogin.modelLogin.admin == true) {
            this.viewMain.jMenuItemUsuario.setEnabled(false);
            this.viewMain.jMenuItemProductos.setEnabled(false);
            this.viewMain.jmiVentas.setEnabled(false);
            this.viewMain.jmiCliente.setEnabled(false);
            this.viewMain.jmiCompras.setEnabled(false);
            this.viewMain.jmiProveedores.setEnabled(false);
        } else if (this.controllerLogin.modelLogin.inicio == true && this.controllerLogin.modelLogin.admin == false) {
            this.viewMain.jMenuItemUsuario.setEnabled(true);
            this.viewMain.jMenuItemProductos.setEnabled(false);
            this.viewMain.jmiVentas.setEnabled(false);
            this.viewMain.jmiCliente.setEnabled(false);
            this.viewMain.jmiCompras.setEnabled(false);
            this.viewMain.jmiProveedores.setEnabled(false);
        } else /*if (this.controllerLogin.modelLogin.inicio == false && this.controllerLogin.modelLogin.admin == false)*/{
            this.viewMain.jMenuItemUsuario.setEnabled(true);
            this.viewMain.jMenuItemProductos.setEnabled(true);
            this.viewMain.jmiCliente.setEnabled(true);
            this.viewMain.jmiCompras.setEnabled(true);
            this.viewMain.jmiProveedores.setEnabled(true);
            this.viewMain.jmiVentas.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewMain.jMenuItemProductos) {
            jmiProductosActionPerfomed();
        } else if (ae.getSource() == this.viewMain.jMenuItemIniciar) {
            jmiInicioSesionActionPerformed();
        } else if (ae.getSource() == this.viewMain.jMenuItemUsuario) {
            jmiUsuarioActionPerfomed();
        } else if (ae.getSource() == this.viewMain.jmiVentas) {
            jmiVentaActionPerfomed();
        } else if (ae.getSource() == this.viewMain.jmiCompras) {
            jmiCompraActionPerfomed();
        } else if (ae.getSource() == this.viewMain.jmiCliente) {
            jmiClienteActionPerfomed();
        } else if (ae.getSource() == this.viewMain.jmiProveedores){
            jmiProveedoresActionPerformed();
        } else if (ae.getSource() == this.controllerLogin.viewIniciarSesion.jbtnIngresar) {
            modificarSesion();
        }
    }
}
