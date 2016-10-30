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
public class ModelLogin {
     private DBConnection conection = new DBConnection(3306,"localhost", "acme", "root", "");
     private String us;
     private String pass;
     private String tipo;

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
     * @return the us
     */
    public String getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(String us) {
        this.us = us;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
     public void initValues(){
        conection.executeQuery("SELECT usuario,contrasena FROM admin;");
        conection.moveNext();
        setValues();
    }
    
    
    public void setValues(){
         this.us= conection.getString("usuario");
        this.pass = conection.getString("contrasena");
        
        
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
