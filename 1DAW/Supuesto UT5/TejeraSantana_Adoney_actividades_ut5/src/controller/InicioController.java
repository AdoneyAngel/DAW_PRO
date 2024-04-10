/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.administracion.MenuAdministracionController;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import javax.swing.JButton;
import view.InicioView;

/**
 *
 * @author AdoneyDAW
 */
public class InicioController {

    private static InicioView ventanaPrincipalView;

    public InicioController() {
        ventanaPrincipalView = new InicioView();
        generarVentana();
    }

    public static void destruirVentana() {
        ventanaPrincipalView.setVisible(false);

    }

    public static void generarVentana() {
        JButton btnAdministracion = ventanaPrincipalView.getBtnAdministracionDelSistema();
        JButton btnSistemaPedidos = ventanaPrincipalView.getBtnInicioDelSistema();

        btnAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
                destruirVentana();
            }
        });

        btnSistemaPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSistemaPedidosController menuPedidos = new MenuSistemaPedidosController();
                destruirVentana();
            }
        });

        ventanaPrincipalView.setVisible(true);

    }

    public static void main(String[] args) {
        InicioController inicioController = new InicioController();
    }
}
