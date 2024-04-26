
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.nuevo_pedido;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import view.pedidos.PedidosProductosView;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class NuevoPedidoController {
    private AddProductosModel productosModel;
    private PedidosProductosView pedidosProductosView;
    private int categoriaSeleccionada;
    private List<String[]> productos;
    private List<String[]> pedidoProductos;
    private String[] pedido;
    private String fechaActual;
    private String ultimoProductoModificado;
    private double precioTotal;
    private int idMesa;
    
    public NuevoPedidoController(int idMesa) {
        this.precioTotal = 0;
        this.productosModel = new AddProductosModel();
        this.productos = new ArrayList(this.productosModel.getProductos());
        this.pedidoProductos = new ArrayList(this.productosModel.getPedidoProductos());
        this.idMesa = idMesa;
        
        DefaultTableModel tableModel = this.productosModel.getTablaVaciaModel();
        String[] nombreCategorias = productosModel.getNombreCategorias();
        
        //Se agrega la fecha actual
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaActual = sdf.format(todayDate);
        
        //Se genera el pedido actual int id, double precio, String fecha, int idMesa
        int idPedidoActual = this.generarPedidoId();
        String[] pedidoActual = {String.valueOf(idPedidoActual), "0", fechaActual, String.valueOf(idMesa)};
        this.pedido = pedidoActual;
        
        generarVentana(tableModel, nombreCategorias);
    }
    
    public void destruirVentana() {
        this.pedidosProductosView.dispose();
    }
    
    public void recalcularTotal() {
        List<String[]> productosPedido = this.pedidoProductos;
        double precioTotal = 0;
        
        for (String[] productoActula : productosPedido) {
            String[] productoOriginal = this.buscarProducto(Integer.parseInt(productoActula[1]));
            int cantidad = Integer.parseInt(productoActula[2]);
            double precio = Double.parseDouble(productoOriginal[2].replace(",", "."));
            
            double precioProducto = precio*cantidad;
            
            precioTotal += precioProducto;
                    
        }
        
        this.precioTotal = precioTotal;
        this.pedidosProductosView.getLblTotal().setText("Total: " + String.valueOf(precioTotal) + "€");
    }
    
    public int getIndiceProductoTablaModel(String nombreProducto) {
        JTable tablaProductos = this.pedidosProductosView.getTable();
        
        for (int a = 0; a<tablaProductos.getRowCount(); a++) {
            String nombreProductoActual = (String) tablaProductos.getValueAt(a, 0);
            
            if (nombreProducto.equals(nombreProductoActual)) {
                return a;
            }
        }
        
        return -1;
    }
    
    public void generarVentana(DefaultTableModel vaciaTablaModel, String[] categoriasBotones) {
        this.pedidosProductosView = new PedidosProductosView(vaciaTablaModel, categoriasBotones);
        this.pedidosProductosView.setTitle("Crear pedido");
        
        this.ocultarLbl();
        
        List<String[]> categorias = this.productosModel.getCategorias();
        
        if (categorias.size() <= 0) {
            this.pedidosProductosView.getLblNoHayCategorias().setVisible(true);
        }
        
        //Por defecto se cogerá la primera categoría
        this.categoriaSeleccionada = Integer.parseInt(categorias.get(0)[0]);
        
        //GENERANDO BOTONES Y SUS FUNCIONES
        this.pedidosProductosView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                irAtras();
            }
        });
        
        //  Generando categorias
        for (String[] categoriaActual : categorias) {
            String nombreCategoria = categoriaActual[1];
            int idCategoria = Integer.parseInt(categoriaActual[0]);
            
            JButton btnCategoria = new JButton(nombreCategoria);
            btnCategoria.setPreferredSize(new Dimension(160, 40));
            
            //      Método del boton
            btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                categoriaSeleccionada = idCategoria;
                
                actualizarBotonesProductos();
            }
            });
            
            
            this.pedidosProductosView.getCategoriaPanel().add(btnCategoria);
        }
        
        this.actualizarBotonesProductos();
        
        //  Botones de añadir y reducir cantidad de productos
        
        this.pedidosProductosView.getBtnMas().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProducto = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    int cantidad = Integer.parseInt(filaSeleccionada[2]) + 1; //Se le suma 1

                    actualizarPedidoProducto(idProducto, cantidad);

                    actualizarTabla();     
                    
                } else {
                    pedidosProductosView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        this.pedidosProductosView.getBtnMenos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProducto = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    int cantidad = Integer.parseInt(filaSeleccionada[2]) - 1; //Se le resta 1
                    
                    if (cantidad >0) {
                        actualizarPedidoProducto(idProducto, cantidad);
                        actualizarTabla();  
                        
                    } else {
                        borrarPedidoProducto(idProducto);
                        actualizarTabla();
                        
                    }   
                    
                } else {
                    pedidosProductosView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        
        //Boton de eliminar seleccion
        
        this.pedidosProductosView.getBtnEliminarSeleccion().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProductoSeleccionado = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    
                    borrarPedidoProducto(idProductoSeleccionado);
                    
                    actualizarTabla();
                    
                } else {
                    pedidosProductosView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        
        this.pedidosProductosView.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                
                if (pedidoProductos.size() > 0) {
                    insertarPedido();
                    irAtras();                    
                } else {
                    pedidosProductosView.getLblNoGuardar().setVisible(true);
                }
            }
        });
        
        this.actualizarTabla();
        
        this.pedidosProductosView.setVisible(true);
    }
    
    public void insertarActionListenerBotonesProductos() {
        JButton[] productoBotones = this.pedidosProductosView.getProductosButtons();
        
        for (JButton botonActual : productoBotones) {
            botonActual.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String nombreProducto = botonActual.getText();
                    
                    insertarProductoTablaModel(nombreProducto);
                    
                    actualizarTabla();
                }
            });
        }
    }
    
    private void irAtras() {
        MesasNuevoPedidoController mesasNuevoPedidoController = new MesasNuevoPedidoController();
        this.destruirVentana();
    }
    
    private void insertarPedido() {
        int comandaId = generarComandaId();
        List<int[]> comandaProductos = new ArrayList();
        
        for (String[] productoActual : this.pedidoProductos) {
            int idProducto = Integer.parseInt(productoActual[1]);            
            int cantidad = Integer.parseInt(productoActual[2]);
            
            int[] pedidoProducto = {comandaId, idProducto, cantidad};
            
            comandaProductos.add(pedidoProducto);
        }
        
        double precioTotal = this.precioTotal;
        String fecha = this.pedido[2];
        int pedidoId = Integer.parseInt(this.pedido[0]);
        int idMesa = this.idMesa;
        
        this.pedido[1] = String.valueOf(precioTotal);
        
        this.productosModel.insertarComanda(comandaId, pedidoId);
        this.productosModel.insertarPedido(pedidoId, precioTotal, fecha, idMesa);
        
        for (int[] comandaProductoActual : comandaProductos) {
            this.productosModel.insertarComandaProducto(comandaProductoActual[0], comandaProductoActual[1], comandaProductoActual[2]);
        }
        
        //this.productosModel.getComandaProductos().addAll(comandaProductos);
    }
    
    private void borrarPedidoProducto(int idProducto) {
        String[] productoPedido = this.buscarProductoPedido(idProducto);
        
        this.pedidoProductos.remove(productoPedido);
        
        this.ultimoProductoModificado = null;
    }
    
    private String[] obtenerFilaSeleccionada() {
        JTable tabla = this.pedidosProductosView.getTable();
        int filaIndex = tabla.getSelectedRow();
        
        if (filaIndex < 0 && Objects.isNull(this.ultimoProductoModificado)) {
            return null;
            
        } else if(filaIndex < 0 && !Objects.isNull(this.ultimoProductoModificado)) {
            int idProducto = this.productosModel.getIdProductoNombre(this.ultimoProductoModificado);
            String[] productoPedido = this.buscarProductoPedido(idProducto);
            String[] productoOriginal = this.buscarProducto(idProducto);
            
            String precio = productoOriginal[3];
            String unidades = productoPedido[2];
            String subtotal = String.valueOf(Integer.parseInt(unidades)*Double.parseDouble(precio));
            
            String[] filaSeleccionada = {this.ultimoProductoModificado, precio, unidades, subtotal};
            
            return filaSeleccionada;
            
        } else {
            String nombreProducto = (String) tabla.getValueAt(filaIndex, 0);
            String precioProducto = (String) tabla.getValueAt(filaIndex, 1);
            String unidadesProducto = (String) tabla.getValueAt(filaIndex, 2);
            String subtotalProducto = (String) tabla.getValueAt(filaIndex, 3);
            
            String[] filaSeleccionada = {nombreProducto, precioProducto, unidadesProducto, subtotalProducto};
            
            this.ultimoProductoModificado = nombreProducto;
            
            return filaSeleccionada;
        }
    }
    
    private void ocultarLbl() {
        this.pedidosProductosView.getLblNoGuardar().setVisible(false);
        this.pedidosProductosView.getLblNoHayCategorias().setVisible(false);
        this.pedidosProductosView.getLblNoHayProductos().setVisible(false);
        this.pedidosProductosView.getLblNoSeleccion().setVisible(false);
    }
    
    private void actualizarPedidoProducto(int idProducto, int cantidad) {
        String[] pedidoProductoOriginal = this.buscarProductoPedido(idProducto);
        
        int indexPedidoProductoOriginal = this.pedidoProductos.indexOf(pedidoProductoOriginal);
        
        String[] pedidoProductoActualizado = {this.pedido[0], String.valueOf(idProducto), String.valueOf(cantidad)};
        
        this.pedidoProductos.set(indexPedidoProductoOriginal, pedidoProductoActualizado);
    }
    
    private DefaultTableModel actualizarTabla() {
        List<String[]> pedidoProductos = new ArrayList();
        List<String[]> productosParaTabla = new ArrayList();
        DefaultTableModel tableModel;
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        
        //SE INSERTAN LOS DATOS DE pedidoProductos A LA NUEVA LISTA
        for (String[] producto : this.pedidoProductos) {
            String[] productoCopia = Arrays.copyOf(producto, producto.length);
            pedidoProductos.add(productoCopia);
        }
        
        //Se inserta los datos de los productos a la variable productosParaTabla
        for (String[] pedidoProductoActual : pedidoProductos) {
            int idPedidoProductoActual = Integer.parseInt(pedidoProductoActual[1]);
            
            //Se busca el producto original al que hace referencia el pedidoProductoActual
            String[] productoOriginal = this.buscarProducto(idPedidoProductoActual);
            
            //Se extrae los datos necesarios para las columnas de la tabla
            String nombrePedidoProducto = productoOriginal[1];
            double precio = Double.parseDouble(productoOriginal[2].replace(",", "."));
            int cantidad = Integer.parseInt(pedidoProductoActual[2]);
            double subtotal = cantidad*precio;
            
            String[] nuevoProductoTabla = {nombrePedidoProducto, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(subtotal)};
            
            productosParaTabla.add(nuevoProductoTabla);
        }
        
        //Se transforma la lista para tabla a un Array
        String[][] tableData = new String[productosParaTabla.size()][4];
        tableData = productosParaTabla.toArray(tableData);
        
        tableModel = new DefaultTableModel(tableData, tableColumns) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        //Se establece el nuevo modelo
        this.pedidosProductosView.getTable().setModel(tableModel);
        
        //Se actualiza el precio total del pedido
        this.recalcularTotal();
        
        return tableModel;
    }
    
    private void actualizarBotonesProductos() {
        this.limpiarBotonesProductos();
        
        //Se insrtan los botones con la funcion addProductosButtons de la vista
        
        String[] CategoriaSeleccionado = this.getCategoria(this.categoriaSeleccionada);
        String[] nombreProductosPorCategoria = this.productosModel.getNombreProductosCategoria(CategoriaSeleccionado[1]);
        
        this.pedidosProductosView.addProductosButtons(nombreProductosPorCategoria);
        
        //Se agrega los eventos de los botones
        
        this.insertarActionListenerBotonesProductos();
        
        this.pedidosProductosView.setVisible(true);
    }
    
    private int generarPedidoId() {
        List<String[]> pedidos = this.productosModel.getPedidos();
        
        int nuevoId = -1;
        boolean idValido = false;
        
        while (!idValido || nuevoId < 0) {
            nuevoId++;
            
            idValido = true;
            
            for (String[] pedido : pedidos) {
                if (pedido[0].equals(String.valueOf(nuevoId))) {
                    idValido = false;
                }
            }
        }
        
        return nuevoId;
    }
    
    private int generarComandaId() {
        List<int[]> comandas = this.productosModel.getComandas();
        
        int nuevoId = -1;
        boolean idValido = false;
        
        while (!idValido || nuevoId < 0) {
            nuevoId++;
            
            idValido = true;
            
            for (int[] coamnda : comandas) {
                if (coamnda[0] == nuevoId) {
                    idValido = false;
                }
            }
        }
        
        return nuevoId;
    }
    
    private void insertarProductoTablaModel(String nombreProducto) {
        int idProducto = productosModel.getIdProductoNombre(nombreProducto);
        
        if (!existeProductoPedido(idProducto)) {
            String[] productoPedido = {this.pedido[0], String.valueOf(idProducto), "1"};
            
            this.pedidoProductos.add(productoPedido);
            
        } else {
            String[] productoPedido = this.buscarProductoPedido(idProducto);
            String[] productoPedidoActualizado = productoPedido;
            
            int cantidad = Integer.parseInt(productoPedido[2]);
            cantidad += 1;
            
            productoPedidoActualizado[2] = String.valueOf(cantidad);
            
            actualizarPedidoProducto(idProducto, cantidad);
        }
        this.ultimoProductoModificado = nombreProducto;
    }
    
    private String[] buscarProductoPedido(int idProducto) {
        List<String[]> productos = this.pedidoProductos;
        
        for (String[] productoActual : productos) {
            if (productoActual[1].equals(String.valueOf(idProducto))) {
                return productoActual;
            }
        }
        
        return null;
    }
    
    private boolean existeProductoPedido(int idProducto) {
        List<String[]> productos = this.pedidoProductos;
        
        for (String[] productoActual : productos) {
            if (productoActual[1].equals(String.valueOf(idProducto))) {
                return true;
            }
        }
        
        return false;
    }
    
    private String[] buscarProducto(int idProducto) {
        List<String[]> productos = this.productos;
        
        for (String[] productoActual : productos) {
            if (productoActual[0].equals(String.valueOf(idProducto))) {
                return productoActual;
            }
        }
        
        return null;
    }
    
    private void limpiarBotonesProductos() {
        JButton[] botones = this.pedidosProductosView.getProductosButtons();
        
        if (botones.length > 0) {
            for (JButton botonActual : botones) {
                botonActual.setVisible(false);
                this.pedidosProductosView.getProductoPanel().remove(botonActual);
            }
        }

    }
    
    private String[] getCategoria(int idCategoria) {
        Iterator<String[]> categoriasIt = this.productosModel.getCategorias().iterator();
        
        while (categoriasIt.hasNext()) {
            String[] categoriaActual = categoriasIt.next();
            
            if (String.valueOf(idCategoria).equals(categoriaActual[0])) {
                return categoriaActual;
            }
        }
        
        return null;
    }
}
