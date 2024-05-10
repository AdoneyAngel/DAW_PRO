
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.BaseDatosConexionServicio;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */

//PRODUCTO: ID, nombre, categoriaID, precio
//CATEGORIA: ID, nombre
//PEDIDO: ID, precio, fecha, idMesa, enCurso
//COMANDA: ID, idPedido
//COMANDAPRODUCTO: IDcomanda, idProducto, cantidad
//PRODUCTOPEDIDO: idPedido, idProducto, Cantidad

public class AddProductosModel {
    private List<String[]> categorias;
    private List<String[]> productos;
    private List<String[]> pedidos;
    private List<String[]> pedidoProductos;
    private List<int[]> comandas;
    private List<int[]> comandaProductos;
    private BaseDatosConexionServicio db;
    
    public AddProductosModel() {
        this.db = new BaseDatosConexionServicio();
        
        this.productos = new ArrayList();
        this.pedidos = new ArrayList();
        this.categorias = new ArrayList();
        this.comandas = new ArrayList();
        this.comandaProductos = new ArrayList();
        this.pedidoProductos = new ArrayList();
        
        this.cargarDatosComandas();
        this.cargarDatosComandaProductos();
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
        this.cargarDatosCategorias();
    }

    public List<String[]> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String[]> categorias) {
        this.categorias = categorias;
    }

    public List<String[]> getProductos() {
        return productos;
    }

    public void setProductos(List<String[]> productos) {
        this.productos = productos;
    }

    public List<String[]> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<String[]> pedidos) {
        this.pedidos = pedidos;
    }

    public List<String[]> getPedidoProductos() {
        return pedidoProductos;
    }

    public void setPedidoProductos(List<String[]> pedidoProductos) {
        this.pedidoProductos = pedidoProductos;
    }

    public List<int[]> getComandas() {
        return comandas;
    }

    public void setComandas(List<int[]> comandas) {
        this.comandas = comandas;
    }

    public List<int[]> getComandaProductos() {
        return comandaProductos;
    }

    public void setComandaProducto(List<int[]> comandaProducto) {
        this.comandaProductos = comandaProducto;
    }

    
   
    public String[] getNombreCategorias() {
        String[] nombreCategorias = new String[this.getCategorias().size()];
        
        Iterator<String[]> categoriasIt = this.getCategorias().iterator();
        int index = 0;
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            nombreCategorias[index] = categoriaActual[1];
            
            index++;
            
        }
        
        return nombreCategorias;
    }
    
    public DefaultTableModel getTablaVaciaModel() {
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        
        DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        return tableModel;
    }
    
    public String[] getNombreProductosCategoria(String nombreCategoria) {
        String[] categoria = getCategoriaPorNombre(nombreCategoria);
        
        String[] nombreProductosCategoria;
        List<String> productosCategoriaList = new ArrayList<>();
        
        int index = 0;
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (categoria[0].equals(productoActual[3])) {
                productosCategoriaList.add(productoActual[1]);
                
                index++;
            }
        }
        
        nombreProductosCategoria = new String[productosCategoriaList.size()];
        
        nombreProductosCategoria = productosCategoriaList.toArray(nombreProductosCategoria);
        
        return nombreProductosCategoria;
    }
    
    public double getPrecioProductoNombre(String nombreProducto) {
        String[] producto = getProductoPorNombre(nombreProducto);
        
        double precio = Double.parseDouble(producto[3]);
        
        return precio;
    }
    
    public int getIdProductoNombre(String nombreProducto) {
        String[] producto = getProductoPorNombre(nombreProducto);
        
        int id = Integer.parseInt(producto[0]);
        
        return id;
    }
    
    public double getPrecioPedidoId (int id) {
        String[] pedido = getPedidoPorId(id);
        String[] producto = getProductoPorId(Integer.parseInt(pedido[0]));
        
        return Double.parseDouble(producto[3]);
        
    }
    
    public void insertarPedido(int id, double precio, String fecha, int idMesa) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".pedido VALUES ("+id+", "+precio+", 'true', CURDATE(), "+idMesa+")");
            
        } catch (SQLException e) {
            System.out.println("ERROR al insertar pedido: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public void insertarComanda(int id, int idPedido) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".comanda VALUES ("+id+", "+idPedido+")");
            
        } catch (SQLException e) {
            System.out.println("ERROR al insertar comanda: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }   
        
    }
    
    public void insertarComandaProducto(int idComanda, int idProducto, int cantidad) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".comanda_producto VALUES ("+idComanda+", "+idProducto+", "+cantidad+")");
            
        } catch (SQLException e) {
            System.out.println("ERROR al insertar producto de comanda: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public void actualizarPrecioTotalPedido(int idPedido, double precio) {
       Iterator<String[]> pedidosIt = this.getPedidos().iterator();
       
       int index = 0;
       
       while (pedidosIt.hasNext()) {
           String[] pedidoActual = pedidosIt.next();
           
           if (pedidoActual[0].equals(String.valueOf(idPedido))) {
               pedidoActual[3] = String.valueOf(precio);
               
               this.getPedidos().set(index, pedidoActual);
           }
           
           index++;
       }
    }
    
    //----------------------Métodos implementados
    private int[] obtenerComanda(int idComanda) {
        for (int[] comandaActual : this.comandas) {
            if (comandaActual[0] == idComanda) {
                return comandaActual;
            }
        }
        
        return null;
    }
    
    private void recalcularPrecioPedido(int idPedido) {
        String[] pedidoOriginal = this.obtenerPedido(idPedido);
        
        this.cargarDatosComandas();
        this.cargarDatosComandaProductos();
        
        double precioTotal = 0;
        
        List<int[]> comandasDePedido = this.obtenerComandasDePedido(idPedido);
        
        for (int[] comandaActual : comandasDePedido) {
            for (int[] comandaProductoActual : this.comandaProductos) {
                if (comandaProductoActual[0] == comandaActual[0]) {
                    String[] productoOriginal = this.obtenerProducto(comandaProductoActual[1]);
                    double subtotal = Double.parseDouble(productoOriginal[2].replace(",", ".")) * comandaProductoActual[2];
                    
                    precioTotal += subtotal;
                }
            }
        }
        
        //Se actualizan los datos del pedido
        this.actualizarPedido(idPedido, precioTotal, Integer.parseInt(pedidoOriginal[2]), pedidoOriginal[3], Integer.parseInt(pedidoOriginal[4]));
        
        //Se vuelve a cargar los datos
        this.cargarDatosPedidos();
    }
    
    private String[] obtenerPedido(int idPedido) {
        for (String[] pedidoActual : this.pedidos) {
            if (Integer.parseInt(pedidoActual[0]) == idPedido) {
                return pedidoActual;
            }
        }
        
        return null;
    }
    
    private void actualizarPedido(int idPedido, double precio, int enCurso, String fecha, int idMesa) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("UDPATE "+this.db.getDbname()+".pedido SET precio="+precio+", enCurso='"+enCurso+"', fecha='"+fecha+"', id_mesa="+idMesa+" WHERE id="+idPedido);
            
        } catch (SQLException e) {
            System.out.println("ERROR al actualizar pedido: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private String[] obtenerProducto(int idProducto) {
        for (String[] productoActual : this.productos) {
            if (Integer.parseInt(productoActual[0]) == idProducto) {
                return productoActual;
            }
        }
        
        return null;
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
    
    private void cargarDatosComandas() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".comanda ORDER BY id");
            
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
    
    private void cargarDatosCategorias() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".categoria ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nombre = resultset.getString("nombre");
                
                String[] categoriaActual = {id, nombre};

                this.categorias.add(categoriaActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos de categorias: " + e.getMessage());
            
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
    
    private String[] getCategoriaPorNombre(String nombreCategoria) {
        Iterator<String[]> categoriasIt = this.getCategorias().iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[1].equals(nombreCategoria)) {
                return categoriaActual;
            }
        }
        
        return null;
    }
    
    private String[] getProductoPorNombre(String nombreProducto) {
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[1].equals(nombreProducto)) {
                return productoActual;
            }
        }
        
        return null;
        
    }
    
    private String[] getProductoPorId(int idProducto) {
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[0].equals(idProducto)) {
                return productoActual;
            }
        }
        
        return null;
    }
    
    private String[] getPedidoPorId(int idPedido) {
        Iterator<String[]> pedidosIt = this.getPedidos().iterator();
        
        while (pedidosIt.hasNext()) {
            String[] pedidoActual = pedidosIt.next();
            
            if (pedidoActual[0].equals(idPedido)) {
                return pedidoActual;
            }
        }
        
        return null;
    }
}
