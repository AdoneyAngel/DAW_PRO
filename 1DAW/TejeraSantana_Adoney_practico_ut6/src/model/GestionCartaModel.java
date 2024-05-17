/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adone
 */
public class GestionCartaModel {
    private List<Carta> cartas;
    private db db;
    
    public GestionCartaModel () {
        this.db = new db();
        this.cartas = new ArrayList();
        
        this.cargarCartas();
    }
    
    private void cargarCartas() {
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".cartas");
            
            while (result.next()) {
                int id = result.getInt("id");
                int mareo_invocacion = result.getInt("mareo_invocacion");
                String nombre = result.getString("nombre");
                int id_rareza = result.getInt("id_rareza");
                String descripcion = result.getString("descripcion");
                int fuerza = result.getInt("fuerza");
                int resistencia = result.getInt("resistencia");
                String imagen = result.getString("imagen");
                int id_tipo_carta = result.getInt("id_tipo_carta");
                int id_tipo_habilidad = result.getInt("id_tipo_habilidad");
                
                Carta nuevaCarta = new Carta(id, mareo_invocacion, nombre, id_rareza, descripcion, fuerza, resistencia, imagen, id_tipo_carta, id_tipo_habilidad);
                
                this.cartas.add(nuevaCarta);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar las cartas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    private List<Carta> cargarCartasPorQuery (String where) {
        List<Carta> cartasCargadas = new ArrayList();
        
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".cartas WHERE " + where);
            
            while (result.next()) {
                int id = result.getInt("id");
                int mareo_invocacion = result.getInt("mareo_invocacion");
                String nombre = result.getString("nombre");
                int id_rareza = result.getInt("id_rareza");
                String descripcion = result.getString("descripcion");
                int fuerza = result.getInt("fuerza");
                int resistencia = result.getInt("resistencia");
                String imagen = result.getString("imagen");
                int id_tipo_carta = result.getInt("id_tipo_carta");
                int id_tipo_habilidad = result.getInt("id_tipo_habilidad");
                
                Carta nuevaCarta = new Carta(id, mareo_invocacion, nombre, id_rareza, descripcion, fuerza, resistencia, imagen, id_tipo_carta, id_tipo_habilidad);
                
                cartasCargadas.add(nuevaCarta);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar las cartas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
            return cartasCargadas;
        }
    }
}
