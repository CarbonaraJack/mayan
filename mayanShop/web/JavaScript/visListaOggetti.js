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
 *  funzione per inizializzare gli oggetti della ricerca per items
 * @returns {undefined}
 */
function initialItems(){
    // aggiungo il filtro sulla categoria
    $("#filtroCategoria").append("<button class='collapsible' id='collapseCat'>Categoria</button>");
    $("#filtroCategoria").append("<div class='content' id='contentSliderCat'></div>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkLibri' name='categoria' value='Libri' onclick='addFilterCat(0)'> Libri<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkElettronica' name='categoria' value='Elettronica' onclick='addFilterCat(1)'> Elettronica<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkAbbigliamento' name='categoria' value='Abbigliamento' onclick='addFilterCat(2)'> Abbigliamento<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkGiardinaggio' name='categoria' value='Giardinaggio' onclick='addFilterCat(3)'> Giardinaggio<br>");
    $("#contentSliderCat").append("<input type='checkbox' id='checkCasalinghi' name='categoria' value='Casalinga' onclick='addFilterCat(4)'> Casalinghi<br>");
    
    $("#containerFiltri").append("<button class='collapsible' id='ordinaPrezzo'>Prezzo</button>");
    $("#containerFiltri").append("<div class='content' id='radioRicercaPrezzo'></div>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='decr'> Decrescente<br>");
    $("#radioRicercaPrezzo").append("<input type='radio' name='radioPrezzo' value='cresc'> Crescente<br>");
    
    // seleziona tutti i radio button per l'ordinamento basato sul prezzo
    var radiosPrezzo = document.querySelectorAll("input[type=radio][name='radioPrezzo']");
    // event handler per gestire l'ordinamento sul prezzo in base a che radio button è stato selezionato
    function changeHandlerPrezzo(event) {
        if ( this.value === "decr" ) {
            // chiamata alla funzione che ordina in modo decrescente secondo il prezzo
            ordinaPrezzoDecr();
            
        } else if ( this.value === "cresc" ) {
            // chiamata alla funzione che ordina in modo crescente secondo il prezzo
            ordinaPrezzoCresc();
        }  
    }
    // l'event handler viene associato a tutti i radio button del prezzo
    Array.prototype.forEach.call(radiosPrezzo, function(radio) {
        radio.addEventListener("change", changeHandlerPrezzo);
    });
}

function initialNegozi(){
    console.log("Neg");
}

/**
 * funzione che aggiunge il filtro cateria
 * @param {type} id id del check selezionato
 * @returns {undefined}
 */
function addFilterCat(id){
    var checkList = ["checkLibri","checkElettronica","checkAbbigliamento","checkGiardinaggio","checkCasalinghi"];
    // valore del checkbox selezionato
    var checkBox = document.getElementById(checkList[id]);

    // se il checkbox è stato selezionato, il filtro viene aggiunto, altrimenti viene tolto
     if (checkBox.checked == true){
        // aggiunta del valore del checkbox alla lista di filtri sulla categoria
        filtriCat.push(checkBox.value);
        // stampa della ricerca con i nuoci filtri
        setStampabili();
    } else {
        // eliminazione del valore del checkbox selezionato dalla lista di filtri sulla categoria
        filtriCat.pop(checkBox.value);
        // stampa della ricerca con i nuoci filtri
        setStampabili();
    }
}

/**
 * funzione che aggiunge i filtri sulla regione
 * @param {type} id id del checkbox selezionato
 * @returns {undefined}
 */
function addFilterReg(id){
    // valore del checkbox selezionato
    var checkBox = document.getElementById(id);
    // se il checkbox è stato selezionato, il filtro viene aggiunto, altrimenti viene tolto
     if (checkBox.checked == true){
        // aggiunta del valore del checkbox alla lista di filtri sulla regione
        filtriReg.push(checkBox.value);
        // stampa della ricerca con i nuoci filtri
        setStampabili();
    } else {
        // eliminazione del valore del checkbox selezionato dalla lista di filtri sulla valutazione
        filtriReg.pop(id);
        // stampa della ricerca con i nuoci filtri
        setStampabili();
    }
}

/**
 * funzione che controlla se un elemento è stampabile secondo i filri sulla regione
 * @param {type} oggetto oggetto che deve essere verificato per essere stampato
 * @returns {Boolean} ritorna true se l'oggetto può essere stampato, false altrimenti
 */
