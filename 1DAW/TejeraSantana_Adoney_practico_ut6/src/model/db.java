/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mysql.cj.jdbc.Driver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Adone
 */
public class db {
    private String host;
    private String port;
    private String user;
    private String password;
    private String connectionString;
    private String driver;
    private String name;
    private Connection connection;
    
    public db () {
        this.cargarDatosArchivo();
    }
    
    public void abrirConexion() {
        try {
            Class.forName(this.driver);
            
            this.connectionString = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.getName();
            
            this.connection = DriverManager.getConnection(this.connectionString, this.user, this.password);
            
            this.verificarConexion();
            
        } catch (SQLException e) {
            System.out.println("Error al abrir conexion: " + e.getMessage());
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver de db: " + e.getMessage());
                    
        }
    }
    
    public boolean verificarConexion() {
        try {
            
            if (this.getConnection() == null || this.getConnection().isClosed()) {
                System.out.println("La conexion con la base de datos esta cerrada");
                return false;
                
            } else {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al verificar conexion: " + e.getMessage());
        }
        
        return false;
    }
    
    public void cerrarConexion() {
        try {
            if (!this.connection.isClosed())
                this.getConnection().close();
            
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexion: " + e.getMessage());
        }
    }
    
    private void cargarDatosArchivo() {
        Properties dbProperties = new Properties();
        InputStream dbStream = null;
        
        try {
            File arhivoDb = new File(System.getProperty("user.dir") + "/src/model/dbData.properties");
            dbStream = new FileInputStream(arhivoDb);
            
            dbProperties.load(dbStream);
            
            this.host = dbProperties.getProperty("host");
            this.port = dbProperties.getProperty("port");
            this.user = dbProperties.getProperty("user");
            this.password = dbProperties.getProperty("password");
            this.driver = dbProperties.getProperty("driver");
            this.name = dbProperties.getProperty("name");
            
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de DB: " + e.getMessage() + "::::" + System.getProperty("user.dir") + "/src/model/dbData.properties");
            
        } finally {
            try {
                dbStream.close();
                
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo de propiedades de db: " + e.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public Connection getConnection() {
        return connection;
    }
}
