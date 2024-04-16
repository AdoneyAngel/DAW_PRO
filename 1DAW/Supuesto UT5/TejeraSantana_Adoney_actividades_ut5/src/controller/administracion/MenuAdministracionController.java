
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller.administracion;

import controller.InicioController;
import controller.administracion.gestion.GestionCategoriasController;
import controller.administracion.gestion.GestionProductosController;
import controller.administracion.historial_pedidos.HistorialPedidosController;
import view.administracion.AdministracionMenuView;

/**
 *
 * @author AdoneyDAW
 */
public class MenuAdministracionController {
    
    AdministracionMenuView administracionMenuView;
    
    public MenuAdministracionController() {
        this.generarVentana();
    }
    
    public void generarVentana() {
        this.administracionMenuView = new AdministracionMenuView();
        
        this.administracionMenuView.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioController inicioController = new InicioController();
                destruirVentana();
            }
        });
        
        this.administracionMenuView.getBtnGestionarCategorias().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionCategoriasController gestonCategoriasController = new GestionCategoriasController();
                destruirVentana();
            }
        });
        
        this.administracionMenuView.getBtnGestionarProductos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionProductosController gestionProductosController = new GestionProductosController();
                destruirVentana();
            }
        });
        
        this.administracionMenuView.getBtnHistorialPedidos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialPedidosController historialPedidosController = new HistorialPedidosController();
                destruirVentana();
            }
        });
        
        this.administracionMenuView.setVisible(true);
    }
    
    public void destruirVentana() {
        this.administracionMenuView.setVisible(false);
    }
}
