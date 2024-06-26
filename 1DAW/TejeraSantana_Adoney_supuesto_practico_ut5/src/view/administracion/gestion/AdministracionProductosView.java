
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.administracion.gestion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * * González Olivares Brandon - Tejera Santana Adoney
 */
public class AdministracionProductosView extends javax.swing.JFrame {

    /**
     * Creates new form AdministracionProductosView
     */
    
    private final String[] categoriasTableColumns = {"ID", "Nombre", "Precio", "Categoría"};
    private DefaultTableModel categoriasTableModel;
    
    public AdministracionProductosView() {
        String[][] temporalCategoriasTableData = {{"1", "Coca Cola", "1,8", "Refrescos"}, {"2", "Bocadillo de Lomo", "2", "Bocadillos"}, {"3", "Hamburguesa con queso", "5,5", "Menus"}};
        
        this.categoriasTableModel = new DefaultTableModel(temporalCategoriasTableData, this.categoriasTableColumns) {
            @Override
            public boolean isCellEditable(int r1, int r2) {
                return false;
            }
        };
        
        initComponents();
    }
    
    public JTextField getFieldNombreCrear() {
        return this.fieldCrearproductonombre;
    }    
    public JSpinner getFieldPrecioCrear() {
        return this.fieldCrearproductoprecio;
    }
    public JTextField getFieldNombreEditar() {
        return this.fieldEditarproductonombre;
    } 
    public JSpinner getFieldPrecioEditar() {
        return this.fieldEditarproductoprecio;
    }
    public JComboBox getComboCrear() {
        return this.comboCrearProducto;
    }
    public JComboBox getComboEditar() {
        return this.comboEditarProducto;
    }
    public JButton getBtnCrearProduct() {
        return this.btnCrearproducto;
    }
    public JButton getBtnEditarProducto() {
        return this.btnEditarProducto;
    }
    public JTable getTableProductos() {
        return this.tProductos;
    }
    public JButton getBtnEliminarSeleccion() {
        return this.btnEliminarseleccion;
    }
    public JButton getBtnAtras() {
        return this.btnAtras;
    }
    public JLabel getLblNombreExiste() {
        return this.lblNombreExiste;
    }
    public JLabel getLblCrearProductoVacio() {
        return this.lblCrearProductoVacio;
    }
    public JLabel getLblEditarProductoVacio() {
        return this.lblEditarProductoVacio;
    }
    public JLabel getLblCrearPrecioInvalido() {
        return this.lblCrearPrecioInvalido;
    }
    public JLabel getLblEditarPrecioInvalido() {
        return this.lblEditarPrecioInvalido;
    }
    public JLabel getLblNoSeleccion() {
        return this.lblNoSeleccion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tProductos = new javax.swing.JTable();
        btnEliminarseleccion = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblCrearproducto = new javax.swing.JLabel();
        fieldCrearproductonombre = new javax.swing.JTextField();
        btnCrearproducto = new javax.swing.JButton();
        lblEditarproducto = new javax.swing.JLabel();
        btnEditarProducto = new javax.swing.JButton();
        lblCategorias = new javax.swing.JLabel();
        lblCrearproductonombre = new javax.swing.JLabel();
        lblCrearproductoprecio = new javax.swing.JLabel();
        fieldCrearproductoprecio = new javax.swing.JSpinner();
        lblEditarproductonombre = new javax.swing.JLabel();
        lblEditarproductoprecio = new javax.swing.JLabel();
        fieldEditarproductoprecio = new javax.swing.JSpinner();
        fieldEditarproductonombre = new javax.swing.JTextField();
        comboCrearProducto = new javax.swing.JComboBox<>();
        comboEditarProducto = new javax.swing.JComboBox<>();
        lblCrearProductoVacio = new javax.swing.JLabel();
        lblEditarProductoVacio = new javax.swing.JLabel();
        lblNombreExiste = new javax.swing.JLabel();
        lblCrearPrecioInvalido = new javax.swing.JLabel();
        lblEditarPrecioInvalido = new javax.swing.JLabel();
        lblNoSeleccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tProductos.setModel(this.categoriasTableModel);
        jScrollPane1.setViewportView(tProductos);

        btnEliminarseleccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminarseleccion.setText("Eliminar Selección");

        btnAtras.setText("Atrás");

        lblCrearproducto.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblCrearproducto.setText("Crear Producto");

        fieldCrearproductonombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCrearproductonombreActionPerformed(evt);
            }
        });

        btnCrearproducto.setText("Crear");

        lblEditarproducto.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblEditarproducto.setText("Editar Producto");

        btnEditarProducto.setText("Modificar");

        lblCategorias.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblCategorias.setText("Productos:");

        lblCrearproductonombre.setText("Nombre:");

        lblCrearproductoprecio.setText("Precio");

        fieldCrearproductoprecio.setModel(new javax.swing.SpinnerNumberModel(1.0d, null, null, 1.0d));

        lblEditarproductonombre.setText("Nombre:");

        lblEditarproductoprecio.setText("Precio");

        fieldEditarproductoprecio.setModel(new javax.swing.SpinnerNumberModel(1.0d, null, null, 1.0d));

        fieldEditarproductonombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEditarproductonombreActionPerformed(evt);
            }
        });

        comboCrearProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Refrescos", "bocadillos", "Menus" }));
        comboCrearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCrearProductoActionPerformed(evt);
            }
        });

        comboEditarProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Refrescos", "bocadillos", "Menus" }));
        comboEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEditarProductoActionPerformed(evt);
            }
        });

        lblCrearProductoVacio.setForeground(new java.awt.Color(255, 10, 10));
        lblCrearProductoVacio.setText("Debe seleccionar un nombre y un precio");

        lblEditarProductoVacio.setForeground(new java.awt.Color(255, 10, 10));
        lblEditarProductoVacio.setText("Debe seleccionar un nombre y un precio");

        lblNombreExiste.setForeground(new java.awt.Color(255, 10, 10));
        lblNombreExiste.setText("Ya existe el nombre de ese producto");

        lblCrearPrecioInvalido.setForeground(new java.awt.Color(255, 10, 10));
        lblCrearPrecioInvalido.setText("Precio inválido");

        lblEditarPrecioInvalido.setForeground(new java.awt.Color(255, 10, 10));
        lblEditarPrecioInvalido.setText("Precio inválido");

        lblNoSeleccion.setForeground(new java.awt.Color(255, 10, 10));
        lblNoSeleccion.setText("Debe seleccionar un producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fieldEditarproductonombre, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblEditarproductonombre)
                                    .addGap(139, 139, 139)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblEditarproductoprecio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblEditarPrecioInvalido))
                                .addComponent(fieldEditarproductoprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCrearproducto)
                            .addGap(18, 18, 18)
                            .addComponent(lblCrearProductoVacio))
                        .addComponent(btnCrearproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fieldCrearproductonombre, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblCrearproductonombre)
                                    .addGap(139, 139, 139)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblCrearproductoprecio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblCrearPrecioInvalido))
                                .addComponent(fieldCrearproductoprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboCrearProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboEditarProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEditarproducto)
                        .addGap(18, 18, 18)
                        .addComponent(lblEditarProductoVacio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCategorias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreExiste)))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEliminarseleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNoSeleccion)
                        .addGap(176, 176, 176))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrearproducto)
                    .addComponent(lblCategorias)
                    .addComponent(lblCrearProductoVacio)
                    .addComponent(lblNombreExiste))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarseleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCrearproductonombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fieldCrearproductonombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldCrearproductoprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCrearproductoprecio)
                                    .addComponent(lblCrearPrecioInvalido))
                                .addGap(30, 30, 30)))
                        .addComponent(comboCrearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditarproducto)
                            .addComponent(lblEditarProductoVacio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEditarproductonombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fieldEditarproductonombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldEditarproductoprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEditarproductoprecio)
                                    .addComponent(lblEditarPrecioInvalido))
                                .addGap(30, 30, 30)))
                        .addComponent(comboEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNoSeleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldCrearproductonombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCrearproductonombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCrearproductonombreActionPerformed

    private void fieldEditarproductonombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEditarproductonombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEditarproductonombreActionPerformed

    private void comboCrearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCrearProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCrearProductoActionPerformed

    private void comboEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEditarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEditarProductoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionProductosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCrearproducto;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarseleccion;
    private javax.swing.JComboBox<String> comboCrearProducto;
    private javax.swing.JComboBox<String> comboEditarProducto;
    private javax.swing.JTextField fieldCrearproductonombre;
    private javax.swing.JSpinner fieldCrearproductoprecio;
    private javax.swing.JTextField fieldEditarproductonombre;
    private javax.swing.JSpinner fieldEditarproductoprecio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblCrearPrecioInvalido;
    private javax.swing.JLabel lblCrearProductoVacio;
    private javax.swing.JLabel lblCrearproducto;
    private javax.swing.JLabel lblCrearproductonombre;
    private javax.swing.JLabel lblCrearproductoprecio;
    private javax.swing.JLabel lblEditarPrecioInvalido;
    private javax.swing.JLabel lblEditarProductoVacio;
    private javax.swing.JLabel lblEditarproducto;
    private javax.swing.JLabel lblEditarproductonombre;
    private javax.swing.JLabel lblEditarproductoprecio;
    private javax.swing.JLabel lblNoSeleccion;
    private javax.swing.JLabel lblNombreExiste;
    private javax.swing.JTable tProductos;
    // End of variables declaration//GEN-END:variables
}
