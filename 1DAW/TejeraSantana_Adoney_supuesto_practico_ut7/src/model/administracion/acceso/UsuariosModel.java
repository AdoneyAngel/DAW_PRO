package model.administracion.acceso;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AdoneyDAW
 */
public class UsuariosModel implements Serializable{
    private List<String[]> usuarios;
    private String rutaUsuarios;
    
    public UsuariosModel() {
        this.usuarios = new ArrayList();
        
        this.cargarRuta();
        //this.crearAdmin();
        this.cargarDatosUsuarios();
    }
    
    public boolean iniciarSesion(String nombre, String contraseña) {
        //Buscar usuario
        
        for (String[] usuarioActual : this.usuarios) {
            if (usuarioActual[1].equals(nombre) && usuarioActual[2].equals(contraseña)) {
                return true;
            } 
        }
        
        return false;
    }
    
    public void cambiarContraseñaPorNombre(String nombre, String contraseñaOriginal, String contraseñaNueva) {
        
        String[] usuarioOriginal = this.buscarUsuario(nombre);
        
        if (usuarioOriginal[2].equals(contraseñaOriginal) && usuarioOriginal != null) {
            
            this.actualizarUsuario(
                    Integer.parseInt(usuarioOriginal[0]), 
                    nombre, 
                    contraseñaNueva, 
                    Integer.parseInt(usuarioOriginal[3]));
            
        }
    }
    
    public void actualizarUsuario(int id, String nombre, String contraseña, int disponible) {
        ObjectOutputStream usuariosWriter = null;
        
        List<String[]> nuevosUsuarios = new ArrayList();
        
        try {
            File archivoUsuario = new File(this.rutaUsuarios);
            usuariosWriter = new ObjectOutputStream(new FileOutputStream(archivoUsuario));
            usuariosWriter.close();
            
            usuariosWriter = new ObjectOutputStream(new FileOutputStream(archivoUsuario));
            
            for (String[] usuarioActual : this.usuarios) {
                if (Integer.parseInt(usuarioActual[0]) != id) {
                    nuevosUsuarios.add(usuarioActual);
                    
                } else {
                    String[] usuarioModificado = {String.valueOf(id), nombre, contraseña, String.valueOf(disponible)};
                    nuevosUsuarios.add(usuarioModificado);
                }
            }
            
            usuariosWriter.writeObject(nuevosUsuarios);
            
        } catch (IOException e) {
            System.out.println("ERROR al actualizar usuario: " + e.getMessage());
            
        } finally {
            try {
                usuariosWriter.close();
                this.cargarDatosUsuarios();
                
            } catch (IOException e) {
                
            }
        }
    }
    
    public String[] buscarUsuario(String nombre) {
        for (String[] usuarioActual : this.usuarios) {
            if (usuarioActual[1].equals(nombre)) {
                return usuarioActual;
            }
        }
        
        return null;
    }
    
    private void cargarRuta() {
        InputStream fileInputStream = null;
        Properties properties = new Properties();
        
        try {
            File archivoProperties = new File(System.getProperty("user.dir") + "\\src\\model\\datos\\rutas.properties");
            fileInputStream = new FileInputStream(archivoProperties);
            
            properties.load(fileInputStream);
            
            String rutaUsuarios = System.getProperty("user.dir") + properties.getProperty("path_usuarios");
            
            this.rutaUsuarios = rutaUsuarios;
            
            fileInputStream.close();
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ruga de usuarios: " + e.getMessage());
            
        } finally {
            try {
                fileInputStream.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar ruta usuarios: " + e.getMessage());
                        
            }
        }
    }
    
    private void cargarDatosUsuarios() {
        List<String[]> usuarios = new ArrayList();
        ObjectInputStream usuariosReader = null;
        
        try {
            usuariosReader = new ObjectInputStream(new FileInputStream(this.rutaUsuarios));
            
            usuarios = (List<String[]>) usuariosReader.readObject();
            
            this.usuarios = usuarios;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR al cargar los ususarios: " + e.getMessage());
            
        } finally {
            try {
                usuariosReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cerrar archivo al cargar usuarios: " + e.getMessage());
            }
        }
        
    }
    
    private void crearAdmin() {
        List<String[]> usuarios = new ArrayList();
        String[] admin = {"0", "admin", "1234", "1"};
        usuarios.add(admin);
        
        ObjectOutputStream usuariosWriter = null;
        
        try {
            File archivoUsuario = new File(this.rutaUsuarios);
            usuariosWriter = new ObjectOutputStream(new FileOutputStream(archivoUsuario));
            
            usuariosWriter.writeObject(usuarios);
            
        } catch (IOException e) {
            
        } finally {
            try {
                usuariosWriter.close();
                
            } catch (IOException e) {
                
            }
        }
    }
}
