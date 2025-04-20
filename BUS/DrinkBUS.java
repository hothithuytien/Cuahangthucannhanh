
package BUS;

import DAO.SanPhamDAO;
import java.util.ArrayList;
import model.SANPHAM;


public class DrinkBUS {
    private SanPhamDAO sanphamDao;
    
    public DrinkBUS (){
        sanphamDao = new SanPhamDAO();
    }
    
    public ArrayList<SANPHAM> disPlayListProduct(String maloai){
        return (ArrayList<SANPHAM>) sanphamDao.getAllSanPham(maloai);
    }
    public boolean addSProduct(SANPHAM sp){
        return sanphamDao.insertSanPham(sp);
    }
    public boolean updateProduct (SANPHAM sp){
        return sanphamDao.updateSanPham(sp);
    }
    public boolean deleteProduct (String  masp){
        return sanphamDao.deleteSanPham(masp);
    }
    public SANPHAM findSanPhamById(String masp){
        return sanphamDao.findSanPhamById(masp);
    }
    public ArrayList<SANPHAM> searchSanPhamByName(String keyword){
        return new ArrayList<> (sanphamDao.searchSanPhamByName(keyword));
    }
    
    
    
    
}
