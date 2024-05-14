/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2;

/**
 *
 * @author AdoneyDAW
 */
public class Lavadora extends Electrodomestico{
    private float precio;
    private boolean aguaCaliente;
    
    public Lavadora () {
        this.precio = 0;
        this.aguaCaliente = false;
    }
    public Lavadora (String tipo, String marca, float potencia, float precio, boolean aguaCaliente) {
        super(tipo, marca, potencia);
        this.precio = precio;
        this.aguaCaliente = aguaCaliente;
    }
    
    public String toString ()  {
        return "Tipo: " + this.tipo + " || Marca: " + this.marca + " || Potencia: " + String.valueOf(this.potencia) + " || Precio: " + String.valueOf(this.precio) + " || Agua caliente: " + String.valueOf(this.aguaCaliente);
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isAguaCaliente() {
        return aguaCaliente;
    }

    public void setAguaCaliente(boolean aguaCaliente) {
        this.aguaCaliente = aguaCaliente;
    }
    
    public float getConsumo (int horas) {
        if (this.aguaCaliente) {
            return this.potencia*horas*0.2f;
            
        } else {
            return this.potencia*horas;
        }
    }
    
    
}
