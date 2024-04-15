
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author AdoneyDAW
 */
public class GestionProductosModel {
    private GestionCategoriasModel categoriasModel;
    private ArrayList<String[]> productos;

    public GestionProductosModel() {
        this.categoriasModel = new GestionCategoriasModel();
        this.productos = new ArrayList<>();
        
        //GENERANDO PRODUCTOS DE PRUEBA
        for (int a = 0; a<10; a++) {
            this.insertarProducto(a, ("Producto" + String.valueOf(a)), a*1.5, a);
        }
        //-----------------------------
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
             String[] nuevoProducto = {
                String.valueOf(id),
                nombre,
                String.valueOf(precio),
                 String.valueOf(idCategoria)
            };
            
            this.productos.add(nuevoProducto);
        }
        
    }
    
    public void actualizarProducto(int id, String nombre, double precio, int idCategoria) {
        if (existeIdProducto(id)) {
            String[] producto = this.getProductoPorId(id);
            int productoIndex = this.productos.indexOf(producto);
            
            producto[1] = nombre;
            producto[2] = String.valueOf(precio);
            producto[3] = String.valueOf(idCategoria);
            
            this.productos.set(productoIndex, producto);
        }
    }
    
    public void eliminarProducto(int id) {
        if (existeIdProducto(id)) {
            String[] producto = this.getProductoPorId(id);
            
            this.productos.remove(producto);
        }
    }
    
    public int buscarCategoriaIdPorNombre(String nombre) {
        List<String[]> categorias = this.categoriasModel.getCategorias();
        
        return categorias.indexOf(nombre);
    }
    
    public List<String[]> getProductos() {
        return this.productos;
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
    
    private String[] getProductoPorId(int id) {
        Iterator<String[]> productosIt = this.productos.iterator();
        
        if (!existeIdProducto(id)) {
            return null;
        }
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[0].equals(String.valueOf(id))) {
                return productoActual;
            }
        }
        
        return null;
        
    }
    
    private String[] getProductoPorNombre(String nombre) {
        Iterator<String[]> productosIt = this.productos.iterator();
        
        if (!existeNombreProducto(nombre)) {
            return null;
        }
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[1].equals(nombre)) {
                return productoActual;
            }
        }
        
        return null;
    }
    
}
