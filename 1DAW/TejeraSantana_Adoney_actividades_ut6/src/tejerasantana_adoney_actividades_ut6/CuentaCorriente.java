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
    private List<String> titulares;
    
    public CuentaCorriente (CuentaBancaria cuenta, List<String> titulares) {
        super(cuenta.getTitular(), cuenta.getSaldo(), cuenta.getIBAN());
        this.titulares = titulares;
    }

    public List<String> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<String> titulares) {
        this.titulares = titulares;
    }
    
    
}
