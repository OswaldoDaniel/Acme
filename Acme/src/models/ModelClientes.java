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
public class ModelClientes {
    private DBConnection conection = new DBConnection(3306,"localhost", "acme", "root", "");
    public String  id;
    private String nombre;
    private String apMat;
    private String apPat;
    private String telefono;
    private String email;
    private String rfc;
    private String calle;
    private int numero;
    private String colonia;
    private String ciudad;
    private String estado;

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
     * @return the apMat
     */
    public String getApMat() {
        return apMat;
    }

    /**
     * @param apMat the apMat to set
     */
    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    /**
     * @return the apPat
     */
    public String getApPat() {
        return apPat;
    }

    /**
     * @param apPat the apPat to set
     */
    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    
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
        conection.executeQuery("SELECT * FROM cliente;");
        conection.moveNext();
        setValues();
    }
    
    public void setValues(){
        
        this.nombre = conection.getString("nombre");
        this.apMat = conection.getString("apellido_mat");
        this.apPat = conection.getString("apellido_pat");
        this.telefono = conection.getString("telefono");
        this.email = conection.getString("email");
        this.rfc = conection.getString("rfc");
        this.calle = conection.getString("calle");
        this.numero = conection.getInteger("no");
        this.colonia = conection.getString("colonia");
        this.ciudad = conection.getString("ciudad");
        this.estado = conection.getString("Estado");
    }
    
    
    public void guardar(){
        try {
            String sql = "insert into clientes(nombre,ap_paterno,ap_paterno,telefono,email,rfc,calle,no,colonia,ciudad,estado) values ('"+"','"+nombre+"','"+apMat+"','"+apPat+"','"+telefono+"','"+email+"','"+rfc+"','"+calle+",'"+numero+"','"+colonia+"','"+ciudad+"','"+estado+"');";
            conection.executeUpdate(sql);
            conection.executeQuery("Select * from clientes");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay cliente");
        }
    }
    
    public void borrar(){
        id = JOptionPane.showInputDialog("Dame la id del cliente","");
        conection.executeUpdate("delete from cliente where id_cliente=" + id);
    }
    
    public void editar(){
        conection.executeUpdate("update cliente set nombre='"+nombre+ "',ap_materno='"+apMat+"',ap_paterno='"+apPat+"',telefono='"+telefono+"',email='"+email+"',rfc='"+rfc+"',calle='"+calle+"',no='"+numero+"',colonia='"+colonia+"',ciudad='"+ciudad+"',estado='"+estado+"'where id_producto='"+ id+"';");
    }
    
}
