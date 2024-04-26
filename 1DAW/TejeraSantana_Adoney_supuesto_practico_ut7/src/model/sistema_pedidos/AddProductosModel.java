
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
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */

//PRODUCTO: ID, nombre, categoriaID, precio
//CATEGORIA: ID, nombre
//PEDIDO: ID, precio, fecha, idMesa, enCurso
//COMANDA: ID, idPedido
//COMANDAPRODUCTO: IDcomanda, idProducto, cantidad
//PRODUCTOPEDIDO: idPedido, idProducto, Cantidad

public class AddProductosModel {
    private List<String[]> categorias;
    private List<String[]> productos;
    private List<String[]> pedidos;
    private List<String[]> pedidoProductos;
    private List<int[]> comandas;
    private List<int[]> comandaProductos;
    private String rutaComanda;
    private String rutaComandaProductos;
    private String rutaPedidos;
    private String rutaProductos;
    private String rutaCategorias;
    
    public AddProductosModel() {
        this.productos = new ArrayList();
        this.pedidos = new ArrayList();
        this.categorias = new ArrayList();
        this.comandas = new ArrayList();
        this.comandaProductos = new ArrayList();
        this.pedidoProductos = new ArrayList();
        
        this.cargarRutas();
        this.cargarDatosComandas();
        this.cargarDatosComandaProductos();
        this.cargarDatosPedidos();
        this.cargarDatosProductos();
        this.cargarDatosCategorias();
    }

