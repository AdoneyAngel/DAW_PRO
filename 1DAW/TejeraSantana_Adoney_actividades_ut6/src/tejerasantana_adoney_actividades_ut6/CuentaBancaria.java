/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

/**
 *
 * @author Adone
 */
public class CuentaBancaria {
    private Persona titular;
    private double saldo;
    private int numeroCuenta;
    
    public CuentaBancaria (Persona titular, double saldo, int numeroCuenta) {
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
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
