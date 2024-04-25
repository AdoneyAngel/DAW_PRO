//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney
package controller.administracion.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.table.DefaultTableModel;
import controller.administracion.MenuAdministracionController;

import view.administracion.gestion.AdministracionMesasView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionMesasController {

    private static AdministracionMesasView view = null;

    public GestionMesasController() {
        generarVentana();
    }

    public static void generarVentana() {
        view = new AdministracionMesasView();
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.getBtnAtras().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruirVentana();

                new MenuAdministracionController();
            }
        });

        view.getBtnEliminarseleccion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getBtnCrearmesa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getBtnEditarmesa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        view.setTitle("Sistema de pedidos");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setVisible(true);

    }

    public static void destruirVentana() {
        view.dispose();
    }
}
