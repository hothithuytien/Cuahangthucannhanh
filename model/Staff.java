
package model;
import java.sql.Date;

public class Staff {
    private String MANV ;
    private String TENNV;
//    private String GIOITINH;
    private String SDT;
    private String DIACHI;
    private Date NGAYSINH;
    
    public Staff(){
        
    }
    
    
    public Staff(String MANV ,String TENNV, String SDT, String DIACHI, Date NGAYSINH){
        
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.SDT = SDT;
        this.DIACHI=DIACHI;
        this.NGAYSINH = NGAYSINH;    
    }
    public String getMANV (){
        return MANV;
    }
    public void setMANV (String MANV){
        this.MANV = MANV;
    }
    public String getTENNV (){
        return TENNV;
    }
    public void setTENNV (String TENNV){
        this.TENNV = TENNV;
    }
    
    public String getSDT (){
        return SDT;
    }
    public void setSDT (String SDT){
        this.SDT = SDT;
    }
    public String getDIACHI (){
        return DIACHI;
    }
    public void setDIACHI (String DIACHI){
        this.DIACHI = DIACHI;
    }
    public Date getNGAYSINH (){
        return NGAYSINH;
    }
    public void setNGAYSINH (Date NGAYSINH){
        this.NGAYSINH = NGAYSINH;
    }
    public String toString(){
        return "NHANVIEN [MANV=" +MANV+",TENNV="+ TENNV +",SDT="+SDT+", DIACHI=" +DIACHI+",NGAYSINH=" +NGAYSINH+"]"; 
    }
}
