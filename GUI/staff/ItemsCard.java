package GUI.staff;


import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import swing.RoundPanel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import DTO.ItemDTO;


public class ItemsCard extends javax.swing.JPanel {

    /**
     * @return the data
     */
    public ItemDTO getData() {
        return data;
    }

    private int quantity = 0;
    private boolean selected;
    private ItemDTO data;
    
    
    public ItemsCard() {
        initComponents();
        setOpaque(false);
    }
    
    public boolean isSelected(){
        return selected;
    }
    
    public void setSelected(boolean selected){
        this.selected = selected;
        repaint();
    }
  
    public void setData(ItemDTO data){
        this.data = data;
        lbTenSP.setText(data.getTenSP());
        DecimalFormat df = new DecimalFormat("#,##0");
        lbGiaBan.setText(df.format(data.getGiaban()) + "VND");
        pic.setImage(data.getImg());
    }
    
    @Override
    public void paint(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
        super.paint(grphcs);
        if(selected){
            g2.setColor(new Color(94,156,255));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
        
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        quantityLabel.setText(String.valueOf(quantity));
        updateButtonsVisibility();
    }
 
    
 
    
    void updateButtonsVisibility(){
        btnPlus.setVisible(quantity < 10);
        btnMinus.setVisible(quantity > 0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        item1 = new swing.RoundPanel();
        lbTenSP = new javax.swing.JLabel();
        lbGiaBan = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        btnPlus = new javax.swing.JButton();
        quantityLabel = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        pic = new swing.PictureBox();

        item1.setBackground(new java.awt.Color(255, 255, 255));

        lbTenSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTenSP.setForeground(new java.awt.Color(102, 102, 102));
        lbTenSP.setText("Gà Tắm Nước Mắm");

        lbGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbGiaBan.setForeground(new java.awt.Color(255, 102, 0));
        lbGiaBan.setText("49.000vnd");

        controlPanel.setBackground(new java.awt.Color(255, 255, 255));
        controlPanel.setPreferredSize(new java.awt.Dimension(151, 35));

        btnPlus.setBackground(new java.awt.Color(254, 194, 8));
        btnPlus.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        btnPlus.setBorderPainted(false);
        btnPlus.setFocusPainted(false);
        btnPlus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        quantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantityLabel.setText("0");

        btnMinus.setBackground(new java.awt.Color(254, 194, 8));
        btnMinus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-subtract-15.png"))); // NOI18N
        btnMinus.setBorderPainted(false);
        btnMinus.setFocusPainted(false);
        btnMinus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus)
                    .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/img_ff/doan/ga_nuocmam.png"))); // NOI18N

        javax.swing.GroupLayout item1Layout = new javax.swing.GroupLayout(item1);
        item1.setLayout(item1Layout);
        item1Layout.setHorizontalGroup(
            item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTenSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lbGiaBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        item1Layout.setVerticalGroup(
            item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item1Layout.createSequentialGroup()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGiaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(item1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(item1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        if (quantity < 10) {
            quantity++;
            quantityLabel.setText(String.valueOf(quantity));
            updateButtonsVisibility();
            
            Container parent = getParent();
            while(parent != null && !(parent instanceof Order)){
                parent = parent.getParent();
            }
            
            if (parent instanceof Order){
                ((Order) parent).addToOrder(data, 1);
            }
        }
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        if (quantity > 0) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
            updateButtonsVisibility();
        }
        
        Container parent = getParent();
        while(parent != null && !(parent instanceof Order)){
            parent = parent.getParent();
        }
        
        if (parent instanceof Order){
            ((Order) parent).removeOrder(data);
        }
    }//GEN-LAST:event_btnMinusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnPlus;
    private javax.swing.JPanel controlPanel;
    private swing.RoundPanel item1;
    private javax.swing.JLabel lbGiaBan;
    private javax.swing.JLabel lbTenSP;
    private swing.PictureBox pic;
    private javax.swing.JLabel quantityLabel;
    // End of variables declaration//GEN-END:variables
}
