<%-- 
    Document   : example_2sections
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>                    
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                        Place TOP content Here <br>
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <div id='bottomContent'>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                        Place All content Here <br>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
