
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.historial_pedidos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import model.sistema_pedidos.MesasModel;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class DetallesPedidoModel {
    List<String[]> pedidos;
    List<String[]> pedidoProductos;
    List<String[]> productos;
    List<String[]> mesas;
    
    public DetallesPedidoModel() {
        //Agregando valores de ejemplo
        //Se utilizara unos modelo ya hecho, usan la misma estructura y ya contiene ejemplos
        MesasModel mesasModelEjemplo = new MesasModel();
        AddProductosModel productosModelEjemplo = new AddProductosModel();
        this.pedidos = productosModelEjemplo.getPedidos();
        this.productos = productosModelEjemplo.getProductos();
        this.pedidoProductos = productosModelEjemplo.getPedidoProductos();
        this.mesas = mesasModelEjemplo.getMesas();
        
    }
    
    public DefaultTableModel getDetallesPedidoModel(int idPedido) {
        DefaultTableModel tableModel;
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        List<String[]> productosDePedido = this.getPedidoProductos(idPedido);
        
        tableModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public boolean isCellEditable (int r, int c) {
                return false;
            }
        };
        
        //Se inserta cada uno de los productos a la tabla
        
        for (String[] productoActual : productosDePedido) {
            tableModel.addRow(productoActual);
        }
        
        return tableModel;
        
    }
    
    public Object[] getPropiedadesPedido(int idPedido) {
        Object[] detalles;
        String[] pedidoOriginal = this.getPedido(idPedido);
        
        String fecha = pedidoOriginal[2];
        double precio = Double.parseDouble(pedidoOriginal[1]);
        boolean enCurso = Boolean.parseBoolean(pedidoOriginal[4]);
        String nombreMesa = this.getNombreMesa(Integer.parseInt(pedidoOriginal[3]));
        
        detalles = new Object[] {idPedido, fecha, enCurso, nombreMesa, precio};
        
        return detalles;
    }
    
    private String getNombreMesa(int idMesa) {
        for (String[] mesaActual : this.mesas) {
            if (mesaActual[0].equals(String.valueOf(idMesa))) {
                return mesaActual[1];
            }
        }
        
        return null;
    }
    
    private String[] getPedido(int idPedido) {
        for (String[] pedidoActual : this.pedidos) {
            if (pedidoActual[0].equals(String.valueOf(idPedido))) {
                return pedidoActual;
            }
        }
        
        return null;
    }
    
    private List<String[]> getPedidoProductos(int idPedido) {
        List<String[]> productos = new ArrayList();
        
        for (String[] productoActual : this.pedidoProductos) {
            if (productoActual[0].equals(String.valueOf(idPedido))) {
                int idProducto = Integer.parseInt(productoActual[1]);
                String[] productoOritinal = this.getProducto(idProducto);
                int cantidad = Integer.parseInt(productoActual[2]);
                double precio = Double.parseDouble(productoOritinal[3]);
                double subtotal = precio*cantidad;
                String nombreProducto = productoOritinal[1];

                String[] productoParaTabla = {nombreProducto, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(subtotal)};

                productos.add(productoParaTabla);                
            }
                    
        }
        
        return productos;
    }
    
    private String[] getProducto (int idProducto) {
        for (String[] productoActual : this.productos) {
            if (String.valueOf(idProducto).equals(productoActual[0])) {
                return productoActual;
            }
        }
        
        return null;
    }
}
