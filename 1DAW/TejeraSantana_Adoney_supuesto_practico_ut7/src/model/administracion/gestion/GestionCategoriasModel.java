
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionCategoriasModel {
    private List<String[]> categorias;
    private String rutaCategorias;
    
    public GestionCategoriasModel() {
        this.categorias = new ArrayList<>();
        
        this.cargarRutaCategorias();
        this.cargarDatos();
        
        //DATOS DE EJEMPLO
        /*
        for (int a = 0; a<10; a++) {
            String categoriaNombre = "Categoria" + String.valueOf(a);
            String id = String.valueOf(a);
            
            String[] categoriaPRUEBA = {id, categoriaNombre};
            
            this.categorias.add(categoriaPRUEBA);
        }*/
        //----------------------
    }
    
    public DefaultTableModel getModelCategorias() {
        DefaultTableModel tableModel;
        String[] tableColumns = {"ID", "Nombre"};
        
        String[][] tableData = new String[this.categorias.size()][2];
        tableData = this.categorias.toArray(tableData);
        
        tableModel = new DefaultTableModel(tableData, tableColumns) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        return tableModel;
        
    }
    
    public void insertarCategoria(int id, String nombre) {
        if (!this.nombreExiste(nombre)) {
            String[] nuevaCategoria = {String.valueOf(id), nombre};
            
            //this.categorias.add(nuevaCategoria);
            
            BufferedWriter categoriasWriter = null;
            
            try {
                String newData = nuevaCategoria[0] + "#" + nuevaCategoria[1];
                
                File archivoCategorias = new File(this.rutaCategorias);
                categoriasWriter = new BufferedWriter(new FileWriter(archivoCategorias, true));
                
                categoriasWriter.newLine();
                categoriasWriter.write(newData);
                
            } catch (IOException e) {
                System.out.println("ERROR al insertar categoria: " + e.getMessage());
                
            } finally {
                try {
                    categoriasWriter.close();
                    this.cargarDatos();
                    
                } catch (IOException e) {
                    System.out.println("ERROR al insrtar categoria: " + e.getMessage());
                }
            }
        }
    }
    
    public void actualizarCategoria(int id, String nombre) {
        if (!this.nombreExiste(nombre)) {
            String[] categoria = getCategoriaPorId(id);
            categoria[1] = nombre;
            
            /*int categoriaIndex = this.categorias.indexOf(categoria);
            
            this.categorias.set(categoriaIndex, categoria);*/
            
            BufferedWriter categoriasWriter = null;
            
            try {
                File archivoCategorias = new File(this.rutaCategorias);
                
                //Se abre 2 veces el flujo, uno borrará todos los datos y el siguiente añadirá todos los datos linea a linea
                categoriasWriter = new BufferedWriter(new FileWriter(archivoCategorias));
                categoriasWriter.close();
                
                categoriasWriter = new BufferedWriter(new FileWriter(archivoCategorias, true));
                
                for (String[] categoriaActual : this.categorias) {
                    categoriasWriter.newLine();
                    if (id != Integer.parseInt(categoriaActual[0])) {
                        String rowData = categoriaActual[0] + "#" + categoriaActual[1];
                        categoriasWriter.write(rowData);
                        
                    } else {
                        String rowData = categoriaActual[0] + "#" + nombre;
                        categoriasWriter.write(rowData);
                    }
                }
                
            } catch (IOException e) {
                System.out.println("ERROR al actualizar categoria: " + e.getMessage());
                
            } finally {
                try {
                    categoriasWriter.close();
                    this.cargarDatos();
                    
                } catch (IOException e) {
                    System.out.println("ERROR al actualizar categoria: " + e.getMessage());
                }
            }
        }
    }
    
    public void eliminarCategoria(int id) {
        if (this.idExiste(id)) {
            String[] categoria = getCategoriaPorId(id);
            
            //this.categorias.remove(categoria);
            
            BufferedWriter categoriasWriter = null;
            
            try {
                File archivoCategorias = new File(this.rutaCategorias);
                
                //Se crea 2 veces el flujo, uno para borrarlo y otro para añadirlos, pero con append
                categoriasWriter = new BufferedWriter(new FileWriter(archivoCategorias));
                categoriasWriter.close();
                
                categoriasWriter = new BufferedWriter(new FileWriter(archivoCategorias, true));
                
                for (String[] categoriaActual : this.categorias) {
                    if (Integer.parseInt(categoriaActual[0]) != id) {
                        String rowData = categoriaActual[0] + "#" + categoriaActual[1];
                        categoriasWriter.write(rowData);
                        
                        categoriasWriter.newLine();
                    }
                }
                
                
            } catch (IOException e) {
                System.out.println("ERROR al eliminar categoria: " + e.getMessage());
                
            } finally {
                try {
                    categoriasWriter.close();
                    this.cargarDatos();
                    
                } catch (IOException e) {
                    System.out.println("ERROR al eliminar categoria: " + e.getMessage());
                }
            }
        }
    }
    
    private void cargarRutaCategorias() {
        InputStream fileInputStream = null;
        Properties properties = new Properties();
        
        try {
            File archivoProperties = new File(System.getProperty("user.dir") + "\\src\\model\\datos\\rutas.properties");
            fileInputStream = new FileInputStream(archivoProperties);
            
            properties.load(fileInputStream);
            
            String rutaCategorias = System.getProperty("user.dir") + properties.getProperty("path_categorias");
            
            this.rutaCategorias = rutaCategorias;
            
            fileInputStream.close();
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ruga de categorias: " + e.getMessage());
            
        } finally {
            try {
                fileInputStream.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar ruta categorias: " + e.getMessage());
                        
            }
        }
        
    }
    
    private void cargarDatos() {
        List<String[]> categorias = new ArrayList();
        BufferedReader categoriasReader = null;
        
        try {
            File archivoCategorias = new File(this.rutaCategorias);
            
            categoriasReader = new BufferedReader(new FileReader(archivoCategorias));
            
            String fila = "";
            
            while ((fila = categoriasReader.readLine()) != null) {
                if (!fila.isEmpty()) {
                    String[] rowData = fila.split("#");
                    categorias.add(rowData);                    
                }
            }
            
            this.categorias = categorias;
            
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos: " + e.getMessage());
            
        } finally {
            try {
                categoriasReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos: " + e.getMessage());
            }
        }
    }
    
    private String[] getCategoriaPorId(int id) {
        Iterator<String[]> categoriasIt = this.categorias.iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[0].equals(String.valueOf(id))) {
                return categoriaActual;
            }
        }
        
        return null;
    }
    
    public List<String[]> getCategorias() {
        return this.categorias;
    }
    
    private String[] getCategoriaPorNombre(String nombre) {
        Iterator<String[]> categoriasIt = this.categorias.iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[1].equals(nombre)) {
                return categoriaActual;
            }
        }
        
        return null;
    }
    
    private boolean idExiste(int id) {
        Iterator<String[]> categoriasIt = this.categorias.iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[0].equals(String.valueOf(id))) {
                return true;
            }
            
        }
        
        return false;
    }
    
    private boolean nombreExiste(String nombre) {
        Iterator<String[]> categoriasIt = this.categorias.iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[1].equals(nombre)) {
                return true;
            }
            
        }
        
        return false;
    }
}
