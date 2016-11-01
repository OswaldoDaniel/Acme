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
public class ModelReporteVentas {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private int id_venta;
    private String fecha;
    private String nombre;
    private String producto;
    private int cantidad;

    /**
     * @return the id_venta
     */
    public int getId_venta() {
        return id_venta;
    }

    /**
     * @param id_venta the id_venta to set
     */
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
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
    
    public void initValues(){
        conection.executeQuery("SELECT ventas.id_venta,ventas.fecha,cliente.nombre,productos.producto,detalle_venta.cantidad FROM cliente, ventas, detalle_venta, productos WHERE cliente.id_cliente = ventas.id_cliente AND ventas.id_venta = detalle_venta.id_venta AND productos.id_producto = detalle_venta.id_producto AND clientes.nombre = '"+nombre+"' AND productos.producto = '"+producto+"' AND veentas.fecha = '"+fecha+"'");
        conection.moveNext();
        setValues();
    }
    public void setValues(){
        this.id_venta = conection.getInteger("id_venta");
        this.nombre = conection.getString("nombre");
        this.producto = conection.getString("producto");
        this.fecha = conection.getString("fecha");
        this.cantidad = conection.getInteger("cantidad");
    }
}
