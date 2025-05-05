
package GUI.admin;



import BUS.BillDetailBUS;
import DTO.BillDetailDTO;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connect.JDBCconn;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class BillDetail extends javax.swing.JPanel {
    
    private BillDetailBUS billDetailBus;
    private boolean isEditing = false;
    private String currentMAHD = "";
    private Bill billReference;
    
    public BillDetail(String mahd, Bill billReference) {
        initComponents();
        billDetailBus = new BillDetailBUS();
         this.currentMAHD = mahd;
         this.billReference = billReference;
         
        System.out.println("MAHD: " +mahd);
        List<BillDetailDTO> details = billDetailBus.findBillDetailByMAHD(mahd);
        System.out.println("Số lượng chi tiết: " + details.size());
        loadTableByMAHD(mahd);
        
    }

    
    public void loadTableByMAHD(String mahd) {
    DefaultTableModel model = (DefaultTableModel) tbBilldetail.getModel();
    model.setRowCount(0);
    java.util.List<DTO.BillDetailDTO> list = billDetailBus.findBillDetailByMAHD(mahd);
    
    for(DTO.BillDetailDTO cthd : list){
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
            DTO.BillDetailDTO cthd = getBillDateilFromForm();
            if (billDetailBus.addBillDetail(cthd)){
                JOptionPane.showMessageDialog(this,"Thêm chi tiết hóa đơn thành công");
                clearForm();
                loadTableByMAHD(currentMAHD);   
            } else{
                JOptionPane.showMessageDialog(this,"Thêm chi tiết hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    private void updateBillDetail(){
        if(validataForm()){
            DTO.BillDetailDTO cthd = getBillDateilFromForm();
            if(billDetailBus.updateBillDetail(cthd)){
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
                clearForm();
                loadTableByMAHD(currentMAHD);
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
                        loadTableByMAHD(currentMAHD);
                        
                        if(billReference != null){
                            billReference.refreshTable();
                        }
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
            java.util.List<DTO.BillDetailDTO> list = billDetailBus.findBillDetailByMAHD(mahd);
            for(DTO.BillDetailDTO cthd : list){
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
     
     private String getSelectedProductId(){
         String productName = txttensp.getText().trim();
         if (productName.isEmpty()){
             return "";
         }
         
         try(Connection conn = JDBCconn.getConnection()){
             
             String sql = "SELECT MASP FROM SANPHAM WHERE TENSP = ?";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, productName);
             
             try(ResultSet rs = ps.executeQuery();){
                 if(rs.next()){
                 return rs.getString("MASP");
             }else{
                 JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm tương ứng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                 return "";
                }
            }
         }catch(SQLException ex){
             Logger.getLogger(BillDetail.class.getName()).log(Level.SEVERE, null, ex); 
             JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn mã sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return "";
         }
     }
       
    private DTO.BillDetailDTO getBillDateilFromForm(){
        String MAHD = txtmahd.getText();
        String TENSP = txttensp.getText();
        String MASP = getSelectedProductId();
        int SoLuong = Integer.parseInt(txtsl.getText());
        double DonGia = Double.parseDouble(txtdongia.getText());
        return new DTO.BillDetailDTO(MAHD, MASP, TENSP, SoLuong, DonGia);
    }
    private boolean validataForm(){
        if(txtmahd.getText().isEmpty() ){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập mã hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmahd.requestFocus();
            return false;
        }else if  (!txtmahd.getText().matches("HD\\d+")){
            JOptionPane.showMessageDialog(this,"Mã sản phẩm chưa đúng định dạng 'HD' ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmahd.requestFocus();
            return false;
        }
        if (txttensp.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm", "Loi", JOptionPane.ERROR_MESSAGE);
            txttensp.requestFocus();;
            return false;
        }else if( !txttensp.getText().matches("[\\p{L}\\s]+")){
            JOptionPane.showMessageDialog(this, "Tên sản phẩm chỉ chứa chữ cái", "Loi", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Số lượng phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        cbSearch = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBilldetail = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(32767, 32767));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(250, 240, 215));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 200));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Chi tiết hóa đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn");

        txtmahd.setBackground(new java.awt.Color(250, 241, 215));
        txtmahd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmahd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Đơn giá");

        txtdongia.setBackground(new java.awt.Color(250, 241, 215));
        txtdongia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtdongia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        txttensp.setBackground(new java.awt.Color(250, 241, 215));
        txttensp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttensp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Thành tiền");

        txtthanhtien.setBackground(new java.awt.Color(250, 241, 215));
        txtthanhtien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtthanhtien.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        txtsl.setBackground(new java.awt.Color(250, 241, 215));
        txtsl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtsl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
        btnxoa.setText("Xóa ");
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

        btnsearch.setBackground(new java.awt.Color(241, 185, 4));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsearch.setText("Tìm kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        txtsearch.setBackground(new java.awt.Color(250, 241, 215));
        txtsearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        cbSearch.setBackground(new java.awt.Color(250, 240, 215));
        cbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo mã ", "Tìm theo tên" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(668, 668, 668)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnthem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnxoa)
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnsua)
                                        .addGap(49, 49, 49)
                                        .addComponent(btnluu)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnhuy)))
                        .addGap(6, 6, 6)
                        .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
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
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1050, 200);

        tbBilldetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2);
        jPanel2.setBounds(0, 200, 1030, 390);
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

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    
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
    private javax.swing.JTable tbBilldetail;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txtthanhtien;
    // End of variables declaration//GEN-END:variables
}
