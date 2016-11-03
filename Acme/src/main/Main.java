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
        ModelProductos modelProductos = new ModelProductos();
        ViewProductos viewProductos = new ViewProductos();
        ControllerProductos controllerProductos = new ControllerProductos(modelProductos, viewProductos);

        ModelLogin modelLogin = new ModelLogin();
        ViewIniciarSecion viewIniciarSecion = new ViewIniciarSecion();
        ControllerLogin controllerLogin = new ControllerLogin(modelLogin, viewIniciarSecion, viewProductos);

        ModelUsuario modelUsuario = new ModelUsuario();
        ViewUsuario viewUsuario = new ViewUsuario();
        ControllerUsuario controllerUsuario = new ControllerUsuario(modelUsuario, viewUsuario);
        
        ModelClientes modelClientes = new ModelClientes();
        ViewClientes viewClientes = new ViewClientes();
        ControllerClientes controllerClientes = new ControllerClientes(modelClientes, viewClientes);
        
        ModelCompras modelCompras = new ModelCompras();
        viewCompras viewCompras = new viewCompras();
        ControllerCompras controllerCompras = new ControllerCompras(viewCompras, modelCompras);
        
        ModelVentas modelVentas = new ModelVentas();
        ViewVentas viewVentas = new ViewVentas();
        ControllerVentas controllerVentas = new ControllerVentas(viewVentas, modelVentas);

        JPanel view[] = new JPanel[11];
        view[0] = viewProductos;
        view[1] = viewIniciarSecion;
        view[2] = viewUsuario;
        view[3] = viewClientes;
        view[4] = viewCompras;
        view[5] = viewVentas;

        ViewMain viewMain = new ViewMain();
        ModelMain modelMain = new ModelMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, view);
        controllerMain.init_view();
    }
}
