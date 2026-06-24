
<%@page import="java.util.List"%>
<%@page import="pe.model.ProductDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <h2>Search Product</h2>

        <%-- Thanh tìm kiếm gửi action về MainController --%>
        <form action="MainController" method="POST">
            <%
                String search = request.getParameter("search");
                if (search == null)
                    search = "";
            %>
            Search Product Name: <input type="text" name="search" value="<%= search%>" />
            <input type="submit" name="action" value="Search" />
        </form>

        <br/><hr/><br/>

        <%
            List<ProductDto> list = (List<ProductDto>) request.getAttribute("LIST_PRODUCT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Stock Quantity</th> </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ProductDto product : list) {
                %>
                <tr>
                    <td><%= count++%></td>
                    <td><%= product.getId()%></td>
                    <td><%= product.getName()%></td>
                    <td><%= product.getCategory()%></td>
                    <td><%= product.getPrice()%></td>
                    <td><%= product.getStockQuantity()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <h3 style="color: orange;">No product found!</h3>
        <%
                }
            }
        %>

        <br/>
        <a href="welcome.jsp">Back to Welcome Page</a>
    </body>
</html>
