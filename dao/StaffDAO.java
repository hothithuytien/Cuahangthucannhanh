/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;
import utils.JDBCconn;




/**
 *
 * @author PC
 */
public class StaffDAO implements DAOinterface <Staff> {
    public static StaffDAO getInstance(){
        return new StaffDAO();
    }
    @Override
    public int insert(Staff t) {
//        Connection con = JDBCconn.getConnection();
//        try {
//            Statement st = con.createStatement();
//            String sql = "INSERT INTO NHANVIEN (MANV, TENNV, GIOITINH,SDT, DIACHI, NGAYSINH)" +
//                    "VALUES("+t.getTENNV()+" , "+t.getTENNV()+" , "+t.getGIOITINH()+" , "+t.getSDT()+" , "+t.getDIACHI()+" , "+t.getNGAYSINH()+")";
//            int KetQua = st.executeUpdate(sql);
//            System.out.println("co" + KetQua +"dong bi thay doi");
////            JDBCconn.closeConnection(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Staff t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Staff t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Staff> selectAll() {
        ArrayList<Staff> ketQua = new ArrayList();
        Connection con = JDBCconn.getConnection();

        try{
            Statement st = con.createStatement();
            String sql = "SELECT * FROM NHANVIEN";
            
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String maNV = rs.getString("MANV");
                String tenNV = rs.getString("HOTENNV");
                String SDT = rs.getString("SDT");
                String diaChi = rs.getString("DIACHI");
                Date ngaySinh = rs.getDate("NGAYSINH");
                Staff nv = new Staff(maNV, tenNV, SDT, diaChi, ngaySinh);
                ketQua.add(nv);
            }
            
        }catch(SQLException ex){
             Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Staff selectByID(Staff t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Staff> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
