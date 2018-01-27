/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// filtro sulla categoria
var filtriCat = [];
// filtro sulla regione
var filtriReg = [];
// filtro sulla valutazione
var filtriVal = null;
// filtro sulla distanza
var filtriDist = null;
// posizione dell'utente
var userPositionLat = null;
var userPositionLong = null;

$(document).ready(function () {
    // in base al tipo di ricerca, vengono inizializzati differenti oggetti
    if (sceltaRicerca === "negozi"){
        initialNegozi()
    } else if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        initialItems();
    }
    
    // per tutti gli elementi espandibili, viene aggiunto un evento che gestisce il collapse
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
    
    // slider del filtro sulla valutazione
    var sliderVal = document.getElementById("sliderValutazione");
    // etichetta associata allo slider del filtro sulla valutazione
    var outputVal = document.getElementById("labelValutazione");
    outputVal.innerHTML = sliderVal.value;
    
    // funzione associata all'evento oninput dello slider della valutazione
    // ogni volta che viene cambiato valore allo slider, la funzione viene eseguita
    sliderVal.oninput = function() {
        // mostra all'utente il valore selezionato con lo slider
        outputVal.innerHTML = this.value;
        // setta il filtro della valutazione con il valore selezionato
        filtriVal = this.value;
        // se il container dello slider non ha la classe active, aggiunge active per mostrare all'utente che il filtro è attivo
        if (!document.getElementById("slideContainerValutazione").classList.contains("active")){
            document.getElementById("slideContainerValutazione").classList.add("active");
        }
        setStampabili();
    };
    
    // slider del filtro sulla distanza
    var sliderDist = document.getElementById("sliderDistanza");
    // etichetta associata allo slider del filtro sulla distanza
    var outputDist = document.getElementById("labelDistanza");
    outputDist.innerHTML = sliderDist.value;
    
    // funzione associata all'evento oninput dello slider della distanza
    // ogni volta che viene cambiato valore allo slider, la funzione viene eseguita
    sliderDist.oninput = function() {
        // mostra all'utente il valore selezionato con lo slider
        outputDist.innerHTML = this.value;
        // setta il filtro della distanza con il valore selezionato
        filtriDist = this.value;
        // se la geolocalizzazione non è attiva, manda un messaggio di errore
        if (navigator.geolocation) {
            // passa alla funzione setPosition, la posizione attuale
            navigator.geolocation.getCurrentPosition(setPosition);
            // se il container dello slider non ha la classe active, aggiunge active per mostrare all'utente che il filtro è attivo
            if (!document.getElementById("slideContainerDistanza").classList.contains("active")){
                document.getElementById("slideContainerDistanza").classList.add("active");
            }
        } else { 
            console.log("Geolocation is not supported by this browser.");
            alert("La tua posizione è necessaria per la selezione degli oggetti vicini a te");
        }
    };
    
    // seleziona tutti i radioButton per l'ordinamento basato sulla distanza
    var radiosDistanza = document.querySelectorAll("input[type=radio][name='radioDistanza']");
    // event handler per gestire l'ordinamento sulla distanza in base a che radio button è stato selezionato
    function changeHandlerDist(event) {
        if ( this.value === "decr" ) {
            // chiamata alla funzione che cerca la posizione attuale e prosegue con l'ordinamento decrescente
            getPosition("decr");
            
        } else if ( this.value === "cresc" ) {
            // chiamata alla funzione che cerca la posizione attuale e prosegue con l'ordinamento crescente
            getPosition("cresc");
        }  
    }
    // l'event handler viene associato a tutti i radio button della distanza
    Array.prototype.forEach.call(radiosDistanza, function(radio) {
        radio.addEventListener("change", changeHandlerDist);
    });
    
    // seleziona tutti i radio button per l'ordinamento basato sulla valutazione
    var radiosValutazione = document.querySelectorAll("input[type=radio][name='radioValutazione']");
    // event handler per gestire l'ordinamento sulla valutazione in base a che radio button è stato selezionato
    function changeHandlerValutazione(event) {
        if ( this.value === "decr" ) {
            // chiamata alla funzione che ordina in modo decrescente secondo la valutazione
            ordinaValutazioneDecr();
            
        } else if ( this.value === "cresc" ) {
            // chiamata alla funzione che ordina in modo crescente secondo la valutazione
            ordinaValutazioneCresc();
        }  
    }
    // l'event handler viene associato a tutti i radio button della valutazione
    Array.prototype.forEach.call(radiosValutazione, function(radio) {
        radio.addEventListener("change", changeHandlerValutazione);
    });
    
    // stampa i risultati della ricerca secondo i filtri impostati
    setStampabili();
});

