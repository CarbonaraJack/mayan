/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //$("#containerNegozio").append("<div class='negImage' id='negImage'></div>");
    //$("#negImage").append("<img class='image' src='img/" + negozio.foto[0] + "' alt='Foto " + negozio.nome + "'/>");
	if (negozio.foto.length !== 0) {
            document.getElementById("visualizzatoreFoto").style.display = "grid";
            document.getElementById("visualizzatoreFotoVuoto").style.display =
                    "none";
	var selettoreFoto = document.getElementById("selettoreFoto");
            //cancello i figli del selettore
            while(selettoreFoto.firstChild){
                selettoreFoto.removeChild(selettoreFoto.firstChild);
            }
	for (var i = 0; i < negozio.foto.length; i++) {
                var divSelettore = document.createElement("div");
                var imgSelettore = document.createElement("img");
                imgSelettore.setAttribute("src", "./img/" + negozio.foto[i]);
                imgSelettore.classList.add("imgSelettore");
                divSelettore.classList.add("divSelettore");
                divSelettore.id = "divSelettore" + i;
                divSelettore.setAttribute("onclick", "cambiaFoto("+ i + ");");
                divSelettore.appendChild(imgSelettore);
                selettoreFoto.appendChild(divSelettore);
            }
			cambiaFoto(0);
        } else {
            document.getElementById("visualizzatoreFoto").style.display = "none";
            document.getElementById("visualizzatoreFotoVuoto").style.display =
                    "grid";
        }
    
    $("#containerNegozio").append("<div class='negInformazioni' id='negInformazioni'></div>");
    $("#negInformazioni").append("<div class='negNome'>" + negozio.nome + "</div>");
    $("#negInformazioni").append("<div class='negLink'><a href='" + negozio.webLink + "' target='_blank'>Vai al sito</a></div>");
    $("#negInformazioni").append("<div class='negOrari'>Orari: " + negozio.orari + "</div>");
    $("#negInformazioni").append("<div class='negDescrizione'>" + negozio.descrizione + "</div>");
    
    if ((!negozio.items) || (negozio.items.length <= 0)) {
        $("#containerNegozio").append("<div class='negProdotti'> Questo negozio non ha item disponibili</div>");
    } else {
        for (var i = 0; i < negozio.items.length; i++) {
            $("#containerNegozio").append("<div class='rigaProdotti' id='rigaProdotti" + negozio.items[i].idItem + "'></div>");
            $("#rigaProdotti" + negozio.items[i].idItem).append("<div class='nomeProdotto'><a href='controlloItems?idOgg=" + negozio.items[i].idItem + "'>" + negozio.items[i].nome + "</a></div>");
            $("#rigaProdotti" + negozio.items[i].idItem).append("<div class='prezzoProdotti'>Prezzo: " + negozio.items[i].prezzoMinimo + "€</div>");
        }
    }
    
    if (negozio.tipo === "online") {
        $("#negInformazioni").append("<div class='negTipo'>I prodotti di questo negozio sono disponibili solo online.</div>");
    } else {
        $("#negInformazioni").append("<div class='negTipo'>Per i prodotti di questo negozio è possibile il ritiro in negozio.</div>");
    }
              
    $("#negInformazioni").append("<div class='negStars' id='negStars'></div>");
    var stars = negozio.valutazioneMedia;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            $("#negStars").append("<span class='fa fa-star checked'></span>");
        } else {
            $("#negStars").append("<span class='fa fa-star'></span>");
        }
    }
});


var cambiaFoto = function (i) {
    //modifico lo stile dell'elemento selezionato
    var divSelettore = document.getElementById("divSelettore" + i);
    var elementiSelezionati =
            document.getElementsByClassName("divSelettoreSelezionato");
    for (let elem of elementiSelezionati) {
        elem.classList.remove("divSelettoreSelezionato");
    }
    divSelettore.classList.add("divSelettoreSelezionato");
    //ingrandisco la foto
    document.getElementById("foto").setAttribute("src", "./img/" + negozio.foto[i]);
    //imposto l'indirizzo della foto come parametro del form
    // document.getElementById("idCancellaFoto").value = oggetto.foto[i];
  };