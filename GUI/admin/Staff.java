/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.admin;

import BUS.StaffBUS;
import DAO.StaffDAO;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import connect.JDBCconn;

public class Staff extends javax.swing.JPanel {

    private StaffBUS staffBus;
    private boolean isEditing = false;


    public Staff() {
        initComponents();
        staffBus = new StaffBUS();
        loadTable();


    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tableNV.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        // Lấy danh sách nhân viên từ BUS
        java.util.List<DTO.StaffDTO> list = staffBus.disPlayListStaff();

        for (DTO.StaffDTO nv : list) {
            model.addRow(new Object[]{
                nv.getMANV(),
                nv.getHOTENNV(),
                nv.getGIOITINH(),
                nv.getSDT(),
                nv.getDIACHI(),
                nv.getNGAYSINH()
            });
        }
    }

    private void addStaff() {
        if (validateForm()) {
            DTO.StaffDTO nv = getStaffFromForm();
            if (staffBus.addStaff(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                clearForm();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateStaff() {
        if (validateForm()) {
            DTO.StaffDTO nv = getStaffFromForm();
            if (staffBus.updateNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
                clearForm();
                loadTable();
                isEditing = false;
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteStaff() {
        String manv = txtmanv.getText();
        if (!manv.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (staffBus.deleteNhanVien(manv)) {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                    clearForm();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByComboChoice() {
    String keyword = tfsearch.getText().trim();
    String type = cbSearch.getSelectedItem().toString();
    
    DefaultTableModel model = (DefaultTableModel) tableNV.getModel();
    model.setRowCount(0); // Xóa bảng

    if (keyword.isEmpty()) {
        loadTable(); // Nếu không nhập gì thì hiện lại toàn bộ
        return;
    }

    if (type.equals("Tìm theo mã")) {
            DTO.StaffDTO nv = staffBus.findNhanVienById(keyword);
        if (nv != null) {
            model.addRow(new Object[]{
                nv.getMANV(),
                nv.getHOTENNV(),
                nv.getGIOITINH(),
                nv.getSDT(),
                nv.getDIACHI(),
                nv.getNGAYSINH()
            });
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có mã: " + keyword);
        }
    } else { // Tìm theo tên
        java.util.List<DTO.StaffDTO> list = staffBus.searchNhanVienByName(keyword);
        for (DTO.StaffDTO nv : list) {
            model.addRow(new Object[]{
                nv.getMANV(),
                nv.getHOTENNV(),
                nv.getGIOITINH(),
                nv.getSDT(),
                nv.getDIACHI(),
                nv.getNGAYSINH()
            });
        }
    }
}

    private DTO.StaffDTO getStaffFromForm() {
        String MANV = txtmanv.getText();
        String HOTENNV = txthotennv.getText();
        String GIOITINH = nam.isSelected() ? "Nam" : "Nu";
        String SDT = txtsdt.getText();
        String DIACHI = txtdc.getText();
        String NGAYSINH = txtngs.getText();
        return new DTO.StaffDTO(MANV, HOTENNV, GIOITINH, SDT, DIACHI, NGAYSINH);
    }

    private boolean validateForm() {
        // Kiểm tra các trường bắt buộc
        if (txtmanv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmanv.requestFocus();
            return false;
        } else if (!txtmanv.getText().matches("NV\\d+")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không đúng định dạng NV ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtmanv.requestFocus();
            return false;
        }

        if (txthotennv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txthotennv.requestFocus();
            return false;
        } else if (!txthotennv.getText().matches("[\\p{L}\\s]+")) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txthotennv.requestFocus();
            return false;
        }

        // Kiểm tra giới tính
        if (!nam.isSelected() && !nu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra số điện thoại
        if (txtsdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsdt.requestFocus();
            return false;
        } else if (!txtsdt.getText().matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu 0 và co 10 chữ số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtsdt.requestFocus();
            return false;
        }
        // Kiểm tra ngày sinh định dạng yyyy-MM-dd
        String ngaysinhStr = txtngs.getText().trim();
        if (!ngaysinhStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Định dạng phải là yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtngs.requestFocus();
            return false;
        }
        if (txtdc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtdc.requestFocus();
            return false;
        }

        return true;
    }
    // Xóa form

    private void clearForm() {
        txtmanv.setText("");
        txthotennv.setText("");
        txtsdt.setText("");
        txtdc.setText("");
        txtngs.setText("");
        gioitinh.clearSelection();
    }

    private void disableFormFields() {
        txtmanv.setEnabled(false);
        txthotennv.setEnabled(false);
        txtsdt.setEnabled(false);
        txtdc.setEnabled(false);
        txtngs.setEnabled(false);
        nam.setEnabled(false);
        nu.setEnabled(false);
    }

    private void enableFormFields() {
        txthotennv.setEnabled(true);
        txtsdt.setEnabled(true);
        txtdc.setEnabled(true);
        txtngs.setEnabled(true);
        nam.setEnabled(true);
        nu.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gioitinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txthotennv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtngs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdc = new javax.swing.JTextField();
        btnthemnv = new javax.swing.JButton();
        btnxoanv = new javax.swing.JButton();
        btnsuanv = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        nam = new javax.swing.JRadioButton();
        nu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        tfsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        cbSearch = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();

        gioitinh.add(nam);
        gioitinh.add(nu);

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(236, 233, 233));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 580));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(250, 240, 215));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 200));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Thông tin nhân viên");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(410, 0, 190, 25);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhân viên");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(100, 40, 100, 20);

        txtmanv.setBackground(new java.awt.Color(250, 240, 215));
        txtmanv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmanv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(txtmanv);
        txtmanv.setBounds(210, 40, 100, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhân viên");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(340, 40, 90, 20);

        txthotennv.setBackground(new java.awt.Color(250, 240, 215));
        txthotennv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txthotennv.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(txthotennv);
        txthotennv.setBounds(450, 40, 100, 23);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày sinh");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(580, 40, 70, 20);

        txtngs.setBackground(new java.awt.Color(250, 240, 215));
        txtngs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtngs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(txtngs);
        txtngs.setBounds(650, 40, 100, 23);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(340, 100, 100, 20);

        txtsdt.setBackground(new java.awt.Color(250, 240, 215));
        txtsdt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtsdt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });
        jPanel2.add(txtsdt);
        txtsdt.setBounds(450, 100, 100, 23);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Địa chỉ");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(580, 100, 50, 20);

        txtdc.setBackground(new java.awt.Color(250, 240, 215));
        txtdc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtdc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(txtdc);
        txtdc.setBounds(650, 100, 100, 23);

        btnthemnv.setBackground(new java.awt.Color(241, 185, 4));
        btnthemnv.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnthemnv.setText("Thêm");
        btnthemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemnvActionPerformed(evt);
            }
        });
        jPanel2.add(btnthemnv);
        btnthemnv.setBounds(100, 150, 75, 30);

