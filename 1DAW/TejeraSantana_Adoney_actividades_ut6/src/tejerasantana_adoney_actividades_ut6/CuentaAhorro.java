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
    
    public CuentaAhorro (CuentaBancaria cuenta, double interes) {
        super(cuenta.getTitular(), cuenta.getSaldo(), cuenta.getNumeroCuenta());
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }
    public void setInteres(double interes) {
        this.interes = interes;
    }
}
