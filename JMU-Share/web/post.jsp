<%-- 
    Document   : post
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
        <script type="text/javascript" language="javascript" src="ajax_req.js"></script>
        <script type="text/javascript" language="javascript" src="comment_voting.js"></script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <h1>Title -- Cryptography Ciphers and Their Weaknesses</h1>
                        <h2>Endorse- <s:property value="post.getEndorse()"/></h2>
                        <h2>Rating- <s:property value="post.getRating()"/></h2>
                        <h2>Class Id- <s:property value="post.getClassId()"/></h2>
                        <h2>Author Id- <s:property value="post.getAuthorName()"/></h2>
                        <p><s:property value="post.getContentBody()"/></p>

                        <s:iterator value="base64URLSafeStrings">
                            <img src="data:image/jpeg;charset=utf-8;base64,<s:property value="testImageString"/>" alt="Default Image 1" height="100%" width="100%"/> 
                        </s:iterator>

                        <!--<img src="image/2016-34-08-20-34-561593348437IMG_0952.JPG" alt="Default Image 1" height="100%" width="100%"> -->
                        <img src="image/default_profile.png" alt="Default Image 2" height="100%" width="100%">
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <div id="commentsSection">
                            <s:iterator  value="commentsList">
                                <div class="comment" id="comment<s:property value='commentId'/>">
                                    <div class="arrows">
                                        <div class="arrow-up" onclick="upVote(this)" onmouseenter="brightenUp(this)" onmouseleave="darkenUp(this)"></div>
                                        <div id="scoreOnCommentId_<s:property value='commentId'/>"><s:property value="score"/></div>
                                        <div class="arrow-down" onclick="downVote(this)" onmouseenter="brightenDown(this)" onmouseleave="darkenDown(this)"></div>
                                    </div>
                                    <div class="floating-box"><s:property value="comment"/><br>By Author <s:property value="commentAuthorName"/></div>
                                </div>
                                <br>
                            </s:iterator>
                        </div>
                        <% //Set Default session attribute userId for testing
                            if (session.getAttribute("userId") == null) {
                                session.setAttribute("userId", 1);
                            }
                        %>

                        <p>Parameter: <s:property value="%{#parameters.postId}"/></p>
                        <p>Session User <s:property value="%{#session.userId}"/></p>
                        <s:form id="submitComment" action="submitComment" method="post">
                            <s:textarea style="width:100%" name="comment" id="comment" placeholder="Write your comment here..."/>
                            <s:hidden name="postId" value="%{#parameters.postId}"/>
                            <s:submit type="submit" value="submitComment"/>
                        </s:form>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
