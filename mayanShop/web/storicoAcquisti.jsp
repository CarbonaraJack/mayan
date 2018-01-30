<%-- 
    Document   : storicoAquisti
    Created on : 26-gen-2018, 16.57.37
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/storico.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/storico.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
         <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>         

        <title>Mayan - Storico Aquisti</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String listaStorico = (String) session.getAttribute("listaStorico");
    %> 
    <body> 

        <script>
            var lista = <%=listaStorico%>;
        </script>
        <script src="JavaScript/storicoAcquisti.js"></script>

        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <h1>I Miei Ordini</h1>
                <div class="tabAcquisti" id="tabAcquisti">
                </div>

            </div>
        </div>

    </div>
    <div id="bgFader">
        <div id="containerPopup">
            <div id="contenutoPopup">
                <span id="chiudi">&times;</span>
                <div id="formRecensione">
                    <form action="./aggiungiRecensione" onsubmit="return validaForm();" method="post">
                        <h3 id="titoloPopup"> Titolo</h3>
                        <input type="hidden" id="idRecensione" name="idForm"/>
                        <input type="hidden" id="modeRecensione" name="modeForm"/>
                        <textarea id="testoRecensione" name="recensione" required></textarea>
                        <label>Valutazione:</label>
                        <span class="starRating">
                            <input id="rating5" type="radio" name="rating" value="5">
                            <label for="rating5">5</label>
                            <input id="rating4" type="radio" name="rating" value="4">
                            <label for="rating4">4</label>
                            <input id="rating3" type="radio" name="rating" value="3">
                            <label for="rating3">3</label>
                            <input id="rating2" type="radio" name="rating" value="2">
                            <label for="rating2">2</label>
                            <input id="rating1" type="radio" name="rating" value="1">
                            <label for="rating1">1</label>
                        </span>
                        <label id="messaggioRecensione">Inserisci una valutazione per favore</label>
                        <input type="submit" id="submit" value="Invia recensione"/>
                    </form>
                </div>
                <div id="formSegnalazione">
                    <form action="./inviaSegnalazione" onsubmit="return validaSegnalazione();" method="post">
                        <h3> Che problema hai riscontrato?</h3>
                        <input type="hidden" id="idNegozio" name="idNegozio"/>
                        <input type="hidden" id="idTransazione" name="idTransazione"/>
                        <textarea id="testoSegnalazione" name="testo" required></textarea>
                        <input type="submit" id="submit" value="Invia segnalazione"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
        <div class="footer"><div class="footertext"> Mayan&reg;</div></div>
</body>
</html>
