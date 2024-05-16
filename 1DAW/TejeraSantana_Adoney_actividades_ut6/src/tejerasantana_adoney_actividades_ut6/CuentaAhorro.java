/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

/**
 *
 * @author Adone
 */
public class CuentaAhorro extends CuentaBancaria{
    private double interes;
    
    public CuentaAhorro (Persona titular, double saldo, String IBAN, double interes) {
        super(titular, saldo, IBAN);
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }
    public void setInteres(double interes) {
        this.interes = interes;
    }
    
    @Override
    public String getTipo () {
        return "ahorro";
    }

    @Override
    public String devolverInfoString() {
        return "Cuenta ahorro -> " + this.infoBasica() + " | Intereses: " + String.valueOf(this.interes);
    }
}
