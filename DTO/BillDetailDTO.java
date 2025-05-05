package DTO;

public class BillDetailDTO {
    private String MAHD;
    private String MASP;
    private String TENSP;
    private int SOLUONG;
    private double DONGIA;

    public BillDetailDTO() {}

    public BillDetailDTO(String MAHD, String MASP, String TENSP, int SOLUONG, double DONGIA) {
        this.MAHD = MAHD;
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
    }

    public String getMAHD() { return MAHD; }
    public void setMAHD(String MAHD) { this.MAHD = MAHD; }

    public String getTENSP() { return TENSP; }
    public void setTENSP(String TENSP) { this.TENSP = TENSP; }

    public int getSOLUONG() { return SOLUONG; }
    public void setSOLUONG(int SOLUONG) { this.SOLUONG = SOLUONG; }

    public double getDONGIA() { return DONGIA; }
    public void setDONGIA(double DONGIA) { this.DONGIA = DONGIA; }

    public double getTHANHTIEN() {
        return getSOLUONG() * getDONGIA();
    }

    /**
     * @return the MASP
     */
    public String getMASP() {
        return MASP;
    }

    /**
     * @param MASP the MASP to set
     */
    public void setMASP(String MASP) {
        this.MASP = MASP;
    }
}
