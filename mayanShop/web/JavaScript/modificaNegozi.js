$(document).ready(function () {
    if (userId === 0) {
        console.log(userId);
        window.location = './alert.jsp?mode=notlog';
    } else if (userType !== "venditore") {
        window.location = './alert.jsp?mode=restricted';
    } else if (listaNegozi == null) {
        window.location = "./editNegozio";
    } else {
        console.log(listaNegozi);

        /** EDITOR INFORMAZIONI **/
        var selettoreNegozi = document.getElementById("selettoreNegozi");
        for (var i = 0; i < listaNegozi.length; i++) {
            var negozio = listaNegozi[i];
            //creazione elementi
            var riga = document.createElement("div");
            riga.setAttribute("class", "selettoreRiga");
            riga.setAttribute("id", "selettoreRiga" + i);
            riga.setAttribute("onclick", "visualizzaNegozio(" + i + ");");
            var nome = document.createElement("div");
            nome.setAttribute("class", "selettoreNome");
            var via = document.createElement("div");
            via.setAttribute("class", "selettoreVia");
            var citta = document.createElement("div");
            citta.setAttribute("class", "selettoreCitta");
            var tipo = document.createElement("div");
            tipo.setAttribute("class", "selettoreTipo");
            //popolamento elementi
            nome.innerHTML = negozio.nome;
            if (negozio.idLocation !== -1) {
                via.innerHTML = negozio.location.via;
                citta.innerHTML = negozio.citta.citta;
            }
            tipo.innerHTML = negozio.tipo;

            //attaccamento elementi
            riga.appendChild(nome);
            riga.appendChild(via);
            riga.appendChild(citta);
            riga.appendChild(tipo);
            selettoreNegozi.appendChild(riga);
        }
        //aggiungo la riga per inserire il nuovo negozio
        //creazione elementi
        var riga = document.createElement("div");
        riga.setAttribute("class", "selettoreRigaFoot");
        riga.setAttribute("id", "selettoreRigaNuovo");
        riga.setAttribute("onclick", "visualizzaNegozio(\"nuovo\");");
        var nome = document.createElement("div");
        nome.setAttribute("class", "selettoreNome");
        var via = document.createElement("div");
        via.setAttribute("class", "selettoreVia");
        var citta = document.createElement("div");
        citta.setAttribute("class", "selettoreCitta");
        var tipo = document.createElement("div");
        tipo.setAttribute("class", "selettoreTipo");

        //popolamento elementi
        nome.innerHTML = "Inserisci un nuovo negozio";

        //attaccamento elementi
        riga.appendChild(nome);
        riga.appendChild(via);
        riga.appendChild(citta);
        riga.appendChild(tipo);
        selettoreNegozi.appendChild(riga);

        if (listaNegozi.length > 0) {
            visualizzaNegozio(0);
        } else {
            visualizzaNegozio("nuovo");
        }
    }

});
/*
 * Funzione che popola i div del jsp con i valori del negozio selezionato
 */
