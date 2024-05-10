/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7;

/**
 *
 * @author AdoneyDAW
 */
public abstract class Cuenta {
    protected int numeroCuenta;
    protected double saldo;
    protected Persona cliente;
    
    public Cuenta (int numeroCuenta, double saldo, Persona cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }
    
    public void ingresar (double dinero) {
        this.saldo += dinero;
    }
}
