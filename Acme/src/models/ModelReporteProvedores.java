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
public class ModelReporteProvedores {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private String nombre;
    private String producto;
    private String fecha;
    private int cantidad;

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
        conection.executeQuery("SELECT proveedores.nombre,productos.producto,compras.fecha,detalle_compras.cantidad FROM proveedores,productos,compras,detalle_compras WHERE proveedores.id_provedor = compras.id_proveedor AND compras.id_compra = detalle_compra.id_compra AND productos.id_producto = detalle_compra.id_prodicto AND proveedores.nombre = '"+nombre+"' AND productos.producto = '"+producto+"' AND compras.fecha = '"+fecha+"'");
        conection.moveNext();
        setValues();
    }
    public void setValues(){
        this.nombre = conection.getString("nombre");
        this.producto = conection.getString("producto");
        this.fecha = conection.getString("fecha");
        this.cantidad = conection.getInteger("cantidad");
    }
}