
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.historial_pedidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

//PEDIDOS: ID, precio, fecha, mesa, hecho

public class HistorialPedidosModel {
    private List<String[]> pedidos;
    private List<String[]> mesas;
    private List<String[]> productos;
    private String rutaPedidos;
    private String rutaProductos;
    private String rutaMesas;
    
    public HistorialPedidosModel() {
        this.pedidos = new ArrayList();
        this.mesas = new ArrayList();
        this.productos = new ArrayList();
        
        this.cargarRutas();
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
        this.cargarDatosMesas();
        
        /*//Generando valores de ejemplo
        //  Mesas
        for (int a = 0; a<5; a++) {
            String nombreMesa = "Mesa" + String.valueOf(a);
            String[] nuevaMesa = {String.valueOf(a), nombreMesa};
            mesas.add(nuevaMesa);
        }
        //  Pedidos
        for (int a = 0; a<2; a++) {
            double precio = a*1.2;
            boolean hecho = a % 2 == 0;
            String fecha = String.valueOf(a) + "-03-2024";
            
            String[] nuevoPedido = {String.valueOf(a), String.valueOf(precio), fecha, this.mesas.get(a)[0], String.valueOf(hecho)};
            this.pedidos.add(nuevoPedido);
        }
        // Productos
        for (int a = 0; a<4; a++) {
            String nombreProducto = "Producto"+String.valueOf(a);
            double precio = a*1.1;
            
            String[] nuevoProducto = {String.valueOf(a), nombreProducto, String.valueOf(precio)};
            
            this.productos.add(nuevoProducto);
        }
        // Productos de pedido
        for (int a = 0; a<4; a++) {
            int dePedido = a%2 == 0 ? 1 : 2;
            int cantidad = a;
            
            String[] nuevoProducto = {String.valueOf(dePedido), String.valueOf(a), String.valueOf(cantidad)};
            
            this.pedidoProductos.add(nuevoProducto);
        }*/
        
    }
    
    public DefaultTableModel getModelPedidos() {
        DefaultTableModel tableModel;
        
        String[] tableColumns = {"ID", "Precio", "En curso", "Fecha", "Mesa"};
        
        tableModel = new DefaultTableModel(tableColumns, 0) {
            Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class};
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int r, int c) {
                
                return false;
            }
        };
        
        //Se obtienen los datos
        
        for (String[] pedidoActual : this.pedidos) {
            String id = pedidoActual[0];
            String precio = pedidoActual[1];
            String fecha = pedidoActual[3];
            String nombreMesa = this.buscarNombreMesa(Integer.parseInt(pedidoActual[4]));
            boolean enCurso = Integer.parseInt(pedidoActual[2]) == 1 ? true : false;
            
            Object[] nuevoPedidoParaTabla = {id, precio, enCurso, fecha, nombreMesa};
            
            tableModel.addRow(nuevoPedidoParaTabla);
        }
        
        //Se crea la clase de tabla
        
        
        return tableModel;
    }
    
    private String buscarNombreMesa(int idMesa) {
        for (String[] mesaActual : this.mesas) {
            int idActual = Integer.parseInt(mesaActual[0]);
            
            if (idActual == idMesa) {
                return mesaActual[1];
            }
        }
        
        return null;
    } 
    
    private void cargarRutas() {
        InputStream fileInputStream = null;
        Properties properties = new Properties();
        
        try {
            File archivoProperties = new File(System.getProperty("user.dir") + "\\src\\model\\datos\\rutas.properties");
            fileInputStream = new FileInputStream(archivoProperties);
            
            properties.load(fileInputStream);
            
            String rutaPedidos = System.getProperty("user.dir") + properties.getProperty("path_pedidos");
            String rutaProductos = System.getProperty("user.dir") + properties.getProperty("path_productos");
            String rutaMesas = System.getProperty("user.dir") + properties.getProperty("path_mesas");
            
            this.rutaPedidos = rutaPedidos;
            this.rutaProductos = rutaProductos;
            this.rutaMesas = rutaMesas;
            
            fileInputStream.close();
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ruga de comandas: " + e.getMessage());
            
        } finally {
            try {
                fileInputStream.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar ruta de comandas: " + e.getMessage());
                        
            }
        }
    }
    
    private void cargarDatosPedidos() {
        List<String[]> pedidos = new ArrayList();
        
        BufferedReader pedidosReader = null;
        
        try {
            File archivoPedidos = new File(this.rutaPedidos);
            pedidosReader = new BufferedReader(new FileReader(archivoPedidos));
            
            String fila = "";
            
            while ((fila = pedidosReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] filaSplit = fila.split("#");
                    String rowData[] = {filaSplit[0], filaSplit[1], filaSplit[2], filaSplit[3], filaSplit[4]};
                    pedidos.add(rowData);
                }
            }
            
            this.pedidos = pedidos;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos de pedidos: " + e.getMessage());
            
        } finally {
            try {
                pedidosReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos de pedidos: " + e.getMessage());
            }
        }
    }
    
    private void cargarDatosProductos() {
        List<String[]> productos = new ArrayList();
        
        BufferedReader productosReader = null;
        
        try {
            File archivoProductos = new File(this.rutaProductos);
            productosReader = new BufferedReader(new FileReader(archivoProductos));
            
            String fila = "";
            
            while ((fila = productosReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] filaSplit = fila.split("#");
                    String rowData[] = {filaSplit[0], filaSplit[1], filaSplit[2], filaSplit[3]};
                    productos.add(rowData);
                }
            }
            
            this.productos = productos;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos de productos: " + e.getMessage());
            
        } finally {
            try {
                productosReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos de productos: " + e.getMessage());
            }
        }
    }
    
    private void cargarDatosMesas() {
            BufferedReader mesasReader = null;
        try {
            File archivo = new File(this.rutaMesas);
            mesasReader = new BufferedReader(new FileReader(archivo));
            
            String fila;
            
            List<String[]> mesas = new ArrayList();
            
            while ((fila = mesasReader.readLine()) != null) {
                String[] rowData = fila.split("#");
                mesas.add(rowData);
            }
            
            this.mesas = mesas;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos: " + e.getMessage());
            
        } finally {
            try {
                mesasReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos: " + e.getMessage());
            }
        }
    }
}
