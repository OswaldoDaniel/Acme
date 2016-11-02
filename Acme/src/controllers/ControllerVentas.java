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
import views.ViewVentas;
import models.ModelVentas;
import sax.DBConnection;

/**
 *
 * @author usuario
 */
public class ControllerVentas implements ActionListener {

    ViewVentas viewVentas;
    ModelVentas modelVentas;

    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    PreparedStatement ps;

    ResultSetMetaData rsm;
    DefaultTableModel dtm;

    public ControllerVentas(ViewVentas viewVentas, ModelVentas modelVentas) {
        this.viewVentas = viewVentas;
        this.modelVentas = modelVentas;
        this.viewVentas.jbAdd.addActionListener(this);
        this.viewVentas.jbCancel.addActionListener(this);
        this.viewVentas.jbNew.addActionListener(this);
    }

    public void Guardar() {
        try {
            String producto = this.viewVentas.jtfProducto.getText();
            String fecha = this.viewVentas.jtfFecha.getText();
            String cliente = this.viewVentas.jtfCliente.getText();
            this.modelVentas.setCantidad(Integer.parseInt(this.viewVentas.jtfCantidad.getText()));
            this.modelVentas.setTotalPrecProd(Integer.parseInt(this.viewVentas.jtfTotPrecProd.getText()));
            String total = "" + this.modelVentas.getTotal();
            String subtotal = "" + this.modelVentas.getSubtotal();
            String cant =this.viewVentas.jtfCantidad.getText();
            String tot = this.viewVentas.jtfTotPrecProd.getText();
            String sql = "insert into ventas(fecha,id_cliente,subtotal,num,total) values (" + "'" + fecha + "','" + cliente + "','" +subtotal + "','" +  total+ "');";
            String sql2 = "insert into detalle_venta(id_venta,id_producto,cantidad,total_precio_producto) values ("+"'"+"','"+producto+"','"+cant+"','"+tot+"');";
            //System.out.println("Nombre " + producto); //Esto para que?
            //System.out.println("SQL " + sql); //Esto para que?
            conection.executeUpdate(sql);
            conection.executeUpdate(sql2);
            conection.executeQuery("Select * from ventas");
            Primero();
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewVentas, "No hay objeto seleccionado");
        }
    }

    public void Primero() {
        modelVentas.moveFirst();
    }

    public void Siguiente() {
        modelVentas.moveNext();
    }

    public void Anterior() {
        modelVentas.movePrevious();
    }
    
    public void Eliminar() {
        try {
            
            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta","");
            conection.executeUpdate("delete from detalle_venta where id_venta=" + id_venta);
            conection.executeUpdate("delete from ventas where id_venta=" + id_venta);
            conection.executeQuery("Select * from productos order by id_producto");

            this.viewVentas.jtfCantidad.setText("");
            this.viewVentas.jtfCliente.setText("");
            this.viewVentas.jtfFecha.setText("");
            this.viewVentas.jtfProducto.setText("");
            this.viewVentas.jtfTotPrecProd.setText("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }
    
    public void Nueva() {
        try {
            Guardar();
            this.viewVentas.jtfCantidad.setText("");
            this.viewVentas.jtfCliente.setText("");
            this.viewVentas.jtfFecha.setText("");
            this.viewVentas.jtfProducto.setText("");
            this.viewVentas.jtfTotPrecProd.setText("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.viewVentas.jbAdd){
            Guardar();
        } else if (e.getSource() == this.viewVentas.jbNew){
            Nueva();
        } else if (e.getSource() == this.viewVentas.jbCancel){
            Eliminar();
        }
    }

}
