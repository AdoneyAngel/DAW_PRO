/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

/**
 *
 * @author Adone
 */
public abstract class CuentaBancaria implements Imprimible{
    private Persona titular;
    private double saldo;
    private String IBAN;
    
    public CuentaBancaria (Persona titular, double saldo, String IBAN) {
        this.titular = titular;
        this.saldo = saldo;
        this.IBAN = IBAN;
        
        this.titular.setDNI(this.titular.getDNI().toUpperCase());
    }

    public Persona getTitular() {
        return titular;
    }
    public void setTitular(Persona titular) {
        this.titular = titular;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public String getIBAN() {
        return IBAN;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.IBAN = numeroCuenta;
    }
    
    protected String infoBasica () {
        return this.titular.devolverInfoString() + " | IBAN: ES " + this.IBAN + " | Saldo: $ " + String.valueOf(this.saldo);
    }

    @Override
    public abstract String devolverInfoString();
    
    public abstract String getTipo();
}
