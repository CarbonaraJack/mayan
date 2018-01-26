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
    
    if (sceltaRicerca === "negozi"){
        initialNegozi()
    } else if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")){
        initialItems();
    }
    
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

/**
 * 
 * @returns {undefined}
 */
function initialItems(){
    $("#filtroCategoria").append("<button class='collapsible' id='collapseCat'>Categoria</button>");
    $("#filtroCategoria").append("<div class='content' id='contentSliderCat'></div>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkLibri' name='categoria' value='Libri' onclick='addFilterCat(0)'> Libri<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkElettronica' name='categoria' value='Elettronica' onclick='addFilterCat(1)'> Elettronica<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkAbbigliamento' name='categoria' value='Abbigliamento' onclick='addFilterCat(2)'> Abbigliamento<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkGiardinaggio' name='categoria' value='Giardinaggio' onclick='addFilterCat(3)'> Giardinaggio<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkCasalinghi' name='categoria' value='Casalinga' onclick='addFilterCat(4)'> Casalinghi<br>");
    
    $("#ordinaPrezzo").append("<button class='collapsible'>Prezzo</button>");
    $("#ordinaPrezzo").append("<div class='content' id='radioRicercaPrezzo'></div>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='decr'> Decrescente<br>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='cresc'> Crescente<br>");
    
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
}

function initialNegozi(){
    console.log("Neg");
}

/**
 * 
 * @param {type} id
 * @returns {undefined}
 */
function addFilterCat(id){
    var checkList = ["checkLibri","checkElettronica","checkAbbigliamento","checkGiardinaggio","checkCasalinghi"];
    var checkBox = document.getElementById(checkList[id]);

     if (checkBox.checked == true){
        filtriCat.push(checkBox.value);
        setStampabili();
    } else {
        filtriCat.pop(checkBox.value);
        setStampabili();
    }
}

/**
 * 
 * @param {type} id
 * @returns {undefined}
 */
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

/**
 * 
 * @param {type} oggetto
 * @returns {Boolean}
 */
function checkReg(oggetto){
    if(filtriReg.length == 0){
        return true;
    }
    if(sceltaRicerca === "negozi"){
        for (var j = 0; j < filtriReg.length; j++) {
            if(oggetto.citta.regione === filtriReg[j]){
                return true;
            }
        }
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")){
        for (var j = 0; j < filtriReg.length; j++) {
            for(var z = 0; z < oggetto.regioni.length; z++){
                if(oggetto.regioni[z] === filtriReg[j]){
                    return true;
                }
            }
        }
    }
    return false;
}

/**
 * 
 * @param {type} oggetto
 * @returns {Boolean}
 */
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

/**
 * 
 * @param {type} oggetto
 * @returns {Boolean}
 */
function checkVal(oggetto){
    var estremoSup = parseInt(filtriVal)+1;
    if(filtriVal===null){
        return true;
    }
    if(sceltaRicerca === "negozi"){
        if((oggetto.valutazioneMedia >= filtriVal) && (oggetto.valutazioneMedia < estremoSup)){
            return true;
        }
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        if((oggetto.voto >= filtriVal) && (oggetto.voto < estremoSup)){
            return true;
        }
    }
    return false;
}

/**
 * 
 * @param {type} oggetto
 * @returns {Boolean}
 */
function checkDist(oggetto){
    if(filtriDist===null){
        return true;
    }/*
    if(sceltaRicerca === "negozi"){
        if((oggetto.valutazioneMedia >= filtriVal) && (oggetto.valutazioneMedia < estremoSup)){
            return true;
        }
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        if((oggetto.voto >= filtriVal) && (oggetto.voto < estremoSup)){
            return true;
        }
    }*/
    return false;
}

/**
 * 
 * @returns {undefined}
 */
function setStampabili(){
    $("#containerItem").empty();
    
    if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")){
        for (var i = 0; i < oggetti.length; i++) {
            if((checkCat(oggetti[i])===true) && (checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true)){
                stampaOggItem(oggetti[i]);
            }
        }
    } else if (sceltaRicerca === "negozi"){
        for (var i = 0; i < oggetti.length; i++) {
            if((checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true)){
                stampaOggNeg(oggetti[i]);
            }
        }
    }
}

/**
 * 
 * @param {type} oggetto
 * @returns {undefined}
 */
