window.onload = function () {
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
    if (!request) {
        alert("Error initializing XMLHttpRequest!");
    }
}