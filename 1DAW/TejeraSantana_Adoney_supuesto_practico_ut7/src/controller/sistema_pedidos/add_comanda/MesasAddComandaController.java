
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.add_comanda;

import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;
import view.pedidos.PedidosMesasView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasAddComandaController {
    
    
    private MesasModel mesasModel;
    private List<String> nombreMesasOcupadas;
    private PedidosMesasView pedidosMesasView;
    private AddProductosModel productosModel;
    
    public MesasAddComandaController() {
        this.mesasModel = new MesasModel();
        this.productosModel = new AddProductosModel();
        
        //Se obtiene los nombres de mesas ocupadas
        List<String[]> mesas = this.mesasModel.getMesasOcupadas();
        List<String> nombreMesasOcupadas = new ArrayList();
        
        for (String[] mesaActual : mesas) {
            if (!mesaActual[2].isBlank()) {
                nombreMesasOcupadas.add(mesaActual[1]);
            }
        }
        
        this.nombreMesasOcupadas = nombreMesasOcupadas;
        
        this.generarVentana(nombreMesasOcupadas.toArray(new String[nombreMesasOcupadas.size()]));
    }
    
    public void generarVentana(String[] mesasBotonesOcupadas) {
        this.pedidosMesasView = new PedidosMesasView(mesasBotonesOcupadas);
        this.pedidosMesasView.setTitle("Mesas");
        
        if (this.nombreMesasOcupadas.size() <= 0) {
            this.pedidosMesasView.getLblNoMesas().setVisible(true);
            
        } else {
            this.pedidosMesasView.getLblNoMesas().setVisible(false);
        }
        
        this.pedidosMesasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSistemaPedidosController menuSistemaPedidosController = new MenuSistemaPedidosController();
                
                destruirVentana();
            }
        });
        
        //RECORRE TODOS SUS BOTONES Y SE AÑADE SU MÉTODO
        Component[] mesaBotones = this.pedidosMesasView.getMesasButtons();
        
        for (Component componenteActual : mesaBotones) {
            JButton botonActual = (JButton) componenteActual;
            
            botonActual.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String nombreMesa = botonActual.getText();
                    String[] pedidoDeMesa = buscarMesaPedido(nombreMesa);
                    
                    int idPedido = Integer.parseInt(pedidoDeMesa[0]);
                    
                    AddComandaController addComandaController = new AddComandaController(idPedido);
                    
                    destruirVentana();
                }
            });
        }
        
        this.pedidosMesasView.setVisible(true);
        
    }
    
    public void destruirVentana() {
        this.pedidosMesasView.dispose();
    }
    
    private String[] buscarMesaPedido(String nombreMesa) {
        List<String[]> pedidos = this.productosModel.getPedidos();
        String[] mesaOriginal = this.buscarMesa(nombreMesa);
        
        for (String[] pedidoActual : pedidos) {
            
            if (pedidoActual[3].equals(mesaOriginal[0])) {
                return pedidoActual;
            }
            
        }
        
        return null;
    }
    
    private String[] buscarMesa(String nombreMesa) {
        List<String[]> mesas = this.mesasModel.getMesas();
        
        for (String[] mesaActual : mesas) {
            if (mesaActual[1].equals(nombreMesa)) {
                return mesaActual;
            }
        }
        
        return null;
    }
}
