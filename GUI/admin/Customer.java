/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;

import BUS.CustomerBUS;
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
import model.KHACHHANG;
import utils.JDBCconn;


public class Customer extends javax.swing.JPanel {
    private CustomerBUS customerBus;
    private boolean isEditing = false;
    
    
    public Customer() {
        initComponents();
        customerBus = new CustomerBUS();
        loadTable();
//        disableFromFields();
//        setButtonStates(true, false, false, false);    
    }
    public void loadTable() {
       DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
       model.setRowCount(0);
       
       java.util.List<KHACHHANG> list = customerBus.disPlayListCustomer();
       for(KHACHHANG kh : list){
           model.addRow(new Object[]{
           kh.getMAKH(),
           kh.getHOTENKH(),
           kh.getGIOITINH(),
           kh.getSDT(),
           kh.getDIACHI()    
       });
       }
    }
    
    private void addCustomer(){
        if(validataForm()){
            KHACHHANG kh = getCustomerFromForm();
            if(customerBus.addCustomer(kh)){
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                clearForm();
                loadTable();
            }
            else{
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void updateCustomer (){
        if(validataForm()){
            KHACHHANG kh = getCustomerFromForm();
            if(customerBus.updateCustomer(kh)){
                JOptionPane.showMessageDialog(this,"Cập nhật khách hàng thành công");
                clearForm();
                loadTable();
                isEditing = false;
//                setButtonStates(true, false, false, false);
            }else{
                JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thất bại !","Lỗi" ,JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void deleteCustomer (){
        String makh = txtmakh.getText();
        if(!makh.isEmpty()){
            int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa khách hàng này?","Xác nhận", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                if (customerBus.deleteCustomer(makh)){
                    JOptionPane.showMessageDialog(this,"Xóa khách hàng thành công!");
                    clearForm();
                    loadTable();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Xóa khách hàng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                    JOptionPane.showMessageDialog(this,"Vui lòng chọn khách hàng cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        }  
    }
    private void searchCustomer(){
        String keyword = tfsearchtenkh.getText();
        if(!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
            model.setRowCount(0);
            
            java.util.List <KHACHHANG> list = customerBus.searchKhachhangByName(keyword);
            for(KHACHHANG kh : list){
                model.addRow(new Object[]{
                    kh.getMAKH(),
                    kh.getHOTENKH(),
                    kh.getGIOITINH(),
                    kh.getSDT(),
                    kh.getDIACHI()
                });     
            }
        }else {
            loadTable();
        }
        
    }
    private void searchCustomerById(){
        String makh = tfsearchtenkh.getText();
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0);
        if(!makh.isEmpty()){
            KHACHHANG kh = customerBus.findKhachHangById(makh);
            if( kh != null){
                model.addRow(new Object[]{
                    kh.getMAKH(),
                    kh.getHOTENKH(),
                    kh.getGIOITINH(),
                    kh.getSDT(),
                    kh.getDIACHI()
                });
            }else{
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + makh);
            }
        }else{
            loadTable();
        }
    }
    
    private KHACHHANG getCustomerFromForm(){
        String MAKH = txtmakh.getText();
        String HOTENKH = txttenkh.getText();
        String GIOITINH = nam.isSelected() ? "Nam" : "Nu";
        String SDT =  txtsdt.getText();
        String DIACHI = txtdc.getText();
        return new KHACHHANG(MAKH, HOTENKH, GIOITINH, SDT, DIACHI);
    }
    
    private boolean validataForm(){
        if (txtmakh.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmakh.requestFocus();
            return false;
        }else if(!txtmakh.getText().matches("KH\\d+")){
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải bắt đầu bằng KH", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmakh.requestFocus();
            return false;
        }
            
        if(txttenkh.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập tên khách hàng","LỖi", JOptionPane.ERROR_MESSAGE);
            txttenkh.requestFocus();
            return false;
        } else if( !txttenkh.getText().matches("[\\p{L}\\s]+")){
            JOptionPane.showMessageDialog(this, "Tên khách hàng không đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttenkh.requestFocus();;
            return false;
        }
        if (!nam.isSelected() && !nu.isSelected()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtsdt.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsdt.requestFocus();
            return false;
        } else if(!txtsdt.getText().matches("0\\d{9}")){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu từ số 0 và có 10 chữ số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsdt.requestFocus();
            return false;
        }
        
        if(txtdc.getText().isEmpty()){
           JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ", "Lỗi", JOptionPane.ERROR_MESSAGE);
           txtdc.requestFocus();
            return false; 
        }
        return true;
    }
    private void clearForm(){
        txtmakh.setText("");
        txttenkh.setText("");
        txtsdt.setText("");
        txtdc.setText("");
        gioiTinh.clearSelection();
    }
    private void disableFromFields(){
        txtmakh.setEnabled(false);
        txttenkh.setEditable(false);
        txtsdt.setEditable(false);
        txtdc.setEditable(false);
        nam.setEnabled(false);
        nu.setEnabled(false);   
    }
    private void enableFormFields(){
//        txtmakh.setEnabled(true);
        txttenkh.setEditable(true);
        txtsdt.setEditable(true);
        txtdc.setEditable(true);
        nam.setEnabled(true);
        nu.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmakh = new javax.swing.JTextField();
        txttenkh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdc = new javax.swing.JTextField();
        btnthemkh = new javax.swing.JButton();
        btnxoakh = new javax.swing.JButton();
        btnsuakh = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        nam = new javax.swing.JRadioButton();
        nu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        tfsearchtenkh = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();

        setBackground(new java.awt.Color(236, 233, 233));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(236, 233, 233));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Thông tin khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã khách hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        txtmakh.setBackground(new java.awt.Color(250, 240, 215));
        txtmakh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmakh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txttenkh.setBackground(new java.awt.Color(250, 240, 215));
        txttenkh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttenkh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại");

        txtsdt.setBackground(new java.awt.Color(250, 240, 215));
        txtsdt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtsdt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ");

        txtdc.setBackground(new java.awt.Color(250, 240, 215));
        txtdc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtdc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnthemkh.setBackground(new java.awt.Color(241, 185, 4));
        btnthemkh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnthemkh.setText("Thêm");
        btnthemkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemkhActionPerformed(evt);
            }
        });

        btnxoakh.setBackground(new java.awt.Color(241, 185, 4));
        btnxoakh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnxoakh.setText("Xóa");
        btnxoakh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoakhActionPerformed(evt);
            }
        });

