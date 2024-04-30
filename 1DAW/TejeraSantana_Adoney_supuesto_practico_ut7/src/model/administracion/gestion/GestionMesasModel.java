
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class GestionMesasModel {
    private List<String[]> mesas;
    private String rutaMesass;
    
    public GestionMesasModel() {
        this.mesas = new ArrayList();
        
        this.cargarRutaMesas();
        this.cargarDatosMesas();
        
    }
    
    public void insertarMesa(int id, String nombre) {
        String nuevaMesa = String.valueOf(id) + "#" + nombre;
        
        BufferedWriter mesasWriter = null;
        
        try {
            File archivoMesas = new File(this.rutaMesass);
            mesasWriter = new BufferedWriter(new FileWriter(archivoMesas, true));
            
            mesasWriter.newLine();
            mesasWriter.write(nuevaMesa);
            
        } catch (IOException e) {
            System.out.println("ERROR al insertar mesa: " + e.getMessage());
            
        } finally {
            try {
                mesasWriter.close();
                this.cargarDatosMesas();
                
            } catch (IOException e) {
                System.out.println("ERROR al cerrar archivo al insertar mesa: " + e.getMessage());
            }
        }
    }
    
    public void borrarMesa(int id) {
        BufferedWriter mesasWriter = null;
        
        try {
            File archivoMesas = new File(this.rutaMesass);
            mesasWriter = new BufferedWriter(new FileWriter(archivoMesas));
            mesasWriter.close();
            
            mesasWriter = new BufferedWriter(new FileWriter(archivoMesas, true));
            
            for (String[] mesaActual : this.mesas) {
                if (Integer.parseInt(mesaActual[0]) != id) {
                    String nuevaMesa = mesaActual[0] + "#" + mesaActual[1];
                    
                    mesasWriter.newLine();
                    mesasWriter.write(nuevaMesa);                    
                }
            }
            
        } catch (IOException e) {
            System.out.println("ERROR al insertar mesa: " + e.getMessage());
            
        } finally {
            try {
                mesasWriter.close();
                this.cargarDatosMesas();
                
            } catch (IOException e) {
                System.out.println("ERROR al cerrar archivo al insertar mesa: " + e.getMessage());
            }
        }
        
    }
    
    public void actualizarMesa(int id, String nombre) {
        BufferedWriter mesasWriter = null;

        try {
            File archivoMesas = new File(this.rutaMesass);
            mesasWriter = new BufferedWriter(new FileWriter(archivoMesas));
            mesasWriter.close();

            mesasWriter = new BufferedWriter(new FileWriter(archivoMesas, true));

            for (String[] mesaActual : this.mesas) {
                if (Integer.parseInt(mesaActual[0]) != id) {
                    String nuevaMesa = mesaActual[0] + "#" + mesaActual[1];

                    mesasWriter.newLine();
                    mesasWriter.write(nuevaMesa);
                    
                } else {
                    String nuevaMesa = mesaActual[0] + "#" + nombre;

                    mesasWriter.newLine();
                    mesasWriter.write(nuevaMesa);
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR al insertar mesa: " + e.getMessage());

        } finally {
            try {
                mesasWriter.close();
                this.cargarDatosMesas();

            } catch (IOException e) {
                System.out.println("ERROR al cerrar archivo al insertar mesa: " + e.getMessage());
            }
        }
    }
    
    public DefaultTableModel getModelMesas() {
        this.cargarDatosMesas();
        
        String[] tableColumns = {"ID", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        for (String[] mesaActual : this.mesas) {
            tableModel.addRow(mesaActual);
        }
        
        return tableModel;
    }
    
    public List<String[]> getMesas() {
        return this.mesas;
    }
    
    private void cargarRutaMesas() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        
        try {            
            String path = "";

            File archivo = new File(System.getProperty("user.dir") + "\\src\\model\\datos\\rutas.properties");
            
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            
            inputStream = new FileInputStream(archivo);
            
            properties.load(inputStream);
            
            path = System.getProperty("user.dir") + properties.getProperty("path_mesas");
            
            File archivoMesas = new File(path);
            
            if (!archivoMesas.exists()) {
                archivoMesas.createNewFile();
            }
            
            this.rutaMesass = path;
            
        } catch (IOException e) {
            System.out.println("Error al cargar ruta de rutas.properties");
            
        } finally {
            try {
                inputStream.close();
                
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
                        
            }
        }
    }
    
    private void cargarDatosMesas() {
            BufferedReader mesasReader = null;
        try {
            File archivo = new File(this.rutaMesass);
            mesasReader = new BufferedReader(new FileReader(archivo));
            
            String fila;
            
            List<String[]> mesas = new ArrayList();
            
            while ((fila = mesasReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] rowData = fila.split("#");
                    mesas.add(rowData);                    
                }
            }
            
            this.mesas = mesas;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar mesas: " + e.getMessage());
            
        } finally {
            try {
                mesasReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar mesas: " + e.getMessage());
            }
        }
    }
    
    
}
