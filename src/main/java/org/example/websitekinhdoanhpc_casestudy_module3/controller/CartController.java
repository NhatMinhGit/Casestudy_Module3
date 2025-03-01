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
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet {
    private final IOrderItemService orderItemService = new OrderItemService();
    private final IProductService productService = new ProductService();
    private final IOrderService orderService = new OrderService();

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(true);
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        try {
//            // Lấy product_id và quantity từ request
//            String productIdParam = req.getParameter("product_id");
//            String quantityParam = req.getParameter("quantity");
//            System.out.println("Received product_id: " + productIdParam);
//            System.out.println("Received quantity: " + quantityParam);
//
//            if (productIdParam == null) {
//                throw new IllegalArgumentException("Thiếu product_id");
//            }
//
//            int productId = Integer.parseInt(productIdParam);
//            int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;
//            if (quantityParam != null) {
//                try {
//                    quantity = Integer.parseInt(quantityParam);
//                    if (quantity <= 0) {
//                        throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
//                    }
//                } catch (NumberFormatException e) {
//                    throw new IllegalArgumentException("Số lượng không hợp lệ");
//                }
//            }
//
//            System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
//
//            Product product = productService.findById(productId);
//            if (product == null) {
//                System.out.println("Lỗi: Không tìm thấy sản phẩm với ID: " + productId);
//            } else {
//                System.out.println("Sản phẩm tìm thấy: " + product.getName());
//            }
//
//
//            // Xử lý giỏ hàng
//            List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new ArrayList<>();
//                session.setAttribute("cart", cart);
//            }
//
//            boolean exists = false;
//            for (OrderItem item : cart) {
//
//                if (item.getProduct().getProduct_id() == productId) {
//                    item.setQuantity(quantity);
//                    System.out.println("Cập nhật số lượng sản phẩm ID " + productId + " thành: " + quantity);
//                    exists = true;
//                    break;
//                }
//            }
//            if (!exists) {
//                System.out.println("Sản phẩm chưa có trong giỏ hàng, thêm mới.");
//            }
//
//
//            if (!exists) {
//                OrderItem newItem = new OrderItem(cart.size() + 1, null, product, quantity, product.getPrice());
//                cart.add(newItem);
//            }
//
//            session.setAttribute("cart", cart);
//            System.out.println(cart);
//            resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được thêm vào giỏ hàng\"}");
//
//        } catch (Exception e) {
//            e.printStackTrace(); // In lỗi ra console server
//            resp.getWriter().write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
//        }
//        // Kiểm tra tất cả tham số gửi đến
//        Enumeration<String> parameterNames = req.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            System.out.println("Received parameter: " + paramName + " = " + req.getParameter(paramName));
//        }
//        // Lấy order từ session (nếu chưa có, tạo mới)
//        Order order = (Order) session.getAttribute("order");
//        if (order == null) {
//            order = new Order();
//            order.setOrder_date(LocalDate.now());
//            order.setStatus("pending"); // Trạng thái đơn hàng chưa hoàn tất
//            session.setAttribute("order", order);
//        }
//
//        // Xử lý giỏ hàng
//        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new ArrayList<>();
//            session.setAttribute("cart", cart);
//        }
//
//        boolean exists = false;
//        for (OrderItem item : cart) {
//            if (item.getProduct().getProduct_id() == productId) {
//                item.setQuantity(quantity);
//                System.out.println("Cập nhật số lượng sản phẩm ID " + productId + " thành: " + quantity);
//                exists = true;
//                break;
//            }
//        }
//
//        if (!exists) {
//            OrderItem newItem = new OrderItem(cart.size() + 1, order, product, quantity, product.getPrice());
//            cart.add(newItem);
//        }
//
////  Cập nhật lại giỏ hàng trong session
//        session.setAttribute("cart", cart);
//        session.setAttribute("order", order);
//
//        resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được thêm vào giỏ hàng\"}");
//    }
//@Override
//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    HttpSession session = req.getSession(true);
//    resp.setContentType("application/json");
//    resp.setCharacterEncoding("UTF-8");
//
//    try {
//        // Nhận tham số từ request
//        String productIdParam = req.getParameter("product_id");
//        String quantityParam = req.getParameter("quantity");
//        String orderIdParam = req.getParameter("order_id"); // Nhận order_id nếu có
//
//        if (productIdParam == null) {
//            throw new IllegalArgumentException("Thiếu product_id");
//        }
//
//        int productId = Integer.parseInt(productIdParam);
//        int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;
//        if (quantity <= 0) {
//            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
//        }
//
//        // Tìm sản phẩm từ productService
//        Product product = productService.findById(productId);
//        if (product == null) {
//            throw new IllegalArgumentException("Sản phẩm không tồn tại");
//        }
//
//        // Lấy đơn hàng từ session hoặc từ DB
//        Order order = (Order) session.getAttribute("order");
//        int orderId = (orderIdParam != null) ? Integer.parseInt(orderIdParam) : 0;
//
//        if (order == null && orderId > 0) {
//            order = orderService.findOrderById(orderId);
//        }
//
//        if (order == null) {
//            order = new Order();
//            order.setOrder_date(LocalDate.now());
//            order.setStatus("pending");
//            order.setTotal_price(0.0); // Khởi tạo tổng tiền
//            orderService.save(order);
//
//            // Lấy lại order từ database để đảm bảo có order_id
//            order = orderService.findLastInsertedOrder();
//            System.out.println("DEBUG: Order vừa tạo có ID = " + order.getOrder_id());
//        }
//
//        // Cập nhật lại order_id
//        orderId = order.getOrder_id();
//        session.setAttribute("order", order);
//        System.out.println("DEBUG 2: Order vừa tạo có ID = " + order.getOrder_id());
//
//        // Lấy danh sách OrderItems từ DB
//        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
//
//        // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
//        boolean existsInOrder = false;
//        for (OrderItem item : orderItems) {
//            if (item.getProduct().getProduct_id() == productId) {
//                item.setQuantity(quantity);
//                orderItemService.updateItemInCart(orderId, item); // Cập nhật số lượng trong DB
//                existsInOrder = true;
//                break;
//            }
//        }
//
//        // Nếu chưa có sản phẩm trong OrderItem, thêm mới
//        if (!existsInOrder) {
//            OrderItem newItem = new OrderItem(order, product, quantity, product.getPrice());
//            orderItemService.save(newItem); // Lưu vào DB
//        }
//        // 🔥 **Tính tổng tiền của đơn hàng**
//        double totalPrice = orderItemService.calculateTotalPrice(orderId); // Hàm này sẽ cộng dồn quantity * price
//        order.setTotal_price(totalPrice);
//        orderService.updateTotalPrice(orderId, totalPrice); // Cập nhật vào database
//        System.out.println("Order in doPost"+order);
//        System.out.println("OrderItems in doPost"+orderItems);
//
//        // Trả về JSON phản hồi
//        resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được thêm vào đơn hàng\"}");
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        String errorMessage = e.getMessage().replace("\"", "\\\""); // Xử lý dấu " trong chuỗi
//        resp.getWriter().write("{\"status\": \"error\", \"message\": \"" + errorMessage + "\"}");
//
//    }
//}
//@Override
//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    HttpSession session = req.getSession(true);
//    resp.setContentType("application/json");
//    resp.setCharacterEncoding("UTF-8");
//
//    try {
//        String productIdParam = req.getParameter("product_id");
//        String quantityParam = req.getParameter("quantity");
//        String orderIdParam = req.getParameter("order_id");
//
//        if (productIdParam == null) {
//            throw new IllegalArgumentException("Thiếu product_id");
//        }
//
//        int productId = Integer.parseInt(productIdParam);
//        int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;
//        if (quantity <= 0) {
//            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
//        }
//
//        Product product = productService.findById(productId);
//        if (product == null) {
//            throw new IllegalArgumentException("Sản phẩm không tồn tại");
//        }
//
//        Order order = (Order) session.getAttribute("order");
//        int orderId = (orderIdParam != null) ? Integer.parseInt(orderIdParam) : 0;
//
//        if (order == null && orderId > 0) {
//            order = orderService.findOrderById(orderId);
//        }
//
//        if (order == null) {
//            order = new Order();
//            order.setOrder_date(LocalDate.now());
//            order.setStatus("pending");
//            order.setTotal_price(0.0);
//            orderService.save(order);
//
//            order = orderService.findLastInsertedOrder();
//            orderId = order.getOrder_id();
//            session.setAttribute("order", order);
//        } else {
//            orderId = order.getOrder_id();
//        }
//
//        // Lấy danh sách OrderItems từ DB
//        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
//
//        boolean existsInOrder = false;
//        for (OrderItem item : orderItems) {
//            if (item.getProduct().getProduct_id() == productId) {
//                item.setQuantity(quantity);
//                orderItemService.updateItemInCart(orderId, item); // Cập nhật số lượng trong DB
//                existsInOrder = true;
//                break;
//            }
//        }
//
//        if (!existsInOrder) {
//            OrderItem newItem = new OrderItem(order, product, quantity, product.getPrice());
//            orderItemService.save(newItem); // Lưu vào DB
//        }
//
//        // **Cập nhật lại tổng giá đơn hàng**
//        double totalPrice = orderItemService.calculateTotalPrice(orderId);
//        order.setTotal_price(totalPrice);
//        orderService.updateTotalPrice(orderId, totalPrice);
//
//        // **Cập nhật giỏ hàng trong session để phản ánh thay đổi ngay lập tức**
//        List<OrderItem> updatedCart = orderItemService.findOrderItemsByOrderId(orderId);
//        session.setAttribute("cart", updatedCart); // Giỏ hàng sẽ được cập nhật lại
//
//        System.out.println("Order in doPost: " + order);
//        System.out.println("Updated Cart: " + updatedCart);
//
//        resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được cập nhật trong đơn hàng\"}");
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        String errorMessage = e.getMessage().replace("\"", "\\\"");
//        resp.getWriter().write("{\"status\": \"error\", \"message\": \"" + errorMessage + "\"}");
//    }
//}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    try {
        String productIdParam = req.getParameter("product_id");
        String quantityParam = req.getParameter("quantity");
        String orderIdParam = req.getParameter("order_id");

        if (productIdParam == null) {
            throw new IllegalArgumentException("Thiếu product_id");
        }

        int productId = Integer.parseInt(productIdParam);
        int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;
        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }

        Product product = productService.findById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }

        Order order = (Order) session.getAttribute("order");
        int orderId = (orderIdParam != null) ? Integer.parseInt(orderIdParam) : 0;

        if (order == null && orderId > 0) {
            order = orderService.findOrderById(orderId);
        }

        if (order == null) {
            order = new Order();
            order.setOrder_date(LocalDate.now());
            order.setStatus("pending");
            order.setTotal_price(0.0);
            orderService.save(order);

            order = orderService.findLastInsertedOrder();
            orderId = order.getOrder_id();
            session.setAttribute("order", order);
        } else {
            orderId = order.getOrder_id();
        }

        // Lấy danh sách OrderItems từ DB
        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderId(orderId);

        boolean existsInOrder = false;
        for (OrderItem item : orderItems) {
            if (item.getProduct().getProduct_id() == productId) {
                item.setQuantity(quantity);
                orderItemService.updateItemInCart(orderId, item); // Cập nhật số lượng trong DB
                existsInOrder = true;

                // Cập nhật lại danh sách giỏ hàng sau mỗi lần cập nhật số lượng
                List<OrderItem> updatedCart = orderItemService.findOrderItemsByOrderId(orderId);
                session.setAttribute("cart", updatedCart);
                session.setAttribute("order", orderService.findOrderById(orderId));

                System.out.println("Cập nhật giỏ hàng sau khi thay đổi số lượng: " + updatedCart);
                break;
            }
        }


        if (!existsInOrder) {
            OrderItem newItem = new OrderItem(order, product, quantity, product.getPrice());
            orderItemService.save(newItem); // Lưu vào DB
        }

        // **Cập nhật lại tổng giá đơn hàng**
        double totalPrice = orderItemService.calculateTotalPrice(orderId);
        order.setTotal_price(totalPrice);
        orderService.updateTotalPrice(orderId, totalPrice);

        // **Cập nhật giỏ hàng trong session để phản ánh thay đổi ngay lập tức**
        List<OrderItem> updatedCart = orderItemService.findOrderItemsByOrderId(orderId);
        session.setAttribute("cart", updatedCart); // Giỏ hàng sẽ được cập nhật lại

        System.out.println("Order in doPost: " + order);
        System.out.println("Updated Cart: " + updatedCart);

        resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được cập nhật trong đơn hàng\"}");

    } catch (Exception e) {
        e.printStackTrace();
        String errorMessage = e.getMessage().replace("\"", "\\\"");
        resp.getWriter().write("{\"status\": \"error\", \"message\": \"" + errorMessage + "\"}");
    }
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Lấy order từ session
        Order order = (Order) session.getAttribute("order");
        List<OrderItem> cart = null;

        if (order != null) {
            int orderId = order.getOrder_id();
            session.setAttribute("order_id", orderId); // Lưu order_id vào session
            System.out.println("doGet orderId cart "+orderId);
            cart = orderItemService.findOrderItemsByOrderId(orderId);
            session.setAttribute("cart", cart);
        } else {
            cart = (List<OrderItem>) session.getAttribute("cart");
        }

        req.setAttribute("cart", cart);

        // Log kiểm tra
        System.out.println("Cart content: " + cart);
        System.out.println("Debug: order_id stored in session = " + session.getAttribute("order_id"));

        // Chuyển đến trang giỏ hàng
        req.getRequestDispatcher("/WEB-INF/view/product/Cart.jsp").forward(req, resp);
    }
}
