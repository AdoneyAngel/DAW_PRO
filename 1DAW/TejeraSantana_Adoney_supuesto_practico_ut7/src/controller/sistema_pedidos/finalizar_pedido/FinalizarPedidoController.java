
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.finalizar_pedido;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import view.pedidos.PedidosFinalizarView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class FinalizarPedidoController {
    private static PedidosFinalizarView view =null;
    
    public static void main(String[] args) {
       GenerarVentana();
    }
    public static void GenerarVentana(){
        view = new PedidosFinalizarView();
        view.setLayout(null);
        
        view.getbtnFinalizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DestruirVentana();
                new MenuSistemaPedidosController();
            }
        });
        view.getbtnAtras().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DestruirVentana();
                new MenuSistemaPedidosController();
            }
        });
        
        view.setTitle("Inicio");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    public FinalizarPedidoController(){
        GenerarVentana();
    }
    public static void DestruirVentana(){
        view.dispose();
    }
}

