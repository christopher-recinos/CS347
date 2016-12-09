<%-- 
    Document   : error
    Created on : Dec 7, 2016, 7:39:12 PM
    Author     : recinocs
--%>

<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>
            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <h5><b><i>Oops! We couldn't complete that transaction. We're sorry for the inconvenience!</i></b></h5>
                        <br/><a class="register_info" href='sign_in'>Click here to return to the login page.</a>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>

