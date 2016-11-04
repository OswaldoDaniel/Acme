/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import sax.DBConnection;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement; 
//import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ModelClientes;
import views.ViewClientes;
//import SRL.TableConection;
/**
 *
 * @author usuario
 */
public class ControllerClientes implements ActionListener{
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    Connection cn;
    PreparedStatement ps;
    static Statement s;
    ResultSet rs;
    ResultSetMetaData rsm;
    
    ModelClientes modelClientes;
    ViewClientes viewClientes;
    
    public ControllerClientes(ModelClientes modelClientes, ViewClientes viewClientes){
        this.modelClientes = modelClientes;
        this.viewClientes = viewClientes;
        this.viewClientes.jbAdd.addActionListener(this);
        this.viewClientes.jbDelete.addActionListener(this);
        this.viewClientes.jbEdit.addActionListener(this);
        this.viewClientes.jbLast.addActionListener(this);
        this.viewClientes.jbFirst.addActionListener(this);
        this.viewClientes.jbNext.addActionListener(this);
        this.viewClientes.jbPrevious.addActionListener(this);
        this.viewClientes.jbSearch.addActionListener(this);
        init_view();
    }
    
    public void guardar(){
        this.modelClientes.setApMat(this.viewClientes.jtfMatLastName.getText());
        this.modelClientes.setApPat(this.viewClientes.jtfPatLastName.getText());
        this.modelClientes.setCalle(this.viewClientes.jtfCalle.getText());
        this.modelClientes.setCiudad(this.viewClientes.jtfCity.getText());
        this.modelClientes.setColonia(this.viewClientes.jtfColony.getText());
        this.modelClientes.setEmail(this.viewClientes.jtfEmail.getText());
        this.modelClientes.setEstado(this.viewClientes.jtfState.getText());
        this.modelClientes.setNombre(this.viewClientes.jtfName.getText());
        this.modelClientes.setNumero(Integer.parseInt(this.viewClientes.jtfNumber.getText()));
        this.modelClientes.setRfc(this.viewClientes.jtfRFC.getText());
        this.modelClientes.setTelefono(this.viewClientes.jtfTelephone.getText());        
    }
    
    public void init_view() {
        this.viewClientes.setVisible(true);
        this.modelClientes.initValues();
        Tabla();
    }
    
    public void Tabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            this.viewClientes.jtBusqueda.setModel(modelo);
            String url = "jdbc:mysql://localhost:3306/acme?zeroDateTimeBehavior=convertToNull";
            cn = DriverManager.getConnection(url, "root", "");
            s = cn.createStatement();
            rs = s.executeQuery("select * from ventas");
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void Primero() {
        modelClientes.moveFirst();
        this.modelClientes.initValues();
    }

    public void Siguiente() {
       modelClientes.moveNext();
       this.modelClientes.initValues();
    }

    public void Anterior() {
        modelClientes.movePrevious();
        this.modelClientes.initValues();
    }
    public void Ultimo() {
        modelClientes.moveLast();
        this.modelClientes.initValues();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.viewClientes.jbAdd){
            guardar();
            this.modelClientes.guardar();
        } else if (e.getSource() ==this.viewClientes.jbDelete){
            guardar();
            this.modelClientes.borrar();
        } else if(e.getSource() == this.viewClientes.jbEdit){
            guardar();
            this.modelClientes.editar();
        } else if(e.getSource() == this.viewClientes.jbFirst){
            Primero();
        } else if(e.getSource() == this.viewClientes.jbLast){
            Ultimo();
        } else if(e.getSource() == this.viewClientes.jbNext){
            Siguiente();
        } else if(e.getSource() == this.viewClientes.jbPrevious){
            Anterior();
        } else {
            
        }
    }
}
