/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    if ((!negozio) || (negozio.length <= 0) || (negozio.idNegozio !== negozio)){
        window.location.replace("./controlloNegozi?idNegozio=" + idRequest);
    } else {
        stampaNegozio();
    }
});

function stampaNegozio(){
    $("#containerNegozio").append("<div class='negImage' id='negImage'></div>");
    if((!negozio.foto) || (negozio.foto.length <= 0)){
        $("#negImage").append("Nessuna immagine da mostrare");
    } else {
        $("#negImage").append("<img class='image' src='img/" + negozio.foto[0] + "' alt='Foto " + negozio.nome + "'/>");
    }
    
    $("#containerNegozio").append("<div class='negInformazioni' id='negInformazioni'></div>");
    $("#negInformazioni").append("<div class='negNome'>" + negozio.nome + "</div>");
    $("#negInformazioni").append("<div class='negLink'><a href='" + negozio.webLink + "' target='_blank'>Vai al sito</a></div>");
    $("#negInformazioni").append("<div class='negOrari'>Orari: " + negozio.orari + "</div>");
    $("#negInformazioni").append("<div class='negDescrizione'>" + negozio.descrizione + "</div>");
    
    if ((!negozio.items) || (negozio.items.length <= 0)) {
        $("#containerNegozio").append("<div class='negProdotti'> Questo negozio non ha item disponibili</div>");
    } else {
        for (var i = 0; i < negozio.items.length; i++) {
            $("#containerNegozio").append("<div class='rigaProdotti' id='rigaProdotti" + negozio.items[i].idItem + "'></div>");
            $("#rigaProdotti" + negozio.items[i].idItem).append("<div class='nomeProdotto'><a href='controlloItems?idOgg=" + negozio.items[i].idItem + "'>" + negozio.items[i].nome + "</a></div>");
            $("#rigaProdotti" + negozio.items[i].idItem).append("<div class='prezzoProdotti'>Prezzo: " + negozio.items[i].prezzoMinimo + "€</div>");
        }
    }
    
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
}