/**
 * funzione che setta le variabili globali con la posizione dell'utente
 * @param {type} position posizione attuale dell'utente
 * @returns {undefined}
 */
function setPosition(position){
    userPositionLat = position.coords.latitude;
    userPositionLong = position.coords.longitude;
    setStampabili();
}

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
    /*
    $("#ordinaPrezzo").append("<button class='collapsible' id='ordinaPrezzo'>Prezzo</button>");
    $("#ordinaPrezzo").append("<div class='content' id='radioRicercaPrezzo'></div>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='decr'> Decrescente<br>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='cresc'> Crescente<br>");*/
    
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
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
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
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
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
    }
    if (navigator.geolocation) {
        if(sceltaRicerca === "negozi"){
            var distEffettiva = calcolaDistanza(userPositionLat,userPositionLong,oggetto.location.latitudine,oggetto.location.longitudine);
            if(distEffettiva <= filtriDist){
                return true;
            }
        } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
            for (var i = 0; i < oggetto.negozi.length; i++) {
                var distEffettiva = calcolaDistanza(userPositionLat,userPositionLong,oggetto.negozi[i].location.latitudine,oggetto.negozi[i].location.longitudine);
                if(distEffettiva <= filtriDist){
                    return true;
                }
            }
        }
        return false;
    } else { 
        console.log("Geolocation is not supported by this browser.");
    }
    return false;
}

/**
 * 
 * @returns {undefined}
 */
