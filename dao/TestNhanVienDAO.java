package DAO;

import java.util.List;
import java.util.Scanner;
import model.NHANVIEN;

public class TestNhanVienDAO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NhanVienDAO nvDAO = new NhanVienDAO();
        int choice;

        do {
            System.out.println("\n=== MENU QUẢN LÝ NHÂN VIÊN ===");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Sửa nhân viên");
            System.out.println("3. Xóa nhân viên");
            System.out.println("4. Tìm nhân viên theo mã");
            System.out.println("5. Tìm nhân viên theo tên");
            System.out.println("6. Hiển thị danh sách nhân viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("--- Thêm nhân viên mới ---");
                    System.out.print("Mã NV: ");
                    String manv = sc.nextLine();
                    System.out.print("Họ tên NV: ");
                    String hotennv = sc.nextLine();
                    System.out.print("Giới tính: ");
                    String gioitinh = sc.nextLine();
                    System.out.print("SDT: ");
                    String sdt = sc.nextLine();
                    System.out.print("Địa chỉ: ");
                    String diachi = sc.nextLine();
                    System.out.print("Ngày sinh: ");
                    String ngaysinh = sc.nextLine();
                    
                    NHANVIEN nv = new NHANVIEN(manv, hotennv, gioitinh, sdt, diachi, ngaysinh);
                    boolean kq = nvDAO.insertNhanVien(nv);
                    System.out.println(kq ? "✅ Thêm thành công!" : "❌ Thêm thất bại!");
                }

                case 2 -> {
                    System.out.println("--- Sửa nhân viên ---");
                    System.out.print("Nhập mã NV cần sửa: ");
                    String manv = sc.nextLine();
                    NHANVIEN nv = nvDAO.findNhanVienById(manv);
                    if (nv != null) {
                        System.out.print("Tên NV mới: ");
                        nv.setHOTENNV(sc.nextLine());
                        System.out.print("Giới tính mới: ");
                        nv.setGIOITINH(sc.nextLine());
                        System.out.print("SDT mới: ");
                        nv.setSDT(sc.nextLine());
                        System.out.print("Địa chỉ mới: ");
                        nv.setDIACHI(sc.nextLine());
                        System.out.print("Ngày sinh mới: ");
                        nv.setNGAYSINH(sc.nextLine());

                        boolean kq = nvDAO.updateNhanVien(nv);
                        System.out.println(kq ? "✅ Sửa thành công!" : "❌ Sửa thất bại!");
                    } else {
                        System.out.println("❗ Không tìm thấy nhân viên có mã: " + manv);
                    }
                }

                case 3 -> {
                    System.out.println("--- Xóa nhân viên ---");
                    System.out.print("Nhập mã NV cần xóa: ");
                    String manv = sc.nextLine();
                    boolean kq = nvDAO.deleteNhanVien(manv);
                    System.out.println(kq ? "✅ Xóa thành công!" : "❌ Xóa thất bại!");
                }

                case 4 -> {
                    System.out.println("--- Tìm nhân viên theo mã ---");
                    System.out.print("Nhập mã NV: ");
                    String manv = sc.nextLine();
                    NHANVIEN nv = nvDAO.findNhanVienById(manv);
                    if (nv != null) {
                        hienThiNV(nv);
                    } else {
                        System.out.println("❗ Không tìm thấy sản phẩm.");
                    }
                }

                case 5 -> {
                    System.out.println("--- Tìm nhân viên theo tên ---");
                    System.out.print("Nhập từ khóa: ");
                    String keyword = sc.nextLine();
                    List<NHANVIEN> ds = nvDAO.searchNhanVienByName(keyword);
                    if (ds.isEmpty()) {
                        System.out.println("❗ Không có nhân viên nào phù hợp.");
                    } else {
                        for (NHANVIEN nv : ds) {
                            hienThiNV(nv);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("--- Danh sách nhân viên ---");
                    List<NHANVIEN> ds = nvDAO.getAllNhanVien();
                    for (NHANVIEN nv : ds) {
                        hienThiNV(nv);
                    }
                }

                case 0 -> System.out.println("✅ Đã thoát chương trình.");

                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    public static void hienThiNV(NHANVIEN nv) {
        System.out.println("Mã NV: " + nv.getMANV() +
                " | Tên NV: " + nv.getHOTENNV() +
                " | Giới tính: " + nv.getGIOITINH() +
                " | SDT: " + nv.getSDT() +
                " | Địa chỉ: " + nv.getDIACHI() +
                " | Ngày sinh: " + nv.getNGAYSINH());
    }
}
