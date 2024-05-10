/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.ej1;

/**
 *
 * @author AdoneyDAW
 */
public class NotaAlarma extends Nota {
    private String fechaAlarma;
    
    public NotaAlarma (Nota nota) {
        super(nota.getId(), nota.getNota());
        
        this.fechaAlarma = "";
    }
    public NotaAlarma (Nota nota, String fecha) {
        super(nota.getId(), nota.getNota());
        this.fechaAlarma = fecha;
    }
    public NotaAlarma (int id, String nota, String fecha) {
        super(id, nota);
        this.fechaAlarma = fecha;
    }

    public String getFechaAlarma() {
        return fechaAlarma;
    }

    public void setFechaAlarma(String fechaAlarma) {
        this.fechaAlarma = fechaAlarma;
    }
    
    public String toString () {
        return String.valueOf(this.id) + ": " + this.nota + " -> " + this.fechaAlarma;
    }
    
    
}
