/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.JOptionPane;
import sax.DBConnection;

/**
 *
 * @author usuario
 */
public class ModelCompras {

    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private int numProveedor;
    private int numProducto;
    private int cantidad;
    private int totalPrecProd;
    private String fecha;
    private int precio;
    private String id;
    public String sql;
    public String sql2;
    public int iva = 16;
    public double total;
    public double subtotal;

    /**
     * @return the numProveedor
     */
    public int getNumproveedor() {
        return numProveedor;
    }

    /**
     * @param numProveedor the numProveedore to set
     */
    public void setNumproveedor(int numProveedor) {
        this.numProveedor = numProveedor;
    }

    /**
     * @return the numProducto
     */
    public int getNumProducto() {
        return numProducto;
    }

    /**
     * @param numProducto the numProducto to set
     */
    public void setNumProducto(int numProducto) {
        this.numProducto = numProducto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the totalPrecProd
     */
    public int getTotalPrecProd() {
        return totalPrecProd;
    }

    /**
     * @param totalPrecProd the TotalPrecProd to set
     */
    public void setTotalPrecProd(int totalPrecProd) {
        this.totalPrecProd = totalPrecProd;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public void moveNext() {
        conection.moveNext();
        setValues();
    }

    public void movePrevious() {
        conection.movePrevious();
        setValues();
    }

    public void moveFirst() {
        conection.moveFirst();
        setValues();
    }

    public void moveLast() {
        conection.moveLast();
        setValues();
    }

    public void setValues() {
        this.numProveedor = conection.getInteger("id_proveedor");
        this.fecha = conection.getString("fecha");
        this.cantidad = conection.getInteger("producto");
        this.numProducto = conection.getInteger("producto");
        this.fecha = conection.getString("fecha");
        this.totalPrecProd = conection.getInteger("total_producto");
        this.precio = conection.getInteger("precio");
    }

    public double getSubtotal() {
        subtotal = cantidad * totalPrecProd;
        return subtotal;
    }

    public double getTotal() {
        total = subtotal + iva;
        return total;
    }

    public String sqlCompra() {
        sql = "insert into compras(fecha,id_proveedor,subtotal,iva,total) values ('" + id + "','" + fecha + "','" + numProveedor + "','" + subtotal + "','" + iva + "','" + total + "');";
        return sql;
    }

    public String sqlDetalleCompra() {
        sql2 = "insert into detalle_compra(id_compra,id_producto,cantidad,total_precio_producto,precio) values (" + "'" + id + "','" + numProducto + "','" + cantidad + "','" + totalPrecProd + "','" + precio + "');";
        return sql2;
    }
}
