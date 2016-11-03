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
//import java.sql.Connection; 
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
//import java.sql.SQLException;
//import java.sql.Statement; 
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
    PreparedStatement ps;

    ResultSetMetaData rsm;
    DefaultTableModel dtm;

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
    }
    public void Primero() {
        modelClientes.moveFirst();
    }

    public void Siguiente() {
       modelClientes.moveNext();
    }

    public void Anterior() {
        modelClientes.movePrevious();
    }
    public void Ultimo() {
        modelClientes.moveLast();
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
