
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller.administracion;
import controller.administracion.PassAdministracionController;
import controller.administracion.MenuAdministracionController;
import view.administracion.acceso.AdministracionGestionPasswordView;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class CambiarPassAdministracionController {
    private static AdministracionGestionPasswordView view =null;
    public static void main(String[] args) {
       GenerarVentana();
    }
    
    public static void GenerarVentana(){
        view = new AdministracionGestionPasswordView();
        view.setLayout(null);
        view.setTitle("Cambiar contraseña");
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.getbtnCancelar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
                DestruirVentana();
            }
        });
        view.getbtnAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassAdministracionController passAdministracionController = new PassAdministracionController();
                DestruirVentana();
            }
        });
        
    }
    public static void DestruirVentana(){
        view.dispose();
    }
    public CambiarPassAdministracionController(){
        GenerarVentana();
    }
}