
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller.administracion;

import controller.administracion.MenuAdministracionController;
import view.administracion.acceso.AdministracionInicioSesionView;

public class PassAdministracionController {
    private AdministracionInicioSesionView view;
    private final String usuario = "admin";
    private final String contraseña = "1234";
    
    public PassAdministracionController() {
        this.generarVentana();
    }
    
    public void generarVentana() {
        this.view = new AdministracionInicioSesionView();
        
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
        
        this.view.setVisible(true);
    }
    
    public void iniciarSesion() {
        MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
        this.destruirVentana();
    }
    
    public void destruirVentana() {
        this.view.setVisible(false);
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
