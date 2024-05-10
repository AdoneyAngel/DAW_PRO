
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package model.sistema_pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import model.BaseDatosConexionServicio;

/**
 *
 * González Olivares Brandon - Tejera Santana Adoney
 */
public class MesasModel {
    
    private List<String[]> mesas;
    private List<String[]> mesasOcupadas;
    private List<String[]> pedidos;
    private AddProductosModel productosModel;
    private BaseDatosConexionServicio db;
    
    public MesasModel() {
        this.db = new BaseDatosConexionServicio();
        this.mesasOcupadas = new ArrayList();
        this.mesas = new ArrayList();
        this.productosModel = new AddProductosModel();
        this.pedidos = this.productosModel.getPedidos();
        
        this.cargarDatos();
        this.obtenerMesasOcupadas();
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
    
    public List<String[]> getMesasOcupadas() {
        return this.mesasOcupadas;
    }
    
    private void cargarDatos() {
        this.db.abrirConexion();
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = this.db.getConnection().createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+this.db.getDbname()+".mesa ORDER BY id");
            
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nombre = resultset.getString("nombre");
                
                String[] mesaActual = {id, nombre};
                
                this.mesas.add(mesaActual);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR al cargar datos: " + e.getMessage());
            
        } finally {
            this.db.cerrarConexion();
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
            if (Integer.parseInt(pedidoActual[4]) == idMesa && pedidoActual[2].equals("true")) {
                String[] mesaOriginal = obtenerMesaPorId(idMesa);
                String[] mesaOcupada = {mesaOriginal[0], mesaOriginal[1], pedidoActual[0]};
                
                return mesaOcupada;
            }
        }
        
        return null;
    }
    
    
}
