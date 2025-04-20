package DAO;

import java.sql.*;
import java.util.*;
import model.CTHOADON;
import utils.JDBCconn;

public class CTHoaDonDAO {
    public List<CTHOADON> getAllCTHoaDon() {
        List<CTHOADON> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM CTHOADON";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHOADON ct = new CTHOADON(
                    rs.getString("MAHD"),
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

    public boolean insertCTHoaDon(CTHOADON ct) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "INSERT INTO CTHOADON (MAHD, TENSP, SOLUONG, DONGIA) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ct.getMAHD());
            ps.setString(2, ct.getTENSP());
            ps.setInt(3, ct.getSOLUONG());
            ps.setDouble(4, ct.getDONGIA());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCTHoaDon(CTHOADON ct) {
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

    public boolean deleteCTHoaDon(String mahd, String tensp) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "DELETE FROM CTHOADON WHERE MAHD=? AND TENSP=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            ps.setString(2, tensp);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CTHOADON> findCTHoaDonByMAHD(String mahd) {
        List<CTHOADON> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM CTHOADON WHERE MAHD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHOADON ct = new CTHOADON(
                    rs.getString("MAHD"),
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
}
