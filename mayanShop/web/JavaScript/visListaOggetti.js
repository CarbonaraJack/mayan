/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var filtriCat = [];
var filtriReg = [];
var filtriVal = null;
var filtriDist = null;
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
    
    var sliderVal = document.getElementById("sliderValutazione");
    var outputVal = document.getElementById("labelValutazione");
    outputVal.innerHTML = sliderVal.value;
    
    sliderVal.oninput = function() {
        outputVal.innerHTML = this.value;
        filtriVal = this.value;
        setStampabili();
    };
    
    var sliderDist = document.getElementById("sliderDistanza");
    var outputDist = document.getElementById("labelDistanza");
    outputDist.innerHTML = sliderDist.value;
    
    sliderDist.oninput = function() {
        outputDist.innerHTML = this.value;
        filtriDist = this.value;
        setStampabili();
    };
    
    var radiosDistanza = document.querySelectorAll("input[type=radio][name='radioDistanza']");
    function changeHandlerDist(event) {
        if ( this.value === "decr" ) {
            console.log("decr");
            getPosition("decr");
            
        } else if ( this.value === "cresc" ) {
            console.log("cresc");
            getPosition("cresc");
        }  
    }
    Array.prototype.forEach.call(radiosDistanza, function(radio) {
        radio.addEventListener("change", changeHandlerDist);
    });
    
    var radiosPrezzo = document.querySelectorAll("input[type=radio][name='radioPrezzo']");
    function changeHandlerPrezzo(event) {
        if ( this.value === "decr" ) {
            ordinaPrezzoDecr();
            
        } else if ( this.value === "cresc" ) {
            ordinaPrezzoCresc();
        }  
    }
    Array.prototype.forEach.call(radiosPrezzo, function(radio) {
        radio.addEventListener("change", changeHandlerPrezzo);
    });
    
    var radiosValutazione = document.querySelectorAll("input[type=radio][name='radioValutazione']");
    function changeHandlerValutazione(event) {
        if ( this.value === "decr" ) {
            ordinaValutazioneDecr();
            
        } else if ( this.value === "cresc" ) {
            ordinaValutazioneCresc();
        }  
    }
    Array.prototype.forEach.call(radiosValutazione, function(radio) {
        radio.addEventListener("change", changeHandlerValutazione);
    });
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

function checkVal(oggetto){
    var estremoSup = parseInt(filtriVal)+1;
    if(filtriVal===null){
        return true;
    }
    if((oggetto.voto >= filtriVal) && (oggetto.voto < estremoSup)){
        return true;
    }
    return false;
}

function checkDist(oggetto){
    if(filtriDist===null){
        return true;
    }
    return false;
}

function setStampabili(){
    $("#containerItem").empty();
    for (var i = 0; i < oggetti.length; i++) {
        if((checkCat(oggetti[i])===true) && (checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true)){
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

function ordinaPrezzoCresc(){
    oggetti.sort(function(a, b) {
        return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
    });
    setStampabili();
}

function ordinaPrezzoDecr(){
    oggetti.sort(function(a, b) {
        return parseFloat(b.prezzoMinimo) - parseFloat(a.prezzoMinimo);
    });
    setStampabili();
}

function ordinaValutazioneCresc(){
    oggetti.sort(function(a, b) {
        return parseFloat(a.voto) - parseFloat(b.voto);
    });
    setStampabili();
}

function ordinaValutazioneDecr(){
    oggetti.sort(function(a, b) {
        return parseFloat(b.voto) - parseFloat(a.voto);
    });
    setStampabili();
}

function reset() {
    filtriCat = [];
    filtriReg = [];
    filtriVal = null;
    setStampabili();
    
    document.getElementById("checkLazio").checked = false;
    document.getElementById("checkLombardia").checked = false;
    document.getElementById("checkTrentino").checked = false;
    document.getElementById("checkVeneto").checked = false;
    
    document.getElementById("checkLibri").checked = false;
    document.getElementById("checkElettronica").checked = false;
    document.getElementById("checkAbbigliamento").checked = false;
    document.getElementById("checkGiardinaggio").checked = false;
    document.getElementById("checkCasalinghi").checked = false;
}


function calcolaDistanza(myLat, myLong, lat, long){
    var distanza = 6366*Math.acos(Math.cos(degrees_to_radians(myLat))*Math.cos(degrees_to_radians(lat))*
            Math.cos(degrees_to_radians(long)-degrees_to_radians(myLong))+Math.sin(degrees_to_radians(myLat))*Math.sin(degrees_to_radians(lat)));
   /* 6366 *
   acos(cos(radians(lat)) * 
   cos(radians(Latitudine)) * 
   cos(radians(Longitudine) - 
   radians(lng)) + 
   sin(radians(lat)) * 
   sin(radians(Latitudine )))*/
   return distanza;
}

function degrees_to_radians(degrees)
{
    var pi = Math.PI;
    return degrees * (pi/180);
}

function getPosition(ordinamento){
    if (navigator.geolocation) {
        if(ordinamento==="cresc"){
            navigator.geolocation.getCurrentPosition(ordinaDistanzaCresc);
        } else if (ordinamento==="decr"){
            navigator.geolocation.getCurrentPosition(ordinaDistanzaDecr);
        }
        
    } else { 
        console.log("Geolocation is not supported by this browser.");
    }
}

function ordinaDistanzaCresc(position){
    for (var i = 0; i < oggetti.length; i++) {
        oggetti[i].negozi.sort(function(a, b) {
           return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine);
        });
        //sconsole.log(oggetti[i].negozi);
    }
    oggetti.sort(function(a, b) {
        return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine);
    });
    //console.log(oggetti);
    setStampabili();
}

function ordinaDistanzaDecr(position){
    for (var i = 0; i < oggetti.length; i++) {
        oggetti[i].negozi.sort(function(a, b) {
           return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine);
        });
    }
    oggetti.sort(function(a, b) {
        return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine);
    });
    setStampabili();
}