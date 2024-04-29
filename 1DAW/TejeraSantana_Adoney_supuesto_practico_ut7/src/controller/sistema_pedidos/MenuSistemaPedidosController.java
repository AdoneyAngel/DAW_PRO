
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos;

import controller.InicioController;
import controller.sistema_pedidos.add_comanda.MesasAddComandaController;
import controller.sistema_pedidos.finalizar_pedido.FinalizarPedidoController;
import controller.sistema_pedidos.finalizar_pedido.MesasFinalizarPedidoController;
import controller.sistema_pedidos.nuevo_pedido.MesasNuevoPedidoController;
import java.awt.event.ActionEvent;
import view.pedidos.PedidosMenuView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MenuSistemaPedidosController {
    
    private PedidosMenuView pedidosMenuView;
    
    public MenuSistemaPedidosController() {
        this.generarVentana();
    }
    
    private void generarVentana() {
        this.pedidosMenuView = new PedidosMenuView();
        this.pedidosMenuView.setTitle("Sistema de pedidos");
        
        pedidosMenuView.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioController inicioController = new InicioController();
                
                destruirVentana();
            }
        });
        
        pedidosMenuView.getBtnNuevoPedido().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesasNuevoPedidoController mesasNuevoPedidoController = new MesasNuevoPedidoController();
                
                destruirVentana();
            }
        });
        
        pedidosMenuView.getBtnAddComanda().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesasAddComandaController mesasNuevoPedidoController = new MesasAddComandaController();
                
                destruirVentana();
            }
        });
        pedidosMenuView.getBtnFinalizarPedido().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MesasFinalizarPedidoController();
                destruirVentana();
            }
        });
        
        pedidosMenuView.setVisible(true);
        pedidosMenuView.setResizable(false);
        pedidosMenuView.setLocationRelativeTo(null);
        pedidosMenuView.setVisible(true);
        
    }
    
    private void destruirVentana() {
        this.pedidosMenuView.dispose();
    }
    
    
}
