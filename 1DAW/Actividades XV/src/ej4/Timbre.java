/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej4;

/**
 *
 * @author AdoneyDAW
 */
public class Timbre {
    private boolean activado;
    
    public Timbre () {
        
    }
    
    public void activar () {
        this.activado = true;
        System.out.println("Timbre activado");
    }
    
    public void desActivar () {
        this.activado = false;
        System.out.println("Timbre desactivado");
    }
}