function stampaOggItem(oggetto){
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

/**
 * 
 * @param {type} oggetto
 * @returns {undefined}
 */
function stampaOggNeg(oggetto){
    $("#containerItem").append("<div class='itemBox' id='neg" + oggetto.idNegozio + "'></div>");
    
    $("#neg" + oggetto.idNegozio).append("<div class='itemImageContainer' id='image" + oggetto.idNegozio + "'></div>");
    $("#image" + oggetto.idNegozio).append("<a href='controlloNegozi?idNegozio=" + oggetto.idNegozio + "'><img class='itemImage' src='img/"+oggetto.foto[0]+"'/></a>");
    $("#neg" + oggetto.idNegozio).append("<div class='itemName'><a href='controlloNegozi?idNegozio=" + oggetto.idNegozio + "'>" + oggetto.nome + "</a></div>");
    $("#neg" + oggetto.idNegozio).append("<div class='negCitta'>" + oggetto.citta.citta + "</div>");
    $("#neg" + oggetto.idNegozio).append("<div class='negRegione'>" + oggetto.citta.regione + "</div>");

    $("#neg" + oggetto.idNegozio).append("<div class='itemStars' id='negStars" + oggetto.idNegozio + "'>");
    var stars = oggetto.valutazioneMedia;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            $("#negStars" + oggetto.idNegozio).append("<span class='fa fa-star checked'></span>");
        } else {
            $("#negStars" + oggetto.idNegozio).append("<span class='fa fa-star'></span>");
        }
    }
}

/**
 * 
 * @returns {undefined}
 */
function ordinaPrezzoCresc(){
    oggetti.sort(function(a, b) {
        return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
    });
    setStampabili();
}

/**
 * 
 * @returns {undefined}
 */
function ordinaPrezzoDecr(){
    oggetti.sort(function(a, b) {
        return parseFloat(b.prezzoMinimo) - parseFloat(a.prezzoMinimo);
    });
    setStampabili();
}

/**
 * 
 * @returns {undefined}
 */
function ordinaValutazioneCresc(){
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        oggetti.sort(function(a, b) {
            return parseFloat(a.voto) - parseFloat(b.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        oggetti.sort(function(a, b) {
            return parseFloat(a.valutazioneMedia) - parseFloat(b.valutazioneMedia);
        });
    }
    setStampabili();
}

/**
 * 
 * @returns {undefined}
 */
function ordinaValutazioneDecr(){
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        oggetti.sort(function(a, b) {
            return parseFloat(b.voto) - parseFloat(a.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        oggetti.sort(function(a, b) {
            return parseFloat(b.valutazioneMedia) - parseFloat(a.valutazioneMedia);
        });
    }
    setStampabili();
}

/**
 * 
 * @returns {undefined}
 */
function reset() {
    filtriCat = [];
    filtriReg = [];
    filtriVal = null;
    filtriDist = null;
    setStampabili();
    
    var reg = document.getElementById("collapseReg");
    reg.click();
    document.getElementById("checkLazio").checked = false;
    document.getElementById("checkLombardia").checked = false;
    document.getElementById("checkTrentino").checked = false;
    document.getElementById("checkVeneto").checked = false;
    
    if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        var cat = document.getElementById("collapseCat");
        cat.click();
        
        document.getElementById("checkLibri").checked = false;
        document.getElementById("checkElettronica").checked = false;
        document.getElementById("checkAbbigliamento").checked = false;
        document.getElementById("checkGiardinaggio").checked = false;
        document.getElementById("checkCasalinghi").checked = false;
    }
    
    document.getElementById("sliderValutazione").value = 3;
    document.getElementById("sliderDistanza").value = 2;
}

/**
 * 
 * @param {type} myLat
 * @param {type} myLong
 * @param {type} lat
 * @param {type} long
 * @returns {Number}
 */
function calcolaDistanza(myLat, myLong, lat, long){
    var distanza = 6366*Math.acos(Math.cos(degrees_to_radians(myLat))*Math.cos(degrees_to_radians(lat))*
            Math.cos(degrees_to_radians(long)-degrees_to_radians(myLong))+Math.sin(degrees_to_radians(myLat))*Math.sin(degrees_to_radians(lat)));
   return distanza;
}

/**
 * 
 * @param {type} degrees
 * @returns {Number}
 */
function degrees_to_radians(degrees)
{
    var pi = Math.PI;
    return degrees * (pi/180);
}

/**
 * 
 * @param {type} ordinamento
 * @returns {undefined}
 */
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

/**
 * 
 * @param {type} position
 * @returns {undefined}
 */
function ordinaDistanzaCresc(position){
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")){
        for (var i = 0; i < oggetti.length; i++) {
            oggetti[i].negozi.sort(function(a, b) {
               return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine);
            });
        }
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine);
        });
    } else if (sceltaRicerca === "negozi") {
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.locatoin.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.locatoin.latitudine,b.location.longitudine);
        });
    }
    setStampabili();
}

/**
 * 
 * @param {type} position
 * @returns {undefined}
 */
function ordinaDistanzaDecr(position){
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti")) {
        for (var i = 0; i < oggetti.length; i++) {
            oggetti[i].negozi.sort(function(a, b) {
               return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine);
            });
        }
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine);
        });
    } else if (sceltaRicerca === "negozi") {
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.locatoin.latitudine,b.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.locatoin.latitudine,a.location.longitudine);
        });
    }
    setStampabili();
}