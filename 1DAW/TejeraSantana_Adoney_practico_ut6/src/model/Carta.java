/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Adone
 */
public class Carta {
    protected int id;
    protected int mareo_invocacion;
    protected String nombre;
    protected int id_rareza;
    protected String descripcion;
    protected int fuerza;
    protected int resistencia;
    protected String imagen;
    protected int id_tipo_carta;
    protected int id_tipo_habilidad;
    protected List<String[]> tipos;
    protected List<String[]> tiposHabilidades;
    protected List<String[]> tiposMana;
    protected db db;
    
    public Carta (int id, 
            int mareo_invocacion, 
            String nombre, 
            int id_rareza, 
            String descripcion, 
            int fuerza, 
            int resistencia,
            String imagen, 
            int id_tipo_carta,
            int id_tipo_habilidad) {
        
        this.id = id;
        this.mareo_invocacion = mareo_invocacion;
        this.nombre = nombre;
        this.id_rareza = id_rareza;
        this.descripcion = descripcion;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.imagen = imagen;
        this.id_tipo_carta = id_tipo_carta;
        this.id_tipo_habilidad = id_tipo_habilidad;
        this.db = new db();
        this.tipos = new ArrayList();
        this.tiposHabilidades = new ArrayList();
        this.tiposMana = new ArrayList();
        
    }
    
    //Funciones para cargar las listas de tipos de la carta actual
    
    public void cargarTipos () {
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_carta WHERE id IN (SELECT id_tipo_carta FROM "+this.db.getName()+".cartas_tipos_carta WHERE id_carta = "+this.id+")");
            
            while (result.next()) {
                String[] tipo = {
                    result.getString("id"),
                    result.getString("tipo")
                };
                
                this.getTipos().add(tipo);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de la carta actual: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public void cargarTiposHabilidad () {
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_habilidad WHERE id IN (SELECT id_habilidad FROM "+this.db.getName()+".cartas_tipo_habilidad WHERE id_carta = "+this.id+")");
            
            while (result.next()) {
                String[] tipo = {
                    result.getString("id"),
                    result.getString("tipo")
                };
                
                this.getTiposHabilidades().add(tipo);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de habilidades de la carta actual: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public void cargarTiposMana () {
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT "+this.db.getName()+".tipos_mana.id, "+
                    this.db.getName()+".tipos_mana.tipo, "+
                    this.db.getName()+".cartas_tipos_mana.cantidad "
                            + "FROM "+this.db.getName()+".cartas_tipos_mana "
                            + "LEFT JOIN "+this.db.getName()+".tipos_mana "
                            + "ON "+this.db.getName()+".cartas_tipos_mana.id_tipo_mana = "
                            + this.db.getName()+".tipos_mana.id WHERE "
                            + this.db.getName()+".cartas_tipos_mana.id_carta = " + this.id);
            
            while (result.next()) {
                String[] tipo = {
                    result.getString("id"),
                    result.getString("tipo"),
                    result.getString("cantidad")
                };
                
                this.getTiposMana().add(tipo);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de mana de la carta actual: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    //-------------------------------------------

    //GETTES/SETTERS
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMareo_invocacion() {
        return mareo_invocacion;
    }

    public void setMare_invocacion(int mare_invocacion) {
        this.mareo_invocacion = mare_invocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_rareza() {
        return id_rareza;
    }

    public void setId_rareza(int id_rareza) {
        this.id_rareza = id_rareza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_tipo_carta() {
        return id_tipo_carta;
    }

    public void setId_tipo_carta(int id_tipo_carta) {
        this.id_tipo_carta = id_tipo_carta;
    }

    public int getId_tipo_habilidad() {
        return id_tipo_habilidad;
    }

    public void setId_tipo_habilidad(int id_tipo_habilidad) {
        this.id_tipo_habilidad = id_tipo_habilidad;
    }

    public List<String[]> getTipos() {
        return tipos;
    }

    public List<String[]> getTiposHabilidades() {
        return tiposHabilidades;
    }

    public List<String[]> getTiposMana() {
        return tiposMana;
    }
    
    
}
