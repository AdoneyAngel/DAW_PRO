/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdoneyDAW
 */
public class Main {
    public static void main (String[] args) {
        List<Sensor> sensores = new ArrayList<>();
        List<Timbre> timbres = new ArrayList<>();
        
        Sensor sensor1 = new Sensor(0);        
        Sensor sensor2 = new Sensor(1);
        
        Timbre timbre = new Timbre();
        
        sensores.add(sensor1);
        sensores.add(sensor2);
        
        timbres.add(timbre);
        
        Alarma alarma = new Alarma(sensores, timbres, 4);
        
        for (int a = 0; a<10; a++) {
            sensor1.setValorActual(a);
            alarma.comprobar();
        }
        
    }
}
