package dao;

import java.sql.*;
import java.util.*;
import connect.JDBCconn;


public class StatisticsDAO {

    // Thống kê doanh thu theo Ngày nhập vào
    public List<Object[]> getRevenueBySpecificDate(String date) {
        List<Object[]> data = new ArrayList<>();
        String sql = "SELECT NGAYLAP, SUM(TONGTIEN) as DoanhThu FROM HOADON WHERE NGAYLAP = ? GROUP BY NGAYLAP";

        try (Connection conn = JDBCconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getDate("NGAYLAP"),
                    rs.getDouble("DoanhThu")
                };
                data.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    //Lấy Top 5 sản phẩm theo Ngày
    public List<Object[]> getTop5ProductsByDay(String ngayLap) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection conn = JDBCconn.getConnection();

        String sql = "SELECT sp.TenSP, SUM(cthd.SoLuong) AS TongSoLuong " +
                     "FROM CTHOADON cthd " +
                     "JOIN SANPHAM sp ON cthd.MaSP = sp.MaSP " +
                     "JOIN HOADON hd ON cthd.MaHD = hd.MaHD " +
                     "WHERE CAST(hd.NgayLap AS DATE) = CAST(? AS DATE) " +
                     "GROUP BY sp.TenSP " +
                     "ORDER BY TongSoLuong DESC " +
                     "OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(ngayLap));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{ rs.getString("TenSP"), rs.getInt("TongSoLuong") });
        }
        rs.close();
        ps.close();
        return list;
    }
    
    // Lấy Top 5 sản phẩm theo Tháng
    public List<Object[]> getTop5ProductsByMonth(String thangLap) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection conn = JDBCconn.getConnection();
        String sql = "SELECT sp.TenSP, SUM(cthd.SoLuong) as TongSoLuong " +
                 "FROM CTHOADON cthd " +
                 "JOIN SANPHAM sp ON cthd.MaSP = sp.MaSP " +
                 "JOIN HOADON hd ON cthd.MaHD = hd.MaHD " +
                 "WHERE FORMAT(hd.NgayLap, 'yyyy-MM') = ? " +
                 "GROUP BY sp.TenSP " +
                 "ORDER BY TongSoLuong DESC " +
                 "OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, thangLap);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{ rs.getString(1), rs.getInt(2) });
        }
        rs.close();
        ps.close();
        return list;
    }

    //Doanh thu theo khoảng thời gian
    public List<Object[]> getRevenueByDateRange(String fromDate, String toDate) {
        List<Object[]> data = new ArrayList<>();
        String sql = "SELECT MIN(NGAYLAP) AS NgayBatDau, MAX(NGAYLAP) AS NgayKetThuc, SUM(TONGTIEN) AS TongDoanhThu " +
                     "FROM HOADON WHERE NGAYLAP BETWEEN ? AND ?";

        try (Connection conn = JDBCconn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Object[] row = {
                    fromDate,
                    toDate,
                    rs.getDouble("TongDoanhThu") != 0 ? rs.getDouble("TongDoanhThu") : 0
                };
                data.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
