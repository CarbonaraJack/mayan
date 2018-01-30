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
        s = s + "<div class='itemMessaggio'>";
        s += "<p>Tipo di messaggio: " + messaggio.tipo + "</p>\n";
        s += "<br>\n";
        s += "<p>Mittente: " + messaggio.nomeMittente + "</p>\n";
        s += "<br>\n";
        s += "<p>Destinatario: " + messaggio.nomeDestinatario + "</p>\n";
        s += "<br>\n";
        s += "<p>Testo: " + messaggio.descrizione + "</p>\n";
        s += "<br>\n";
        s += "<div>";
            s += "<form action='./risSegnalazione' onsubmit=\"return validaRisp();\" method=\"POST\">\n";
            s += "Risposta:<br>";
            s += "<textarea id=\"risposta\" name=\"risposta\"></textarea>\n";
            s += "<input type=\"hidden\" name=\"idMessaggio\" id=\"rispIdMess\"/>"
            s += "<input type=\"submit\" value=\"Rispondi\">\n";
            s += "</form>\n";
        s = s + "</div>";
        
    }

    document.getElementById("tabMessaggio").innerHTML = s;
});

var validaRisp = function () {
    return true;
}