/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdoneyDAW
 */
public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = new ArrayList();
        
        Cuadrado miCuadrado = new Cuadrado(10, 10);
        Triangulo miTriangulo = new Triangulo(10, 10);
        Circulo miCirculo = new Circulo(10);
        
        figuras.add(miCuadrado);
        figuras.add(miTriangulo);
        figuras.add(miCirculo);
        
        for (Figura figuraActual : figuras) {
            System.out.println(String.valueOf(figuraActual.area()));
        }
    }
}
