
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.pedidos;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * * González Olivares Brandon - Tejera Santana Adoney
 */
public class PedidosFinalizarView extends javax.swing.JFrame {

    /**
     * Creates new form PedidosFinalizarView
     */
    
    private DefaultTableModel facturaTableModel;
    private final String[] facturaTableColumns = {"Producto", "Precio", "Unidades", "Subtotal"};
    
    public PedidosFinalizarView() {
        this.facturaTableModel = new DefaultTableModel(this.facturaTableColumns, 0);
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tFacturas = new javax.swing.JTable();
        btnReiniciarpago = new javax.swing.JButton();
        btn1cent = new javax.swing.JButton();
        btn2cent = new javax.swing.JButton();
        btn5cent = new javax.swing.JButton();
        btn10cent = new javax.swing.JButton();
        btn20cent = new javax.swing.JButton();
        btn1euro = new javax.swing.JButton();
        btn50cent1 = new javax.swing.JButton();
        btn2euro = new javax.swing.JButton();
        btn5euro = new javax.swing.JButton();
        btn10euro = new javax.swing.JButton();
        btn20euro = new javax.swing.JButton();
        btn50euro = new javax.swing.JButton();
        btn100€ = new javax.swing.JButton();
        btn200euro = new javax.swing.JButton();
        btn500euro = new javax.swing.JButton();
        btnPagotarjeta = new javax.swing.JButton();
        lblPagado = new javax.swing.JLabel();
        lblPagadoNum = new javax.swing.JLabel();
        lblDevolucion = new javax.swing.JLabel();
        lblDevolucionNum = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblMesa = new javax.swing.JLabel();
        lblMesaNum = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTotalNum = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tFacturas.setModel(this.facturaTableModel);
        jScrollPane1.setViewportView(tFacturas);

        btnReiniciarpago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReiniciarpago.setText("Reiniciar Pago");

        btn1cent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn1cent.setText("1 céntimo");

        btn2cent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn2cent.setText("2 céntimos");

        btn5cent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn5cent.setText("5 céntimos");

        btn10cent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn10cent.setText("10 céntimos");

        btn20cent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn20cent.setText("20 céntimos");

        btn1euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn1euro.setText("1€");

        btn50cent1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn50cent1.setText("50 céntimos");

        btn2euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn2euro.setText("2€");

        btn5euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn5euro.setText("5€");

        btn10euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn10euro.setText("10€");

        btn20euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn20euro.setText("20€");

        btn50euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn50euro.setText("50€");

        btn100€.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn100€.setText("100€");

        btn200euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn200euro.setText("200€");

        btn500euro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn500euro.setText("500€");

        btnPagotarjeta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPagotarjeta.setText("Pago con Tarjeta");

        lblPagado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPagado.setText("Pagado: ");

        lblPagadoNum.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPagadoNum.setForeground(new java.awt.Color(200, 20, 20));
        lblPagadoNum.setText("0,00€");

        lblDevolucion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDevolucion.setText("Devolución:");

        lblDevolucionNum.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblDevolucionNum.setForeground(new java.awt.Color(20, 200, 20));
        lblDevolucionNum.setText("0,00€");

        btnAtras.setText("Atrás");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Factura: ");

        lblMesa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblMesa.setText("Mesa:");

        lblMesaNum.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMesaNum.setText("Mesa 1");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotal.setText("Total:");

        lblTotalNum.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalNum.setText("0,00€");

        btnFinalizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn10cent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn20cent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn50cent1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn1cent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2cent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn5cent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn1euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn5euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn10euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn20euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn50euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn100€, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn200euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn500euro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnPagotarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPagado))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblPagadoNum))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDevolucion)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(lblDevolucionNum))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnReiniciarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMesaNum)
                            .addComponent(lblTotalNum)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMesa)
                            .addComponent(lblTotal))))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReiniciarpago)
                    .addComponent(jLabel5)
                    .addComponent(lblMesa))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMesaNum)
                        .addGap(71, 71, 71)
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalNum)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn1cent)
                                    .addComponent(btn2cent)
                                    .addComponent(btn5cent))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn10cent)
                                    .addComponent(btn20cent)
                                    .addComponent(btn50cent1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn1euro)
                                    .addComponent(btn2euro)
                                    .addComponent(btn5euro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn10euro)
                                    .addComponent(btn20euro)
                                    .addComponent(btn50euro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn100€)
                                    .addComponent(btn200euro)
                                    .addComponent(btn500euro))
                                .addGap(18, 18, 18)
                                .addComponent(btnPagotarjeta)
                                .addGap(25, 25, 25)
                                .addComponent(lblPagado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPagadoNum)
                                .addGap(18, 18, 18)
                                .addComponent(lblDevolucion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDevolucionNum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFinalizarActionPerformed

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
            java.util.logging.Logger.getLogger(PedidosFinalizarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosFinalizarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosFinalizarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosFinalizarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosFinalizarView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn100€;
    private javax.swing.JButton btn10cent;
    private javax.swing.JButton btn10euro;
    private javax.swing.JButton btn1cent;
    private javax.swing.JButton btn1euro;
    private javax.swing.JButton btn200euro;
    private javax.swing.JButton btn20cent;
    private javax.swing.JButton btn20euro;
    private javax.swing.JButton btn2cent;
    private javax.swing.JButton btn2euro;
    private javax.swing.JButton btn500euro;
    private javax.swing.JButton btn50cent1;
    private javax.swing.JButton btn50euro;
    private javax.swing.JButton btn5cent;
    private javax.swing.JButton btn5euro;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnPagotarjeta;
    private javax.swing.JButton btnReiniciarpago;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDevolucion;
    private javax.swing.JLabel lblDevolucionNum;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblMesaNum;
    private javax.swing.JLabel lblPagado;
    private javax.swing.JLabel lblPagadoNum;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalNum;
    private javax.swing.JTable tFacturas;
    // End of variables declaration//GEN-END:variables

    public JButton getbtnFinalizar() {
        return this.btnFinalizar;
    }

    public JButton getbtnAtras() {
        return this.btnAtras;
    }
}
