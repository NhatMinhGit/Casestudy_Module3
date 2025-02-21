<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>
<h1>Welcome, ${user.name}!</h1>
<p>Email: ${user.email}</p>
<p>Role: ${user.role}</p>
<p>Phone Number: ${user.phone_number}</p>
<p>Address: ${user.address}</p>
<!-- Nút log out -->
<form action="<%= request.getContextPath() %>/logout" method="POST">
    <input type="submit" value="Logout">
</form>

<!-- Nút quay về trang chủ -->
<form action="<%= request.getContextPath() %>/TrangChu" method="GET">
    <input type="submit" value="Quay về Trang Chủ">
</form>

</body>
</html>
