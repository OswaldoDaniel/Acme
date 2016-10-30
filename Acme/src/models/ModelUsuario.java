/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import sax.DBConnection;
/**
 *
 * @author RoseLandjlord
 */
public class ModelUsuario {
    private DBConnection conection = new DBConnection(3306,"localhost", "acme", "root", "");
    private String id_admin;
    private String nombre;
    private String ap_pat;
    private String ap_mat;
    private String nivel;
    private String estado;
    private String usuario;
    private String contrasena;

    /**
     * @return the conection
     */
    public DBConnection getConection() {
        return conection;
    }

    /**
     * @param conection the conection to set
     */
    public void setConection(DBConnection conection) {
        this.conection = conection;
    }

    /**
     * @return the id_admin
     */
    public String getId_admin() {
        return id_admin;
    }

    /**
     * @param id_admin the id_admin to set
     */
    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
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
     * @return the ap_pat
     */
    public String getAp_pat() {
        return ap_pat;
    }

    /**
     * @param ap_pat the ap_pat to set
     */
    public void setAp_pat(String ap_pat) {
        this.ap_pat = ap_pat;
    }

    /**
     * @return the ap_mat
     */
    public String getAp_mat() {
        return ap_mat;
    }

    /**
     * @param ap_mat the ap_mat to set
     */
    public void setAp_mat(String ap_mat) {
        this.ap_mat = ap_mat;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
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
        conection.executeQuery("SELECT id_admin,nombre,apellido_pat,apellido_mat,usuario,contrasena,nivel,estado FROM admin;");
        conection.moveNext();
        setValues();
    }
    
    
    public void setValues(){
         this.id_admin = conection.getString("id_admin");
        this.nombre = conection.getString("nombre");
        this.ap_pat = conection.getString("apellido_pat");
        this.ap_mat = conection.getString("apellido_mat");
        this.setUsuario(conection.getString("usuario"));
        this.setContrasena(conection.getString("contrasena"));
        this.nivel = conection.getString("nivel");
        this.estado = conection.getString("estado");
              
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
