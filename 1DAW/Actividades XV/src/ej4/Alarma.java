/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej4;

import java.util.List;

/**
 *
 * @author AdoneyDAW
 */
public class Alarma {
    private List<Sensor> sensores;
    private List<Timbre> timbres;
    private int umbral;
    
    public Alarma (List<Sensor> sensores, List<Timbre> timbres, int umbral) {
        this.sensores = sensores;
        this.timbres = timbres;
        this.umbral = umbral;
    }
    
    public void comprobar() {
        boolean activar = false;
        
        for (Sensor sensorActual : this.sensores) {
            if (sensorActual.getValorActual() >= this.umbral) {
                activar = true;
                
            }
        }
        
        if (activar) {
            for (Timbre timbreActual : this.timbres) {
                timbreActual.activar();
            }
            
        } else {
            for (Timbre timbreActual : this.timbres) {
                timbreActual.desActivar();
            }
        }
    }
    
}
