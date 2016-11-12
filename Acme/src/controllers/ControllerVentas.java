/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Connection cn;
    PreparedStatement ps;
    Statement s;
    ResultSet rs;
    ResultSetMetaData rsm;
    

    public ControllerVentas(ViewVentas viewVentas, ModelVentas modelVentas) {
        this.viewVentas = viewVentas;
        this.modelVentas = modelVentas;
        this.viewVentas.jbAdd.addActionListener(this);
        this.viewVentas.jbCancel.addActionListener(this);
        this.viewVentas.jbNew.addActionListener(this);
        this.viewVentas.jbBuscarProductos.addActionListener(this);
        this.viewVentas.jbBuscarProveedores.addActionListener(this);
        this.viewVentas.jbMostar.addActionListener(this);
    }

    public void Guardar() {
        try {
            String producto = this.viewVentas.jtfProducto.getText();
            String fecha = this.viewVentas.jtfFecha.getText();
            String cliente = this.viewVentas.jtfCliente.getText();
            this.modelVentas.setCantidad(Integer.parseInt(this.viewVentas.jtfCantidad.getText()));
            this.modelVentas.setTotalPrecProd(Integer.parseInt(this.viewVentas.jtfTotPrecProd.getText()));
            String precio = this.viewVentas.jtfPrecio.getText();
            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta", "");
            String sql = "insert into ventas(id_venta,fecha,id_cliente,subtotal,iva,total) values ('" + id_venta + "','" + fecha + "','" + cliente + "','" + this.modelVentas.gSubtotal() + "','16','" + this.modelVentas.gTotal() + "');";
            String sql2 = "insert into detalle_venta(id_venta,id_producto,cantidad,total_producto,precio) values (" + "'" + id_venta + "','" + producto + "','" + this.modelVentas.getCantidad() + "','" + this.modelVentas.getTotalPrecProd() + "','"+precio+"');";
            conection.executeUpdate(sql);
            conection.executeUpdate(sql2);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewVentas, "No hay objeto seleccionado");
        }
    }

    public void Tabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            modelo.addColumn("venta");
            modelo.addColumn("fecha");
            modelo.addColumn("cliente");
            modelo.addColumn("producto");
            modelo.addColumn("cantidad");
            this.viewVentas.jtVentas.setModel(modelo);
            String datos[] = new String[5];
            s = cn.createStatement();
            rs = s.executeQuery("SELECT ventas.id_venta,ventas.fecha,cliente.nombre,productos.producto,detalle_venta.cantidad FROM cliente, ventas, detalle_venta, productos WHERE cliente.id_cliente = ventas.id_cliente AND ventas.id_venta = detalle_venta.id_venta AND productos.id_producto = detalle_venta.id_producto");
            rsm = rs.getMetaData();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            this.viewVentas.jtVentas.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public void Eliminar() {
        try {

            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta", "");
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

    public void tablaProductos() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Descripcion");
            modelo.addColumn("P_compra");
            modelo.addColumn("P_venta");
            modelo.addColumn("Existencias");
            this.viewVentas.jtBuscaProductos.setModel(modelo);
            String datos[] = new String[6];
            String sql = "";
            if (this.viewVentas.jcbSpecificBrand.isSelected()) {
                String producto = JOptionPane.showInputDialog("Deme el nombre del producto", "");
                sql = "Select * from productos where producto ='" + producto + "'";
            } else {
                sql = "Select * from productos";
            }
            s = cn.createStatement();
            rs = s.executeQuery(sql);
            rsm = rs.getMetaData();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.viewVentas.jtBuscaProductos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void TablaClientes() {
        try {
            cn=DriverManager.getConnection("jdbc:mysql://localhost/acme","root",""); 
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
            this.viewVentas.jtBuscaClientes.setModel(modelo);
            String datos[] = new String[12];
            String sql = "";
            if (this.viewVentas.jcbSpecificCostumer.isSelected()) {
                String producto = JOptionPane.showInputDialog("Deme el nombre del cliente", "");
                sql = "Select * from cliente where nombre ='" + producto + "'";
            } else {
                sql = "Select * from cliente";
            }
            s = cn.createStatement();
            rs = s.executeQuery(sql);
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
            this.viewVentas.jtBuscaClientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewVentas.jbAdd) {
            Guardar();
        } else if (e.getSource() == this.viewVentas.jbNew) {
            Nueva();
        } else if (e.getSource() == this.viewVentas.jbCancel) {
            Eliminar();
        } else if (e.getSource() == this.viewVentas.jbMostar){
            Tabla();
        } else if (e.getSource() == this.viewVentas.jbBuscarProveedores){
            TablaClientes();
        } else if (e.getSource() == this.viewVentas.jbBuscarProductos){
            tablaProductos();
        }
    }

}
