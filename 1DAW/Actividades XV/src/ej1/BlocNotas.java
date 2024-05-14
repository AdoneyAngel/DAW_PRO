/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author AdoneyDAW
 */
public class BlocNotas {
    private Set<Nota> notas;
    
    public BlocNotas () {
        this.notas = new HashSet();
    }
    public BlocNotas (Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }
    
    public int getCantidad() {
        return this.notas.size();
    }
    
    public void addNota (NotaAlarma nota) {
        this.notas.add(nota);
    }
    
    public void delNota (int index) {
        Iterator<Nota> notasIt = this.notas.iterator();
        
        int whileIndex = 0;
        
        while (notasIt.hasNext()) {
            Nota notaActual = notasIt.next();
            
            if (whileIndex == index) {
                this.notas.remove(notaActual);
                break;
            }
            
            
            whileIndex++;
        }
    }
    
    public void listar () {
        for (Nota notaActual : this.notas) {
            System.out.println(notaActual.toString()); 
        }
    }
}
