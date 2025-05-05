/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author PC
 */
public class test {
   
    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();

        // In toàn bộ khách hàng để kiểm tra
        System.out.println("Toàn bộ KH:");
        for (var kh : dao.getAllKhachHang()) {
            System.out.println(kh.getSDT() + " -> " + kh.getMAKH());
        }

        String phone = "0909123456".trim();
        System.out.println("Đang tìm với SDT: [" + phone + "]");

        String makh = dao.getCustomerIdByPhone(phone);

        if (makh != null) {
            System.out.println("Tìm thấy MAKH: " + makh);
        } else {
            System.out.println("Không tìm thấy khách hàng với số điện thoại: " + phone);
        }
    }
}

