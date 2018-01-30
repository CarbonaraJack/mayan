/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        /*var url_string = document.URL;
        var url = new URL(url_string);
        var i = url.searchParams.get("index");*/

/* global oggetto */

$(document).ready(function () {
    //$("#containerItem").append("<div class='itemImage' id='itemImage'></div>");
    //$("#itemImage").append("<img class='image' src='img/" + oggetto.foto[0] + "'/>");
    // riempiV(0);
	if (oggetto.foto.length !== 0) {
            document.getElementById("visualizzatoreFoto").style.display = "grid";
            document.getElementById("visualizzatoreFotoVuoto").style.display =
                    "none";
	var selettoreFoto = document.getElementById("selettoreFoto");
            //cancello i figli del selettore
            while(selettoreFoto.firstChild){
                selettoreFoto.removeChild(selettoreFoto.firstChild);
            }
	for (var i = 0; i < oggetto.foto.length; i++) {
                var divSelettore = document.createElement("div");
                var imgSelettore = document.createElement("img");
                imgSelettore.setAttribute("src", "./img/" + oggetto.foto[i]);
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
	
    $("#containerItem").append("<div class='itemInformazioni' id='itemInformazioni'></div>");
    $("#itemInformazioni").append("<div class='itemNome'>" + oggetto.nome + "</div>");
    $("#itemInformazioni").append("<div class='itemProduttore'>di " + oggetto.produttore + "</div>");
    
    $("#itemInformazioni").append("<div class='itemStars' id='itemStars'></div>");
    var stars = oggetto.voto;
    for (var j = 1; j <= 5; j++) {
        if (j <= stars) {
            $("#itemStars").append("<span class='fa fa-star checked'></span>");
        } else {
            $("#itemStars").append("<span class='fa fa-star'></span>");
        }
    }
    
    $("#itemInformazioni").append("<div class='itemDescrizione' id='itemDescrizione'>" + oggetto.descrizione + "</div>");
    
    $("#containerItem").append("<div class='containerNegozi' id='containerNegozi'></div>");
    
    if ((!oggetto.negozi) || (oggetto.negozi.length <= 0)) {
        $("#containerNegozi").append("<div> L'item non è disponibile in nessun negozio</div>");
    } else {
        for (var i = 0; i < oggetto.negozi.length; i++) {
            if(oggetto.negozi[i].numStock>0){
                $("#containerNegozi").append("<div class='rigaNegozio' id='rigaNegozio" + oggetto.negozi[i].idNegozio + "'></div>");
                $("#rigaNegozio" + oggetto.negozi[i].idNegozio).append("<div class='infoNeg' id='infoNeg" + oggetto.negozi[i].idNegozio + "'></div>");
                $("#infoNeg" + oggetto.negozi[i].idNegozio).append("<div class='nomeNeg'><a href='controlloNegozi?idNegozio=" + oggetto.negozi[i].idNegozio + "'>" + oggetto.negozi[i].nomeNegozio + "</a></div>")
                $("#infoNeg" + oggetto.negozi[i].idNegozio).append("<div class='prezzoItem'>Prezzo: " + oggetto.negozi[i].prezzo + "€</div>");

                //$("#rigaNegozio" + oggetto.negozi[i].idNegozio).append("<div class='itemCarrello' id='itemCarrello" + oggetto.negozi[i].idNegozio + "'></div>")
                //$("#itemCarrello" + oggetto.negozi[i].idNegozio).append("<a href='./controlloCarrello?item=" + oggetto.idItem + "&negozio=" + oggetto.negozi[i].idNegozio + "&quant=1&del=false' id='carNeg" + oggetto.negozi[i].idNegozio + "'></a>");
                //$("#carNeg" + oggetto.negozi[i].idNegozio).append("<button class='carrello'>Aggiungi al carrello</button>");

                $("#rigaNegozio" + oggetto.negozi[i].idNegozio).append("<div class='itemCarrello' id='itemCarrello" + oggetto.negozi[i].idNegozio + "'></div>");
                $("#itemCarrello" + oggetto.negozi[i].idNegozio).append("<form name='acquista' id='acquista" + oggetto.negozi[i].idNegozio + "' action='./controlloCarrello' method='GET'></form>");
                $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='item' value='"+oggetto.idItem+"'>");
                $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='negozio' value='"+oggetto.negozi[i].idNegozio+"'>");
                $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='hidden' name='del' value='false'>");
                $("#acquista" + oggetto.negozi[i].idNegozio).append("<div class='quantita'><input id='quantita" + oggetto.idItem + oggetto.negozi[i].idNegozio + "' name='quantita" + oggetto.idItem + oggetto.negozi[i].idNegozio + "' min='1' max='"+oggetto.negozi[i].numStock+"' value='1' type='number'></div>")
                $("#acquista" + oggetto.negozi[i].idNegozio).append("<input type='submit' value='Aggiungi al carrello'/>");
            }
        }
    }
    
    $("#containerItem").append("<div class='containerRecensioni' id='containerRecensioni'></div>");
    
    if ((!oggetto.recensioni) || (oggetto.recensioni.length <= 0)) {
        $("#containerRecensioni").append("<div> Non sono disponibili recensioni per questo oggetto</div>");
    } else {
        for (var i = 0; i < oggetto.recensioni.length; i++) {
            $("#containerRecensioni").append("<div class='recensioni' id='recensioni" + i + "'></div>");
            $("#recensioni" + i).append("- RECENSIONE di: " + oggetto.recensioni[i].nomeAutore + " " + oggetto.recensioni[i].cognomeAutore + ": " + oggetto.recensioni[i].testo + "<br>");
            if (oggetto.recensioni[i].risposta !== undefined){
                $("#recensioni" + i).append("--- RISPOSTA di: " + oggetto.recensioni[i].risposta.nomeAutore + " " + oggetto.recensioni[i].risposta.cognomeAutore + ": " + oggetto.recensioni[i].risposta.testo + "<br>")
            }
            $("#recensioni" + i).append("<br>");
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
    document.getElementById("foto").setAttribute("src", "./img/" + oggetto.foto[i]);
    //imposto l'indirizzo della foto come parametro del form
    // document.getElementById("idCancellaFoto").value = oggetto.foto[i];
  };
   
  
