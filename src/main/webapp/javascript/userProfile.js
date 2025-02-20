// Xử lý chỉnh sửa hồ sơ người dùng
function editProfile() {
    let newUsername = prompt("Nhập tên mới:", document.getElementById("username").innerText);
    if (newUsername !== null && newUsername.trim() !== "") {
        document.getElementById("username").innerText = newUsername;
        alert("Tên đã được cập nhật!");
        // TODO: Gửi AJAX cập nhật tên trên server
    }
}

// Xử lý đăng xuất
function confirmLogout() {
    let confirmAction = confirm("Bạn có chắc chắn muốn đăng xuất?");
    if (confirmAction) {
        window.location.href = "logout"; // Điều hướng đến Servlet xử lý đăng xuất
    }
}

// Chạy khi trang tải xong
document.addEventListener("DOMContentLoaded", function () {
    console.log("UserProfile.js loaded!");
});
