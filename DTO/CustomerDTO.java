/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BAO TRAN
 */
public class CustomerDTO {
    private String MAKH;
    private String HOTENKH;
    private String GIOITINH;
    private String SDT;
    private String DIACHI;

    public CustomerDTO() {
    }

    public CustomerDTO(String MAKH, String HOTENKH, String GIOITINH, String SDT, String DIACHI) {
        this.MAKH = MAKH;
        this.HOTENKH = HOTENKH;
        this.GIOITINH = GIOITINH;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getHOTENKH() {
        return HOTENKH;
    }

    public void setHOTENKH(String HOTENKH) {
        this.HOTENKH = HOTENKH;
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
}
