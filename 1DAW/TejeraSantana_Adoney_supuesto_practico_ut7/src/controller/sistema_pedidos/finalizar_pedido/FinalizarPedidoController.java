
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.finalizar_pedido;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import view.pedidos.PedidosFinalizarView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.FinalizarPedidoModel;
import model.sistema_pedidos.MesasModel;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class FinalizarPedidoController {
    private PedidosFinalizarView view = null;
    private AddProductosModel productosModel;
    private MesasModel mesasModel;
    private FinalizarPedidoModel model;
    private int idPedido;
    private float pagado;
    private float devuelto;
    private float precio;
    
    public FinalizarPedidoController(int idPedido){
        this.mesasModel = new MesasModel();
        this.model = new FinalizarPedidoModel();
        this.productosModel = new AddProductosModel();
        this.idPedido = idPedido;
        
        String[] pedidoOriginal = this.getPedido(this.idPedido);
        this.precio = Float.parseFloat(pedidoOriginal[1].replace(",", "."));
        
        GenerarVentana();
    }
    
    public void GenerarVentana(){
        String[] pedidoOriginal = this.getPedido(this.idPedido);
        int idMesa = Integer.parseInt(pedidoOriginal[4]);
        String nombreMesa = this.getNombreMesa(idMesa);
        double precio = Double.parseDouble(pedidoOriginal[1].replace(",", "."));
        
        this.view = new PedidosFinalizarView(false, nombreMesa, precio, this.model.getDetallesPedidoModel(idPedido));
        this.view.setLayout(null);
        
        this.ocultarLbl();
        
        this.view.getbtnAtras().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DestruirVentana();
                new MenuSistemaPedidosController();
            }
        });
        
        this.view.setTitle("Inicio");
        this.view.setResizable(false);
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
        
        this.view.getTableDetallesPedido().setModel(this.model.getDetallesPedidoModel(idPedido));
        this.view.getLblPrecio().setText(String.valueOf(precio) + "€");
        this.view.getLblMesa().setText(nombreMesa);
        
        //SE ESTABLECE LAS FUNCINOES DE LOS BOTONES
            //PAGOS
        
        this.view.getBtn1c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.01);
            }
        });
        
        this.view.getBtn2c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.02);
            }
        });
        
        this.view.getBtn5c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.05);
            }
        });
        
        this.view.getBtn10c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.1);
            }
        });
        
        this.view.getBtn20c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.2);
            }
        });
        
        this.view.getBtn50c().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(0.5);
            }
        });
        
        this.view.getBtn1e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(1);
            }
        });
        
        this.view.getBtn2e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(2);
            }
        });
        
        this.view.getBtn5e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(5);
            }
        });
        
        this.view.getBtn10e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(10);
            }
        });
        
        this.view.getBtn20e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(20);
            }
        });
        
        this.view.getBtn50e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(50);
            }
        });
        
        this.view.getBtn100e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(100);
            }
        });
        
        this.view.getBtn200e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(200);
            }
        });
        
        this.view.getBtn500e().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                añadirDinero(500);
            }
        });
        
        this.view.getBtnTarjeta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pagoTarjeta();
            }
        });
        
        this.view.getBtnReiniciarPago().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reiniciarPago();
            }
        });
        
        this.view.getbtnFinalizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pagarFactura();
            }
        });
    }
    
    public void pagarFactura() {
        if (this.pagado >= this.precio) {
            this.model.finalizarPedido(this.idPedido);
            this.DestruirVentana();
        
        } else {
            this.view.getLblAtention().setVisible(true);
        }
        
    }
    
    public void DestruirVentana(){
        this.view.dispose();
    }
    
    private void ocultarLbl() {
        this.view.getLblAtention().setVisible(false);
    }
    
    private void reiniciarPago() {
        this.pagado = 0;
        
        this.actualizarVista();
    }
    
    private void añadirDinero(double cantidad) {
        this.pagado += cantidad;
        
        this.actualizarVista();
    }
    
    private void pagoTarjeta() {
        this.pagado = this.precio;
        
        this.actualizarVista();
    }
    
    private void actualizarVista() {
        this.devuelto = this.pagado > this.precio ? (this.pagado-this.precio) : 0;
        
        this.view.getLblPagado().setText(String.valueOf(this.pagado) + "€");
        this.view.getLblDevolucion().setText(String.valueOf(this.devuelto) + "€");
        
        this.ocultarLbl();
    }
    
    private String getNombreMesa(int idMesa) {
        for (String[] mesaActual : this.mesasModel.getMesasOcupadas()) {
            if (Integer.parseInt(mesaActual[0]) == idMesa) {
                return mesaActual[1];
            }
        }
        
        return null;
    }
    
    private String[] getPedido(int idPedido) {
        for (String[] pedidoActual : this.productosModel.getPedidos()) {
            if (Integer.parseInt(pedidoActual[0]) == idPedido) {
                return pedidoActual;
            }
        }
        
        return null;
    }
}