function setStampabili(){
    $("#containerItem").empty();
    
    if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        for (var i = 0; i < oggetti.length; i++) {
            if((checkCat(oggetti[i])===true) && (checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true) && (checkDist(oggetti[i])===true)){
                stampaOggItem(oggetti[i]);
            }
        }
    } else if (sceltaRicerca === "negozi"){
        for (var i = 0; i < oggetti.length; i++) {
            if((checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true) && (checkDist(oggetti[i])===true)){
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
    if((!oggetto.foto) || (oggetto.foto.length <= 0)){
        $("#image" + oggetto.idItem).append("Nessuna immagine da mostrare");
    } else {
      $("#image" + oggetto.idItem).append("<a href='controlloItems?idOgg=" + oggetto.idItem + "'><img class='itemImage' src='img/"+oggetto.immagine+"'/></a>");  
    }
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
    if((!oggetto.foto) || (oggetto.foto.length <= 0)){
        $("#image" + oggetto.idNegozio).append("Nessuna immagine da mostrare");
    } else {
        $("#image" + oggetto.idNegozio).append("<a href='controlloNegozi?idNegozio=" + oggetto.idNegozio + "'><img class='itemImage' src='img/"+oggetto.foto[0]+"'/></a>");
    }
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
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        setActivePrezzo();
        oggetti.sort(function(a, b) {
            return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
        });
        // stampa del risultato della ricerca con i nuovi filtri
        setStampabili();
    }
}

/**
 * 
 * @returns {undefined}
 */
function ordinaPrezzoDecr(){
    setActivePrezzo();
    oggetti.sort(function(a, b) {
        return parseFloat(b.prezzoMinimo) - parseFloat(a.prezzoMinimo);
    });
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

function setActivePrezzo(){
    if (document.getElementById("ordinaDistanza").classList.contains("active")){
        document.getElementById("ordinaDistanza").click();
        $("input[name=radioDistanza]").removeAttr("checked");
    }
    if (document.getElementById("ordinaValutazione").classList.contains("active")){
        document.getElementById("ordinaValutazione").click();
        $("input[name=radioValutazione]").removeAttr("checked");
    }
}

/**
 * 
 * @returns {undefined}
 */
function ordinaValutazioneCresc(){
    setActiveValutazione();
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        oggetti.sort(function(a, b) {
            return parseFloat(a.voto) - parseFloat(b.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        oggetti.sort(function(a, b) {
            return parseFloat(a.valutazioneMedia) - parseFloat(b.valutazioneMedia);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che ordina i risultati della ricerca in modo crescente basandosi sulla valutazione
 * @returns {undefined}
 */
function ordinaValutazioneDecr(){
    setActiveValutazione();
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        oggetti.sort(function(a, b) {
            return parseFloat(b.voto) - parseFloat(a.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        oggetti.sort(function(a, b) {
            return parseFloat(b.valutazioneMedia) - parseFloat(a.valutazioneMedia);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

function setActiveValutazione(){
    if (document.getElementById("ordinaDistanza").classList.contains("active")){
        document.getElementById("ordinaDistanza").click();
        $("input[name=radioDistanza]").removeAttr("checked");
    }
    if (document.getElementById("ordinaPrezzo").classList.contains("active")){
        document.getElementById("ordinaPrezzo").click();
        $("input[name=radioPrezzo]").removeAttr("checked");
    }
}

/**
 * funzione che resetta tutti i filtri impostati
 * @returns {undefined}
 */
function reset() {
    filtriCat = [];
    filtriReg = [];
    filtriVal = null;
    filtriDist = null;
    
    // se il collapse del filtro delle regioni è attivo, viene disattivato e chiuso
    if (document.getElementById("collapseReg").classList.contains("active")){
        document.getElementById("collapseReg").click();
    }
    // tutti i checkbox della regione vengono deselezionati
    document.getElementById("checkLazio").checked = false;
    document.getElementById("checkLombardia").checked = false;
    document.getElementById("checkTrentino").checked = false;
    document.getElementById("checkVeneto").checked = false;
    
    // se il collapse del filtro delle categorie è presente (presente solo per ricerca per oggetti, produttori e zone), viene resettato
    if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        // se il collapse del filtro delle categorie è attivo, viene disattivato e chiuso
        if (document.getElementById("collapseCat").classList.contains("active")){
            document.getElementById("collapseCat").click();
        }
        // tutti i checkbox della categorie vengono deselezionati
        document.getElementById("checkLibri").checked = false;
        document.getElementById("checkElettronica").checked = false;
        document.getElementById("checkAbbigliamento").checked = false;
        document.getElementById("checkGiardinaggio").checked = false;
        document.getElementById("checkCasalinghi").checked = false;
    }
    
    // reset dei valori dello slider per il filtro delle valutazioni
    document.getElementById("sliderValutazione").value = 3;
    document.getElementById("labelValutazione").textContent = 3;
    // se il filtro delle valutazioni è attivo, viene disattivato e chiuso
    if (document.getElementById("slideContainerValutazione").classList.contains("active")){
        document.getElementById("slideContainerValutazione").classList.remove("active");
    }
    // reset dei valori dello slider per il filtro sulla distanza
    document.getElementById("sliderDistanza").value = 2;
    document.getElementById("labelDistanza").textContent = 2;
    // se il filtro sulla distanza è attivo, viene disattivato e chiuso
    if (document.getElementById("slideContainerDistanza").classList.contains("active")){
        document.getElementById("slideContainerDistanza").classList.remove("active");
    }
    
    // stampa del risultato della ricerca con i filtri resettati
    setStampabili();
}

/**
 * funzione che calcola la distanza tra due coordinate passate come parametri, tutti i parametri sono coordinate espresse in gradi
 * @param {type} myLat latitudine dell'utene
 * @param {type} myLong longitudine dell'utente
 * @param {type} lat latitudine del punto di cui si vuole calcolare la distanza
 * @param {type} long longitudine del punto di cui si vuole calcolare la distanza
 * @returns {Number} ritorna la distanza in km tra i due punti specificati
 */
function calcolaDistanza(myLat, myLong, lat, long){
    var distanza = 6366*Math.acos(Math.cos(degrees_to_radians(myLat))*Math.cos(degrees_to_radians(lat))*
            Math.cos(degrees_to_radians(long)-degrees_to_radians(myLong))+Math.sin(degrees_to_radians(myLat))*Math.sin(degrees_to_radians(lat)));
   return distanza;
}

/**
 * funzione che trasforma il parametro passato da gradi a radianti
 * @param {type} degrees numero espresso in grado
 * @returns {Number} ritorna la conversione di degrees in radianti
 */
function degrees_to_radians(degrees)
{
    var pi = Math.PI;
    return degrees * (pi/180);
}

/**
 * funzione che fornisce la posizione dell'utente per l'ordinamento del risultato (secondo l'ordinamento specificato) della ricerca secondo la distanza
 * @param {type} ordinamento specifica l'ordinamento crescente o decrescente
 * @returns {undefined}
 */
function getPosition(ordinamento){
    // se non è attiva la geolocalizzazione dà un messaggio d'errore
    if (navigator.geolocation) {
        // in base al parametro di ordinamento, viene ordinato il risultato della ricerca
        if(ordinamento==="cresc"){
            setActiveDistanza();
            navigator.geolocation.getCurrentPosition(ordinaDistanzaCresc);
        } else if (ordinamento==="decr"){
            setActiveDistanza();
            navigator.geolocation.getCurrentPosition(ordinaDistanzaDecr);
        }
    } else { 
        console.log("Geolocation is not supported by this browser.");
        alert("Per l'ordinamento del risultato della ricerca, è necessaria la posizione geografica.");
    }
}

/**
 * funzione che ordina in modo crescente (dalla distanza minore alla distanza maggiore) il risultato della ricerca 
 * basandosi sulla posizione dell'utente 
 * @param {type} position posizione dell'utente
 * @returns {undefined}
 */
function ordinaDistanzaCresc(position){
    // viene verificato la tipologia di ricerca effettuata per ordinare sui guisti parametri
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        for (var i = 0; i < oggetti.length; i++) {
            // prima di ordinare gli oggetti, vengono ordinati i negozi in cui è presente ogni oggetto della lista, questo per
            // semplificare l'ordinamento di tutti gli oggetti
            oggetti[i].negozi.sort(function(a, b) {
               return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine);
            });
        }
        // ordinamento degli oggetti in base alla distanza
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine);
        });
    } else if (sceltaRicerca === "negozi") {
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.locatoin.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.locatoin.latitudine,b.location.longitudine);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che ordina in modo decrescente (dalla distanza maggiore alla distanza minore) il risultato della ricerca 
 * basandosi sulla posizione dell'utente 
 * @param {type} position posizione dell'utente
 * @returns {undefined}
 */
function ordinaDistanzaDecr(position){
    // viene verificato la tipologia di ricerca effettuata per ordinare sui guisti parametri
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        for (var i = 0; i < oggetti.length; i++) {
            // prima di ordinare gli oggetti, vengono ordinati i negozi in cui è presente ogni oggetto della lista, questo per
            // semplificare l'ordinamento di tutti gli oggetti
            oggetti[i].negozi.sort(function(a, b) {
               return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine);
            });
        }
        // ordinamento degli oggetti in base alla distanza
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.negozi[0].location.latitudine,b.negozi[0].location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.negozi[0].location.latitudine,a.negozi[0].location.longitudine);
        });
    } else if (sceltaRicerca === "negozi") {
        oggetti.sort(function(a, b) {
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.locatoin.latitudine,b.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.locatoin.latitudine,a.location.longitudine);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che setta
 * @returns {undefined}
 */
function setActiveDistanza(){
    if (document.getElementById("ordinaPrezzo").classList.contains("active")){
        document.getElementById("ordinaPrezzo").click();
        $("input[name=radioPrezzo]").removeAttr("checked");
    }
    if (document.getElementById("ordinaValutazione").classList.contains("active")){
        document.getElementById("ordinaValutazione").click();
        $("input[name=radioValutazione]").removeAttr("checked");
    }
}