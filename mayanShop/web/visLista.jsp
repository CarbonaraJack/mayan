<%-- 
    Document   : visLista
    Created on : 10-ott-2017, 13.49.01
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/><link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/visLista.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/visLista.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
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
        String scelta = (String) session.getAttribute("selectRicerca");
        String ricercato = (String) session.getAttribute("ricercato");
    %>
    <script>
        var oggetti = <%= resItems%>;
        var sceltaRicerca = <%= scelta%>;
        var queryRicerca = <%= ricercato%>;
    </script>
    <script src="JavaScript/visListaOggetti.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="bottonelaterale"><button onclick="openside()">F<br>I<br>L<br>T<br>R<br>I</button></div>
            <div class="sidebar" id="mySidnav">
                <div class="titSidebar" onclick="closeside()">
                    Filtri:
                </div>
                <div class="filtri" id="containerFiltri">
                <button type="button" id="bottonechiusura" onclick="closeside()">CHIUDI</button>
                    <div id="filtroRegione">
                        <button class="collapsible" id="collapseReg">Regione</button>
                        <div class="content">
                            <input type="checkbox" id="checkLazio" name="regione" value="Lazio" onclick="addFilterReg('checkLazio')"> Lazio<br>
                            <input type="checkbox" id="checkLombardia" name="regione" value="Lombardia" onclick="addFilterReg('checkLombardia')"> Lombardia<br>
                            <input type="checkbox" id="checkTrentino" name="regione" value="Trentino Alto Adige" onclick="addFilterReg('checkTrentino')"> Trentino Alto Adige<br>
                            <input type="checkbox" id="checkVeneto" name="regione" value="Veneto" onclick="addFilterReg('checkVeneto')"> Veneto<br>
                        </div>
                    </div>
                    <div id="filtroCategoria">

                    </div>
                    <br>
                    <div class="slideContainerValutazione" id="slideContainerValutazione">
                        Valutazione:<br>
                        <input type="range" min="0" max="5" value="3" class="slider" id="sliderValutazione">
                        <label id="labelValutazione"></label>
                    </div>
                    <div class="slideContainerDistanza" id="slideContainerDistanza">
                        Distanza(km):<br>
                        <input type="range" min="1" max="200" value="5" class="slider" id="sliderDistanza">
                        <label id="labelDistanza"></label>
                    </div>
                    <br>
                    <button onclick="reset()">Rimuovi filtri</button>
                    <br><br><br>Ordina per:<br><br>
                    <button class="collapsible" id="ordinaDistanza">Distanza</button>
                    <div class="content">
                        <input type="radio" name="radioDistanza" value="cresc"> Crescente<br>
                        <input type="radio" name="radioDistanza" value="decr"> Decrescente
                    </div>
                    <button class="collapsible" id="ordinaValutazione">Valutazione</button>
                    <div class="content">
                        <input type="radio" name="radioValutazione" value="cresc"> Crescente<br>
                        <input type="radio" name="radioValutazione" value="decr"> Decrescente
                    </div>
                </div>
            </div>
            <div class="main">
                <div class="intestazione">
                    <div>
                        <h2>RISULTATI:</h2>
                    </div>
                </div>
                <div class="containerItem" id="containerItem">

                </div>
            </div>
        </div>
        <div class="footer"><div class="footertext"> Mayan&reg;</div></div>

        <script>
            function openside() {
                document.getElementById("mySidnav").style.width = "200px";
            }

            function closeside() {
                document.getElementById("mySidnav").style.width = "0";
            }

        </script>
    </body>
</html>
