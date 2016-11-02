/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import models.*;
import views.*;
import controllers.*;
import javax.swing.JPanel;

/**
 *
 * @author RoseLandjlord
 */
public class Main {

    public static void main(String[] Rose) {
        ViewProductos viewProductos = new ViewProductos();
        ModelProductos modelProductos = new ModelProductos();
        ControllerProductos controllerProductos = new ControllerProductos(modelProductos, viewProductos);

        ViewIniciarSecion viewIniciarSecion = new ViewIniciarSecion();
        ModelLogin modelLogin = new ModelLogin();
        ControllerLogin controllerLogin = new ControllerLogin(modelLogin, viewIniciarSecion, viewProductos);

        ModelUsuario modelUsuario = new ModelUsuario();
        ViewUsuario viewUsuario = new ViewUsuario();
        ControllerUsuario controllerUsuario = new ControllerUsuario(modelUsuario, viewUsuario);

        JPanel view[] = new JPanel[3];
        view[0] = viewProductos;
        view[1] = viewIniciarSecion;
        view[2] = viewUsuario;

        ViewMain viewMain = new ViewMain();
        ModelMain modelMain = new ModelMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, view);
        controllerMain.init_view();
    }
}
