/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

/**
 *
 * @author Adone
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente{
    private double interesPorDescubierto;
    private int maximoDescubierto;
    
    public CuentaCorrienteEmpresa (CuentaCorriente cuentaCorriente, double interesesPorDescubierto, int maximoDescubierto) {
        super(cuentaCorriente, cuentaCorriente.getTitulares());
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
    
    @Override
    public String getTipo() {
        return "corrienteEmpresa";
    }
    
}
