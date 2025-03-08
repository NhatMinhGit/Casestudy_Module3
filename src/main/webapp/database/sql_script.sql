CREATE DATABASE OnlinePcShop;
USE OnlinePcShop;

-- Bảng User
CREATE TABLE Users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(64),
  salt VARCHAR(24),
  role VARCHAR(20),
  phone_number VARCHAR(15),
  address TEXT
);

-- Bảng Order
CREATE TABLE `Order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10,2),
    status VARCHAR(50),
    shipping_address TEXT,
    payment_method VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Bảng Category
CREATE TABLE Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Bảng Product
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image_url TEXT,
    description TEXT,
    stock_quantity INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

-- Bảng OrderItem
CREATE TABLE OrderItem (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price_per_unit DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- Bảng Image
-- CREATE TABLE Image (
--     image_id INT AUTO_INCREMENT PRIMARY KEY,
--     product_id INT,
--     image_url VARCHAR(500),
--     image_name VARCHAR(255),
--     FOREIGN KEY (product_id) REFERENCES Product(product_id)
-- );

-- Bảng Voucher
CREATE TABLE Voucher (
    voucher_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    title VARCHAR(255) NOT NULL,
    sale_amount DECIMAL(10,2),
    note TEXT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id)
);

-- Dữ liệu mẫu user
INSERT INTO Users (name, email, password, role, phone_number, address) VALUES
    ('Nguyễn Văn A', 'nguyenvana@example.com', 'password123', 'customer', '0987654321', 'Hà Nội, Việt Nam'),
('Trần Thị B', 'tranthib@example.com', 'password123', 'customer', '0978123456', 'TP. Hồ Chí Minh, Việt Nam'),
('Lê Văn C', 'levanc@example.com', 'password123', 'admin', '0912345678', 'Đà Nẵng, Việt Nam'),
('Phạm Thị D', 'phamthid@example.com', 'password123', 'customer', '0923456789', 'Cần Thơ, Việt Nam'),
('Hoàng Văn E', 'hoangvane@example.com', 'password123', 'customer', '0934567890', 'Hải Phòng, Việt Nam'),
('Đỗ Thị F', 'dothif@example.com', 'password123', 'customer', '0945678901', 'Nha Trang, Việt Nam'),
('Bùi Văn G', 'buivang@example.com', 'password123', 'customer', '0956789012', 'Huế, Việt Nam'),
('Võ Thị H', 'vothih@example.com', 'password123', 'customer', '0967890123', 'Quảng Ninh, Việt Nam'),
('Ngô Văn I', 'ngovanii@example.com', 'password123', 'customer', '0978901234', 'Bắc Ninh, Việt Nam'),
('Dương Thị J', 'duongthij@example.com', 'password123', 'customer', '0989012345', 'Đồng Nai, Việt Nam'),
('Lý Văn K', 'lyvank@example.com', 'password123', 'customer', '0990123456', 'Hà Tĩnh, Việt Nam'),
('Trịnh Thị L', 'trinhthil@example.com', 'password123', 'customer', '0901234567', 'Thanh Hóa, Việt Nam'),
('Hà Văn M', 'havanm@example.com', 'password123', 'customer', '0912345678', 'Vĩnh Phúc, Việt Nam'),
('Tạ Thị N', 'tatinh@example.com', 'password123', 'customer', '0923456789', 'Phú Thọ, Việt Nam'),
('Đinh Văn O', 'dinhvano@example.com', 'password123', 'customer', '0934567890', 'Thái Nguyên, Việt Nam'),
('Lương Thị P', 'luongthip@example.com', 'password123', 'customer', '0945678901', 'Nam Định, Việt Nam'),
('Vũ Văn Q', 'vuvanqu@example.com', 'password123', 'customer', '0956789012', 'Hưng Yên, Việt Nam'),
('Châu Thị R', 'chauthir@example.com', 'password123', 'customer', '0967890123', 'Bình Định, Việt Nam'),
('Giang Văn S', 'giangvans@example.com', 'password123', 'customer', '0978901234', 'Bạc Liêu, Việt Nam'),
('Quách Thị T', 'quachthit@example.com', 'password123', 'customer', '0989012345', 'Sóc Trăng, Việt Nam');

-- Dữ liệu mẫu category
INSERT INTO Category (category_name, description) VALUES
('Laptop Gaming', 'Laptop hiệu suất cao dành cho game thủ'),
('Laptop Văn Phòng', 'Laptop phù hợp cho công việc văn phòng và học tập'),
('Laptop Đồ Họa', 'Laptop chuyên dụng cho thiết kế đồ họa và chỉnh sửa video'),
('PC Gaming', 'Máy tính bàn mạnh mẽ dành cho game thủ'),
('PC Văn Phòng', 'Máy tính bàn phù hợp cho công việc văn phòng'),
('PC Workstation', 'Máy tính chuyên dụng cho thiết kế, lập trình, render'),
('Màn Hình Gaming', 'Màn hình có tần số quét cao dành cho game thủ'),
('Màn Hình Văn Phòng', 'Màn hình phù hợp cho công việc văn phòng'),
('Bàn Phím Cơ', 'Bàn phím cơ cho game thủ và người dùng chuyên nghiệp'),
('Bàn Phím Không Dây', 'Bàn phím không dây tiện lợi cho văn phòng'),
('Chuột Gaming', 'Chuột gaming với độ nhạy cao và nhiều nút tùy chỉnh'),
('Chuột Không Dây', 'Chuột không dây nhỏ gọn, tiện lợi khi di chuyển'),
('Tai Nghe Gaming', 'Tai nghe có micro, âm thanh vòm dành cho game thủ'),
('Tai Nghe Bluetooth', 'Tai nghe không dây kết nối Bluetooth tiện lợi'),
('Loa Máy Tính', 'Loa chất lượng cao dành cho PC, laptop'),
('Ổ Cứng SSD', 'Ổ cứng SSD tốc độ cao giúp tăng hiệu suất máy tính'),
('Ổ Cứng HDD', 'Ổ cứng HDD dung lượng lớn để lưu trữ dữ liệu'),
('Ram Laptop', 'Bộ nhớ RAM nâng cấp hiệu suất cho laptop'),
('Ram PC', 'Bộ nhớ RAM dành cho máy tính bàn'),
('Card Đồ Họa', 'GPU mạnh mẽ giúp tăng hiệu suất xử lý đồ họa và chơi game');

-- Dữ liệu mẫu order
INSERT INTO `Order` (user_id, order_date, total_price, status, shipping_address, payment_method) VALUES
(1, '2025-02-01', 15000000, 'Processing', '123 Lê Lợi, Hà Nội', 'Credit Card'),
(2, '2025-02-02', 12000000, 'Shipped', '45 Trần Hưng Đạo, TP.HCM', 'Bank Transfer'),
(3, '2025-02-03', 8500000, 'Delivered', '78 Nguyễn Huệ, Đà Nẵng', 'Cash on Delivery'),
(4, '2025-02-04', 9600000, 'Pending', '90 Bạch Đằng, Hải Phòng', 'Credit Card'),
(5, '2025-02-05', 13450000, 'Shipped', '56 Lý Tự Trọng, Cần Thơ', 'PayPal'),
(6, '2025-02-06', 7200000, 'Cancelled', '12 Nguyễn Trãi, Hà Nội', 'Bank Transfer'),
(7, '2025-02-07', 14800000, 'Processing', '34 Võ Văn Kiệt, TP.HCM', 'Cash on Delivery'),
(8, '2025-02-08', 5600000, 'Delivered', '89 Pasteur, Đà Nẵng', 'Credit Card'),
(9, '2025-02-09', 10000000, 'Shipped', '23 Hoàng Văn Thụ, Hải Phòng', 'PayPal'),
(10, '2025-02-10', 11500000, 'Processing', '77 Nguyễn Đình Chiểu, Cần Thơ', 'Bank Transfer'),
(11, '2025-02-11', 9900000, 'Pending', '44 Nguyễn Thị Minh Khai, Hà Nội', 'Credit Card'),
(12, '2025-02-12', 8100000, 'Delivered', '67 Hai Bà Trưng, TP.HCM', 'Cash on Delivery'),
(13, '2025-02-13', 17600000, 'Processing', '21 Trường Chinh, Đà Nẵng', 'PayPal'),
(14, '2025-02-14', 7200000, 'Cancelled', '32 Trần Quang Khải, Hải Phòng', 'Bank Transfer'),
(15, '2025-02-15', 6400000, 'Shipped', '54 Phạm Ngũ Lão, Cần Thơ', 'Credit Card'),
(16, '2025-02-16', 9800000, 'Delivered', '88 Điện Biên Phủ, Hà Nội', 'Cash on Delivery'),
(17, '2025-02-17', 11300000, 'Processing', '76 Nguyễn Văn Cừ, TP.HCM', 'PayPal'),
(18, '2025-02-18', 14500000, 'Shipped', '99 Tôn Đức Thắng, Đà Nẵng', 'Bank Transfer'),
(19, '2025-02-19', 8900000, 'Pending', '29 Lê Văn Lương, Hải Phòng', 'Credit Card'),
(20, '2025-02-20', 13400000, 'Delivered', '60 Quang Trung, Cần Thơ', 'Cash on Delivery');

-- Dữ liệu mẫu product
INSERT INTO Product (category_id, name, price , image_url, description, stock_quantity) VALUES
(1, 'Laptop Dell XPS 13', 32000000,'/assets/img/products/laptop_dell_id01.webp', 'Laptop Dell XPS 13, màn hình 13.4 inch, chip Intel Core i7, RAM 16GB, SSD 512GB', 10),
(2, 'Laptop ASUS ROG Strix G15', 29000000,'/assets/img/products/laptop_asus_ROG_Strix_id02.webp', 'Laptop gaming ASUS ROG Strix G15, màn hình 15.6 inch, Ryzen 7, RAM 16GB, SSD 1TB', 15),
(3, 'MacBook Pro 14 M2', 45000000,'/assets/img/products/macbook_pro_id03.webp', 'MacBook Pro 14 inch, chip M2 Pro, RAM 16GB, SSD 512GB', 8),
(4, 'PC Gaming RTX 4060', 25000000,'/assets/img/products/pc_gaming_RTX4060_id04.webp', 'PC gaming với CPU Intel Core i5-13600K, GPU RTX 4060, RAM 16GB, SSD 1TB', 12),
(5, 'PC Workstation Ryzen 9', 58000000,'/assets/img/products/pc_workstation__ryzen_id05.webp', 'PC Workstation dùng cho đồ họa với Ryzen 9 5950X, RTX 4090, RAM 64GB, SSD 2TB', 5),
(6, 'Bàn phím cơ Keychron K6', 1900000,'/assets/img/products/ban_phim_co_keychron_id06.webp', 'Bàn phím cơ không dây Keychron K6, switch Gateron Brown, hỗ trợ macOS và Windows', 25),
(7, 'Chuột Logitech G Pro X', 3200000,'/assets/img/products/chuot_logitech_g_pro_x_id07.webp', 'Chuột gaming Logitech G Pro X Superlight, không dây, cảm biến HERO 25K', 30),
(8, 'Màn hình LG UltraGear 27GP850', 8900000,'/assets/img/products/manhinh_lg_ultragear_27GP850_id08.webp', 'Màn hình gaming LG UltraGear 27 inch, tần số quét 165Hz, độ phân giải 2K', 20),
(9, 'SSD Samsung 970 EVO Plus 1TB', 2900000,'/assets/img/products/ssd_samsung_970_evo_plus_1tb_id09.webp', 'Ổ cứng SSD Samsung 970 EVO Plus 1TB, NVMe M.2 PCIe Gen 3', 50),
(10, 'HDD WD Black 4TB', 4200000,'/assets/img/products/hhd_wd_black_4tb_id10.webp', 'Ổ cứng HDD WD Black 4TB, tốc độ 7200RPM, cache 256MB', 40),
(11, 'RAM Corsair Vengeance 32GB', 3600000,'/assets/img/products/ram_corsair_vengeance_32GB_id11.webp', 'RAM Corsair Vengeance LPX 32GB (2x16GB) DDR4 3200MHz', 35),
(12, 'Mainboard MSI B550 Tomahawk', 4200000,'/assets/img/products/mainboard_msi_B550_tomahawk_id12.webp', 'Mainboard MSI B550 Tomahawk hỗ trợ Ryzen 5000 series', 18),
(13, 'Card đồ họa RTX 4070 Ti', 20000000,'/assets/img/products/card_manhinh_RTX4070_ti_id13.webp', 'Card đồ họa NVIDIA RTX 4070 Ti, 12GB GDDR6X', 10),
(14, 'Tản nhiệt nước NZXT Kraken X73', 4200000,'/assets/img/products/tan_nhiet_nuoc_NZXT_Kraken_X73_id14.webp', 'Tản nhiệt nước AIO NZXT Kraken X73, kích thước 360mm, RGB', 12),
(15, 'Nguồn Corsair RM850', 3500000,'/assets/img/products/nguon_corsair_rm850_id15.webp', 'Bộ nguồn Corsair RM850, chuẩn 80 Plus Gold, công suất 850W', 22),
(16, 'Ghế gaming Secretlab TITAN Evo', 9900000,'/assets/img/products/ghe_gaming_secretlab_titan_evo_id16.webp', 'Ghế gaming Secretlab TITAN Evo 2022, chất liệu da PU cao cấp', 15),
(17, 'Bàn gaming E-Dra EGT 1600', 4500000,'/assets/img/products/ban_gaming_EDra_EGT1600_id17.webp', 'Bàn gaming E-Dra EGT 1600, mặt gỗ MDF phủ carbon, chân sắt chịu lực', 10),
(18, 'Tai nghe Razer BlackShark V2 Pro', 4500000,'/assets/img/products/tai_nghe_razer_blackshark_v2_pro_id18.webp', 'Tai nghe gaming không dây Razer BlackShark V2 Pro, công nghệ THX Spatial Audio', 28),
(19, 'Webcam Logitech C920 Pro', 2500000,'/assets/img/products/webcam_logitech_c920_pro_id19.webp', 'Webcam Logitech C920 Pro, hỗ trợ Full HD 1080p, micro kép', 32),
(20, 'Micro Rode NT-USB', 4200000,'/assets/img/products/micro_node_NT_usb_id20.webp', 'Micro Rode NT-USB, kết nối USB, phù hợp cho streamer và podcast', 20);

-- Dữ liệu mẫu OrderItem
INSERT INTO OrderItem (order_id, product_id, quantity, price_per_unit) VALUES
(1, 5, 1, 58000000), -- PC Workstation Ryzen 9
(2, 12, 2, 4200000), -- Mainboard MSI B550 Tomahawk
(3, 8, 1, 8900000), -- Màn hình LG UltraGear 27GP850
(4, 1, 1, 32000000), -- Laptop Dell XPS 13
(5, 17, 1, 4500000), -- Bàn gaming E-Dra EGT 1600
(6, 3, 1, 45000000), -- MacBook Pro 14 M2
(7, 9, 2, 2900000), -- SSD Samsung 970 EVO Plus 1TB
(8, 15, 1, 3500000), -- Nguồn Corsair RM850
(9, 7, 1, 3200000), -- Chuột Logitech G Pro X
(10, 13, 1, 20000000), -- Card đồ họa RTX 4070 Ti
(11, 2, 1, 29000000), -- Laptop ASUS ROG Strix G15
(12, 18, 1, 4500000), -- Tai nghe Razer BlackShark V2 Pro
(13, 10, 2, 4200000), -- HDD WD Black 4TB
(14, 6, 1, 1900000), -- Bàn phím cơ Keychron K6
(15, 4, 1, 25000000), -- PC Gaming RTX 4060
(16, 14, 1, 4200000), -- Tản nhiệt nước NZXT Kraken X73
(17, 11, 2, 3600000), -- RAM Corsair Vengeance 32GB
(18, 16, 1, 9900000), -- Ghế gaming Secretlab TITAN Evo
(19, 20, 1, 4200000), -- Micro Rode NT-USB
(20, 19, 1, 2500000); -- Webcam Logitech C920 Pro

-- Dữ liệu mẫu image 
-- INSERT INTO Image (product_id, image_url, image_name) VALUES
-- (1, 'https://example.com/images/laptop_dell_xps_13.jpg', 'Laptop Dell XPS 13'),
-- (2, 'https://example.com/images/laptop_asus_rog_strix_g15.jpg', 'Laptop ASUS ROG Strix G15'),
-- (3, 'https://example.com/images/macbook_pro_14_m2.jpg', 'MacBook Pro 14 M2'),
-- (4, 'https://example.com/images/pc_gaming_rtx_4060.jpg', 'PC Gaming RTX 4060'),
-- (5, 'https://example.com/images/pc_workstation_ryzen_9.jpg', 'PC Workstation Ryzen 9'),
-- (6, 'https://example.com/images/banphim_keychron_k6.jpg', 'Bàn phím cơ Keychron K6'),
-- (7, 'https://example.com/images/chuot_logitech_g_pro_x.jpg', 'Chuột Logitech G Pro X'),
-- (8, 'https://example.com/images/man_hinh_lg_27gp850.jpg', 'Màn hình LG UltraGear 27GP850'),
-- (9, 'https://example.com/images/ssd_samsung_970_evo_plus.jpg', 'SSD Samsung 970 EVO Plus 1TB'),
-- (10, 'https://example.com/images/hdd_wd_black_4tb.jpg', 'HDD WD Black 4TB'),
-- (11, 'https://example.com/images/ram_corsair_vengeance_32gb.jpg', 'RAM Corsair Vengeance 32GB'),
-- (12, 'https://example.com/images/mainboard_msi_b550_tomahawk.jpg', 'Mainboard MSI B550 Tomahawk'),
-- (13, 'https://example.com/images/card_do_hoa_rtx_4070_ti.jpg', 'Card đồ họa RTX 4070 Ti'),
-- (14, 'https://example.com/images/tan_nhiet_nuoc_nzxt_kraken_x73.jpg', 'Tản nhiệt nước NZXT Kraken X73'),
-- (15, 'https://example.com/images/nguon_corsair_rm850.jpg', 'Nguồn Corsair RM850'),
-- (16, 'https://example.com/images/ghe_gaming_secretlab_titan.jpg', 'Ghế gaming Secretlab TITAN Evo'),
-- (17, 'https://example.com/images/ban_gaming_edra_egt_1600.jpg', 'Bàn gaming E-Dra EGT 1600'),
-- (18, 'https://example.com/images/tai_nghe_razer_blackshark_v2_pro.jpg', 'Tai nghe Razer BlackShark V2 Pro'),
-- (19, 'https://example.com/images/webcam_logitech_c920_pro.jpg', 'Webcam Logitech C920 Pro'),
-- (20, 'https://example.com/images/micro_rode_nt_usb.jpg', 'Micro Rode NT-USB');

-- Dữ liệu mẫu voucher
INSERT INTO Voucher (order_id, title, sale_amount, note, start_date, end_date) VALUES
(1, 'Giảm 10% cho đơn hàng đầu tiên', 10.00, 'Chỉ áp dụng cho đơn hàng đầu tiên', '2025-01-01', '2025-12-31'),
(2, 'Voucher giảm 50K', 50.00, 'Áp dụng cho đơn hàng trên 500K', '2025-02-01', '2025-12-31'),
(3, 'Tặng 100K cho đơn hàng trên 1 triệu', 100.00, 'Không áp dụng cùng chương trình khác', '2025-03-01', '2025-12-31'),
(4, 'Miễn phí vận chuyển toàn quốc', 30.00, 'Giảm tối đa 30K phí ship', '2025-04-01', '2025-12-31'),
(5, 'Giảm 5% cho thành viên VIP', 5.00, 'Chỉ áp dụng cho thành viên VIP', '2025-05-01', '2025-12-31'),
(6, 'Giảm 15% khi mua laptop', 15.00, 'Áp dụng cho sản phẩm laptop', '2025-06-01', '2025-12-31'),
(7, 'Voucher sinh nhật - Giảm 20%', 20.00, 'Áp dụng cho khách hàng có sinh nhật trong tháng', '2025-07-01', '2025-07-31'),
(8, 'Giảm 200K cho đơn từ 2 triệu', 200.00, 'Áp dụng cho đơn từ 2 triệu trở lên', '2025-08-01', '2025-12-31'),
(9, 'Tặng 50K cho lần mua tiếp theo', 50.00, 'Sử dụng cho đơn hàng kế tiếp', '2025-09-01', '2025-12-31'),
(10, 'Giảm 8% khi thanh toán qua ví điện tử', 8.00, 'Chỉ áp dụng cho VNPay, MoMo, ZaloPay', '2025-10-01', '2025-12-31'),
(11, 'Flash Sale - Giảm 10%', 10.00, 'Áp dụng trong khung giờ flash sale', '2025-11-01', '2025-11-30'),
(12, 'Voucher hoàn tiền 5%', 5.00, 'Hoàn tiền vào ví điểm tích lũy', '2025-12-01', '2025-12-31'),
(13, 'Voucher giảm 300K cho đơn từ 3 triệu', 300.00, 'Áp dụng cho đơn từ 3 triệu trở lên', '2025-01-15', '2025-12-31'),
(14, 'Tặng tai nghe khi mua PC', 0.00, 'Khuyến mãi quà tặng kèm', '2025-02-10', '2025-12-31'),
(15, 'Voucher Black Friday - Giảm 30%', 30.00, 'Chỉ áp dụng trong dịp Black Friday', '2025-11-25', '2025-11-30'),
(16, 'Giảm 100K cho đơn hàng đầu tiên trên 1 triệu', 100.00, 'Chỉ áp dụng cho khách mới', '2025-03-01', '2025-12-31'),
(17, 'Voucher đặc biệt 12/12', 12.00, 'Áp dụng cho sự kiện 12/12', '2025-12-01', '2025-12-12'),
(18, 'Giảm 5% khi mua combo PC + Màn hình', 5.00, 'Chỉ áp dụng khi mua combo', '2025-06-01', '2025-12-31'),
(19, 'Voucher Tết - Giảm 15%', 15.00, 'Áp dụng trong dịp Tết Nguyên Đán', '2025-01-20', '2025-02-10'),
(20, 'Miễn phí gói bảo hành mở rộng', 0.00, 'Áp dụng khi mua laptop hoặc PC', '2025-07-01', '2025-12-31');

INSERT INTO Users (name, email, password, role, phone_number, address) VALUES
('Admin', 'Admin@example.com', 'password123', 'Admin', '000000000', 'CodeGym');

ALTER TABLE Users DROP COLUMN salt;
ALTER TABLE Users MODIFY password VARCHAR(255);

SELECT * FROM users WHERE email = 'nguyenvana@example.com';
USE onlinepcshop;
SHOW TABLES;
DESC users;
ALTER TABLE Users ADD CONSTRAINT unique_email UNIQUE (email);