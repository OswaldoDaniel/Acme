"# Acme" 
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.ModelMain;
import sax.DBConnection;
import views.ViewMain;
import views.ViewProductos;
import views.ViewUsuario;
import views.ViewIniciarSecion;
import views.ViewCompras;
import views.ViewClientes;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerMAin implements ActionListener{
    
     private DBConnection conection = new DBConnection(3306,"localhost", "acme", "root", "");
            private ModelMain modelMain;
            private ViewMain viewMain;
            //JPanel views[];
            Object modules[];
    
            ControllerProducto controllerProducto;
            ControllerLogin controllerLogin;
            ControllerUsuario controllerUsuario;
            ControllerVenta controllerVenta;
            ControllerClientes controllerCliente;
            ControllerCompras controllerCompra;
            
            
    public ControllerMAin(ModelMain modelMain,ViewMain viewMain, /*JPanel views[]*/ Object modules[])
    {
        this.viewMain=viewMain;
         this.modelMain=modelMain;
         this.modules=modules;
         controllerProducto = (ControllerProducto)modules[0];
         controllerUsuario = (ControllerUsuario)modules[1];
          controllerLogin= (ControllerLogin )modules[2];
          controllerVenta=(ControllerVenta)modules[3];
          controllerCliente=(ControllerClientes)modules[4];
          controllerCompra=(ControllerCompras)modules[5];
         //this.views=views;
         this.viewMain.jMenuItemProductos.addActionListener(this);
         this.viewMain.jMenuItemIniciar.addActionListener(this);
         this.viewMain.jMenuItemUsuario.addActionListener(this);
         this.controllerLogin.viewIniciarSecion.jbtnIngresar.addActionListener(this);
         this.viewMain.jMenuItemVenta.addActionListener(this);
         this.viewMain.jMenuItemClientes.addActionListener(this);
         this.viewMain.jMenuItemCompras.addActionListener(this);
         
        
    }
     public void init_view()
     {
         this.viewMain.setTitle("Acme");
         this.viewMain.setLocationRelativeTo(null);
         this.viewMain.setVisible(true);
         this.viewMain.jMenuItemProductos.setEnabled(false);
         this.viewMain.jMenuItemUsuario.setEnabled(false);
         this.viewMain.jMenuItemVenta.setEnabled(false);
         this.viewMain.jMenuItemClientes.setEnabled(false);
         this.viewMain.jMenuItemCompras.setEnabled(false);
         this.viewMain.jMenuItemProveed.setEnabled(false);
     
     }
     public void actionPerfomedProductos()
    {
       this.viewMain.setContentPane(controllerProducto.viewProductos);
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }
      public void actionPerfomedLogin()
    {
       this.viewMain.setContentPane(controllerLogin.viewIniciarSecion);
       
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }
      public void actionPerfomedUsuario()
    {
       this.viewMain.setContentPane(controllerUsuario.viewUsuario);
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }
       public void actionPerfomedVenta()
    {
       this.viewMain.setContentPane(controllerVenta.viewVenta);
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }  public void actionPerfomedCompra()
    {
       this.viewMain.setContentPane(controllerCompra.viewCompras);
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }  public void actionPerfomedCliente()
    {
       this.viewMain.setContentPane(controllerCliente.viewClientes);
       this.viewMain.revalidate();
       this.viewMain.repaint();
    
    }
       public void fecha()
       {
           /*DateFormat df=  DateFormat.getDateInstance();
           Date fechaActual=new Date();
           this.controllerVenta.viewVenta.jtfFecha.setText(df.format(fechaActual));*/
           Date fecha= new Date();
           SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
           this.controllerVenta.viewVenta.jtfFecha.setText(formatoFecha.format(fecha));
       }
    
       
    public void actionPerfomedIniCerr()
    {
         String  us=this.controllerLogin.viewIniciarSecion.jtfUser.getText();
        String pass=new String(this.controllerLogin.viewIniciarSecion.jPassword.getPassword());
        
        String niv="";
        String est1="";
        String sql="SELECT * FROM admin WHERE usuario = '"+us+"'&& contrasena='"+pass+"'";
        
            
            conection.executeQuery(sql);
            while(conection.moveNext())
            {
              niv=conection.getString("nivel");
              est1=conection.getString("estado");
            }
            if(niv.equals("administrador") && est1.equals("Activo") )
            {
                
                
                JOptionPane.showMessageDialog(null,"Bienvenido  " + us);
                      this.viewMain.jMenuItemUsuario.setEnabled(true);
                      this.viewMain.jMenuItemProductos.setEnabled(true);
                      this.viewMain.jMenuItemVenta.setEnabled(true);
                      this.viewMain.jMenuItemClientes.setEnabled(true);
                      this.viewMain.jMenuItemCompras.setEnabled(true);
                      this.viewMain.jMenuItemProveed.setEnabled(true);
                       this.controllerVenta.viewVenta.JLVendedor.setText(us);
                      this.controllerLogin.viewIniciarSecion.setVisible(false);
                      this.viewMain.setVisible(true);
                       cerrarSesionText();
                        fecha();
                
            }
            else  if(niv.equals("vendedor") && est1.equals("Activo"))
            {
                JOptionPane.showMessageDialog(null,"Bienvenido  " + us);
                      this.viewMain.jMenuItemUsuario.setEnabled(false);
                      this.viewMain.jMenuItemProductos.setEnabled(false);
                       this.viewMain.jMenuItemClientes.setEnabled(false);
                      this.viewMain.jMenuItemCompras.setEnabled(false);
                      this.viewMain.jMenuItemProveed.setEnabled(false);
                      this.viewMain.jMenuItemVenta.setEnabled(true);
                      this.controllerVenta.viewVenta.JLVendedor.setText(us);
                       this.controllerLogin.viewIniciarSecion.setVisible(false);
                       this.viewMain.setVisible(true);
                        cerrarSesionText();
                      fecha();
                
            }else if(niv.equals("administrador") && est1.equals("Desactivo"))
            {
                 JOptionPane.showMessageDialog(null,"Esta persona esta Desactivado");
            }else if(niv.equals("vendedor") && est1.equals("Desactivo"))
            {
                 JOptionPane.showMessageDialog(null,"Esta persona esta Desactivado");
            }
            else
                JOptionPane.showMessageDialog(null,"Vuelve a intentar");
    
             
    }
        
     public void cerrarSesionText()
     {
         this.viewMain.jMenuItemIniciar.setText("Cerrar Sesión");
         
     }
     public void actionPerfomedCerrarSesion()
     {
         this.viewMain.jMenuItemIniciar.setText("Iniciar Sesión");
         this.viewMain.jMenuItemUsuario.setEnabled(false);
         this.viewMain.jMenuItemProductos.setEnabled(false);
         this.viewMain.jMenuItemVenta.setEnabled(false);
         this.viewMain.jMenuItemClientes.setEnabled(false);
         this.viewMain.jMenuItemCompras.setEnabled(false);
         this.viewMain.jMenuItemProveed.setEnabled(false);
         this.controllerLogin.viewIniciarSecion.setVisible(false);
         this.controllerLogin.viewIniciarSecion.setVisible(false);
         this.controllerProducto.viewProductos.setVisible(false);
         this.controllerUsuario.viewUsuario.setVisible(false);
         this.controllerVenta.viewVenta.setVisible(false);
         this.controllerCliente.viewClientes.setVisible(false);
         this.controllerCompra.viewCompras.setVisible(false);
         
               
    
     }

      
      
      
    @Override
    public void actionPerformed(ActionEvent ae) {
     if(ae.getSource()==this.viewMain.jMenuItemProductos)
        {
          actionPerfomedProductos();
         
        
        }else if(ae.getSource()==this.viewMain.jMenuItemIniciar &&  this.viewMain.jMenuItemIniciar.getText().equals("Iniciar Sesión"))
        {
           actionPerfomedLogin();
                
        
        }else if(ae.getSource()==this.viewMain.jMenuItemIniciar &&  this.viewMain.jMenuItemIniciar.getText().equals("Cerrar Sesión"))
        {
           actionPerfomedCerrarSesion();
                
        
        }
        else if(ae.getSource()==this.viewMain.jMenuItemUsuario)
        {
            actionPerfomedUsuario();
        
        } else if(ae.getSource()==this.controllerLogin.viewIniciarSecion.jbtnIngresar)
        {
            actionPerfomedIniCerr();
        }else if(ae.getSource()==this.viewMain.jMenuItemVenta)
        {
            actionPerfomedVenta();
        }else if(ae.getSource()==this.viewMain.jMenuItemCompras)
        {
            actionPerfomedCompra();
        }else if(ae.getSource()==this.viewMain.jMenuItemClientes)
        {
            actionPerfomedCliente();
        }
     
    
        
    }
    
}






