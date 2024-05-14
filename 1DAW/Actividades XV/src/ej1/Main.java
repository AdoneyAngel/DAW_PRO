/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej1;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author AdoneyDAW
 */
public class Main {
    public static void main (String[] args) {
        BlocNotas bloc;
        Set<Nota> notas = new HashSet();
        
        NotaAlarma nota1 = new NotaAlarma(0, "Primera nota", "2024-5-10");
        NotaAlarma nota2 = new NotaAlarma(1, "Segunda nota", "2024-5-16");
        NotaAlarma nota3 = new NotaAlarma(2, "Tercera nota", "2024-7-13");
        Nota nota4 = new Nota(3, "Cuarta nota");
        
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);
        notas.add(nota4);
        
        bloc = new BlocNotas(notas);
        
        bloc.delNota(0);
        
        bloc.listar();
    }
}
