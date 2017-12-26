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
    
    if (!carrello) {
        s = s + "<div> Non ci sono elementi nel carrello</div>";
    } else {
        for (var i = 0; i < carrello.length; i++) {
            s = s + "<div class='rigaItem'>";
                s = s + "<div class='item'>";
                    s = s + "<img class='itemImage' src='img/000001.jpg'>";
                    s = s + "<div class='itemNome'>" + carrello[i].nome + "</div>";
                    s = s + "<div class='itemInfo'>Possibili info</div>";
                    //s = s + "<div class='itemAzioni'><a href='' onclick=elimina(" + carrello[i].idItem + ")'>Rimuovi</a></div>";
                    s = s + "<div class='itemAzioni'><a href='conrolloCarrello?del=true&idDel=" + carrello[i].idItem + "'>Rimuovi</a></div>";
                    // <a data-confirm="Are you sure?" data-method="delete" href="/link-to-resource" rel="nofollow">Delete</a>
                    // sbagliatos = s + "<div class='itemAzioni'><a data-confirm='Sei sicuro?' data-method='delete' href='controlloCarrello?" + carrello[i].idItem + "'>Rimuovi</a></div>";
                s = s + "</div>";
                s = s + "<div class='prezzo'>" + carrello[i].prezzo + "€</div>";
                s = s + "<div class='quantita'><input id='quantita' name='quantita' min='1' value='" + carrello[i].quantita + "' type='number'></div>";
            s = s + "</div>";
            //$("#tabItems").append(s);           
        }
    }
    document.getElementById("tabItems").innerHTML = s;
});

function elimina (id) {
    
}


