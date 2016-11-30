<%-- 
    Document   : example
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            html,
            body {
                margin: 0;
                padding: 0;
                height: 100%; /* needed for container min-height */
            }
            div#container {
                position: relative; /* needed for footer positioning*/
                height: auto !important; /* real browsers */
                min-height: 100%; /* real browsers */
            }
            div#header {
                padding-top: 2em;
            }
            div#content {
                /* padding:1em 1em 5em; *//* bottom padding for footer */
            }
            div#footer {
                position: absolute;
                width: 100%;
                bottom: 0; /* stick to bottom */
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>

            <div id="content">
                Place All content Here
            </div>

            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
