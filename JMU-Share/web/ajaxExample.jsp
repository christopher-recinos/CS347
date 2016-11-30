<%-- 
   Document   : ajaxExample
    Created on : Nov 18, 2016, 1:03:32 PM
    Author     : greatwmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Ajax Demo</title>

    <script type="text/javascript" language="javascript">
      window.onload = function() {
        try {  // for Firefox, IE7, Opera
          request = new XMLHttpRequest()
        }
        catch (exc) {
          try {  // for IE6
            request = new ActiveXObject('MSXML2.XMLHTTP.5.0')
          }
          catch (e) {
            request = false
          }
        }
        if (request) {
          checkName()
        }
        else {
          alert("Error initializing XMLHttpRequest!");
        }
      }

      function checkName() {
        if (!request) return
        var lastname = document.getElementById("lastname").value.trim()
        var url = "/cs347/LookupName?name=" + escape(lastname)
        request.open("GET", url, true)
        request.onreadystatechange = updatePage
        request.send(null)
        document.getElementById('names').value = ''
      }

      function updatePage() {
        if (request.readyState == 4) {
          if (request.status == 200) {
            var names = request.responseText.split("|")
            for (var i=0; i<names.length; i++) {
              document.getElementById('names').value += names[i].trim() + '\n'
            }
            if (names.length == 1) {
              document.getElementById("lastname").value = names[0]
            }
          }
          else {
            alert("Error: status code " + request.status)
          }
        }
      }
    </script>
  </head>

  <body>

    <form name="directoryform">
      <p>
        Enter name:
        <input type="text" size="25" id="lastname" onkeyup="checkName()" />
      </p>

      <p>Name:</p>
        <textarea id="names" rows="15" cols="25"></textarea>
    </form>

  </body>
</html>
