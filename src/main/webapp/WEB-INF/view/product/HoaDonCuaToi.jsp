<%@ page import="org.example.websitekinhdoanhpc_casestudy_module3.entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 27/02/2025
  Time: 7:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PC codegym</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <%--    <link rel="stylesheet" href="TrangChu.css"> &lt;%&ndash; Updated paths &ndash;%&gt;--%>

    <%--    <link rel="stylesheet" href="/css/TrangChu.css">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>
    <style>
        body {
            background-color: #fff;
            color: #000;
        }
        html .navbar {
            background-color: rgb(227, 0, 25) !important;
        }

        /* ===== Header & Navigation ===== */
        .header, .nav-menu, .navbar, .btn-primary {
            background-color: rgb(227,0,25);
        }

        .header {
            display: flex;
            justify-content: center;
            padding: 10px;
        }

        .nav-menu {
            display: flex;
            gap: 20px;
            align-items: center;
        }

        .nav-menu a, .navbar a, .btn-primary {
            text-decoration: none;
            color: white !important;
            font-weight: 500;
            padding: 8px 12px;
        }

        .nav-icons {
            height: 100%;
            display: flex;
            align-items: center;
        }

        .btn-primary:hover {
            background-color: #b3001b;
        }

        /* ===== Card Styles ===== */
        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }

        .card {
            position: relative;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            padding: 20px;
        }

        .card img {
            height: 416px;
            width: 100%;
            object-fit: cover;
        }

        .card-body {
            height: 230px;
        }

        /* ===== Add to Cart Button ===== */
        .add-to-cart {
            position: absolute;
            bottom: 50px;
            right: 40px;
            padding: 8px 12px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s;
        }

        .add-to-cart:hover {
            background-color: #b3001b;
        }


        /* ===== Dropdown Menu ===== */
        .dropdown-menu {
            min-width: 300px;
        }

        .dropdown-menu table {
            width: 100%;
            margin: 0;
        }

        .dropdown-menu th,
        .dropdown-menu td {
            padding: 10px;
            text-align: left;
        }

        .dropdown-menu th {
            background-color: #f8f9fa;
            font-weight: bold;
        }

        /* Đổi màu chữ của dropdown menu */
        .dropdown-menu .dropdown-item {
            color: red !important; /* Màu đỏ */
            font-weight: bold;
        }

        /* Khi hover vào menu item */
        .dropdown-menu .dropdown-item:hover {
            background-color: #f8d7da; /* Màu nền nhạt để dễ nhìn */
            color: darkred !important; /* Đậm hơn khi hover */
        }

        /* ===== Modal (Popup) Styles ===== */
        #registerModal {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            width: 400px;
            border-radius: 8px;
        }

        /* ===== Close Button ===== */
        .close {
            font-size: 1.5em;
            color: #aaa;
            border: none;
            background: none;
            cursor: pointer;
        }

        .close:hover {
            color: black;
        }

        /* ===== Form Styles ===== */
        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body>