        btnxoanv.setBackground(new java.awt.Color(241, 185, 4));
        btnxoanv.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnxoanv.setText("Xóa");
        btnxoanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoanvActionPerformed(evt);
            }
        });
        jPanel2.add(btnxoanv);
        btnxoanv.setBounds(210, 150, 72, 30);

        btnsuanv.setBackground(new java.awt.Color(241, 185, 4));
        btnsuanv.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsuanv.setText("Sửa");
        btnsuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuanvActionPerformed(evt);
            }
        });
        jPanel2.add(btnsuanv);
        btnsuanv.setBounds(330, 150, 70, 30);

        btnluu.setBackground(new java.awt.Color(241, 185, 4));
        btnluu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });
        jPanel2.add(btnluu);
        btnluu.setBounds(440, 150, 72, 30);

        btnhuy.setBackground(new java.awt.Color(241, 185, 4));
        btnhuy.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnhuy.setText("Hủy");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });
        jPanel2.add(btnhuy);
        btnhuy.setBounds(560, 150, 72, 30);

        nam.setBackground(new java.awt.Color(250, 240, 215));
        gioitinh.add(nam);
        nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nam.setText("Nam");
        jPanel2.add(nam);
        nam.setBounds(210, 80, 70, 25);

        nu.setBackground(new java.awt.Color(250, 240, 215));
        gioitinh.add(nu);
        nu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nu.setText("Nữ");
        jPanel2.add(nu);
        nu.setBounds(210, 110, 70, 25);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giới tính");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(100, 100, 90, 20);

        tfsearch.setBackground(new java.awt.Color(250, 240, 217));
        tfsearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfsearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(tfsearch);
        tfsearch.setBounds(660, 170, 130, 22);

        btnsearch.setBackground(new java.awt.Color(250, 185, 4));
        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnsearch.setText("Tìm kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnsearch);
        btnsearch.setBounds(930, 170, 100, 25);

        cbSearch.setBackground(new java.awt.Color(250, 240, 215));
        cbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo mã", "Tìm theo tên" }));
        cbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSearchActionPerformed(evt);
            }
        });
        jPanel2.add(cbSearch);
        cbSearch.setBounds(800, 170, 120, 26);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1300, 200);

        jPanel3.setBackground(new java.awt.Color(250, 240, 215));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên"));

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên nhân viên", "Giới tính", "Số điện thoại", "Địa chỉ", "Ngày sinh"
            }
        ));
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 200, 1300, 550);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1240, 760);
    }// </editor-fold>//GEN-END:initComponents

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        searchByComboChoice();
    }//GEN-LAST:event_btnsearchActionPerformed
