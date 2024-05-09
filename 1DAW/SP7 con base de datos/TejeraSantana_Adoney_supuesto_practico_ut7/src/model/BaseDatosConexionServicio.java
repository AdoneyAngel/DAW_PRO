
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class BaseDatosConexionServicio {
    private String dbdriver;
    private String dbhost;
    private int dbport;
    private String dbname;
    private String dbuser;
    private String dbpassword;
    private String url;
    private Connection connection;
    
    public BaseDatosConexionServicio () {
    }
    
    public void abrirConexion () {
        this.cargarDatosArchivo();
        
        try {
            Class.forName(this.dbdriver);
            
            this.url = "jdbc:mysql://" + this.dbhost + ":" + this.dbport + "/" + this.dbname;
            this.connection = DriverManager.getConnection(this.url, this.dbuser, this.dbpassword);
            this.verificarConexion();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void cerrarConexion () {
        try {
            if (this.getConnection() != null) {
                this.getConnection().close();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void verificarConexion () {
        try {
            if (this.getConnection() == null || this.getConnection().isClosed()) {
                System.out.println("ADVERTENCIA: la conexion con la base de datos esta cerrada");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
    private void cargarDatosArchivo () {
        Properties properties = new Properties();
        InputStream propertiesStream = null;
        
        try {
            File archivoProperties = new File(System.getProperty("user.dir") + "/src/model/datos/rutas.properties");
            propertiesStream = new FileInputStream(archivoProperties);
            
            properties.load(propertiesStream);
            
            this.dbdriver = properties.getProperty("db_driver");
            this.dbhost = properties.getProperty("db_host");
            this.dbport = Integer.parseInt(properties.getProperty("db_port"));
            this.dbname = properties.getProperty("db_name");
            this.dbuser = properties.getProperty("db_user");
            this.dbpassword = properties.getProperty("db_password");
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar los datos de la base de datos: " + e.getMessage());
        
        } finally {
            try {
                propertiesStream.close();
                
            } catch (IOException e) {
            System.out.println("ERROR al cerrra archivo de la base de datos: " + e.getMessage());
            }
        }
    } 

    public Connection getConnection() {
        return connection;
    }

    public String getDbname() {
        return dbname;
    }

    public String getDbhost() {
        return dbhost;
    }
    
}
