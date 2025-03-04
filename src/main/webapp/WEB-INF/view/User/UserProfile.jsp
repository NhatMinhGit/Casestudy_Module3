<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hồ sơ người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card mx-auto shadow-lg" style="max-width: 500px; border-radius: 12px;">
        <!-- Cập nhật màu đỏ cho header -->
        <div class="card-header text-center text-white" style="background-color: #ff4d4d; border-top-left-radius: 12px; border-top-right-radius: 12px;">
            <h3>Thông tin cá nhân</h3>
        </div>
        <div class="card-body text-center">

            <h5 class="text-danger">Xin chào, ${user.name}!</h5>

            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Vai trò:</strong> ${user.role}</p>
            <p><strong>Số điện thoại:</strong> ${user.phone_number}</p>
            <p><strong>Địa chỉ:</strong> ${user.address}</p>

            <form action="<%= request.getContextPath() %>/logout" method="POST">
                <button type="submit" class="btn btn-danger w-100">Đăng xuất</button>
            </form>


            <a href="<%= request.getContextPath() %>/TrangChu" class="btn w-100 mt-2 text-white" style="background-color: #ff4d4d;">Quay về Trang Chủ</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
