<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/02/2025
  Time: 1:56 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PC codegym</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/resources/css/styles.css">  <%-- Updated paths --%>
    <link rel="stylesheet" href="/resources/css/TrangChu.css"> <%-- Updated paths --%>
    <style>
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
        .nav-menu a {
            text-decoration: none;
            color: black;
            font-weight: 500;
            padding: 8px 12px;
        }
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
        .dropdown-item {
            padding: 8px 12px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<header class="header">
    <a href="">
        <img src="" alt="">
    </a>

    <nav class="nav-menu">
        <a href="#" class="icon me-2" data-bs-toggle="tooltip" title="Search">
            <i class="bi bi-search"></i>
        </a>
        <a href="#">Laptop</a>
        <a href="#">PC</a>
        <a href="#">Linh kiện máy tính</a>
        <a href="#">Thương Hiệu</a>
        <a href="#">Khuyến Mãi</a>
    </nav>
    <div class="nav-icons">
        <div class="d-flex justify-content-end align-items-center">
            <a href="#" class="icon me-2" data-bs-toggle="tooltip" title="Login">
                <i class="bi bi-person"></i>
                <span class="d-none d-md-inline">Đăng nhập</span>
            </a>
            <a href="#" class="icon me-2" data-bs-toggle="tooltip" title="Messages">
                <i class="bi bi-chat-left-text"></i>
            </a>
            <a class="cart-icon me-2 position-relative" data-bs-toggle="tooltip" title="Cart">
                <i class="bi bi-cart"></i>
                <span class="cart-count position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">2</span>
            </a>
            <a href="#" class="icon" data-bs-toggle="tooltip" title="More">
                <i class="bi bi-three-dots-vertical"></i>
            </a>
        </div>
    </div>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>