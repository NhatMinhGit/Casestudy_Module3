package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.*;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IProductService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.ProductService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet{
    private IOrderItemService orderItemService = new OrderItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy giỏ hàng từ Session
        HttpSession session = req.getSession();

        Category laptopCategory = new Category(1,"Laptop Gaming", "Laptop hiệu suất cao dành cho game thủ");
        User user = new User(1,"Nguyễn Văn A", "nguyenvana@example.com", "password123", "customer", "0987654321", "Hà Nội, Việt Nam");
        LocalDate orderDate = LocalDate.parse("2025-02-01");
        Product product = new Product(1, laptopCategory, "Laptop Dell XPS 13", 32000000.0,"/assets/img/products/laptop_dell_id01.webp","Laptop Dell XPS 13, màn hình 13.4 inch, chip Intel Core i7, RAM 16GB, SSD 512GB", 10);
        Order order = new Order(1,user, orderDate, 15000000.0, "Processing", "123 Lê Lợi, Hà Nội", "Credit Card");

        // Lấy giỏ hàng từ session, nếu null thì tạo mới
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>(); // Tạo danh sách rỗng nếu chưa có trong session
            session.setAttribute("cart", cart); // Lưu vào session
        }
//
        // Lấy thông tin sản phẩm từ request
        //int orderItemId = Integer.parseInt(request.getParameter("order_item_id"));
        //int orderId = Integer.parseInt(request.getParameter("order_id"));
        //int productId = Integer.parseInt(request.getParameter("product_id"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        double price_per_unit = Double.parseDouble(request.getParameter("price_per_unit"));

//
//        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
//        boolean exists = false;
//        for (OrderItem item : cart) {
//            if (item.getOrder_item_id() == orderItemId) {
//                item.setQuantity(item.getQuantity() + 1);
//                exists = true;
//                break;
//            }
//        }
//
//        // Nếu chưa có thì thêm mới
//        if (!exists) {
//            cart.add(new OrderItem(orderItemId,order, product, quantity, price_per_unit));
//        }

        List<OrderItem> orderItemList = orderItemService.getAll();
        req.setAttribute("orderItemList", orderItemList);
        System.out.println(orderItemList);
        System.out.println("Số lượng sản phẩm: " + orderItemList.size());
        for (OrderItem oi : orderItemList) {
            System.out.println(oi.getProduct().getName() + " - " + oi.getProduct().getPrice());
        }

//        // Đưa giỏ hàng vào request attribute để hiển thị trong JSP
//        req.setAttribute("cart", cart);
//
//        //demo
//        cart.add(new OrderItem(1,new Order(1,user, orderDate, 15000000.0, "Processing", "123 Lê Lợi, Hà Nội", "Credit Card"),new Product(1, laptopCategory, "Laptop Dell XPS 13", 32000000.0,"/assets/img/products/laptop_dell_id01.webp", "Laptop Dell XPS 13, màn hình 13.4 inch, chip Intel Core i7, RAM 16GB, SSD 512GB", 10), 1, 9.89));
//        cart.add(new OrderItem(2,new Order(1,user, orderDate, 15000000.0, "Processing", "123 Lê Lợi, Hà Nội", "Credit Card"),new Product(1, laptopCategory, "Laptop Dell XPS 13", 32000000.0,"/assets/img/products/laptop_dell_id01.webp", "Laptop Dell XPS 13, màn hình 13.4 inch, chip Intel Core i7, RAM 16GB, SSD 512GB", 10), 1, 9.89));
//
//
//
//
//        // Đưa giỏ hàng vào request attribute để hiển thị trong JSP
//        req.setAttribute("cart", cart);

        // Chuyển hướng đến cart.jsp
        req.getRequestDispatcher("/WEB-INF/view/product/Cart.jsp").forward(req, resp);

    }


}

