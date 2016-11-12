/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import static controllers.ControllerProducto.copiarArchivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;
import models.ModelUsuario;
import views.ViewUsuario;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerUsuario implements ActionListener {
    Connection conexion;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsm;
    ModelUsuario modelUsuario;
    ViewUsuario viewUsuario;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");

    public ControllerUsuario(ModelUsuario modelUsuario, ViewUsuario viewUsuario) {
        this.modelUsuario = modelUsuario;
        this.viewUsuario = viewUsuario;
        this.viewUsuario.jbtnAgregar.addActionListener(this);
        this.viewUsuario.jbtnAnterior.addActionListener(this);
        this.viewUsuario.jbtnEditar.addActionListener(this);
        this.viewUsuario.jbtnEliminar.addActionListener(this);
        this.viewUsuario.jbtnSiguiente.addActionListener(this);
        this.viewUsuario.jbtnUltimo.addActionListener(this);
        this.viewUsuario.jbntPrimero.addActionListener(this);
        this.viewUsuario.jbtnNuevo.addActionListener(this);
        this.viewUsuario.jbBuscar.addActionListener(this);
        
        this.viewUsuario.setVisible(true);
        init_view();

    }

    public void init_view() {

        modelUsuario.initValues();

        showValues();

    }

    private void showValues() {
        this.viewUsuario.jtfId.setText("" + modelUsuario.getId_admin());
        this.viewUsuario.jPassword.setText("" + modelUsuario.getContrasena());
        this.viewUsuario.jtfUsuario.setText("" + modelUsuario.getUsuario());
        this.viewUsuario.jtfNombre.setText("" + modelUsuario.getNombre());
        this.viewUsuario.jtfAp_pat.setText("" + modelUsuario.getAp_pat());
        this.viewUsuario.jtfAp_mat.setText("" + modelUsuario.getAp_mat());
        this.viewUsuario.jComboBoxEstado.setSelectedItem("" + modelUsuario.getEstado());
        this.viewUsuario.jComboBoxNivel.setSelectedItem("" + modelUsuario.getNivel());
    }

    public void Guardar() {
        try {
            String id_admin = this.viewUsuario.jtfId.getText();
            String nombre = this.viewUsuario.jtfNombre.getText();
            String ap_pat = this.viewUsuario.jtfAp_pat.getText();
            String ap_mat = this.viewUsuario.jtfAp_mat.getText();
            String usuario = this.viewUsuario.jtfUsuario.getText();
            String contrasena = this.viewUsuario.jPassword.getText();
            this.viewUsuario.jComboBoxEstado.getSelectedItem();
            this.viewUsuario.jComboBoxNivel.getSelectedItem();
            String sql = "insert into admin(nombre,apellido_pat,apellido_mat,usuario,contrasena,nivel,estado) values (" + "'" + nombre + "','" + ap_pat + "','" + ap_mat + "','" + usuario + "',(MD5('"+contrasena+"')),'" + this.viewUsuario.jComboBoxNivel.getSelectedItem() + "','" + this.viewUsuario.jComboBoxEstado.getSelectedItem() + "');";

            System.out.println("Nombre " + nombre);
            System.out.println("SQL " + sql);

            conection.executeUpdate(sql);

            conection.executeQuery("Select * from admin");
            Primero();
            showValues();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewUsuario, "No hay usuario seleccionado");
        }

    }

    public void Primero() {
        modelUsuario.moveFirst();
        showValues();
    }

    public void Siguiente() {
        modelUsuario.moveNext();
        showValues();

    }

    public void Anterior() {
        modelUsuario.movePrevious();
        showValues();
    }

    public void ultimo() {
        modelUsuario.moveLast();
        showValues();
    }

    public void Eliminar() {
        try {
            String id_admin = this.viewUsuario.jtfId.getText();
            conection.executeUpdate("delete from admin where id_user=" + id_admin);
            conection.executeQuery("Select * from admin order by id_user");
            this.viewUsuario.jtfId.setText("");
            this.viewUsuario.jPassword.setText("");
            this.viewUsuario.jtfUsuario.setText("");
            this.viewUsuario.jtfNombre.setText("");
            this.viewUsuario.jtfAp_pat.setText("");
            this.viewUsuario.jtfAp_mat.setText("");
            this.viewUsuario.jComboBoxEstado.setSelectedItem("");
            this.viewUsuario.jComboBoxNivel.setSelectedItem("");
            //this.viewProductos.JlImagen.setIcon(null);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay usuario seleccionado");
        }
    }

    public void Editar() {
        String id_admin = this.viewUsuario.jtfId.getText();
        String nombre = this.viewUsuario.jtfNombre.getText();
        String ap_pat = this.viewUsuario.jtfAp_pat.getText();
        String ap_mat = this.viewUsuario.jtfAp_mat.getText();
        String usuario = this.viewUsuario.jtfUsuario.getText();
        String contrasena = this.viewUsuario.jPassword.getText();
        this.viewUsuario.jComboBoxEstado.getSelectedItem();
        this.viewUsuario.jComboBoxNivel.getSelectedItem();
        try {
            conection.executeUpdate("update admin set nombre='" + nombre + "',apellido_pat='" + ap_pat + "',apellido_mat='" + ap_mat + "',usuario='" + usuario + "',contrasena='" + contrasena + "',nivel='" + this.viewUsuario.jComboBoxNivel.getSelectedItem() + "',estado='" + this.viewUsuario.jComboBoxEstado.getSelectedItem() + "' where id_user'" + id_admin + "';");
            this.viewUsuario.jtfId.setText("");
            this.viewUsuario.jPassword.setText("");
            this.viewUsuario.jtfUsuario.setText("");
            this.viewUsuario.jtfNombre.setText("");
            this.viewUsuario.jtfAp_pat.setText("");
            this.viewUsuario.jtfAp_mat.setText("");
            this.viewUsuario.jComboBoxEstado.setSelectedItem("");
            this.viewUsuario.jComboBoxNivel.setSelectedItem("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay usuario seleccionado ");
        }
    }
    
    public void mostrarTablaUsuarios(){
        try {
            DefaultTableModel model = new DefaultTableModel();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/acme","root",""); 
            model.addColumn("id");
            model.addColumn("Nombre");
            model.addColumn("ApM");
            model.addColumn("ApP");
            model.addColumn("Usuario");
            model.addColumn("Contraseña");
            model.addColumn("Nivel");
            model.addColumn("Estado");
            this.viewUsuario.jtUsuario.setModel(model);
            String datos[] = new String [8];     
            String sql = "";
            if (this.viewUsuario.jrbSpecific.isSelected()){
                String nombre = JOptionPane.showInputDialog(null, "dame el nombre del cliente","");
                sql = "SELECT * FROM admin WHERE nombre='"+nombre+"'";
            } else {
                sql = "SELECT * FROM admin";
            }
            st=conexion.createStatement();
            rs = st.executeQuery(sql);
            rsm = rs.getMetaData();
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                model.addRow(datos);
            }
            this.viewUsuario.jtUsuario.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void nuevo() {
        this.viewUsuario.jtfId.setText("");
        this.viewUsuario.jPassword.setText("");
        this.viewUsuario.jtfUsuario.setText("");
        this.viewUsuario.jtfNombre.setText("");
        this.viewUsuario.jtfAp_pat.setText("");
        this.viewUsuario.jtfAp_mat.setText("");
        this.viewUsuario.jComboBoxEstado.setSelectedItem("");
        this.viewUsuario.jComboBoxNivel.setSelectedItem("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewUsuario.jbtnAgregar) {
            Guardar();
        } else if (ae.getSource() == this.viewUsuario.jbtnAnterior) {
            Anterior();
        } else if (ae.getSource() == this.viewUsuario.jbtnEditar) {
            Editar();
        } else if (ae.getSource() == this.viewUsuario.jbtnEliminar) {
            Eliminar();
        } else if (ae.getSource() == this.viewUsuario.jbtnSiguiente) {
            Siguiente();
        } else if (ae.getSource() == this.viewUsuario.jbtnUltimo) {
            ultimo();
        } else if (ae.getSource() == this.viewUsuario.jbntPrimero) {
            Primero();
        } else if (ae.getSource() == this.viewUsuario.jbtnNuevo) {
            nuevo();
        } else if (ae.getSource() == this.viewUsuario.jbBuscar){
            mostrarTablaUsuarios();
        }
    }
}
