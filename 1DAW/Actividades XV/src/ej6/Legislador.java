/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej6;

/**
 *
 * @author AdoneyDAW
 */
public abstract class Legislador extends Persona{
    protected String provinciaQueRepresenta;
    
    public Legislador (String dni, String nombre, String provincia) {
        super(dni, nombre);
        this.provinciaQueRepresenta = provincia;
    }
    
    public abstract String getCamaraEnQueTrabaja ();
}
