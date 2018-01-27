/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var s = "<div class='rigaTit'>" +
                //"<div class='titTransazione'>Id Transazione </div" +
                "<div class='titItem'>Item</div>" +
                "<div class='titPrezzo'>Prezzo</div>" +
                "<div class='titQuantità'>Quantità</div>" +
            "</div>";

    if ((!lista) || (lista.length <= 0)) {
        s = s + "<div>Non hai ancora fatto acquisti</div>";
    } else {
        for (var i = 0; i < lista.length; i++) {
            s = s + "<div class='rigaItem'>";
            //s = s + "<div class='transazione'>" + lista[i].idTransazione;
                s = s + "<div class='item'>";
                    s = s + "<img class='itemImage' src='img/" + lista[i].linkFoto + "'>";
                    s = s + "<div class='itemNome'><a href='controlloItems?id=" + lista[i].idItem + "'>" + lista[i].nomeItem + "</a></div>";
                    s = s + "<div class='itemInfo'>Acquistato da: " + lista[i].nomeNegozio + "\nIn data: " + lista[i].dataora + "</div>";
                    //Inserire codicer per pulsante
                    s = s + "<div class='itemAzioni'><a href=''>Invia una segnalazione</a></div>";
                s = s + "</div>";
                s = s + "<div class='prezzo'>Prezzo: " + lista[i].prezzo + "€</div>";
                s = s + "<div class='quantita'>Quantità: " + lista[i].quantità + "</div>";
            s = s + "</div>";            
        }
    }
    document.getElementById("tabAcquisti").innerHTML = s;
});

