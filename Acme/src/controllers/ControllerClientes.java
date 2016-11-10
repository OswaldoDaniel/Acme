/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import sax.DBConnection;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement; 
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
    Statement s;
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
        
        conectar();
    }
    public void conectar(){
        try{ 
            cn=DriverManager.getConnection("jdbc:mysql://localhost/acme","root","");                     
            s=cn.createStatement();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null,"Ocurrio un problema al conectar con la base de datos"); 
        } 
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
            //conectar();
            rs = s.executeQuery("SELECT * FROM cliente;");
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
    
    public void añadir(){
        try {
            guardar();
            conectar();
            s.executeUpdate(this.modelClientes.guardarSql());
            JOptionPane.showMessageDialog(null, "Cliente Añadido Exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
        }
    }
    
    public void editar(){
        try {
            guardar();
            conectar();
            s.executeUpdate(this.modelClientes.editarSql());
            JOptionPane.showMessageDialog(null, "Cliente Editado Exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
        }
    }
    
    public void borrar(){
        try {
            guardar();
            conectar();
            s.executeUpdate(this.modelClientes.borrarSql());
            JOptionPane.showMessageDialog(null, "Cliente Borrado Exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "HA HABIDO UN ERROR");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.viewClientes.jbAdd){
            añadir();
        } else if (e.getSource() ==this.viewClientes.jbDelete){
            borrar();
        } else if(e.getSource() == this.viewClientes.jbEdit){
            editar();
        } else if (e.getSource() == this.viewClientes.jbSearch){
            Tabla();
        }
    }
}
