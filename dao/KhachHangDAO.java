package DAO;

import java.sql.*;
import java.util.*;
import model.*;
import utils.JDBCconn;

public class KhachHangDAO {
    public List<KHACHHANG> getAllKhachHang() {
        List<KHACHHANG> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KHACHHANG");
            while (rs.next()) {
                KHACHHANG kh = new KHACHHANG(
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

    public boolean insertKhachHang(KHACHHANG kh) {
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

    public boolean updateKhachHang(KHACHHANG kh) {
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

    public KHACHHANG findKhachHangById(String makh) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new KHACHHANG(
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

    public List<KHACHHANG> searchKhachHangByName(String keyword) {
        List<KHACHHANG> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM KHACHHANG WHERE HOTENKH LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KHACHHANG kh = new KHACHHANG(
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
}