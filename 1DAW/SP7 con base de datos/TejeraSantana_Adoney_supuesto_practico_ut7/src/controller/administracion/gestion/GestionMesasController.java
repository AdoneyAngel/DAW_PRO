//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney
package controller.administracion.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.administracion.MenuAdministracionController;
import java.awt.event.MouseEvent;
import java.util.Objects;
import model.administracion.gestion.GestionMesasModel;

import view.administracion.gestion.AdministracionMesasView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionMesasController {

    private AdministracionMesasView view = null;
    private GestionMesasModel model;

    public GestionMesasController() {
        this.model = new GestionMesasModel();
        
        generarVentana();
    }

    public void generarVentana() {
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
                eliminarMesa();
            }
        });

        view.getBtnCrearmesa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreMesa = view.getCrearField().getText();
                
                insertarMesa(nombreMesa);
            }
        });

        view.getBtnEditarmesa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = view.getEditarField().getText();
                
                editarMesa(nombre);
            }
        });

        view.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    actualizarMesaSeleccionada();
                }
            });
        
        view.getTable().setModel(this.model.getModelMesas());

        view.setTitle("Sistema de pedidos");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setVisible(true);
        
        this.ocultarLbl();

    }

    public void destruirVentana() {
        view.dispose();
    }
    
    private void actualizarMesaSeleccionada() {
        String[] mesaSeleccionada = this.mesaSeleccionada();
        
       this.view.getEditarField().setText(mesaSeleccionada[1]);
    }
    
    private void insertarMesa (String nombre) {
        this.ocultarLbl();
        
        if (this.getMesaPorNombre(nombre) == null) {
            
            if (nombre.length() > 0) {
                int id = this.generarId();

                this.model.insertarMesa(id, nombre);

                this.actualizarTabla();
                
            } else {
                this.view.getLblNoCrear().setVisible(true);
            }
            
        } else {
            this.view.getLblNombreCrear().setVisible(true);
        }
    }
    
    private void eliminarMesa() {
        this.ocultarLbl();
        
        int seleccionIndex = this.view.getTable().getSelectedRow();
        
        if (seleccionIndex != -1) {
            String id = this.view.getTable().getValueAt(seleccionIndex, 0).toString();
            
            this.model.borrarMesa(Integer.parseInt(id));
            
            this.actualizarTabla();
            
        } else {
            this.view.getLblSeleccion().setVisible(true);
        }
    }
    
    private void editarMesa(String nombre) {
        this.ocultarLbl();
        
        int seleccionIndex = this.view.getTable().getSelectedRow();
        
        if (seleccionIndex != -1) {
            String[] mesaOriginal = this.getMesaPorNombre(nombre);
            
            String id = this.view.getTable().getValueAt(seleccionIndex, 0).toString();
            String antiguoNombre = this.view.getTable().getValueAt(seleccionIndex, 1).toString();
            
            if (this.getMesaPorNombre(nombre) == null || antiguoNombre.equals(nombre)) {
                
                if (nombre.length() > 0) {
                    this.model.actualizarMesa(Integer.parseInt(id), nombre);

                    this.actualizarTabla();     
                    
                } else {
                    this.view.getLblNoEditar().setVisible(true);
                }
                
            } else {
                this.view.getLblNombreEditar().setVisible(true);
            }

        } else {
            this.view.getLblSeleccion().setVisible(true);
        }
    }
    
    private String[] mesaSeleccionada () {
        int seleccionIndex = this.view.getTable().getSelectedRow();
        String id = this.view.getTable().getValueAt(seleccionIndex, 0).toString();
        String nombre = this.view.getTable().getValueAt(seleccionIndex, 1).toString();
        
        String[] mesa = {id, nombre};
        
        return mesa;
    }
    
    private void ocultarLbl() {
        this.view.getLblSeleccion().setVisible(false);
        this.view.getLblNombreCrear().setVisible(false);
        this.view.getLblNombreEditar().setVisible(false);
        this.view.getLblNoCrear().setVisible(false);
        this.view.getLblNoEditar().setVisible(false);
    }
    
    private String[] getMesaPorNombre(String nombre) {
        for (String[] mesaActual : this.model.getMesas()) {
            if (mesaActual[1].endsWith(nombre)) {
                return mesaActual;
            }
        }
        
        return null;
    }
    
    private int generarId() {
        int nuevoId = -1;
        boolean idValido = false;
        
        while (!idValido) {
            idValido = true;
            nuevoId++;
            
            for (String[] mesaActual : this.model.getMesas()) {
                if (Integer.parseInt(mesaActual[0]) == nuevoId) {
                    idValido = false;
                }
            }
        }
        
        return nuevoId;
    }
    
    private void actualizarTabla() {
        this.view.getTable().setModel(this.model.getModelMesas());
    }
}
