/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Adone
 */
public class Banco {
    private Set<CuentaBancaria> cuentas;
    private Random rand = new Random();
    
    public Banco () {
        this.cuentas = new HashSet();
    }
    
    public Set<CuentaBancaria> getCuentas() {
        return this.cuentas;
    }
    
    public boolean insertarCuenta (CuentaBancaria cuenta) {
        return this.cuentas.add(cuenta);
    }
    
    public String generarNumeroCuenta() {
        boolean numeroValido = false;
        String nuevoNumeroStr = "";
        
        while (!numeroValido) {
            numeroValido = true;
            nuevoNumeroStr = "";
            
            for (int digitIndex = 0; digitIndex<20; digitIndex++) {
                int nuevoDigito = this.rand.nextInt(0, 9);
                nuevoNumeroStr += String.valueOf(nuevoDigito);
            }
            
            numeroValido = !this.existeNumeroCuenta(nuevoNumeroStr);
        }
        
        return nuevoNumeroStr;
    }
    
    public boolean existeNumeroCuenta(String numeroCuenta) {
        for (CuentaBancaria cuentaActual : this.cuentas) {
            if (cuentaActual.getNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        
        return false;
    }
    
    
    
    
}