var visualizzaNegozio = function (param) {
    //passo il parametro alla funzione check
    document.getElementById("editForm").setAttribute("onsubmit", "return validateForm(\"" + param + "\");");
    var bottone = document.getElementById("visitNegozio");
    if (bottone !== null) {
        bottone.parentNode.removeChild(bottone);
    }
    if (param === "nuovo") {
        /** EDITOR INFORMAZIONI **/
        //console.log("Nuovo negozio");
        //evidenzio la riga selezionata
        for (let elem of document.getElementsByClassName("selectedRow")) {
            elem.classList.remove("selectedRow");
        }
        document.getElementById("selettoreRigaNuovo").classList.add("selectedRow");

        //aggiorno il form
        document.getElementById("editIdSelector").value = "nuovo";
        document.getElementById("editName").value = "";
        document.getElementById("editLink").value = "";
        document.getElementById("editType").value = "online";
        document.getElementById("editDesc").innerHTML = "";
        document.getElementById("editHour").innerHTML = "";
        document.getElementById("editSubmit").value = "Inserisci negozio";
        /** EDITOR LOCATION **/
        //nascondo i div
        document.getElementById("editorLocation").style.display = "none";
        /** EDITOR FOTO **/
        document.getElementById("editorFoto").style.display = "none";
        /** GRID **/
        var containers = document.getElementsByClassName("container");
        for (let container of containers) {
            container.classList.add("container-nuovo");
        }
        //editor mappa
        impostaEditorMappa(null, null);
    } else {
        //console.log(param);
        //evidenzio la riga selezionata
        for (let elem of document.getElementsByClassName("selectedRow")) {
            elem.classList.remove("selectedRow");
        }
        document.getElementById("selettoreRiga" + param).classList.add("selectedRow");

        //aggiorno il form
        document.getElementById("editIdSelector").value = listaNegozi[param].idNegozio;
        document.getElementById("editName").value = listaNegozi[param].nome;
        document.getElementById("editLink").value = listaNegozi[param].webLink;
        document.getElementById("editType").value = listaNegozi[param].tipo;
        document.getElementById("editDesc").innerHTML = listaNegozi[param].descrizione;
        document.getElementById("editHour").innerHTML = listaNegozi[param].orari;
        document.getElementById("editSubmit").value = "Aggiorna negozio";
        //aggiungo il tasto di visualizzazione
        var bottoneVisual = document.createElement("button");
        bottoneVisual.setAttribute("id", "visitNegozio");
        bottoneVisual.setAttribute("onclick",
                "window.location=\'controlloNegozi?idNegozio=" + listaNegozi[param].idNegozio + "\';");
        bottoneVisual.innerHTML = "Pagina del negozio";
        document.getElementById("editorInfo").appendChild(bottoneVisual);

        /** EDITOR LOCATION **/
        document.getElementById("editorLocation").style.display = "block";
        /** EDITOR FOTO **/
        document.getElementById("editorFoto").style.display = "block";
        var foto = listaNegozi[param].foto;
        if (foto.length !== 0) {
            document.getElementById("visualizzatoreFoto").style.display = "grid";
            document.getElementById("visualizzatoreFotoVuoto").style.display =
                    "none";
            
            var selettoreFoto = document.getElementById("selettoreFoto");
            //cancello i figli del selettore
            while(selettoreFoto.firstChild){
                selettoreFoto.removeChild(selettoreFoto.firstChild);
            }
            //popolo il selettore
            for (var i = 0; i < foto.length; i++) {
                var divSelettore = document.createElement("div");
                var imgSelettore = document.createElement("img");
                imgSelettore.setAttribute("src", "./img/" + foto[i]);
                imgSelettore.classList.add("imgSelettore");
                divSelettore.classList.add("divSelettore");
                divSelettore.id = "divSelettore" + i;
                divSelettore.setAttribute("onclick", "cambiaFoto(" + param + "," + i + ");");
                divSelettore.appendChild(imgSelettore);
                selettoreFoto.appendChild(divSelettore);
            }
            cambiaFoto(param, 0);
        } else {
            document.getElementById("visualizzatoreFoto").style.display = "none";
            document.getElementById("visualizzatoreFotoVuoto").style.display =
                    "grid";
        }
        document.getElementById("uploadId").value = listaNegozi[param].idNegozio;
        /** GRID **/
        var containers = document.getElementsByClassName("container");
        for (let container of containers) {
            container.classList.remove("container-nuovo");
        }
        //popolo l'editor mappa
        impostaEditorMappa(listaNegozi[param].location, listaNegozi[param].citta);
        document.getElementById("formLocation").setAttribute("onSubmit",
                "return validaLocation(" + param + ");");
        document.getElementById("idNegozioLoc").value=listaNegozi[param].idNegozio;
    }

};

var validateForm = function (param) {
    var result;
    //controllo se sono cambiati dei parametri
    if (param === "nuovo") {
        result = (document.getElementById("editName").value === "") &&
                (document.getElementById("editLink").value === "") &&
                (document.getElementById("editType").value === "online") &&
                (document.getElementById("editDesc").value === "") &&
                (document.getElementById("editHour").value === "");
    } else {
        result = (document.getElementById("editName").value === listaNegozi[param].nome) &&
                (document.getElementById("editLink").value === listaNegozi[param].webLink) &&
                (document.getElementById("editType").value === listaNegozi[param].tipo) &&
                (document.getElementById("editDesc").value === listaNegozi[param].descrizione) &&
                (document.getElementById("editHour").value === listaNegozi[param].orari);
    }
    if (result) {
        var editMessage = document.getElementById("editMessage");
        editMessage.innerHTML = "Nulla da aggiornare!<br>";
        editMessage.style.display = "inline";
    } else {
        //Codifico i caratteri speciali
        var nome = document.getElementById("editName").value;
        nome = encodeURIComponent(nome);
        var link = document.getElementById("editLink").value;
        link = encodeURIComponent(link);
        var descrizione = document.getElementById("editDesc").value;
        descrizione = encodeURIComponent(descrizione);
        var orari = document.getElementById("editHour").value;
        orari = encodeURIComponent(orari);
    }
    result = !result;
    return result;
};

