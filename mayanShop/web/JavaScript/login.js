$(document).ready(function () {
    //prendo il parametro mode dal get
    var mode = $(document).getUrlParam("mode");
    if(mode === "login"){
        showLogin();
    }
    if(mode === "signin"){
        showSignin(); 
    }
    if(mode === "forgot"){
        showForgot(); 
    }
    //prendo il parametro err dal get
    var err = $(document).getUrlParam("err");
    if(err === "l1"){
        document.getElementById("messaggioErroreLogIn").style.display="block";  
    }
    if(err === "s1"){
        var messaggioErr = document.getElementById("messaggioErroreSignIn");
        messaggioErr.style.display="block";
        messaggioErr.innerHTML = "Indirizzo email gi\à registrato";        
    }
    if(err === "f1"){
        var messaggioErr = document.getElementById("messaggioErroreForgot");
        messaggioErr.style.display="block";
        messaggioErr.innerHTML = "Nessun utente associato a questa mail";        
    }
});

var showLogin = function(){
        document.getElementById("contentSignin").style.display="none";
        document.getElementById("contentForgot").style.display="none";
        document.getElementById("contentLogin").style.display="block";
};
var showSignin = function(){
        document.getElementById("contentSignin").style.display="block";
        document.getElementById("contentLogin").style.display="none";
        document.getElementById("contentForgot").style.display="none";
};
var showForgot = function(){
        document.getElementById("contentSignin").style.display="none";
        document.getElementById("contentLogin").style.display="none";
        document.getElementById("contentForgot").style.display="block";
};

var validaPassword = function(){
    var password = document.getElementById("password").value;
    var conferma = document.getElementById("conferma").value;
    var bool = password === conferma;
    if(!bool){
        document.getElementById("messaggioErroreSignIn").style.display="block";   
    }
    return bool;
}

var validaPasswordForgot = function(){
    var password = document.getElementById("passwordForgot").value;
    var conferma = document.getElementById("confermaForgot").value;
    var bool = password === conferma;
    if(!bool){
        document.getElementById("messaggioErroreForgot").style.display="block";   
    }
    return bool;
}