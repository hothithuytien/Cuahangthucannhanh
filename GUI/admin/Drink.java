/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;

import ui.admin.*;
import BUS.DrinkBUS;
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
public class Drink extends javax.swing.JPanel {

    private DrinkBUS drinkBus;
    private boolean isEditing = false;

    public Drink() {
        initComponents();
        drinkBus = new DrinkBUS();
        loadTable("DR2");
//        disableFormFields();
    }

    public void loadTable(String maloai) {
        DefaultTableModel model = (DefaultTableModel) tableDrink.getModel();
        model.setRowCount(0);
        java.util.List<SANPHAM> list = drinkBus.disPlayListProduct(maloai);
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
            if (drinkBus.addSProduct(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                clearForm();
                loadTable("DR2");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateProduct() {
        if (validataForm()) {
            SANPHAM sp = getProductFromForm();
            if (drinkBus.updateProduct(sp)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
                clearForm();
                loadTable("DR2");
                isEditing = false;
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteProduct() {
        String masp = txtmamon.getText();
        if (!masp.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (drinkBus.deleteProduct(masp)) {
                    clearForm();
                    loadTable("DR2");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchProduct() {
        String keyword = txtsearch.getText();
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableDrink.getModel();
            model.setRowCount(0);
            java.util.List<SANPHAM> list = drinkBus.searchSanPhamByName(keyword);
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
            loadTable("DR2");
        }
    }

    private void searchProductById() {
        String masp = txtsearch.getText();
        DefaultTableModel model = (DefaultTableModel) tableDrink.getModel();
        model.setRowCount(0);
        if (!masp.isEmpty()) {
            SANPHAM sp = drinkBus.findSanPhamById(masp);
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
            loadTable("DR2");
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
        if (txtmamon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false;
        }else if(!txtmamon.getText().matches("SP\\d+")){
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không đúng định dạng SP ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false;
        }
        if (txttenmon.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttenmon.requestFocus();
            return false;
        }else if(!txttenmon.getText().matches("[p\\{L}\\s]+")){
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
        if (txtmaloai.getText().isEmpty() ) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmamon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttenmon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        txtmaloai = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDrink = new javax.swing.JTable();

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(250, 240, 215));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Menu đồ uống");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã món nước");

        txtmamon.setBackground(new java.awt.Color(250, 240, 215));
        txtmamon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmamon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên món nước");

        txttenmon.setBackground(new java.awt.Color(250, 240, 215));
        txttenmon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttenmon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giá nhâp");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá Bán");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã loại");

        txtgianhap.setBackground(new java.awt.Color(250, 240, 215));
        txtgianhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtgianhap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtgiaban.setBackground(new java.awt.Color(250, 240, 215));
        txtgiaban.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtgiaban.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtmaloai.setBackground(new java.awt.Color(250, 240, 215));
        txtmaloai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmaloai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnthem.setBackground(new java.awt.Color(241, 185, 4));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
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

        btnxoa.setBackground(new java.awt.Color(241, 185, 4));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
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

        txtsearch.setBackground(new java.awt.Color(250, 240, 215));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtmamon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnthem)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenmon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnsua)
                                .addGap(13, 13, 13)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnxoa)
                                .addGap(47, 47, 47)
                                .addComponent(btnluu)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)))
                        .addGap(10, 10, 10)
                        .addComponent(btnhuy))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmamon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch)))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1080, 200);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách món nước", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(1100, 380));

        tableDrink.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tableDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã món nước", "Tên món nước", "Giá nháp", "Giá bán", "Mã loại", "Trạng thái"
            }
        ));
        tableDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDrinkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDrink);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 200, 1080, 380);
    }// </editor-fold>//GEN-END:initComponents

    private void tableDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDrinkMouseClicked
        int row = tableDrink.getSelectedRow();
        if (row >= 0) {
            txtmamon.setText(tableDrink.getValueAt(row, 0).toString());
            txttenmon.setText(tableDrink.getValueAt(row, 1).toString());

            txtgianhap.setText(tableDrink.getValueAt(row, 2).toString());
            txtgiaban.setText(tableDrink.getValueAt(row, 3).toString());
            txtmaloai.setText(tableDrink.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_tableDrinkMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        deleteProduct();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        clearForm();
        enableFormFields();
        txtmamon.setEditable(true);

    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        if (!txtmamon.getText().isEmpty()) {
            enableFormFields();
            txtmamon.setEditable(false);
            isEditing = true;
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
      if (isEditing){
          updateProduct();
      }
      else{
          addProduct();
      }
      
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        clearForm();
        disableFormFields();
    }//GEN-LAST:event_btnhuyActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDrink;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtmaloai;
    private javax.swing.JTextField txtmamon;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttenmon;
    // End of variables declaration//GEN-END:variables
}