        btnsuakh.setBackground(new java.awt.Color(241, 185, 4));
        btnsuakh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnsuakh.setText("Sửa");
        btnsuakh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuakhActionPerformed(evt);
            }
        });

        btnluu.setBackground(new java.awt.Color(241, 185, 4));
        btnluu.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
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

        nam.setBackground(new java.awt.Color(250, 240, 215));
        gioiTinh.add(nam);
        nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nam.setText("Nam");

        nu.setBackground(new java.awt.Color(250, 240, 215));
        gioiTinh.add(nu);
        nu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giới tinh");

        tfsearchtenkh.setBackground(new java.awt.Color(250, 240, 215));
        tfsearchtenkh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tfsearchtenkh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnsearch.setBackground(new java.awt.Color(241, 185, 4));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsearch.setText("Tìm Kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(btnthemkh)
                        .addGap(44, 44, 44)
                        .addComponent(btnxoakh)
                        .addGap(44, 44, 44)
                        .addComponent(btnsuakh)
                        .addGap(44, 44, 44)
                        .addComponent(btnluu)
                        .addGap(46, 46, 46)
                        .addComponent(btnhuy))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nam, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(txtdc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(181, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tfsearchtenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nam)
                            .addComponent(nu)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemkh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoakh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuakh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfsearchtenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch))
                        .addContainerGap())))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1080, 200);

        jPanel4.setBackground(new java.awt.Color(250, 240, 215));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel4.setMinimumSize(new java.awt.Dimension(1300, 550));
        jPanel4.setPreferredSize(new java.awt.Dimension(1300, 550));

        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Họ tên khách hàng", "Giới tính", "Số điện thoại", "Địa chỉ"
            }
        ));
        tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCustomer);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 200, 1080, 550);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1150, 750);
    }// </editor-fold>//GEN-END:initComponents

    private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCustomerMouseClicked
        int row = tableCustomer.getSelectedRow();
        if (row >= 0) {
        txtmakh.setText(tableCustomer.getValueAt(row, 0).toString());
        txttenkh.setText(tableCustomer.getValueAt(row, 1).toString());
        String gt= tableCustomer.getValueAt(row, 2).toString();
        if (gt.equalsIgnoreCase("Nam")) {
            nam.setSelected(true);
        } else {
            nu.setSelected(true);
            }
        txtsdt.setText(tableCustomer.getValueAt(row, 3).toString());
        txtdc.setText(tableCustomer.getValueAt(row, 4).toString());
        //txtChucVu.setText(tableNhanVien.getValueAt(row, 5).toString());
//        txtngs.setText(tableCustomer.getValueAt(row, 6).toString());
//        setButtonStates(false, true, true, false);
    }//GEN-LAST:event_tableCustomerMouseClicked
    }
    private void btnthemkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemkhActionPerformed
        clearForm();
       
        enableFormFields();
        txtmakh.setEnabled(true);
        isEditing = false;
//        setButtonStates(false, false, false, true);
    }//GEN-LAST:event_btnthemkhActionPerformed
    
    
    private void btnxoakhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoakhActionPerformed
        deleteCustomer();
    }//GEN-LAST:event_btnxoakhActionPerformed

    private void btnsuakhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuakhActionPerformed
        if(!txtmakh.getText().isEmpty()){
            enableFormFields();
            txtmakh.setEnabled(false);
            isEditing = true;
//            setButtonStates(false, false, false, true);
        }else{
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnsuakhActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
       if (isEditing){
           updateCustomer();
       }
       else{
           addCustomer();
       }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        clearForm();
        disableFromFields();
//        setButtonStates(true, false, false, false);
    }//GEN-LAST:event_btnhuyActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
       searchCustomer();
       searchCustomerById();
    }//GEN-LAST:event_btnsearchActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsuakh;
    private javax.swing.JButton btnthemkh;
    private javax.swing.JButton btnxoakh;
    private javax.swing.ButtonGroup gioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nam;
    private javax.swing.JRadioButton nu;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField tfsearchtenkh;
    private javax.swing.JTextField txtdc;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttenkh;
    // End of variables declaration//GEN-END:variables

//    private void setButtonStates(boolean them, boolean sua, boolean xoa, boolean huy) {
//        btnthemkh.setEnabled(them);
//        btnxoakh.setEnabled(sua);
//        btnxoakh.setEnabled(xoa);
//        btnhuy.setEnabled(huy);
//         
//    }
}
