$(document).ready(function () {
    if (userId === 0) {
        console.log(userId);
        window.location = './alert.jsp?mode=notlog';
    } else {
        if (userType !== "venditore") {
            window.location = './alert.jsp?mode=restricted';
        } else {
            console.log(listaNegozi);
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
    }
    /**
     //prendo il parametro err dal get
     var err = $(document).getUrlParam("err");
     var updateMessage = document.getElementById("updateMessage");
     var passwordMessage = document.getElementById("passwordMessage");
     if(err==="u1"){
     updateMessage.style.display="inline";
     updateMessage.innerHTML="Nessuna informazione da aggiornare.";
     }
     */
});

var visualizzaNegozio = function (param) {
    //passo il parametro alla funzione check
    document.getElementById("editForm").setAttribute("onsubmit", "return validateForm(\"" + param + "\");");
    var bottone = document.getElementById("visitNegozio");
    if (bottone !== null) {
        bottone.parentNode.removeChild(bottone);
    }
    if (param === "nuovo") {
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
    }
    ;
};

var validateForm = function (param) {
    var result;
    //controllo se sono cambiati dei parametri
    if (param === "nuovo") {
        result = (document.getElementById("editName").value === "") &&
                (document.getElementById("editLink").value === "") &&
                (document.getElementById("editType").value === "online") &&
                (document.getElementById("editDesc").innerHTML === "") &&
                (document.getElementById("editHour").innerHTML === "");
    } else {
        result = (document.getElementById("editName").value === listaNegozi[param].nome) &&
                (document.getElementById("editLink").value === listaNegozi[param].webLink) &&
                (document.getElementById("editType").value === listaNegozi[param].tipo) &&
                (document.getElementById("editDesc").innerHTML === listaNegozi[param].descrizione) &&
                (document.getElementById("editHour").innerHTML === listaNegozi[param].orari);
    }
    if (result) {
        var editMessage = document.getElementById("editMessage");
        editMessage.innerHTML = "Nulla da aggiornare!<br>";
        editMessage.style.display = "inline";
    }
    result = !result;
    return result;
};