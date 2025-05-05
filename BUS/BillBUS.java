package BUS;

import dao.BillDAO;
import java.util.ArrayList;
import DTO.BillDTO;
import DAO.BillDetailDAO;


public class BillBUS {
    private BillDAO hoadonDao;
    
    public BillBUS(){
        hoadonDao = new BillDAO();
    }
    
    public ArrayList<BillDTO> displayListBillDetail(){
        return (ArrayList<BillDTO>) hoadonDao.getAllHoaDon();
    }
    
   public boolean addBill(BillDTO bill){
       return hoadonDao.insertHoaDon(bill);
   }
   
   public String generateNewBillId(){
       return hoadonDao.generateNewBillId();
   }
   
   public boolean deleteBill(String mahd){
       return hoadonDao.deleteHoaDon(mahd);
   }
    
}
