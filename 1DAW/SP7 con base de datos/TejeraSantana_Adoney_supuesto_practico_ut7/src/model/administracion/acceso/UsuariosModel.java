package model.administracion.acceso;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;
import model.BaseDatosConexionServicio;
/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class UsuariosModel implements Serializable{
    private BaseDatosConexionServicio db;
    
    public UsuariosModel() {
        this.db = new BaseDatosConexionServicio();
    }
    
    public boolean iniciarSesion(String nombre, String contraseña) {
        
        boolean sesionIniciada = false;
        
        Statement statement = null;
        ResultSet resultset = null;
        
        this.db.abrirConexion();
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM usuario WHERE nombre = '" + nombre + "' AND password = '" + contraseña + "' AND disponible = 1");
            
            while (resultset.next()) {
                sesionIniciada = true;
            }
            
        } catch (SQLException error)  {
            System.out.println("ERROR AL INICIAR SESION: " + error.getMessage());
            
        } finally {
            this.db.cerrarConexion();
            return sesionIniciada;
        }
        
    }
    
    public void actualizarUsuario(int id, String nombre, String contraseña, int disponible) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            
            statement.executeUpdate("UPDATE " + this.db.getDbname() + ".usuario SET nombre='"+nombre+"', password='"+contraseña+"', disponible="+String.valueOf(disponible)+" WHERE id="+id);
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public void cambiarContraseñaPorNombre (String nombre, String nuevaContraseña) {
        this.db.abrirConexion();
        
        Statement statement = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            
            statement.executeUpdate("UPDATE " + this.db.getDbname() + ".usuario SET password='"+nuevaContraseña+"' WHERE nombre='"+nombre+"'");
            
        } catch (SQLException e) {
            System.out.println("Error al cambiar contraseña de usuario: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
    }
    
    public String[] buscarUsuario(String nombre) {
        String usuario[] = null;
        
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".usuario WHERE nombre='"+nombre+"'");
            
            usuario = new String[4];
            
            while (resultset.next()) {
                usuario[0] = resultset.getString("id");
                usuario[1] = resultset.getString("nombre");
                usuario[2] = resultset.getString("password");
                usuario[3] = resultset.getString("disponible");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: "+e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
        }
        
        return usuario;
    }
}
