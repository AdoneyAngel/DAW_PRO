/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej6;

/**
 *
 * @author AdoneyDAW
 */
public class Persona {
    protected String dni;
    protected String nombre;
    
    public Persona (String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    public String toString() {
        return this.dni + ": " + this.nombre;
    }
}
