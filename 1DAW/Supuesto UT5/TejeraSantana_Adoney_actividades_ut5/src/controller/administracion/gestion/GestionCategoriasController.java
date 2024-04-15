
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package controller.administracion.gestion;

import controller.administracion.MenuAdministracionController;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.administracion.gestion.GestionCategoriasModel;
import view.administracion.gestion.AdministracionCategoriasView;

/**
 *
 * @author AdoneyDAW
 */
public class GestionCategoriasController {
    private GestionCategoriasModel model;
    private AdministracionCategoriasView administracionCategoriasView;
    
    public GestionCategoriasController() {
        this.model = new GestionCategoriasModel();
        
        generarVentana(this.model.getModelCategorias());
    }
    
    public void generarVentana(DefaultTableModel pedidosTablaModel) {  
        this.administracionCategoriasView = new AdministracionCategoriasView(pedidosTablaModel);
        this.hiddenLbl();
        
        //DEFINICION DE METODOS
        this.administracionCategoriasView.getBtnAtras().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAdministracionController menuAdministracionController = new MenuAdministracionController();
                
                destruirVentana();
            }
        });
        
        this.administracionCategoriasView.getBtnCrear().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String nuevoNombre = administracionCategoriasView.getTextFieldNewNombre().getText();
                
                //Excepciones
                hiddenLbl();
                
                if (nuevoNombre.isBlank() || nuevoNombre.isEmpty()) {
                    administracionCategoriasView.getLblNewVacio().setVisible(true);
                    
                } else if(existeCategoriaNombre(nuevoNombre)) {
                    administracionCategoriasView.getLblNewUnico().setVisible(true);
                    
                } else {
                    int categoriaId = generarId();
                    
                    model.insertarCategoria(categoriaId, nuevoNombre);
                    TableModel newTableModel = model.getModelCategorias();
                    
                    administracionCategoriasView.getTable().setModel(newTableModel);
                }
                
                clearFields();
                //--------------
                
            }
        });
        
        this.administracionCategoriasView.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String nuevoNombre = administracionCategoriasView.getTextFieldEditNombre().getText();
                int categoriaSeleccionadaIndex = administracionCategoriasView.getTable().getSelectedRow();
                
                //Excepciones
                hiddenLbl();
                
                if (nuevoNombre.isBlank() || nuevoNombre.isEmpty()) {
                    administracionCategoriasView.getLblEditVacio().setVisible(true);
                    
                } else if (existeCategoriaNombre(nuevoNombre)) {
                    administracionCategoriasView.getLblEditUnico().setVisible(true);
                    
                } else if(categoriaSeleccionadaIndex == -1) {
                    administracionCategoriasView.getLblEditSeleccion().setVisible(true);
                    
                } else {
                    String categoriaSeleccionadaId = (String) administracionCategoriasView.getTable().getValueAt(categoriaSeleccionadaIndex, 0);
                    int categoriaSeleccionadaIdInt = Integer.parseInt(categoriaSeleccionadaId);
                    
                    model.actualizarCategoria(categoriaSeleccionadaIdInt, nuevoNombre);
                    TableModel newTableModel = model.getModelCategorias();
                    
                    administracionCategoriasView.getTable().setModel(newTableModel);
                }
                
                clearFields();
                //--------------
                
            }
        });
        
        this.administracionCategoriasView.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int categoriaSeleccionadaIndex = administracionCategoriasView.getTable().getSelectedRow();
                
                //Excepciones
                hiddenLbl();
                
                if(categoriaSeleccionadaIndex != -1) {
                    String categoriaSeleccionadaId = (String) administracionCategoriasView.getTable().getValueAt(categoriaSeleccionadaIndex, 0);
                    int categoriaSeleccionadaIdInt = Integer.parseInt(categoriaSeleccionadaId);
                    
                    model.eliminarCategoria(categoriaSeleccionadaIdInt);
                    TableModel newTableModel = model.getModelCategorias();
                    
                    administracionCategoriasView.getTable().setModel(newTableModel);
                }
                
                clearFields();
                //--------------
                
            }
        });
        //-----------------------
        
        this.administracionCategoriasView.setVisible(true);
        
    }
    
    public void destruirVentana() {
        this.administracionCategoriasView.setVisible(false);
    }
    
    private void clearFields() {
        this.administracionCategoriasView.getTextFieldEditNombre().setText("");
        this.administracionCategoriasView.getTextFieldNewNombre().setText("");
    }
    
    private int generarId() {
        boolean idValido = false;
        int nuevoId = -1;
        
        while (!idValido || nuevoId < 0) {
            nuevoId++;
            
            idValido = !existeCategoriaId(nuevoId);
        }
        
        return nuevoId;
    }
    
    private void hiddenLbl() {
        this.administracionCategoriasView.getLblNewVacio().setVisible(false);
        this.administracionCategoriasView.getLblEditVacio().setVisible(false);
        this.administracionCategoriasView.getLblNewUnico().setVisible(false);
        this.administracionCategoriasView.getLblEditUnico().setVisible(false);
        this.administracionCategoriasView.getLblEditSeleccion().setVisible(false);
    }
    
    private boolean existeCategoriaId(int id) {
        TableModel tableModel = this.model.getModelCategorias();
        
        for (int a = 0; a<tableModel.getRowCount(); a++) {
            String rowData = (String) tableModel.getValueAt(a, 0);
            
            if (Integer.parseInt(rowData) == id) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean existeCategoriaNombre(String nombre) {
        TableModel tableModel = this.model.getModelCategorias();
        
        for (int a = 0; a<tableModel.getRowCount(); a++) {
            String rowData = (String) tableModel.getValueAt(a, 1);
            
            if (rowData.equals(nombre)) {
                return true;
            }
        }
        
        return false;
    }
}
