/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhachHangDAO;
import java.util.ArrayList;
import model.KHACHHANG;


public class CustomerBUS {
    private KhachHangDAO khachhangDao;
    
    public CustomerBUS(){
        khachhangDao = new KhachHangDAO();
    }
    
    public ArrayList<KHACHHANG> disPlayListCustomer(){
        return (ArrayList<KHACHHANG>) khachhangDao.getAllKhachHang();
    }
    public boolean addCustomer(KHACHHANG kh){
        return khachhangDao.insertKhachHang(kh);
    }
    public boolean updateCustomer(KHACHHANG kh){
        return khachhangDao.updateKhachHang(kh);
    }
    public boolean deleteCustomer(String  makh){
        return khachhangDao.deleteKhachHang(makh);
    }
    public KHACHHANG findKhachHangById(String makh){
        return khachhangDao.findKhachHangById(makh);
    }
    public ArrayList<KHACHHANG> searchKhachhangByName(String keyword){
        return new ArrayList<> (khachhangDao.searchKhachHangByName(keyword));
    }
}
