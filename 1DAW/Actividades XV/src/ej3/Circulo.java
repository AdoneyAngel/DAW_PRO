/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

/**
 *
 * @author AdoneyDAW
 */
public class Circulo extends Figura{
    private float radio;
    
    public Circulo () {
        
    }
    public Circulo (float r) {
        this.radio = r;
    }
    
    public float area () {
        return this.radio*((float) Math.PI * (float) Math.PI);
    }
}
