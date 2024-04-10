/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
//PEDIDO: ID, idProducto
//COMANDA: ID, idPedido
//COMANDAPRODUCTO: IDcomanda, IDpedido

public class AddProductosModel {
    private List<String[]> categorias;
    private List<String[]> productos;
    private List<String[]> pedidos;
    private List<int[]> comandas;
    private List<int[]> comandaProducto;
    
    public AddProductosModel() {
        this.productos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.comandas = new ArrayList<>();
        this.comandaProducto = new ArrayList<>();
        
        //GENERANDO PRODUCTOS Y CATEGORIAS DE PRUEBA
        List<String[]> categoriasPRUEBA = new ArrayList<>();
        
        for (int a = 0; a<10; a++) {
            String[] categoriaPRUEBA = {String.valueOf(a), "categoria" + String.valueOf(a)};
            
            categoriasPRUEBA.add(categoriaPRUEBA);
        }
        
        this.categorias = categoriasPRUEBA;
        
        List<String[]> productosPRUEBA = new ArrayList<>();
        for (int a = 0; a<10; a++) {
            String nombrePRUEBA = "producto" + String.valueOf(a);
            String categoriaPRUEBA = String.valueOf(a);
            String idPRUEBA = String.valueOf(a);
            String precioPrueba = String.valueOf((a*1.5));
            
            String[] productoPRUEBA = {idPRUEBA, nombrePRUEBA, categoriaPRUEBA, precioPrueba};
            
            productosPRUEBA.add(productoPRUEBA);
        }
        
        this.productos = productosPRUEBA;
        //------------------------------
    }

    public List<String[]> getCategorias() {
        return categorias;
    }

    public List<String[]> getProductos() {
        return productos;
    }

    public List<String[]> getPedidos() {
        return pedidos;
    }

    public List<int[]> getComandas() {
        return comandas;
    }

    public List<int[]> getComandaProducto() {
        return comandaProducto;
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
        
        nombreProductosCategoria = (String[]) productosCategoriaList.toArray();
        
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
        
        this.getComandaProducto().add(nuevoComandaProducto);
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
