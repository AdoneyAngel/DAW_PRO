/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.nuevo_pedido;

import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosMesasView;
import view.pedidos.PedidosProductosView;

/**
 *
 * @author AdoneyDAW
 */
public class MesasNuevoPedidoController {
    
    private MesasModel mesas;
    private PedidosMesasView pedidosMesasView;
    private AddProductosModel productosModel;
    
    public MesasNuevoPedidoController() {
        this.mesas = new MesasModel();
        this.generarVentana();
    }
    
    public void destruirVentana() {
        this.pedidosMesasView.setVisible(false);
    }
    
    public void generarVentana() {
        this.productosModel = new AddProductosModel();
        List<String[]> categorias = this.productosModel.getCategorias();
        
        String[] mesasDisponibles = this.mesas.getNombreMesasDisponibles();
        this.pedidosMesasView = new PedidosMesasView(mesasDisponibles);
        
        pedidosMesasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSistemaPedidosController menuSistemaPedidosController = new MenuSistemaPedidosController();
                destruirVentana();
            }
        });
        
        
        //SE RECORRE TODOS LOS BOTONES Y SE SE AÑADE SU MÉTODO
        Component[] botonesGenerados = pedidosMesasView.getMesasButtons();
        
        for (Component botonActual : botonesGenerados) {
            JButton botonActualBtn = (JButton) botonActual;
            String nombreBoton = botonActualBtn.getText();
            
            botonActualBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                    int idCategoria = getIdCategoriaNombre(nombreBoton);
                    NuevoPedidoController nuevoPedidoController = new NuevoPedidoController(idCategoria);
                    
                    destruirVentana();
                }
            });
        }
        
        if (botonesGenerados.length > 0) {
            this.pedidosMesasView.getLblNoMesas().setVisible(false);
        }
        
        pedidosMesasView.setVisible(true);
        
    }
    
    private int getIdCategoriaNombre(String nombre) {
        Iterator<String[]> categoriasIt = this.productosModel.getCategorias().iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[1].equals(nombre)) {
                return Integer.parseInt(categoriaActual[0]);
            }
        }
        
        return -1;
    }
}
