/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Adone
 */
public class CartaSeleccionada extends Carta{
    
    public CartaSeleccionada (Carta carta) {
        super(carta.getId(), 
                carta.getMareo_invocacion(), 
                carta.getNombre(), 
                carta.getId_rareza(), 
                carta.getDescripcion(), 
                carta.getFuerza(), 
                carta.getResistencia(), 
                carta.getImagen(), 
                carta.getId_tipo_carta(), 
                carta.getId_tipo_habilidad());
    }
    
    public String generarDescripcion () {
        this.cargarTipos();
        this.cargarTiposHabilidad();
        this.cargarTiposMana();
        
        String descripcion = "";
        
        descripcion += "ID: " + String.valueOf(this.id);
        
        //Lista de mana
        descripcion += "\n\nCoste de Maná: \n";
        for (String[] manaActual : this.tiposMana) {
            descripcion += "-" + manaActual[2] + " " + manaActual[1] + "\n";
        }
        
        descripcion += "\nNombre: \n" + this.nombre;
        descripcion += "\n\nMareo de invocación: \n" + (this.mareo_invocacion == 1 ? "Si tiene" : "No tiene");
        descripcion += "\n\nDescripcion: \n" + this.descripcion;
        descripcion += "\n\nRareza: \n" + this.getTipoRarezaPorId(this.id_rareza)[1];
        
        //Lista de tipos de carta
        String[] tipoPrincipal = this.getTipoCartaPorId(this.id_tipo_carta);
        
        descripcion += "\n\nTipos: \n";
        descripcion += "-" + tipoPrincipal[1] + "\n";
        for (String[] tipoActual : this.tipos) {
            descripcion += "-" + tipoActual[1] + "\n";
        }
        
        //Lista de habilidades
        String[] tipoHabilidadPrincipal = this.getTipoHabilidadPorId(this.id_tipo_carta);
        
        descripcion += "\nHabilidades: \n";
        descripcion += "-" + tipoHabilidadPrincipal[1] + "\n";
        for (String[] habilidaActual : this.tiposHabilidades) {
            descripcion += "-" + habilidaActual[1] + "\n";
        }
        
        descripcion += "\nAtaque: \n" + this.fuerza;
        descripcion += "\n\nResistencia: \n" + this.resistencia;
        
        return descripcion;
    }
    
    private String[] getTipoCartaPorId (int id) {
        Statement statement = null;
        ResultSet result = null;
        
        String[] tipo = new String [2];
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_carta WHERE id = " + id);
            
            while (result.next()) {
                tipo[0] = result.getString("id");
                tipo[1] = result.getString("tipo");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar el tipo de carta por ID: " + e.getMessage());
            
        } finally {
            return tipo;
        }
    }
    
    private String[] getTipoHabilidadPorId (int id) {
        Statement statement = null;
        ResultSet result = null;
        
        String[] tipo = new String [2];
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_habilidad WHERE id = " + id);
            
            while (result.next()) {
                tipo[0] = result.getString("id");
                tipo[1] = result.getString("tipo");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar el tipo de habilidad por ID: " + e.getMessage());
            
        } finally {
            return tipo;
        }
    }
    
    private String[] getTipoRarezaPorId (int id) {
        Statement statement = null;
        ResultSet result = null;
        
        String[] tipo = new String [2];
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM "+this.db.getName()+".tipos_rareza WHERE id = " + id);
            
            while (result.next()) {
                tipo[0] = result.getString("id");
                tipo[1] = result.getString("rareza");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar el tipo de rareza por ID: " + e.getMessage());
            
        } finally {
            return tipo;
        }
    }
}
