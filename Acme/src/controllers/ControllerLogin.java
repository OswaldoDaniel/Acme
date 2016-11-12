/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelLogin;
import views.ViewIniciarSesion;

/**
 *
 * @author L.A.M.M#13
 */
public class ControllerLogin implements ActionListener {

    ModelLogin modelLogin;
    ViewIniciarSesion viewIniciarSesion;
    public String estado = "";
    
    public ControllerLogin(ModelLogin modelLogin, ViewIniciarSesion viewIniciarSesion) {
        this.modelLogin = modelLogin;
        this.viewIniciarSesion = viewIniciarSesion;
        this.viewIniciarSesion.jbtnIngresar.addActionListener(this);
        this.viewIniciarSesion.setVisible(true);
    }

    public void conectar(){
        String us = this.viewIniciarSesion.jtfUser.getText();
        String pass = new String(this.viewIniciarSesion.jPassword.getPassword());
        String opcion = this.viewIniciarSesion.jbtnIngresar.getText();
        this.modelLogin.Entrar(us, pass, opcion);
        if(this.modelLogin.inicio == true && this.modelLogin.admin == true){
            this.viewIniciarSesion.jbtnIngresar.setText("Salir");
            estado = "Admin iniciado";
        } else if (this.modelLogin.inicio == true && this.modelLogin.admin == false)  {
            this.viewIniciarSesion.jbtnIngresar.setText("Salir");
            estado = "Vendedor Iniciado";
        } else if (this.modelLogin.inicio == false){
            this.viewIniciarSesion.jbtnIngresar.setText("Iniciar");
            estado = "No iniciado";
        } else {
            estado = "No iniciado";
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewIniciarSesion.jbtnIngresar) {
            conectar();
        }
    }
}
