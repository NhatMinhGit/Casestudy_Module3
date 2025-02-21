<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/02/2025
  Time: 1:56 pm
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
<<<<<<< HEAD
<%--    <link rel="stylesheet" href="/css/TrangChu.css">--%>
    <style>
        body {
            background-color: #fff;
            color: #000;
        }
        .header, .nav-menu, .navbar, .btn-primary {
            background-color: rgb(227,0,25);
        }
        .header a, .nav-menu a, .navbar a, .btn-primary {
            color: white !important;
        }
        .btn-primary:hover {
            background-color: #b3001b;
        }
        .card img {
            height: 416px;
            width: 100%;
            object-fit: cover;
        }
        .custom-red {
            background-color: #ff4d4d; /* Màu đỏ tùy chỉnh */
            color: white; /* Để chữ dễ nhìn hơn */
=======
    <link rel="stylesheet" href="css/TrangChu.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Định dạng cho pop-up (Modal) */
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



        /* Định dạng nút đóng */
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

        /* Định dạng form */
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
>>>>>>> origin/main
        }
    </style>
</head>
<body>
<header class="header">
    <a href="">
        <img src="" alt="">
    </a>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid d-flex justify-content-between align-items-center custom-red">
            <a class="navbar-brand" href="#">PC MNhat</a>
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
<%--<p>Danh sách sản phẩm: ${products}</p>--%>
<div class="container mt-4">
    <div id="productList" class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="${product.image_url}" class="card-img-top" alt="${product.description}">
<%--                    <img src="/assets/img/products/laptop_dell_id01.webp" class="card-img-top" alt="${product.description}">--%>

                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <h5 class="card-title">${product.description}</h5>
                        <p class="card-text">Giá: ${product.price}</p>
                        <button class="btn btn-primary" onclick="window.location.href='/TrangChu?action=addToCart&id=${product.product_id}';">Mua ngay</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
</script>
</body>
</html>
