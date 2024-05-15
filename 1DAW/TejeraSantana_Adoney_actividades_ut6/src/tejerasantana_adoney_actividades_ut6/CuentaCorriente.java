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
public class CuentaCorriente extends CuentaBancaria{
    private List<Persona> titulares;
    
    public CuentaCorriente (CuentaBancaria cuenta, List<Persona> titulares) {
        super(cuenta.getTitular(), cuenta.getSaldo(), cuenta.getNumeroCuenta());
        this.titulares = titulares;
    }

    public List<Persona> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Persona> titulares) {
        this.titulares = titulares;
    }
    
    
}
