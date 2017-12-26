/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        var url_string = document.URL;
        var url = new URL(url_string);
        var i = url.searchParams.get("index");

        $(document).ready(function () {
            var s = "<div class='itemImage'>" + "<img class='image' src='img/000001.jpg'/></div>";
            s = s + "<div class='itemInformazioni'>";
            s = s + "<div class='itemNome'>" + oggetto.nome + "</div>";
            s = s + "<div class='itemProduttore'>di <a href=''>" + oggetto.produttore + "</a></div>";
            
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
            
            s = s + "<div class='itemPrice'>Prezzo: " + oggetto.prezzo + "€</div>";
            s = s + "<div class='itemDescrizione'>" + oggetto.descrizione + "</div>";
            
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
            $("#containerItem").append(s);
        });

