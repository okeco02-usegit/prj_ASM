/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.ProductDao;
import pe.model.ProductDto;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Bảo mật 1 lớp: Kiểm tra phải đăng nhập mới cho Update
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String url = "update.jsp"; // Nếu lỗi, ở lại trang update
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String priceText = request.getParameter("price");
            String quantityText = request.getParameter("stockQuantity");

            String error = "";
            double price = 0;
            int quantity = 0;

            // ---- BẮT ĐẦU VALIDATE DỮ LIỆU ----
            if (name == null || name.trim().isEmpty()) {
                error += "Product name cannot be blank.<br/>";
            }
            if (category == null || category.trim().isEmpty()) {
                error += "Category cannot be blank.<br/>";
            }

            try {
                price = Double.parseDouble(priceText);
                if (price <= 0) {
                    error += "Price must be greater than 0.<br/>";
                }
            } catch (Exception e) {
                error += "Price must be a valid number.<br/>";
            }

            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity < 0) {
                    error += "Stock quantity must be 0 or greater.<br/>";
                }
            } catch (Exception e) {
                error += "Stock quantity must be a valid integer.<br/>";
            }

            ProductDto productToUpdate = new ProductDto(id, name, category, price, quantity);

            if (!error.isEmpty()) {
                // Nếu có lỗi, trả lỗi và trả lại product đã nhập lên JSP để không bị mất chữ
                request.setAttribute("ERROR", error);
                request.setAttribute("PRODUCT", productToUpdate);
            } else {
                // Nếu thành công, update vào DB
                ProductDao dao = new ProductDao();
                boolean isUpdated = dao.updateProduct(productToUpdate);

                if (isUpdated) {
                    // Update xong thì chuyển về SearchController (cùng từ khóa rỗng để load lại toàn bộ bảng)
                    url = "MainController?action=Search&search=";
                } else {
                    request.setAttribute("ERROR", "Update failed at Database!");
                    request.setAttribute("PRODUCT", productToUpdate);
                }
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
