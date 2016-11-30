function updatePage() {
    if (request.readyState == 4) {
        if (request.status == 200) {
            var res = request.responseText
        }
        else {
            alert("Error: status code " + request.status)
        }
    }
}

function voteUpRequest(commentId)
{
    if (!request)
        return;
    var url = "/JMU-Share/upvote?commentIdParam=" + commentId;
    request.open("GET", url, true)
    request.onreadystatechange = updatePage
    request.send()
}

function voteDownRequest(commentId)
{
    if (!request)
        return;
    var url = "/JMU-Share/downvote?commentIdParam=" + commentId;
    request.open("GET", url, true)
    request.onreadystatechange = updatePage
    request.send()
}

function brightenUp(x) {
    x.style.borderBottom = "15px solid #66ff66";
}

function darkenUp(x) {
    x.style.borderBottom = "15px solid #008000";
}

function brightenDown(x) {
    x.style.borderTop = "15px solid #ff0000";
}

function darkenDown(x) {
    x.style.borderTop = "15px solid #990000";
}

function downVote(x) {
    var middle = x.previousElementSibling;
    var inner = middle.innerHTML;
    var score = parseInt(inner, 10) - 1;
    middle.innerHTML = score;
    var cIdArrSplit = middle.id.split('_');
    var commentId = cIdArrSplit[1];
    voteDownRequest(commentId);
}

function upVote(x) {
    var middle = x.nextElementSibling;
    var downArrow = middle.nextElementSibling;
    var inner = middle.innerHTML;
    var score = parseInt(inner, 10) + 1;
    middle.innerHTML = score;
    var cIdArrSplit = middle.id.split('_');
    var commentId = cIdArrSplit[1];
    voteUpRequest(commentId);
}