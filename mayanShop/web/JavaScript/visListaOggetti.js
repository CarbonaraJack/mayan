/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    for (var i = 0; i < oggetti.length; i++) {
        $("#containerItem").append("<div class='itemBox' id='item" + oggetti[i].idItem + "'></div>");
        
        $("#item" + oggetti[i].idItem).append("<div class='itemImageContainer' id='image" + oggetti[i].idItem + "'></div>");
        $("#image" + oggetti[i].idItem).append("<a href='controlloItems?idOgg=" + oggetti[i].idItem + "'><img class='itemImage' src='img/"+oggetti[i].immagine+"'/></a>");
        $("#item" + oggetti[i].idItem).append("<div class='itemName'><a href='controlloItems?idOgg=" + oggetti[i].idItem + "'>" + oggetti[i].nome + "</a></div>");
        $("#item" + oggetti[i].idItem).append("<div class='itemProduttore'>" + oggetti[i].produttore + "</div>");
        $("#item" + oggetti[i].idItem).append("<div class='itemPrice'>" + oggetti[i].prezzoMinimo + "€</div>");

        $("#item" + oggetti[i].idItem).append("<div class='itemStars' id='itemStars" + oggetti[i].idItem + "'>");
        var stars = oggetti[i].voto;
        for (var j = 1; j <= 5; j++) {
            if (j <= stars) {
                $("#itemStars" + oggetti[i].idItem).append("<span class='fa fa-star checked'></span>");
            } else {
                $("#itemStars" + oggetti[i].idItem).append("<span class='fa fa-star'></span>");
            }
        }
    }
});
