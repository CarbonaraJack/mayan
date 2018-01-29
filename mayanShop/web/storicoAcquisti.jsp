<%-- 
    Document   : storicoAquisti
    Created on : 26-gen-2018, 16.57.37
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/storico.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
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
                <form action="" method="post">
                    <h3 id="titoloPopup"> Titolo</h3>
                    <input type="hidden" id="idForm" name="idForm"/>
                    <input type="hidden" id="modeForm" name="modeForm"/>
                    <textarea id="recensione" name="recensione"></textarea>
                    <label>Valutazione:</label>
                    <input type="submit" id="submit" value="Invia recensione"/>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
