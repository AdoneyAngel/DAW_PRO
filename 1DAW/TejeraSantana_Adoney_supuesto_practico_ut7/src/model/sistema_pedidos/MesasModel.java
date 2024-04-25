
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasModel {
    
    //private String[][] mesas = {{"1", "Mesa 1"}, {"2", "Mesa 2"}, {"3", "Mesa A"}, {"4", "Mesa B"}, {"5", "Mesa Calle"}};
    //MESA: {ID, nombre}
    private List<String[]> mesas;
    private List<String[]> mesasOcupadas;
    private List<String[]> pedidos;
    private AddProductosModel productosModel;
    private String rutaDatos;
    
    public MesasModel() {
        this.mesasOcupadas = new ArrayList();
        this.mesas = new ArrayList();
        this.productosModel = new AddProductosModel();
        this.pedidos = this.productosModel.getPedidos();
        
        this.cargarRutaMesas();
        this.cargarDatos();
        this.obtenerMesasOcupadas();
        
        
        //Agregando mesas de ejemplo
        /*
        String[] mesa1 = {"1", "Mesa 1", "0"};
        String[] mesa2 = {"2", "Mesa 2", "1"};
        String[] mesa3 = {"3", "Mesa A", ""};
        String[] mesa4 = {"4", "Mesa B", ""};
        String[] mesa5 = {"5", "Mesa Calle", ""};
        
        this.mesas.add(mesa1);
        this.mesas.add(mesa2);
        this.mesas.add(mesa3);
        this.mesas.add(mesa4);
        this.mesas.add(mesa5);*/
    }
    
    public List<String[]> getMesas() {
        return mesas;
    }
    
    public List<String> getNombreMesasDisponibles() {
        List<String> nombreMesas = new ArrayList();
        
        for (String[] mesaActual : this.mesas) {
            int idMesaActual = Integer.parseInt(mesaActual[0]);
            
            if (Objects.isNull(ocupado(idMesaActual))) {
                nombreMesas.add(mesaActual[1]);
            }
        }
        
        return nombreMesas;
    }
    
    private void obtenerMesasOcupadas() {
        List<String[]> mesas = new ArrayList();
        
        for (String[] mesaActual : this.mesas) {
            int idMesaActual = Integer.parseInt(mesaActual[0]);
            
            String[] mesaOcupadaActual = this.ocupado(idMesaActual);
            
            if (!Objects.isNull(mesaOcupadaActual)) {
                mesas.add(mesaOcupadaActual);
                
            }
        }
        
        this.mesasOcupadas = mesas;
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
            
            this.rutaDatos = path;
            
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
    
    public List<String[]> getMesasOcupadas() {
        return this.mesasOcupadas;
    }
    
    private void cargarDatos() {
        try {
            File archivo = new File(this.rutaDatos);
            BufferedReader mesasReader = new BufferedReader(new FileReader(archivo));
            
            String fila;
            
            List<String[]> mesas = new ArrayList();
            
            while ((fila = mesasReader.readLine()) != null) {
                String[] rowData = fila.split("#");
                mesas.add(rowData);
            }
            
            this.mesas = mesas;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos: " + e.getLocalizedMessage());
        }
    }
    
    private String[] obtenerMesaPorId(int idMesa) {
        for (String[] mesaActual : this.mesas) {
            if (mesaActual[0].equals(String.valueOf(idMesa))) {
                return mesaActual;
            }
        }
        
        return null;
    }
    
    private String[] ocupado(int idMesa) {
        for (String[] pedidoActual : this.pedidos) {
            if (pedidoActual[3].equals(String.valueOf(idMesa))) {
                String[] mesaOriginal = obtenerMesaPorId(idMesa);
                String[] mesaOcupada = {mesaOriginal[0], mesaOriginal[1], pedidoActual[0]};
                
                return mesaOcupada;
            }
        }
        
        return null;
    }
    
    
}
