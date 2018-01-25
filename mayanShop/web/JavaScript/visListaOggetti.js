/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var filtriCat = [];
var first = true;

$(document).ready(function () {
    stampa();
    
    var coll = document.getElementsByClassName("collapsible");
    var i;
    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.maxHeight){
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + "px";
            }
        });
    }
});

function addFilterCat(id){
    var checkBox = document.getElementById(id);

     if (checkBox.checked == true){
        filtriCat.push(checkBox.value);
        setStampabiliCat();
    } else {
        filtriCat.pop(id);
        if (filtriCat.length == 0){
            stampa();
        } else {
            setStampabiliCat();
        }
    }
}

function setStampabiliCat(){
    $("#containerItem").empty();
    for (var i = 0; i < oggetti.length; i++) {
        var stamp = false;
        for (var j = 0; j < filtriCat.length; j++) {
            if(oggetti[i].categoria === filtriCat[j]){
                stamp = true;
            }
        }
        if(stamp === true){
            //var ogg = document.getElementById("item"+oggetti[i].idItem);
            //document.removeChild(ogg);
            //$("#item"+oggetti[i].idItem).remove();
            stampaOgg(oggetti[i]);
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
    oggetti.sort(function(a, b) {
        return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
    });
    stampa();
}

function ordinaValutazione(){
    oggetti.sort(function(a, b) {
        return parseFloat(a.voto) - parseFloat(b.voto);
    });
    stampa();
}
