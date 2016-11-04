/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
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
    static Statement s;
    ResultSet rs;
    ResultSetMetaData rsm;

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
            String total = "" + this.modelVentas.gTotal();
            String subtotal = "" + this.modelVentas.gSubtotal();
            String cant = this.viewVentas.jtfCantidad.getText();
            String tot = this.viewVentas.jtfTotPrecProd.getText();
            String id_venta = JOptionPane.showInputDialog("deme el numero de la venta", "");
            String sql = "insert into ventas(id_venta,fecha,id_cliente,subtotal,iva,total) values ('" + id_venta + "','" + fecha + "','" + cliente + "','" + subtotal + "','16','" + total + "');";

            String sql2 = "insert into detalle_venta(id_venta,id_producto,cantidad,total_producto) values (" + "'" + id_venta + "','" + producto + "','" + cant + "','" + tot + "');";
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

    public void Tabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            this.viewVentas.jTable1.setModel(modelo);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewVentas.jbAdd) {
            Guardar();
            Tabla();
        } else if (e.getSource() == this.viewVentas.jbNew) {
            Nueva();
            Tabla();
        } else if (e.getSource() == this.viewVentas.jbCancel) {
            Eliminar();
            Tabla();
        }
    }

}
