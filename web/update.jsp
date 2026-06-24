<%@page import="pe.model.ProductDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <h2>Update Product Information</h2>
        <%
            ProductDto product = (ProductDto) request.getAttribute("PRODUCT");
            if (product == null) {
                product = new ProductDto("", "", "", 0, 0);
            }
        %>
        
        <form action="MainController" method="POST">
            Product ID: <input type="text" name="id" value="<%= product.getId() %>" readonly="readonly"/><br/>
            Product Name: <input type="text" name="name" value="<%= product.getName() %>" /><br/>
            Category: <input type="text" name="category" value="<%= product.getCategory() %>" /><br/>
            Price: <input type="text" name="price" value="<%= product.getPrice() %>" /><br/>
            Stock Quantity: <input type="text" name="stockQuantity" value="<%= product.getStockQuantity() %>" /><br/>
            
            <input type="submit" name="action" value="UpdateProduct"/>
        </form>

        <%
            String error = (String) request.getAttribute("ERROR");
            if (error != null) {
        %>
            <h4 style="color: red;"><%= error %></h4>
        <%  } %>

        <br/>
        <a href="search.jsp">Back to Search</a>
    </body>
</html>