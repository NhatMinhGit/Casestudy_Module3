package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveGuestInfo")
public class SaveGuestInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy thông tin khách hàng từ form
        String guestName = request.getParameter("guestName");
        String guestEmail = request.getParameter("guestEmail");
        String guestPhone = request.getParameter("guestPhone");
        String guestAddress = request.getParameter("guestAddress");

        // Lưu vào session
        session.setAttribute("guestName", guestName);
        session.setAttribute("guestEmail", guestEmail);
        session.setAttribute("guestPhone", guestPhone);
        session.setAttribute("guestAddress", guestAddress);

        // Chuyển hướng lại checkout.jsp
        response.sendRedirect("checkout.jsp");
    }
}
