/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    var s = "";
    if (messaggio == null) {
        s = s + "<div>C'è stato un errore</div>";
    } else {
        
        document.getElementById("rispIdMess").value = idMessaggio;
        document.getElementById("rifIdMess").value = idMessaggio;
        s = s + "<div class='itemMessaggio'>";
        s += "<p>Tipo di messaggio: " + messaggio.tipo + "</p>\n";
        s += "<br>\n";
        s += "<p>Mittente: " + messaggio.nomeMittente + "</p>\n";
        s += "<br>\n";
        s += "<p>Destinatario: " + messaggio.nomeDestinatario + "</p>\n";
        s += "<br>\n";
        s += "<p>Transazione: " + messaggio.id_transazione + "</p>\n";
        s += "<br>\n";
        s += "<p>Testo: " + messaggio.descrizione + "</p>\n";
        s += "<br>\n";
        //Aggiungere pulsanti per la gestione e apparizione del form

        s += "<button type=\"button\" onclick=\"showForm();\"id=\"respingi\" value >"

        s += "<form action=\"/uploadMessage\" method=\"POST\">\n";
        s += "Risposta: <input type=\"text\" name=\"risposta\" id=\"risposta\"><br>\n";
        s += "<input type=\"submit\" value=\"Rispondi\">\n";
        s += "</form>\n";
        s = s + "</div>";
        
    }

    document.getElementById("tabNotifiche").innerHTML = s;
});

var showRisp = function () {
    document.getElementById("formRisposta").style.display = "block";
    document.getElementById("formRifiuto").style.display = "none";
}
var showDeny = function () {
    document.getElementById("formRisposta").style.display = "none";
    document.getElementById("formRifiuto").style.display = "block";
}

var validaRisp = function () {
    return false;
}