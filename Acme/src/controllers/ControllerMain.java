/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import models.ModelMain;
import views.ViewMain;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerMain implements ActionListener {

    private ModelMain modelMain;
    private ViewMain viewMain;
    JPanel views[];

    public ControllerMain(ModelMain modelMain, ViewMain viewMain, JPanel views[]) {
        this.viewMain = viewMain;
        this.modelMain = modelMain;
        this.views = views;
        this.viewMain.jMenuItemProductos.addActionListener(this);
        this.viewMain.jMenuItemIniciar.addActionListener(this);
        this.viewMain.jMenuItemUsuario.addActionListener(this);

    }

    public void init_view() {
        this.viewMain.setTitle("Acme");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);

    }

    public void actionPerfomedProductos() {
        this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedLogin() {
        this.viewMain.setContentPane(views[1]);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }

    public void actionPerfomedUsuario() {
        this.viewMain.setContentPane(views[2]);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedCliente() {
        this.viewMain.setContentPane(views[3]);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedCompras() {
        this.viewMain.setContentPane(views[4]);
        this.viewMain.revalidate();
        this.viewMain.repaint();

    }
    
    public void actionPerfomedVentas() {
        this.viewMain.setContentPane(views[5]);
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
