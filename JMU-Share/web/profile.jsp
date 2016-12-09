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
                        <div style="float:left;">
                        <img src="image/default_profile.png" alt="Default Image" height="150" width="150">
                        </div>
                        <div style="float:right;">
                        <p3 class="thick">Name: <s:property value='user.getName()'/></p3><br/>
                        <p3 class="thick">Username:<s:property value='user.getUsername()'/></p3><br/>
                        <p3 class="thick">Email: <s:property value='user.getEmail()'/></p3><br/>
                        <p3 class="thick">Role: <s:property value='role.getRoleName()'/></p3><br/>
                        <p3 class="thick">Professor: <s:property value='user.getIsProfessor()'/></p3><br/>
                        </div>
                        <div style="clear:both"></div>
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <h2>Your posts...</h2>
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
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
