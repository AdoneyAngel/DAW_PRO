/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej6;

/**
 *
 * @author AdoneyDAW
 */
public class Senador extends Legislador{
    public Senador (String dni, String nombre, String provincia) {
        super(dni, nombre, provincia);
    }
    
    @Override
    public String getCamaraEnQueTrabaja () {
        return "Senador";
    }
}
