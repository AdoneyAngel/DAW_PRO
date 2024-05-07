
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

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
public class FinalizarPedidoModel {
    private List<String[]> pedidos;
    private List<int[]> comandas;
    private List<int[]> comandaProductos;
    private List<String[]> productos;
    private String rutaComanda;
    private String rutaComandaProductos;
    private String rutaPedidos;
    private String rutaProductos;
    
    public FinalizarPedidoModel() {
        this.pedidos = new ArrayList();
        this.comandas = new ArrayList();
        this.comandaProductos = new ArrayList();
        this.productos = new ArrayList();
        
        this.cargarRutas();
        this.cargarDatosComandaProductos();
        this.cargarDatosComandas();
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
    }
    
    public DefaultTableModel getDetallesPedidoModel(int idPedido) {
        DefaultTableModel tableModel;
        List<String[]> productosDePedido = this.getPedidoProductos(idPedido);
        
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };
        
        for (String[] productoActual : productosDePedido) {
            tableModel.addRow(productoActual);
        }
        
        return tableModel;
    }
    
    public void finalizarPedido(int id) {
        this.cargarDatosPedidos();
        
        BufferedWriter pedidosWriter = null;
        
        try {
            File archivoPedidos = new File(this.rutaPedidos);
            pedidosWriter = new BufferedWriter(new FileWriter(archivoPedidos));
            pedidosWriter.close();
            
            pedidosWriter = new BufferedWriter(new FileWriter(archivoPedidos, true));
            
            for (String[] pedidoActual : this.pedidos) {
                pedidosWriter.newLine();
                
                if (Integer.parseInt(pedidoActual[0]) == id) {
                    String rowData = 
                            pedidoActual[0] + "#" + 
                            pedidoActual[1] + "#0#" + 
                            pedidoActual[3] + "#" + 
                            pedidoActual[4];
                    
                    pedidosWriter.write(rowData);
                    
                } else {
                    String rowData = 
                            pedidoActual[0] + "#" + 
                            pedidoActual[1] + "#" + 
                            pedidoActual[2] + "#" + 
                            pedidoActual[3] + "#" + 
                            pedidoActual[4];
                    
                    pedidosWriter.write(rowData);
                }
            }
            
        } catch (IOException e) {
            System.out.println("ERROR al finalizar pedido: " + e.getMessage());
            
        } finally {
            try {
                pedidosWriter.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cerrar archivo al finalizar pedido: " + e.getMessage());
            }
        }
    }
    
    public List<String[]> getPedidos() {
        return this.pedidos;
    }
    
    private List<String[]> getPedidoProductos(int idPedido) {
        List<String[]> productos = new ArrayList();
        List<int[]> comandasDePedido = this.obtenerComandasDePedido(idPedido);
        
        for (int[] comandaActual : comandasDePedido) {
            for (int[] productoActual : this.comandaProductos) {
                if (productoActual[0] == comandaActual[0]) {
                    String[] productoOriginal = this.getProducto(productoActual[1]);
                    
                    String nombreProducto = productoOriginal[1];
                    String precioProducto = productoOriginal[2];
                    String unidades = String.valueOf(productoActual[2]);
                    double subtotal = Double.parseDouble(precioProducto.replace(",", ".")) * Integer.parseInt(unidades);
                    
                    String[] productoParaTabla = {
                        nombreProducto,
                        precioProducto,
                        unidades,
                        String.valueOf(subtotal)
                    };
                    
                    productos.add(productoParaTabla);
                }
            }
        }
        
        return productos;
    }
    
    private List<int[]> obtenerComandasDePedido(int idPedido) {
        List<int[]> comandas = new ArrayList();
        
        for (int[] comandaActual : this.comandas) {
            if (comandaActual[1] == idPedido) {
                comandas.add(comandaActual);
            }
        }
        
        return comandas;
    }
    
    private String[] getProducto (int idProducto) {
        for (String[] productoActual : this.productos) {
            if (String.valueOf(idProducto).equals(productoActual[0])) {
                return productoActual;
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
            
            String rutaComandas = System.getProperty("user.dir") + properties.getProperty("path_comandas");
            String rutaComandaProductos = System.getProperty("user.dir") + properties.getProperty("path_comandaProductos");
            String rutaPedidos = System.getProperty("user.dir") + properties.getProperty("path_pedidos");
            String rutaProductos = System.getProperty("user.dir") + properties.getProperty("path_productos");
            
            this.rutaComanda = rutaComandas;
            this.rutaComandaProductos = rutaComandaProductos;
            this.rutaPedidos = rutaPedidos;
            this.rutaProductos = rutaProductos;
            
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
    
    private void cargarDatosComandas() {
        List<int[]> comandas = new ArrayList();
        
        BufferedReader comandasReader = null;
        
        try {
            File archivoComandas = new File(this.rutaComanda);
            comandasReader = new BufferedReader(new FileReader(archivoComandas));
            
            String fila = "";
            
            while ((fila = comandasReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] filaSplit = fila.split("#");
                    int rowData[] = {Integer.parseInt(filaSplit[0]), Integer.parseInt(filaSplit[1])};
                    comandas.add(rowData);
                }
            }
            
            this.comandas = comandas;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos de comandas: " + e.getMessage());
            
        } finally {
            try {
                comandasReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos de comandas: " + e.getMessage());
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
    
    private void cargarDatosComandaProductos() {
        List<int[]> comandaProductos = new ArrayList();
        
        BufferedReader comandasProductosReader = null;
        
        try {
            File archivoComandas = new File(this.rutaComandaProductos);
            comandasProductosReader = new BufferedReader(new FileReader(archivoComandas));
            
            String fila = "";
            
            while ((fila = comandasProductosReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] filaSplit = fila.split("#");
                    int rowData[] = {Integer.parseInt(filaSplit[0]), Integer.parseInt(filaSplit[1]), Integer.parseInt(filaSplit[2])};
                    comandaProductos.add(rowData);
                }
            }
            
            this.comandaProductos = comandaProductos;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos de productos de comandas: " + e.getMessage());
            
        } finally {
            try {
                comandasProductosReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos de productos de comandas: " + e.getMessage());
            }
        }
    }
}