function checkReg(oggetto){
    // se non sono presenti filtri sulla regione, ritorna true perchè l'oggetto può essere stampato
    if(filtriReg.length == 0){
        return true;
    }
    // se la ricerca è su negozi, è necessario utilizzare parametri diversi
    if(sceltaRicerca === "negozi"){
        // per ogni filtro sulla regione, viene verificato che il negozio sia nella regione verificato
        // se viene trovata la regione del negozio nel filtro, allora l'oggetto può essere stampato
        for (var j = 0; j < filtriReg.length; j++) {
            if(oggetto.citta.regione === filtriReg[j]){
                return true;
            }
        }
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        // per ogni filtro sulla regione, viene verificato che l'oggett sia nella regione verificato
        // se viene trovata la regione dell'oggetto nel filtro, allora l'oggetto può essere stampato
        for (var j = 0; j < filtriReg.length; j++) {
            for(var z = 0; z < oggetto.regioni.length; z++){
                if(oggetto.regioni[z] === filtriReg[j]){
                    return true;
                }
            }
        }
    }
    // se le condizioni finora non sono state rispettate, significa che l'oggetto non può essere stampato
    return false;
}

/**
 * funzione che controlla se un elemento è stampabile secondo i filri sulla categoria
 * un oggetto può essere stampato secondo i filtri sulla categoria, se la sua categoria è presente nella lista dei filtri delle categorie
 * @param {type} oggetto oggetto che deve essere verificato per essere stampato
 * @returns {Boolean} ritorna true se l'oggetto può essere stampato, false altrimenti
 */
function checkCat(oggetto){
    // se non ci sono filtri sulla categoria, allora l'oggetto può essere stampato
    if(filtriCat.length == 0){
        return true;
    }
    // per ogni categoria nella lista dei filtri, viene controllato se la categoria dell'oggetto corrisponde
    // se corrisponde, l'oggetto può essere stampato
    for (var j = 0; j < filtriCat.length; j++) {
        if(oggetto.categoria === filtriCat[j]){
            return true;
        }
    }
    // se le condizioni finora non sono state rispettate, significa che l'oggetto non può essere stampato
    return false;
}

/**
 * funzione che controlla se un elemento è stampabile secondo i filri sulla valutazione
 * un oggetto è stampabile secondo la valutazione se la sua valitazione è compresa tra il valore specificato dall'utente e
 * il valore specificato dall'utente + 1
 * @param {type} oggetto oggetto che deve essere verificato per essere stampato
 * @returns {Boolean} ritorna true se l'oggetto può essere stampato, false altrimenti
 */
function checkVal(oggetto){
    // limite superiore della stampa
    var estremoSup = parseInt(filtriVal)+1;
    // se non sono presenti filtri sulla valutazione, allora l'oggetto può essere stampato
    if(filtriVal===null){
        return true;
    }
    // se la ricerca è su negozi, è necessario utilizzare parametri diversi
    if(sceltaRicerca === "negozi"){
        // se la valutazione del negozio è compreso nel range specificato, allora può essere stampato
        if((oggetto.valutazioneMedia >= filtriVal) && (oggetto.valutazioneMedia < estremoSup)){
            return true;
        }
    } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        // se la valutazione del negozio è compreso nel range specificato, allora può essere stampato
        if((oggetto.voto >= filtriVal) && (oggetto.voto < estremoSup)){
            return true;
        }
    }
    // se le condizioni finora non sono state rispettate, significa che l'oggetto non può essere stampato
    return false;
}

/**
 * funzione che controlla se un elemento è stampabile secondo i filri sulla distanza
 * un oggetto è stampabile se la sua distanza dall'utente è minore o uguale della distanza specificata nel filtro
 * @param {type} oggetto oggetto che deve essere verificato per essere stampato
 * @returns {Boolean} ritorna true se l'oggetto può essere stampato, false altrimenti
 */
