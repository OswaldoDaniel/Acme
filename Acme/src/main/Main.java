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
 * @author usuario
 */
public class Main {
    /*public static void main(String[] Rose) {
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

        ViewMain viewMain = new ViewMain();
        ModelMain modelMain = new ModelMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, modelClientes, modelCompras, modelProductos, modelLogin, modelVentas, modelUsuario, viewMain, viewClientes, viewCompras, viewProductos, viewIniciarSecion, viewVentas, viewUsuario);
        controllerMain.init_view();
    }*/
    public static void main(String[] ODST) {
        Object modules[] = new Object[7];
        ViewProductos viewProductos = new ViewProductos();
        ModelProductos modelProductos = new ModelProductos();
        ControllerProductos controllerProducto = new ControllerProductos (modelProductos, viewProductos);

        ViewIniciarSecion viewIniciarSecion = new ViewIniciarSecion();
        ModelLogin modelLogin = new ModelLogin();
        ControllerLogin controllerLogin = new ControllerLogin(modelLogin, viewIniciarSecion, viewProductos);

        ModelUsuario modelUsuario = new ModelUsuario();
        ViewUsuario viewUsuario = new ViewUsuario();
        ControllerUsuario controllerUsuario = new ControllerUsuario(modelUsuario, viewUsuario);

        ModelVentas modelVentas = new ModelVentas();
        ViewVentas viewVenta = new ViewVentas();
        ControllerVentas controllerVenta = new ControllerVentas(viewVenta, modelVentas);

        ModelClientes modelClientes = new ModelClientes();
        ViewClientes viewClientes = new ViewClientes();
        ControllerClientes controllerClientes = new ControllerClientes(modelClientes, viewClientes);
        
        ModelCompras modelCompras = new ModelCompras();
        viewCompras viewCompras = new viewCompras();
        ControllerCompras controllerCompras = new ControllerCompras(viewCompras, modelCompras);
        
        ModelProveedores modelProveedores = new ModelProveedores();
        ViewProveedores viewProveedores = new ViewProveedores();
        ControllerProveedores controllerProveedores = new ControllerProveedores(modelProveedores, viewProveedores);
        
        //JPanel view[]=new JPanel[3];
        //view[0]=viewProductos;
        //view[1]=viewIniciarSecion;
        //view[2]=viewUsuario;
        modules[0] = controllerProducto;
        modules[1] = controllerUsuario;
        modules[2] = controllerLogin;
        modules[3] = controllerVenta;
        modules[4] = controllerClientes;
        modules[5] = controllerCompras;
        modules[6] = controllerProveedores;
        
        ViewMain viewMain = new ViewMain();
        ModelMain modelMain = new ModelMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, modules);
        controllerMain.init_view();
    }
}
