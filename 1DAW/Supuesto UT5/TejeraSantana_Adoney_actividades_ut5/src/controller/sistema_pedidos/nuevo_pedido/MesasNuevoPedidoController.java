/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.nuevo_pedido;

import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosMesasView;

/**
 *
 * @author AdoneyDAW
 */
public class MesasNuevoPedidoController {
    
    private MesasModel mesas;
    private PedidosMesasView pedidosMesasView;
    
    public MesasNuevoPedidoController() {
        this.mesas = new MesasModel();
        this.generarVentana();
    }
    
    public void destruirVentana() {
        this.pedidosMesasView.setVisible(false);
    }
    
    public void generarVentana() {
        String[] mesasDisponibles = this.mesas.getNombreMesasDisponibles();
        
        this.pedidosMesasView = new PedidosMesasView(mesasDisponibles);
        
        pedidosMesasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSistemaPedidosController menuSistemaPedidosController = new MenuSistemaPedidosController();
                destruirVentana();
            }
        });
        
        pedidosMesasView.setVisible(true);
        
    }
}
