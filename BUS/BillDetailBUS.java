package BUS;

import DAO.BillDetailDAO;
import java.util.ArrayList;
import DTO.BillDetailDTO;
import dao.BillDAO;
import java.util.List;


public class BillDetailBUS {
    private BillDetailDAO cthoadonDao ;
    
    public BillDetailBUS (){
        cthoadonDao = new BillDetailDAO();
    }
    public ArrayList<BillDetailDTO> displayListBillDetail(){
        return (ArrayList<BillDetailDTO>) cthoadonDao.getAllCTHoaDon();
    }
    public boolean addBillDetail (BillDetailDTO cthoadon){
        return cthoadonDao.insertCTHoaDon(cthoadon);
    }
    public boolean updateBillDetail (BillDetailDTO cthoadon){
        return cthoadonDao.updateCTHoaDon(cthoadon);
    }
    public boolean deleteBillDetail (String mahd){
        return cthoadonDao.deleteCTHoaDon(mahd);
        
    }
    public ArrayList<BillDetailDTO> findBillDetailByMAHD(String mahd){
        return new ArrayList <> (cthoadonDao.findCTHoaDonByMAHD(mahd));
    }
    
    public boolean insertFromBill(String sourceMAHD, String targetMAHD){
        try{
            List<BillDetailDTO> sourceDetails = findBillDetailByMAHD(sourceMAHD);
            
            for(BillDetailDTO detail : sourceDetails){
                BillDetailDTO newDetail = new BillDetailDTO(
                    targetMAHD,
                    detail.getMASP(),
                    detail.getTENSP(),
                    detail.getSOLUONG(),
                    detail.getDONGIA()
                );
                if(!addBillDetail(newDetail)){
                    return false;
                }
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
  
}
