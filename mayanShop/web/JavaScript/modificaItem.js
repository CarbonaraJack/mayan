$(document).ready(function () {
    if (userId === 0) {
        window.location = './alert.jsp?mode=notlog';
    } else if (userType !== "venditore" && userType !== "amministratore") {
        window.location = './alert.jsp?mode=restricted';
    } else if (parametri == null) {
        window.location = "./editItemList";
    } else {
        //separo i parametri per comodità
        var categorie = parametri[0];
        var produttori = parametri[1];
        var item = parametri[2];
        var negozi = parametri[3];
        var listaFoto = parametri[4];
        //var mode = "<%= mode%>";      per ricordarmelo
        console.log(categorie);
        console.log(produttori);
        console.log(item);
        console.log(negozi);
        console.log(listaFoto);
        console.log(mode);

        /** EDITOR INFORMAZIONI **/
        //popolo i due select
        var option;
        var selectProd = document.getElementById("editProd");
        for (let produttore of produttori) {
            option = document.createElement("option");
            option.value = produttore;
            option.innerHTML = produttore;
            selectProd.appendChild(option);
        }
        option = document.createElement("option");
        option.value = "nuovo";
        option.innerHTML = "- nuovo produttore -";
        option.style = "color:red;";
        selectProd.appendChild(option);

        var selectCat = document.getElementById("editCat");
        for (let categoria of categorie) {
            option = document.createElement("option");
            option.value = categoria;
            option.innerHTML = categoria;
            selectCat.appendChild(option);
        }
        option = document.createElement("option");
        option.value = "nuova";
        option.innerHTML = "- nuova categoria -";
        option.style = "color:red;";
        selectCat.appendChild(option);
        //<option value="online">Online</option>
        if (mode === "new") {
            //Nascondo gli altri editor e cambio il layout del grid
            for (let container of document.getElementsByClassName("container")) {
                container.classList.remove("container");
                container.classList.add("container-new");
            }
            document.getElementById("editorStock").style.display = "none";
            document.getElementById("editorFoto").style.display = "none";
        } else {
            //POPOLO L'EDITOR
            document.getElementById("editName").value=item.nome;
            document.getElementById("editDesc").value=item.descrizione;
            document.getElementById("editProd").value=item.produttore;
            document.getElementById("editCat").value=item.categoria;
        }
    }

});
var nuovoProd = function () {
    var selettore = document.getElementById("editProd");
    if (selettore.options[selettore.selectedIndex].value === "nuovo") {
        document.getElementById("nuovoProdDiv").style.display = "block";
    } else {
        document.getElementById("nuovoProdDiv").style.display = "none";
    }
};
var nuovaCat = function () {
    var selettore = document.getElementById("editCat");
    if (selettore.options[selettore.selectedIndex].value === "nuova") {
        document.getElementById("nuovaCatDiv").style.display = "block";
    } else {
        document.getElementById("nuovaCatDiv").style.display = "none";
    }
};
String.prototype.escapeForCompare = function() {
  return this
    .replace(/\b/g, "")
    .replace(/\f/g, "")
    .replace(/\\/g, "\\")
    .replace(/\"/g, "\\\"")
    .replace(/\t/g, "\\t")
    .replace(/\r/g, "")
    .replace(/\n/g, "\\n")
    .replace(/\u2028/g, "\\u2028")
    .replace(/\u2029/g, "\\u2029");
};
var validaForm = function () {
    var item = parametri[2];
    var result;
    var editMessage = document.getElementById("editMessage");

    //controllo se sono cambiati dei parametri
    var editProd = document.getElementById("editProd");
    var editCat = document.getElementById("editCat");
    var prodSel = editProd.options[editProd.selectedIndex].value;
    var catSel = editCat.options[editCat.selectedIndex].value;
    if (prodSel === "nuovo") {
        result = document.getElementById("nuovoProdNome").value.trim() === "";
        editMessage.innerHTML = "Non lasciare il produttore vuoto!<br>";
    }
    if (!result && catSel === "nuova") {
        result = document.getElementById("nuovaCatNome").value.trim() === "";
        editMessage.innerHTML = "Non lasciare la categoria vuota!<br>";
    }
    if (!result && mode !== "new") {
        if (prodSel === "nuovo") {
            result = document.getElementById("nuovoProdNome").value.trim() === item.produttore;
        } else {
            result = prodSel === item.produttore;
        }
        if (catSel === "nuova") {
            result = result &&
                    document.getElementById("nuovaCatNome").value.trim() === item.categoria;
        } else {
            result = result && catSel === item.categoria;
        }
        result = result && (document.getElementById("editName").value.trim() === item.nome) &&
                (encodeURIComponent(document.getElementById("editDesc").value.trim().escapeForCompare())
                === encodeURIComponent(item.descrizione.escapeForCompare()));
        if (result) {
            editMessage.innerHTML = "Nulla da modificare!<br>";
        }
    }
    if (result) {
        editMessage.style.display = "inline";
    } else {
        //Codifico i caratteri speciali
        if (prodSel === "nuovo") {
            var nuovoNomeProd = document.getElementById("nuovoProdNome").value;
            nuovoNomeProd = encodeURIComponent(nuovoNomeProd.trim());
        } else {
            prodSel = encodeURIComponent(prodSel);
        }
        if (catSel === "nuova") {
            var nuovoNomeCat = document.getElementById("nuovaCatNome").value;
            nuovoNomeCat = encodeURIComponent(nuovoNomeCat.trim());
        } else {
            prodSel = encodeURIComponent(prodSel);
        }
        var nome = document.getElementById("editName").value;
        nome = encodeURIComponent(nome.trim());
        var descrizione = document.getElementById("editDesc").value;
        descrizione = encodeURIComponent(descrizione.trim());
    }
    result = !result;
    return result;
};
/**
 var cambiaFoto = function (param, i) {
 //modifico lo stile dell'elemento selezionato
 var divSelettore = document.getElementById("divSelettore" + i);
 var elementiSelezionati =
 document.getElementsByClassName("divSelettoreSelezionato");
 for (let elem of elementiSelezionati) {
 elem.classList.remove("divSelettoreSelezionato");
 }
 divSelettore.classList.add("divSelettoreSelezionato");
 //ingrandisco la foto
 document.getElementById("foto").setAttribute("src", "./img/" + listaNegozi[param].foto[i]);
 //imposto l'indirizzo della foto come parametro del form
 document.getElementById("idCancellaFoto").value = listaNegozi[param].foto[i];
 };
 **/