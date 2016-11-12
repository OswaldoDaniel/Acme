/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ODST.DatosNumericos;
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
public class ControllerCompras implements ActionListener {

    viewCompras viewCompras;
    ModelCompras modelCompras;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    Connection cn;
    PreparedStatement ps;
    Statement s;
    ResultSet rs;
    ResultSetMetaData rsm;
    DatosNumericos dats = new DatosNumericos();

    public ControllerCompras(viewCompras viewCompras, ModelCompras modelCompras) {
        this.viewCompras = viewCompras;
        this.viewCompras = viewCompras;
        this.viewCompras.jbAdd.addActionListener(this);
        this.viewCompras.jbCancel.addActionListener(this);
        this.viewCompras.jbNew.addActionListener(this);
        this.viewCompras.jbBuscarProveedores.addActionListener(this);
        this.viewCompras.jbBuscarProductos.addActionListener(this);
        this.viewCompras.jbMostrar.addActionListener(this);
        this.viewCompras.setVisible(true);
    }
    public void Guardar() {
        String producto = this.viewCompras.jtfProducto.getText();
        String fecha = this.viewCompras.jtfFecha.getText();
        String cliente = this.viewCompras.jtfProveedor.getText();
        int cantidad = dats.stringToInt(this.viewCompras.jtfCantidad.getText());
        float precio = dats.stringToFloat(this.viewCompras.jtfPrecio.getText());
        int iva = 16;
        float subtotal = cantidad * precio;
        float tot = subtotal;
        this.viewCompras.jtfTotPrecProd.setText(""+tot);
        float total = (subtotal*(iva/100)) + subtotal;
        String id_venta = JOptionPane.showInputDialog("deme el numero de la venta", "");
        String sql = "insert into compras(id_compra,fecha,id_proveedor,subtotal,iva,total) values ('" + id_venta + "','" + fecha + "','" + cliente + "','" + subtotal + "','"+iva+"','" + total + "');";
        String sql2 = "insert into detalle_compra(id_compra,id_producto,cantidad,total_producto,precio) values (" + "'" + id_venta + "','" + producto + "','" + cantidad + "','" + tot + "','"+precio+"');";
        conection.executeUpdate(sql);
        conection.executeUpdate(sql2);
    }
    public void datos() {
        this.modelCompras.setCantidad(dats.stringToInt(this.viewCompras.jtfCantidad.getText()));
        this.modelCompras.setFecha(this.viewCompras.jtfFecha.getText());
        this.modelCompras.setNumProducto(dats.stringToInt(this.viewCompras.jtfProducto.getText()));
        this.modelCompras.setNumproveedor(dats.stringToInt(this.viewCompras.jtfProveedor.getText()));
        this.modelCompras.setPrecio(dats.stringToInt(this.viewCompras.jtfPrecio.getText()));
        this.modelCompras.setTotalPrecProd(dats.stringToInt(this.viewCompras.jtfTotPrecProd.getText()));
    }

    public void Eliminar() {
        try {
            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta", "");
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
            datos();
            this.viewCompras.jtfCantidad.setText("");
            this.viewCompras.jtfProveedor.setText("");
            this.viewCompras.jtfFecha.setText("");
            this.viewCompras.jtfProducto.setText("");
            this.viewCompras.jtfTotPrecProd.setText("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }

    public void tablaProveedores() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            model.addColumn("id");
            model.addColumn("Nombre");
            model.addColumn("RFC");
            model.addColumn("Calle");
            model.addColumn("No");
            model.addColumn("Colonia");
            model.addColumn("Ciudad");
            model.addColumn("Estado");
            model.addColumn("Contacto");
            model.addColumn("Telefono");
            model.addColumn("Email");
            this.viewCompras.jtBuscaProveedores.setModel(model);
            String datos[] = new String[11];
            String sql = "";
            if (this.viewCompras.jcbSpecificProduct.isSelected()) {
                String producto = JOptionPane.showInputDialog("Deme el nombre del proveedor", "");
                sql = "Select * from proveedores where nombre ='" + producto + "'";
            } else {
                sql = "Select * from proveedores";
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
                model.addRow(datos);
            }
            this.viewCompras.jtBuscaProveedores.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCompras.class.getName()).log(Level.SEVERE, null, ex);
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
            this.viewCompras.jtBuscaProductos.setModel(modelo);
            String datos[] = new String[6];
            String sql = "";
            if (this.viewCompras.jcbSpecificBrand.isSelected()) {
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
            this.viewCompras.jtBuscaProductos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TablaCompras() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            modelo.addColumn("compra");
            modelo.addColumn("fecha");
            modelo.addColumn("proveedor");
            modelo.addColumn("producto");
            modelo.addColumn("cantidad");
            this.viewCompras.jtMostrarCompra.setModel(modelo);
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
            this.viewCompras.jtMostrarCompra.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewCompras.jbAdd) {
            Guardar();
            TablaCompras();
        } else if (e.getSource() == this.viewCompras.jbCancel) {
            Eliminar();
            TablaCompras();
        } else if (e.getSource() == this.viewCompras.jbNew) {
            Nueva();
        } else if (e.getSource() == this.viewCompras.jbBuscarProveedores) {
            tablaProveedores();
        } else if (e.getSource() == this.viewCompras.jbBuscarProductos) {
            tablaProductos();
        }
    }
}
