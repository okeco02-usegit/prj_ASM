

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <h2>Login System</h2>
        <form action="MainController" method="POST">
            User ID: <input type="text" name="userID" required/><br/>
            Password: <input type="password" name="password" required/><br/>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
        <%
            String error = (String) request.getAttribute("ERROR_MSG");
            if (error != null) {
        %>
        <h3 style="color: red;"><%= error%></h3>
        <%
            }
        %>
    </body>
</html>
