/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.finalizar_pedido;

import controller.administracion.MenuAdministracionController;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import view.administracion.gestion.AdministracionMesasView;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasFinalizarPedidoController {
    AdministracionMesasView AdministracionMesasView;
    
    public MesasFinalizarPedidoController() {
        this.generarVentana();
    }
    public void generarVentana() {
        this.AdministracionMesasView = new AdministracionMesasView();
        this.AdministracionMesasView.setVisible(true);
        this.AdministracionMesasView.setResizable(false);
        this.AdministracionMesasView.setLocationRelativeTo(null);
        this.AdministracionMesasView.setTitle("Gestion de mesas");
        this.AdministracionMesasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdministracionController MenuAdministracionController = new MenuAdministracionController();
                destruirVentana();
            }
        });
    }
    public void destruirVentana() {
        this.AdministracionMesasView.setVisible(false);
    }
}
