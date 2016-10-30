/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import sax.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//import java.sql.SQLException;
//import java.sql.Statement; 
//import java.util.ArrayList;
import javax.swing.JOptionPane;
//import javax.swing.ImageIcon;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import models.ModelProductos;
import views.ViewProductos;
/**
 *
 * @author usuario
 */
public class ControllerProductos implements ActionListener{
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");
//     private TableConection conect=new TableConection();
    private ModelProductos modelProductos;
    private ViewProductos viewProductos;
    private String nombreArchivo;
    private String ruta;
    private String nuevaRuta;
    PreparedStatement ps;

    ResultSetMetaData rsm;
    DefaultTableModel dtm;

    public ControllerProductos(ModelProductos modelProductos, ViewProductos viewProductos) {
        this.modelProductos = modelProductos;
        this.viewProductos = viewProductos;
        this.viewProductos.jbtnAgregar.addActionListener(this);
        this.viewProductos.jbtnBusacr.addActionListener(this);
        this.viewProductos.jbtnEditar.addActionListener(this);
        this.viewProductos.jbtnEliminar.addActionListener(this);
        this.viewProductos.jbtnPrimero.addActionListener(this);
        this.viewProductos.jbtnSiguiente.addActionListener(this);
        this.viewProductos.jbtnUltimo.addActionListener(this);

        this.viewProductos.jbtnNuevo.addActionListener(this);
        this.viewProductos.jbtnAnter.addActionListener(this);
        this.viewProductos.setVisible(true);

        init_view();

    }

    public void init_view() {

        modelProductos.initValues();
        showValues();
        this.viewProductos.jtfId.setVisible(false);

    }

    private void showValues() {
        viewProductos.jtfId.setText("" + modelProductos.getIdProducto());
        viewProductos.jtfProducto.setText("" + modelProductos.getProducto());
        viewProductos.jtfPrecioC.setText(modelProductos.getPrecioCompra());
        viewProductos.jtfPrecioV.setText(modelProductos.getPrecioVenta());
        viewProductos.jTextAreaDescripción.setText(modelProductos.getDescripcion());
        viewProductos.jtfExistencia.setText(modelProductos.getExistencia());

    }

    /*public static void copiarArchivo(String origen, String destino) {
        try {
            Path rutaOrigen = Paths.get(origen);
            Path rutaDestino = Paths.get(destino);
            CopyOption[] opciones = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(rutaOrigen, rutaDestino, opciones);
        } catch (IOException err) {
            JOptionPane.showMessageDialog(null, "No hay archivo seleccionado");
        }
    }*/

    public void bloquearDesbloquear(boolean todos) {
        this.viewProductos.jbtnAgregar.setEnabled(todos);
        this.viewProductos.jbtnAnter.setEnabled(todos);
        this.viewProductos.jbtnBusacr.setEnabled(todos);

        this.viewProductos.jbtnEditar.setEnabled(todos);
        this.viewProductos.jbtnEliminar.setEnabled(!todos);
        this.viewProductos.jbtnPrimero.setEnabled(!todos);
        this.viewProductos.jbtnSiguiente.setEnabled(todos);
        this.viewProductos.jbtnUltimo.setEnabled(todos);

    }

