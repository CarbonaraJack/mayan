/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    stampa(oggetti);
});

function stampa(oggetti) {
    for (var i = 0; i < oggetti.length; i++) {
        console.log(oggetti[i]);
        $("#articoli").append("<div class='rigaItem' id='rigaItem" + oggetti[i].idItem + "'></div>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='containerImgItem' id='containerImgItem" + oggetti[i].idItem + "'></div>");
        $("#containerImgItem" + oggetti[i].idItem).append("<img class='itemImage' src='img/"+oggetti[i].immagine+"'/>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='nome'>" + oggetti[i].nome + "</div>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='produttore'>Prodotto da: " + oggetti[i].produttore + "</div>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='venditore'>Venduto da: " + oggetti[i].venditore + "</div>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='quantità'>Pezzi: " + oggetti[i].quantita + "</div>");
        $("#rigaItem" + oggetti[i].idItem).append("<div class='prezzo'>" + oggetti[i].prezzo + "EUR</div>");
    }
}