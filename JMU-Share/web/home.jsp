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
            <% //Set Default session attribute userId for testing
                if (session.getAttribute("userId") == null) {
                    session.setAttribute("userId", 1);
                }
            %>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        Include links to the users classes or favorite notes here<br>
                        Include links to the users classes here<br>
                        Include links to the users classes here<br>
                        Include links to the users classes here<br>
                        Include links to the users classes here<br>
                        Include links to the users classes here<br>
                        Include links to the users classes here<br>
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <h1>Your posts...</h2>
                            Include links to the User's created notes here <br>
                            <% //Set Default session attribute userId for testing
                                if (session.getAttribute("userId") == null) {
                                    session.setAttribute("userId", 1);
                                }
                            %>
                            <p>Session User <s:property value="%{#session.userId}"/></p>
                            <s:iterator value="userPosts">
                                <s:url var="url" action="viewpost">
                                    <s:param name="postId" value="%{id}"/>
                                </s:url>
                                <s:a href="%{url}"><s:property value="title"/></s:a>
                                <div class="post" id="postId<s:property value='id'/>">
                                    <div class="floating-box">
                                        <s:property value="notes_desc"/>
                                        <br>Class: <s:property value='className'/>
                                        <br>Author: <s:property value="authorName"/></div>
                                </div>
                                <br>
                            </s:iterator>
                            </div>
                            </div>
                            </div>
                            <div id="footer">
                                <jsp:include page='footer.jsp'/>
                            </div>
                    </div>
                    </body>
                    </html>