    public void Guardar() {
        try {
            String producto = this.viewProductos.jtfProducto.getText();
            String idproducto = this.viewProductos.jtfId.getText();
            //String id_producto=this.viewProductos.jLId.getText();
            String descripción = this.viewProductos.jTextAreaDescripción.getText();
            String precioCompra = this.viewProductos.jtfPrecioC.getText();
            String precioVenta = this.viewProductos.jtfPrecioV.getText();
            String existencia = this.viewProductos.jtfExistencia.getText();

            String sql = "insert into productos(producto,descripción,precio_compra,precio_venta,existencia) values (" + "'" + producto + "','" + descripción + "','" + precioCompra + "','" + precioVenta + "','" + existencia + "');";

            System.out.println("Nombre " + producto);
            System.out.println("SQL " + sql);

            conection.executeUpdate(sql);

            conection.executeQuery("Select * from productos");
            Primero();
            showValues();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewProductos, "No hay producto seleccionado");
        }

    }

    public void Primero() {
        modelProductos.moveFirst();
        showValues();
    }

    public void Siguiente() {
        modelProductos.moveNext();
        showValues();

    }

    public void Anterior() {
        modelProductos.movePrevious();
        showValues();
    }

    public void Eliminar() {
        try {
            String idproducto = this.viewProductos.jtfId.getText();

            conection.executeUpdate("delete from productos where id_producto=" + idproducto);
            conection.executeQuery("Select * from productos order by id_producto");

            this.viewProductos.jtfProducto.setText("");

            this.viewProductos.jTextAreaDescripción.setText("");
            this.viewProductos.jtfPrecioC.setText("");

            this.viewProductos.jtfPrecioV.setText("");
            this.viewProductos.jtfExistencia.setText("");

            //this.viewProductos.JlImagen.setIcon(null);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado");
        }
    }

    public void Editar() {

        String producto = this.viewProductos.jtfProducto.getText();
        // String id_producto=this.viewProductos.jtfId.getText();
        String descripción = this.viewProductos.jTextAreaDescripción.getText();
        String precioCompra = this.viewProductos.jtfPrecioC.getText();
        String precioVenta = this.viewProductos.jtfPrecioV.getText();
        String existencia = this.viewProductos.jtfExistencia.getText();

        try {
            // modelProductos.editar();

            String idproducto = this.viewProductos.jtfId.getText();

            conection.executeUpdate("update productos set producto='" + producto + "',descripción='" + descripción + "',precio_compra='" + precioCompra + "',precio_venta='" + precioVenta + "',existencia='" + existencia + "' where id_producto='" + idproducto + "';");

            this.viewProductos.jtfId.setText("");
            this.viewProductos.jtfProducto.setText("");

            this.viewProductos.jTextAreaDescripción.setText("");
            this.viewProductos.jtfPrecioC.setText("");

            this.viewProductos.jtfPrecioV.setText("");
            this.viewProductos.jtfExistencia.setText("");

            //this.rs.first();   
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay producto seleccionado ");
        }
    }

    public void ultimo() {
        modelProductos.moveLast();
        showValues();
    }

    /* public void mostrarTable()
    {
        try
        {
            String producto=this.viewProductos.jtfProducto.getText();
            ps("Select producto,descripción,precio_compra,precio_venta,existencia,imagen from productos where producto='"+producto+"'");
            rs=ps.executeQuery();
            rsm=rs.getMetaData();
            ArrayList<Object[]> data=new ArrayList<>();
            while(rs.next())
            {
            Object[] rows= new Object[rsm.getColumnCount()];
           
            for (int i=0; i<rows.length; i++)
            {
                rows[i]=rs.getObject(i+1);
            }
                data.add(rows);
            }
            dtm=(DefaultTableModel)this.viewProductos.jTable1.getModel();
           
            for (int i=0; i<data.size(); i++)
            {
                dtm.addRow(data.get(i));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Producto no existe" );
        }    
    }*/
    public void Nuevo() {
        this.viewProductos.jtfProducto.setText("");

        this.viewProductos.jTextAreaDescripción.setText("");
        this.viewProductos.jtfPrecioC.setText("");

        this.viewProductos.jtfPrecioV.setText("");
        this.viewProductos.jtfExistencia.setText("");

        //this.viewProductos.JlImagen.setIcon(null);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewProductos.jbtnAgregar) {
            Guardar();
        } else if (ae.getSource() == this.viewProductos.jbtnSiguiente) {
            Siguiente();
        } else if (ae.getSource() == this.viewProductos.jbtnAnter) {
            Anterior();
        } else if (ae.getSource() == this.viewProductos.jbtnEditar) {
            Editar();
        } else if (ae.getSource() == this.viewProductos.jbtnEliminar) {
            Eliminar();
        } else if (ae.getSource() == this.viewProductos.jbtnUltimo) {
            ultimo();

        } else if (ae.getSource() == this.viewProductos.jbtnPrimero) {
            Primero();
        } else if (ae.getSource() == this.viewProductos.jbtnNuevo) {
            Nuevo();
        }

    }
}
