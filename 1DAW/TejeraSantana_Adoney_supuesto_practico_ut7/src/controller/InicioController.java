/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;
import controller.administracion.PassAdministracionController;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.InicioView;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class InicioController {
    private static InicioView view =null;
    
    public static void main(String[] args) {
       GenerarVentana();
    }
    public static void GenerarVentana(){
        view = new InicioView();
        view.setLayout(null);
        
        view.getBtnAdministracionDelSistema().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DestruirVentana();
                new PassAdministracionController();
            }
        });
        view.getBtnInicioDelSistema().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DestruirVentana();
                
                new MenuSistemaPedidosController();
            }
        });
        
        view.setTitle("Inicio");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    public InicioController(){
        GenerarVentana();
    }
    public static void DestruirVentana(){
        view.dispose();
    }
}
