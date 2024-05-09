
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BaseDatosConexionServicio;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionProductosModel {
    private GestionCategoriasModel categoriasModel;
    private ArrayList<String[]> productos;
    private BaseDatosConexionServicio db;

    public GestionProductosModel() {
        this.db = new BaseDatosConexionServicio();
        
        this.categoriasModel = new GestionCategoriasModel();
        this.productos = new ArrayList<>();
        
        this.cargarDatos();
    }
    
    public DefaultTableModel getModelProductos() {
        ArrayList<String[]> productosCopia = new ArrayList();
        
        for (String[] producto : this.productos) {
            String[] productoCopia = Arrays.copyOf(producto, producto.length);
            productosCopia.add(productoCopia);
        }          
        
        String[] tableColumns = {"ID", "Nombre", "Precio", "Categoria"};
        
        String[][] tableData = new String[productosCopia.size()][4];
        tableData = productosCopia.toArray(tableData);
        
        //Se cambia el ID de categoria por el nombre
        
        for (int tablaIndex = 0; tablaIndex<tableData.length; tablaIndex++) {
            String[] producto = tableData[tablaIndex];
            
            int categoriaId = Integer.parseInt(producto[3]);
            String nombreCategoria = this.getCategoriaNombre(categoriaId);
            
            producto[3] = nombreCategoria;
            
            tableData[tablaIndex] = producto;
            
        }
        
        for (String[] producto : this.productos) {
            String[] productoCopia = Arrays.copyOf(producto, producto.length);
            productosCopia.add(productoCopia);
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableColumns) {
             @Override
             public boolean isCellEditable(int r, int c) {
                 return false;
             }
        };
        
        return tableModel;
    }
    
    public DefaultComboBoxModel<String> getComboBoxModelCategorias() {
        String[] categorias = getCategoriasNombre();
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel(categorias);
        
        return comboModel;
    }
    
    public void insertarProducto(int id, String nombre, double precio, int idCategoria) {
        
        if (!existeNombreProducto(nombre)) {
            this.db.abrirConexion();
            
            Statement statement = null;
            
            try {
                statement = this.db.getConnection().createStatement();
                statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".producto VALUES ("+String.valueOf(id)+", '"+nombre+"', "+String.valueOf(precio)+", "+String.valueOf(idCategoria)+")");
                
            } catch (SQLException e) {
                System.out.println("ERROR AL INSERTAR PRODUCTO: " + e.getMessage());
                
            } finally {
                this.db.cerrarConexion();
                this.cargarDatos();
            }
        }
        
    }
    
    public void actualizarProducto(int id, String nombre, double precio, int idCategoria) {
        if (existeIdProducto(id)) {
            this.db.abrirConexion();
            
            Statement statement = null;
            
            try {
                statement = this.db.getConnection().createStatement();
                statement.executeUpdate("UPDATE "+this.db.getDbname()+".producto SET nombre='"+nombre+"', precio="+String.valueOf(precio)+", id_categoria="+String.valueOf(idCategoria)+" WHERE id="+String.valueOf(id));
                
            } catch (SQLException e) {
                System.out.println("ERROR AL ACTUALIZAR PRODUCTO: " + e.getLocalizedMessage());
                
            } finally {
                this.db.cerrarConexion();
                this.cargarDatos();
            }
        }
    }
    
    public void eliminarProducto(int id) {
        if (existeIdProducto(id)) {
            this.db.abrirConexion();
            
            Statement statement = null;
            
            try {
                statement = this.db.getConnection().createStatement();
                statement.executeUpdate("DELETE FROM "+this.db.getDbname()+".producto WHERE id="+String.valueOf(id));
                
            } catch (SQLException e) {
                System.out.println("ERROR al eliminar producto: " + e.getMessage());
                
            }finally {
                this.db.cerrarConexion();
                this.cargarDatos();
            }
        }
    }
    
    public int buscarCategoriaIdPorNombre(String nombre) {
        List<String[]> categorias = this.categoriasModel.getCategorias();
        
        return categorias.indexOf(nombre);
    }
    
    public List<String[]> getProductos() {
        return this.productos;
    }
    
    private void cargarDatos() {
        this.productos = new ArrayList();
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".producto ORDER BY id");
            
            while (resultset.next()) {
                String[] productoActual = {
                    resultset.getString("id"),
                    resultset.getString("nombre"),
                    resultset.getString("precio"),
                    resultset.getString("id_categoria")
                };
                
                this.productos.add(productoActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR AL CARGAR LOS PRODUCTOS: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private boolean existeIdProducto(int id) {
        Iterator<String[]> productosIt = this.productos.iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[0].equals(String.valueOf(id))) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean existeNombreProducto(String nombre) {
        Iterator<String[]> productosIt = this.productos.iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[1].equals(nombre)) {
                return true;
            }
        }
        
        return false;
    }
    
    private String[] getCategoriasNombre() {
        TableModel categoriaTable = this.categoriasModel.getModelCategorias();
        
        String[] nombres = new String[categoriaTable.getRowCount()];
        
        int index = 0;
        
        for (int a = 0; a<categoriaTable.getRowCount(); a++) {
            String IdCategoria = (String) categoriaTable.getValueAt(a, 1);
            String nombreCategoria = (String) categoriaTable.getValueAt(a, 1);
            
            nombres[index] = nombreCategoria;
            
            index++;
            
        }
        
        return nombres;
    }
    
    private String getCategoriaNombre (int id) {
        List <String[]> categorias = this.categoriasModel.getCategorias();
        
        for (String[] categoria : categorias) {
            String IdCategoria = categoria[0];
            String nombreCategoria = categoria[1];
            
            if (IdCategoria.equals(String.valueOf(id))) {
                return nombreCategoria;
            }
            
        }
        
        return null;
    }
    
}
