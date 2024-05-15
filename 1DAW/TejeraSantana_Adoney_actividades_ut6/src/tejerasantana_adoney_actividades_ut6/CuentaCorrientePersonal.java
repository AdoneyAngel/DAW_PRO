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
public class CuentaCorrientePersonal extends CuentaCorriente {
    private double comisionMantenimiento;
    
    public CuentaCorrientePersonal (CuentaCorriente cuentaCorriente, double comisionMantenimiento) {
        super(cuentaCorriente, cuentaCorriente.getTitulares());
        this.comisionMantenimiento = comisionMantenimiento;
    }

    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }
    
    @Override
    public String getTipo() {
        return "corrientePersonal";
    }
    
    
}
