/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;

import BUS.BillBUS;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.HOADON;

//import ui.admin.BillDetail;
import utils.JDBCconn;

public class Bill extends javax.swing.JPanel {

   private BillBUS billBus;
    public Bill() {
        initComponents();
        billBus = new BillBUS();
        loadTable();
    }

   public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tableBill.getModel();
            model.setRowCount(0);
            java.util.List<HOADON> list = billBus.displayListBillDetail();
            for(HOADON hd : list){
                model.addRow(new Object[]{
                    hd.getMAHD(),
                    hd.getNGAYLAP(),
                    hd.getMAKH(),
                    hd.getMANV(),
                    hd.getTONGTIEN(),
                    hd.getTRANGTHAI()  
                });
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainBill = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmakh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtngaylap = new javax.swing.JTextField();
        btnchitiethoadon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBill = new javax.swing.JTable();

        setLayout(null);

        mainBill.setBackground(new java.awt.Color(236, 233, 233));
        mainBill.setPreferredSize(new java.awt.Dimension(800, 500));
        mainBill.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setMinimumSize(new java.awt.Dimension(1300, 200));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Hóa đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn");

        txtmahd.setBackground(new java.awt.Color(250, 240, 215));
        txtmahd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtmahd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        txtmanv.setBackground(new java.awt.Color(250, 240, 215));
        txtmanv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmanv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã khách hàng");

        txtmakh.setBackground(new java.awt.Color(250, 240, 215));
        txtmakh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmakh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày lập");

        txtngaylap.setBackground(new java.awt.Color(250, 240, 215));
        txtngaylap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtngaylap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnchitiethoadon.setBackground(new java.awt.Color(241, 185, 4));
        btnchitiethoadon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnchitiethoadon.setText("CHI TIẾT HÓA ĐƠN");
        btnchitiethoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitiethoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(87, 87, 87)
                        .addComponent(btnchitiethoadon)))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnchitiethoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        mainBill.add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 200);

        jPanel3.setBackground(new java.awt.Color(250, 240, 215));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel3.setMinimumSize(new java.awt.Dimension(1300, 550));
        jPanel3.setPreferredSize(new java.awt.Dimension(1300, 550));

        tableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập", "Tổng tiền", "Trạng thái"
            }
        ));
        tableBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBill);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainBill.add(jPanel3);
        jPanel3.setBounds(0, 200, 1280, 550);

        add(mainBill);
        mainBill.setBounds(0, 0, 1310, 750);
    }// </editor-fold>//GEN-END:initComponents

    private void btnchitiethoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitiethoadonActionPerformed
        // Xóa nội dung cũ
        mainBill.removeAll();

        // Tạo panel mới từ class Staff
        BillDetail billDetailPanel = new BillDetail();

        // Thêm vào mainPanel
        mainBill.setLayout(new BorderLayout());
        mainBill.add(billDetailPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainBill.revalidate();
        mainBill.repaint(); // TODO add your handling code here:
    }//GEN-LAST:event_btnchitiethoadonActionPerformed

    private void tableBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBillMouseClicked
       int row = tableBill.getSelectedRow();
        if (row >= 0) {
        txtmahd.setText(tableBill.getValueAt(row, 0).toString());
        txtmanv.setText(tableBill.getValueAt(row, 2).toString());
        
        txtmakh.setText(tableBill.getValueAt(row, 3).toString());
        txtngaylap.setText(tableBill.getValueAt(row, 1).toString());
        
    }         
    }//GEN-LAST:event_tableBillMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchitiethoadon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainBill;
    private javax.swing.JTable tableBill;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtngaylap;
    // End of variables declaration//GEN-END:variables
}
