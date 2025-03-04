package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CheckoutGuestServlet", value = "/checkout-guest")
public class CheckoutGuestServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/WEB-INF/view/product/checkout_guest_form.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            if (name == null || email == null || phone == null || address == null ||
                    name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
                req.getRequestDispatcher("/WEB-INF/view/product/checkout_guest_form.jsp").forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("guestName", name);
            session.setAttribute("guestEmail", email);
            session.setAttribute("guestPhone", phone);
            session.setAttribute("guestAddress", address);

            // Chuyển hướng đến trang xác nhận
            resp.sendRedirect(req.getContextPath() + "/checkout-review");
        }
    }

