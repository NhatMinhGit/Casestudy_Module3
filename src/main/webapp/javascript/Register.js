// Hiển thị modal đăng ký
function showRegisterModal() {
    document.getElementById("registerModal").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

// Đóng modal đăng ký
function closeRegisterModal() {
    document.getElementById("registerModal").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}

// Kiểm tra email đã tồn tại hay chưa
document.getElementById("registerForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Ngừng gửi form để kiểm tra dữ liệu

    var email = document.getElementById("registerEmail").value;

    // Gửi yêu cầu AJAX để kiểm tra email đã tồn tại chưa
    fetch("<%= request.getContextPath() %>/checkEmail?email=" + email)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                // Hiển thị thông báo lỗi nếu email đã tồn tại
                document.getElementById("error-message").style.display = "block";
            } else {
                // Nếu email chưa tồn tại, gửi form đi
                document.getElementById("registerForm").submit();
            }
        })
        .catch(error => console.error("Error checking email:", error));
});
