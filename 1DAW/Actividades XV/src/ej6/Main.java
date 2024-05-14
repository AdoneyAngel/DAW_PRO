/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej6;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdoneyDAW
 */
public class Main {
    public static void main (String[] args) {
        List<Legislador> legisladores = new ArrayList<>();
        
        Diputado diputado1 = new Diputado("1", "Dip1", "Aqui");
        Diputado diputado2 = new Diputado("1", "Dip2", "Ahi");
        Senador sen1 = new Senador("1", "Sen2", "Alli");
        
        legisladores.add(diputado1);
        legisladores.add(diputado2);
        legisladores.add(sen1);
        
        for (Legislador legisladorActual : legisladores) {
            System.out.println(legisladorActual.toString() + " -> " + legisladorActual.getCamaraEnQueTrabaja());
        }
    }
}
