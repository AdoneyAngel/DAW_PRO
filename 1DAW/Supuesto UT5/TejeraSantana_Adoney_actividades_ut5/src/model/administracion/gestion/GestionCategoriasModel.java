
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AdoneyDAW
 */
public class GestionCategoriasModel {
    List<String[]> categorias;
    
    public GestionCategoriasModel() {
        this.categorias = new ArrayList<>();
        
        
        //DATOS DE EJEMPLO
        for (int a = 0; a<10; a++) {
            String categoriaNombre = "Categoria" + String.valueOf(a);
            String id = String.valueOf(a);
            
            String[] categoriaPRUEBA = {id, categoriaNombre};
            
            this.categorias.add(categoriaPRUEBA);
        }
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
            
            this.categorias.add(nuevaCategoria);
        }
    }
    
    public void actualizarCategoria(int id, String nombre) {
        if (!this.nombreExiste(nombre)) {
            String[] categoria = getCategoriaPorId(id);
            categoria[1] = nombre;
            
            int categoriaIndex = this.categorias.indexOf(categoria);
            
            this.categorias.set(categoriaIndex, categoria);
        }
    }
    
    public void eliminarCategoria(int id) {
        if (this.idExiste(id)) {
            String[] categoria = getCategoriaPorId(id);
            
            this.categorias.remove(categoria);
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
