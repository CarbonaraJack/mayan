$(document).ready(function () {
    stampa(oggetti);
});

/**
 * funzione che stampa gli oggetti specificati
 * @param {type} oggetti lista degli oggetti da stampare
 * @returns {undefined}
 */
function stampa(oggetti) {
    var totaleAcquisto = 0;
    for (var i = 0; i < oggetti.length; i++) {
        $("#articoli").append("<div class='rigaItem' id='rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore + "'></div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='containerImgItem' id='containerImgItem" + oggetti[i].idItem + oggetti[i].idVenditore + "'></div>");
        if (!oggetti[i].immagine) {
            $("#containerImgItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("Nessuna immagine da mostrare");
        } else {
            $("#containerImgItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<img class='itemImage' src='img/" + oggetti[i].immagine + "'/>");
        }
        var totale = oggetti[i].prezzo * oggetti[i].quantita;
        totaleAcquisto += totale;
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='nome'>" + oggetti[i].nome + "</div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='produttore'>Prodotto da: " + oggetti[i].produttore + "</div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='venditore'>Venduto da: " + oggetti[i].venditore + "</div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='quantità'>Pezzi: " + oggetti[i].quantita + "</div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='prezzo'>" + totale + "EUR</div>");
        $("#rigaItem" + oggetti[i].idItem + oggetti[i].idVenditore).append("<div class='consegna' id='consegna" + oggetti[i].idItem + oggetti[i].idVenditore + "'>Tipologia di consegna: <br></div>");
        if(oggetti[i].tipologiaVenditore === "fisico"){
            $("#consegna" + oggetti[i].idItem + oggetti[i].idVenditore).append("Consegna in negozio: <input type='radio' name='radio" + oggetti[i].idItem + oggetti[i].idVenditore + "' value='negozio'>");
        }
        $("#consegna" + oggetti[i].idItem + oggetti[i].idVenditore).append("Consegna a casa: <input type='radio' name='radio" + oggetti[i].idItem + oggetti[i].idVenditore + "' value='casa' checked>");
    }
    $("#totaleAcquisto").append("Totale acquisto (" + oggetti.length + " item): " + totaleAcquisto + "EUR");
}
