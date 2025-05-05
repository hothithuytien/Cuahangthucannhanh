/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;


/**
 *
 * @author BAO TRAN
 */
public class StaffDTO {
    private String MANV;
    private String HOTENNV;
    private String GIOITINH;
    private String SDT;
    private String DIACHI;
    private String NGAYSINH;
    
    public StaffDTO(){
    }
    
    public StaffDTO(String MANV, String HOTENNV, String GIOITINH, String SDT, String DIACHI, String NGAYSINH){
        this.MANV = MANV;
        this.HOTENNV = HOTENNV;
        this.GIOITINH = GIOITINH;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.NGAYSINH = NGAYSINH;
    }
    
    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getHOTENNV() {
        return HOTENNV;
    }

    public void setHOTENNV(String HOTENNV) {
        this.HOTENNV = HOTENNV;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }   
}
