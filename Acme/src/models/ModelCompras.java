/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import sax.DBConnection;

/**
 *
 * @author usuario
 */
public class ModelCompras {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private int numProveedor;
    private int numProducto;
    private int Cantidad;
    private int TotalPrecProd;
    private String fecha;
    
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
     * @return the Cantidad
     */
    public int getCantidad() {
        return Cantidad;
    }

    /**
     * @param Cantidad the Cantidad to set
     */
    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    /**
     * @return the TotalPrecProd
     */
    public int getTotalPrecProd() {
        return TotalPrecProd;
    }

    /**
     * @param TotalPrecProd the TotalPrecProd to set
     */
    public void setTotalPrecProd(int TotalPrecProd) {
        this.TotalPrecProd = TotalPrecProd;
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
    
    public void moveNext(){
        conection.moveNext();
        setValues();
    }
    
    public void movePrevious(){
        conection.movePrevious();
        setValues();
    }
    
    public void moveFirst(){
        conection.moveFirst();
        setValues();
    }
    
    public void moveLast(){
        conection.moveLast();
        setValues();
    }
    
    public void setValues(){
        this.numProveedor = conection.getInteger("id_proveedor");
        this.fecha = conection.getString("fecha");
        this.Cantidad = conection.getInteger("producto");
        this.numProducto = conection.getInteger("producto");
        this.fecha = conection.getString("fecha");
        this.TotalPrecProd = conection.getInteger("Total_precio_producto");
    }
    
    public double getSubtotal(){
        subtotal = Cantidad * TotalPrecProd;
        return subtotal;
    }
    
    public double getTotal(){
        total = subtotal +(subtotal*0.16);
        return total;
    }
}