var cambiaFoto = function (param, i) {
    //modifico lo stile dell'elemento selezionato
    var divSelettore = document.getElementById("divSelettore" + i);
    var elementiSelezionati =
            document.getElementsByClassName("divSelettoreSelezionato");
    for (let elem of elementiSelezionati) {
        elem.classList.remove("divSelettoreSelezionato");
    }
    divSelettore.classList.add("divSelettoreSelezionato");
    //ingrandisco la foto
    document.getElementById("foto").setAttribute("src", "./img/" + listaNegozi[param].foto[i]);
    //imposto l'indirizzo della foto come parametro del form
    document.getElementById("idCancellaFoto").value = listaNegozi[param].foto[i];
};

var cercaIndirizzo = function () {
    var indirizzo = document.getElementById("indirizzoInput").value;
    fetch("https://maps.googleapis.com/maps/api/geocode/json?address="
            + encodeURIComponent(indirizzo)
            + "&region=it&key=AIzaSyD0BSa0n9a1UTzBBiIdrIz0NfpTMsNcFwQ")
            .then((response) => response.json())
            .then(function (response) {
                var testoIndirizzo = document.getElementById("indirizzoTrovato");
                var addressComponents = response.results[0].address_components;
                var via, civico, cap, citta, regione, codiceProv, stato;
                //estrapolo l'indirizzo dal JSON
                for (let elem of addressComponents) {
                    for (let type of elem.types) {
                        switch (type) {
                            case "route"://via
                                via = elem.long_name;
                                break;
                            case "street_number"://civico
                                civico = elem.long_name;
                                break;
                            case "postal_code"://cap
                                cap = elem.long_name;
                                break;
                            case "locality"://città-paese
                                citta = elem.long_name;
                                break;
                            case "administrative_area_level_1"://regione
                                regione = elem.long_name;
                                break;
                            case "administrative_area_level_2"://provincia
                                codiceProv = elem.short_name;
                                break;
                            case "country"://stato
                                stato = elem.long_name;
                                break;
                        }
                    }
                }
                //estrampo le coordinate dal JSON
                var lat = response.results[0].geometry.location.lat;
                var lon = response.results[0].geometry.location.lng;

                var locationJson = {
                    "latitudine": lat,
                    "longitudine": lon,
                    "via": via + " " + civico + ", " + cap + " " + codiceProv
                };
                var cittaJson = {
                    "citta": citta,
                    "regione": regione,
                    "stato": stato
                };

                impostaEditorMappa(locationJson, cittaJson);


                console.log(response.results[0]);
            });
};

function impostaMappa(coordinate) {
    if (typeof google === 'undefined') {
        //ASPETTO CHE SI CARICHI L'API GOOGLE
        setTimeout(function () {
            impostaMappa(coordinate);
        }
        , 500);
    } else {
        var mappa = new google.maps.Map(
                document.getElementById("mappaGoogle"),
                {
                    zoom: 15,
                    center: coordinate
                }
        );
        var marker = new google.maps.Marker({
            position: coordinate,
            map: mappa
        });
    }
}
function impostaEditorMappa(location, citta) {
    var inputArea = document.getElementById("indirizzoInput");
    var labelIndirizzo = document.getElementById("indirizzoTrovato");
    var mappa = document.getElementById("mappaGoogle");
    var submitArea = document.getElementById("containerSubmitLocation");
    document.getElementById("warnLocation").style.display = "none";
    if (location == null) {
        inputArea.value = "";
        labelIndirizzo.innerHTML = "Nessun indirizzo impostato per questo negozio";
        mappa.style.display = "none";
        submitArea.style.display = "none";
    } else {
        document.getElementById("locationJson").value = JSON.stringify(location);
        document.getElementById("cittaJson").value = JSON.stringify(citta);
        console.log(location);
        console.log(citta);
        inputArea.value = location.via + " " + citta.citta;
        labelIndirizzo.innerHTML = location.via + ", " + citta.citta + " " + citta.stato;
        submitArea.style.display = "block";
        mappa.style.display = "block";
        impostaMappa({"lat": parseFloat(location.latitudine),
            "lng": parseFloat(location.longitudine)});

    }

}

function validaLocation(param) {
    var location = listaNegozi[param].location;
    var citta = listaNegozi[param].citta;
    var locationJson = JSON.parse(document.getElementById("locationJson").value);
    var cittaJson = JSON.parse(document.getElementById("cittaJson").value);
    console.log(location);
    console.log(locationJson);

    var res = citta.citta === cittaJson.citta
            && citta.regione === cittaJson.regione
            && citta.stato === cittaJson.stato
            && location.latitudine === locationJson.latitudine
            && location.longitudine === locationJson.longitudine
            && location.via === locationJson.via;

    if (res) {
        document.getElementById("warnLocation").style.display = "inline";
        return false;
    } else {
        var loc = document.getElementById("locationJson").value;
        loc = encodeURIComponent(loc);
        var cit = document.getElementById("cittaJson").value;
        cit = encodeURIComponent(cit);
        return true;
    }
}