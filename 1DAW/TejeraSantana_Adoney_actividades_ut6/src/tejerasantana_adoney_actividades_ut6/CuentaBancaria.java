/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

/**
 *
 * @author Adone
 */
public class CuentaBancaria implements Imprimible{
    private Persona titular;
    private double saldo;
    private String numeroCuenta;
    
    public CuentaBancaria (Persona titular, double saldo, String numeroCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
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
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String devolverInfoString() {
        String info = "";
        
        String saldo = String.valueOf(this.saldo);
        String numeroCuenta = String.valueOf(this.numeroCuenta);
        
        info = "ES " + numeroCuenta + ": $ " + saldo + " | Titular: [" + this.titular.devolverInfoString() + "]";
        
        return info;
    }
    
    public String getTipo() {
        return "cuenta";
    }
}
