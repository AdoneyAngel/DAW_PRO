
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.administracion.historial;

/**
 *
 * @author AdoneyDAW
 */
public class AdministracionDetallesPedidoView extends javax.swing.JFrame {

    /**
     * Creates new form AdministracionDetallesPedidoView
     */
    public AdministracionDetallesPedidoView() {
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

        lblProductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tProductos = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        lblPedidoID = new javax.swing.JLabel();
        lblPedidoIDNum = new javax.swing.JLabel();
        lblPedidofecha = new javax.swing.JLabel();
        lblPedidofechaNum = new javax.swing.JLabel();
        lblPedidofinalizado = new javax.swing.JLabel();
        lblPedidofinalizadoNum = new javax.swing.JLabel();
        lblPedidoprecio = new javax.swing.JLabel();
        lblPedidoprecioNum = new javax.swing.JLabel();
        lblPedidomesa = new javax.swing.JLabel();
        lblPedidomesaNum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblProductos.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblProductos.setText("Productos:");

        tProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tProductos);

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitle.setText("Detalles del Pedido:");

        lblPedidoID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPedidoID.setText("ID del Pedido:");

        lblPedidoIDNum.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblPedidoIDNum.setForeground(new java.awt.Color(107, 107, 200));
        lblPedidoIDNum.setText("1");

        lblPedidofecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPedidofecha.setText("Fecha:");

        lblPedidofechaNum.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblPedidofechaNum.setForeground(new java.awt.Color(107, 107, 200));
        lblPedidofechaNum.setText("30-2-2024");

        lblPedidofinalizado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPedidofinalizado.setText("Pedido finalizado:");

        lblPedidofinalizadoNum.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblPedidofinalizadoNum.setForeground(new java.awt.Color(200, 107, 107));
        lblPedidofinalizadoNum.setText("NO");

        lblPedidoprecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPedidoprecio.setText("Precio Total:");

        lblPedidoprecioNum.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblPedidoprecioNum.setForeground(new java.awt.Color(107, 200, 107));
        lblPedidoprecioNum.setText("19,50€");

        lblPedidomesa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPedidomesa.setText("Mesa:");

        lblPedidomesaNum.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblPedidomesaNum.setForeground(new java.awt.Color(107, 107, 200));
        lblPedidomesaNum.setText("Mesa 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPedidoID)
                            .addComponent(lblPedidoIDNum)
                            .addComponent(lblPedidofecha)
                            .addComponent(lblPedidofechaNum)
                            .addComponent(lblPedidofinalizado)
                            .addComponent(lblPedidofinalizadoNum)
                            .addComponent(lblPedidomesa)
                            .addComponent(lblPedidomesaNum)
                            .addComponent(lblPedidoprecio)
                            .addComponent(lblPedidoprecioNum)))
                    .addComponent(lblProductos))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(39, 39, 39)
                .addComponent(lblProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPedidoID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidoIDNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidofecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidofechaNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidofinalizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidofinalizadoNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidomesa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidomesaNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidoprecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidoprecioNum)
                        .addContainerGap(121, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AdministracionDetallesPedidoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionDetallesPedidoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionDetallesPedidoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionDetallesPedidoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionDetallesPedidoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPedidoID;
    private javax.swing.JLabel lblPedidoIDNum;
    private javax.swing.JLabel lblPedidofecha;
    private javax.swing.JLabel lblPedidofechaNum;
    private javax.swing.JLabel lblPedidofinalizado;
    private javax.swing.JLabel lblPedidofinalizadoNum;
    private javax.swing.JLabel lblPedidomesa;
    private javax.swing.JLabel lblPedidomesaNum;
    private javax.swing.JLabel lblPedidoprecio;
    private javax.swing.JLabel lblPedidoprecioNum;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tProductos;
    // End of variables declaration//GEN-END:variables
}
