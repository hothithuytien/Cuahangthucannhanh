
package BUS;

import DAO.CTHoaDonDAO;
import java.util.ArrayList;
import model.CTHOADON;


public class BillDetailBUS {
    private CTHoaDonDAO cthoadonDao ;
    
    public BillDetailBUS (){
        cthoadonDao = new CTHoaDonDAO();
    }
    public ArrayList<CTHOADON> displayListBillDetail(){
        return (ArrayList<CTHOADON>) cthoadonDao.getAllCTHoaDon();
    }
    public boolean addBillDetail (CTHOADON cthoadon){
        return cthoadonDao.insertCTHoaDon(cthoadon);
    }
    public boolean updateBillDetail (CTHOADON cthoadon){
        return cthoadonDao.updateCTHoaDon(cthoadon);
    }
    public boolean deleteBillDetail (String mahd){
        return cthoadonDao.deleteCTHoaDon(mahd);
    }
    public ArrayList<CTHOADON> findBillDetailByMAHD(String mahd){
        return new ArrayList <> (cthoadonDao.findCTHoaDonByMAHD(mahd));
    }
}
