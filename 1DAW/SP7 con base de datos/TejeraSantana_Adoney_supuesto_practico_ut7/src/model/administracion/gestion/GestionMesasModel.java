
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.BaseDatosConexionServicio;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionMesasModel {
    private List<String[]> mesas;
    private BaseDatosConexionServicio db;
    
    public GestionMesasModel() {
        this.db = new BaseDatosConexionServicio();
        
        this.mesas = new ArrayList();
        
        this.cargarDatosMesas();
        
    }
    
    public void insertarMesa(int id, String nombre) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "+this.db.getDbname()+".mesa VALUES ("+String.valueOf(id)+", '"+nombre+"')");
            
        } catch (SQLException e) {
            System.out.println("ERROR al insertar mesa: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
            this.cargarDatosMesas();
        }
    }
    
    public void borrarMesa(int id) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM "+this.db.getDbname()+".mesa WHERE id="+String.valueOf(id));
            
        } catch (SQLException e) {
            System.out.println("ERROR al eliminar mesa: " + e.getMessage());
            
        } finally {
            this.db.abrirConexion();
            this.cargarDatosMesas();
        }
        
    }
    
    public void actualizarMesa(int id, String nombre) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            statement.executeUpdate("UPDATE "+this.db.getDbname()+".mesa SET nombre='"+nombre+"' WHERE id="+String.valueOf(id));
            
        } catch (SQLException e) {
            System.out.println("ERROR al borrar mesa: " + e.getMessage());

        } finally {
            this.db.cerrarConexion();
            this.cargarDatosMesas();
        }
    }
    
    public DefaultTableModel getModelMesas() {
        this.cargarDatosMesas();
        
        String[] tableColumns = {"ID", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        for (String[] mesaActual : this.mesas) {
            tableModel.addRow(mesaActual);
        }
        
        return tableModel;
    }
    
    public List<String[]> getMesas() {
        return this.mesas;
    }
    
    private void cargarDatosMesas() {
        this.mesas = new ArrayList();
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".mesa ORDER BY id");
            
            while (resultset.next()) {
                String[] mesaActual = {
                    resultset.getString("id"),
                    resultset.getString("nombre")
                };
                
                this.mesas.add(mesaActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar mesas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    
}
