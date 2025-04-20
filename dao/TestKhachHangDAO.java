package DAO;

import java.util.List;
import java.util.Scanner;
import model.KHACHHANG;

public class TestKhachHangDAO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KhachHangDAO khDAO = new KhachHangDAO();
        int choice;

        do {
            System.out.println("\n=== MENU QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Sửa khách hàng");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Tìm khách hàng theo mã");
            System.out.println("5. Tìm khách hàng theo tên");
            System.out.println("6. Hiển thị danh sách khách hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("--- Thêm khách hàng mới ---");
                    System.out.print("Mã KH: ");
                    String makh = sc.nextLine();
                    System.out.print("Họ tên KH: ");
                    String hotenkh = sc.nextLine();
                    System.out.print("Giới tính: ");
                    String gioitinh = sc.nextLine();
                    System.out.print("SDT: ");
                    String sdt = sc.nextLine();
                    System.out.print("Địa chỉ: ");
                    String diachi = sc.nextLine();
                    
                    KHACHHANG kh = new KHACHHANG(makh, hotenkh, gioitinh, sdt, diachi);
                    boolean kq = khDAO.insertKhachHang(kh);
                    System.out.println(kq ? "✅ Thêm thành công!" : "❌ Thêm thất bại!");
                }

                case 2 -> {
                    System.out.println("--- Sửa khách hàng ---");
                    System.out.print("Nhập mã KH cần sửa: ");
                    String makh = sc.nextLine();
                    KHACHHANG kh = khDAO.findKhachHangById(makh);
                    if (kh != null) {
                        System.out.print("Tên KH mới: ");
                        kh.setHOTENKH(sc.nextLine());
                        System.out.print("Giới tính mới: ");
                        kh.setGIOITINH(sc.nextLine());
                        System.out.print("SDT mới: ");
                        kh.setSDT(sc.nextLine());
                        System.out.print("Địa chỉ mới: ");
                        kh.setDIACHI(sc.nextLine());

                        boolean kq = khDAO.updateKhachHang(kh);
                        System.out.println(kq ? "✅ Sửa thành công!" : "❌ Sửa thất bại!");
                    } else {
                        System.out.println("❗ Không tìm thấy khách hàng có mã: " + makh);
                    }
                }

                case 3 -> {
                    System.out.println("--- Xóa khách hàng ---");
                    System.out.print("Nhập mã KH cần xóa: ");
                    String makh = sc.nextLine();
                    boolean kq = khDAO.deleteKhachHang(makh);
                    System.out.println(kq ? "✅ Xóa thành công!" : "❌ Xóa thất bại!");
                }

                case 4 -> {
                    System.out.println("--- Tìm khách hàng theo mã ---");
                    System.out.print("Nhập mã KH: ");
                    String makh = sc.nextLine();
                    KHACHHANG kh = khDAO.findKhachHangById(makh);
                    if (kh != null) {
                        hienThiKH(kh);
                    } else {
                        System.out.println("❗ Không tìm thấy khách hàng.");
                    }
                }

                case 5 -> {
                    System.out.println("--- Tìm khách hàng theo tên ---");
                    System.out.print("Nhập từ khóa: ");
                    String keyword = sc.nextLine();
                    List<KHACHHANG> ds = khDAO.searchKhachHangByName(keyword);
                    if (ds.isEmpty()) {
                        System.out.println("❗ Không có khách hàng nào phù hợp.");
                    } else {
                        for (KHACHHANG kh : ds) {
                            hienThiKH(kh);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("--- Danh sách khách hàng ---");
                    List<KHACHHANG> ds = khDAO.getAllKhachHang();
                    for (KHACHHANG kh : ds) {
                        hienThiKH(kh);
                    }
                }
                
                case 0 -> System.out.println("✅ Đã thoát chương trình.");

                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    public static void hienThiKH(KHACHHANG kh) {
        System.out.println("Mã KH: " + kh.getMAKH() +
                " | Tên KH: " + kh.getHOTENKH() +
                " | Giới tính: " + kh.getGIOITINH() +
                " | SDT: " + kh.getSDT() +
                " | Địa chỉ: " + kh.getDIACHI());
    }
}
