package DTO;

import javax.swing.Icon;

public class ItemDTO{

    /**
     * @return the maSP
     */
    public String getMaSP() {
        return maSP;
    }

    /**
     * @param maSP the maSP to set
     */
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    /**
     * @return the tenSP
     */
    public String getTenSP() {
        return tenSP;
    }

    /**
     * @param tenSP the tenSP to set
     */
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    /**
     * @return the giaban
     */
    public double getGiaban() {
        return giaban;
    }

    /**
     * @param giaban the giaban to set
     */
    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    /**
     * @return the img
     */
    public Icon getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Icon img) {
        this.img = img;
    }

    /**
     * @return the soluong
     */
    public int getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    /**
     * @return the loaiSP
     */
    public String getLoaiSP() {
        return loaiSP;
    }

    /**
     * @param loaiSP the loaiSP to set
     */
    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }
    private String maSP;
    private String tenSP;
    private double giaban;
    private Icon img;
    private int soluong;
    private String loaiSP;
    
    public ItemDTO(String maSP, String tenSP, double giaban, Icon img, int soluong, String loaiSP ){
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaban = giaban;
        this.img = img;
        this.soluong = soluong;
        this.loaiSP = loaiSP;
    }
    
    public ItemDTO(){
        
    }
}