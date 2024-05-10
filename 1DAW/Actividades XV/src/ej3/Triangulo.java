/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

/**
 *
 * @author AdoneyDAW
 */
public class Triangulo extends Figura{
    public Triangulo () {
        
    } 
    public Triangulo (float ancho, float alto) {
        super(ancho, alto);
    }
    
    public float area () {
        return (this.ancho * this.alto)/2;
    }
}
