$(document).ready(function () {
    //prendo il parametro mode dal get
    var mode = $(document).getUrlParam("mode");
    var url = "./index.jsp";
    var messaggio = document.getElementById("messaggio");
    var redirect = document.getElementById("redirect");
    //prendo il parametro err dal get
    var err = $(document).getUrlParam("err");
    if (mode === "reset") {
        messaggio.innerHTML = "Password reimpostata con successo.";
        url = "./login.jsp?mode=login";
    }
    if (mode === "login") {
        var userName = document.getElementById("userName").value;
        messaggio.innerHTML = "Login eseguito con successo. Benvenuto " + userName;
        url = "./index.jsp";
    }
    if (mode === "logout") {
        var userName = document.getElementById("userName").value;
        messaggio.innerHTML = "Logout eseguito con successo.";
        url = "./index.jsp";
    }
    if (mode === "signin") {
        messaggio.innerHTML = "Registrazione utente eseguita con successo.";
        url = "./login.jsp?mode=login";
    }
    if (mode === "notlog") {
        messaggio.innerHTML = "Devi essere loggato per poter accedere a quella funzione.";
        url = "./index.jsp";
    }
    if (mode === "updinf") {
        messaggio.innerHTML = "Profilo utente modificato con successo.";
        url = "./index.jsp";
    }
    if (mode === "updpwd") {
        messaggio.innerHTML = "Password modificata con successo.";
        url = "./index.jsp";
    }
    if (mode === "restricted") {
        messaggio.innerHTML = "Il tuo account utente non possiede i permessi " +
                "necessari per visualizzare questa pagina.";
        url = "./index.jsp";
    }
    if (mode === "editforbidden") {
        messaggio.innerHTML = "Il tuo account utente non possiede i permessi " +
                "necessari per modificare quell'oggetto.";
        url = "./index.jsp";
    }
    if (mode === "editnegozio") {
        if (err == "n1") {
            messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        } else {
            messaggio.innerHTML = "Aggiornamento dei dati del negozio"
                    + " eseguito con successo.";
        }
        url = "./modificaNegozi.jsp";
    }
    if (mode === "insertnegozio") {
        url = "./modificaNegozi.jsp";
        if (err === "i1") {
            messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        } else if (err === "i2") {
            messaggio.innerHTML = "Non hai i permessi per inserire un negozio.";
            url = "./index.jsp";
        } else if (err === "i3") {
            messaggio.innerHTML = "Non hai i permessi per modificare " +
                    "la foto di questo negozio.";
            url = "./index.jsp";
        } else {
            messaggio.innerHTML = "Inserimento del negozio"
                    + " eseguito con successo.";
        }
    }
    if (mode === "insertFoto") {
        var id = $(document).getUrlParam("id");
        url = "./index.jsp";
        if (err === "f1") {
            messaggio.innerHTML = "C\'\è stato un problema nel caricamento di" +
                    " una o pi\ù foto.";
        } else {
            if (id != null) {
                url = "./editItem?mode=edit&item=" + id;
            }
            messaggio.innerHTML = "Foto caricata\\e con successo.";
        }
    }
    if (mode === "deleteFoto") {
        url = "./index.jsp";
        if (err === "f1") {
            messaggio.innerHTML = "Il tuo utente non ha i permessi per" +
                    " cancellare quella foto.";
        } else if (err === "f2") {
            messaggio.innerHTML =
                    "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        } else {
            messaggio.innerHTML = "Foto cancellata con successo.";
        }
        var id = $(document).getUrlParam("id");
        if (id != null) {
            url = "./editItem?mode=edit&item=" + id;
        }
    }
    if (mode === "newitem") {
        var id = $(document).getUrlParam("id");
        if (id != null) {
            url = "./editItem?mode=edit&item=" + id;
        } else {
            url = "./editItemList"
        }
        messaggio.innerHTML = "Oggetto inserito con successo.";
    }
    if (mode === "thumb") {
        var id = $(document).getUrlParam("id");
        url = "./editItem?mode=edit&item=" + id;
        messaggio.innerHTML = "Thumbnail aggiornata con successo.";
    }
    if (mode === "upditem") {
        var id = $(document).getUrlParam("id");
        url = "./editItem?mode=edit&item=" + id;
        messaggio.innerHTML = "Oggetto aggiornato con successo.";
    }
    if (mode === "stock") {
        var id = $(document).getUrlParam("id");
        url = "./editItem?mode=edit&item=" + id;
        messaggio.innerHTML = "Stocks aggiornati con successo.";
    }
    if (mode === "recensione"){
        url = "./index.jsp";
        messaggio.innerHTML = "Grazie per aver inserito la tua recensione.";
    }
    if (mode === "aggiornaLoc") {
        url = "./index.jsp";
        messaggio.innerHTML = "Indirizzo inserito con successo.";
    }
    if (mode === "risSegn") {
        url = "./index.jsp";
        messaggio.innerHTML = "Risposta inviata correttamente.";
    }
    if (mode === "generic") {
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        url = "./index.jsp";
    }

    if (err === "r1") {
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        url = "./login.jsp?mode=forgot";
    }
    if (err === "s1") {
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        url = "./login.jsp?mode=signin";
    }
    if (err === "s1") {
        messaggio.innerHTML = "Qualcosa \è andato storto. Riprova pi\ù tardi.";
        url = "./login.jsp?mode=signin";
    }
    redirect.href = url;
    //dopo 5 secondi esegue il redirect
    setTimeout(function () {
        window.location.href = url;
    }, 5000);
});
