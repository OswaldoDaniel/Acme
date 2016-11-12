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
public class ModelReporteClientes {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    
    private String cliente;
    private String producto;
    private int cantidad;
    private String fecha;

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
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
    
    public void initValues(){
        conection.executeQuery("SELECT cliente.nombre, productos.producto, detalle_venta.cantidad, ventas.fecha FROM cliente, productos, detalle_venta, ventas WHERE cliente.id_cliente = ventas.id_cliente, AND ventas.id_venta = detalle_venta.id_venta AND detalle_venta.id_producto = productos.id_producto AND clientes.nombre = '"+cliente+"' AND ventas.fecha'"+fecha+"' AND productos.producto = '"+producto+"' AND detalle_venta.cantidad = '"+cantidad+"'");
        conection.moveNext();
        setValues();
    }
    public void setValues(){
        this.cantidad = conection.getInteger("cantidad");
        this.producto = conection.getString("producto");
        this.cliente = conection.getString("descripción");
        this.fecha = conection.getString("existencia");
    }
}
