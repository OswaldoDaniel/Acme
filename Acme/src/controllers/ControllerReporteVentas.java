/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import sax.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ModelReporteVentas;
import views.ViewReporteVentas;

/**
 *
 * @author usuario
 */
public class ControllerReporteVentas implements ActionListener {

    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    ModelReporteVentas modRepVentas;
    ViewReporteVentas viewRepVentas;
    PreparedStatement ps;
    ResultSetMetaData rsm;
    DefaultTableModel dtm;

    public ControllerReporteVentas(ModelReporteVentas modRepVentas, ViewReporteVentas viewRepVentas) {
        this.modRepVentas = modRepVentas;
        this.viewRepVentas = viewRepVentas;
        this.viewRepVentas.jbBuscar.addActionListener(this);
        this.viewRepVentas.jbPrimero.addActionListener(this);
        this.viewRepVentas.jbUltimo.addActionListener(this);
        this.viewRepVentas.jbAnterior.addActionListener(this);
        this.viewRepVentas.jbSiguiente.addActionListener(this);
    }

    public void init_view() {
        this.viewRepVentas.setVisible(true);
        modRepVentas.initValues();
        
    }
    
    public void Primero() {
        modRepVentas.moveFirst();
    }

    public void Siguiente() {
        modRepVentas.moveNext();
    }

    public void Anterior() {
        modRepVentas.movePrevious();
    }
    
    public void Ultimo() {
        modRepVentas.moveLast();
    }
    
    public void Buscar(){
        this.modRepVentas.setCantidad(Integer.parseInt(this.viewRepVentas.jtfCantidad.getText()));
        this.modRepVentas.setFecha(this.viewRepVentas.jtfFecha.getText());
        this.modRepVentas.setId_venta(Integer.parseInt(this.viewRepVentas.jtfVenta.getText()));
        //this.modRepVentas.setNombre("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
