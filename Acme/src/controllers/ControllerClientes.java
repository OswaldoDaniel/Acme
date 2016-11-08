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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.viewClientes.jbSearch.addActionListener(this);
        this.viewClientes.setVisible(true);
        Tabla();
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
    
public void Tabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            modelo.addColumn("id");
            modelo.addColumn("nombre");
            modelo.addColumn("APM");
            modelo.addColumn("APP");
            modelo.addColumn("telefono");
            modelo.addColumn("email");
            modelo.addColumn("rfc");
            modelo.addColumn("calle");
            modelo.addColumn("numero");
            modelo.addColumn("colonia");
            modelo.addColumn("ciudad");
            modelo.addColumn("estado");
            this.viewClientes.jtBusqueda.setModel(modelo);
            String datos[] = new String[12];
            s = cn.createStatement();
            rs = s.executeQuery("SELECT * FROM cliente");
            rsm = rs.getMetaData();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                modelo.addRow(datos);
            }
            this.viewClientes.jtBusqueda.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
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
        } else if (e.getSource() == this.viewClientes.jbSearch){
            Tabla();
        }
    }
}
