/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sistema_pedidos;

/**
 *
 * @author AdoneyDAW
 */
public class MesasModel {
    
    private String[][] mesas = {{"1", "Mesa 1"}, {"2", "Mesa 2"}, {"3", "Mesa A"}, {"4", "Mesa B"}, {"5", "Mesa Calle"}};
    private int[] mesasOcupadas = {1, 3};
    
    public MesasModel() {
        
    }
    
    public String[] getNombreMesasDisponibles() {
        String[] nombreMesas = new String[this.mesas.length - this.mesasOcupadas.length];
        
        int index = 0;
        
        for (String[] mesa : this.mesas) {
            int idMesa = Integer.parseInt(mesa[0]);
            
            if (!ocupado(idMesa)) {
                nombreMesas[index] = mesa[1];
                index++;
            }
            
        }
        
        return nombreMesas;
    }
    
    public String[] getNombreMesasOcupadas() {
        String[] nombreMesasOcupadas = new String[this.mesasOcupadas.length];
        
        int index = 0;
        
        for (String[] mesa : this.mesas) {
            int mesaId = Integer.parseInt(mesa[0]);
            
            if (ocupado(mesaId)) {
                nombreMesasOcupadas[index] = mesa[1];
                index++;
            }
        }
        
        return nombreMesasOcupadas;
    }
    
    private boolean ocupado(int idMesa) {
        for (int idMesaActual : this.mesasOcupadas) {
            if (idMesaActual == idMesa) {
                return true;
            }
        }
        
        return false;
    }
}
