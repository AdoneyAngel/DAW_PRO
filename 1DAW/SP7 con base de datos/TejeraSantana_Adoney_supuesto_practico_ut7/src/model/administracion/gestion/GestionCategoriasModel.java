
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import model.BaseDatosConexionServicio;
import java.sql.SQLException;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionCategoriasModel {
    private List<String[]> categorias;
    private BaseDatosConexionServicio db;
    
    public GestionCategoriasModel() {
        this.db = new BaseDatosConexionServicio();
        this.categorias = new ArrayList<>();
        
        this.cargarDatos();
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
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".categoria VALUES ("+id+", '"+nombre+"')");
            
        } catch (SQLException e) {
            System.out.println("ERROR AL INSERTAR CATEGORIA: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
            this.cargarDatos();
        }
    }
    
    public void actualizarCategoria(int id, String nombre) {
        if (!this.nombreExiste(nombre)) {
            Statement statement = null;
            this.db.abrirConexion();
            
            try {
                statement = this.db.getConnection().createStatement();
                
                statement.executeUpdate("UPDATE "+this.db.getDbname()+".categoria SET nombre = '"+nombre+"' WHERE id = "+String.valueOf(id)+"");
                
            } catch (SQLException e) {
                System.out.println("ERROR al actualizar categoria: " + e.getMessage());
                
            } finally {
                this.db.cerrarConexion();
                this.cargarDatos();
            }
        }
    }
    
    public void eliminarCategoria(int id) {
        if (this.idExiste(id)) {
            
            Statement statement = null;
            this.db.abrirConexion();
            
            try {
                statement = this.db.getConnection().createStatement();
                
                statement.executeUpdate("DELETE FROM "+this.db.getDbname()+".categoria WHERE id = "+String.valueOf(id));
                
            } catch (SQLException e) {
                System.out.println("ERROR al eliminar categoria: " + e.getMessage());
                
            } finally {
                this.db.cerrarConexion();
                this.cargarDatos();
            }
        }
    }
    
    private void cargarDatos() {
        this.categorias = new ArrayList();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM categoria ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nombre = resultset.getString("nombre");
                
                String[] nuevaCategoria = {id, nombre};
                
                this.categorias.add(nuevaCategoria);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR AL CARGAR LAS CATEGORIAS: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public List<String[]> getCategorias() {
        return this.categorias;
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
