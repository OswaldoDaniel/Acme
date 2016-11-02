/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;
import views.ViewCompras;
import models.ModelCompras;
/**
 *
 * @author usuario
 */
public class ControllerCompras implements ActionListener{
    ViewCompras viewCompras;
    ModelCompras modelCompras;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    PreparedStatement ps;
    ResultSetMetaData rsm;
    DefaultTableModel dtm;
    
    public ControllerCompras(ViewCompras viewCompras,ModelCompras modelCompras) {
        this.viewCompras = viewCompras;
        this.viewCompras = viewCompras;
        this.viewCompras.jbAdd.addActionListener(this);
        this.viewCompras.jbCancel.addActionListener(this);
        this.viewCompras.jbNew.addActionListener(this);
    }
    
    public void Guardar() {
        try {
            String producto = this.viewCompras.jtfProducto.getText();
            String fecha = this.viewCompras.jtfFecha.getText();
            String proveedor = this.viewCompras.jtfProveedor.getText();
            this.modelCompras.setCantidad(Integer.parseInt(this.viewCompras.jtfCantidad.getText()));
            this.modelCompras.setTotalPrecProd(Integer.parseInt(this.viewCompras.jtfTotPrecProd.getText()));
            String total = "" + this.modelCompras.getTotal();
            String subtotal = "" + this.modelCompras.getSubtotal();
            String cant =this.viewCompras.jtfCantidad.getText();
            String tot = this.viewCompras.jtfTotPrecProd.getText();
            String sql = "insert into compras(fecha,id_proveedor,subtotal,num,total) values (" + "'" + fecha + "','" + proveedor + "','" +subtotal + "','" +  total+ "');";
            String sql2 = "insert into detalle_compra(id_compra,id_producto,cantidad,total_precio_producto) values ("+"'"+"','"+producto+"','"+cant+"','"+tot+"');";
            //System.out.println("Nombre " + producto); //Esto para que?
            //System.out.println("SQL " + sql); //Esto para que?
            conection.executeUpdate(sql);
            conection.executeUpdate(sql2);
            conection.executeQuery("Select * from ventas");
            Primero();
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewCompras, "No hay objeto seleccionado");
        }
    }

    public void Primero() {
        modelCompras.moveFirst();
    }

    public void Siguiente() {
        modelCompras.moveNext();
    }

    public void Anterior() {
        modelCompras.movePrevious();
    }
    
    public void Eliminar() {
        try {
            
            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta","");
            conection.executeUpdate("delete from detalle_compra where id_venta=" + id_venta);
            conection.executeUpdate("delete from compras where id_venta=" + id_venta);
            conection.executeQuery("Select * from compras order by id_producto");

            this.viewCompras.jtfCantidad.setText("");
            this.viewCompras.jtfProveedor.setText("");
            this.viewCompras.jtfFecha.setText("");
            this.viewCompras.jtfProducto.setText("");
            this.viewCompras.jtfTotPrecProd.setText("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }
    
    public void Nueva() {
        try {
            Guardar();
            this.viewCompras.jtfCantidad.setText("");
            this.viewCompras.jtfProveedor.setText("");
            this.viewCompras.jtfFecha.setText("");
            this.viewCompras.jtfProducto.setText("");
            this.viewCompras.jtfTotPrecProd.setText("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.viewCompras.jbAdd){
            Guardar();
        } else if(e.getSource() == this.viewCompras.jbCancel){
            Eliminar();
        } else if(e.getSource() == this.viewCompras.jbNew){
            Nueva();
        }
    }
}