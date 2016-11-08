/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.ControllerVentas.s;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;
import views.viewCompras;
import models.ModelCompras;
/**
 *
 * @author usuario
 */
public class ControllerCompras implements ActionListener{
    viewCompras viewCompras;
    ModelCompras modelCompras;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    Connection cn;
    PreparedStatement ps;
    static Statement s;
    ResultSet rs;
    ResultSetMetaData rsm;
    
    
    
    public ControllerCompras(viewCompras viewCompras,ModelCompras modelCompras) {
        this.viewCompras = viewCompras;
        this.viewCompras = viewCompras;
        this.viewCompras.jbAdd.addActionListener(this);
        this.viewCompras.jbCancel.addActionListener(this);
        this.viewCompras.jbNew.addActionListener(this);
        this.viewCompras.setVisible(true);
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
            String precio = this.viewCompras.jtfPrecio.getText();
            String sql = "insert into compras(fecha,id_proveedor,subtotal,num,total) values (" + "'" + fecha + "','" + proveedor + "','" +subtotal + "','" +  total+ "','"+precio+"');";
            String sql2 = "insert into detalle_compra(id_compra,id_producto,cantidad,total_precio_producto) values ("+"'"+"','"+producto+"','"+cant+"','"+tot+"');";
            
            conection.executeUpdate(sql);
            conection.executeUpdate(sql2);
            conection.executeQuery("Select * from compras");
            Primero();
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewCompras, "No hay objeto seleccionado");
        }
    }
    public void Tabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            modelo.addColumn("compra");
            modelo.addColumn("fecha");
            modelo.addColumn("proveedor");
            modelo.addColumn("producto");
            modelo.addColumn("cantidad");
            this.viewCompras.jtBuscaClientes.setModel(modelo);
            String datos[] = new String[5];
            s = cn.createStatement();
            rs = s.executeQuery("SELECT compras.id_compra,compras.fecha,proveedores.nombre,productos.producto,detalle_compra.cantidad FROM proveedores, compras, detalle_compra, productos WHERE proveedores.id_proveedor = compras.id_proveedor AND compras.id_compra = detalle_compra.id_compra AND productos.id_producto = detalle_compra.id_producto");
            rsm = rs.getMetaData();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            this.viewCompras.jtBuscaClientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentas.class.getName()).log(Level.SEVERE, null, ex);
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
