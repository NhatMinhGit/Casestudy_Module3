package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.ProductRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    private ProductRepository productRepository = new ProductRepository(); // Gọi repository để lấy dữ liệu

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách sản phẩm từ database
        List<Product> products = productRepository.getAllProducts();

        // In ra console để debug
        System.out.println("Debug - Số lượng sản phẩm: " + products.size());
        for (Product p : products) {
            System.out.println("Debug - " + p.getName() + " - " + p.getPrice());
        }

        // Đặt danh sách sản phẩm vào request
        req.setAttribute("products", products);

        // Chuyển hướng sang trang admin
        req.getRequestDispatcher("/WEB-INF/view/product/TrangChuAdmin.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("save".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String imageUrl = request.getParameter("image_url");

            ProductRepository productRepository = new ProductRepository();
            Product product = new Product(productId, null, name, price, imageUrl, description, 0);

            if (productId == 0) {
                productRepository.save(product);
            } else {
                productRepository.update(productId, product);
            }

            response.sendRedirect("webapp/WEB-INF/view/product/TrangChu.jsp");
        }
    }
}

