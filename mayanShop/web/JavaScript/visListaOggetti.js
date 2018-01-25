/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var filtriCat = [];
var filtriReg = [];
var first = true;

$(document).ready(function () {
    setStampabili();
    
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
        setStampabili();
    } else {
        filtriCat.pop(id);
        setStampabili();
    }
}

function addFilterReg(id){
    var checkBox = document.getElementById(id);

     if (checkBox.checked == true){
        filtriReg.push(checkBox.value);
        setStampabili();
    } else {
        filtriReg.pop(id);
        setStampabili();
    }
}

function checkReg(oggetto){
    if(filtriReg.length == 0){
        return true;
    }
    for (var j = 0; j < filtriReg.length; j++) {
        for(var z = 0; z < oggetto.regioni.length; z++){
            if(oggetto.regioni[z] === filtriReg[j]){
                return true;
            }
        }
    }
    return false;
}

function checkCat(oggetto){
    if(filtriCat.length == 0){
        return true;
    }
    for (var j = 0; j < filtriCat.length; j++) {
        if(oggetto.categoria === filtriCat[j]){
            return true;
        }
    }
    return false;
}

function setStampabili(){
    $("#containerItem").empty();
    for (var i = 0; i < oggetti.length; i++) {
        if((checkCat(oggetti[i])===true) && (checkReg(oggetti[i])===true)){
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
    setStampabili();
}

function ordinaValutazione(){
    oggetti.sort(function(a, b) {
        return parseFloat(a.voto) - parseFloat(b.voto);
    });
    setStampabili();
}
