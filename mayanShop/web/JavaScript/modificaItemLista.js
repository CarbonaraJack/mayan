$(document).ready(function () {
    if (userId === 0) {
        window.location = './alert.jsp?mode=notlog';
    } else if (userType !== "venditore" && userType !== "amministratore") {
        window.location = './alert.jsp?mode=restricted';
    } else if (listaItems == null) {
        window.location = "./editItemList";
    } else {
        console.log(listaItems);

        /** listaItems **/
        var selettoreItem = document.getElementById("selettoreItem");
        for (var i = 0; i < listaItems.length; i++) {
            var item = listaItems[i];
            //creazione elementi
            var riga = document.createElement("div");
            riga.setAttribute("class", "selettoreRiga");
            var nome = document.createElement("div");
            nome.setAttribute("class", "selettoreNome");
            var produttore = document.createElement("div");
            produttore.setAttribute("class", "selettoreProduttore");
            var categoria = document.createElement("div");
            categoria.setAttribute("class", "selettoreCategoria");
            var cellaModifica = document.createElement("div");
            cellaModifica.setAttribute("class", "cellaModifica");
            var bottone = document.createElement("button");
            bottone.innerHTML = "Modifica";
            //popolamento elementi
            nome.innerHTML = item.nome;
            produttore.innerHTML = item.produttore;
            categoria.innerHTML = item.categoria;
            bottone.onclick =
                    function () {
                        window.location =
                                "./editItem?mode=edit&item=" + item.idItem;
                    };

            //attaccamento elementi
            cellaModifica.appendChild(bottone);
            riga.appendChild(nome);
            riga.appendChild(produttore);
            riga.appendChild(categoria);
            riga.appendChild(cellaModifica);
            selettoreItem.appendChild(riga);
        }
        var selettorePagina = document.getElementById("selettorePagina");
        if (mode === "post") {
            selettorePagina.style.display = "none";
            //aggiungo la riga per inserire il nuovo item
            //creazione elementi
            var riga = document.createElement("div");
            riga.setAttribute("class", "selettoreRigaFoot");
            var nome = document.createElement("div");
            nome.setAttribute("class", "selettoreNome");
            var produttore = document.createElement("div");
            produttore.setAttribute("class", "selettoreProduttore");
            var categoria = document.createElement("div");
            categoria.setAttribute("class", "selettoreCategoria");
            var cellaModifica = document.createElement("div");
            cellaModifica.setAttribute("class", "cellaModifica");
            var bottone = document.createElement("button");
            bottone.innerHTML = "Inserisci";
            //popolamento elementi
            nome.innerHTML = "Nuovo oggetto";
            bottone.onclick =
                    function () {
                        window.location = "./editItem?mode=new";
                    };
            //attaccamento elementi
            cellaModifica.appendChild(bottone);
            riga.appendChild(nome);
            riga.appendChild(produttore);
            riga.appendChild(categoria);
            riga.appendChild(cellaModifica);
            selettoreItem.appendChild(riga);
        } else {
            selettorePagina.style.display = "block";
        }
    }

});