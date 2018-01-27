/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    if (((!oggettiVis) || (oggettiVis.length <= 0)) && ((!oggettiAcq) || (oggettiAcq.length <= 0))) {
        window.location.replace("./index");
    } else {
        stampa(oggettiVis, "containerItemVis","Vis");
        stampa(oggettiAcq, "containerItemAcq","Acq");
    }
});

function stampa(oggetti,divId,p) {
    for (var i = 0; i < oggetti.length; i++) {
        //$("#containerItem").append("<div class='itemBox' id='item" + oggetti[i].idItem + "'></div>");
        $("#" + divId).append("<div class='itemBox' id='item" + oggetti[i].idItem + p + "'></div>");
        
        $("#item" + oggetti[i].idItem + p).append("<div class='itemImageContainer' id='image" + oggetti[i].idItem + p + "'></div>");
        $("#image" + oggetti[i].idItem + p).append("<a href='controlloItems?idOgg=" + oggetti[i].idItem + "'><img class='itemImage' src='img/"+oggetti[i].immagine+"'/></a>");
        $("#item" + oggetti[i].idItem + p).append("<div class='itemName'><a href='controlloItems?idOgg=" + oggetti[i].idItem + "'>" + oggetti[i].nome + "</a></div>");
        $("#item" + oggetti[i].idItem + p).append("<div class='itemProduttore'>" + oggetti[i].produttore + "</div>");
        $("#item" + oggetti[i].idItem + p).append("<div class='itemPrice'>" + oggetti[i].prezzoMinimo + "€</div>");

        $("#item" + oggetti[i].idItem + p).append("<div class='itemStars' id='itemStars" + oggetti[i].idItem + p + "'>");
        var stars = oggetti[i].voto;
        for (var j = 1; j <= 5; j++) {
            if (j <= stars) {
                $("#itemStars" + oggetti[i].idItem + p).append("<span class='fa fa-star checked'></span>");
            } else {
                $("#itemStars" + oggetti[i].idItem + p).append("<span class='fa fa-star'></span>");
            }
        }
    }
}