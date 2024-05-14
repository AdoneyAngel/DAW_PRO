/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej1;

/**
 *
 * @author AdoneyDAW
 */
public class Nota {
    protected int id;
    protected String nota;
    
    public Nota (int id) {
        this.id = id;
        this.nota = "";
    }
    public Nota (int id, String nota) {
        this.id = id;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    public String toString () {
        return String.valueOf(this.id) + ": " + this.nota;
    }
}
