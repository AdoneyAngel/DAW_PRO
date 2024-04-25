
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionProductosModel {
    private GestionCategoriasModel categoriasModel;
    private ArrayList<String[]> productos;
    private String rutaProductos;

    public GestionProductosModel() {
        this.categoriasModel = new GestionCategoriasModel();
        this.productos = new ArrayList<>();
        
        /*//GENERANDO PRODUCTOS DE PRUEBA
        for (int a = 0; a<10; a++) {
            this.insertarProducto(a, ("Producto" + String.valueOf(a)), a*1.5, a);
        }*/
        //-----------------------------
        
        this.cargarRutaProductos();
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
             String[] nuevoProducto = {
                String.valueOf(id),
                nombre,
                String.valueOf(precio),
                 String.valueOf(idCategoria)
            };
            
            //this.productos.add(nuevoProducto);
            
            BufferedWriter productosWriter = null;
            
            try {
                String rowData = nuevoProducto[0] + "#" + nuevoProducto[1] + "#" + nuevoProducto[2] + "#" + nuevoProducto[3];
                
                File archivoProductos = new File(this.rutaProductos);
                productosWriter = new BufferedWriter(new FileWriter(archivoProductos, true));
                
                productosWriter.newLine();
                productosWriter.write(rowData);
                
            } catch (IOException e) {
                System.out.println("ERROR al insrtar producto: " + e.getMessage());
                
            } finally {
                try {
                    productosWriter.close();
                    this.cargarDatos();
                    
                } catch (IOException e) {
                    System.out.println("ERROR al inserar producto: " + e.getMessage());
                }
            }
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
            /*String[] producto = this.getProductoPorId(id);
            
            this.productos.remove(producto);*/
            
            BufferedWriter productosWriter = null;
            
            try {
                File archivoProductos = new File(this.rutaProductos);
                
                //Se abre 2 flujos, uno vaciará el archivo y el siguiente añadirá la información
                productosWriter = new BufferedWriter(new FileWriter(archivoProductos));
                productosWriter.close();
                
                productosWriter = new BufferedWriter(new FileWriter(archivoProductos, true));
                
                for (String[] productoActual : this.productos) {
                    if (Integer.parseInt(productoActual[0]) != id) {
                        String rowData = productoActual[0] + "#" + productoActual[1] + "#" + productoActual[2] + "#" + productoActual[3];
                        
                        productosWriter.newLine();
                        productosWriter.write(rowData);
                    }
                }
                
                
            } catch (IOException e) {
                System.out.println("ERROR al eliminar producto: " + e.getMessage());
                
            }finally {
                try {
                    productosWriter.close();
                    this.cargarDatos();
                    
                } catch (IOException e) {
                    System.out.println("ERROR al eliminar producto: " + e.getMessage());
                }
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
    
    private void cargarRutaProductos() {
        InputStream fileInputStream = null;
        Properties properties = new Properties();
        
        try {
            File archivoProperties = new File(System.getProperty("user.dir") + "\\src\\model\\datos\\rutas.properties");
            fileInputStream = new FileInputStream(archivoProperties);
            
            properties.load(fileInputStream);
            
            String rutaProductos = System.getProperty("user.dir") + properties.getProperty("path_productos");
            
            this.rutaProductos = rutaProductos;
            
            fileInputStream.close();
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ruga de productos: " + e.getMessage());
            
        } finally {
            try {
                fileInputStream.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar ruta productos: " + e.getMessage());
                        
            }
        }
    }
    
    private void cargarDatos() {
        ArrayList<String[]> productos = new ArrayList();
        BufferedReader productosReader = null;
        
        try {
            File archivoProductos = new File(this.rutaProductos);
            
            productosReader = new BufferedReader(new FileReader(archivoProductos));
            
            String fila = "";
            
            while ((fila = productosReader.readLine()) != null) {
                if (!fila.isEmpty()) {
                    String[] rowData = fila.split("#");
                    productos.add(rowData);                    
                }
            }
            
            this.productos = productos;
            
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos: " + e.getMessage());
            
        } finally {
            try {
                productosReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos: " + e.getMessage());
            }
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
