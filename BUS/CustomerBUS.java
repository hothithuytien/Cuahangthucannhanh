package BUS;

import DAO.CustomerDAO;
import java.util.ArrayList;
import DTO.CustomerDTO;


public class CustomerBUS {
    private CustomerDAO khachhangDao;
    
    public CustomerBUS(){
        khachhangDao = new CustomerDAO();
    }
    
    public ArrayList<CustomerDTO> disPlayListCustomer(){
        return (ArrayList<CustomerDTO>) khachhangDao.getAllKhachHang();
    }
    public boolean addCustomer(CustomerDTO kh){
        return khachhangDao.insertKhachHang(kh);
    }
    public boolean updateCustomer(CustomerDTO kh){
        return khachhangDao.updateKhachHang(kh);
    }
    public boolean deleteCustomer(String  makh){
        return khachhangDao.deleteKhachHang(makh);
    }
    public CustomerDTO findKhachHangById(String makh){
        return khachhangDao.findKhachHangById(makh);
    }
    public ArrayList<CustomerDTO> searchKhachhangByName(String keyword){
        return new ArrayList<> (khachhangDao.searchKhachHangByName(keyword));
    }
}
