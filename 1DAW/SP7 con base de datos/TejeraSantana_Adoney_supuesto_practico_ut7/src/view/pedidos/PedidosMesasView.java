
//@@@@@@@@@@@@@@@@ PROYECTO Brandom-Adoney


package view.pedidos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * * González Olivares Brandon - Tejera Santana Adoney
 */
public class PedidosMesasView extends javax.swing.JFrame {

    /**
     * Creates new form PedidosMesasView
     */
    public PedidosMesasView(String[] mesasButtonNames) {
        initComponents();
        
        this.panelMesas.setLayout(new GridLayout(0, 2, 10, 20));
        
        //SE RECCORRE LAS MESAS PARA AGREGARLOS AL PANEL
        for (String nombreMesa : mesasButtonNames) {
            
            JButton btnMesa = new JButton(nombreMesa);
            
            btnMesa.setBounds(0, 0, 190, 58);
            btnMesa.setPreferredSize(new Dimension(150, 60));
            
            this.panelMesas.add(btnMesa);
        }
    }
    
    public JButton getBtnAtras() {
        return this.btnAtras;
    }
    
    public JLabel getLblNoMesas() {
        return this.lblNoMesas;
    }
    
    public Component[] getMesasButtons() {
        return this.panelMesas.getComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMesa6 = new javax.swing.JButton();
        btnMesa8 = new javax.swing.JButton();
        btnMesa1 = new javax.swing.JButton();
        btnMesa7 = new javax.swing.JButton();
        btnMesa3 = new javax.swing.JButton();
        btnMesa4 = new javax.swing.JButton();
        btnMesa2 = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        panelMesas = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        lblNoMesas = new javax.swing.JLabel();

        btnMesa6.setText("Mesa 6");
        btnMesa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa6ActionPerformed(evt);
            }
        });

        btnMesa8.setText("Mesa 8");
        btnMesa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa8ActionPerformed(evt);
            }
        });

        btnMesa1.setText("Mesa 1");
        btnMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa1ActionPerformed(evt);
            }
        });

        btnMesa7.setText("Mesa 7");
        btnMesa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa7ActionPerformed(evt);
            }
        });

        btnMesa3.setText("Mesa 3");
        btnMesa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa3ActionPerformed(evt);
            }
        });

        btnMesa4.setText("Mesa 4");
        btnMesa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa4ActionPerformed(evt);
            }
        });

        btnMesa2.setText("Mesa 2");
        btnMesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("Mesas");
        lblTitulo.setToolTipText("");
        lblTitulo.setFocusable(false);
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitulo.setName(""); // NOI18N

        panelMesas.setAutoscrolls(true);
        panelMesas.setMaximumSize(new java.awt.Dimension(100, 200));
        panelMesas.setMinimumSize(new java.awt.Dimension(80, 100));

        javax.swing.GroupLayout panelMesasLayout = new javax.swing.GroupLayout(panelMesas);
        panelMesas.setLayout(panelMesasLayout);
        panelMesasLayout.setHorizontalGroup(
            panelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );
        panelMesasLayout.setVerticalGroup(
            panelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        btnAtras.setText("Atrás");

        lblNoMesas.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblNoMesas.setForeground(new java.awt.Color(200, 10, 10));
        lblNoMesas.setText("No hay mesas disponibles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNoMesas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(panelMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoMesas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMesa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa2ActionPerformed

    private void btnMesa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa3ActionPerformed

    private void btnMesa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa4ActionPerformed

    private void btnMesa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa7ActionPerformed

    private void btnMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa1ActionPerformed

    private void btnMesa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa8ActionPerformed

    private void btnMesa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnMesa1;
    private javax.swing.JButton btnMesa2;
    private javax.swing.JButton btnMesa3;
    private javax.swing.JButton btnMesa4;
    private javax.swing.JButton btnMesa6;
    private javax.swing.JButton btnMesa7;
    private javax.swing.JButton btnMesa8;
    private javax.swing.JLabel lblNoMesas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelMesas;
    // End of variables declaration//GEN-END:variables
}