//    private void setButtonStates(boolean them, boolean sua, boolean xoa, boolean huy) {
//        btnthemnv.setEnabled(them);
//        btnsuanv.setEnabled(sua);
//        btnxoanv.setEnabled(xoa);
//        btnhuy.setEnabled(huy);
//    }
    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        int row = tableNV.getSelectedRow();
        if (row >= 0) {
            txtmanv.setText(tableNV.getValueAt(row, 0).toString());
            txthotennv.setText(tableNV.getValueAt(row, 1).toString());
            String gioiTinh = tableNV.getValueAt(row, 2).toString();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                nam.setSelected(true);
            } else {
                nu.setSelected(true);
            }
            txtsdt.setText(tableNV.getValueAt(row, 3).toString());
            txtdc.setText(tableNV.getValueAt(row, 4).toString());
            //txtChucVu.setText(tableNhanVien.getValueAt(row, 5).toString());
            Object ngaysinhObj = tableNV.getValueAt(row, 5); // Cột 5 hoặc 6 tùy bạn
            txtngs.setText(ngaysinhObj != null ? ngaysinhObj.toString() : "");
//            setButtonStates(false, true, true, false);
        }

    }//GEN-LAST:event_tableNVMouseClicked
    // Thiết lập trạng thái các nút


    private void btnthemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemnvActionPerformed

        clearForm();
        enableFormFields();
        txtmanv.setEnabled(true);
        isEditing = false;
//        setButtonStates(false, false, false, true);
    }//GEN-LAST:event_btnthemnvActionPerformed

    private void btnsuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuanvActionPerformed
        if (!txtmanv.getText().isEmpty()) {
            enableFormFields();
            txtmanv.setEnabled(false);
            isEditing = true;
//            setButtonStates(false, false, false, true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnsuanvActionPerformed

    private void btnxoanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoanvActionPerformed
        deleteStaff();
    }//GEN-LAST:event_btnxoanvActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        if (isEditing) {
            updateStaff();
        } else {
            addStaff();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        clearForm();
        disableFormFields();
//        setButtonStates(true, false, false, false);// TODO add your handling code here:
    }//GEN-LAST:event_btnhuyActionPerformed

    private void cbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsuanv;
    private javax.swing.JButton btnthemnv;
    private javax.swing.JButton btnxoanv;
    private javax.swing.JComboBox<String> cbSearch;
    private javax.swing.ButtonGroup gioitinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nam;
    private javax.swing.JRadioButton nu;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField tfsearch;
    private javax.swing.JTextField txtdc;
    private javax.swing.JTextField txthotennv;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtngs;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
