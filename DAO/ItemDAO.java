package DAO;

import DTO.ItemDTO;
import DTO.ItemDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import connect.JDBCconn;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;



public class ItemDAO{
    private Connection conn;

    public ItemDAO(){
        try{
            conn = JDBCconn.getConnection();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<ItemDTO> getAllItems(){
        ArrayList<ItemDTO> list = new ArrayList<>();
        String sql = "SELECT MASP, TENSP, GIABAN, HINHANH FROM SANPHAM";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){
                String maSP = rs.getString("MASP");
                String tenSP = rs.getString("TENSP");
                double giaBan = rs.getDouble("GIABAN");
                String hinhAnh = rs.getString("HINHANH");
                
                
                Icon img = null;
                if(hinhAnh != null && !hinhAnh.isEmpty()){
                    img = new ImageIcon(getClass().getResource(hinhAnh));
                }
                
                ItemDTO item = new ItemDTO();
                item.setMaSP(maSP);
                item.setTenSP(tenSP);
                item.setGiaban(giaBan);
                item.setImg(img);
                
                list.add(item);
            }
            
            rs.close();
            ps.close();
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}