
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney

package controller.administracion;
import controller.administracion.PassAdministracionController;
import controller.administracion.MenuAdministracionController;
import model.administracion.acceso.UsuariosModel;
import view.administracion.acceso.AdministracionGestionPasswordView;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class CambiarPassAdministracionController {
    private static AdministracionGestionPasswordView view =null;
    private static UsuariosModel model;
    
    public CambiarPassAdministracionController(){
        this.model = new UsuariosModel();
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
                if (cambiarContraseña()) {
                    PassAdministracionController passAdministracionController = new PassAdministracionController();
                    DestruirVentana();
                }
            }
        });
        
        ocultarLbl();
        
    }
    
    public static void DestruirVentana(){
        view.dispose();
    }
    
    private static void ocultarLbl() {
        view.getLblIncorrecto().setVisible(false);
        view.getLblNocoincide().setVisible(false);
    }
    
    private static boolean cambiarContraseña() {
        String usuario = "admin";
        
        String contraseñaActual = view.getFieldContraeñaactual().getText().toString();
        String contraseñaNueva = view.getFieldNuevaContraseña().getText().toString();
        String contraseñaRepetida = view.getFieldRepetircontraseña().getText().toString();
        
        if (model.iniciarSesion(usuario, contraseñaActual)) {
            if (contraseñaNueva.equals(contraseñaRepetida)) {
                String[] usuarioOriginal = model.buscarUsuario(usuario);
                
                model.cambiarContraseñaPorNombre(usuario, contraseñaActual, contraseñaNueva);
                
                return true;
                
            } else {
                view.getLblNocoincide().setVisible(true); 
            }
            
        } else {
            view.getLblIncorrecto().setVisible(true);
        }
        
        return false;
    }
}