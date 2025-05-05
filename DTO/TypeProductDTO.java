/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BAO TRAN
 */
public class TypeProductDTO {
    private String MALOAI;
    private String TENLOAI;

    public TypeProductDTO() {
    }

    public TypeProductDTO(String MALOAI, String TENLOAI) {
        this.MALOAI = MALOAI;
        this.TENLOAI = TENLOAI;
    }

    public String getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(String MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }
}
