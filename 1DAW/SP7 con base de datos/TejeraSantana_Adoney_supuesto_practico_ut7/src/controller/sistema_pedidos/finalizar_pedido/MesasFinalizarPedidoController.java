/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.finalizar_pedido;

import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosMesasView;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasFinalizarPedidoController {
    private PedidosMesasView view;
    private MesasModel mesasModel;
    private AddProductosModel productosModel;
    
    public MesasFinalizarPedidoController() {
        this.mesasModel = new MesasModel();
        this.productosModel = new AddProductosModel();
        
        this.generarVentana();
    }
    public void generarVentana() {
        List<String[]> mesasOcupadas = this.mesasModel.getMesasOcupadas();
        String[] nombreMesas = this.obtenerNombreMesas(mesasOcupadas);
        
        this.view = new PedidosMesasView(nombreMesas);
        
        this.ocultarLbl();
        
        this.view.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MenuSistemaPedidosController();
                destruirVentana();
            }
        });
        
        Component[] botones = this.view.getMesasButtons();
        
        for (Component botonActual : botones) {
             JButton JButtonActual = (JButton) botonActual;
             
             String nombreMesa = JButtonActual.getText();
             int idMesa = this.obtenerIdMesaPorNombre(nombreMesa);
             int idPedido = this.obtenerIdPedido(idMesa);
             
             JButtonActual.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    new FinalizarPedidoController(idPedido);
                    destruirVentana();
                }
            });
        }
        
        if (nombreMesas.length == 0) {
            this.view.getLblNoMesas().setVisible(true);
        }
        
        this.view.setVisible(true);
    }
    
    public void destruirVentana() {
        this.view.dispose();
    }
    
    private int obtenerIdPedido(int idMesa) {
        for (String[] pedidoActual : this.productosModel.getPedidos()) {
            if (Integer.parseInt(pedidoActual[4]) == idMesa) {
                return Integer.parseInt(pedidoActual[0]);
            }
        }
        
        return -1;
    }
    
    private int obtenerIdMesaPorNombre(String nombre) {
        for (String[] mesaActual : this.mesasModel.getMesasOcupadas()) {
            if (mesaActual[1].equals(nombre)) {
                return Integer.parseInt(mesaActual[0]);
            }
        }
        
        return -1;
    }
    
    private String[] obtenerNombreMesas(List<String[]> mesas) {
        String[] nombres = new String[mesas.size()];
        
        for (int a = 0; a<mesas.size(); a++) {
            String[] mesaActual = mesas.get(a);
            
            nombres[a] = mesaActual[1];
        }
        
        return nombres;
    }
    
    private void ocultarLbl() {
        this.view.getLblNoMesas().setVisible(false);
    }
}
