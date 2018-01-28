$(document).ready(function () {
    if (userId === 0) {
        window.location = './alert.jsp?mode=notlog';
    } else if (userType !== "venditore" && userType !== "amministratore") {
        window.location = './alert.jsp?mode=restricted';
    } else if (parametri == null) {
        window.location = "./editItemList";
    } else {
        //separo i parametri per comodità
        var categorie = parametri[0];
        var produttori = parametri[1];
        var item = parametri[2];
        var negozi = parametri[3];
        var listaFoto = parametri[4];
        //var mode = "<%= mode%>";      per ricordarmelo
        console.log(categorie);
        console.log(produttori);
        console.log(item);
        console.log(negozi);
        console.log(listaFoto);
        console.log(mode);

        /** EDITOR INFORMAZIONI **/
        //popolo i due select
        var option;
        var selectProd = document.getElementById("editProd");
        for (let produttore of produttori) {
            option = document.createElement("option");
            option.value = produttore;
            option.innerHTML = produttore;
            selectProd.appendChild(option);
        }
        option = document.createElement("option");
        option.value = "nuovo";
        option.innerHTML = "- nuovo produttore -";
        option.style = "color:red;";
        selectProd.appendChild(option);

        var selectCat = document.getElementById("editCat");
        for (let categoria of categorie) {
            option = document.createElement("option");
            option.value = categoria;
            option.innerHTML = categoria;
            selectCat.appendChild(option);
        }
        option = document.createElement("option");
        option.value = "nuova";
        option.innerHTML = "- nuova categoria -";
        option.style = "color:red;";
        selectCat.appendChild(option);
        //<option value="online">Online</option>
        if (mode === "new") {
            //Nascondo gli altri editor e cambio il layout del grid
            for (let container of document.getElementsByClassName("container")) {
                container.classList.remove("container");
                container.classList.add("container-new");
            }
            document.getElementById("editorStock").style.display = "none";
            document.getElementById("editorFoto").style.display = "none";
        } else {
            //POPOLO L'EDITOR DI INFORMAZIONI
            document.getElementById("editorIdItem").value = item.idItem;
            document.getElementById("editInfoSubmit").value = "Aggiorna oggetto";
            document.getElementById("titoloEditor").innerText = "Modifica l\'oggetto";
            document.getElementById("editName").value = item.nome;
            document.getElementById("editDesc").value = item.descrizione;
            document.getElementById("editProd").value = item.produttore;
            document.getElementById("editCat").value = item.categoria;
            //POPOLO L'EDITOR DEGLI STOCK
            document.getElementById("stockItemId").value = item.idItem;
            var stockGrid = document.getElementById("stockGrid");
            for (let stock of negozi) {
                //creo i div
                var nomeContainer = document.createElement("div");
                var prezzoContainer = document.createElement("div");
                var numStockContainer = document.createElement("div");
                nomeContainer.classList.add("stockRow");
                prezzoContainer.classList.add("stockRow");
                numStockContainer.classList.add("stockRow");
                var nome = document.createElement("div");
                var prezzo = document.createElement("div");
                var numStock = document.createElement("div");

                //creo gli elementi del form                
                var inputHidden = document.createElement("input");
                inputHidden.setAttribute("type", "hidden");
                inputHidden.setAttribute("name", "idItem" + stock.idNegozio);
                inputHidden.setAttribute("value", stock.idItem);
                var inputPrezzo = document.createElement("input");
                inputPrezzo.setAttribute("type", "number");
                inputPrezzo.setAttribute("min", "0");
                inputPrezzo.setAttribute("step", "0.01");
                inputPrezzo.setAttribute("name", "prezzo" + stock.idNegozio);
                inputPrezzo.setAttribute("value", stock.prezzo);
                var inputStock = document.createElement("input");
                inputStock.setAttribute("type", "number");
                inputStock.setAttribute("min", "0");
                inputStock.setAttribute("name", "stock" + stock.idNegozio);
                inputStock.setAttribute("value", stock.numStock);

                //attacco gli elementi ai div
                nome.innerHTML = stock.nomeNegozio;
                nome.appendChild(inputHidden);
                prezzo.appendChild(inputPrezzo);
                numStock.appendChild(inputStock);
                //attacco i div ai containers
                nomeContainer.appendChild(nome);
                prezzoContainer.appendChild(prezzo);
                numStockContainer.appendChild(numStock);

                //attacco i containers al grid
                stockGrid.appendChild(nomeContainer);
                stockGrid.appendChild(prezzoContainer);
                stockGrid.appendChild(numStockContainer);

            }

            /** POPOLO L'EDITOR FOTO **/
            if (listaFoto.length !== 0) {
                document.getElementById("visualizzatoreFoto").style.display = "grid";
                document.getElementById("visualizzatoreFotoVuoto").style.display =
                        "none";

                var selettoreFoto = document.getElementById("selettoreFoto");
                //cancello i figli del selettore
                while (selettoreFoto.firstChild) {
                    selettoreFoto.removeChild(selettoreFoto.firstChild);
                }
                //popolo il selettore
                for (var i = 0; i < listaFoto.length; i++) {
                    var divSelettore = document.createElement("div");
                    var imgSelettore = document.createElement("img");
                    imgSelettore.setAttribute("src", "./img/" + listaFoto[i].linkFoto);
                    imgSelettore.classList.add("imgSelettore");
                    divSelettore.classList.add("divSelettore");
                    divSelettore.id = "divSelettore" + i;
                    divSelettore.setAttribute("onclick", "cambiaFoto(" + i + ");");
                    divSelettore.appendChild(imgSelettore);
                    selettoreFoto.appendChild(divSelettore);
                }
                cambiaFoto(0);
            } else {
                document.getElementById("visualizzatoreFoto").style.display = "none";
                document.getElementById("visualizzatoreFotoVuoto").style.display =
                        "grid";
            }
            document.getElementById("uploadId").value = item.idItem;
            document.getElementById("idItemDThumb").value = item.idItem;
            document.getElementById("idItemIThumb").value = item.idItem;
        }
    }

});
/**
 * Funzione che mostra/nasconde l'input di nuovo produttore se si è scelta
 * l'opzione inserisci produttore dal menù a tendina
 */
