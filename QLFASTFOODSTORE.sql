CREATE DATABASE QLFASTFOODSTORE; 

USE QLFASTFOODSTORE;

-- Bảng NHANVIEN
CREATE TABLE NHANVIEN (
    MANV NVARCHAR(10) PRIMARY KEY,
    HOTENNV NVARCHAR(100),
    GIOITINH NVARCHAR(5),
    SDT VARCHAR(10),
    DIACHI NVARCHAR(100),
	NGAYSINH date
);	

-- Bảng KHACHHANG
CREATE TABLE KHACHHANG (
    MAKH NVARCHAR(10) PRIMARY KEY,
    HOTENKH NVARCHAR(100),
    GIOITINH NVARCHAR(5),
    SDT VARCHAR(10),
    DIACHI NVARCHAR(100)
);


-- Bảng LOAISANPHAM (Tạo trước để tránh lỗi FK)
CREATE TABLE LOAISANPHAM (
    MALOAI NVARCHAR(50) PRIMARY KEY,
    TENLOAI NVARCHAR(50)
);

-- Bảng SANPHAM
CREATE TABLE SANPHAM (
    MASP NVARCHAR(10) PRIMARY KEY,
    TENSP NVARCHAR(100),
    GIANHAP REAL,
    GIABAN REAL,
    MALOAI NVARCHAR(50),
	SOLUONG int,
	HINHANH nvarchar(255),
    TRANGTHAI NVARCHAR(20),
    CONSTRAINT FK_SANPHAM_LOAISANPHAM FOREIGN KEY (MALOAI) REFERENCES LOAISANPHAM(MALOAI)
);

-- Bảng HOADON
CREATE TABLE HOADON (
    MAHD NVARCHAR(10) PRIMARY KEY,
    NGAYLAP DATETIME DEFAULT GETDATE(),
    MANV NVARCHAR(10) NOT NULL,
    MAKH NVARCHAR(10) NOT NULL,
    TONGTIEN REAL DEFAULT 0,
    TRANGTHAI NVARCHAR(50),
    CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV),
    CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH)
);


-- Bảng CTHOADON
CREATE TABLE CTHOADON (
    MAHD NVARCHAR(10) NOT NULL,
    MASP NVARCHAR(10) NOT NULL,
    SOLUONG INT NOT NULL CHECK (SOLUONG > 0),
    DONGIA REAL NOT NULL CHECK (DONGIA >= 0),
    THANHTIEN AS (SOLUONG * DONGIA) PERSISTED,
    CONSTRAINT PK_CTHOADON PRIMARY KEY (MAHD, MASP),
    CONSTRAINT FK_CTHOADON_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD),
    CONSTRAINT FK_CTHOADON_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP)
);

drop table CTHOADON
-- Bảng USERS
CREATE TABLE USERS (
    TENTK NVARCHAR(50) PRIMARY KEY,
	TENNGUOIDUNG VARCHAR(50),
    MATKHAU VARCHAR(255) NOT NULL,
    MANV NVARCHAR(10),
    PHANQUYEN NVARCHAR(50)NOT NULL,
    CONSTRAINT FK_TAIKHOAN_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV)
);

-----------------THEM THONG TIN---------------------
insert into NHANVIEN (MANV, HOTENNV, GIOITINH, SDT, DIACHI, NGAYSINH) Values
('NV001', 'Nguyen Duong Bao Tran','Nu','0912345678','297 Phan Huy Ich, Q.Go Vap','2005-10-06'),
('NV002', 'Ho Thi Thuy Tien','Nu','0987654321','45 Vinh Loc A, H.Binh Chanh', '2006-07-13'),
('NV003', 'Le Thi Thuy Tien','Nu','0909123456','89 Trinh Thi Doi, H.Hoc Mon', '1004-08-22');




