/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function (){                           
         
         var s="";
        if (messaggio == null){
            s = s + "<div>C'è stato un errore</div>";
        } else {
            if(messaggio.tipo == "anomalia")
        
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
                    s += "<form action=\"/uploadMessage\" method=\"POST\">\n";
                    s += "Risposta: <input type=\"text\" name=\"risposta\" id=\"risposta\"><br>\n";
                    s += "<input type=\"submit\" value=\"Rispondi\">\n";
                    s += "</form>\n";
                s = s + "</div>";
                
            }
        }
        document.getElementById("tabNotifiche").innerHTML = s;
});

