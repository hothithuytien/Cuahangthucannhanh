package BUS;

import DAO.ProductDAO;
import java.util.ArrayList;
import DTO.ProductDTO;


public class DrinkBUS {
    private ProductDAO sanphamDao;
    
    public DrinkBUS (){
        sanphamDao = new ProductDAO();
    }
    
    public ArrayList<ProductDTO> disPlayListProduct(String maloai){
        return (ArrayList<ProductDTO>) sanphamDao.getAllSanPham(maloai);
    }
    public boolean addSProduct(ProductDTO sp){
        return sanphamDao.insertSanPham(sp);
    }
    public boolean updateProduct (ProductDTO sp){
        return sanphamDao.updateSanPham(sp);
    }
    public boolean deleteProduct (String  masp){
        return sanphamDao.deleteSanPham(masp);
    }
    public ProductDTO findSanPhamById(String masp){
        return sanphamDao.findSanPhamById(masp);
    }
    public ArrayList<ProductDTO> searchSanPhamByName(String keyword){
        return new ArrayList<> (sanphamDao.searchSanPhamByName(keyword));
    }
}
