/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Adone
 */
public class Carta {
    private int id;
    private int mareo_invocacion;
    private String nombre;
    private int id_rareza;
    private String descripcion;
    private int fuerza;
    private int resistencia;
    private String imagen;
    private int id_tipo_carta;
    private int id_tipo_habilidad;
    
    public Carta () {
        
    }
    
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
        
    }

    //GETTES/SETTERS
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMare_invocacion() {
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
    
    
}
