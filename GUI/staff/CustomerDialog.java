
package GUI.staff;

import DAO.CustomerDAO;

import connect.JDBCconn;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CustomerDialog extends java.awt.Dialog {
     private String customerId;
    private static String phone;
    
    public CustomerDialog(java.awt.Frame parent, String phone) {
        super(parent, true);
        initComponents();
        this.phone = phone;
        setLocationRelativeTo(parent);
    }
    

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gioitinh = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Nam = new javax.swing.JRadioButton();
        Nu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Địa chỉ");
        add(jLabel1);
        jLabel1.setBounds(80, 140, 50, 18);

        txtAddress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(txtAddress);
        txtAddress.setBounds(180, 140, 130, 20);

        btnSave.setBackground(new java.awt.Color(102, 255, 102));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        add(btnSave);
        btnSave.setBounds(160, 220, 70, 30);

        txtName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(txtName);
        txtName.setBounds(180, 100, 130, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Họ và tên");
        add(jLabel2);
        jLabel2.setBounds(80, 100, 60, 18);
        jLabel2.getAccessibleContext().setAccessibleName("Họ và tên\n\n");

        gioitinh.add(Nam);
        Nam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Nam.setText("Nam");
        add(Nam);
        Nam.setBounds(180, 180, 100, 21);

        gioitinh.add(Nu);
        Nu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Nu.setText("Nữ");
        add(Nu);
        Nu.setBounds(270, 180, 120, 21);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN KHÁCH HÀNG");
        add(jLabel3);
        jLabel3.setBounds(-50, 50, 490, 25);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Giới tính");
        add(jLabel4);
        jLabel4.setBounds(80, 180, 60, 18);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
            String name = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            String gender = Nam.isSelected() ? "Nam" : "Nu";
            if (name.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            customerId = saveNewCustomer(name,gender, address, phone);
            dispose();
        
    }//GEN-LAST:event_btnSaveActionPerformed
    private String saveNewCustomer(String name,String gender, String address, String phone) {
        String newId = new CustomerDAO().generateNewCustomerId();
        String query = "INSERT INTO KHACHHANG (MAKH, HOTENKH,GIOITINH, DIACHI, SDT) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = JDBCconn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.executeUpdate();
            return newId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCustomerId() {
        return customerId;
    }

        public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerDialog dialog = new CustomerDialog(new java.awt.Frame(), phone);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Nam;
    private javax.swing.JRadioButton Nu;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup gioitinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
