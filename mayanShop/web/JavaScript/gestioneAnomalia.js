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
        document.getElementById("rifIdMess").value= messaggio.id_messaggio;
            if(messaggio.stato === "chiusa"){
                s += "<p>La segnalazione è stata chiusa</p>";
            } 
        s = s + "</div>";
        
    }

    document.getElementById("tabMessaggio").innerHTML = s;
});

var validaRisp = function () {
    return true;
}

