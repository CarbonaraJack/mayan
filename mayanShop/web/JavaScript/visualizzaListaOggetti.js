/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    for (var i = 0; i < oggetti.length; i++) {
        var s = "<div class='itemBox' id='item" + oggetti[i].idItem + "'>";
        //s = s + "<div class='itemImageContainer'>" + "<a href='DisplayObject.jsp?index=" + i + "'>" + "<img class='itemImage' src='img/000001.jpg'/></a></div>";
        //s = s + "<div class='itemName'><a href='DisplayObject.jsp?index=" + i + "'>" + oggetti[i].nome + "</a></div>";
        s = s + "<div class='itemImageContainer'>" + "<a href='controlloItems?ric=false&objS=true&idOgg=" + i + "'>" + "<img class='itemImage' src='img/000001.jpg'/></a></div>";
        s = s + "<div class='itemName'><a href='controlloItems?ric=false&objS=true&idOgg=" + i + "'>" + oggetti[i].nome + "</a></div>";
        s = s + "<div class='itemProduttore'>" + oggetti[i].produttore + "</div>";
        s = s + "<div class='itemPrice'>" + oggetti[i].prezzo + "€</div>";

        s = s + "<div class='itemStars'>";
        var stars = oggetti[i].voto;
        for (var j = 1; j <= 5; j++) {
            if (j <= stars) {
                s = s + "<span class='fa fa-star checked'></span>";
            } else {
                s = s + "<span class='fa fa-star'></span>";
            }
        }
        s = s + "</div>";

        s = s + "</div>";
        $("#containerItem").append(s);
    }
});