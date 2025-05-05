/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
/**
 *
 * @author BAO TRAN
 */
public class BillDTO {
    private String MAHD;
    private String NGAYLAP;
    private String MANV;
    private String MAKH;
    private double TONGTIEN;
    private String TRANGTHAI;

    public BillDTO() {
    }

    public BillDTO(String MAHD, String NGAYLAP, String MANV, String MAKH, double TONGTIEN, String TRANGTHAI) {
        this.MAHD = MAHD;
        this.NGAYLAP = NGAYLAP;
        this.MANV = MANV;
        this.MAKH = MAKH;
        this.TONGTIEN = TONGTIEN;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getNGAYLAP() {
        return NGAYLAP;
    }

    public void setNGAYLAP(String NGAYLAP) {
        this.NGAYLAP = NGAYLAP;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
}
