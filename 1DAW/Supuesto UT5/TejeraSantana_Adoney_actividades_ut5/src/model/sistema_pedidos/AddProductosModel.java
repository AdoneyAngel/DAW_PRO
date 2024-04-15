
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AdoneyDAW
 */

//PRODUCTO: ID, nombre, categoriaID, precio
//CATEGORIA: ID, nombre
//PEDIDO: ID, precio, fecha, idMesa
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
    
    public AddProductosModel() {
        this.productos = new ArrayList();
        this.pedidos = new ArrayList();
        this.categorias = new ArrayList();
        this.comandas = new ArrayList();
        this.comandaProductos = new ArrayList();
        this.pedidoProductos = new ArrayList();
        
        //GENERANDO PRODUCTOS, CATEGORIAS Y PEDIDOS DE PRUEBA
            //CATEGORIAS
        List<String[]> categoriasPRUEBA = new ArrayList<>();
        
        for (int a = 0; a<10; a++) {
            String[] categoriaPRUEBA = {String.valueOf(a), "categoria" + String.valueOf(a)};
            
            categoriasPRUEBA.add(categoriaPRUEBA);
        }
        
        this.categorias = categoriasPRUEBA;
        
            //PRODUCTOS
        List<String[]> productosPRUEBA = new ArrayList<>();
        for (int a = 0; a<5; a++) {
            String nombrePRUEBA = "producto" + String.valueOf(a);
            String categoriaPRUEBA = String.valueOf(a);
            String idPRUEBA = String.valueOf(a);
            String precioPrueba = String.valueOf((a*1.5));
            
            String[] productoPRUEBA = {idPRUEBA, nombrePRUEBA, categoriaPRUEBA, precioPrueba};
            
            productosPRUEBA.add(productoPRUEBA);
        }
        for (int a = 0; a<3; a++) {
            String nombrePRUEBA = "producto" + String.valueOf(a+5);
            String categoriaPRUEBA = String.valueOf(a);
            String idPRUEBA = String.valueOf(a+5);
            String precioPrueba = String.valueOf((a*1.5));
            
            String[] productoPRUEBA = {idPRUEBA, nombrePRUEBA, categoriaPRUEBA, precioPrueba};
            
            productosPRUEBA.add(productoPRUEBA);
        }
        
        this.productos = productosPRUEBA;
        
            // PEDIDOS
            String[] pedido1 = {"0", "23", "0", "1"};           
            String[] pedido2 = {"1", "10", "0", "2"};   
            
            this.pedidos.add(pedido1);
            this.pedidos.add(pedido2);

        
        
        //------------------------------
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
            
            if (categoria[0].equals(productoActual[2])) {
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
        
        this.getPedidos().add(nuevoPedido);
    }
    
    public void insertarComanda(int id, int idPedido) {
        
        int[] nuevaComanda = {id, idPedido};
        
        this.getComandas().add(nuevaComanda);
    }
    
    public void insertarComandaProducto(int idComanda, int idProducto, int cantidad) {
        int[] nuevoComandaProducto = {idComanda, idProducto, cantidad};
        
        this.getComandaProductos().add(nuevoComandaProducto);
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
