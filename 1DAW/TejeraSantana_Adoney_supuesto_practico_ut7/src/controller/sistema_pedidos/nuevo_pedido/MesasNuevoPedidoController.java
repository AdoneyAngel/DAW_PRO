
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.nuevo_pedido;

import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosMesasView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasNuevoPedidoController {
    
    private MesasModel mesas;
    private PedidosMesasView pedidosMesasView;
    private AddProductosModel productosModel;
    
    public MesasNuevoPedidoController() {
        this.mesas = new MesasModel();
        this.generarVentana();
    }
    
    public void destruirVentana() {
        this.pedidosMesasView.dispose();
    }
    
    public void generarVentana() {
        this.productosModel = new AddProductosModel();
        List<String> mesasDisponibles = this.mesas.getNombreMesasDisponibles();
        
        String[] mesasDisponiblesArray = new String[mesasDisponibles.size()];
        
        this.pedidosMesasView = new PedidosMesasView(mesasDisponibles.toArray(mesasDisponiblesArray));
        this.pedidosMesasView.setTitle("Mesas");
        
        pedidosMesasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSistemaPedidosController menuSistemaPedidosController = new MenuSistemaPedidosController();
                destruirVentana();
            }
        });
        
        
        //SE RECORRE TODOS LOS BOTONES Y SE SE AÑADE SU MÉTODO
        Component[] botonesGenerados = pedidosMesasView.getMesasButtons();
        
        for (Component botonActual : botonesGenerados) {
            JButton botonActualBtn = (JButton) botonActual;
            String nombreBoton = botonActualBtn.getText();
            
            botonActualBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                    int idMesa = getIdMesaNombre(nombreBoton);
                    NuevoPedidoController nuevoPedidoController = new NuevoPedidoController(idMesa);
                    
                    destruirVentana();
                }
            });
        }
        
        if (botonesGenerados.length > 0) {
            this.pedidosMesasView.getLblNoMesas().setVisible(false);
        }
        
        pedidosMesasView.setVisible(true);
        
    }
    
    private int getIdMesaNombre(String nombre) {
        List<String[]> categorias = this.mesas.getMesas();
        
        for (String[] categoriaActual : categorias) {
            if (categoriaActual[1].equals(nombre)) {
                return Integer.parseInt(categoriaActual[0]);
            }
        }
        
        return -1;
    }
}
