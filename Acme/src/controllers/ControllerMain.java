/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/*import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import models.*;
import views.*;*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.ModelMain;
import sax.DBConnection;
import views.ViewMain;
import views.*;
import models.*;
import views.ViewUsuario;
import views.ViewIniciarSesion;
import views.viewCompras;
import views.ViewClientes;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ControllerMain implements ActionListener {

    /*private ModelMain modelMain;
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
    }*/
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private ModelMain modelMain;
    private ViewMain viewMain;
    //JPanel views[];
    Object modules[];

    ControllerProductos controllerProducto;
    ControllerLogin controllerLogin;
    ControllerUsuario controllerUsuario;
    ControllerVentas controllerVenta;
    ControllerClientes controllerCliente;
    ControllerCompras controllerCompra;
    ControllerProveedores controllerProveedores;

    public ControllerMain(ModelMain modelMain, ViewMain viewMain, Object modules[] ){//JPanel views[] ) {
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
        //this.views=views;
        this.viewMain.jMenuItemProductos.addActionListener(this);
        this.viewMain.jMenuItemIniciar.addActionListener(this);
        this.viewMain.jMenuItemUsuario.addActionListener(this);
        this.controllerLogin.viewIniciarSesion.jbtnIngresar.addActionListener(this);
        this.viewMain.jmiVentas.addActionListener(this);
        this.viewMain.jmiCliente.addActionListener(this);
        this.viewMain.jmiCompras.addActionListener(this);
        this.viewMain.jmiProveedores.addActionListener(this);

    }

    public void init_view() {
        this.viewMain.setTitle("Acme");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
        //this.viewMain.jMenuItemProductos.setEnabled(false);
        //this.viewMain.jMenuItemUsuario.setEnabled(false);
        //this.viewMain.jmiVentas.setEnabled(false);
        //this.viewMain.jmiCliente.setEnabled(false);
        //this.viewMain.jmiCompras.setEnabled(false);
        //this.viewMain.jmiProveedores.setEnabled(false);

    }

    public void actionPerfomedProductos() {
        this.viewMain.setContentPane(controllerProducto.viewProductos);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedLogin() {
        this.viewMain.setContentPane(controllerLogin.viewIniciarSesion);

        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedUsuario() {
        this.viewMain.setContentPane(controllerUsuario.viewUsuario);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedVenta() {
        this.viewMain.setContentPane(controllerVenta.viewVentas);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedCompra() {
        this.viewMain.setContentPane(controllerCompra.viewCompras);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedCliente() {
        this.viewMain.setContentPane(controllerCliente.viewClientes);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void jmiProveedoresActionPerformed(){
        this.viewMain.setContentPane(controllerProveedores.viewProveedores);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    public void fecha() {
        //DateFormat df=  DateFormat.getDateInstance();
        //Date fechaActual=new Date();
        //this.controllerVenta.viewVenta.jtfFecha.setText(df.format(fechaActual));
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.controllerVenta.viewVentas.jtfFecha.setText(formatoFecha.format(fecha));
    }

    public void actionPerfomedIniCerr() {
        String us = this.controllerLogin.viewIniciarSesion.jtfUser.getText();
        String pass = new String(this.controllerLogin.viewIniciarSesion.jPassword.getPassword());
        String niv = "";
        String est1 = "";
        String sql = "SELECT * FROM admin WHERE usuario = '" + us + "'&& contrasena='" + pass + "'";
        conection.executeQuery(sql);
        while (conection.moveNext()) {
            niv = conection.getString("nivel");
            est1 = conection.getString("estado");
        }
        if (niv.equals("administrador") && est1.equals("Activo")) {
            JOptionPane.showMessageDialog(null, "Bienvenido  " + us);
            this.viewMain.jMenuItemUsuario.setEnabled(true);
            this.viewMain.jMenuItemProductos.setEnabled(true);
            this.viewMain.jmiVentas.setEnabled(true);
            this.viewMain.jmiCliente.setEnabled(true);
            this.viewMain.jmiCompras.setEnabled(true);
            this.viewMain.jmiProveedores.setEnabled(true);
            //this.controllerVenta.viewVentas.JLVendedor.setText(us);
            this.controllerLogin.viewIniciarSesion.setVisible(false);
            this.viewMain.setVisible(true);
            cerrarSesionText();
            fecha();
        } else if (niv.equals("vendedor") && est1.equals("Activo")) {
            JOptionPane.showMessageDialog(null, "Bienvenido  " + us);
            this.viewMain.jMenuItemUsuario.setEnabled(false);
            this.viewMain.jMenuItemProductos.setEnabled(false);
            this.viewMain.jmiCliente.setEnabled(false);
            this.viewMain.jmiCompras.setEnabled(false);
            this.viewMain.jmiProveedores.setEnabled(false);
            this.viewMain.jmiVentas.setEnabled(true);
            //this.controllerVenta.viewVentas.JLVendedor.setText(us);
            this.controllerLogin.viewIniciarSesion.setVisible(false);
            this.viewMain.setVisible(true);
            cerrarSesionText();
            fecha();
        } else if (niv.equals("administrador") && est1.equals("Desactivo")) {
            JOptionPane.showMessageDialog(null, "Esta persona esta Desactivado");
        } else if (niv.equals("vendedor") && est1.equals("Desactivo")) {
            JOptionPane.showMessageDialog(null, "Esta persona esta Desactivado");
        } else {
            JOptionPane.showMessageDialog(null, "Vuelve a intentar");
        }
    }

    public void cerrarSesionText() {
        this.viewMain.jMenuItemIniciar.setText("Cerrar Sesión");

    }

    public void actionPerfomedCerrarSesion() {
        this.viewMain.jMenuItemIniciar.setText("Iniciar Sesión");
        this.viewMain.jMenuItemUsuario.setEnabled(false);
        this.viewMain.jMenuItemProductos.setEnabled(false);
        this.viewMain.jmiVentas.setEnabled(false);
        this.viewMain.jmiCliente.setEnabled(false);
        this.viewMain.jmiCompras.setEnabled(false);
        this.viewMain.jmiProveedores.setEnabled(false);
        this.controllerLogin.viewIniciarSesion.setVisible(false);
        this.controllerLogin.viewIniciarSesion.setVisible(false);
        this.controllerProducto.viewProductos.setVisible(false);
        this.controllerUsuario.viewUsuario.setVisible(false);
        this.controllerVenta.viewVentas.setVisible(false);
        this.controllerCliente.viewClientes.setVisible(false);
        this.controllerCompra.viewCompras.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewMain.jMenuItemProductos) {
            actionPerfomedProductos();
        //} else if (ae.getSource() == this.viewMain.jMenuItemIniciar && this.viewMain.jMenuItemIniciar.getText().equals("Iniciar Sesión")) {
            //actionPerfomedLogin();
        //} else if (ae.getSource() == this.viewMain.jMenuItemIniciar && this.viewMain.jMenuItemIniciar.getText().equals("Cerrar Sesión")) {
            //actionPerfomedCerrarSesion();
        } else if (ae.getSource() == this.viewMain.jMenuItemUsuario) {
            actionPerfomedUsuario();
        //} else if (ae.getSource() == this.controllerLogin.viewIniciarSecion.jbtnIngresar) {
            //actionPerfomedIniCerr();
        } else if (ae.getSource() == this.viewMain.jmiVentas) {
            actionPerfomedVenta();
        } else if (ae.getSource() == this.viewMain.jmiCompras) {
            actionPerfomedCompra();
        } else if (ae.getSource() == this.viewMain.jmiCliente) {
            actionPerfomedCliente();
        } else if (ae.getSource() == this.viewMain.jmiProveedores){
            jmiProveedoresActionPerformed();
        }
    }
}
