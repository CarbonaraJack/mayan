$(document).ready(function () {
    if (userId === 0) {
        window.location = './alert.jsp?mode=notlog';
    }
    //prendo il parametro err dal get
    var err = $(document).getUrlParam("err");
    var updateMessage = document.getElementById("updateMessage");
    var passwordMessage = document.getElementById("passwordMessage");
    if (err === "u1") {
        updateMessage.style.display = "inline";
        updateMessage.innerHTML = "Nessuna informazione da aggiornare.";
    }
    if (err === "u2") {
        updateMessage.style.display = "inline";
        updateMessage.innerHTML = "Errore nell'aggiornamento dei dati.<br>Riprova pi\ù tardi.";
    }
    if (err === "p1") {
        passwordMessage.style.display = "inline";
        passwordMessage.innerHTML = "Errore nell'aggiornamento della password.<br>Riprova pi\ù tardi.";
    }
});
var validaPassword = function () {
    var password = document.getElementById("password").value;
    var conferma = document.getElementById("conferma").value;
    var bool = password === conferma;
    if (!bool) {
        var passwordMessage = document.getElementById("passwordMessage");
        passwordMessage.style.display = "inline";
        passwordMessage.innerHTML = "Le password non combaciano.";
    }
    return bool;
}