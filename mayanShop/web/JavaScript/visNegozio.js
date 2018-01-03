/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var s = "<div class='negImage'>" + "<img class='image' src='img/000001.jpg'/></div>";
    s = s + "<div class='negInformazioni'>";
    s = s + "<div class='negNome'>" + negozio.nome + "</div>";
    s = s + "<div class='negLink'><a href='" + negozio.webLink + "'>Vai al sito</a></div>";
    s = s + "<div class='negOrari'>Orari: " + negozio.orari + "</div>";
            
    s = s + "<div class='negDescrizione'>" + negozio.descrizione + "</div>";
    
    if (negozio.tipo === "online") {
        s = s + "<div class='negTipo'>I prodotti di questo negozio sono disponibili solo online.</div>";
    } else {
        s = s + "<div class='negTipo'>Per i prodotti di questo negozio è possibile il ritiro in negozio.</div>";
    }
              
    s = s + "<div class='negStars'>";
    var stars = negozio.voto;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            s = s + "<span class='fa fa-star checked'></span>";
        } else {
            s = s + "<span class='fa fa-star'></span>";
        }
    }
    s = s + "</div>";

    s = s + "</div>";
            
    s = s + "</div>";
    $("#containerNegozio").append(s);
});
