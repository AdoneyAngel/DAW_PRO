
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.historial_pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.BaseDatosConexionServicio;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class DetallesPedidoModel {
    private List<String[]> pedidos;
    private List<int[]> comandaProductos;
    private List<String[]> productos;
    private List<String[]> mesas;
    private List<int[]> comandas;
    private BaseDatosConexionServicio db;
    
    public DetallesPedidoModel() {
        this.db = new BaseDatosConexionServicio();
        
        this.pedidos = new ArrayList();
        this.productos = new ArrayList();
        this.comandaProductos = new ArrayList();
        this.mesas = new ArrayList();
        this.comandas = new ArrayList();
        
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
        this.cargarDatosMesas();
        this.cargarDatosComandaProductos();
        this.cargarDatosComandas();
        
    }
    
    public DefaultTableModel getDetallesPedidoModel(int idPedido) {
        DefaultTableModel tableModel;
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        List<String[]> productosDePedido = this.getPedidoProductos(idPedido);
        
        tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable (int r, int c) {
                return false;
            }
        };
        
        //Se inserta cada uno de los productos a la tabla
        
        for (String[] productoActual : productosDePedido) {
            tableModel.addRow(productoActual);
        }
        
        return tableModel;
        
    }
    
    public Object[] getPropiedadesPedido(int idPedido) {
        Object[] detalles;
        String[] pedidoOriginal = this.getPedido(idPedido);
        
        String fecha = pedidoOriginal[3];
        double precio = Double.parseDouble(pedidoOriginal[1]);
        boolean finalizado = pedidoOriginal[2].equals("true");
        String nombreMesa = this.getNombreMesa(Integer.parseInt(pedidoOriginal[4]));
        
        detalles = new Object[] {idPedido, fecha, finalizado, nombreMesa, precio};
        
        return detalles;
    }
    
    private String getNombreMesa(int idMesa) {
        for (String[] mesaActual : this.mesas) {
            if (mesaActual[0].equals(String.valueOf(idMesa))) {
                return mesaActual[1];
            }
        }
        
        return null;
    }
    
    private String[] getPedido(int idPedido) {
        for (String[] pedidoActual : this.pedidos) {
            if (pedidoActual[0].equals(String.valueOf(idPedido))) {
                return pedidoActual;
            }
        }
        
        return null;
    }
    
    private List<String[]> getPedidoProductos(int idPedido) {
        List<String[]> productos = new ArrayList();
        List<int[]> comandasDePedido = this.obtenerComandasDePedido(idPedido);
        
        for (int[] comandaActual : comandasDePedido) {
            for (int[] productoActual : this.comandaProductos) {
                if (productoActual[0] == comandaActual[0]) {
                    String[] productoOriginal = this.getProducto(productoActual[1]);
                    
                    String nombreProducto = productoOriginal[1];
                    String precioProducto = productoOriginal[2];
                    String unidades = String.valueOf(productoActual[2]);
                    double subtotal = Double.parseDouble(precioProducto.replace(",", ".")) * Integer.parseInt(unidades);
                    
                    String[] productoParaTabla = {
                        nombreProducto,
                        precioProducto,
                        unidades,
                        String.valueOf(subtotal)
                    };
                    
                    productos.add(productoParaTabla);
                }
            }
        }
        
        return productos;
    }
    
    private List<int[]> obtenerComandasDePedido(int idPedido) {
        List<int[]> comandas = new ArrayList();
        
        for (int[] comandaActual : this.comandas) {
            if (comandaActual[1] == idPedido) {
                comandas.add(comandaActual);
            }
        }
        
        return comandas;
    }
    
    private String[] getProducto (int idProducto) {
        for (String[] productoActual : this.productos) {
            if (String.valueOf(idProducto).equals(productoActual[0])) {
                return productoActual;
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
    
    private void cargarDatosComandas() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".comanda");
            
            while (resultset.next()) {
                int id = resultset.getInt("id");
                int idPedido = resultset.getInt("id_pedido");
                
                int[] comandaActual = {id, idPedido};
                
                this.comandas.add(comandaActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de comandas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private void cargarDatosComandaProductos() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".comanda_producto ORDER BY id_comanda");
            
            while (resultset.next()) {
                int idComanda = resultset.getInt("id_comanda");
                int idProducto = resultset.getInt("id_producto");
                int cantidad = resultset.getInt("cantidad");
                
                int[] productoActual = {idComanda, idProducto, cantidad};

                this.comandaProductos.add(productoActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de producto de comandas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
}
