
package BUS;

import DAO.NhanVienDAO;
import java.util.ArrayList;
import model.NHANVIEN;


public class StaffBUS {
    private NhanVienDAO nhanvienDao;
    
    public StaffBUS(){
        nhanvienDao = new NhanVienDAO();   
    }
    public ArrayList<NHANVIEN> disPlayListStaff(){
        return (ArrayList<NHANVIEN>) nhanvienDao.getAllNhanVien();
    } 
    public boolean addStaff (NHANVIEN nv){
        return nhanvienDao.insertNhanVien(nv) ;
        
    }
     public boolean updateNhanVien(NHANVIEN nv) {
        return nhanvienDao.updateNhanVien(nv);
    }

    public boolean deleteNhanVien(String manv) {
        return nhanvienDao.deleteNhanVien(manv);
    }

    public NHANVIEN findNhanVienById(String manv) {
        return nhanvienDao.findNhanVienById(manv);
    }

    public ArrayList<NHANVIEN> searchNhanVienByName(String keyword) {
        return new ArrayList<>(nhanvienDao.searchNhanVienByName(keyword));
    }
}
