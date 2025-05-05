package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DTO.BillDTO;
import java.sql.PreparedStatement;

import connect.JDBCconn;
import java.sql.SQLException;


public class BillDAO {
    public List<BillDTO> getAllHoaDon() {
        List<BillDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM HOADON");
            while (rs.next()) {
                BillDTO hoadon = new BillDTO(
                    rs.getString("MAHD"),
                    rs.getString("NGAYLAP"),
                    rs.getString("MANV"),
                    rs.getString("MAKH"),
                    rs.getDouble("TONGTIEN"),
                    rs.getString("TRANGTHAI")
                );
                list.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int countBills() {
    int count = 0;
    try (Connection conn = JDBCconn.getConnection()) {
        String sql = "SELECT COUNT(*) FROM HOADON";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
    }
    
    //-------------------------------------------------------------------
    public boolean insertHoaDon(BillDTO hoadon){
        try(Connection conn = JDBCconn.getConnection()){
            String sql = "INSERT INTO HOADON (MAHD, NGAYLAP, MANV, MAKH, TONGTIEN, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";
       
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoadon.getMAHD());
            ps.setString(2, hoadon.getNGAYLAP());
            ps.setString(3, hoadon.getMANV());
            ps.setString(4, hoadon.getMAKH());
            ps.setDouble(5, hoadon.getTONGTIEN());
            ps.setString(6, hoadon.getTRANGTHAI());
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public String generateNewBillId(){
        int count = countBills() + 1;
        return String.format("HD%03d", count);
    }
    
    public boolean deleteHoaDon(String mahd){
        try(Connection conn = JDBCconn.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                String sqlDeleteDetails = "DELETE FROM CTHOADON WHERE MAHD=?";
                try(PreparedStatement psDetails = conn.prepareStatement(sqlDeleteDetails)){
                    psDetails.setString(1, mahd);
                    psDetails.executeUpdate();
                }
                
                String sqlDeleteBill = "DELETE FROM HOADON WHERE MAHD=?";
                try(PreparedStatement psBill = conn.prepareStatement(sqlDeleteBill)){
                    psBill.setString(1, mahd);
                    int result = psBill.executeUpdate();
                    
                    conn.commit();
                    return result > 0;
                }
            }catch(SQLException e){
                conn.rollback();
                e.printStackTrace();
                return false;
            }finally{
                conn.setAutoCommit(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public BillDTO getBillById(String mahd){
        BillDTO bill = null;
        try(Connection conn = JDBCconn.getConnection()){
            String sql = "SELECT * FROM HOADON WHERE MAHD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bill = new BillDTO(
                    rs.getString("MAHD"),
                    rs.getString("NGAYLAP"),
                    rs.getString("MANV"),
                    rs.getString("MAKH"),
                    rs.getDouble("TONGTIEN"),
                    rs.getString("TRANGTHAI")
                );
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return bill;
    }
}