var nuovoProd = function () {
    var selettore = document.getElementById("editProd");
    if (selettore.options[selettore.selectedIndex].value === "nuovo") {
        document.getElementById("nuovoProdDiv").style.display = "block";
    } else {
        document.getElementById("nuovoProdDiv").style.display = "none";
    }
};

/**
 * Funzione che mostra/nasconde l'input di nuova categoria se si è scelta
 * l'opzione inserisci categoria dal menù a tendina
 */
var nuovaCat = function () {
    var selettore = document.getElementById("editCat");
    if (selettore.options[selettore.selectedIndex].value === "nuova") {
        document.getElementById("nuovaCatDiv").style.display = "block";
    } else {
        document.getElementById("nuovaCatDiv").style.display = "none";
    }
};

/**
 * Funzione che rimuove i caratteri speciali dal JSON per comparare il textarea
 */
String.prototype.escapeForCompare = function () {
    return this
            .replace(/\b/g, "")
            .replace(/\f/g, "")
            .replace(/\\/g, "\\")
            .replace(/\"/g, "\\\"")
            .replace(/\t/g, "\\t")
            .replace(/\r/g, "")
            .replace(/\n/g, "\\n")
            .replace(/\u2028/g, "\\u2028")
            .replace(/\u2029/g, "\\u2029");
};

/**
 * Funzione che valida il form di edit informazioni
 * @returns False se non è valido, true altrimenti
 */