INSERT INTO KHACHHANG (MAKH, HOTENKH, GIOITINH, SDT, DIACHI) VALUES
('KH001', 'Le Thi Mai', 'Nu', '0987333111', '45 Nguyen Van Cu, Q10'),
('KH002', 'Tran Van Binh', 'Nam', '0909123456', '12 Ly Thuong Kiet, Q5'),
('KH003', 'Nguyen Huu Tuan', 'Nam', '0911222333', '23 Ly Chinh Thang, Q3'),
('KH004', 'Pham Thi Hong', 'Nu', '0966888999', '89 Hoang Van Thu, Q.Phu Nhuan'),
('KH005', 'Vo Thanh Tung', 'Nam', '0933444555', '17 Pham Van Dong, Q.Go Vap');

--------------------SANPHAM------------------------------
INSERT INTO LOAISANPHAM (MALOAI, TENLOAI) VALUES
('MD1', 'Maindish'),
('DR2', 'Drink'),
('DS3', 'Dessert');


INSERT INTO SANPHAM (MASP, TENSP, GIANHAP, GIABAN, MALOAI, SOLUONG, HINHANH,TRANGTHAI) VALUES
-- Pizza
('MD101', N'Pizza Phô Mai', 50000, 80000, 'MD1',10,'/img_ff/doan/pizza_phomai.png', N'Còn hàng'),
('MD102', N'Pizza Hải Sản', 70000, 110000, 'MD1',10,'/img_ff/doan/pizza_haisan.png', N'Còn hàng'),
('MD103', N'Pizza Gà BBQ', 60000, 105000, 'MD1',10,'/img_ff/doan/pizza_gabbq.png', N'Còn hàng'),
('MD104', N'Pizza Jambon', 80000, 120000, 'MD1',10,'/img_ff/doan/pizza_jambon.png', N'Còn hàng'),

-- Hamburger
('MD105', N'Hamburger Bò', 30000, 50000, 'MD1',10,'/img_ff/doan/burger_bo.png', N'Còn hàng'),
('MD106', N'Hamburger Gà', 28000, 48000, 'MD1',10,'/img_ff/doan/burger_ga.png', N'Còn hàng'),
('MD107', N'Hamburger Tôm', 30000, 55000, 'MD1',10,'/img_ff/doan/burger_tom.png', N'Còn hàng'),
('MD108', N'Hamburger Cá', 25000, 44000, 'MD1',10,'/img_ff/doan/burger_ca.png', N'Còn hàng'),

-- Hotdog
('MD109', N'Hotdog Truyền Thống', 20000, 35000, 'MD1',10,'/img_ff/doan/hotdog.png', N'Còn hàng'),

-- Mì Ý
('MD110', N'Mì Ý Sốt Bò Bằm', 40000, 65000, 'MD1',10,'/img_ff/doan/mi_y_bobam.png', N'Còn hàng'),
('MD111', N'Mì Ý Hải Sản', 45000, 70000, 'MD1',10,'/img_ff/doan/mi_y_haisan.png', N'Còn hàng'),
('MD112', N'Mì Ý Sốt Cà Chua', 40000, 59000, 'MD1',10,'/img_ff/doan/mi_y_cachua.png', N'Còn hàng'),

-- Gà Rán
('MD113', N'Gà Rán Chiên Giòn', 20000, 35000, 'MD1',10,'/img_ff/doan/garan_chiengion.png', N'Còn hàng'),
('MD114', N'Gà Sốt Cay', 40000, 65000, 'MD1',10,'/img_ff/doan/garan_sotcay.png', N'Còn hàng'),
('MD115', N'Gà Tắm Nước Mắm', 25000, 49000, 'MD1',10,'/img_ff/doan/ga_nuocmam.png', N'Còn hàng'),

