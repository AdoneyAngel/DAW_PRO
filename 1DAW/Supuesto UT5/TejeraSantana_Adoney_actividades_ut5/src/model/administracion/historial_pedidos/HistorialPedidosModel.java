
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.administracion.historial_pedidos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AdoneyDAW
 */

//PEDIDOS: ID, precio, fecha, mesa, hecho

public class HistorialPedidosModel {
    private List<String[]> pedidos;
    private List<String[]> mesas;
    private List<String[]> pedidoProductos;
    private List<String[]> productos;
    
    public HistorialPedidosModel() {
        this.pedidos = new ArrayList();
        this.mesas = new ArrayList();
        this.productos = new ArrayList();
        this.pedidoProductos = new ArrayList();
        
        //Generando valores de ejemplo
        //  Mesas
        for (int a = 0; a<5; a++) {
            String nombreMesa = "Mesa" + String.valueOf(a);
            String[] nuevaMesa = {String.valueOf(a), nombreMesa};
            mesas.add(nuevaMesa);
        }
        //  Pedidos
        for (int a = 0; a<3; a++) {
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
        }
        
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
                if (c == 2) {
                    return true;
                }
                
                return false;
            }
        };
        
        //Se obtienen los datos
        
        for (String[] pedidoActual : this.pedidos) {
            String id = pedidoActual[0];
            String precio = pedidoActual[1];
            String fecha = pedidoActual[2];
            String nombreMesa = this.buscarNombreMesa(Integer.parseInt(pedidoActual[3]));
            String hecho = pedidoActual[4];
            
            Object[] nuevoPedidoParaTabla = {id, precio, new Boolean(Boolean.valueOf(hecho)), fecha, nombreMesa};
            
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
}
