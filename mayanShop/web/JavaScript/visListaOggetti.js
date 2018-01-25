/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var filtri = [];
var first = true;

$(document).ready(function () {
//$( window ).on( "load", function() {
    /*
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
    }*/
    /*
    if(first===true){ 
        first=false;
        stampa();
    } else{
        console.log("non primo");
    }*/
    stampa();
});

function addFilter(id){
    var checkBox = document.getElementById(id);

     if (checkBox.checked == true){
        filtri.push(checkBox.value);
        //setStampabili(checkBox.value);
    } else {
        filtri.pop(id);
    }
    //stampa();
}

function setStampabili(id){
    switch(id){
        case "libri":
            for (var i = 0; i < oggetti.length; i++) {
                if(oggetti[i].categoria === "libri"){
                    //oggetti[i].visualizza = tr
                }
            }
    }
}

function stampa(){
    $("#containerItem").empty();
    for (var i = 0; i < oggetti.length; i++) {
        if(oggetti[i].visualizza === true){
            stampaOgg(oggetti[i]);
        }
    }
}

function stampaOgg(oggetto){
    $("#containerItem").append("<div class='itemBox' id='item" + oggetto.idItem + "'></div>");
    
    $("#item" + oggetto.idItem).append("<div class='itemImageContainer' id='image" + oggetto.idItem + "'></div>");
    $("#image" + oggetto.idItem).append("<a href='controlloItems?idOgg=" + oggetto.idItem + "'><img class='itemImage' src='img/"+oggetto.immagine+"'/></a>");
    $("#item" + oggetto.idItem).append("<div class='itemName'><a href='controlloItems?idOgg=" + oggetto.idItem + "'>" + oggetto.nome + "</a></div>");
    $("#item" + oggetto.idItem).append("<div class='itemProduttore'>" + oggetto.produttore + "</div>");
    $("#item" + oggetto.idItem).append("<div class='itemPrice'>" + oggetto.prezzoMinimo + "€</div>");

    $("#item" + oggetto.idItem).append("<div class='itemStars' id='itemStars" + oggetto.idItem + "'>");
    var stars = oggetto.voto;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            $("#itemStars" + oggetto.idItem).append("<span class='fa fa-star checked'></span>");
        } else {
            $("#itemStars" + oggetto.idItem).append("<span class='fa fa-star'></span>");
        }
    }
}

function ordinaPrezzo(){
    console.log(oggetti);
    oggetti.sort(function(a, b) {
        return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
    });
    console.log(oggetti);
    stampa();
}

function ordinaValutazione(){
    console.log(oggetti);
    oggetti.sort(function(a, b) {
        return parseFloat(a.voto) - parseFloat(b.voto);
    });
    console.log(oggetti);
    stampa();
}
