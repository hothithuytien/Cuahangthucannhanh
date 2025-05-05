/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BAO TRAN
 */
public class UsersDTO {
    private String TENTK;
    private String MATKHAU;
    private String MANV;
    private String PHANQUYEN;

    public UsersDTO() {
    }

    public UsersDTO(String TENTK, String MATKHAU, String MANV, String PHANQUYEN) {
        this.TENTK = TENTK;
        this.MATKHAU = MATKHAU;
        this.MANV = MANV;
        this.PHANQUYEN = PHANQUYEN;
    }

    public String getTENTK() {
        return TENTK;
    }

    public void setTENTK(String TENTK) {
        this.TENTK = TENTK;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getPHANQUYEN() {
        return PHANQUYEN;
    }

    public void setPHANQUYEN(String PHANQUYEN) {
        this.PHANQUYEN = PHANQUYEN;
    }
}
