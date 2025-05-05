package DAO;

import java.sql.*;
import java.util.*;
import DTO.BillDetailDTO;
import connect.JDBCconn;

public class BillDetailDAO {
    public List<BillDetailDTO> getAllCTHoaDon() {
        List<BillDetailDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM CTHOADON";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillDetailDTO ct = new BillDetailDTO(
                    rs.getString("MAHD"),
                    rs.getString("MASP"),
                    rs.getString("TENSP"),
                    rs.getInt("SOLUONG"),
                    rs.getDouble("DONGIA")
                );
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertCTHoaDon(BillDetailDTO ct) {
        try (Connection conn = JDBCconn.getConnection()) {

            String sql = "INSERT INTO CTHOADON (MAHD, MASP, SOLUONG, DONGIA) VALUES (?, ?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, ct.getMAHD());
                ps.setString(2, ct.getMASP());
                ps.setInt(3, ct.getSOLUONG());
                ps.setDouble(4, ct.getDONGIA());
                
                int result = ps.executeUpdate();
                System.out.println("Số dòng được thêm vào CTHOADON " + result);
                return result > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Loi SQL khi them CTHOADON");
            e.printStackTrace();
            return false;
        }
    }
    
    

    public boolean updateCTHoaDon(BillDetailDTO ct) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "UPDATE CTHOADON SET SOLUONG=?, DONGIA=? WHERE MAHD=? AND TENSP=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ct.getSOLUONG());
            ps.setDouble(2, ct.getDONGIA());
            ps.setString(3, ct.getMAHD());
            ps.setString(4, ct.getTENSP());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCTHoaDon(String mahd) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql1 = "DELETE FROM CTHOADON WHERE MAHD=?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, mahd);
            ps1.executeUpdate();
            
            String sql2 = "DELETE FROM HOADON WHERE MAHD = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, mahd);
            int result = ps2.executeUpdate();
           
            return result > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //--------------------------------------------------------
    public boolean deleteHoaDon(String mahd){
        try(Connection conn = JDBCconn.getConnection()){
            String sql1 = "DELETE FROM CTHOADON WHERE MAHD=?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, mahd);
            ps1.executeUpdate();
            
            String sql2 = "DELETE FROM HOADON WHERE MAHD=?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, mahd);
            return ps2.executeUpdate() > 0;
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    //--------------------------------------------------------
    public List<BillDetailDTO> findCTHoaDonByMAHD(String mahd) {
        List<BillDetailDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT c.MAHD, c.MASP, s.TENSP, c.SOLUONG, c.DONGIA " + 
                         "FROM CTHOADON c " +
                         "JOIN SANPHAM s ON c.MASP = s.MASP " +
                         "WHERE c.MAHD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    BillDetailDTO ct = new BillDetailDTO(
                        rs.getString("MAHD"),
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getInt("SOLUONG"),
                        rs.getDouble("DONGIA")
                    );
                    list.add(ct);
                }
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
