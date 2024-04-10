/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos;

import controller.InicioController;
import controller.sistema_pedidos.nuevo_pedido.MesasNuevoPedidoController;
import view.pedidos.PedidosMenuView;

/**
 *
 * @author AdoneyDAW
 */
public class MenuSistemaPedidosController {
    
    private PedidosMenuView pedidosMenuView;
    
    public MenuSistemaPedidosController() {
        this.generarVentana();
    }
    
    private void generarVentana() {
        this.pedidosMenuView = new PedidosMenuView();
        
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
        
        pedidosMenuView.setVisible(true);
        
    }
    
    private void destruirVentana() {
        this.pedidosMenuView.setVisible(false);
    }
    
    
}
