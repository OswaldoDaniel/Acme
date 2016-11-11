/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelLogin;
import views.ViewIniciarSesion;
import views.ViewProductos;
import views.ViewMain;
import sax.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerLogin implements ActionListener {

    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    ModelLogin modelLogin;
    ViewIniciarSesion viewIniciarSesion;
    ViewProductos viewProductos;
    ViewMain viewMain;
    private Statement st;
    private ResultSet rs;
    // private ResultSet rs;

    public ControllerLogin(ModelLogin modelLogin, ViewIniciarSesion viewIniciarSesion, ViewProductos viewProductos) {
        this.modelLogin = modelLogin;
        this.viewIniciarSesion = viewIniciarSesion;
        this.viewProductos = viewProductos;
        this.viewMain = viewMain;
        this.viewIniciarSesion.jbtnIngresar.addActionListener(this);
        this.viewIniciarSesion.setVisible(true);
        init_view();
    }

    public void init_view() {
        modelLogin.initValues();
        showValues();
    }

    private void showValues() {
        this.viewIniciarSesion.jtfUser.setText("" + modelLogin.getUs());
        this.viewIniciarSesion.jPassword.setText("" + modelLogin.getPass());
    }

    public void Entrar(String us, String pass) {
        us = this.viewIniciarSesion.jtfUser.getText();
        pass = new String(this.viewIniciarSesion.jPassword.getPassword());
        String nivel = "";
        String sql = "SELECT * FROM admin WHERE usuario = '" + us + "'&& contrasena='" + pass + "'";
        conection.executeQuery(sql);
        while (conection.moveNext()) {
            nivel = conection.getString("nivel");
        }
        if (nivel.equals("administrador")) {
            // this.viewIniciarSecion.setVisible(false);
            JOptionPane.showMessageDialog(null, "Bienvenido  " + us);
            /* JFVoto1 vot= new JFVoto1(0,us);
            vot.setVisible(true);
            vot.pack();
            this.setVisible(false);*/
            // JFVoto1.jtfUsuario2.setText(us);
            //  this.viewMain.jMenuItemUsuario.setEnabled(false);
        } else if (nivel.equals("vendedor")) {
            // this.viewIniciarSecion.setVisible(false);
            JOptionPane.showMessageDialog(null, "Bienvenido  " + us);
            /*JFVoto1 vot = new JFVoto1(1,us);
            vot.setVisible(true);
            vot.pack();
            this.setVisible(false);*/
            // JFVoto1.jtfUsuario2.setText(us);
        } else {
            JOptionPane.showMessageDialog(null, "Vuelve a intentar");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewIniciarSesion.jbtnIngresar) {
            String us = this.viewIniciarSesion.jtfUser.getText();
            String pass = new String(this.viewIniciarSesion.jPassword.getPassword());
            Entrar(us, pass);
        }
    }

}
