
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller.administracion;

import controller.InicioController;
import view.administracion.acceso.AdministracionInicioSesionView;


// González Olivares Brandon - Tejera Santana Adoney

public class PassAdministracionController {
    private AdministracionInicioSesionView view;
    public  String usuario = "admin";
    public  String contraseña = "1234";
    
    public PassAdministracionController() {
        this.generarVentana();
    }

    
    
    public void generarVentana() {
        this.view = new AdministracionInicioSesionView();
        view.setLayout(null);
        view.setTitle("Iniciar sesion");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.clearLbl();
        
        this.view.getBtnEntrar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLbl();
                
                String usuario = view.getFieldUsuario().getText();
                String contraseña = view.getFieldContraseña().getText();
                
                if (validar(usuario, contraseña)) {
                    iniciarSesion();
                    
                } else {
                    view.getLblInvalido().setVisible(true);
                }
            }
        });
        
        this.view.getBtnCancelar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioController inicio = new InicioController();
                destruirVentana();
            }
        });
        
        this.view.setVisible(true);
    }
    
    public void iniciarSesion() {
        MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
        this.destruirVentana();
    }
    
    public void destruirVentana() {
        this.view.dispose();
    }
    
    private void clearLbl() {
        this.view.getLblInvalido().setVisible(false);
    }
    
    private boolean validar(String usuario, String contraseña) {
        if (this.usuario.equals(usuario) && this.contraseña.equals(contraseña)) {
            return true;
        }
        
        return false;
    }
}
