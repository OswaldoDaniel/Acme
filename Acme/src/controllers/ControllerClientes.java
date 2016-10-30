/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import sax.DBConnection;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.sql.Connection; 
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
//import java.sql.SQLException;
//import java.sql.Statement; 
//import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ModelClientes;
import views.viewClientes;
//import SRL.TableConection;
/**
 *
 * @author usuario
 */
public class ControllerClientes implements ActionListener{
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
    PreparedStatement ps;

    ResultSetMetaData rsm;
    DefaultTableModel dtm;

    private ModelClientes modelClientes;
    private viewClientes viewClientes;
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
