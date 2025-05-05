package GUI.staff;

import BUS.BillBUS;
import BUS.BillDetailBUS;
import DAO.CustomerDAO;
import DAO.ItemDAO;
import DTO.BillDTO;
import DTO.BillDetailDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import swing.OrderItem;
import swing.PanelItem;
import swing.RoundPanel;
import swing.ScrollBar;
import swing.PictureBox;
import DTO.ItemDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import swing.OrderCard;
import GUI.staff.CustomerDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Order extends javax.swing.JPanel {
       
    private Map<String, OrderItem> orderItems = new HashMap<>();
    private double totalAmount = 0;
    private int totalItems = 0;
    
    public Order(String staffId) {
          this.currentStaffId = staffId;
        initComponents();
        Scroll.setVerticalScrollBar(new ScrollBar());
        loadData();
        
        btnReset.setBackground(new Color(255,255,255));
            btnReset.setForeground(new Color(0,0,0));
            btnOrder.setBackground(new Color(255,255,255));
            btnOrder.setForeground(new Color(0,0,0));
            btnCheckPhone.setBackground(new Color(255,255,255));
            btnCheckPhone.setForeground(new Color(0,0,0));

    }
    
    private void updateOrderSummary(){
        totalAmount = 0;
        totalItems = 0;
        
        
        
        for(OrderItem item : orderItems.values()){
            totalAmount += item.getQuantity() * item.getPrice();
            totalItems += item.getQuantity();
        }
        
        totalItemslb.setText("Tong mon: " +totalItems);
        totalPricelb.setText("Tong tien: "+String.format("%,.0fVND", totalAmount));
    }
    
    public void addToOrder(ItemDTO item, int quantity){
        String productID = item.getMaSP();
        
        if(orderItems.containsKey(productID)){
            OrderItem orderItem = orderItems.get(productID);
            orderItem.setQuantity(orderItem.getQuantity() + quantity);
        }else{
            OrderItem orderItem = new OrderItem(item);
            orderItem.setQuantity(quantity);
            orderItems.put(productID, orderItem);
            OrderItem.add(orderItem);
        }
        updateOrderSummary();
        OrderItem.revalidate();
        OrderItem.repaint();
    }
    
    public void removeOrder(ItemDTO item){
        String productId = item.getMaSP();
        
        if(orderItems.containsKey(productId)) {
            OrderItem orderItem = orderItems.get(productId);
            
            if(orderItem.getQuantity() > 1){
                orderItem.setQuantity(orderItem.getQuantity() - 1);
                orderItem.updateUI();
            }else{
                orderItems.remove(productId);
                OrderItem.remove(orderItem);
            }
            updateOrderSummary();
            OrderItem.revalidate();
            OrderItem.repaint();
        }
    }
    
    private class OrderItem extends RoundPanel{
        private ItemDTO item;
        private int quantity;
        private OrderCard orderCard;
        
        public OrderItem(ItemDTO item){
            this.item = item;
            this.quantity = 1;
            initComponents(); 
            scrollItem.setVerticalScrollBar(new ScrollBar());
            
        }
        
        private void initComponents(){
            setBackground(new Color(254,194,8));
            
            
            
            orderCard = new OrderCard();
            orderCard.getLbName().setText(item.getTenSP());
            orderCard.getLbQuantity().setText("x" + quantity);
            orderCard.getLbPrice().setText(String.format("%,.0fVND", item.getGiaban() * quantity));
        
            orderCard.getPicItem().setImage(item.getImg());
            
            add(orderCard, BorderLayout.CENTER);
        }
        

        public void setQuantity(int quantity){
            this.quantity = quantity;
            orderCard.getLbQuantity().setText("x" + quantity);
            orderCard.getLbPrice().setText(String.format("%,.0fVND", item.getGiaban() * quantity));
        }
        
        public int getQuantity(){
            return quantity;
        }
        
        public double getPrice(){
            return item.getGiaban();
        }
  
    }

    public void addItem(ItemDTO data){
         ItemsCard item = new ItemsCard();
         item.setData(data);
         item.addMouseListener(new MouseAdapter(){
             @Override
             public void mousePressed(MouseEvent me){
                 if(SwingUtilities.isLeftMouseButton(me) ){
                     item.setSelected(!item.isSelected());
                 }
             }
         });
        panelItem.add(item);
        panelItem.repaint();
        panelItem.revalidate();
    }
    
    private void loadData(){
        ItemDAO dao = new ItemDAO();
        List<ItemDTO> items = dao.getAllItems();
        
        panelItem.removeAll();
        
        for(ItemDTO item : items){
            addItem(item);
        }
    }
    
    private BillBUS billBUS = new BillBUS();
    private BillDetailBUS billDetailBUS = new BillDetailBUS();
    private String currentStaffId ;
    private String currentCustomerId ;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelContainer = new swing.RoundPanel();
        OrderListContainer = new swing.RoundPanel();
        TitleOrderDetails = new javax.swing.JPanel();
        lbMenu1 = new javax.swing.JLabel();
        lbMenu = new javax.swing.JLabel();
        scrollItem = new javax.swing.JScrollPane();
        OrderItem = new swing.OrderItem();
        totalItemslb = new javax.swing.JLabel();
        totalPricelb = new javax.swing.JLabel();
        ButtonContainer = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnCheckPhone = new javax.swing.JButton();
        Scroll = new javax.swing.JScrollPane();
        panelItem = new swing.PanelItem();
        item1 = new swing.RoundPanel();
        lbTenSP = new javax.swing.JLabel();
        lbGiaBan = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        quantityLabel = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        pic = new swing.PictureBox();
        titlePanelItem = new swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();

        OrderListContainer.setBackground(new java.awt.Color(254, 194, 8));

        TitleOrderDetails.setBackground(new java.awt.Color(254, 194, 8));

        lbMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMenu1.setForeground(new java.awt.Color(255, 102, 0));
        lbMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMenu1.setText("Order");

        lbMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbMenu.setText("Details");

        javax.swing.GroupLayout TitleOrderDetailsLayout = new javax.swing.GroupLayout(TitleOrderDetails);
        TitleOrderDetails.setLayout(TitleOrderDetailsLayout);
        TitleOrderDetailsLayout.setHorizontalGroup(
            TitleOrderDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleOrderDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TitleOrderDetailsLayout.setVerticalGroup(
            TitleOrderDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleOrderDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrollItem.setBorder(null);
        scrollItem.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollItem.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollItem.setViewportView(OrderItem);

        totalItemslb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalItemslb.setText("Tổng món:");

        totalPricelb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalPricelb.setText("Tổng tiền:");

        ButtonContainer.setBackground(new java.awt.Color(254, 194, 8));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOrder.setText("Order");
        btnOrder.setBorderPainted(false);
        btnOrder.setFocusPainted(false);
        btnOrder.setOpaque(true);
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonContainerLayout = new javax.swing.GroupLayout(ButtonContainer);
        ButtonContainer.setLayout(ButtonContainerLayout);
        ButtonContainerLayout.setHorizontalGroup(
            ButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        ButtonContainerLayout.setVerticalGroup(
            ButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        btnCheckPhone.setText("SĐT");
        btnCheckPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckPhoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrderListContainerLayout = new javax.swing.GroupLayout(OrderListContainer);
        OrderListContainer.setLayout(OrderListContainerLayout);
        OrderListContainerLayout.setHorizontalGroup(
            OrderListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderListContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrderListContainerLayout.createSequentialGroup()
                        .addComponent(ButtonContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(OrderListContainerLayout.createSequentialGroup()
                        .addGroup(OrderListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollItem)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderListContainerLayout.createSequentialGroup()
                                .addComponent(totalPricelb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(114, 114, 114))
                            .addGroup(OrderListContainerLayout.createSequentialGroup()
                                .addComponent(TitleOrderDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(3, 3, 3))
                    .addGroup(OrderListContainerLayout.createSequentialGroup()
                        .addComponent(totalItemslb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCheckPhone)
                        .addGap(36, 36, 36))))
        );
        OrderListContainerLayout.setVerticalGroup(
            OrderListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderListContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleOrderDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollItem, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OrderListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalItemslb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheckPhone))
                .addGap(2, 2, 2)
                .addComponent(totalPricelb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        Scroll.setBorder(null);
        Scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelItem.setBackground(new java.awt.Color(242, 242, 242));

        item1.setBackground(new java.awt.Color(255, 255, 255));

        lbTenSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTenSP.setForeground(new java.awt.Color(102, 102, 102));
        lbTenSP.setText("Gà Tắm Nước Mắm");

        lbGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbGiaBan.setForeground(new java.awt.Color(255, 102, 0));
        lbGiaBan.setText("49.000vnd");

        controlPanel.setBackground(new java.awt.Color(255, 255, 255));
        controlPanel.setPreferredSize(new java.awt.Dimension(151, 35));

        quantityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantityLabel.setText("0");

        btnMinus.setBackground(new java.awt.Color(254, 194, 8));
        btnMinus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-subtract-15.png"))); // NOI18N
        btnMinus.setBorderPainted(false);
        btnMinus.setFocusPainted(false);
        btnMinus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnPlus.setBackground(new java.awt.Color(254, 194, 8));
        btnPlus.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-15.png"))); // NOI18N
        btnPlus.setBorderPainted(false);
        btnPlus.setFocusPainted(false);
        btnPlus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnMinus)
                .addGap(18, 18, 18)
                .addComponent(quantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPlus)
                .addGap(11, 11, 11))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinus)
                    .addComponent(btnPlus))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/img_ff/doan/ga_nuocmam.png"))); // NOI18N

        javax.swing.GroupLayout item1Layout = new javax.swing.GroupLayout(item1);
        item1.setLayout(item1Layout);
        item1Layout.setHorizontalGroup(
            item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTenSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lbGiaBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        item1Layout.setVerticalGroup(
            item1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(item1Layout.createSequentialGroup()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGiaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelItem.add(item1);

        Scroll.setViewportView(panelItem);

        titlePanelItem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(245, 245, 245));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 194, 8));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALL PRODUCTS");

        javax.swing.GroupLayout titlePanelItemLayout = new javax.swing.GroupLayout(titlePanelItem);
        titlePanelItem.setLayout(titlePanelItemLayout);
        titlePanelItemLayout.setHorizontalGroup(
            titlePanelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelItemLayout.setVerticalGroup(
            titlePanelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelContainerLayout = new javax.swing.GroupLayout(PanelContainer);
        PanelContainer.setLayout(PanelContainerLayout);
        PanelContainerLayout.setHorizontalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContainerLayout.createSequentialGroup()
                .addGroup(PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titlePanelItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OrderListContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelContainerLayout.setVerticalGroup(
            PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContainerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OrderListContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelContainerLayout.createSequentialGroup()
                        .addComponent(titlePanelItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 757, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed

    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed

    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        orderItems.clear();
        OrderItem.removeAll();
        
        totalAmount = 0;
        totalItems = 0;
        totalItemslb.setText("Tong mon: 0");
        totalPricelb.setText("Tong tien: 0VND");
        
        for(Component comp : panelItem.getComponents()){
            if(comp instanceof ItemsCard){
                ItemsCard itemCard = (ItemsCard) comp;
                itemCard.setQuantity(0);
                itemCard.setSelected(false);
                itemCard.updateButtonsVisibility();
            }
        }
        
        OrderItem.revalidate();
        OrderItem.repaint();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        for(OrderItem orderItem : orderItems.values()){
            if(orderItem.item.getMaSP() == null || orderItem.item.getMaSP().isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(orderItem.getQuantity() <= 0){
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        if(orderItems.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món trước khi đặt hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (currentCustomerId == null) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng trước!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        return;
    }
        String newBillId = billBUS.generateNewBillId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        
        BillDTO newBill = new BillDTO();
        newBill.setMAHD(newBillId);
        newBill.setNGAYLAP(currentDate);
        newBill.setMANV(currentStaffId);
        newBill.setMAKH(currentCustomerId);
        newBill.setTONGTIEN(totalAmount);
        newBill.setTRANGTHAI("Đã thanh toán");
        
        try{
            boolean billAdded = billBUS.addBill(newBill);
            if(!billAdded){
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại", "Loi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean allDetailsAdded = true;
            StringBuilder errorDetails = new StringBuilder();
            
            for(OrderItem orderItem : orderItems.values()){
                BillDetailDTO billdetail = new BillDetailDTO();
                billdetail.setMAHD(newBillId);
                billdetail.setMASP(orderItem.item.getMaSP());
                billdetail.setSOLUONG(orderItem.getQuantity());
                billdetail.setDONGIA(orderItem.getPrice());
                
                System.out.println("Thêm chi tiết : " +billdetail.getMAHD() + " - " +
                                billdetail.getMASP() + " - " +
                                billdetail.getSOLUONG() + " - " +
                                billdetail.getDONGIA());
                
                if(!billDetailBUS.addBillDetail(billdetail)){
                    allDetailsAdded = false;
                    errorDetails.append("loi khi them san pham: ").append(orderItem.item.getMaSP()).append("\n");
                    break;
                }
            }
            
            if(allDetailsAdded){
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn " +newBillId+ " thành công!","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                btnResetActionPerformed(null);
            }else{
                billBUS.deleteBill(newBillId);
                JOptionPane.showMessageDialog(this, "Tạo chi tiết hóa đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống "+ e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOrderActionPerformed
    
    private void btnCheckPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckPhoneActionPerformed
        String phone = JOptionPane.showInputDialog(this, "Nhập số điện thoại khách hàng:");
    if (phone != null && !phone.trim().isEmpty()) {
        currentCustomerId = getCustomerIdByPhone(phone); // Lấy MAKH
        if (currentCustomerId != null) {
            updateOrderSummary(); // Cập nhật tổng tiền/số lượng
            JOptionPane.showMessageDialog(this, "Đã xác nhận khách hàng: " + currentCustomerId);
        }
    }
    }//GEN-LAST:event_btnCheckPhoneActionPerformed
    private String getCustomerIdByPhone(String phone) {
    String customerId = new CustomerDAO().getCustomerIdByPhone(phone);
    if (customerId != null) {
        return customerId; // Trả về MAKH nếu tìm thấy
    } else {
        // Hiển thị dialog nhập thông tin khách hàng mới
        CustomerDialog dialog = new CustomerDialog((JFrame) SwingUtilities.getWindowAncestor(this), phone);
        dialog.setVisible(true);
        return dialog.getCustomerId(); // Trả về MAKH mới hoặc null nếu hủy
    }
}
// Thêm nút/xử lý nhập số điện thoại trong giao diện Order


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonContainer;
    private swing.OrderItem OrderItem;
    private swing.RoundPanel OrderListContainer;
    private swing.RoundPanel PanelContainer;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JPanel TitleOrderDetails;
    private javax.swing.JButton btnCheckPhone;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnReset;
    private javax.swing.JPanel controlPanel;
    private swing.RoundPanel item1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbGiaBan;
    private javax.swing.JLabel lbMenu;
    private javax.swing.JLabel lbMenu1;
    private javax.swing.JLabel lbTenSP;
    private swing.PanelItem panelItem;
    private swing.PictureBox pic;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JScrollPane scrollItem;
    private swing.RoundPanel titlePanelItem;
    private javax.swing.JLabel totalItemslb;
    private javax.swing.JLabel totalPricelb;
    // End of variables declaration//GEN-END:variables
}
