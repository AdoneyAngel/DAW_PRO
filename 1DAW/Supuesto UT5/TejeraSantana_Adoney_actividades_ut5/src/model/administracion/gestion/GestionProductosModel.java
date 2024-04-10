/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.administracion.gestion;

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
    private List<String[]> productos;

    public GestionProductosModel() {
        this.categoriasModel = new GestionCategoriasModel();
        
        //GENERANDO PRODUCTOS DE PRUEBA
        for (int a = 0; a<10; a++) {
            String nombreCategoria = this.getCategoriaNombre(a);
            
            String[] nuevoProducto = {String.valueOf(a), ("Producto" + String.valueOf(a)), String.valueOf(a*1.5), nombreCategoria};
            
            this.productos.add(nuevoProducto);
        }
        //-----------------------------
    }
    
    public DefaultTableModel getModelProductos() {
        String[] tableColumns = {"ID", "Nombre", "Precio", "Categoria"};
        String[][] tableData = new String[this.productos.size()][4];
        tableData = this.productos.toArray(tableData);
        
        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableColumns);
        
        return tableModel;
    }
    
    public DefaultComboBoxModel<String> getComboBoxModelCategorias() {
        String[] categorias = getCategoriasNombre();
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel(categorias);
        
        return comboModel;
    }
    
    public void insertarProducto(int id, String nombre, double precio, int idCategoria) {
        
        if (!existeNombreProducto(nombre)) {
            String categoriaNombre = getCategoriaNombre(idCategoria);
            
            String[] nuevoProducto = {
                String.valueOf(id),
                nombre,
                String.valueOf(precio),
                categoriaNombre
            };
            
            this.productos.add(nuevoProducto);
        }
        
    }
    
    public void actualizarProducto(int id, String nombre, double precio, int idCategoria) {
        if (existeIdProducto(id)) {
            String[] producto = this.getProductoPorId(id);
            int productoIndex = this.productos.indexOf(producto);
            String nombreCategoria = this.getCategoriaNombre(idCategoria);
            
            producto[1] = nombre;
            producto[2] = String.valueOf(precio);
            producto[3] = nombreCategoria;
            
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
        if (existeNombreProducto(nombre)) {
            String[] producto = getProductoPorNombre(nombre);
            int categoriaId = getCategoriaId(producto[3]);
            
            return categoriaId;
            
        }
        
        return -1;
    }
    
    private int generarId() {
        boolean idValido = false;
        int nuevoId = -1;
        
        while (!idValido || nuevoId < 0) {
            nuevoId++;
            
            idValido = !existeIdProducto(nuevoId);
        }
        
        return nuevoId;
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
    
    private int getCategoriaId(String nombre) {
        TableModel categoriaTable = this.categoriasModel.getModelCategorias();
        
        for (int a = 0; a<categoriaTable.getRowCount(); a++) {
            String nombreCategoria = (String) categoriaTable.getValueAt(a, 1);
            String idCategoria = (String) categoriaTable.getValueAt(a, 0);
            
            if (nombreCategoria.equals(nombre)) {
                return Integer.parseInt(idCategoria);
            }
            
        }
        
        return -1;
    }
    
    private String getCategoriaNombre (int id) {
        TableModel categoriaTable = this.categoriasModel.getModelCategorias();
        
        for (int a = 0; a<categoriaTable.getRowCount(); a++) {
            String IdCategoria = (String) categoriaTable.getValueAt(a, 1);
            String nombreCategoria = (String) categoriaTable.getValueAt(a, 1);
            
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
