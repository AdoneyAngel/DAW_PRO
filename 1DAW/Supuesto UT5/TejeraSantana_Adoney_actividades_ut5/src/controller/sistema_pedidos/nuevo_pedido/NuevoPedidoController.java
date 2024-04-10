/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sistema_pedidos.nuevo_pedido;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import view.pedidos.PedidosProductosView;

/**
 *
 * @author AdoneyDAW
 */
public class NuevoPedidoController {
    private AddProductosModel productosModel;
    private PedidosProductosView pedidosProductosView;
    
    public NuevoPedidoController(int idMesa) {
        this.productosModel = new AddProductosModel();
    }
    
    public void destruirVentana() {
        this.pedidosProductosView.setVisible(false);
    }
    
    public void generarVentana(DefaultTableModel vaciaTablaModel, String[] categoriasBotones) {
        
        
        
    }
}
