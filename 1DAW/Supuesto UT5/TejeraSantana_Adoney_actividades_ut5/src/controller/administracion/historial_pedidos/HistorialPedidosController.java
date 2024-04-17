
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
        
        
        this.view.getBtnDetalles().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int pedidoSeleccionado = getPedidoSeleccionado();
                
                if (pedidoSeleccionado >= 0) {
                    DetallesPedidoController detallesPedidoController = new DetallesPedidoController(pedidoSeleccionado);
                    destruirVentana();                    
                }
            }
        });
        
        this.view.setVisible(true);
    }
    
    public void destruirVentana() {
        this.view.setVisible(false);
    }
    
    private int getPedidoSeleccionado() {
        int filaRow = this.view.getTable().getSelectedRow();
        
        if (filaRow >= 0) {
            int idPedido = Integer.parseInt((String) this.view.getTable().getValueAt(filaRow, 0));
            return idPedido;
        }
        
        return -1;
    }
}
