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
            totale = totale + (carrello[i].prezzo*carrello[i].quantita);
            s = s + "<div class='rigaItem'>";
                s = s + "<div class='item'>";
                if(!carrello[i].immagine){
                    s = s + "<div>Nessuna foto da mostrare</div>";
                } else {
                    s = s + "<div><img class='itemImage' src='img/" + carrello[i].immagine + "'></div>";
                }
                    s = s + "<div class='itemNome'><a href='controlloItems?ric=false&objS=true&idOgg=" + carrello[i].idItem + "'>" + carrello[i].nome + "</a></div>";
                    s = s + "<div class='itemInfo'>Venduto da: " + carrello[i].venditore + "\nProdotto da: " + carrello[i].produttore + "</div>";
                    s = s + "<div class='itemAzioni'><a href='controlloCarrello?del=true&idDel=" + carrello[i].idItem + "&idNeg=" + carrello[i].idVenditore + "'>Rimuovi</a></div>";
                s = s + "</div>";
                s = s + "<div class='prezzo'>" + carrello[i].prezzo + "€</div>";
                s = s + "<div class='quantita'><input id='quantita" + carrello[i].idItem + carrello[i].idVenditore + "' name='quantita" + carrello[i].idItem + carrello[i].idVenditore + "' min='1' value='" + carrello[i].quantita + "' type='number'></div>";
            s = s + "</div>"; 
        }
        s = s + "<div class='totCarrello'>Totale (" + carrello.length + " item): " + totale + "€</div>";
    }
    document.getElementById("tabItems").innerHTML = s;
});

