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
    
//    if ((!negozio.item) || (negozio.item.length <= 0)) {
//        $("#containerProdotti").append("<div class='negProdotti'> L'item non è disponibile in nessun negozio</div>");
//    } else {
//        for (var i = 0; i < negozio.item.length; i++) {
//            $("#containerProdotti").append("<div class='negProdotti' id='rigaProdotti'>" + negozio.item[i].idItem + "</div>");
//            $("#containerProdotti" + negozio.item[i].idItem).append("<div class='negProdotti' id='rigaProdotti'>" + negozio.item[i].idItem + "</div>");
//            
//            $("#containerNegozi").append("<div class='rigaNegozio' id='rigaNegozio" + oggetto.negozi[i].idNegozio + "'></div>");
//            $("#rigaNegozio" + oggetto.negozi[i].idNegozio).append("<div class='infoNeg' id='infoNeg" + oggetto.negozi[i].idNegozio + "'></div>");
//            $("#infoNeg" + oggetto.negozi[i].idNegozio).append("<div class='nomeNeg'><a href='controlloNegozi?idNegozio=" + oggetto.negozi[i].idNegozio + "'>" + oggetto.negozi[i].nomeNegozio + "</a></div>")
//            $("#infoNeg" + oggetto.negozi[i].idNegozio).append("<div class='prezzoItem'>Prezzo: " + oggetto.negozi[i].prezzo + "€</div>");
//            $("#rigaNegozio" + oggetto.negozi[i].idNegozio).append("<div class='itemCarrello' id='itemCarrello" + oggetto.negozi[i].idNegozio + "'></div>");
//            $("#itemCarrello" + oggetto.negozi[i].idNegozio).append("<form name='acquista' id='acquista" + oggetto.negozi[i].idNegozio + "' action='./controlloCarrello' method='GET'></form>");
//            $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='item' value='"+oggetto.idItem+"'>");
//            $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='negozio' value='"+oggetto.negozi[i].idNegozio+"'>");
//            $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='del' value='false'>");
//            $("#acquista" + oggetto.negozi[i].idNegozio).append("<div class='quantita'><input id='quantita" + oggetto.idItem + oggetto.negozi[i].idNegozio + "' name='quantita" + oggetto.idItem + oggetto.negozi[i].idNegozio + "' min='0' max='"+oggetto.negozi[i].numStock+"' value='0' type='number'></div>")
//            $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='submit' value='Aggiungi al carrello'/>");
//        }
//    }
//    
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
