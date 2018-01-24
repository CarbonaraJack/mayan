$(document).ready(function () {
    //prendo il parametro mode dal get
    var mode = $(document).getUrlParam("mode");
    var url="./index.jsp";
    var messaggio = document.getElementById("messaggio");
    var redirect = document.getElementById("redirect");
    if(mode === "reset"){
        messaggio.innerHTML = "Password reimpostata con successo."; 
        url="./login.jsp?mode=login";
    }
    if(mode === "login"){
        var userName=document.getElementById("userName").value;
        messaggio.innerHTML = "Login eseguito con successo. Benvenuto "+userName; 
        url="./index.jsp";
    }
    if(mode === "logout"){
        var userName=document.getElementById("userName").value;
        messaggio.innerHTML = "Logout eseguito con successo."; 
        url="./index.jsp";
    }
    if(mode === "signin"){
        messaggio.innerHTML = "Registrazione utente eseguita con successo."; 
        url="./login.jsp?mode=login";
    }
    if(mode === "notlog"){
        messaggio.innerHTML = "Devi essere loggato per poter accedere a quella funzione.";
        url="./index.jsp";
    }
    if(mode === "updinf"){
        messaggio.innerHTML = "Profilo utente modificato con successo.";
        url="./index.jsp";
    }
    if(mode === "updpwd"){
        messaggio.innerHTML = "Password modificata con successo.";
        url="./index.jsp";
    }
    if(mode === "restricted"){
        messaggio.innerHTML = "Il tuo account utente non possiede i permessi "+
                "necessari per visualizzare questa pagina.";
        url="./index.jsp";
    }
    //prendo il parametro err dal get
    var err = $(document).getUrlParam("err");
    if(err === "r1"){
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi."; 
        url="./login.jsp?mode=forgot";
    }
    if(err === "s1"){
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi."; 
        url="./login.jsp?mode=signin";
    }
    redirect.href=url;
    //dopo 5 secondi esegue il redirect
    setTimeout(function () {
       window.location.href = url;
    }, 5000);
});