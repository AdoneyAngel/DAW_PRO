
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdoneyDAW
 */
public class MesasModel {
    
    //private String[][] mesas = {{"1", "Mesa 1"}, {"2", "Mesa 2"}, {"3", "Mesa A"}, {"4", "Mesa B"}, {"5", "Mesa Calle"}};
    //MESA: {ID, nombre}
    private List<String[]> mesas;
    private List<String[]> pedidos;
    private AddProductosModel productosModel;
    
    public MesasModel() {
        this.mesas = new ArrayList();
        this.productosModel = new AddProductosModel();
        this.pedidos = this.productosModel.getPedidos();
        
        //Agregando mesas de ejemplo
        String[] mesa1 = {"1", "Mesa 1", "0"};
        String[] mesa2 = {"2", "Mesa 2", "1"};
        String[] mesa3 = {"3", "Mesa A", ""};
        String[] mesa4 = {"4", "Mesa B", ""};
        String[] mesa5 = {"5", "Mesa Calle", ""};
        
        this.mesas.add(mesa1);
        this.mesas.add(mesa2);
        this.mesas.add(mesa3);
        this.mesas.add(mesa4);
        this.mesas.add(mesa5);
    }
    
    public List<String[]> getMesas() {
        return mesas;
    }
    
    public List<String> getNombreMesasDisponibles() {
        List<String> nombreMesas = new ArrayList();
        
        for (String[] mesaActual : this.mesas) {
            int idMesaActual = Integer.parseInt(mesaActual[0]);
            
            if (!ocupado(idMesaActual)) {
                nombreMesas.add(mesaActual[1]);
            }
        }
        
        return nombreMesas;
    }
    
    public List<String> getNombreMesasOcupadas() {
        List<String> nombreMesas = new ArrayList();
        
        for (String[] mesaActual : this.mesas) {
            int idMesaActual = Integer.parseInt(mesaActual[0]);
            
            if (ocupado(idMesaActual)) {
                nombreMesas.add(mesaActual[1]);
            }
        }
        
        return nombreMesas;
    }
    
    private boolean ocupado(int idMesa) {
        for (String[] pedidoActual : this.pedidos) {
            if (pedidoActual[3].equals(String.valueOf(idMesa))) {
                return true;
            }
        }
        
        return false;
    }
    
    
}
