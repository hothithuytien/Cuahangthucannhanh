/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;


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
import DTO.ProductDTO;
import connect.JDBCconn;

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
        java.util.List<ProductDTO> list = drinkBus.disPlayListProduct(maloai);
        for (ProductDTO sp : list) {
            model.addRow(new Object[]{
                sp.getMASP(),
                sp.getTENSP(),
                sp.getGIANHAP(),
                sp.getGIABAN(),
                sp.getMALOAI(),
                sp.getTRANGTHAI()
            });
        }

    }

    private void addProduct() {
        if (validataForm()) {
            ProductDTO sp = getProductFromForm();
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
            ProductDTO sp = getProductFromForm();
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

    private void searchByComboChoice() {
    String keyword = txtsearch.getText().trim();
    String type = cbSearch.getSelectedItem().toString();
    
    DefaultTableModel model = (DefaultTableModel) tableDrink.getModel();
    model.setRowCount(0); // Xóa bảng

    if (keyword.isEmpty()) {
        loadTable("DR2"); // Nếu không nhập gì thì hiện lại toàn bộ
        return;
    }

    if (type.equals("Tìm theo mã")) {
        ProductDTO sp = drinkBus.findSanPhamById(keyword);
        if (sp != null) {
            model.addRow(new Object[]{
                sp.getMASP(),
                sp.getTENSP(),
                sp.getGIANHAP(),
                sp.getGIABAN(),
                sp.getMALOAI(),
                sp.getTRANGTHAI()
            });
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có mã: " + keyword);
        }
    } else { // Tìm theo tên
        java.util.List<ProductDTO> list = drinkBus.searchSanPhamByName(keyword);
        for (ProductDTO sp : list) {
            model.addRow(new Object[]{
               sp.getMASP(),
                sp.getTENSP(),
                sp.getGIANHAP(),
                sp.getGIABAN(),
                sp.getMALOAI(),
                sp.getTRANGTHAI()
            });
        }
    }
}
    private ProductDTO getProductFromForm() {
        String MASP = txtmamon.getText();
        String TENSP = txttenmon.getText();
        double GIANHAP = Double.parseDouble(txtgianhap.getText());
        double GIABAN = Double.parseDouble(txtgiaban.getText());
        String MALOAI = txtmaloai.getText();
        String TRANGTHAI ="Còn hàng";
        return new ProductDTO(MASP, TENSP, GIANHAP, GIABAN, MALOAI, TRANGTHAI);
    }

    public boolean validataForm() {
        if (txtmamon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false;
        }else if(!txtmamon.getText().matches("DR2\\d+")){
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không đúng định dạng Drink (DR2) ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmamon.requestFocus();
            return false;
        }
        if (txttenmon.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttenmon.requestFocus();
            return false;
        }
        else if( !txttenmon.getText().matches("[\\p{L}\\s]+")){
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
        else if(!txtmaloai.getText().matches("DR2")){
            JOptionPane.showMessageDialog(this, "Mã loại không đúng định dạng của thức uống", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        cbSearch = new javax.swing.JComboBox<>();
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
        jLabel4.setText("Giá nhập");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá bán");

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
        txtsearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnsearch.setBackground(new java.awt.Color(241, 185, 4));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsearch.setText("Tìm kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        cbSearch.setBackground(new java.awt.Color(250, 240, 215));
        cbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo mã", "Tìm theo tên" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmamon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnthem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txttenmon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnxoa)
                                .addGap(45, 45, 45)
                                .addComponent(btnsua)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnluu)
                                .addGap(43, 43, 43)
                                .addComponent(btnhuy)
                                .addGap(55, 55, 55)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmamon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1080, 200);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách món nước"));
        jPanel2.setMinimumSize(new java.awt.Dimension(1100, 380));

        tableDrink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                "Mã món nước", "Tên món nước", "Giá nhập", "Giá bán", "Mã loại", "Trạng thái"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
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
       searchByComboChoice();
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbSearch;
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
