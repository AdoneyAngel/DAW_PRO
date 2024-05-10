
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.historial_pedidos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.BaseDatosConexionServicio;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */

//PEDIDOS: ID, precio, fecha, mesa, hecho

public class HistorialPedidosModel {
    private List<String[]> pedidos;
    private List<String[]> mesas;
    private List<String[]> productos;
    private BaseDatosConexionServicio db;
    
    public HistorialPedidosModel() {
        this.db = new BaseDatosConexionServicio();
        
        this.pedidos = new ArrayList();
        this.mesas = new ArrayList();
        this.productos = new ArrayList();
        
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
        this.cargarDatosMesas();
        
    }
    
    public DefaultTableModel getModelPedidos() {
        DefaultTableModel tableModel;
        
        String[] tableColumns = {"ID", "Precio", "En curso", "Fecha", "Mesa"};
        
        tableModel = new DefaultTableModel(tableColumns, 0) {
            Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class};
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int r, int c) {
                
                return false;
            }
        };
        
        //Se obtienen los datos
        
        for (String[] pedidoActual : this.pedidos) {
            String id = pedidoActual[0];
            String precio = pedidoActual[1] + " €";
            String fecha = pedidoActual[3];
            String nombreMesa = this.buscarNombreMesa(Integer.parseInt(pedidoActual[4]));
            boolean enCurso = pedidoActual[2].equals("true") ? true : false;
            
            Object[] nuevoPedidoParaTabla = {id, precio, enCurso, fecha, nombreMesa};
            
            tableModel.addRow(nuevoPedidoParaTabla);
        }
        
        //Se crea la clase de tabla
        
        
        return tableModel;
    }
    
    private String buscarNombreMesa(int idMesa) {
        for (String[] mesaActual : this.mesas) {
            int idActual = Integer.parseInt(mesaActual[0]);
            
            if (idActual == idMesa) {
                return mesaActual[1];
            }
        }
        
        return null;
    } 
    
    private void cargarDatosPedidos() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".pedido ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");        
                String fecha = resultset.getString("fecha");
                String enCurso = resultset.getString("en_curso");
                String idMesa = resultset.getString("id_mesa");
                String precio = resultset.getString("precio");
                
                String[] pedidoActual = {id, precio, enCurso, fecha, idMesa};

                this.pedidos.add(pedidoActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de pedidos: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private void cargarDatosProductos() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".producto ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nombre = resultset.getString("nombre");
                String categoria = resultset.getString("id_categoria");
                String precio = resultset.getString("precio");
                
                String[] productoActual = {id, nombre, precio, categoria};

                this.productos.add(productoActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de productos: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private void cargarDatosMesas() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".mesa ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nombre = resultset.getString("nombre");
                
                String[] mesaActual = {id, nombre};
                
                this.mesas.add(mesaActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de mesas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
}
