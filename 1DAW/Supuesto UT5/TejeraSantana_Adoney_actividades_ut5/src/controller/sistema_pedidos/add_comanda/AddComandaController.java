
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.sistema_pedidos.add_comanda;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import view.pedidos.PedidosComandaView;

/**
 *
 * @author AdoneyDAW
 */
public class AddComandaController {
    private AddProductosModel productosModel;
    private PedidosComandaView pedidosComandasView;
    private int categoriaSeleccionada;
    private List<String[]> productos;
    private List<int[]> comandaProductos;
    private int[] comanda;
    private String ultimoProductoModificado;
    private double precioTotal;
    private int idPedido;
    
    public AddComandaController(int idPedido) {
        this.precioTotal = 0;
        this.productosModel = new AddProductosModel();
        this.productos = new ArrayList(this.productosModel.getProductos()) ;
        this.comandaProductos = new ArrayList(this.productosModel.getComandaProductos()) ;
        this.idPedido = idPedido;
        
        DefaultTableModel tableModel = this.productosModel.getTablaVaciaModel();
        String[] nombreCategorias = productosModel.getNombreCategorias();
        
        //Se genera la comanda actual int id, int idPedido
        int idComandaActual = this.generarComandaId();
        int[] comandaActual = {idComandaActual, idPedido};
        this.comanda = comandaActual;
        
        generarVentana(tableModel, nombreCategorias);
    }
    
    public void destruirVentana() {
        this.pedidosComandasView.setVisible(false);
    }
    
    public void recalcularTotal() {
        List<int[]> productosComanda = this.comandaProductos;
        double precioTotal = 0;
        
        for (int[] productoActula : productosComanda) {
            String[] productoOriginal = this.buscarProducto(productoActula[1]);
            int cantidad = productoActula[2];
            double precio = Double.parseDouble(productoOriginal[3]);
            
            double precioProducto = precio*cantidad;
            
            precioTotal += precioProducto;
                    
        }
        
        this.precioTotal = precioTotal;
        this.pedidosComandasView.getLblTotal().setText("Total: " + String.valueOf(precioTotal) + "€");
    }
    
    public int getIndiceProductoTablaModel(String nombreProducto) {
        JTable tablaProductos = this.pedidosComandasView.getTable();
        
        for (int a = 0; a<tablaProductos.getRowCount(); a++) {
            String nombreProductoActual = (String) tablaProductos.getValueAt(a, 0);
            
            if (nombreProducto.equals(nombreProductoActual)) {
                return a;
            }
        }
        
        return -1;
    }
    
    public void generarVentana(DefaultTableModel vaciaTablaModel, String[] categoriasBotones) {
        this.pedidosComandasView = new PedidosComandaView();
        this.pedidosComandasView.getTable().setModel(vaciaTablaModel);
        
        this.ocultarLbl();
        
        List<String[]> categorias = this.productosModel.getCategorias();
        
        if (categorias.size() <= 0) {
            this.pedidosComandasView.getLblNoHayCategorias().setVisible(true);
        }
        
        //Por defecto se cogerá la primera categoría
        this.categoriaSeleccionada = Integer.parseInt(categorias.get(0)[0]);
        
        //GENERANDO BOTONES Y SUS FUNCIONES
        this.pedidosComandasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
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
            
            
            this.pedidosComandasView.getCategoriaPanel().add(btnCategoria);
        }
        
        this.actualizarBotonesProductos();
        
        //  Botones de añadir y reducir cantidad de productos
        
        this.pedidosComandasView.getBtnMas().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProducto = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    int cantidad = Integer.parseInt(filaSeleccionada[2]) + 1; //Se le suma 1

                    actualizarComandaProducto(idProducto, cantidad);

