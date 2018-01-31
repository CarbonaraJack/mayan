$(document).ready(function () {
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
    var s = //"<div class='titTransazione'>Id Transazione </div" +
            "<div class='titItem rigaTit'>Item</div>" +
            "<div class='prezzo rigaTit'>Prezzo</div>" +
            "<div class='quantita rigaTit'>Quantità</div>";

    if ((!lista) || (lista.length <= 0)) {
        s = s + "<div>Non hai ancora fatto acquisti</div>";
    } else {
        for (var i = 0; i < lista.length; i++) {
            //s = s + "<div class='transazione'>" + lista[i].idTransazione;
            s = s + "<div class='item rigaItem'>";
            s = s + "<div class='itemImage'>"
            s = s + "<img class='thumb' src='img/" + lista[i].linkFoto + "'>";
            s = s + "</div>"
            s = s + "<div class='itemNome'><a href='controlloItems?ric=false&objS=true&idOgg=" + lista[i].idItem + "'>" + lista[i].nomeItem + "</a></div>";
            s = s + "<div class='itemInfo'>Acquistato da: " + lista[i].nomeNegozio + "<br>In data: " + lista[i].dataora + "</div>";
            //Inserire codicer per pulsante
            s = s + "<div class='itemAzioni'>";
                    if(listaCheckR[i] == '1'){
                        s = s + "<span>Oggetto già recensito</span>";
                    } else {
                        s = s + "<button onclick=\'recensisciItem(" + lista[i].idItem + ",\"" + lista[i].nomeItem + "\");\' "
                            + " class=\"headerBarButton\">"
                            + "Recensisci Oggetto</button>";
                    }
                    if(listaCheckV[i] == '1'){
                        s = s + "<span>Venditore già recensito</span>";
                    } else {
                        s = s + "<button onclick=\'recensisciVenditore(" + lista[i].idNegozio + ",\"" + lista[i].nomeNegozio + "\");\' "
                            + " class=\"headerBarButton\">"
                            + "Recensisci Venditore</button>";
                    }                    
                    if(listaCheck[i] == '1'){
                        s = s + "<span>Segnalazione già inviata</span>";
                    } else {
                        s = s + "<button onclick=\"segnalaAnomalia("
                            + lista[i].idNegozio +", "
                            + lista[i].idTransazione
                            + ");\" class=\"headerBarButton\">"
                            + "Segnala un problema</button>";
                    }
                    s = s + "</div>";
            s = s + "</div>";
            s = s + "<div class='prezzo rigaItem'><div>" + lista[i].prezzo + "€</div></div>";
            s = s + "<div class='quantita rigaItem'><div>" + lista[i].quantità + "</div></div>";
        }
    }
    document.getElementById("tabAcquisti").innerHTML = s;
});
function apriPopup() {
    var popup = document.getElementById("bgFader");
    popup.style.display = "block";
}
function recensisciItem(idItem, nomeItem) {
    apriPopup();
    showRecensione();
    document.getElementById("testoRecensione").value = "";
    document.getElementById("messaggioRecensione").style.display = "none";
    var titolo = document.getElementById("titoloPopup");
    var idRecensione = document.getElementById("idRecensione");
    var modeRecensione = document.getElementById("modeRecensione");
    titolo.innerHTML = "Recensione per " + nomeItem;
    modeRecensione.value = "item";
    idRecensione.value = idItem;
}
function recensisciVenditore(idNegozio, nomeNegozio) {
    apriPopup();
    showRecensione();
    document.getElementById("testoRecensione").value = "";
    document.getElementById("messaggioRecensione").style.display = "none";
    var titolo = document.getElementById("titoloPopup");
    var idRecensione = document.getElementById("idRecensione");
    var modeRecensione = document.getElementById("modeRecensione");
    titolo.innerHTML = "Recensione per " + nomeNegozio;
    modeRecensione.value = "negozio";
    idRecensione.value = idNegozio;
}
function segnalaAnomalia(idNegozio,idTransazione) {
    apriPopup();
    showSegnalazione();
    document.getElementById("idNegozio").value=idNegozio;
    document.getElementById("idTransazione").value=idTransazione;
    document.getElementById("testoSegnalazione").value="";
}
function showSegnalazione() {
    document.getElementById("formSegnalazione").style.display = "block";
    document.getElementById("formRecensione").style.display = "none";
}
function showRecensione() {
    document.getElementById("formSegnalazione").style.display = "none";
    document.getElementById("formRecensione").style.display = "block";
}
function validaForm() {
    if ($('input[name=rating]:checked').length > 0) {
        document.getElementById("testoRecensione").value =
                criptaStringa(document.getElementById("testoRecensione").value);
        return true;
    } else {
        document.getElementById("messaggioRecensione").style.display = "block";
        return false;
    }
}
function validaSegnalazione() {
    document.getElementById("testoSegnalazione").value =
            criptaStringa(document.getElementById("testoSegnalazione").value);
    return true;
}
function criptaStringa(stringa) {
    return stringa = encodeURIComponent(stringa.trim());
}