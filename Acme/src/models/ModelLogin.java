/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author L.A.M.M#13
 */
public class ModelLogin {
    Connection cn;
    private String us;
    private String pass;
    public boolean inicio;
    public boolean admin;
    
    public String getUs() {
        return us;
    }
    
    public void setUs(String us) {
        this.us = us;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void Entrar(String us, String pass, String opcion) {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme", "root", "");
            String nivel = "";
            String estado = "";
            String sql = "SELECT * FROM admin WHERE usuario = '" + us + "'&& contrasena='(MD5('"+pass+"'))'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nivel = rs.getString("nivel");
                estado = rs.getString("estado");
            }
            if (nivel.equals("administrador") && estado.equals("Activo") && opcion.equals("Iniciar")) {
                JOptionPane.showMessageDialog(null, "Bienvenid@  " + us);
                inicio = true;
                admin = true;
            } else if (nivel.equals("vendedor") && estado.equals("Activo") && opcion.equals("Iniciar")) {
                JOptionPane.showMessageDialog(null, "Bienvenid@  " + us);
                inicio = true;
                admin  = false;
            } else if(opcion.equals("Salir")){
                JOptionPane.showMessageDialog(null, "Sesion Cerrada ");
                inicio = false;
                admin  = false;
            }else {
                JOptionPane.showMessageDialog(null, "Vuelve a intentar");
                inicio = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}