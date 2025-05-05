package GUI.admin;

import GUI.Login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import swing.RoundPanel;


public class AdminLayout extends javax.swing.JFrame {
    
    private java.util.List<JButton> menuButtons = new ArrayList<>();
    private JButton currentlyActiveButton = null;
    
    
    public AdminLayout() {
        initComponents();
        //setOpaque(false);
        menuButtons.add(btnHome);
        menuButtons.add(btnStaff);
        menuButtons.add(btnCustomer);
        menuButtons.add(btnOrder);
        menuButtons.add(btnBill);
        menuButtons.add(btnLogout);
        menuButtons.add(btnStatistic);
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
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Disk = new javax.swing.JMenuItem();
        Drink = new javax.swing.JMenuItem();
        Dessert = new javax.swing.JMenuItem();
        PanelContainer = new javax.swing.JPanel();
        Header = new swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        SideMenu = new javax.swing.JPanel();
        MenuContainer2 = new swing.RoundPanel();
        btnHome = new javax.swing.JButton();
        btnStaff = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        btnStatistic = new javax.swing.JButton();
        MenuContainer1 = new swing.RoundPanel();
        imageAvatar1 = new swing.ImageAvatar();
        jLabelName = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        mainPanel = new swing.RoundPanel();

        Disk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Disk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/car_1411441.png"))); // NOI18N
        Disk.setText("Main Disk");
        Disk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Disk);

