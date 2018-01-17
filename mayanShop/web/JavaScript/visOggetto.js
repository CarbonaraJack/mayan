/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        /*var url_string = document.URL;
        var url = new URL(url_string);
        var i = url.searchParams.get("index");*/
/*
        $(document).ready(function () {
            var s = "<div class='itemImage'>" + "<img class='image' src='img/000001.jpg'/></div>";
            s = s + "<div class='itemInformazioni'>";
            s = s + "<div class='itemNome'>" + oggetto.nome + "</div>";
            s = s + "<div class='itemProduttore'>di " + oggetto.produttore + "</div>";
            
            s = s + "<div class='itemStars'>";
            var stars = oggetto.voto;
            for (var j = 1; j <= 5; j++) {
                if (j <= stars) {
                    s = s + "<span class='fa fa-star checked'></span>";
                } else {
                    s = s + "<span class='fa fa-star'></span>";
                }
            }
            s = s + "</div>";
            
            //s = s + "<div class='itemPrice'>Prezzo: " + oggetto.prezzo + "€</div>";
            s = s + "<div class='itemDescrizione'>" + oggetto.descrizione + "</div>";
            
            if ((!oggetto.negozi) || (oggetto.negozi.length <= 0)) {
                s = s + "<div> L'item non è disponibile in nessun negozio</div>";
            } else {
                for (var i = 0; i < oggetto.negozi.length; i++) {
                    s = s + "<div class='rigaNegozio'>";
                        s = s + "<div class='nomeNeg'><a href='controlloNegozi?idNegozio=" + oggetto.negozi[i].idNegozio + "'>" + oggetto.negozi[i].nomeNegozio + "</a></div>";
                        s = s + "<div class='prezzoItem'>Prezzo: " + oggetto.negozi[i].prezzo + "€</div>";
                    s = s + "</div>";
                } 
            }
            
            s = s + "<div class='itemCarrello'>";
                s = s + "<a href='./controlloCarrello?item=" + oggetto.idItem + "&quant=1&del=false'>";
                    s = s + "<button class='carrello'>Aggiungi al carrello</button>"
                s = s + "</a>";
            s = s + "</div>";
            
            //s = s + "<div class='itemCarrello'><form name='search' action='./controlloCarrello?item=" + oggetto.idItem + "&quant=1 method='GET'>";
            //s = s + "<input type='submit' value='Aggiungi al carrello' class='carrello'/>";
            //s = s + "</form></div>";

            s = s + "</div>";
            
            s = s + "</div>";
            //$("#containerItem").append(s);
            document.getElementById("containerItem").innerHTML = s;
        });
*/
$(document).ready(function () {
    $("#containerItem").append("<div class='itemImage' id='itemImage'></div>");
    $("#itemImage").append("<img class='image' src='img/000001.jpg'/>");
    
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
            $("#containerNegozi").append("<div class='rigaNegozio' id='rigaNegozio" + i + "'></div>");
            $("#rigaNegozio" + i).append("<div class='infoNeg' id='infoNeg"+i+"'></div>");
            $("#infoNeg" + i).append("<div class='nomeNeg'><a href='controlloNegozi?idNegozio=" + oggetto.negozi[i].idNegozio + "'>" + oggetto.negozi[i].nomeNegozio + "</a></div>")
            $("#infoNeg" + i).append("<div class='prezzoItem'>Prezzo: " + oggetto.negozi[i].prezzo + "€</div>");
            
            $("#rigaNegozio" + i).append("<div class='itemCarrello' id='itemCarrello'></div>")
            $("#itemCarrello").append("<a href='./controlloCarrello?item=" + oggetto.idItem + "&negozio=" + oggetto.negozi[i].idNegozio + "&quant=1&del=false' id='carNeg" + oggetto.negozi[i].idNegozio + "'></a>");
            $("#carNeg" + oggetto.negozi[i].idNegozio).append("<button class='carrello'>Aggiungi al carrello</button>");
        }
    }    
});