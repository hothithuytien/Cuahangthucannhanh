package DTO;

public class ProductDTO {
    private String MASP;
    private String TENSP;
    private double GIANHAP;
    private double GIABAN;
    private String MALOAI;
    private String TRANGTHAI;

    public ProductDTO() {}

    public ProductDTO(String MASP, String TENSP, double GIANHAP, double GIABAN, String MALOAI, String TRANGTHAI) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.GIANHAP = GIANHAP;
        this.GIABAN = GIABAN;
        this.MALOAI = MALOAI;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMASP() { return MASP; }
    public void setMASP(String MASP) { this.MASP = MASP; }

    public String getTENSP() { return TENSP; }
    public void setTENSP(String TENSP) { this.TENSP = TENSP; }

    public double getGIANHAP() { return GIANHAP; }
    public void setGIANHAP(double GIANHAP) { this.GIANHAP = GIANHAP; }

    public double getGIABAN() { return GIABAN; }
    public void setGIABAN(double GIABAN) { this.GIABAN = GIABAN; }

    public String getMALOAI() { return MALOAI; }
    public void setMALOAI(String MALOAI) { this.MALOAI = MALOAI; }
   
    public String getTRANGTHAI() { return TRANGTHAI; }
    public void setTRANGTHAI(String TRANGTHAI) { this.TRANGTHAI = TRANGTHAI;}
}
