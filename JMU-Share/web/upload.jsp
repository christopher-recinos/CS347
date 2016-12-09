<%-- 
    Document   : example_2sections
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@taglib uri="/struts-tags" prefix="s" %> 
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
                if(session.getAttribute("userId") == null) session.setAttribute("userId", 1);
            %>
            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <h1>Upload your notes</h1>
                        <p>Session User <s:property value="%{#session.userId}"/></p>
                        <s:form action="uploadToServer"  method="post"  enctype="multipart/form-data">
                            <s:file name="upload"/>
                            <s:file name="upload"/>
                            <s:file name="upload"/>
                            <s:textfield name="notesDesc" placeholder="Description of the Notes"/>
                            <s:textfield name="title"     placeholder="Title of the Notes"/>
                            <s:textfield name="classId"   placeholder="number for class will be replaced with drop down of classes"/>
                            <s:submit/>
                        </s:form>

                        <s:iterator value="upload" var="u">
                            <s:property value="u"/><br/>
                        </s:iterator>
                        <s:iterator value="uploadContentType" var="ct">
                            <s:property value="ct"/><br/>
                        </s:iterator>
                        <s:iterator value="uploadFileName" var="fn">
                            <s:property value="fn"/><br/>
                        </s:iterator>
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
