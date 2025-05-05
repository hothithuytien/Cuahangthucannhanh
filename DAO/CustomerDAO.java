package DAO;

import DTO.CustomerDTO;
import java.sql.*;
import java.util.*;
import connect.JDBCconn;

public class CustomerDAO {
    public List<CustomerDTO> getAllKhachHang() {
        List<CustomerDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KHACHHANG");
            while (rs.next()) {
                CustomerDTO kh = new CustomerDTO(
                    rs.getString("MAKH"),
                    rs.getString("HOTENKH"),
                    rs.getString("GIOITINH"),
                    rs.getString("SDT"),
                    rs.getString("DIACHI")
                );
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertKhachHang(CustomerDTO kh) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "INSERT INTO KHACHHANG VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMAKH());
            ps.setString(2, kh.getHOTENKH());
            ps.setString(3, kh.getGIOITINH());
            ps.setString(4, kh.getSDT());
            ps.setString(5, kh.getDIACHI());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(CustomerDTO kh) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "UPDATE KHACHHANG SET HOTENKH=?, GIOITINH=?, SDT=?, DIACHI=? WHERE MAKH=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getHOTENKH());
            ps.setString(2, kh.getGIOITINH());
            ps.setString(3, kh.getSDT());
            ps.setString(4, kh.getDIACHI());
            ps.setString(5, kh.getMAKH());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKhachHang(String makh) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "DELETE FROM KHACHHANG WHERE MAKH=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CustomerDTO findKhachHangById(String makh) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CustomerDTO(
                    rs.getString("MAKH"),
                    rs.getString("HOTENKH"),
                    rs.getString("GIOITINH"),
                    rs.getString("SDT"),
                    rs.getString("DIACHI")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CustomerDTO> searchKhachHangByName(String keyword) {
        List<CustomerDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM KHACHHANG WHERE HOTENKH LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerDTO kh = new CustomerDTO(
                    rs.getString("MAKH"),
                    rs.getString("HOTENKH"),
                    rs.getString("GIOITINH"),
                    rs.getString("SDT"),
                    rs.getString("DIACHI")
                );
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int countCustomers() {
        int count = 0;
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT COUNT(*) FROM KHACHHANG";
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
    // CustomerDAO.java
public String getCustomerIdByPhone(String phone) {
    String query = "SELECT MAKH FROM KHACHHANG WHERE SDT = ?";
    try (Connection conn = JDBCconn.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("MAKH"); // Trả về MAKH nếu tìm thấy
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Không tìm thấy
}
// CustomerDAO.java
public String generateNewCustomerId() {
    String query = "SELECT MAX(MAKH) AS MAX_ID FROM KHACHHANG";
    try (Connection conn = JDBCconn.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(query)) {
        if (rs.next()) {
            String maxId = rs.getString("MAX_ID");
            if (maxId != null) {
                int num = Integer.parseInt(maxId.substring(2)) + 1; // Bỏ "KH" và tăng 1
                return String.format("KH%03d", num); // Định dạng KH001, KH002,...
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "KH001"; // Mặc định nếu không có khách hàng nào
    }

    
}
