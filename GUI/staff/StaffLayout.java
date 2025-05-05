  package GUI.staff;

import GUI.Login;
import GUI.admin.Bill;
import GUI.admin.Customer;
import GUI.admin.Home;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StaffLayout extends javax.swing.JFrame {
    private String currentStaffId;
    private java.util.List<JButton> menuButtons = new ArrayList<>();
    private JButton currentlyActiveButton = null;
    
    
    public StaffLayout() {
        initComponents();
        menuButtons.add(btnHome);
        menuButtons.add(btnCustomer);
        menuButtons.add(btnOrder);
        menuButtons.add(btnBill);
        menuButtons.add(btnLogout);
         for (JButton btn : menuButtons){
            resetButtonColor(btn);
        }
    }
    
    private void resetButtonColor(JButton button){
        button.setBackground(new Color(254,194,8));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    private void setActiveButton(JButton selectedButton){
       if(currentlyActiveButton != null){
           resetButtonColor(currentlyActiveButton);
       }
       
       selectedButton.setBackground(new Color(218,41,28)); //mau moi khi click vao
       selectedButton.setForeground(Color.BLACK);
       
       currentlyActiveButton = selectedButton;
    }
    
    public StaffLayout(String name, String role, String staffId){
         initComponents();
         this.currentStaffId = staffId;
        jLabelName.setText(name);
        jLabelRole.setText(role);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Header = new swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        SideMenu = new javax.swing.JPanel();
        roundPanel2 = new swing.RoundPanel();
        imageAvatar1 = new swing.ImageAvatar();
        jLabelName = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        roundPanel3 = new swing.RoundPanel();
        btnHome = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        mainPanel = new swing.RoundPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 233, 233));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 600));

        Header.setBackground(new java.awt.Color(254, 194, 8));
        Header.setPreferredSize(new java.awt.Dimension(100, 100));
        Header.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        Header.add(jLabel3);
        jLabel3.setBounds(-20, 0, 130, 79);

        SideMenu.setBackground(new java.awt.Color(236, 233, 233));
        SideMenu.setPreferredSize(new java.awt.Dimension(208, 916));

        roundPanel2.setBackground(new java.awt.Color(254, 194, 8));
        roundPanel2.setPreferredSize(new java.awt.Dimension(208, 85));

        imageAvatar1.setForeground(new java.awt.Color(255, 255, 255));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile.jpg"))); // NOI18N

        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setText("Tien Ho");

        jLabelRole.setText("Staff");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRole)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(254, 194, 8));
        roundPanel3.setPreferredSize(new java.awt.Dimension(208, 825));

        btnHome.setBackground(new java.awt.Color(254, 194, 8));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setBorderPainted(false);
        btnHome.setFocusPainted(false);
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.setPreferredSize(new java.awt.Dimension(97, 32));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(254, 194, 8));
        btnCustomer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customers.png"))); // NOI18N
        btnCustomer.setText("Customer");
        btnCustomer.setBorderPainted(false);
        btnCustomer.setFocusPainted(false);
        btnCustomer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCustomer.setPreferredSize(new java.awt.Dimension(97, 32));
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnOrder.setBackground(new java.awt.Color(254, 194, 8));
        btnOrder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/order.png"))); // NOI18N
        btnOrder.setText("Order");
        btnOrder.setBorderPainted(false);
        btnOrder.setFocusPainted(false);
        btnOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOrder.setPreferredSize(new java.awt.Dimension(97, 32));
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnBill.setBackground(new java.awt.Color(254, 194, 8));
        btnBill.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bill-32.png"))); // NOI18N
        btnBill.setText("Bill");
        btnBill.setBorderPainted(false);
        btnBill.setFocusPainted(false);
        btnBill.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBill.setPreferredSize(new java.awt.Dimension(97, 32));
        btnBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(254, 194, 8));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        btnLogout.setText("Log out");
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setPreferredSize(new java.awt.Dimension(97, 32));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 532, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout SideMenuLayout = new javax.swing.GroupLayout(SideMenu);
        SideMenu.setLayout(SideMenuLayout);
        SideMenuLayout.setHorizontalGroup(
            SideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideMenuLayout.setVerticalGroup(
            SideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideMenuLayout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1286, 0));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1286, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setActiveButton(btnHome);
        mainPanel.removeAll();

        // Tạo panel mới từ class Staff
        Home homePanel = new Home();

        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(homePanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();   
     }//GEN-LAST:event_btnHomeActionPerformed
 
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        setActiveButton(btnLogout);
         Login login = new Login();
                   login.setVisible(true);
                   login.pack();
                    login.setLocationRelativeTo(null);
                    this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        setActiveButton(btnCustomer);
        mainPanel.removeAll();

        Customer customerPanel = new Customer();
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(customerPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
        
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        setActiveButton(btnOrder);
        mainPanel.removeAll();

        Order orderPanel = new Order(currentStaffId);
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(orderPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillActionPerformed
        setActiveButton(btnBill);
        mainPanel.removeAll();

        Bill billPanel = new Bill();
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(billPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_btnBillActionPerformed

    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.RoundPanel Header;
    private javax.swing.JPanel SideMenu;
    private javax.swing.JButton btnBill;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JPanel jPanel1;
    private swing.RoundPanel mainPanel;
    private swing.RoundPanel roundPanel2;
    private swing.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables

}