                    actualizarTabla();     
                    
                } else {
                    pedidosComandasView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        this.pedidosComandasView.getBtnMenos().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProducto = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    int cantidad = Integer.parseInt(filaSeleccionada[2]) - 1; //Se le resta 1
                    
                    if (cantidad >0) {
                        actualizarComandaProducto(idProducto, cantidad);
                        actualizarTabla();  
                        
                    } else {
                        borrarComandaProducto(idProducto);
                        actualizarTabla();
                        
                    }   
                    
                } else {
                    pedidosComandasView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        
        //Boton de eliminar seleccion
        
        this.pedidosComandasView.getBtnEliminarSeleccion().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarLbl();
                String[] filaSeleccionada = obtenerFilaSeleccionada();
                
                if (!Objects.isNull(filaSeleccionada)) {
                    int idProductoSeleccionado = productosModel.getIdProductoNombre(filaSeleccionada[0]);
                    
                    borrarComandaProducto(idProductoSeleccionado);
                    
                    actualizarTabla();
                    
                } else {
                    pedidosComandasView.getLblNoSeleccion().setVisible(true);
                }
            }
        });
        
        this.pedidosComandasView.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarComanda();
                irAtras();
            }
        });
        
        this.pedidosComandasView.setVisible(true);
    }
    
    public void insertarActionListenerBotonesProductos() {
        JButton[] productoBotones = this.pedidosComandasView.getProductosButtons();
        
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
    
    private void addProductosButtons (String[] nombreProductos) {
        for (String nombreProducot : nombreProductos) {
            JButton btnProducto = new JButton (nombreProducot);
            btnProducto.setPreferredSize(new Dimension(160, 40));
            
            this.pedidosComandasView.getProductoPanel().add(btnProducto);
        }
    }
    
    private void irAtras() {
        MesasAddComandaController mesasNuevoComandaController = new MesasAddComandaController();
        this.destruirVentana();
    }
    
    private void insertarComanda() {
        int id = this.comanda[0];
        int idPedido = this.idPedido;
        
        this.productosModel.getComandaProductos().addAll(comandaProductos);
        this.productosModel.insertarComanda(id, idPedido);
    }
    
    private void borrarComandaProducto(int idProducto) {
        int[] productoPedido = this.buscarProductoComanda(idProducto);
        
        this.comandaProductos.remove(productoPedido);
        
        this.ultimoProductoModificado = null;
    }
    
    private String[] obtenerFilaSeleccionada() {
        JTable tabla = this.pedidosComandasView.getTable();
        int filaIndex = tabla.getSelectedRow();
        
        if (filaIndex < 0 && Objects.isNull(this.ultimoProductoModificado)) {
            return null;
            
        } else if(filaIndex < 0 && !Objects.isNull(this.ultimoProductoModificado)) {
            int idProducto = this.productosModel.getIdProductoNombre(this.ultimoProductoModificado);
            int[] productoComanda = this.buscarProductoComanda(idProducto);
            String[] productoOriginal = this.buscarProducto(idProducto);
            
            String precio = productoOriginal[3];
            int unidades = productoComanda[2];
            double subtotal = unidades*Double.parseDouble(precio);
            
            String[] filaSeleccionada = {this.ultimoProductoModificado, precio, String.valueOf(unidades), String.valueOf(subtotal)};
            
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
        this.pedidosComandasView.getLblNoHayCategorias().setVisible(false);
        this.pedidosComandasView.getLblNoHayProductos().setVisible(false);
        this.pedidosComandasView.getLblNoSeleccion().setVisible(false);
    }
    
    private void actualizarComandaProducto(int idProducto, int cantidad) {
        int[] comandaProductoOriginal = this.buscarProductoComanda(idProducto);
        
        int indexComandaProductoOriginal = this.comandaProductos.indexOf(comandaProductoOriginal);
        
        int[] comandaProductoActualizado = {this.comanda[0], idProducto, cantidad};
        
        this.comandaProductos.set(indexComandaProductoOriginal, comandaProductoActualizado);
    }
    
    private DefaultTableModel actualizarTabla() {
        List<int[]> comandaProductos = new ArrayList();
        List<String[]> productosParaTabla = new ArrayList();
        DefaultTableModel tableModel;
        String[] tableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
        
        //SE INSERTAN LOS DATOS DE comandaProductos A LA NUEVA LISTA
        for (int[] producto : this.comandaProductos) {
            int[] productoCopia = Arrays.copyOf(producto, producto.length);
            comandaProductos.add(productoCopia);
        }
        
        //Se inserta los datos de los productos a la variable productosParaTabla
        for (int[] comandaProductoActual : comandaProductos) {
            int idComandaProductoActual = comandaProductoActual[1];
            
            //Se busca el producto original al que hace referencia el comandaProductoActual
            String[] productoOriginal = this.buscarProducto(idComandaProductoActual);
            
            //Se extrae los datos necesarios para las columnas de la tabla
            String nombreComandaProducto = productoOriginal[1];
            double precio = Double.parseDouble(productoOriginal[3]);
            int cantidad = comandaProductoActual[2];
            double subtotal = cantidad*precio;
            
            String[] nuevoProductoTabla = {nombreComandaProducto, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(subtotal)};
            
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
        this.pedidosComandasView.getTable().setModel(tableModel);
        
        //Se actualiza el precio total de la comanda
        this.recalcularTotal();
        
        return tableModel;
    }
    
    private void actualizarBotonesProductos() {
        this.limpiarBotonesProductos();
        
        //Se insrtan los botones con la funcion addProductosButtons de la vista
        
        String[] CategoriaSeleccionado = this.getCategoria(this.categoriaSeleccionada);
        String[] nombreProductosPorCategoria = this.productosModel.getNombreProductosCategoria(CategoriaSeleccionado[1]);
        
        this.addProductosButtons(nombreProductosPorCategoria);
        
        //Se agrega los eventos de los botones
        
        this.insertarActionListenerBotonesProductos();
        
        this.pedidosComandasView.setVisible(true);
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
        
        if (!existeProductoComanda(idProducto)) {
            int[] productoComanda = {this.comanda[0], idProducto, 1};
            
            this.comandaProductos.add(productoComanda);
            
        } else {
            int[] productoComanda = this.buscarProductoComanda(idProducto);
            int[] productoComandaActualizado = productoComanda;
            
            int cantidad = productoComanda[2];
            cantidad += 1;
            
            productoComandaActualizado[2] = cantidad;
            
            actualizarComandaProducto(idProducto, cantidad);
        }
        this.ultimoProductoModificado = nombreProducto;
    }
    
    private int[] buscarProductoComanda(int idProducto) {
        List<int[]> productos = this.comandaProductos;
        
        for (int[] productoActual : productos) {
            if (productoActual[1] == idProducto) {
                return productoActual;
            }
        }
        
        return null;
    }
    
    private boolean existeProductoComanda(int idProducto) {
        List<int[]> productos = this.comandaProductos;
        
        for (int[] productoActual : productos) {
            if (productoActual[1] == idProducto) {
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
        JButton[] botones = this.pedidosComandasView.getProductosButtons();
        
        if (botones.length > 0) {
            for (JButton botonActual : botones) {
                botonActual.setVisible(false);
                this.pedidosComandasView.getProductoPanel().remove(botonActual);
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
