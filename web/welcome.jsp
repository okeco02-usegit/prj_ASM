<%@page import="pe.model.UserDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <%
            UserDto user = (UserDto) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <h2>Update Product Details</h2>
        <form action="MainController" method="POST">
            Product ID: <input type="text" name="id" value="<%= request.getParameter("id") %>" readonly/><br/>
            Product Name: <input type="text" name="name" value="<%= request.getParameter("name") %>" required/><br/>
            Category: <input type="text" name="category" value="<%= request.getParameter("category") %>" required/><br/>
            Price: <input type="number" step="0.01" name="price" value="<%= request.getParameter("price") %>" required/><br/>
            Stock Quantity: <input type="number" name="stockQuantity" value="<%= request.getParameter("stockQuantity") %>" required/><br/>
            
            <input type="submit" name="action" value="UpdateProduct"/>
        </form>

        <%
            String error = (String) request.getAttribute("ERROR_MSG");
            if (error != null) {
        %>
        <h3 style="color: red;"><%= error%></h3>
        <%
            }
        %>
        <br/>
        <a href="search.jsp">Back to Search</a>
    </body>
</html>