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
    
    public CuentaCorrientePersonal (Persona titular, double saldo, String IBAN, List<String> titulares, double comisionMantenimiento) {
        super(titular, saldo, IBAN, titulares);
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

    @Override
    public String devolverInfoString() {
        return "Cuenta corriente personal -> " + this.infoBasica() + " | Comision mantenimiento: " + String.valueOf(this.comisionMantenimiento) + " | Titulares permitidos: " + this.titulares.toString();
    }
    
}
