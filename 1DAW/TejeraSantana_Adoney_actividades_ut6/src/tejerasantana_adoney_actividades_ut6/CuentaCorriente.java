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
public abstract class CuentaCorriente extends CuentaBancaria{
    protected List<String> titulares;
    
    public CuentaCorriente (Persona titular, double saldo, String IBAN, List<String> titulares) {
        super(titular, saldo, IBAN);
        this.titulares = titulares;
    }

    public List<String> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<String> titulares) {
        this.titulares = titulares;
    }
    
    @Override
    public abstract String getTipo ();

    @Override
    public abstract String devolverInfoString ();
    
}
