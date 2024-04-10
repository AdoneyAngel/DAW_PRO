/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.administracion.gestion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.administracion.gestion.GestionProductosModel;
import view.administracion.gestion.AdministracionProductosView;

/**
 *
 * @author AdoneyDAW
 */
public class GestionProductosController {
    private AdministracionProductosView administracionProductosView;
    private GestionProductosModel model;

    public GestionProductosController() {
        this.model = new GestionProductosModel();
    }
    
    public void generarVentana(DefaultTableModel productosTablaModel, DefaultComboBoxModel<String> categoriaComboBoxModelNew, DefaultComboBoxModel<String> categoriaComboBoxModelEdit) {
        this.administracionProductosView = new AdministracionProductosView();
        
        // NO SE COMO PASAR LOS MODELOS Y TABLAS A LA VISTA SI LA VISTA NO TIENE FUNCIONES
    }
}
