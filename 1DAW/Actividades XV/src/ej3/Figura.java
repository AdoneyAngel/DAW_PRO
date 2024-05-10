/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

/**
 *
 * @author AdoneyDAW
 */
public class Figura {
    protected float ancho;
    protected float alto;
    
    public Figura () {
        
    }
    public Figura (float ancho, float alto) {
        this.alto = alto;
        this.ancho = ancho;
    }
    
    public float area () {
        return ancho*alto;
    }
}
