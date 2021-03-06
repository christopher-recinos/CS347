<%-- 
    Document   : about
    Created on : Dec 9, 2016, 5:57:08 PM
    Author     : recinocs
--%>

<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
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
                <jsp:include page='menubar_nologout.jsp'/>
            </div>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <br/><br/><br/><p4>About the developers: </p4><br/><br/>
                        <div class="about_info">
                        <p6><b>Christopher Recinos - </b><i>Chris is a senior Computer Science major pursuing a minor in Economics. He's a backend developer who prefers to work with databases, but isn't afraid to step into the front-end. He's an Oracle Certified Programmer in Java and will be working as a Software Engineer for BTI360 after graduation.</i></p6>
                        <br/><br/>
                        <p6><b>Miles Greatwood - </b><i>Miles is a Senior CS Major at JMU. He enjoys long walks on the beach but doesn't live near a beach. Sucks for him.</i></p6>
                        <br/><br/>
                        <p6><b>Jacob Wilson - </b><i>Jacob is a senior Computer Science major going after an Art minor.  He is a backend developer who enjoys working with java, and html.  He is a CIW certified web technician, and enjoys working as part of a team.</i></p6>
                        <br/><br/>
                        <p6><b>Kendrick Smith - </b><i>Kendrick S. is a senior Computer Science Major who is working towards a Computer Information Systems Minor as well. He enjoys both front and back end programming and enjoys the Python and C++ programming languages the most.</i></p6>
                        </div>
                    </div>
                </div>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <div class="logo">
                            <img src="image/James_MadisonU_seal.png" alt="James Madison University seal"/>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
