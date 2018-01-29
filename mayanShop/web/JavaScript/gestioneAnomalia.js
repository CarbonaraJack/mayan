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
        s += "<div class='itemMessaggio'>";
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
        s += "</div>";
        s += "<div>";
            if(messaggio.stato === "chiusa"){
                s += "<p>La segnalazione è stata chiusa</p>";
            } else {
                s += "<button type=\"button\" onclick=\"showRisp();\"id=\"rispondi\">Rispondi</button>" ;
                s += "<button type=\"button\" onclick=\"showDeny();\"id=\"rifiuta\">Rifiuta</button>" ;
            }
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