<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 18/02/2025
  Time: 9:09 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem" %>
<%@ page import="java.util.List" %>
<%--<% int cart = (Integer) request.getAttribute("cart"); %>--%>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết giỏ hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #fff;
            color: #000;
        }

        /* ===== Header & Navigation ===== */
        .header, .nav-menu, .navbar, .btn-primary {
            background-color: rgb(227,0,25);
        }
        .navbar {
            background-color: rgb(227,0,25) !important;
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

        /* ===== Custom Red Theme ===== */
        .custom-red {
            background-color: #ff4d4d;
            color: white;
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

    </style>
</head>
<body>
<header class="header">
    <a href="">
        <img src="" alt="">
    </a>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand" href="TrangChu">PC MNhat</a>
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
                <a href="#" class="icon" data-bs-toggle="tooltip" title="Login">
                    <i class="bi bi-person"></i> <span class="d-none d-md-inline">Đăng nhập</span>
                </a>
                <a href="#" class="icon" data-bs-toggle="tooltip" title="Messages">
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
<section class="h-100 h-custom">
    <div class="container h-100 py-5">
<%--        <p>Danh sách giỏ hàng: ${cart}</p>--%>

        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col">

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" class="h5">Shopping Bag</th>
                            <th scope="col">Sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Tổng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr data-product-id="${item.product_id}">
                            <c:forEach var="item" items="${cart}">
                                <tr>
                                    <th scope="row">
                                        <div class="d-flex align-items-center">
                                            <img src="${item.product.image_url}" class="img-fluid rounded-3"
                                                 alt="${item.product.name}" style="width: 120px;" />
                                            <div class="flex-column ms-4">
                                                <p class="mb-2">${item.product.name}</p>
                                            </div>
                                        </div>
                                    </th>
                                    <td class="align-middle">
                                        <p class="mb-0" style="font-weight: 500;">${item.product.category.category_name}</p>
                                    </td>
<%--                                    <td>${item.quantity}</td>--%>
                                    <td class="align-middle">
                                        <div class="d-flex flex-row">
                                            <!-- Nút Giảm (-) -->
                                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-link px-2"
                                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown(); handleUpdateQuantity(this)">
                                                <i class="fas fa-minus"></i>
                                            </button>

                                            <!-- Ô nhập số lượng -->
                                            <input id="form1" min="1" name="quantity" value="${item.quantity}" type="number"
                                                   class="form-control form-control-sm quantity" style="width: 50px;"
                                                   oninput="handleUpdateQuantity(this, '${item.product.product_id}')" />


                                            <!-- Nút Tăng (+) -->
                                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-link px-2"
                                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp(); handleUpdateQuantity(this)">
                                                <i class="fas fa-plus"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        <p class="mb-0" style="font-weight: 500;">${item.product.formattedPrice}</p>
                                    </td>
                                    <td class="align-middle">
                                        <fmt:setLocale value="vi_VN"/>
                                        <fmt:formatNumber value="${item.product.price * item.quantity}" type="currency" currencySymbol="₫"/>
                                    </td>
                                    <td class="align-middle">
                                        <i class="bi bi-trash" style="cursor: pointer;" onclick="deleteItem('${item.product.product_id}', this)"></i>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tr>

                        </tbody>
                    </table>
                </div>

<%--                <div class="card shadow-2-strong mb-5 mb-lg-0" style="border-radius: 16px;">--%>
<%--                    <div class="card-body p-4">--%>
<%--                        <div class="row gap-4"> <!-- Thêm khoảng cách giữa hai phần -->--%>
<%--                            <div class="col-md-6 col-lg-4 col-xl-3 mb-4 mb-md-0">--%>
<%--                                <form>--%>
<%--                                    <div class="d-flex flex-row pb-3">--%>
<%--                                        <div class="d-flex align-items-center pe-2">--%>
<%--                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel1v"--%>
<%--                                                   value="" aria-label="..." checked />--%>
<%--                                        </div>--%>
<%--                                        <div class="rounded border w-100 p-3">--%>
<%--                                            <p class="d-flex align-items-center mb-0">--%>
<%--                                                <i class="fab fa-cc-mastercard fa-2x text-body pe-2"></i>Chuyển khoản--%>
<%--                                            </p>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="d-flex flex-row pb-3">--%>
<%--                                        <div class="d-flex align-items-center pe-2">--%>
<%--                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel2v"--%>
<%--                                                   value="" aria-label="..." />--%>
<%--                                        </div>--%>
<%--                                        <div class="rounded border w-100 p-3">--%>
<%--                                            <p class="d-flex align-items-center mb-0">--%>
<%--                                                <i class="fab fa-cc-visa fa-2x fa-lg text-body pe-2"></i>Thanh toán trực tiếp--%>
<%--                                            </p>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </div>--%>

<%--                            <!-- Thêm lớp flex-grow-1 để đẩy nội dung bên phải sang cạnh phải -->--%>
<%--                            <div class="col-lg-4 col-xl-3 d-flex flex-column">--%>
<%--                                <div class="d-flex justify-content-between" style="font-weight: 500;">--%>
<%--                                    <p class="mb-2">Subtotal</p>--%>


<%--                                    <!-- Tạo biến `totalPrice` tạm thời chỉ để hiển thị -->--%>
<%--                                    <c:set var="subTotalPrice" value="0" />--%>

<%--                                    <!-- Lặp qua danh sách orderItems để tính tổng -->--%>
<%--                                    <c:forEach var="item" items="${cart}">--%>
<%--                                        <c:set var="subTotalPrice" value="${subTotalPrice + (item.product.price * item.quantity)}" />--%>
<%--                                    </c:forEach>--%>


<%--                                    <!-- Hiển thị tổng tiền đã định dạng -->--%>
<%--                                    <p class="mb-2">--%>
<%--                                        <fmt:setLocale value="vi_VN"/>--%>
<%--                                        Tổng cộng:--%>
<%--                                        <fmt:formatNumber value="${subTotalPrice}" type="currency" currencySymbol="₫"/>--%>
<%--                                    </p>--%>
<%--                                </div>--%>

<%--                                <div class="d-flex justify-content-between" style="font-weight: 500;">--%>
<%--                                    <p class="mb-0">Shipping</p>--%>
<%--                                    <p class="mb-0">100.000đ</p>--%>
<%--                                </div>--%>

<%--                                <hr class="my-4">--%>
<%--                                <c:set var="totalPrice" value="${subTotalPrice - 100000}" />--%>
<%--                                <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">--%>
<%--                                    <p class="mb-2">Total (tax included)</p>--%>
<%--                                    <p class="mb-2">--%>
<%--                                        <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>--%>
<%--                                    </p>--%>
<%--                                </div>--%>

<%--                                <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block btn-lg">--%>
<%--                                    <div class="d-flex justify-content-between">--%>
<%--                                        <span>Thanh toán</span>--%>
<%--                                    </div>--%>
<%--                                </button>--%>
<%--                                <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block btn-lg">--%>
<%--                                    <div class="d-flex justify-content-between">--%>
<%--                                        <a href="TrangChu" style="color: white; text-decoration: none;">--%>
<%--                                            <span>Tiếp tục mua sắm</span>--%>
<%--                                        </a>--%>
<%--                                    </div>--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="card shadow-2-strong mb-5 mb-lg-0" style="border-radius: 16px;">
                    <div class="card-body p-4">
                        <div class="row">
                            <!-- Phần phương thức thanh toán (1/3) -->
                            <div class="col-lg-4 mb-4 mb-md-0">
                                <form>
                                    <div class="d-flex flex-row pb-3">
                                        <div class="d-flex align-items-center pe-2">
                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel1v"
                                                   value="" aria-label="..." checked />
                                        </div>
                                        <div class="rounded border w-100 p-3">
                                            <p class="d-flex align-items-center mb-0">
                                                <i class="fab fa-cc-mastercard fa-2x text-body pe-2"></i>Chuyển khoản
                                            </p>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row pb-3">
                                        <div class="d-flex align-items-center pe-2">
                                            <input class="form-check-input" type="radio" name="radioNoLabel" id="radioNoLabel2v"
                                                   value="" aria-label="..." />
                                        </div>
                                        <div class="rounded border w-100 p-3">
                                            <p class="d-flex align-items-center mb-0">
                                                <i class="fab fa-cc-visa fa-2x fa-lg text-body pe-2"></i>Thanh toán trực tiếp
                                            </p>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <!-- Phần thông tin tổng tiền (2/3) -->
                            <div class="col-lg-8 d-flex flex-column">
                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                    <p class="mb-2">Subtotal</p>

                                    <!-- Tạo biến `subTotalPrice` -->
                                    <c:set var="subTotalPrice" value="0" />

                                    <!-- Tính tổng tiền -->
                                    <c:forEach var="item" items="${cart}">
                                        <c:set var="subTotalPrice" value="${subTotalPrice + (item.product.price * item.quantity)}" />
                                    </c:forEach>

                                    <!-- Hiển thị tổng tiền -->
                                    <p class="mb-2">
                                        <fmt:setLocale value="vi_VN"/>
                                        Tổng cộng:
                                        <fmt:formatNumber value="${subTotalPrice}" type="currency" currencySymbol="₫"/>
                                    </p>
                                </div>

                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                    <p class="mb-0">Shipping</p>
                                    <p class="mb-0">100.000đ</p>
                                </div>

                                <hr class="my-4">

                                <c:set var="totalPrice" value="${subTotalPrice - 100000}" />
                                <div class="d-flex justify-content-between mb-4" style="font-weight: 500;">
                                    <p class="mb-2">Total (tax included)</p>
                                    <p class="mb-2">
                                        <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>
                                    </p>
                                </div>

                                <div class="d-flex justify-content-between">
                                    <button type="button" class="btn btn-primary w-30 btn-lg">
                                        <a href="TrangChu" style="color: white; text-decoration: none;">
                                            <span>Tiếp tục mua sắm</span>
                                        </a>
                                    </button>
                                    <button type="button" class="btn btn-primary w-20 btn-lg" id="btnThanhToan">
                                        Thanh toán
                                    </button>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</section>

<%--<script src="../../../javascript/TrangChu.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
 <%--get cart từ session--%>
<script>
    function handleUpdateQuantity(inputElement, productId) {
        let newQuantity = parseInt(inputElement.value);
        alert("Product id:"+ productId);
        alert("Quantity:"+ newQuantity);
        // Kiểm tra nếu số lượng nhỏ hơn 1 thì đặt lại thành 1
        if (newQuantity < 1) {
            newQuantity = 1;
            inputElement.value = 1;
        }

        // Gửi AJAX đến Servlet để cập nhật giỏ hàng
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "Cart", true); // Gửi request đến Servlet
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let response = JSON.parse(xhr.responseText);
                if (!response.success) {
                    alert('Cập nhật giỏ hàng thành công');
                }
            }
        };

        xhr.send("product_id=" + productId + "&quantity=" + newQuantity);

    }

    function deleteItem(productId, element) {
        console.log("🛒 Đang xóa sản phẩm với ID:", productId); // Debug ID

        if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
            fetch('/cart/remove', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: 'product_id=' + encodeURIComponent(productId) // Đảm bảo tham số trùng với Servlet
            }).then(response => {
                console.log("Phản hồi từ server:", response);
                return response.text(); // Đọc nội dung phản hồi
            }).then(text => {
                console.log("Nội dung trả về:", text);
                let data = JSON.parse(text);
                if (data.success) {
                    let row = element.closest("tr");
                    if (row) row.remove();
                } else {
                    alert("Xóa thất bại!");
                }
            }).catch(error => console.error("Lỗi:", error));
        }
    }
    <%
      Integer order_id = (Integer) session.getAttribute("order_id");
    %>
        var orderId = "<%= order_id != null ? order_id : "" %>";
        console.log("Order ID in JSP:", orderId);

        document.getElementById('btnThanhToan').addEventListener('click', function() {
        if (!confirm('Bạn có chắc chắn muốn thanh toán?')) return;

        fetch(`/Cart/ThanhToan`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `order_id=${orderId}` // ✅ Đặt đúng tên tham số
    })
        .then(response => response.text())
        .then(data => {
        alert(data);
        window.location.href = "/Cart/ThanhToan";
    })
        .catch(error => {
        alert('Có lỗi xảy ra! Vui lòng thử lại.');
        console.error('Lỗi:', error);
    });
    });
</script>

</body>
</html>