    public List<String[]> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String[]> categorias) {
        this.categorias = categorias;
    }

    public List<String[]> getProductos() {
        return productos;
    }

    public void setProductos(List<String[]> productos) {
        this.productos = productos;
    }

    public List<String[]> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<String[]> pedidos) {
        this.pedidos = pedidos;
    }

    public List<String[]> getPedidoProductos() {
        return pedidoProductos;
    }

    public void setPedidoProductos(List<String[]> pedidoProductos) {
        this.pedidoProductos = pedidoProductos;
    }

    public List<int[]> getComandas() {
        return comandas;
    }

    public void setComandas(List<int[]> comandas) {
        this.comandas = comandas;
    }

    public List<int[]> getComandaProductos() {
        return comandaProductos;
    }

    public void setComandaProducto(List<int[]> comandaProducto) {
        this.comandaProductos = comandaProducto;
    }

    
   
    public String[] getNombreCategorias() {
        String[] nombreCategorias = new String[this.getCategorias().size()];
        
        Iterator<String[]> categoriasIt = this.getCategorias().iterator();
        int index = 0;
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            nombreCategorias[index] = categoriaActual[1];
            
            index++;
            
        }
        
        return nombreCategorias;
    }
    
    public DefaultTableModel getTablaVaciaModel() {
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        
        DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        return tableModel;
    }
    
    public String[] getNombreProductosCategoria(String nombreCategoria) {
        String[] categoria = getCategoriaPorNombre(nombreCategoria);
        
        String[] nombreProductosCategoria;
        List<String> productosCategoriaList = new ArrayList<>();
        
        int index = 0;
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (categoria[0].equals(productoActual[3])) {
                productosCategoriaList.add(productoActual[1]);
                
                index++;
            }
        }
        
        nombreProductosCategoria = new String[productosCategoriaList.size()];
        
        nombreProductosCategoria = productosCategoriaList.toArray(nombreProductosCategoria);
        
        return nombreProductosCategoria;
    }
    
    public double getPrecioProductoNombre(String nombreProducto) {
        String[] producto = getProductoPorNombre(nombreProducto);
        
        double precio = Double.parseDouble(producto[3]);
        
        return precio;
    }
    
    public int getIdProductoNombre(String nombreProducto) {
        String[] producto = getProductoPorNombre(nombreProducto);
        
        int id = Integer.parseInt(producto[0]);
        
        return id;
    }
    
    public double getPrecioPedidoId (int id) {
        String[] pedido = getPedidoPorId(id);
        String[] producto = getProductoPorId(Integer.parseInt(pedido[0]));
        
        return Double.parseDouble(producto[3]);
        
    }
    
    public void insertarPedido(int id, double precio, String fecha, int idMesa) {
        String idStr = String.valueOf(id);
        String precioStr = String.valueOf(precio);
        String idMesaStr = String.valueOf(idMesa);
        
        String[] nuevoPedido = {idStr, precioStr, fecha, idMesaStr};
        
        //this.getPedidos().add(nuevoPedido);
        
        BufferedWriter pedidosWriter = null;
        
        try {
            File archivoPedido = new File(this.rutaPedidos);
            pedidosWriter = new BufferedWriter(new FileWriter(archivoPedido, true));
            
            String rowData = nuevoPedido[0] + "#" + nuevoPedido[1] + "#" + "1" + "#" + nuevoPedido[2] + "#" + nuevoPedido[3];
            
            pedidosWriter.newLine();
            pedidosWriter.write(rowData);
            
        } catch (IOException e) {
            System.out.println("ERROR al insertar pedido: " + e.getMessage());
            
        } finally {
            try {
                pedidosWriter.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al insertar pedido: " + e.getMessage());
            }
        }
    }
    
    public void insertarComanda(int id, int idPedido) {
        
        int[] nuevaComanda = {id, idPedido};
        
        //this.getComandas().add(nuevaComanda);

        BufferedWriter comandasWriter = null;
        
        try {
            File archivoComanda = new File(this.rutaComanda);
            comandasWriter = new BufferedWriter(new FileWriter(archivoComanda, true));
            
            String rowData = nuevaComanda[0] + "#" + nuevaComanda[1];
            
            comandasWriter.newLine();
            comandasWriter.write(rowData);
            
        } catch (IOException e) {
            System.out.println("ERROR al insertar comanda: " + e.getMessage());
            
        } finally {
            try {
                comandasWriter.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al insertar comanda: " + e.getMessage());
            }
        }        
        
    }
    
    public void insertarComandaProducto(int idComanda, int idProducto, int cantidad) {
        /*int[] nuevoComandaProducto = {idComanda, idProducto, cantidad};
        
        this.getComandaProductos().add(nuevoComandaProducto);*/
        
        BufferedWriter comandaProductoWriter = null;
        
        try {
            File archivoComandaProducto = new File(this.rutaComandaProductos);
            comandaProductoWriter = new BufferedWriter(new FileWriter(archivoComandaProducto, true));
            
            String rowData = String.valueOf(idComanda) + "#" + String.valueOf(idProducto) + "#" + String.valueOf(cantidad);
            
            comandaProductoWriter.newLine();
            comandaProductoWriter.write(rowData);
            
        } catch (IOException e) {
            System.out.println("ERROR al insertar producto de comanda: " + e.getMessage());
            
        } finally {
            try {
                comandaProductoWriter.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al insertar producto de comanda: " + e.getMessage());
            }
        }
    }
    
    public void actualizarPrecioTotalPedido(int idPedido, double precio) {
       Iterator<String[]> pedidosIt = this.getPedidos().iterator();
       
       int index = 0;
       
       while (pedidosIt.hasNext()) {
           String[] pedidoActual = pedidosIt.next();
           
           if (pedidoActual[0].equals(String.valueOf(idPedido))) {
               pedidoActual[3] = String.valueOf(precio);
               
               this.getPedidos().set(index, pedidoActual);
           }
           
           index++;
       }
    }
    
    //----------------------Métodos implementados
    private void actualizarPrecioPedido(int idPedido) {
        this.cargarDatosComandas();
        this.cargarDatosComandaProductos();
        
        double precioTotal = 0;
        List<String[]> pedidoProductos = this.obtenerProductosDePedido(idPedido);
        
        for (String[] productoActual : pedidoProductos) {
            double precio = Double.parseDouble(productoActual[2].replace(",", "."));
            precioTotal += precio; //CONTINUAAAAAAAAAAAAAAAAAAAAAAR
        }
    }
    
    private List<String[]> obtenerProductosDePedido(int idPedido) {
        List<int[]> comandasDePedido = this.obtenerComandasDePedido(idPedido);
        List<String[]> pedidoProductos = new ArrayList();
        
        for (int[] comandaActual : comandasDePedido) {
            for (int[] comandaProductoActual : this.comandaProductos) {
                if (comandaProductoActual[0] == comandaActual[0]) {
                    String[] productoOriginal = this.obtenerProducto(comandaProductoActual[1]);
                    pedidoProductos.add(productoOriginal);
                }
            }
        }
        
        return pedidoProductos;
        
    }
    
    private String[] obtenerProducto(int idProducto) {
        for (String[] productoActual : this.productos) {
            if (Integer.parseInt(productoActual[0]) == idProducto) {
                return productoActual;
            }
        }
        
        return null;
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
            String rutaCategorias = System.getProperty("user.dir") + properties.getProperty("path_categorias");
            
            this.rutaComanda = rutaComandas;
            this.rutaComandaProductos = rutaComandaProductos;
            this.rutaPedidos = rutaPedidos;
            this.rutaProductos = rutaProductos;
            this.rutaCategorias = rutaCategorias;
            
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
    
    private void cargarDatosCategorias() {
        List<String[]> categorias = new ArrayList();
        
        BufferedReader categoriasReader = null;
        
        try {
            File archivoCategorias = new File(this.rutaCategorias);
            categoriasReader = new BufferedReader(new FileReader(archivoCategorias));
            
            String fila = "";
            
            while ((fila = categoriasReader.readLine()) != null) {
                if (!fila.isBlank()) {
                    String[] filaSplit = fila.split("#");
                    String rowData[] = {filaSplit[0], filaSplit[1]};
                    categorias.add(rowData);
                }
            }
            
            this.categorias = categorias;
            
        } catch (IOException e) {
            System.out.println("ERROR al cargar datos de categorias: " + e.getMessage());
            
        } finally {
            try {
                categoriasReader.close();
                
            } catch (IOException e) {
                System.out.println("ERROR al cargar datos de categorias: " + e.getMessage());
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
    
    private String[] getCategoriaPorNombre(String nombreCategoria) {
        Iterator<String[]> categoriasIt = this.getCategorias().iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (categoriaActual[1].equals(nombreCategoria)) {
                return categoriaActual;
            }
        }
        
        return null;
    }
    
    private String[] getProductoPorNombre(String nombreProducto) {
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[1].equals(nombreProducto)) {
                return productoActual;
            }
        }
        
        return null;
        
    }
    
    private String[] getProductoPorId(int idProducto) {
        
        Iterator<String[]> productosIt = this.getProductos().iterator();
        
        while (productosIt.hasNext()) {
            String[] productoActual = productosIt.next();
            
            if (productoActual[0].equals(idProducto)) {
                return productoActual;
            }
        }
        
        return null;
    }
    
    private String[] getPedidoPorId(int idPedido) {
        Iterator<String[]> pedidosIt = this.getPedidos().iterator();
        
        while (pedidosIt.hasNext()) {
            String[] pedidoActual = pedidosIt.next();
            
            if (pedidoActual[0].equals(idPedido)) {
                return pedidoActual;
            }
        }
        
        return null;
    }
}
