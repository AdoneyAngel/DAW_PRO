
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.administracion.historial_pedidos;

import controller.administracion.MenuAdministracionController;
import javax.swing.table.DefaultTableModel;
import model.administracion.historial_pedidos.HistorialPedidosModel;
import view.administracion.historial.AdministracionHistorialView;

/**
 *
 * @author AdoneyDAW
 */
public class HistorialPedidosController {
    private AdministracionHistorialView view;
    private HistorialPedidosModel model;
    
    public HistorialPedidosController() {
        this.model = new HistorialPedidosModel();
        
        this.generarVentana(this.model.getModelPedidos());
    }
    
    public void generarVentana(DefaultTableModel pedidosTablaModel) {
        this.view = new AdministracionHistorialView();
        this.view.getTable().setModel(pedidosTablaModel);
        
        //Métodos
        this.view.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MenuAdministracionController();
                destruirVentana();
            }
        });
        
        this.view.setVisible(true);
    }
    
    public void destruirVentana() {
        this.view.setVisible(false);
    }
}
