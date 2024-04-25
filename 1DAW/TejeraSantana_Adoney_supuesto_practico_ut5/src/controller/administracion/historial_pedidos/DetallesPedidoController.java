
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.administracion.historial_pedidos;

import javax.swing.table.TableModel;
import model.administracion.historial_pedidos.DetallesPedidoModel;
import view.administracion.historial.AdministracionDetallesPedidoView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class DetallesPedidoController {
    DetallesPedidoModel model;
    AdministracionDetallesPedidoView view;
    
    public DetallesPedidoController(int idPedido) {
        this.model = new DetallesPedidoModel();
        
        Object[] detallesPedido = this.model.getPropiedadesPedido(idPedido);
        
        boolean enCurso = (boolean) detallesPedido[2];
        String fecha = (String) detallesPedido[1];
        String mesa = (String) detallesPedido[3];
        double precio = (double) detallesPedido[4];
        
        TableModel tableModel = this.model.getDetallesPedidoModel(idPedido);
        
        this.generarVentana(idPedido, enCurso, fecha, mesa, precio, tableModel);
    }
    
    public void generarVentana(int id, boolean enCurso, String fecha, String mesa, double precio, TableModel tablaDetallesPedidoModel) {
        this.view = new AdministracionDetallesPedidoView();
        this.view.setTitle("Detalles del Pedido");
        
        this.view.getTable().setModel(tablaDetallesPedidoModel);
        this.view.getLblId().setText(String.valueOf(id));
        this.view.getLblFinalizado().setText(String.valueOf(!enCurso));
        this.view.getLblFecha().setText(fecha);
        this.view.getLblMesa().setText(mesa);
        this.view.getLblPrecio().setText(String.valueOf(precio));
        
        //Métodos
        this.view.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialPedidosController historialPedidosController = new HistorialPedidosController();
                destruirVentana();
            }
        });
        
        this.view.setVisible(true);
    }
    
    public void destruirVentana() {
        this.view.dispose();
    }
}
