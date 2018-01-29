$(document).ready(function () {
    // se non ci sono oggetti da visualizzare, la pagin aviene reindirizzata alla servlet per cercare gli oggetti da visualizzare
    if (((!oggettiVis) || (oggettiVis.length <= 0)) && ((!oggettiAcq) || (oggettiAcq.length <= 0))) {
        window.location.replace("./index");
    } else {
        stampa(oggettiVis, "containerItemVis", "Vis");
        stampa(oggettiAcq, "containerItemAcq", "Acq");
    }
});

/**
 * funzione che stampa la lista di oggetti specifiacata
 * @param {type} oggetti lista di oggetti da stampare
 * @param {type} divId id del tag div a cui appendere la lista di oggetti
 * @param {type} p parametro da aggiungere all'id
 * @returns {undefined}
 */
function stampa(oggetti, divId, p) {
    for (var i = 0; i < oggetti.length; i++) {
        $("#" + divId).append("<div class='itemBox' id='item" + oggetti[i].idItem + p + "'></div>");

        $("#item" + oggetti[i].idItem + p).append("<div class='itemImageContainer' id='image" + oggetti[i].idItem + p + "'></div>");
        if (!oggetti[i].immagine) {
            $("#image" + oggetti[i].idItem + p).append("Nessuna immagine da mostrare");
        } else {
            $("#image" + oggetti[i].idItem + p).append("<a href='controlloItems?idOgg=" + oggetti[i].idItem + "'><img class='itemImage' src='img/" + oggetti[i].immagine + "'/></a>");
        }
        $("#item" + oggetti[i].idItem + p).append("<div class='itemName'><a href='controlloItems?idOgg=" + oggetti[i].idItem + "'>" + oggetti[i].nome + "</a></div>");
        $("#item" + oggetti[i].idItem + p).append("<div class='itemProduttore'>" + oggetti[i].produttore + "</div>");
        $("#item" + oggetti[i].idItem + p).append("<div class='itemPrice'>" + oggetti[i].prezzoMinimo + "€</div>");

        $("#item" + oggetti[i].idItem + p).append("<div class='itemStars' id='itemStars" + oggetti[i].idItem + p + "'>");
        var stars = oggetti[i].voto;
        for (var j = 1; j <= 5; j++) {
            if (j <= stars) {
                $("#itemStars" + oggetti[i].idItem + p).append("<span class='fa fa-star checked'></span>");
            } else {
                $("#itemStars" + oggetti[i].idItem + p).append("<span class='fa fa-star'></span>");
            }
        }
    }
}