package main;
import models.*;
import views.*;
import controllers.*;
import javax.swing.JPanel;
/**
 *
 * @author RoseLandjlord
 */
public class Main {
    public static void main(String[]Rose)
    {
        Object modules[] = new Object[6];
        ViewProductos viewProductos=new  ViewProductos();
        ModelProductos modelProductos= new ModelProductos();
        ControllerProducto  controllerProducto= new  ControllerProducto(modelProductos,viewProductos); 
        
        ViewIniciarSecion viewIniciarSecion=new ViewIniciarSecion();
        ModelLogin modelLogin=new ModelLogin();
        ControllerLogin controllerLogin=new ControllerLogin(modelLogin,viewIniciarSecion,viewProductos);
        
        ModelUsuario modelUsuario=new ModelUsuario();
        ViewUsuario viewUsuario=new ViewUsuario();
        ControllerUsuario controllerUsuario=new ControllerUsuario(modelUsuario,viewUsuario);
        
        ModelVentas modelVentas= new ModelVentas();
        ViewVenta viewVenta= new ViewVenta();
        ControllerVenta controllerVenta=new ControllerVenta(modelVentas,viewVenta);
        
        ModelClientes modelClientes = new ModelClientes();
        ViewClientes viewClientes=new ViewClientes();
        ControllerClientes controllerClientes=new ControllerClientes(modelClientes,viewClientes);
        
        ModelCompras modelCompras= new ModelCompras();
        ViewCompras viewCompras= new ViewCompras();
        ControllerCompras controllerCompras=new ControllerCompras(viewCompras,modelCompras);
        
        /* JPanel view[]=new JPanel[3];
                view[0]=viewProductos;
                view[1]=viewIniciarSecion;
                view[2]=viewUsuario;*/
         modules[0] = controllerProducto;
         modules[1] = controllerUsuario;
         modules[2] = controllerLogin;
         modules[3] = controllerVenta;
         modules[4] = controllerClientes;
         modules[5] =controllerCompras;
    
       ViewMain viewMain= new ViewMain();
       ModelMain modelMain=new ModelMain();
       ControllerMAin controllerMain = new ControllerMAin(modelMain,viewMain,modules);
       controllerMain.init_view();
    }
}



