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
    
    public boolean abrirCuenta (CuentaBancaria cuenta) {
        return this.cuentas.add(cuenta);
    }
    
    public String[] listadoCuentas () {
        String[] listado = new String[this.cuentas.size()];
        
        int listadoIndex = 0;
        
        for (CuentaBancaria cuentaActual : this.cuentas) {
            String datoActual = cuentaActual.devolverInfoString();
            
            listado[listadoIndex] = datoActual;
            
            listadoIndex++;
        }
        
        return listado;
    }
    
    public String informacionCuenta(String IBAN) {
        CuentaBancaria cuentaOriginal = this.buscarCuenta(IBAN);
        
        if (cuentaOriginal != null) {
            return cuentaOriginal.devolverInfoString();
            
        } else {
            return null;
        }
    }
    
    public boolean ingresoCuenta(String IBAN, double cantidad) {
        CuentaBancaria cuentaOriginal = this.buscarCuenta(IBAN);
        
        if (cuentaOriginal != null) {
            cuentaOriginal.setSaldo(cuentaOriginal.getSaldo() + cantidad);
            
            return true;
            
        } else {
            return false;
        }
    }
    
    public boolean retiradaCuenta (String IBAN, double cantidad) {
        CuentaBancaria cuentaOriginal = this.buscarCuenta(IBAN);
        
        if (cuentaOriginal != null) {
            if (cuentaOriginal.getSaldo() >= cantidad) {
                cuentaOriginal.setSaldo(cuentaOriginal.getSaldo()-cantidad);
                
                return true;
                
            }
        }
        
        return false;
    }
    
    public double obtenerSaldo (String IBAN) {
        CuentaBancaria cuentaOriginal = this.buscarCuenta(IBAN);
        
        if (cuentaOriginal != null) {
            return cuentaOriginal.getSaldo();
            
        } else {
            return -1;
        }
    }
    
    public String generarIBAN() {
        boolean numeroValido = false;
        String nuevoNumeroStr = "";
        
        while (!numeroValido) {
            numeroValido = true;
            nuevoNumeroStr = "";
            
            for (int digitIndex = 0; digitIndex<20; digitIndex++) {
                int nuevoDigito = this.rand.nextInt(0, 9);
                nuevoNumeroStr += String.valueOf(nuevoDigito);
            }
            
            numeroValido = !this.existeIBAN(nuevoNumeroStr);
        }
        
        return nuevoNumeroStr;
    }
    
    public boolean existeIBAN(String numeroCuenta) {
        for (CuentaBancaria cuentaActual : this.cuentas) {
            if (cuentaActual.getIBAN().equals(numeroCuenta)) {
                return true;
            }
        }
        
        return false;
    }
    
    private CuentaBancaria buscarCuenta(String IBAN) {
        for (CuentaBancaria cuentaActual : this.cuentas) {
            if (cuentaActual.getIBAN().equals(IBAN)) {
                return cuentaActual;
            }
        }
        
        return null;
    }
    
    
}
