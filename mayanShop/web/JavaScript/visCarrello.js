/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var s = "<div class='rigaTit'>" +
                "<div class='titItem'>Item</div>" +
                "<div class='titPrezzo'>Prezzo</div>" + 
                "<div class='titQuantità'>Quantità</div>" + 
            "</div>";

    if ((!carrello) || (carrello.length <= 0)) {
        s = s + "<div>Non ci sono elementi nel carrello</div>";
    } else {
        var totale = 0;
        for (var i = 0; i < carrello.length; i++) {
            totale = totale + carrello[i].prezzo;
            s = s + "<div class='rigaItem'>";
                s = s + "<div class='item'>";
                    s = s + "<img class='itemImage' src='img/" + carrello[i].immagine + "'>";
                    s = s + "<div class='itemNome'><a href='controlloItems?ric=false&objS=true&idOgg=" + carrello[i].idItem + "'>" + carrello[i].nome + "</a></div>";
                    s = s + "<div class='itemInfo'>Venduto da: " + carrello[i].venditore + "\nProdotto da: " + carrello[i].produttore + "</div>";
                    s = s + "<div class='itemAzioni'><a href='controlloCarrello?del=true&idDel=" + carrello[i].idItem + "&idNeg=" + carrello[i].idVenditore + "'>Rimuovi</a></div>";
                s = s + "</div>";
                s = s + "<div class='prezzo'>" + carrello[i].prezzo + "€</div>";
                s = s + "<div class='quantita'><input id='quantita" + carrello[i].idItem + carrello[i].idVenditore + "' name='quantita" + carrello[i].idItem + carrello[i].idVenditore + "' min='1' value='" + carrello[i].quantita + "' type='number'></div>";
            s = s + "</div>"; 
            console.log("'quantita" + carrello[i].idItem + carrello[i].idVenditore + "'");
        }
        s = s + "<div class='totCarrello'>Totale (" + carrello.length + " item): " + totale + "€</div>";
    }
    document.getElementById("tabItems").innerHTML = s;
});

