/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

import java.util.List;

/**
 *
 * @author Adone
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente{
    private double interesPorDescubierto;
    private int maximoDescubierto;
    
    public CuentaCorrienteEmpresa (CuentaBancaria cuenta, List<String> titulares, double interesesPorDescubierto, int maximoDescubierto) {
        super(cuenta, titulares);
        this.interesPorDescubierto = interesesPorDescubierto;
        this.maximoDescubierto = maximoDescubierto;
    }

    public double getInteresPorDescubierto() {
        return interesPorDescubierto;
    }

    public void setInteresPorDescubierto(double interesPorDescubierto) {
        this.interesPorDescubierto = interesPorDescubierto;
    }

    public int getMaximoDescubierto() {
        return maximoDescubierto;
    }

    public void setMaximoDescubierto(int maximoDescubierto) {
        this.maximoDescubierto = maximoDescubierto;
    }
    
    
}
