<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: white !important;
            color: red !important;
        }

        .header, .btn, .modal-header {
            background-color: red !important;
            color: white !important;
        }

        .table th, .table td {
            color: black !important;
        }

        .btn-warning {
            background-color: darkorange !important;
            border-color: darkorange !important;
        }

        .btn-danger {
            background-color: darkred !important;
            border-color: darkred !important;
        }

        .btn-success {
            background-color: red !important;
            border-color: red !important;
        }
    </style>
</head>
<body>
<header class="header p-3 text-center">
    <h1>Admin Dashboard</h1>
</header>
<div class="container mt-4">
    <h2>Quản lý Sản phẩm</h2>
    <button class="btn btn-primary mb-3" onclick="showAddProductModal()">Thêm Sản phẩm</button>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Mô tả</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.product_id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td><img src="${product.image_url}" width="50" height="50"></td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editProduct(${product.product_id})">Sửa</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.product_id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal Thêm/Sửa Sản phẩm -->
<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Thêm/Sửa Sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="productForm" action="admin?action=save" method="post">
                    <input type="hidden" id="productId" name="product_id">
                    <div class="mb-3">
                        <label for="productName" class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="productName" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="productDescription" class="form-label">Mô tả</label>
                        <input type="text" class="form-control" id="productDescription" name="description" required>
                    </div>
                    <div class="mb-3">
                        <label for="productPrice" class="form-label">Giá</label>
                        <input type="number" class="form-control" id="productPrice" name="price" required>
                    </div>
                    <div class="mb-3">
                        <label for="productImage" class="form-label">Hình ảnh (URL)</label>
                        <input type="text" class="form-control" id="productImage" name="image_url" required>
                    </div>
                    <button type="submit" class="btn btn-success">Lưu</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function showAddProductModal() {
        document.getElementById('productForm').reset();
        var modal = new bootstrap.Modal(document.getElementById('productModal'));
        modal.show();
    }

    function editProduct(id) {
        fetch(`/admin?action=get&id=${id}`)
            .then(response => response.json())
            .then(product => {
                document.getElementById('productId').value = product.product_id;
                document.getElementById('productName').value = product.name;
                document.getElementById('productDescription').value = product.description;
                document.getElementById('productPrice').value = product.price;
                document.getElementById('productImage').value = product.image_url;
                var modal = new bootstrap.Modal(document.getElementById('productModal'));
                modal.show();
            });
    }

    function deleteProduct(id) {
        if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
            window.location.href = `/admin?action=delete&id=${id}`;
        }
    }

    document.getElementById("productForm").onsubmit = function(event) {
        event.preventDefault(); // Ngăn form submit mặc định
        let formData = new FormData(this);

        fetch("/admin?action=save", {
            method: "POST",
            body: formData
        }).then(response => {
            if (response.ok) {
                return response.text();  // Đọc phản hồi từ server nếu thành công
            } else {
                throw new Error('Lỗi server: ' + response.status);  // Xử lý lỗi nếu server trả về lỗi
            }
        })
            .then(data => {
                console.log("Server response:", data);  // Kiểm tra phản hồi từ server
                alert("Lưu thành công!");
                window.location.href = "/admin"; // Chuyển về trang admin chính
            })
            .catch(error => {
                console.error("Lỗi khi lưu:", error);
                alert("Lưu thất bại! Vui lòng thử lại.");
            });

    };
</script>
</body>
</html>