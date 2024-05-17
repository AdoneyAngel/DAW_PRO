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
import model.interfaz.*;

/**
 *
 * @author Adone
 */
public class GestionCartaModel implements iCarta, iTiposCarta, iTiposHabilidad, iTiposMana, iTiposRareza, iCartas_tiposCarta{
    private db db;
    
    public GestionCartaModel () {
        this.db = new db();
    }
    
    public List<Carta> getCartasTierra () {
        String[] tipo = this.getTipoCartaPorNombre("Tierra");
        
        List<Carta> cartasTierra = this.cargarCartasPorQuery("id_tipo_carta = "+tipo[0]+" OR id IN (SELECT id_carta FROM "+this.db.getName()+".cartas_tipos_carta WHERE id_carta = "+this.db.getName()+".cartas.id AND id_tipo_carta = "+tipo[0]+")");
        
        return cartasTierra;
    }
    
    public List<Carta> getCartasCriatura () {
        List<Carta> cartasCriatura = this.getCartasDeTipo("Criatura");
                
        return cartasCriatura;
    }
    
    public List<Carta> getCartasEncantamiento () {
        List<Carta> cartasEncantamiento = this.getCartasDeTipo("Encantamiento");
                
        return cartasEncantamiento;
    }
    
    public List<Carta> getCartasInstantaneo () {
        List<Carta> cartasInstantaneo = this.getCartasDeTipo("Instantaneo");
                
        return cartasInstantaneo;
    }
    
    public List<Carta> getCartasConjuro () {
        List<Carta> cartasConjunto = this.getCartasDeTipo("conjunto");
                
        return cartasConjunto;
    }
    
    public List<Carta> getCartasArtefacto () {
        List<Carta> cartasArtefacto = this.getCartasDeTipo("artefacto");
                
        return cartasArtefacto;
    }
    
    @Override
    public String[] getTipoCartaPorNombre (String nombre) {
        Statement statement = null;
        ResultSet result = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_carta WHERE tipo LIKE '"+nombre+"'");
            
            String[] tipo = new String[2];
            
            while (result.next()) {
                tipo[0] = result.getString("id");
                tipo[1] = result.getString("tipo");
            }
            
            return tipo;
            
        } catch (SQLException e) {
            System.out.println("Error al cargar el tipo de carta: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
        
        return null;
    }
    
    private List<Carta> getCartas() {
        List<Carta> cartas = new ArrayList();
        
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
                
                cartas.add(nuevaCarta);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar las cartas: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
            return cartas;
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

    @Override
    public List<String[]> getTiposCarta() {
        Statement statement = null;
        ResultSet result = null;
        
        List<String[]> tipos = new ArrayList();
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_carta");
            
            while (result.next()) {
                String id = result.getString("id");
                String tipo = result.getString("tipo");
                
                String[] nuevoTipoCarta = {id, tipo};
                
                tipos.add(nuevoTipoCarta);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de carta: " + e.getMessage());
            
        } finally {
            return tipos;
        }
    }

    @Override
    public List<Carta> getCartasDeTipo(String nombreTipo) {
        String[] tipo = this.getTipoCartaPorNombre(nombreTipo);
        
        List<Carta> cartasDeTipo = this.cargarCartasPorQuery("id_tipo_carta = "+tipo[0]+" OR+ id IN (SELECT id_carta FROM "+this.db.getName()+".cartas_tipos_carta WHERE id_carta = "+this.db.getName()+".cartas.id AND id_tipo_carta = "+tipo[0]+")");
        
        return cartasDeTipo;
    }

    @Override
    public List<String[]> getTiposHabilidad() {
        Statement statement = null;
        ResultSet result = null;
        
        List<String[]> tipos = new ArrayList();
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_habilidad");
            
            while (result.next()) {
                String id = result.getString("id");
                String tipo = result.getString("tipo");
                
                String[] nuevoTipoHabilidad = {id, tipo};
                
                tipos.add(nuevoTipoHabilidad);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de habilidad: " + e.getMessage());
            
        } finally {
            return tipos;
        }
    }

    @Override
    public List<String[]> getTiposMana() {
        Statement statement = null;
        ResultSet result = null;
        
        List<String[]> tipos = new ArrayList();
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_mana");
            
            while (result.next()) {
                String id = result.getString("id");
                String tipo = result.getString("tipo");
                
                String[] nuevoTipoMana = {id, tipo};
                
                tipos.add(nuevoTipoMana);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de mana: " + e.getMessage());
            
        } finally {
            return tipos;
        }
    }

    @Override
    public List<String[]> getTiposRareza() {
        Statement statement = null;
        ResultSet result = null;
        
        List<String[]> tipos = new ArrayList();
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_rareza");
            
            while (result.next()) {
                String id = result.getString("id");
                String tipo = result.getString("rareza");
                
                String[] nuevoTipoRareza = {id, tipo};
                
                tipos.add(nuevoTipoRareza);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos de rareza: " + e.getMessage());
            
        } finally {
            return tipos;
        }
    }
}
