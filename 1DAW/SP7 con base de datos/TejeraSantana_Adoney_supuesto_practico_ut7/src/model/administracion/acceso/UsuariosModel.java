package model.administracion.acceso;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.BaseDatosConexionServicio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AdoneyDAW
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
    }
}