function checkDist(oggetto){
    // se non sono presenti filtri sulla distanza, allora l'oggetto può essere stampato
    if(filtriDist===null){
        return true;
    }
    // se la geolocalizzazione è attiva, allora è possibile effettuare i calcoli sulla distanza
    if (navigator.geolocation) {
        // se la ricerca è su negozi, è necessario utilizzare parametri diversi
        if(sceltaRicerca === "negozi"){
            // calcolo della distanza effettiva tra l'utente e il negozio 
            var distEffettiva = calcolaDistanza(userPositionLat,userPositionLong,oggetto.location.latitudine,oggetto.location.longitudine);
            // se la distanza è minore del filtro, l'oggetto può essere stampato
            if(distEffettiva <= filtriDist){
                return true;
            }
        } else if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
            for (var i = 0; i < oggetto.negozi.length; i++) {
                // calcolo della distanza effettiva tra l'utente e il negozio in cui è in vendita l'oggetto 
                var distEffettiva = calcolaDistanza(userPositionLat,userPositionLong,oggetto.negozi[i].location.latitudine,oggetto.negozi[i].location.longitudine);
                // se la distanza è minore del filtro, l'oggetto può essere stampato
                if(distEffettiva <= filtriDist){
                    return true;
                }
            }
        }
        // se le condizioni finora non sono state rispettate, significa che l'oggetto non può essere stampato
        return false;
    } else { 
        console.log("Geolocation is not supported by this browser.");
        alert("Per eseguire un filtro sulla distanza è necessaria la geolocalizzazione!");
    }
    // se la geolocalizzazione non è supportata, gli oggetti vengono stampati
    return true;
}

/**
 * funzione che oer ogni oggetto nella lista della ricerca, determina se l'oggetto deve essere stampato oppure no
 * @returns {undefined}
 */
function setStampabili(){
    // svuota il container degli elementi della ricerca
    $("#containerItem").empty();
    // se la ricerca è su negozi, è necessario utilizzare parametri diversi 
    if((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        for (var i = 0; i < oggetti.length; i++) {
            // se l'oggetto supera ogni check dei filtri, allora viene stampato
            if((checkCat(oggetti[i])===true) && (checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true) && (checkDist(oggetti[i])===true)){
                // stampa dell'oggetto specificato
                stampaOggItem(oggetti[i]);
            }
        }
    } else if (sceltaRicerca === "negozi"){
        for (var i = 0; i < oggetti.length; i++) {
            // se il negozio supera ogni check dei filtri, allora viene stampato
            if((checkReg(oggetti[i])===true) && (checkVal(oggetti[i])===true) && (checkDist(oggetti[i])===true)){
                // stampa del negozio specificato
                stampaOggNeg(oggetti[i]);
            }
        }
    }
}

/**
 * funzione che stampa un oggetto
 * @param {type} oggetto oggetto che deve essere stampato
 * @returns {undefined}
 */
function stampaOggItem(oggetto){
    $("#containerItem").append("<div class='itemBox' id='item" + oggetto.idItem + "'></div>");
    
    $("#item" + oggetto.idItem).append("<div class='itemImageContainer' id='image" + oggetto.idItem + "'></div>");
    if(!oggetto.immagine){
        $("#image" + oggetto.idItem).append("Nessuna immagine da mostrare");
    } else {
      $("#image" + oggetto.idItem).append("<a href='controlloItems?idOgg=" + oggetto.idItem + "'><img class='itemImage' src='img/"+oggetto.immagine+"'/></a>");  
    }
    $("#item" + oggetto.idItem).append("<div class='itemName'><a href='controlloItems?idOgg=" + oggetto.idItem + "'>" + oggetto.nome + "</a></div>");
    $("#item" + oggetto.idItem).append("<div class='itemProduttore'>" + oggetto.produttore + "</div>");
    $("#item" + oggetto.idItem).append("<div class='itemPrice'>" + oggetto.prezzoMinimo + "€</div>");

    // stampa della valutazione in stelline
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
 * funzione che stampa un negozio
 * @param {type} oggetto negozio che deve essere stampato
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

    // stampa della valutazione in stelline
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
 * funzione che ordina gli oggetti secondo il prezzo in modo crescente
 * @returns {undefined}
 */
function ordinaPrezzoCresc(){
    // se la ricerca è sugli oggetti, allora si può procedere con l'ordinamento
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        // viene settato il prezzo come attivo
        setActivePrezzo();
        // funzione che riordina la lista in base al prezzo
        oggetti.sort(function(a, b) {
            return parseFloat(a.prezzoMinimo) - parseFloat(b.prezzoMinimo);
        });
        // stampa del risultato della ricerca con i nuovi filtri
        setStampabili();
    }
}

/**
 * funzione che ordina gli oggetti secondo il prezzo in modo decrescente
 * @returns {undefined}
 */
function ordinaPrezzoDecr(){
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")){
        // viene settato il prezzo come attivo
        setActivePrezzo();
        // funzione che riordina la lista in base al prezzo
        oggetti.sort(function(a, b) {
            return parseFloat(b.prezzoMinimo) - parseFloat(a.prezzoMinimo);
        });
        // stampa del risultato della ricerca con i nuovi filtri
        setStampabili();
    }
}

