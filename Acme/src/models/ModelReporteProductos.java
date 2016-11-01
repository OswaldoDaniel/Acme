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
public class ModelReporteProductos {
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    private int id_producto;
    private String producto;
    private int existencia;
    private String fecha;
    private int id_proveedor;

    /**
     * @return the id_producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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
     * @return the existencia
     */
    public int getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(int existencia) {
        this.existencia = existencia;
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
     * @return the id_proveedor
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    /**
     * @param id_proveedor the id_proveedor to set
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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
        conection.executeQuery("SELECT productos.id_producto, productos.producto, productos.existencia, compras.fecha, proveedores.id_proveedor FROM productos, compras, proveedores WHERE proveedores.id_proveedor = compras.id_proveedor AND compras.id_compra = detalle_compra.id_compra AND productos.id_producto = detalle_compra.id_producto AND productos.id_producto = '"+id_producto+"' AND productos.producto = '"+producto+"' AND compras.fecha = '"+fecha+"' AND proveedores.id_proveedor = '"+id_proveedor+"' ");
        conection.moveNext();
        setValues();
    }
    public void setValues(){
        this.id_producto = conection.getInteger("id_producto");
        this.producto = conection.getString("producto");
        this.existencia = conection.getInteger("existencia");
        this.fecha = conection.getString("fecha");
        this.id_proveedor = conection.getInteger("id_proveedor");
    }
}
