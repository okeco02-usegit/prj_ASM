

<%@page import="pe.model.UserDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code here-->
        <%
            UserDto user = (UserDto) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <h1>Welcome <%= user.getFullName() %> !</h1>
        
        <ul>
            <li><a href="search.jsp">Go to Search Page</a></li>
            <li><a href="MainController?action=Logout">Logout</a></li>
        </ul>
    </body>
</html>
