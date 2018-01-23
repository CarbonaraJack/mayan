$(document).ready(function () {
    //prendo il parametro mode dal get
    var mode = $(document).getUrlParam("mode");
    var url="./index.jsp";
    var messaggio = document.getElementById("messaggio");
    var redirect = document.getElementById("redirect");
    if(mode === "reset"){
        messaggio.innerHTML = "Password reimpostata con successo."; 
        url="./login.jsp?mode=login";
        redirect.href=url;
    }
    if(mode === "login"){
        messaggio.innerHTML = "Login eseguito con successo."; 
        url="./index.jsp";
        redirect.href=url;
    }
    //prendo il parametro err dal get
    var err = $(document).getUrlParam("err");
    if(err === "r1"){
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi."; 
        url="./login.jsp?mode=forgot";
        redirect.href=url;
    }
    //dopo 5 secondi esegue il redirect
    setTimeout(function () {
       window.location.href = url;
    }, 5000);
});