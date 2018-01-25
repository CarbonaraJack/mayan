<%-- 
    Document   : visLista
    Created on : 10-ott-2017, 13.49.01
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/visLista.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>  
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String resItems = (String) session.getAttribute("listaItems");
    %>
    <script>
        var oggetti = <%= resItems%>;
    </script>
    <script src="JavaScript/visListaOggetti.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="sidebar">
                <div class="titSidebar">
                    Filtri:
                </div>
                <div class="filtri">
                    <button class="collapsible">Regione</button>
                    <div class="content">
                        <input type="checkbox" name="regione" value="trentino"> Trentino-Alto Adige<br>
                        <input type="checkbox" name="regione" value="veneto"> Veneto<br>
                        <input type="checkbox" name="regione" value="lombardia"> Lombardia<br>
                        <input type="checkbox" name="regione" value="piemonte"> Piemonte<br>
                    </div>
                    <button class="collapsible">Categoria</button>
                    <div class="content">
                        <input type="checkbox" id="checkLibri" name="regione" value="libri" onclick="addFilter('checkLibri')"> Libri<br>
                        <input type="checkbox" id="checkElettronica" name="regione" value="elettronica" onclick="addFilter('checkElettronica')"> Elettronica<br>
                        <input type="checkbox" id="checkAbbigliamento" name="regione" value="abbigliamento" onclick="addFilter('checkAbbigliamento')"> Abbigliamento<br>
                        <input type="checkbox" id="checkGiardinaggio" name="regione" value="giardinaggio" onclick="addFilter('checkGiardinaggio')"> Giardinaggio<br>
                        <input type="checkbox" id="checkCasalinghi" name="regione" value="casalinga" onclick="addFilter('checkCasalinghi')"> Casalinghi<br>
                    </div>
                    <div class="slideContainerValutazione">
                        Valutazione:<br>
                        <input type="range" min="0" max="5" value="3" class="slider" id="sliderValutazione">
                    </div>
                    <div class="slideContainerDistanza">
                        Prezzo:<br>
                        <input type="range" min="1" max="100" value="50" class="slider" id="sliderDistanza">
                    </div>
                    <button>Rimuovi filtri</button>
                </div>
                <script>
                    var coll = document.getElementsByClassName("collapsible");
                    var i;

                    for (i = 0; i < coll.length; i++) {
                      coll[i].addEventListener("click", function() {
                        this.classList.toggle("active");
                        var content = this.nextElementSibling;
                        if (content.style.maxHeight){
                          content.style.maxHeight = null;
                        } else {
                          content.style.maxHeight = content.scrollHeight + "px";
                        }
                      });
                    }
                </script>
                <div class="ordinamento">
                    Ordina per:<br>
                    <div value="prezzo"><a onclick="ordinaPrezzo()">prezzo</a></div>
                    <div value="valutazione"><a onclick="ordinaValutazione()">valutazione</a></div>
                </div>
            </div>
            <div class="main">
                <div class="containerItem" id="containerItem">

                </div>
            </div>
        </div>
    </body>
</html>
