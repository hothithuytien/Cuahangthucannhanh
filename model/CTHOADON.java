package model;

public class CTHOADON {
    private String MAHD;
    private String TENSP;
    private int SOLUONG;
    private double DONGIA;

    public CTHOADON() {}

    public CTHOADON(String MAHD, String TENSP, int SOLUONG, double DONGIA) {
        this.MAHD = MAHD;
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
        return SOLUONG * DONGIA;
    }
}
