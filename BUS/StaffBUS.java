package BUS;

import DAO.StaffDAO;
import java.util.ArrayList;
import DTO.StaffDTO;


public class StaffBUS {
    private StaffDAO nhanvienDao;
    
    public StaffBUS(){
        nhanvienDao = new StaffDAO();   
    }
    public ArrayList<StaffDTO> disPlayListStaff(){
        return (ArrayList<StaffDTO>) nhanvienDao.getAllNhanVien();
    } 
    public boolean addStaff (StaffDTO nv){
        return nhanvienDao.insertNhanVien(nv) ;
        
    }
     public boolean updateNhanVien(StaffDTO nv) {
        return nhanvienDao.updateNhanVien(nv);
    }

    public boolean deleteNhanVien(String manv) {
        return nhanvienDao.deleteNhanVien(manv);
    }

    public StaffDTO findNhanVienById(String manv) {
        return nhanvienDao.findNhanVienById(manv);
    }

    public ArrayList<StaffDTO> searchNhanVienByName(String keyword) {
        return new ArrayList<>(nhanvienDao.searchNhanVienByName(keyword));
    }
}
