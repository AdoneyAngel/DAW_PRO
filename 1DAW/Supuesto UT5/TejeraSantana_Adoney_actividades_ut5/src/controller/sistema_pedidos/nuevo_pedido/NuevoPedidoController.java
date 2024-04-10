/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.nuevo_pedido;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosProductosView;

/**
 *
 * @author AdoneyDAW
 */
public class NuevoPedidoController {
    private AddProductosModel productosModel;
    private PedidosProductosView pedidosProductosView;
    private int categoriaSeleccionada;
    
    public NuevoPedidoController(int idMesa) {
        this.productosModel = new AddProductosModel();
        
        DefaultTableModel tableModel = this.productosModel.getTablaVaciaModel();
        String[] nombreCategorias = productosModel.getNombreCategorias();
        
        generarVentana(tableModel, nombreCategorias);
    }
    
    public void destruirVentana() {
        this.pedidosProductosView.setVisible(false);
    }
    
    public void generarVentana(DefaultTableModel vaciaTablaModel, String[] categoriasBotones) {
        this.pedidosProductosView = new PedidosProductosView(vaciaTablaModel, categoriasBotones);
        List<String[]> categorias = this.productosModel.getCategorias();
        
        //Por defecto se cogerá la primera categoría
        this.categoriaSeleccionada = Integer.parseInt(categorias.get(0)[0]);
        
        //GENERANDO BOTONES Y SUS FUNCIONES
        this.pedidosProductosView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesasNuevoPedidoController mesasNuevoPedidoController = new MesasNuevoPedidoController();
                destruirVentana();
            }
        });
        
        //  Generando categorias
        for (String[] categoriaActual : categorias) {
            String nombreCategoria = categoriaActual[1];
            int idCategoria = Integer.parseInt(categoriaActual[0]);
            
            JButton btnCategoria = new JButton(nombreCategoria);
            btnCategoria.setPreferredSize(new Dimension(160, 40));
            
            //      Método del boton
            btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaSeleccionada = idCategoria;
                
                actualizarBotonesProductos();
            }
            });
            
            
            this.pedidosProductosView.getCategoriaPanel().add(btnCategoria);
        }
        
        this.actualizarBotonesProductos();
        
        this.pedidosProductosView.setVisible(true);
    }
    
    private void actualizarBotonesProductos() {
        this.limpiarBotonesProductos();
        this.pedidosProductosView.getProductoPanel().removeAll();
        
        //  Generando Productos
        String[] CategoriaSeleccionado = this.getCategoria(this.categoriaSeleccionada);
        String[] nombreProductosPorCategoria = this.productosModel.getNombreProductosCategoria(CategoriaSeleccionado[1]);
        
        for (String productoActual : nombreProductosPorCategoria) {
            
            JButton btnProducto = new JButton (productoActual);
            btnProducto.setPreferredSize(new Dimension(160, 40));
            
            this.pedidosProductosView.getProductoPanel().add(btnProducto);
        }
        
        //---------------------
    }
    
    private void limpiarBotonesProductos() {
        JButton[] botones = this.pedidosProductosView.getProductosButtons();
    
        for (JButton botonActual : botones) {
            botonActual.setVisible(false);
        }
    }
    
    private String[] getCategoria(int idCategoria) {
        Iterator<String[]> categoriasIt = this.productosModel.getCategorias().iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (String.valueOf(idCategoria).equals(categoriaActual[0])) {
                return categoriaActual;
            }
        }
        
        return null;
    }
}
