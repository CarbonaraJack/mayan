/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#containerNegozio").append("<div class='negImage' id='negImage'></div>");
    $("#negImage").append("<img class='image' src='img/" + negozio.foto[0] + "' alt='Foto " + negozio.nome + "'/>");
    
    $("#containerNegozio").append("<div class='negInformazioni' id='negInformazioni'></div>");
    $("#negInformazioni").append("<div class='negNome'>" + negozio.nome + "</div>");
    $("#negInformazioni").append("<div class='negLink'><a href='" + negozio.webLink + "' target='_blank'>Vai al sito</a></div>");
    $("#negInformazioni").append("<div class='negOrari'>Orari: " + negozio.orari + "</div>");
    $("#negInformazioni").append("<div class='negDescrizione'>" + negozio.descrizione + "</div>");
    
    if (negozio.tipo === "online") {
        $("#negInformazioni").append("<div class='negTipo'>I prodotti di questo negozio sono disponibili solo online.</div>");
    } else {
        $("#negInformazioni").append("<div class='negTipo'>Per i prodotti di questo negozio è possibile il ritiro in negozio.</div>");
    }
              
    $("#negInformazioni").append("<div class='negStars' id='negStars'></div>");
    var stars = negozio.valutazioneMedia;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            $("#negStars").append("<span class='fa fa-star checked'></span>");
        } else {
            $("#negStars").append("<span class='fa fa-star'></span>");
        }
    }
});
