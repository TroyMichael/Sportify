/**
 * Created by Caroline on 14.12.2015.
 */

function sendRequest() {
    xhr = new XMLHttpRequest();
    if (!xhr) {
        alert("An Error occurred when trying to initialize XMLHttpRequest!");
        return; // exit
    }
    xhr.onreadystatechange = sendRequest_callback;
    xhr.open("POST", "http://localhost:8080/ws/Sportify", true);
    xhr.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
    xhr.setRequestHeader("Host", "http://localhost:8080/");

    // xhr.setRequestHeader("Content-Length", "239");

    xhr.send("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://Server/">
       +"<soapenv:Header/>"
    +"<soapenv:Body>"
    +"<ser:getAllClosedMatches/>"
    +"</soapenv:Body>"
   +" </soapenv:Envelope>");
}
function sendRequest_callback() {
    if ((xhr.readyState == 4) && (xhr.status == 200)) {
        document.getElementById("demo").innerHTML = xhr.responseText;
        document.title = xhr.responseText;
    }
}