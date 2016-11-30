<%-- 
    Document   : welcome
    Created on : Nov 18, 2016, 2:01:32 PM
    Author     : greatwmc
--%>
<%@page import="action.FetchComments"%>
<%@page import="model.DBHandler"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World2!</h1>

        <h3>All Comments:</h3>
        <s:bean name="bean.CommentBean">
            <s:iterator  value="list">  

                <fieldset>  
                    <s:property value="id"/><br/>  
                    <s:property value="author_id"/><br/>  
                    <s:property value="post_id"/><br/>  
                    <s:property value="comment"/><br/>  
                </fieldset>  
            </s:iterator>  
        </s:bean>
    </body>
</html>
