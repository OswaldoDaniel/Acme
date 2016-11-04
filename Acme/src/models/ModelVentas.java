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
public class ModelVentas {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private int numcliente;
    private int numProducto;
    private int Cantidad;
    private int TotalPrecProd;
    private String fecha;
    
    public float total;
    public float subtotal;
    
    /**
     * @return the numcliente
     */
    public int getNumcliente() {
        return numcliente;
    }

    /**
     * @param numcliente the numcliente to set
     */
    public void setNumcliente(int numcliente) {
        this.numcliente = numcliente;
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
        this.numcliente = conection.getInteger("id_cliente");
        this.fecha = conection.getString("fecha");
        this.Cantidad = conection.getInteger("cantidad");
        this.numProducto = conection.getInteger("producto");
        this.fecha = conection.getString("fecha");
        this.TotalPrecProd = conection.getInteger("total_producto");
    }
    
    public float gSubtotal(){
        subtotal = Cantidad * TotalPrecProd;
        return subtotal;
    }
    
    public float gTotal(){
        total = subtotal;
        return total;
    }

    
    
}
