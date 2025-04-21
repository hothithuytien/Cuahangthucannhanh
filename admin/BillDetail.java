
package ui.admin;

import BUS.BillDetailBUS;
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
import model.CTHOADON;
import utils.JDBCconn;

/**
 *
 * @author PC
 */
public class BillDetail extends javax.swing.JPanel {
    
    private BillDetailBUS billDetailBus;
    private boolean isEditing = false;
    
    public BillDetail() {
        initComponents();
        billDetailBus = new BillDetailBUS();
        loadTable();
        
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tbBilldetail.getModel();
        model.setRowCount(0);
        java.util.List<CTHOADON> list = billDetailBus.displayListBillDetail();
        
        for(CTHOADON cthd : list){
            model.addRow(new Object[]{
                cthd.getMAHD(),
                cthd.getTENSP(),
                cthd.getSOLUONG(),
                cthd.getDONGIA(),
                cthd.getTHANHTIEN()
            });
        }
    
    }
    private void addBillDetail(){
        if(validataForm()){
            CTHOADON cthd = getBillDateilFromForm();
            if (billDetailBus.addBillDetail(cthd)){
                JOptionPane.showMessageDialog(this,"Thêm chi tiết hóa đơn thành công");
                clearForm();
                loadTable();   
            } else{
                JOptionPane.showMessageDialog(this,"Thêm chi tiết hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    private void updateBillDetail(){
        if(validataForm()){
            CTHOADON cthd = getBillDateilFromForm();
            if(billDetailBus.updateBillDetail(cthd)){
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
                clearForm();
                loadTable();
                isEditing = false;
            } else{
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        private void deleteBillDetail(){
            String mahd = txtmahd.getText();
            if(!mahd.isEmpty()){
                int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa chi tiết hóa đơn này?","Xác nhận", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    if(billDetailBus.deleteBillDetail(mahd)){
                        JOptionPane.showMessageDialog(this, "Xóa chi tiết hóa đơn thành công");
                    clearForm();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa chi tiết hóa đơn thất bại", "Loi", JOptionPane.ERROR_MESSAGE);
                }
                    }
                } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chi tiết hóa đơn cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
                
            }
        
     private void searchBillDetail(){
         String mahd = txtsearch.getText().trim();
         
         if(!mahd.isEmpty()){
             DefaultTableModel model = (DefaultTableModel) tbBilldetail.getModel();
                model.setRowCount(0);
            java.util.List<CTHOADON> list = billDetailBus.findBillDetailByMAHD(mahd);
            for(CTHOADON cthd : list){
                model.addRow(new Object[]{
                    cthd.getMAHD(),
                    cthd.getTENSP(),
                    cthd.getSOLUONG(),
                    cthd.getDONGIA(),
                    cthd.getTHANHTIEN()
                });
            }
                    
        }
         
         
     }
       
    private CTHOADON getBillDateilFromForm(){
        String MAHD = txtmahd.getText();
        String TENSP = txttensp.getText();
        int SoLuong = Integer.parseInt(txtsl.getText());
        double DonGia = Double.parseDouble(txtdongia.getText());
        return new CTHOADON(MAHD, TENSP, SoLuong, DonGia);
    }
    private boolean validataForm(){
        if(txtmahd.getText().isEmpty() || !txtmahd.getText().matches("HD\\d+")){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập mã hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmahd.requestFocus();
            return false;
        }
        if (txttensp.getText().isEmpty() || !txttensp.getText().matches("[\\p{L}\\s]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Loi", JOptionPane.ERROR_MESSAGE);
            txttensp.requestFocus();;
            return false;
        }
        if (txtsl.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsl.requestFocus();
            return false;
        }

        try {
            int SoLuong= Integer.parseInt(txtsl.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsl.requestFocus();
            return false;
        }
        if (txtdongia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn gia", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtdongia.requestFocus();
            return false;
        }

        try {
            double DonGia = Double.parseDouble(txtdongia.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtdongia.requestFocus();
            return false;
        }
        return true;
        
    }
    private void clearForm(){
        txtmahd.setText("");
        txttensp.setText("");
        txtsl.setText("");
        txtdongia.setText("");
    }
    private void disableFormFields(){
       txtmahd.setEnabled(false);
       txttensp.setEnabled(false);
       txtsl.setEnabled(false);
       txtdongia.setEnabled(false);
       
    }
    private void enableFormFields(){
        txtmahd.setEnabled(true);
       txttensp.setEnabled(true);
       txtsl.setEnabled(true);
       txtdongia.setEnabled(true);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttensp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtthanhtien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsl = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBilldetail = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(32767, 32767));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(250, 240, 215));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 200));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Chi tiết hóa đơn");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn");

        txtmahd.setBackground(new java.awt.Color(250, 241, 215));
        txtmahd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmahd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Đơn giá");

        txtdongia.setBackground(new java.awt.Color(250, 241, 215));
        txtdongia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtdongia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        txttensp.setBackground(new java.awt.Color(250, 241, 215));
        txttensp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttensp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Thành tiền");

        txtthanhtien.setBackground(new java.awt.Color(250, 241, 215));
        txtthanhtien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtthanhtien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        txtsl.setBackground(new java.awt.Color(250, 241, 215));
        txtsl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtsl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnthem.setBackground(new java.awt.Color(241, 185, 4));
        btnthem.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(241, 185, 4));
        btnxoa.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnxoa.setText("Xóa ");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(241, 185, 4));
        btnsua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnluu.setBackground(new java.awt.Color(241, 185, 4));
        btnluu.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnhuy.setBackground(new java.awt.Color(241, 185, 4));
        btnhuy.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnhuy.setText("Hủy");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });

        btnsearch.setText("Tim Kiem");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(318, 318, 318)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnthem)
                                .addGap(44, 44, 44)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnxoa)
                            .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnsua)
                                .addGap(33, 33, 33))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(354, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnluu)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnhuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnsearch)
                                .addGap(30, 30, 30))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsearch)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1100, 200);

        tbBilldetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tbBilldetail.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                tbBilldetailMouseWheelMoved(evt);
            }
        });
        tbBilldetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBilldetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBilldetail);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2);
        jPanel2.setBounds(0, 200, 1100, 390);
    }// </editor-fold>//GEN-END:initComponents

    private void tbBilldetailMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tbBilldetailMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBilldetailMouseWheelMoved

    private void tbBilldetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBilldetailMouseClicked
       int row = tbBilldetail.getSelectedRow();
        if (row >= 0) {
        txtmahd.setText(tbBilldetail.getValueAt(row, 0).toString());
        txttensp.setText(tbBilldetail.getValueAt(row, 1).toString());
        
        txtsl.setText(tbBilldetail.getValueAt(row, 2).toString());
        txtdongia.setText(tbBilldetail.getValueAt(row, 3).toString());
        txtthanhtien.setText(tbBilldetail.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tbBilldetailMouseClicked
    }
    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
       clearForm();
       enableFormFields();
       txtmahd.setEnabled(true);
       isEditing = false;
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        deleteBillDetail();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
       if (!txtmahd.getText().isEmpty()) {
            enableFormFields();
            txtmahd.setEnabled(false);
            isEditing = true;
//            isAdding = false;
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chi tiết hóa đơn cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
       if(isEditing){
           updateBillDetail();
       }
       else{
           addBillDetail();
       }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
       clearForm();
       disableFormFields();
    }//GEN-LAST:event_btnhuyActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        searchBillDetail();
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
    private javax.swing.JTable tbBilldetail;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txtthanhtien;
    // End of variables declaration//GEN-END:variables
}