var validaForm = function () {
    var item = parametri[2];
    var result;
    var editMessage = document.getElementById("editMessage");

    //controllo se sono cambiati dei parametri
    var editProd = document.getElementById("editProd");
    var editCat = document.getElementById("editCat");
    var prodSel = editProd.options[editProd.selectedIndex].value;
    var catSel = editCat.options[editCat.selectedIndex].value;
    if (prodSel === "nuovo") {
        result = document.getElementById("nuovoProdNome").value.trim() === "";
        editMessage.innerHTML = "Non lasciare il produttore vuoto!<br>";
    }
    if (!result && catSel === "nuova") {
        result = document.getElementById("nuovaCatNome").value.trim() === "";
        editMessage.innerHTML = "Non lasciare la categoria vuota!<br>";
    }
    if (!result && mode !== "new") {
        if (prodSel === "nuovo") {
            result = document.getElementById("nuovoProdNome").value.trim() === item.produttore;
        } else {
            result = prodSel === item.produttore;
        }
        if (catSel === "nuova") {
            result = result &&
                    document.getElementById("nuovaCatNome").value.trim() === item.categoria;
        } else {
            result = result && catSel === item.categoria;
        }
        result = result && (document.getElementById("editName").value.trim() === item.nome) &&
                (encodeURIComponent(document.getElementById("editDesc").value.trim().escapeForCompare())
                        === encodeURIComponent(item.descrizione.escapeForCompare()));
        if (result) {
            editMessage.innerHTML = "Nulla da modificare!<br>";
        }
    }
    if (result) {
        editMessage.style.display = "inline";
    } else {
        //Codifico i caratteri speciali
        if (prodSel === "nuovo") {
            var nuovoNomeProd = document.getElementById("nuovoProdNome").value;
            nuovoNomeProd = encodeURIComponent(nuovoNomeProd.trim());
        } else {
            prodSel = encodeURIComponent(prodSel);
        }
        if (catSel === "nuova") {
            var nuovoNomeCat = document.getElementById("nuovaCatNome").value;
            nuovoNomeCat = encodeURIComponent(nuovoNomeCat.trim());
        } else {
            prodSel = encodeURIComponent(prodSel);
        }
        var nome = document.getElementById("editName").value;
        nome = encodeURIComponent(nome.trim());
        var descrizione = document.getElementById("editDesc").value;
        descrizione = encodeURIComponent(descrizione.trim());
    }
    result = !result;
    return result;
};
/**
 * Funzione che valida il form di modifica stock
 * @returns false se il form non è valido, true altrimenti
 */
var validaStock = function () {
    var negozi = parametri[3];
    var res = true;
    //per ogni negozio ho una riga
    for (let negozio of negozi) {
        //controllo i prezzi
        for (let prezzo of document.getElementsByName("prezzo" + negozio.idNegozio)) {
            //per ogni input con nome prezzo+idnegozio
            //uso == e non === perchè uno è un int e uno è una stringa
            res = res && (prezzo.value == negozio.prezzo);
        }
        //controllo gli stock
        for (let stock of document.getElementsByName("stock" + negozio.idNegozio)) {
            //per ogni input con nome prezzo+idnegozio
            res = res && (stock.value == negozio.numStock);
        }
    }
    if (res) {
        //se res è rimasto true allora non ho modificato nessun valore
        document.getElementById("stockMessage").style.display = "inline";
    }
    res = !res
    return res;
}

var cambiaFoto = function (i) {
    var listaFoto = parametri[4];
    var item = parametri[2];
    //modifico lo stile dell'elemento selezionato
    var divSelettore = document.getElementById("divSelettore" + i);
    var elementiSelezionati =
            document.getElementsByClassName("divSelettoreSelezionato");
    for (let elem of elementiSelezionati) {
        elem.classList.remove("divSelettoreSelezionato");
    }
    divSelettore.classList.add("divSelettoreSelezionato");

    //aggiornamento thumb
    if (listaFoto[i].idFoto === item.idThumbnail) {
        //mostro il messaggio
        document.getElementById("messaggioImpostaThumb").style.display = "block";
        //nascondo il bottone
        document.getElementById("bottoneImpostaThumb").style.display = "none";
    } else {
        //nascondo il messaggio
        document.getElementById("messaggioImpostaThumb").style.display = "none";
        //mostro il bottone
        document.getElementById("bottoneImpostaThumb").style.display = "block";
        //aggiorno il valore del bottone
        document.getElementById("idImpostaThumb").value = listaFoto[i].idFoto;
    }

    //ingrandisco la foto
    document.getElementById("foto").setAttribute("src", "./img/" + listaFoto[i].linkFoto);
    //imposto l'indirizzo della foto come parametro del form
    document.getElementById("idCancellaFoto").value = listaFoto[i].linkFoto;
};
 