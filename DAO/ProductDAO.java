package DAO;

import java.sql.*;
import java.util.*;
import DTO.ProductDTO;
import connect.JDBCconn;

public class ProductDAO {

    public List<ProductDTO> getAllSanPham(String maloai) {
        List<ProductDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM SANPHAM where MALOAI = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maloai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO sp = new ProductDTO(
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getDouble("GIANHAP"),
                        rs.getDouble("GIABAN"),
                        rs.getString("MALOAI"),
                        rs.getString("TRANGTHAI")
                );
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertSanPham(ProductDTO sp) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "INSERT INTO SANPHAM (MASP, TENSP, GIANHAP, GIABAN, MALOAI, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMASP());
            ps.setString(2, sp.getTENSP());
            ps.setDouble(3, sp.getGIANHAP());
            ps.setDouble(4, sp.getGIABAN());
            ps.setString(5, sp.getMALOAI());
            ps.setString(6, "Còn hàng");

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSanPham(ProductDTO sp) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "UPDATE SANPHAM SET TENSP=?, GIANHAP=?, GIABAN=?, MALOAI=? WHERE MASP=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTENSP());
            ps.setDouble(2, sp.getGIANHAP());
            ps.setDouble(3, sp.getGIABAN());
            ps.setString(4, sp.getMALOAI());
            ps.setString(5, sp.getMASP());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSanPham(String masp) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "DELETE FROM SANPHAM WHERE MASP=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masp);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProductDTO findSanPhamById(String masp) {
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM SANPHAM WHERE MASP=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductDTO(
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getDouble("GIANHAP"),
                        rs.getDouble("GIABAN"),
                        rs.getString("MALOAI"),
                         rs.getString("TRANGTHAI")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDTO> searchSanPhamByName(String keyword) {
        List<ProductDTO> list = new ArrayList<>();
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT * FROM SANPHAM WHERE TENSP LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO sp = new ProductDTO(
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getDouble("GIANHAP"),
                        rs.getDouble("GIABAN"),
                        rs.getString("MALOAI"),
                        rs.getString("TRANGTHAI")
                );
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int countProducts() {
        int count = 0;
        try (Connection conn = JDBCconn.getConnection()) {
            String sql = "SELECT COUNT(*) FROM SANPHAM";
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
}
