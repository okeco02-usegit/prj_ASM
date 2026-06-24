/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.ProductDao;
import pe.model.ProductDto;

/**
 *
 * @author okeco
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // 1. Lấy từ khóa tìm kiếm từ ô nhập liệu ở search.jsp
            String search = request.getParameter("search");
            if (search == null) {
                search = ""; // Nếu lần đầu vào trang chưa bấm nút, ép thành chuỗi rỗng để hiển thị hết sản phẩm
            }
            // 2. Gọi lớp Dao để lấy danh sách sản phẩm khớp từ khóa
            ProductDao dao = new ProductDao();
            List<ProductDto> list = dao.getProductByName(search);

            // 3. Đóng gói danh sách vào request scope để mang sang trang JSP hiển thị
            request.setAttribute("LIST_PRODUCT", list);
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            // 4. Chuyển tiếp kết quả ngược lại trang search.jsp
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
