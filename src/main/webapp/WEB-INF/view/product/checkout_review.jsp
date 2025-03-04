<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PC codegym</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <%--    <link rel="stylesheet" href="TrangChu.css"> &lt;%&ndash; Updated paths &ndash;%&gt;--%>

    <%--    <link rel="stylesheet" href="/css/TrangChu.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            <a class="navbar-brand" href="TrangChu">PC MNhat</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLaptop" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Build PC
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLaptop">
                            <li><a class="dropdown-item" href="#">Thương hiệu</a></li>
                            <li><a class="dropdown-item" href="#">Giá bán</a></li>
                            <li><a class="dropdown-item" href="#">Nhu cầu sử dụng</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuPC" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Laptop
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuPC">
                            <li><a class="dropdown-item" href="#">Thương hiệu</a></li>
                            <li><a class="dropdown-item" href="#">Giá bán</a></li>
                            <li><a class="dropdown-item" href="#">Nhu cầu sử dụng</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuPhuKien" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
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
                </ul>
            </div> <!-- Đóng .navbar-collapse -->
        </div> <!-- Đóng .container-fluid -->
    </nav>
</header>


<div class="container mt-5">
    <h2>Xác nhận đơn hàng</h2>

    <div class="card p-4">
        <h4>Thông tin khách hàng</h4>
        <p><strong>Họ và Tên:</strong> ${sessionScope.guestName}</p>
        <p><strong>Email:</strong> ${sessionScope.guestEmail}</p>
        <p><strong>Số điện thoại:</strong> ${sessionScope.guestPhone}</p>
        <p><strong>Địa chỉ giao hàng:</strong> ${sessionScope.guestAddress}</p>
    </div>

    <div class="card p-4 mt-4">
        <h4>Giỏ hàng của bạn</h4>
        <c:choose>
            <c:when test="${empty cart}">
                <p>Giỏ hàng trống.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Tổng</th>
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
                    </c:forEach>
                    </tr>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <form id="orderForm" action="place-order" method="post" class="mt-4">
        <button type="submit" class="btn btn-success w-100">Xác nhận đặt hàng</button>
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById("orderForm");

        if (form) {
            form.addEventListener("submit", function(event) {
                event.preventDefault(); // Ngăn chặn gửi form mặc định

                fetch("place-order", { method: "POST" })
                    .then(response => response.json()) // Chuyển phản hồi thành JSON
                    .then(data => {
                        alert(data.message); // Hiển thị thông báo
                        window.location.href = "/TrangChu"; // Chuyển hướng về trang chủ
                    })
                    .catch(error => {
                        console.error("Lỗi:", error);
                        alert("Có lỗi xảy ra. Vui lòng thử lại!");
                    });
            });
        } else {
            console.error("Không tìm thấy form với id='orderForm'");
        }
    });

</script>