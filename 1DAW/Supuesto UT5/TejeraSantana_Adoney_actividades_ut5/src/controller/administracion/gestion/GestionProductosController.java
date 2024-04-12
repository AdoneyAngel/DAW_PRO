/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.administracion.gestion;

import controller.administracion.MenuAdministracionController;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.administracion.gestion.GestionCategoriasModel;
import model.administracion.gestion.GestionProductosModel;
import view.administracion.gestion.AdministracionProductosView;

/**
 *
 * @author AdoneyDAW
 */
public class GestionProductosController {
    private AdministracionProductosView administracionProductosView;
    private GestionProductosModel model;
    private GestionCategoriasModel categoriasModel;

    public GestionProductosController() {
        this.model = new GestionProductosModel();
        this.categoriasModel = new GestionCategoriasModel();
        
        DefaultTableModel tableModel = this.model.getModelProductos();
        DefaultComboBoxModel comboModel = this.model.getComboBoxModelCategorias();
        
        generarVentana(tableModel, comboModel, comboModel);
    }
    
    public void generarVentana(DefaultTableModel productosTablaModel, DefaultComboBoxModel<String> categoriaComboBoxModelNew, DefaultComboBoxModel<String> categoriaComboBoxModelEdit) {
        this.administracionProductosView = new AdministracionProductosView();
        this.ocultarLbl();
        
        //Modelos
        
        this.administracionProductosView.getTableProductos().setModel(productosTablaModel);
        this.administracionProductosView.getComboCrear().setModel(categoriaComboBoxModelNew);
        this.administracionProductosView.getComboEditar().setModel(categoriaComboBoxModelNew);
        
        //Métodos de botones
        
        this.administracionProductosView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
                destruirVentana();
            }
        });
        
        this.administracionProductosView.getBtnCrearProduct().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                
                String nombreProducto = administracionProductosView.getFieldNombreCrear().getText();
                String precioProductoStr = administracionProductosView.getFieldPrecioCrear().getValue().toString();
                
                String categoriaNombre = (String) administracionProductosView.getComboCrear().getSelectedItem();
                int categoriaId = getCategoriaId(categoriaNombre);
                int nuevoId = generarProductoId();
                
                //Validando si el precio añadido es válido
                
                double precioProducto = precioValido(precioProductoStr);
                
                if (precioProducto < 0) {
                    administracionProductosView.getLblCrearPrecioInvalido().setVisible(true);
                    
                } else if (existeNombre(nombreProducto)) {
                    administracionProductosView.getLblNombreExiste().setVisible(true);
                    
                } else if (categoriaNombre.isEmpty() || categoriaNombre.isBlank()) {
                    administracionProductosView.getLblCrearProductoVacio().setVisible(true);
                    
                } else {  
                    model.insertarProducto(nuevoId, nombreProducto, precioProducto, categoriaId);
                }
                
                actualizarTablaProductos();
               
            }
        });
        
        this.administracionProductosView.getBtnEditarProducto().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                
                int rowIndex = administracionProductosView.getTableProductos().getSelectedRow();
                
                String nombreProducto = administracionProductosView.getFieldNombreEditar().getText();
                String categoriaNombre = administracionProductosView.getComboEditar().getSelectedItem().toString();
                String precioProductoStr = administracionProductosView.getFieldPrecioEditar().getValue().toString();
                int categoriaId = getCategoriaId(categoriaNombre);
                
                //Validando si el precio añadido es válido
                
                double precioProducto = precioValido(precioProductoStr);
                
                if (rowIndex < 0) {
                    administracionProductosView.getLblNoSeleccion().setVisible(true);
                    
                }else if (precioProducto <= 0) {
                    administracionProductosView.getLblCrearPrecioInvalido().setVisible(true);
                    
                } else if (existeNombre(nombreProducto)) {
                    administracionProductosView.getLblNombreExiste().setVisible(true);
                    
                } else if (nombreProducto.isBlank() || nombreProducto.isEmpty() || precioProducto <= 0) {
                    administracionProductosView.getLblEditarProductoVacio().setVisible(true);
                    
                } else {
                    int idProducto =  Integer.parseInt(administracionProductosView.getTableProductos().getValueAt(rowIndex, 0).toString());
                    
                    model.actualizarProducto(idProducto, nombreProducto, precioProducto, categoriaId);
                    
                }
                
                actualizarTablaProductos();
               
            }
        });
        
        this.administracionProductosView.getBtnEliminarSeleccion().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                
                int rowIndex = administracionProductosView.getTableProductos().getSelectedRow();
                
                if (rowIndex < 0) {
                    administracionProductosView.getLblNoSeleccion().setVisible(true);
                
                } else {
                    int productoId = Integer.parseInt(administracionProductosView.getTableProductos().getValueAt(rowIndex, 0).toString());
                    
                    model.eliminarProducto(productoId);
                    
                    actualizarTablaProductos();
                }
            }
        });
        
        this.administracionProductosView.setVisible(true);
        
    }
    
    public void destruirVentana() {
        this.administracionProductosView.setVisible(false);
    }
    
    private double precioValido(String precio) {
        try {
            double precioDouble = Double.parseDouble(precio);
            return precioDouble;
            
        } catch (NumberFormatException error) {
            return -1;
        }
    }
    
    private int getCategoriaId(String nombre) {
        List<String[]> categorias = this.categoriasModel.getCategorias();
        
        for (String[] categoriaActual : categorias) {
            if (categoriaActual[1].equals(nombre)) {
                return Integer.parseInt(categoriaActual[0]);
                
            }
        }
        
        return -1;
    }
    
    private void actualizarTablaProductos() {
        DefaultTableModel nuevoTableModel = this.model.getModelProductos();
        this.administracionProductosView.getTableProductos().setModel(nuevoTableModel);
    }
    
    private void ocultarLbl() {
        this.administracionProductosView.getLblCrearProductoVacio().setVisible(false);
        this.administracionProductosView.getLblEditarProductoVacio().setVisible(false);
        this.administracionProductosView.getLblNombreExiste().setVisible(false);
        this.administracionProductosView.getLblCrearPrecioInvalido().setVisible(false);
        this.administracionProductosView.getLblEditarPrecioInvalido().setVisible(false);
        this.administracionProductosView.getLblNoSeleccion().setVisible(false);
    }
    
    private int generarProductoId() {
        List<String[]> productos = this.model.getProductos();
        
        int nuevoId = -1;
        boolean idValido = false;
        
        while (!idValido || nuevoId < 0) {
            idValido = true;
            nuevoId++;
            
            for (String[] productoActual : productos) {
                if (Integer.parseInt(productoActual[0]) == nuevoId) {
                    idValido = false;
                }
            }
        }
        
        return nuevoId;
        
    }
    
    private boolean existeNombre(String nombre) {
        List<String[]> productos = this.model.getProductos();
        
        for (String[] productoActual : productos) {
            if (productoActual[1].equals(nombre)) {
                return true;
            }
        }
        
        return false;
    }
}