        Drink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Drink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/soft-drink_2405479.png"))); // NOI18N
        Drink.setText("Drink");
        Drink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrinkActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Drink);

        Dessert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Dessert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pudding_1888848.png"))); // NOI18N
        Dessert.setText("Dessert");
        Dessert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DessertActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Dessert);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FAST FOOD STORE");

        PanelContainer.setBackground(new java.awt.Color(236, 233, 233));
        PanelContainer.setPreferredSize(new java.awt.Dimension(1200, 600));

        Header.setBackground(new java.awt.Color(254, 194, 8));
        Header.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        Header.add(jLabel3);
        jLabel3.setBounds(-20, -10, 127, 90);

        SideMenu.setBackground(new java.awt.Color(236, 233, 233));

        MenuContainer2.setBackground(new java.awt.Color(254, 194, 8));

        btnHome.setBackground(new java.awt.Color(218, 41, 28));
        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setBorderPainted(false);
        btnHome.setFocusPainted(false);
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnStaff.setBackground(new java.awt.Color(254, 194, 8));
        btnStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStaff.setForeground(new java.awt.Color(242, 242, 242));
        btnStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/staff.png"))); // NOI18N
        btnStaff.setText("Staff  ");
        btnStaff.setBorderPainted(false);
        btnStaff.setFocusPainted(false);
        btnStaff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(254, 194, 8));
        btnCustomer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(242, 242, 242));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customers.png"))); // NOI18N
        btnCustomer.setText("  Customers");
        btnCustomer.setBorderPainted(false);
        btnCustomer.setFocusPainted(false);
        btnCustomer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnOrder.setBackground(new java.awt.Color(254, 194, 8));
        btnOrder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOrder.setForeground(new java.awt.Color(242, 242, 242));
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        btnOrder.setText("Menu");
        btnOrder.setBorderPainted(false);
        btnOrder.setFocusPainted(false);
        btnOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnOrder.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnOrder.setIconTextGap(17);
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(254, 194, 8));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(242, 242, 242));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        btnLogout.setText("Log out  ");
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnBill.setBackground(new java.awt.Color(254, 194, 8));
        btnBill.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBill.setForeground(new java.awt.Color(242, 242, 242));
        btnBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bill-32.png"))); // NOI18N
        btnBill.setText("   Bill");
        btnBill.setBorderPainted(false);
        btnBill.setFocusPainted(false);
        btnBill.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBill.setIconTextGap(2);
        btnBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillActionPerformed(evt);
            }
        });

        btnStatistic.setBackground(new java.awt.Color(254, 194, 8));
        btnStatistic.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStatistic.setForeground(new java.awt.Color(242, 242, 242));
        btnStatistic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-statistic-32.png"))); // NOI18N
        btnStatistic.setText("Statistics");
        btnStatistic.setBorderPainted(false);
        btnStatistic.setFocusPainted(false);
        btnStatistic.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStatistic.setIconTextGap(12);
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuContainer2Layout = new javax.swing.GroupLayout(MenuContainer2);
        MenuContainer2.setLayout(MenuContainer2Layout);
        MenuContainer2Layout.setHorizontalGroup(
            MenuContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuContainer2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStatistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuContainer2Layout.setVerticalGroup(
            MenuContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuContainer2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        MenuContainer1.setBackground(new java.awt.Color(254, 194, 8));

        imageAvatar1.setBackground(new java.awt.Color(242, 242, 242));
        imageAvatar1.setForeground(new java.awt.Color(242, 242, 242));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile.jpg"))); // NOI18N

        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setText("Bao Tran");

        jLabelRole.setForeground(new java.awt.Color(51, 51, 51));
        jLabelRole.setText("Admin");

        javax.swing.GroupLayout MenuContainer1Layout = new javax.swing.GroupLayout(MenuContainer1);
        MenuContainer1.setLayout(MenuContainer1Layout);
        MenuContainer1Layout.setHorizontalGroup(
            MenuContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuContainer1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuContainer1Layout.setVerticalGroup(
            MenuContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuContainer1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(MenuContainer1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRole)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SideMenuLayout = new javax.swing.GroupLayout(SideMenu);
        SideMenu.setLayout(SideMenuLayout);
        SideMenuLayout.setHorizontalGroup(
            SideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuContainer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideMenuLayout.setVerticalGroup(
            SideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideMenuLayout.createSequentialGroup()
                .addComponent(MenuContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuContainer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout PanelContainerLayout = new javax.swing.GroupLayout(PanelContainer);
        PanelContainer.setLayout(PanelContainerLayout);
        PanelContainerLayout.setHorizontalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContainerLayout.createSequentialGroup()
                .addComponent(SideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelContainerLayout.setVerticalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContainerLayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
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

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        setActiveButton(btnStaff);
         mainPanel.removeAll();

        // Tạo panel mới từ class Staff
        Staff staffPanel = new Staff();

        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(staffPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
       jPopupMenu1.show(btnOrder, 0, btnOrder.getHeight());
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        setActiveButton(btnLogout);
         Login login = new Login();
                   login.setVisible(true);
                   login.pack();
                    login.setLocationRelativeTo(null);
                    this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

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

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
       setActiveButton(btnStatistic);
    mainPanel.removeAll();

    Statistics statisticsHomePanel = new Statistics(this); // ⬅ thêm (this)
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(statisticsHomePanel, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void DiskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskActionPerformed
         mainPanel.removeAll();

        GUI.admin.MainDish dishPanel = new GUI.admin.MainDish();
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(dishPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint(); // TODO add your handling code here:
        setActiveButton(btnOrder);
    }//GEN-LAST:event_DiskActionPerformed

    private void DrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrinkActionPerformed
        mainPanel.removeAll();

        GUI.admin.Drink drinkPanel = new GUI.admin.Drink();
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(drinkPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint(); // TODO add your handling code here:
        setActiveButton(btnOrder);
    }//GEN-LAST:event_DrinkActionPerformed

    private void DessertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DessertActionPerformed
        mainPanel.removeAll();

        GUI.admin.Dessert dessertPanel = new GUI.admin.Dessert();
        // Thêm vào mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(dessertPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint(); // TODO add your handling code here:
        setActiveButton(btnOrder);
    }//GEN-LAST:event_DessertActionPerformed

    public RoundPanel getMainPanel() {
    return mainPanel;
}

    
    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLayout().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Dessert;
    private javax.swing.JMenuItem Disk;
    private javax.swing.JMenuItem Drink;
    private swing.RoundPanel Header;
    private swing.RoundPanel MenuContainer1;
    private swing.RoundPanel MenuContainer2;
    private javax.swing.JPanel PanelContainer;
    private javax.swing.JPanel SideMenu;
    private javax.swing.JButton btnBill;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnStaff;
    private javax.swing.JButton btnStatistic;
    private swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JPopupMenu jPopupMenu1;
    private swing.RoundPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
