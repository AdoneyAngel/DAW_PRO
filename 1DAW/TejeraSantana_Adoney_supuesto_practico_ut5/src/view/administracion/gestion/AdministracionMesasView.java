
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.administracion.gestion;

import javax.swing.table.DefaultTableModel;

/**
 * * González Olivares Brandon - Tejera Santana Adoney
 */
public class AdministracionMesasView extends javax.swing.JFrame {

    /**
     * @return the btnAtras
     */
    public javax.swing.JButton getBtnAtras() {
        return btnAtras;
    }

    /**
     * @return the btnCrearmesa
     */
    public javax.swing.JButton getBtnCrearmesa() {
        return btnCrearmesa;
    }

    /**
     * @return the btnEditarmesa
     */
    public javax.swing.JButton getBtnEditarmesa() {
        return btnEditarmesa;
    }

    /**
     * @return the btnEliminarseleccion
     */
    public javax.swing.JButton getBtnEliminarseleccion() {
        return btnEliminarseleccion;
    }

    /**
     * @param btnEliminarseleccion the btnEliminarseleccion to set
     */
    public void setBtnEliminarseleccion(javax.swing.JButton btnEliminarseleccion) {
        this.btnEliminarseleccion = btnEliminarseleccion;
    }

    /**
     * Creates new form AdministracionMesasView
     */
    
    private final String[] mesasTableColumns = {"ID", "Nombre"};
    private DefaultTableModel mesasTableModel;
    
    public AdministracionMesasView() {
        String[][] temporalMesasTableData = {{"1", "Mesa 1"}, {"2", "Mesa 2"}, {"3", "Mesa 3"}};
        this.mesasTableModel = new DefaultTableModel(temporalMesasTableData, this.mesasTableColumns) {
            @Override 
            public boolean isCellEditable(int r1, int r2) {
                return false;
            }
        };
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldEditarmesa = new javax.swing.JTextField();
        btnEditarmesa = new javax.swing.JButton();
        lblMesas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMesas = new javax.swing.JTable();
        btnEliminarseleccion = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblCrearMesa = new javax.swing.JLabel();
        fieldCrearmesa = new javax.swing.JTextField();
        btnCrearmesa = new javax.swing.JButton();
        lblEditarmesa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fieldEditarmesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEditarmesaActionPerformed(evt);
            }
        });

        btnEditarmesa.setText("Modificar");

        lblMesas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblMesas.setText("Mesas:");

        tMesas.setModel(this.mesasTableModel);
        jScrollPane1.setViewportView(tMesas);

        btnEliminarseleccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminarseleccion.setText("Eliminar Selección");

        btnAtras.setText("Atrás");

        lblCrearMesa.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblCrearMesa.setText("Crear Mesa:");

        fieldCrearmesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCrearmesaActionPerformed(evt);
            }
        });

        btnCrearmesa.setText("Crear");

        lblEditarmesa.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblEditarmesa.setText("Editar Mesa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEditarmesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldEditarmesa, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEditarmesa))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCrearmesa, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addComponent(fieldCrearmesa)
                        .addComponent(lblCrearMesa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMesas))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarseleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrearMesa)
                    .addComponent(lblMesas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fieldCrearmesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCrearmesa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(lblEditarmesa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldEditarmesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarmesa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarseleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldEditarmesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEditarmesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEditarmesaActionPerformed

    private void fieldCrearmesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCrearmesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCrearmesaActionPerformed

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
            java.util.logging.Logger.getLogger(AdministracionMesasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionMesasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionMesasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionMesasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionMesasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCrearmesa;
    private javax.swing.JButton btnEditarmesa;
    private javax.swing.JButton btnEliminarseleccion;
    private javax.swing.JTextField fieldCrearmesa;
    private javax.swing.JTextField fieldEditarmesa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCrearMesa;
    private javax.swing.JLabel lblEditarmesa;
    private javax.swing.JLabel lblMesas;
    private javax.swing.JTable tMesas;
    // End of variables declaration//GEN-END:variables
}