/**
 * funzione disattiva gli ordinamenti che non sono sul prezzo
 * @returns {undefined}
 */
function setActivePrezzo(){
    // se l'ordinamento sulla distanza è attivo, allora viene disattivato
    if (document.getElementById("ordinaDistanza").classList.contains("active")){
        document.getElementById("ordinaDistanza").click();
        $("input[name=radioDistanza]").removeAttr("checked");
    }
    // se l'ordinamento sulla valutazione è attivo, allora viene disattivato
    if (document.getElementById("ordinaValutazione").classList.contains("active")){
        document.getElementById("ordinaValutazione").click();
        $("input[name=radioValutazione]").removeAttr("checked");
    }
}

/**
 * funzione che ordina gli oggetti presenti nella lista della ricerca secondo la valutazione in modo crescente
 * @returns {undefined}
 */
function ordinaValutazioneCresc(){
    // viene settato l'ordinamento sulla valutazione com eattivo
    setActiveValutazione();
    // in base alla tipologia di ricerca, bisogna utilizzare parametri differenti
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        // funzione che ordina la lista secondo la valutazione
        oggetti.sort(function(a, b) {
            return parseFloat(a.voto) - parseFloat(b.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        // funzione che ordina la lista secondo la valutazione
        oggetti.sort(function(a, b) {
            return parseFloat(a.valutazioneMedia) - parseFloat(b.valutazioneMedia);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che ordina i risultati della ricerca in modo decrescente basandosi sulla valutazione
 * @returns {undefined}
 */
function ordinaValutazioneDecr(){
    // viene settato l'ordinamento sulla valutazione com eattivo
    setActiveValutazione();
    // in base alla tipologia di ricerca, bisogna utilizzare parametri differenti
    if ((sceltaRicerca === "produttori") || (sceltaRicerca === "oggetti") || (sceltaRicerca === "zone")) {
        // funzione che ordina la lista secondo la valutazione
        oggetti.sort(function(a, b) {
            return parseFloat(b.voto) - parseFloat(a.voto);
        });
    } else if (sceltaRicerca === "negozi"){
        // funzione che ordina la lista secondo la valutazione
        oggetti.sort(function(a, b) {
            return parseFloat(b.valutazioneMedia) - parseFloat(a.valutazioneMedia);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che disattiva gli ordinamenti che non sono sulla valutazione
 * @returns {undefined}
 */
function setActiveValutazione(){
    // se l'ordinamento sulla distanza è attivo, allora viene disattivato
    if (document.getElementById("ordinaDistanza").classList.contains("active")){
        document.getElementById("ordinaDistanza").click();
        $("input[name=radioDistanza]").removeAttr("checked");
    }
    // se l'ordinamento sul prezzo è attivo, allora viene disattivato
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
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine);
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
            return calcolaDistanza(position.coords.latitude,position.coords.longitude,b.location.latitudine,b.location.longitudine) - calcolaDistanza(position.coords.latitude,position.coords.longitude,a.location.latitudine,a.location.longitudine);
        });
    }
    // stampa del risultato della ricerca con i nuovi filtri
    setStampabili();
}

/**
 * funzione che disattiva gli ordinamenti che non sono sulla valutazione
 * @returns {undefined}
 */
function setActiveDistanza(){
    // se l'ordinamento sul prezzo è attivo, allora viene disattivato
    if (document.getElementById("ordinaPrezzo").classList.contains("active")){
        document.getElementById("ordinaPrezzo").click();
        $("input[name=radioPrezzo]").removeAttr("checked");
    }
    // se l'ordinamento sulla valutazione è attivo, allora viene disattivato
    if (document.getElementById("ordinaValutazione").classList.contains("active")){
        document.getElementById("ordinaValutazione").click();
        $("input[name=radioValutazione]").removeAttr("checked");
    }
}