package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import DTO.StaffDTO;
import connect.JDBCconn;

public class StaffDAO {

    public List<StaffDTO> getAllNhanVien() {
        List<StaffDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinh = "";
                java.sql.Date sqlDate = rs.getDate("NGAYSINH");
                if (sqlDate != null) {
                    ngaySinh = sdf.format(sqlDate);
                }

                StaffDTO nv = new StaffDTO(
                        rs.getString("MANV"),
                        rs.getString("HOTENNV"),
                        rs.getString("GIOITINH"),
                        rs.getString("SDT"),
                        rs.getString("DIACHI"),
                        ngaySinh
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên
    public boolean insertNhanVien(StaffDTO nv) {
        String sql = "INSERT INTO NHANVIEN (MANV, HOTENNV, GIOITINH, SDT, DIACHI, NGAYSINH) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getMANV());
            ps.setString(2, nv.getHOTENNV());
            ps.setString(3, nv.getGIOITINH());
            ps.setString(4, nv.getSDT());
            ps.setString(5, nv.getDIACHI());
            ps.setString(6, nv.getNGAYSINH());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nhân viên
    public boolean updateNhanVien(StaffDTO nv) {
        String sql = "UPDATE NHANVIEN SET HOTENNV = ?, GIOITINH = ?, SDT = ?, DIACHI = ?, NGAYSINH = ? WHERE MANV = ?";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getHOTENNV());
            ps.setString(2, nv.getGIOITINH());
            ps.setString(3, nv.getSDT());
            ps.setString(4, nv.getDIACHI());
            ps.setString(5, nv.getNGAYSINH());
            ps.setString(6, nv.getMANV());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xoá nhân viên theo mã
    public boolean deleteNhanVien(String manv) {
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, manv);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm nhân viên theo mã
    public StaffDTO findNhanVienById(String manv) {

        String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manv);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String ngaySinh = "";
                java.sql.Date sqlDate = rs.getDate("NGAYSINH");
                if (sqlDate != null) {
                    ngaySinh = sdf.format(sqlDate);
                }
                return new StaffDTO(
                        rs.getString("MANV"),
                        rs.getString("HOTENNV"),
                        rs.getString("GIOITINH"),
                        rs.getString("SDT"),
                        rs.getString("DIACHI"),
                        ngaySinh
                );
            } else {
                System.out.println("Không tìm thấy nhân viên có mã: " + manv); // <-- Thêm dòng này
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tìm nhân viên theo tên gần đúng (LIKE %keyword%)
    public List<StaffDTO> searchNhanVienByName(String keyword) {
        List<StaffDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN WHERE HOTENNV LIKE ?";

        try (Connection conn = JDBCconn.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StaffDTO nv = new StaffDTO(
                        rs.getString("MANV"),
                        rs.getString("HOTENNV"),
                        rs.getString("GIOITINH"),
                        rs.getString("SDT"),
                        rs.getString("DIACHI"),
                        rs.getString("NGAYSINH")
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
