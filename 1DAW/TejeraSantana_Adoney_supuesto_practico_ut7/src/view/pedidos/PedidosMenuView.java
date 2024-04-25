
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.pedidos;

import javax.swing.JButton;

/**
 *
 * * González Olivares Brandon - Tejera Santana Adoney
 */
public class PedidosMenuView extends javax.swing.JFrame {

    /**
     * Creates new form PedidosMenuVuew
     */
    public PedidosMenuView() {
        initComponents();
    }
    
    public JButton getBtnSalir() {
        return this.btnAtras;
    }
    
    public JButton getBtnNuevoPedido() {
        return this.btnNuevoPedido;
    }
    
    public JButton getBtnFinalizarPedido() {
        return this.btnFinalizarPedido;
    }
    
    public JButton getBtnAddComanda() {
        return this.btnAñadirComandaPedido;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnAñadirComandaPedido = new javax.swing.JButton();
        btnNuevoPedido = new javax.swing.JButton();
        btnFinalizarPedido = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("Sistema de Pedidos");
        lblTitulo.setToolTipText("");
        lblTitulo.setFocusable(false);
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitulo.setName(""); // NOI18N

        btnAñadirComandaPedido.setText("Añadir Comanda de Pedido");
        btnAñadirComandaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirComandaPedidoActionPerformed(evt);
            }
        });

        btnNuevoPedido.setText("Nuevo Pedido");
        btnNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPedidoActionPerformed(evt);
            }
        });

        btnFinalizarPedido.setText("Finalizar Pedido");
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(126, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFinalizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAñadirComandaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitulo))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAñadirComandaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFinalizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAñadirComandaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirComandaPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadirComandaPedidoActionPerformed

    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private void btnNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(PedidosMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosMenuView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadirComandaPedido;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnNuevoPedido;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