<header class="header">
    <a href="">
        <img src="" alt="">
    </a>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand" href="/TrangChu">PC MNhat</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLaptop" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Build PC
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLaptop">
                            <li><a class="dropdown-item" href="#">Thương hiệu</a></li>
                            <li><a class="dropdown-item" href="#">Giá bán</a></li>
                            <li><a class="dropdown-item" href="#">Nhu cầu sử dụng</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuPC" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Laptop
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuPC">
                            <li><a class="dropdown-item" href="#">Thương hiệu</a></li>
                            <li><a class="dropdown-item" href="#">Giá bán</a></li>
                            <li><a class="dropdown-item" href="#">Nhu cầu sử dụng</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuPhuKien" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Phụ kiện
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuPhuKien">
                            <li><a class="dropdown-item" href="#">Tai nghe</a></li>
                            <li><a class="dropdown-item" href="#">Chuột</a></li>
                            <li><a class="dropdown-item" href="#">Bàn phím</a></li>
                            <li><a class="dropdown-item" href="#">Ghế</a></li>
                            <li><a class="dropdown-item" href="#">Tay cầm</a></li>
                        </ul>
                    </li>
                    <%--                    <li class="nav-item dropdown">--%>
                    <%--                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuKhuyenMai" role="button" data-bs-toggle="dropdown" aria-expanded="false">--%>
                    <%--                            Khuyến mãi--%>
                    <%--                        </a>--%>
                    <%--                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuKhuyenMai">--%>
                    <%--                            <li><a class="dropdown-item" href="#">Action</a></li>--%>
                    <%--                            <li><a class="dropdown-item" href="#">Another action</a></li>--%>
                    <%--                            <li><a class="dropdown-item" href="#">Something else here</a></li>--%>
                    <%--                        </ul>--%>
                    <%--                    </li>--%>
                </ul>
            </div>
            <div class="nav-icons d-flex justify-content-end gap-3">
                <a href="javascript:void(0);" class="icon" data-bs-toggle="tooltip" title="Đăng Nhập" onclick="showLoginModal()">
                    <i class="bi bi-person-fill"></i>
                </a>
                <a href="#" class="icon" data-  bs-toggle="tooltip" title="Messages">
                    <i class="bi bi-chat-left-text"></i>
                </a>
                <a href="Cart" class="cart-icon position-relative" data-bs-toggle="tooltip" title="Cart">
                    <i class="bi bi-cart"></i>
                    <span class="cart-count position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">2</span>
                </a>
                <a href="#" class="icon" data-bs-toggle="tooltip" title="More">
                    <i class="bi bi-three-dots-vertical"></i>
                </a>
            </div>
        </div>
    </nav>


</header>
<div class="container mt-4">
    <h2 class="text-center mb-4">Danh Sách Hóa Đơn Của Tôi(Chưa hoàn thiện)</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Mã Đơn Hàng</th>
            <th>Tổng Tiền</th>
            <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.order_id}</td>
                <td>${order.total_price} VNĐ</td>
                <td>${order.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Modal đăng nhập -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Đăng Nhập</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="login" method="post">
                    <div class="form-group">
                        <label for="loginEmail">Email</label>
                        <input type="email" class="form-control" id="loginEmail" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">Mật Khẩu</label>
                        <input type="password" class="form-control" id="loginPassword" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                </form>
                <!-- Nút đăng ký dưới form đăng nhập -->
                <div class="text-center mt-3">
                    <p>Chưa có tài khoản? <a href="#" onclick="switchToRegister()">Đăng ký</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal đăng ký -->
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true" data-bs-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Đăng Ký</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="registerForm" action="<%= request.getContextPath() %>/register" method="POST">
                    <div class="form-group">
                        <label for="registerName">Họ và Tên</label>
                        <input type="text" class="form-control" id="registerName" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="registerEmail">Email</label>
                        <input type="email" class="form-control" id="registerEmail" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="registerPassword">Mật khẩu</label>
                        <input type="password" class="form-control" id="registerPassword" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng Ký</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function showLoginModal() {
        var loginModal = new bootstrap.Modal(document.getElementById('loginModal'));
        loginModal.show();
    }

    function showRegisterModal() {
        var registerModal = new bootstrap.Modal(document.getElementById('registerModal'));
        registerModal.show();
    }

    function switchToRegister() {
        var loginModalEl = document.getElementById('loginModal');
        var registerModalEl = document.getElementById('registerModal');

        if (loginModalEl) {
            var loginModal = bootstrap.Modal.getInstance(loginModalEl) || new bootstrap.Modal(loginModalEl);
            loginModal.hide();
        }

        setTimeout(() => {
            var registerModal = bootstrap.Modal.getInstance(registerModalEl) || new bootstrap.Modal(registerModalEl);
            registerModal.show();
        }, 300);
    }

    // Ajax đọc dữ liệu
    $(document).ready(function () {
        $(".add-to-cart").click(function () {

            var productId = $(this).data("product-id");
            $.ajax({
                url: "/Cart",
                type: "POST",
                data: {product_id: productId},
                success: function (response) {
                    alert(productId);
                    alert(response.message); // Hiển thị thông báo
                },
                error: function () {
                    alert("Có lỗi xảy ra!");
                }
            });
        });
    });

</script>
</body>
</html>
