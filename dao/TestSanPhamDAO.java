package DAO;

import java.util.List;
import java.util.Scanner;
import model.SANPHAM;

public class TestSanPhamDAO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SanPhamDAO spDAO = new SanPhamDAO();
        int choice;

        do {
            System.out.println("\n=== MENU QUẢN LÝ SẢN PHẨM ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Tìm sản phẩm theo mã");
            System.out.println("5. Tìm sản phẩm theo tên");
            System.out.println("6. Hiển thị danh sách sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("--- Thêm sản phẩm mới ---");
                    System.out.print("Mã SP: ");
                    String masp = sc.nextLine();
                    System.out.print("Tên SP: ");
                    String tensp = sc.nextLine();
                    System.out.print("Giá nhập: ");
                    double gianhap = Double.parseDouble(sc.nextLine());
                    System.out.print("Giá bán: ");
                    double giaban = Double.parseDouble(sc.nextLine());
                    System.out.print("Mã loại: ");
                    String maloai = sc.nextLine();
//                    System.out.print("Trạng thái: ");
//                    String trangthai = sc.nextLine();

                    SANPHAM sp = new SANPHAM(masp, tensp, gianhap, giaban, maloai);
                    boolean kq = spDAO.insertSanPham(sp);
                    System.out.println(kq ? "✅ Thêm thành công!" : "❌ Thêm thất bại!");
                }

                case 2 -> {
                    System.out.println("--- Sửa sản phẩm ---");
                    System.out.print("Nhập mã SP cần sửa: ");
                    String masp = sc.nextLine();
                    SANPHAM sp = spDAO.findSanPhamById(masp);
                    if (sp != null) {
                        System.out.print("Tên SP mới: ");
                        sp.setTENSP(sc.nextLine());
                        System.out.print("Giá nhập mới: ");
                        sp.setGIANHAP(Double.parseDouble(sc.nextLine()));
                        System.out.print("Giá bán mới: ");
                        sp.setGIABAN(Double.parseDouble(sc.nextLine()));
                        System.out.print("Mã loại mới: ");
                        sp.setMALOAI(sc.nextLine());
//                        System.out.print("Trạng thái mới: ");
//                        sp.setTRANGTHAI(sc.nextLine());

                        boolean kq = spDAO.updateSanPham(sp);
                        System.out.println(kq ? "✅ Sửa thành công!" : "❌ Sửa thất bại!");
                    } else {
                        System.out.println("❗ Không tìm thấy sản phẩm có mã: " + masp);
                    }
                }

                case 3 -> {
                    System.out.println("--- Xóa sản phẩm ---");
                    System.out.print("Nhập mã SP cần xóa: ");
                    String masp = sc.nextLine();
                    boolean kq = spDAO.deleteSanPham(masp);
                    System.out.println(kq ? "✅ Xóa thành công!" : "❌ Xóa thất bại!");
                }

                case 4 -> {
                    System.out.println("--- Tìm sản phẩm theo mã ---");
                    System.out.print("Nhập mã SP: ");
                    String masp = sc.nextLine();
                    SANPHAM sp = spDAO.findSanPhamById(masp);
                    if (sp != null) {
                        hienThiSP(sp);
                    } else {
                        System.out.println("❗ Không tìm thấy sản phẩm.");
                    }
                }

                case 5 -> {
                    System.out.println("--- Tìm sản phẩm theo tên ---");
                    System.out.print("Nhập từ khóa: ");
                    String keyword = sc.nextLine();
                    List<SANPHAM> ds = spDAO.searchSanPhamByName(keyword);
                    if (ds.isEmpty()) {
                        System.out.println("❗ Không có sản phẩm nào phù hợp.");
                    } else {
                        for (SANPHAM sp : ds) {
                            hienThiSP(sp);
                        }
                    }
                }

//                case 6 -> {
//                    System.out.println("--- Danh sách sản phẩm ---");
//                    List<SANPHAM> ds = spDAO.getAllSanPham();
//                    for (SANPHAM sp : ds) {
//                        hienThiSP(sp);
//                    }
//                }

                case 0 -> System.out.println("✅ Đã thoát chương trình.");

                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }

    public static void hienThiSP(SANPHAM sp) {
        System.out.println("Mã SP: " + sp.getMASP() +
                " | Tên SP: " + sp.getTENSP() +
                " | Giá nhập: " + sp.getGIANHAP() +
                " | Giá bán: " + sp.getGIABAN() +
                " | Mã loại: " + sp.getMALOAI());
    }
}
