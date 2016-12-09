<%-- 
    Document   : index.jsp
    Created on : Nov 18, 2016, 9:32:47 AM
    Author     : greatwmc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="viewpost?postId=1">View All Comments</a><br/>
        <a href="profile?userId=1">View Profile 1</a><br/>
        <a href="profile?userId=5">View Profile 2</a><br/>
        <a href="profile">View Profile No User specified</a><br/>
    </body>
</html>
