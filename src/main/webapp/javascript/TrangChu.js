document.addEventListener("DOMContentLoaded", function() {
    console.log("Script chạy thành công"); // Debug
    function renderProducts() {
        let productList = document.getElementById("productList");
        console.log("Đã tìm thấy phần tử productList:", productList); // Debug
        if (!productList) return; // Nếu không tìm thấy, thoát luôn
        productList.innerHTML = ""; // Xóa nội dung cũ trước khi render
        products.forEach(product => {
            let card = `
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img id="imgProduct" src="${product.image}" class="card-img-top" alt="${product.title}">
                        <div class="card-body">
                            <h5 id="titleProduct" class="card-title">${product.title}</h5>
                            <p id="priceProduct" class="card-text">Giá: ${product.price}</p>
                            <button class="btn btn-primary" onclick="window.location.href='/TrangChu?action=addToCart';">Mua ngay</button>
                        </div>
                    </div>
                </div>
                `;
            productList.innerHTML += card;
        });
        console.log("Sản phẩm đã được thêm vào danh sách."); // Debug
    }

    renderProducts(); // Gọi hàm để hiển thị sản phẩm
});
