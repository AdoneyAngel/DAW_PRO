
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller;

import controller.administracion.PassAdministracionController;
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
                PassAdministracionController inicioSesionView = new PassAdministracionController();
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