-- Thức uống
('DR201', 'Milo', 8000, 13000, 'DR2',10,'/img_ff/thucuong/MILO.png', N'Còn hàng'),
('DR202', 'Fanta', 8000, 13000, 'DR2',10,'/img_ff/thucuong/fanta.png', N'Còn hàng'),
('DR203', 'Aquafina', 5000, 10000, 'DR2',10,'/img_ff/thucuong/aquafina.png', N'Còn hàng'),
('DR204', 'Coca-Cola', 10000, 15000, 'DR2',10,'/img_ff/thucuong/coca.png', N'Còn hàng'),
('DR205', 'Pepsi', 10000, 15000, 'DR2',10,'/img_ff/thucuong/pepsi.png', N'Còn hàng'),
('DR206', 'Sprite', 10000, 15000, 'DR2',10,'/img_ff/thucuong/sprite.png', N'Còn hàng'),

--Tráng miệng
('DS301', 'Kem Vanilla', 5000, 8000, 'DS3',10,'/img_ff/trangmieng/Vanilla.png', N'Còn hàng'),
('DS302', 'Panna Cotta', 10000, 15000, 'DS3',10,'/img_ff/trangmieng/panacotta.png', N'Còn hàng'),
('DS303', 'Flan Caramel', 10000, 15000, 'DS3',10,'/img_ff/trangmieng/flan_caramel.png', N'Còn hàng'),
('DS304', N'Trái cây tô', 20000, 30000, 'DS3',10,'/img_ff/trangmieng/traicay_to.png', N'Còn hàng'),
('DS305', N'Khoai tây chiên', 18000, 25000, 'DS3',10,'/img_ff/trangmieng/ktay_chien.png', N'Còn hàng'),
('DS306', N'Phô mai que', 8000, 13000, 'DS3',10,'/img_ff/trangmieng/pmai_que.png', N'Còn hàng');

-----------------------HOADON---------------------------------
-- Dữ liệu HOADON: 3 hóa đơn của 3 nhân viên và 3 khách hàng
INSERT INTO HOADON (MAHD, NGAYLAP, MANV, MAKH, TONGTIEN, TRANGTHAI) VALUES
('HD001', '2025-04-01', 'NV001', 'KH001', 150000, N'Đã thanh toán'),
('HD002', '2025-04-02', 'NV002', 'KH002', 293000, N'Chưa thanh toán'),
('HD003', '2025-04-03', 'NV003', 'KH003', 194000, N'Đã thanh toán');

-----------------------CTHOADON-----------------------------
-- Chi tiết hóa đơn HD001
INSERT INTO CTHOADON (MAHD, MASP, SOLUONG, DONGIA) VALUES
('HD001', 'MD101', 1, 80000),
('HD001', 'MD109', 2, 35000),
('HD001', 'DS301', 2, 8000);


-- Chi tiết hóa đơn HD002
INSERT INTO CTHOADON (MAHD, MASP, SOLUONG, DONGIA) VALUES
('HD002', 'MD102', 1, 110000),
('HD002', 'MD106', 2, 48000),
('HD002', 'MD114', 1, 65000),
('HD002','DR205', 1, 15000),
('HD002','DS302', 2, 15000);

-- Chi tiết hóa đơn HD003
INSERT INTO CTHOADON (MAHD, MASP, SOLUONG, DONGIA) VALUES
('HD003','MD113', 2, 35000),
('HD003','MD111', 1, 70000),
('HD003','DR203', 1, 10000);

--delete from CTHOADON;

-----------------------USERS-------------------------------------
INSERT INTO USERS (TENTK, TENNGUOIDUNG , MATKHAU, MANV, PHANQUYEN) VALUES
	('baotran','Bao Tran', '123456', 'NV001', 'Admin'),
	('thuytienlee','Le Tien', '123456', 'NV002', 'Staff'),
	('thuytienHo','Ho Tien', '123456', 'NV003', 'Staff');
	



-- Kiểm tra xem HD004 có tồn tại trong bảng HOADON không
SELECT * FROM HOADON WHERE MAHD = 'HD003';

-- Kiểm tra chi tiết hóa đơn HD004
SELECT c.MAHD, c.MASP, s.TENSP, c.SOLUONG, c.DONGIA, c.THANHTIEN
FROM CTHOADON c
JOIN SANPHAM s ON c.MASP = s.MASP
WHERE c.MAHD = 'HD004';