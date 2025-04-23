/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;

import ui.admin.*;
import BUS.DessertBUS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SANPHAM;
import utils.JDBCconn;

/**
 *
 * @author PC
 */
public class Dessert extends javax.swing.JPanel {

    private DessertBUS dessertBus;
    private boolean isEditing = false;
    public Dessert() {
        initComponents();
        dessertBus = new DessertBUS();
        loadTable("DS3");
        
    }

    public void loadTable(String maloai) {
        DefaultTableModel model = (DefaultTableModel) tableDessert.getModel();
        model.setRowCount(0);
        java.util.List<SANPHAM> list = dessertBus.disPlayListProduct(maloai);
        for (SANPHAM sp : list) {
            model.addRow(new Object[]{
                sp.getMASP(),
                sp.getTENSP(),
                sp.getGIANHAP(),
                sp.getGIABAN(),
                sp.getMALOAI()
            });
        }
    }

    private void addProduct() {
        if (validataForm()) {
            SANPHAM sp = getProductFromForm();
            if (dessertBus.addSProduct(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                clearForm();
                loadTable("DS3");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại ", "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    private void updateProduct(){
        if (validataForm()) {
            SANPHAM SP = getProductFromForm();
            if (dessertBus.updateProduct(SP)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
                clearForm();
                loadTable("MD1");
                isEditing = false;

            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void deleteProduct() {
        String masp = txtmamon.getText();
        if (!masp.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dessertBus.deleteProduct(masp)) {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
                    clearForm();
                    loadTable("MD1");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchProduct() {
        String keyword = txtsearch.getText();
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableDessert.getModel();
            model.setRowCount(0);
            java.util.List<SANPHAM> list = dessertBus.searchSanPhamByName(keyword);
            for (SANPHAM sp : list) {
                model.addRow(new Object[]{
                    sp.getMASP(),
                    sp.getTENSP(),
                    sp.getGIANHAP(),
                    sp.getGIABAN(),
                    sp.getMALOAI()
                });
            }
        } else {
            loadTable("DS3");
        }
    }

    private void searchProductById() {
        String masp = txtsearch.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tableDessert.getModel();
        model.setRowCount(0);
        if (!masp.isEmpty()) {
            SANPHAM sp = dessertBus.findSanPhamById(masp);
            if (sp != null) {
                model.addRow(new Object[]{
                    sp.getMASP(),
                    sp.getTENSP(),
                    sp.getGIANHAP(),
                    sp.getGIABAN(),
                    sp.getMALOAI()
                });
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã: " + masp);
            }
        } else {
            loadTable("DS3");
        }
    }

    private SANPHAM getProductFromForm() {
        String MASP = txtmamon.getText();
        String TENSP = txttenmon.getText();
        double GIANHAP = Double.parseDouble(txtgianhap.getText());
        double GIABAN = Double.parseDouble(txtgiaban.getText());
        String MALOAI = txtmaloai.getText();
        return new SANPHAM(MASP, TENSP, GIANHAP, GIABAN, MALOAI);
    }

    public boolean validataForm() {
        if (txtmamon.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false;
        } else if( !txtmamon.getText().matches("SP\\d+")){
           JOptionPane.showMessageDialog(this, "Mã sản phẩm không dúng định dạng SP", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false; 
        }
        if (txttenmon.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttenmon.requestFocus();
            return false;
        }
        else if( !txtmamon.getText().matches("[\\p{L}\\s]+")){
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttenmon.requestFocus();
            return false;
        }
        if (txtgianhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtgianhap.requestFocus();
            return false;
        }

        try {
            double GIANHAP = Double.parseDouble(txtgianhap.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtgianhap.requestFocus();
            return false;
        }
        if (txtgiaban.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtgiaban.requestFocus();
            return false;
        }

        try {
            double GIABAN = Double.parseDouble(txtgiaban.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtgiaban.requestFocus();
            return false;
        }
        if (txtmaloai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã loại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmaloai.requestFocus();
            return false;
        }
        return true;
    }

    public void clearForm() {
        txtmamon.setText("");
        txttenmon.setText("");
        txtgianhap.setText("");
        txtgiaban.setText("");
        txtmaloai.setText("");
    }

    public void disableFormFields() {
        txtmamon.setEnabled(false);
        txttenmon.setEnabled(false);
        txtgianhap.setEnabled(false);
        txtgiaban.setEnabled(false);
        txtmaloai.setEnabled(false);
    }

    public void enableFormFields() {
        txtmamon.setEnabled(true);
        txttenmon.setEnabled(true);
        txtgianhap.setEnabled(true);
        txtgiaban.setEnabled(true);
        txtmaloai.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfsearch = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmamon = new javax.swing.JTextField();
        txttenmon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtmaloai = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDessert = new javax.swing.JTable();

        setLayout(null);

        tfsearch.setBackground(new java.awt.Color(250, 240, 215));
        tfsearch.setMinimumSize(new java.awt.Dimension(1100, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Menu món tráng miệng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã món tráng miêng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên món tráng miêng");

        txtmamon.setBackground(new java.awt.Color(250, 240, 215));
        txtmamon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmamon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txttenmon.setBackground(new java.awt.Color(250, 240, 215));
        txttenmon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttenmon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giá nhập");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá bán");

        txtgianhap.setBackground(new java.awt.Color(250, 240, 215));
        txtgianhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtgianhap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtgiaban.setBackground(new java.awt.Color(250, 240, 215));
        txtgiaban.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã loại");

        txtmaloai.setBackground(new java.awt.Color(250, 240, 215));
        txtmaloai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnthem.setBackground(new java.awt.Color(241, 185, 4));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(241, 185, 4));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(241, 185, 4));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnluu.setBackground(new java.awt.Color(241, 185, 4));
        btnluu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnhuy.setBackground(new java.awt.Color(241, 185, 4));
        btnhuy.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnhuy.setText("Hủy");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });

        txtsearch.setBackground(new java.awt.Color(250, 240, 217));
        txtsearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtsearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnsearch.setBackground(new java.awt.Color(241, 185, 4));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsearch.setText("Tìm kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tfsearchLayout = new javax.swing.GroupLayout(tfsearch);
        tfsearch.setLayout(tfsearchLayout);
        tfsearchLayout.setHorizontalGroup(
            tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfsearchLayout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tfsearchLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(btnthem)
                .addGap(41, 41, 41)
                .addComponent(btnxoa)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tfsearchLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnsua)
                        .addGap(45, 45, 45)
                        .addComponent(btnluu)
                        .addGap(49, 49, 49)
                        .addComponent(btnhuy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tfsearchLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tfsearchLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttenmon)
                    .addComponent(txtmamon, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tfsearchLayout.createSequentialGroup()
                        .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(205, 205, 205))
        );
        tfsearchLayout.setVerticalGroup(
            tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tfsearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addContainerGap())
            .addGroup(tfsearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmamon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(tfsearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        add(tfsearch);
        tfsearch.setBounds(0, 0, 1070, 203);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách món tráng miệng"));

        tableDessert.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã món tráng miêng", "Tên món tráng miệng", "Giá nhập", "Giá bán", "Mã loại", "Trạng thái"
            }
        ));
        tableDessert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDessertMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDessert);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2);
        jPanel2.setBounds(0, 200, 1090, 380);
    }// </editor-fold>//GEN-END:initComponents

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
       clearForm();
       disableFormFields();
    }//GEN-LAST:event_btnhuyActionPerformed

    private void tableDessertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDessertMouseClicked
        int row = tableDessert.getSelectedRow();
        if (row >= 0) {
            txtmamon.setText(tableDessert.getValueAt(row, 0).toString());
            txttenmon.setText(tableDessert.getValueAt(row, 1).toString());

            txtgianhap.setText(tableDessert.getValueAt(row, 2).toString());
            txtgiaban.setText(tableDessert.getValueAt(row, 3).toString());
            txtmaloai.setText(tableDessert.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tableDessertMouseClicked
    }
    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
       clearForm();
       enableFormFields();
       txtmamon.setEnabled(true);
       isEditing = false;
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        deleteProduct();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        if (!txtmamon.getText().isEmpty()) {
            enableFormFields();
            txtmamon.setEnabled(false);
            isEditing = true;
//            isAdding = false;
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        if(isEditing){
            updateProduct();
        }
        else{
            addProduct();
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        searchProduct();
        searchProductById();
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDessert;
    private javax.swing.JPanel tfsearch;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtmaloai;
    private javax.swing.JTextField txtmamon;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttenmon;
    // End of variables declaration//GEN-END:variables
}
