package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IProductService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TrangChuController", value = "/TrangChu")
public class TrangChuController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
//    }

    private IProductService productService = new ProductService();
    private IOrderService orderService = new OrderService();
    private IOrderItemService orderItemService = new OrderItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            default:
                String message = req.getParameter("message");
                if (message != null) {
                    switch (message) {
                        case "created":
                            req.setAttribute("message", "Thêm mới thành công");
                            break;
                        case "updated":
                            req.setAttribute("message", "Cập nhật thành công");
                            break;
                        case "deleted":
                            req.setAttribute("message", "Xóa thành công");
                            break;
                    }
                }
                List<Product> products = productService.getAll();
                req.setAttribute("products", products);
                //System.out.println(products);
                //System.out.println("Số lượng sản phẩm: " + products.size());
//                for (Product p : products) {
//                    System.out.println(p.getName() + " - " + p.getPrice());
//                }

                req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
        }

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "addToCart":
////                String titleProduct = req.getParameter("titleProduct");
////                Double priceProduct = Double.parseDouble(req.getParameter("priceProduct"));
////                String imgProduct = req.getParameter("imgProduct");
////                Product product = new Product(titleProduct, priceProduct, imgProduct);
////                productService.save(product);
////                resp.sendRedirect("/students?message=addedToCart");
////                break;
//                HttpSession session = req.getSession();
//                User user = (User) session.getAttribute("user"); // Lấy thông tin người dùng đăng nhập
//                int productId = Integer.parseInt(req.getParameter("product_id"));
//
//                if (user == null) {
//                    resp.getWriter().write("{\"success\": false, \"message\": \"Chưa đăng nhập!\"}");
//                    return;
//                }
//
//                //Order order = OrderRepository.createOrder(user.getUser_id()); // Tạo đơn hàng mới
//                //boolean success = OrderItemRepository.addProductToOrder(order.getOrder_id(), productId);
//
//                OrderRepository repository = new OrderRepository();
//                Order order = repository.createOrder(456);
//
//                resp.setContentType("application/json");
//                resp.getWriter().write("{\"success\": " + success + "}");
//        }
//    }
//}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    String action = req.getParameter("action");
    if (action == null) {
        action = "";
    }
    switch (action) {
        case "addToCart":
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user"); // Đảm bảo cùng tên với session khi login

            if (user == null) {
                resp.setContentType("application/json");
                resp.getWriter().write("{\"success\": false, \"message\": \"Chưa đăng nhập!\"}");
                return;
            }

            int productId;
            try {
                productId = Integer.parseInt(req.getParameter("product_id"));
            } catch (NumberFormatException e) {
                resp.setContentType("application/json");
                resp.getWriter().write("{\"success\": false, \"message\": \"ID sản phẩm không hợp lệ!\"}");
                return;
            }

            // Tạo đơn hàng mới cho user
            Order order = orderService.createOrder(user);
            resp.getWriter().write("\"order\":" + order.getOrder_id() + ",");
            if (order == null) {
                resp.setContentType("application/json");
                resp.getWriter().write("{\"success\": false, \"message\": \"Không thể tạo đơn hàng!\"}");
                return;
            }

            boolean success = orderItemService.addProductToOrder(order.getOrder_id(), productId);

            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": " + success + "}");
    }
}
}

