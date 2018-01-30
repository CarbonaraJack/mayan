$(document).ready(function () {
    var divLista = document.getElementById("tabNotifiche");
    console.log(lista);
    var popup = document.getElementById("bgFader");
    var chiudi = document.getElementById("chiudi");
    chiudi.onclick = function () {
        popup.style.display = "none";
    }
    window.onclick = function (event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    }
    if ((!lista) || (lista.length <= 0)) {
        var container = document.createElement("div");
        container.classList.add("completeRow");
        var div = document.createElement("div");
        div.innerHTML = "<i>Non hai ancora ricevuto nessuna notifica.</i>";
        container.appendChild(div);
        divLista.appendChild(container);
    } else {
        for (var i = 0; i < lista.length; i++) {
            var classeRiga = "rigaLetta";
            //se l'utente è un admin imposto come non letti i messaggi aperti
            if (userType === "amministratore") {
                if (lista[i].stato == "aperta") {
                    classeRiga = "rigaNonLetta";
                }
            } else {
                //se l'utente non è admin segno non letti solo i messaggi non
                //letti
                if (lista[i].letto == "0") {
                    classeRiga = "rigaNonLetta";
                }
            }
            var containerTipo = document.createElement("div");
            var containerMittente = document.createElement("div");
            var containerMessaggio = document.createElement("div");
            containerTipo.classList.add(classeRiga);
            containerMittente.classList.add(classeRiga);
            containerMessaggio.classList.add(classeRiga);
            containerTipo.classList.add("riga" + i);
            containerMittente.classList.add("riga" + i);
            containerMessaggio.classList.add("riga" + i);
            containerTipo.classList.add("tipo");
            containerMittente.classList.add("mittente");
            containerMessaggio.classList.add("descBreve");
            containerMessaggio.id = "ultimoRiga" + i;

            containerTipo.setAttribute("onclick", "showMessage(" + i + ");");
            containerMittente.setAttribute("onclick", "showMessage(" + i + ");");
            containerMessaggio.setAttribute("onclick", "showMessage(" + i + ");");

            containerTipo.setAttribute("onmouseover", "hoverRiga(" + i + ");");
            containerMittente.setAttribute("onmouseover", "hoverRiga(" + i + ");");
            containerMessaggio.setAttribute("onmouseover", "hoverRiga(" + i + ");");

            containerTipo.setAttribute("onmouseout", "deHoverRiga(" + i + ");");
            containerMittente.setAttribute("onmouseout", "deHoverRiga(" + i + ");");
            containerMessaggio.setAttribute("onmouseout", "deHoverRiga(" + i + ");");
            var tipo = document.createElement("div");
            var mittente = document.createElement("div");
            var messaggio = document.createElement("div");
            tipo.innerHTML = lista[i].tipo;
            mittente.innerHTML = lista[i].nomeMittente;
            messaggio.innerHTML = lista[i].descrizione;
            containerTipo.appendChild(tipo);
            containerMittente.appendChild(mittente);
            containerMessaggio.appendChild(messaggio);
            divLista.appendChild(containerTipo);
            divLista.appendChild(containerMittente);
            divLista.appendChild(containerMessaggio);
            /*
             else {
             startLetto = "<p>";
             stopLetto = "</p>";
             }
             
             s = s + "<div class='itemNotifica'>";
             s = s + startLetto + lista[i].tipo + " - " + lista[i].nomeMittente;
             s = s + "<em> " + lista[i].descrizione + "</em> " + stopLetto;
             s = s + "<button onclick=\'callServlet("+lista[i].id_messaggio+", \"" +lista[i].tipo+"\");\'>Apri<//button>";
             s = s + "</div>";
             
             }
             */
        }
    }
});
function deHoverRiga(indice) {
    for (let div of document.getElementsByClassName("riga" + indice)) {
        div.classList.remove("rigaHover");
    }
}
function hoverRiga(indice) {
    for (let div of document.getElementsByClassName("riga" + indice)) {
        div.classList.add("rigaHover");
    }
}
function showMessage(indice) {
    //tolgo la visualizzazione dei vecchi messaggi
    for (let div of document.getElementsByClassName("formMessaggio")) {
        div.parentNode.removeChild(div);
    }
    //imposto la riga come letta
    for (let div of document.getElementsByClassName("riga" + indice)) {
        div.classList.remove("rigaNonLetta");
        div.classList.add("rigaLetta");
        if (userType !== "amministratore") {
            //esegui aggiornamento nel db
            /*
            $.ajax({
                url:'./setLetto',
                data: {idM : idMessaggio},
                type:'POST',
                success:function(result){
                    console.log(result);
                    //cosa fare dopo la chiamata
                    //qui andrebbe il codice di output, ma il server fa solo la query settandolo 'letto'
                    //senza ritornare roba
                    //eventualmente inserire codice che "aggiorna" l'anteprima 
                    //(es. ricontrolla il set per generare il codice)
                }, 
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status);
                    alert(textStatus);
                    alert(errorThrown);
                }       
            });
            */
        }
    }
    var containerMessage = document.createElement("div");
    containerMessage.classList.add("completeRow");
    containerMessage.classList.add("formMessaggio");
    //messaggio completo
    var messageDiv = document.createElement("div");
    containerMessage.appendChild(messageDiv);
    var completeMessage = document.createElement("textarea");
    completeMessage.readOnly = true;
    completeMessage.classList.add("messaggioCompleto");
    messageDiv.appendChild(completeMessage);
    completeMessage.innerHTML = (lista[indice].descrizione);
    //controlli form
    var containerControlli = document.createElement("div");
    if (lista[indice].stato === "aperta") {
        //aggiungo i controlli solo se la segnalazione è aperta
        var bottone = document.createElement("button");
        bottone.classList.add("headerBarButton");
        bottone.innerHTML = "Rispondi";
        bottone.setAttribute("onclick", "rispondi(" + indice + ");");
        containerControlli.appendChild(bottone);
    } else {
        //la segnalazione è chiusa, quindi non aggiungo i controlli
        containerControlli.innerHTML = "<i>Segnalazione chiusa.</i>"
    }
    containerMessage.appendChild(containerControlli);
    //inserisco il form
    var ultimoElem = document.getElementById("ultimoRiga" + indice);
    insertAfter(containerMessage, ultimoElem);

}
/**
 * Funzione che inserisce un nodo dopo un altro usando DOM
 * @param newNode il nodo da inserire
 * @param referenceNode il nodo da usare come riferimento
 */
function insertAfter(newNode, referenceNode) {
    referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

function apriPopup() {
    var popup = document.getElementById("bgFader");
    popup.style.display = "block";
}
function rispondi(indice) {
    apriPopup();
    document.getElementById("idMessaggio").value = lista[indice].id_messaggio;
    document.getElementById("idTransazione").value = lista[indice].id_transazione;
    document.getElementById("idDestinatario").value =lista[indice].id_destinatario;
    document.getElementById("idMittente").value = lista[indice].id_mittente;
    document.getElementById("testoForm").value = "";
    document.getElementById("checkbox").checked = false;
    if (userType === "amministratore") {
        document.getElementById("adminCheck").style.display = "block";
    } else {
        document.getElementById("adminCheck").style.display = "none";
    }
    
}
function criptaStringa(stringa) {
    return stringa = encodeURIComponent(stringa.trim());
}
function valida() {
    var testo = document.getElementById("testoForm");
    testo.value = criptaStringa(testo.value);
    return true;
}
