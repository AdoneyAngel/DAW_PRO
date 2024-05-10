/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2;

/**
 *
 * @author AdoneyDAW
 */
public class Electrodomestico {
    protected String tipo;
    protected String marca;
    protected float potencia;
    
    public Electrodomestico () {
        
    }
    
    public Electrodomestico (String tipo, String marca, float potencia) {
        this.tipo = tipo;
        this.marca = marca;
        this.potencia = potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }
    
    public String toString () {
        return "Tipo: " + this.tipo + " || Marca: " + this.marca + " || Potencia " + String.valueOf(this.potencia);
    }
    
    public float getConsumo (int horas) {
        return horas*this.potencia;
    }
    
    public double getCosteConsumo (int horas, double costeHoras) {
        return horas*costeHoras;
    }
    
    
}
