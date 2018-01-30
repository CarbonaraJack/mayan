$(document).ready(function(){
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
    
    stampaRecensioniItem(recensioniItem);
    
});

function stampaRecensioniItem(lista){
    var s = "<div class='rigaTit'>" +
            "<div class='titRecensione'>Recensione</div>" +
            "<div class='titValutazione'>Valutazione</div>" +
            "<div class='titAutore'>Autore</div>" +
            "<div class='titItem'>Item</div>" +
            "<div class='titAzioni'>Azioni</div>" +
            "</div>";

    if ((!lista) || (lista.length <= 0)) {
        s = s + "<div>Non ci sono recensioni da mostrare</div>";
    } else {
        for (var i = 0; i < lista.length; i++) {
            s = s + "<div class='rigaItem'>";
                    s = s + "<div class='itemRecensione'>" + lista[i].testo + "</div>";
                    s = s + "<div class='itemValutazione'>" + lista[i].stelline + "</div>";
                    s = s + "<div class='itemAutore'>" + lista[i].cognomeAutore + " " + lista[i].nomeAutore + "</div>";
                    s = s + "<div class='itemItem'><a href='controlloCarrello?del=true&idDel=" + lista[i].idItem + "&idNeg=" + lista[i].idVenditore + "'>"+lista[i].nomeItem+"</a></div>";
                if ((lista[i].idRispRec === null) || (lista[i].idRispRec <= 0)) {
                    s = s + "<div class='itemAzioni'><button onclick=\'risposta(\"" + lista[i].nomeItem + "\"," + lista[i].idRecensione + ")\'>Rispondi</button></div>";
                }
            s = s + "</div>"; 
        }
    }
    document.getElementById("tabList").innerHTML = s;
}

function stampaRecensioniNegozi(lista){
    var s = "<div class='rigaTit'>" +
            "<div class='titRecensione'>Recensione</div>" +
            "<div class='titValutazione'>Valutazione</div>" +
            "<div class='titAutore'>Autore</div>" +
            "<div class='titItem'>Negozio</div>" +
            "<div class='titAzioni'>Azioni</div>" +
            "</div>";

    if ((!lista) || (lista.length <= 0)) {
        s = s + "<div>Non ci sono recensioni da mostrare</div>";
    } else {
        for (var i = 0; i < lista.length; i++) {
            s = s + "<div class='rigaItem'>";
                    s = s + "<div class='itemRecensione'>" + lista[i].testo + "</div>";
                    s = s + "<div class='itemValutazione'>" + lista[i].stelline + "</div>";
                    s = s + "<div class='itemAutore'>" + lista[i].cognomeAutore + " " + lista[i].nomeAutore + "</div>";
                    s = s + "<div class='itemItem'><a href='controlloCarrello?del=true&idDel=" + lista[i].idItem + "&idNeg=" + lista[i].idVenditore + "'>"+lista[i].nomeNegozio+"</a></div>";
                if ((lista[i].idRispRec === null) || (lista[i].idRispRec <= 0)) {
                    s = s + "<div class='itemAzioni'><button onclick='risposta(" + lista[i].nomeNegozio + "," + lista[i].idRecensione + ")'>Rispondi</button></div>";
                }
            s = s + "</div>"; 
        }
    }
    document.getElementById("tabList").innerHTML = s;
}

function setItem(){
    stampaRecensioniItem(recensioniItem);
}

function setNegozi(){
    stampaRecensioniNegozi(recensioniNegozi);
}

function apriPopup() {
    var popup = document.getElementById("bgFader");
    popup.style.display = "block";
}
function risposta(nome, idRec) {
    apriPopup();
    document.getElementById("testoRecensione").value = "";
    document.getElementById("messaggioRecensione").style.display = "none";
    var titolo = document.getElementById("titoloPopup");
    var idRecensione = document.getElementById("idRecensione");
    var modeRecensione = document.getElementById("modeRecensione");
    titolo.innerHTML = "Risposta per " + nome;
    modeRecensione.value = "item";
    idRecensione.value = idRec;
}
function validaForm() {
    document.getElementById("testoRecensione").value =
            criptaStringa(document.getElementById("testoRecensione").value);
    return true;
}
function criptaStringa(stringa) {
    return nuovoNomeProd = encodeURIComponent(nuovoNomeProd.trim());
}


