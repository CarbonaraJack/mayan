<%-- 
    Document   : carrello.jsp
    Created on : 6-nov-2017, 10.26.48
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/carrello.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link href="Styles/DisplayObject.css" rel="stylesheet" type="text/css"/>
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String resCarrello = (String) session.getAttribute("carrello");
    %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var carrello = <%= resCarrello%>;
    </script>
    <script src="JavaScript/displayCarrello.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <h1>Carrello</h1>
                <div class="tabItems" id="tabItems">
                    <%--
                    <div class="rigaTit">
                        <div class="titItem">Item</div>
                        <div class="titPrezzo">Prezzo</div>
                        <div class="titQuantità">Quantità</div> 
                    </div>
                    <div class="rigaItem">
                        <div class="item">
                            <img class="itemImage" src="img/000001.jpg">
                            <div class="itemNome">Pantofole</div>
                            <div class="itemInfo">hsalhfdsjlfhb</div>
                            <div class="itemAzioni">Rimuovi</div>
                        </div>
                        <div class="prezzo">8€</div>
                        <div class="quantita"><input id="quantita" name="quantita" min="1" value="1" type="number"></div> 
                    </div>
                    --%>
                </div>
                <form name="search" action="../controlloItems" method="GET">
                    <input type="submit" value="Acquista"/>
                </form>
            </div>
        </div>
    </body>
</html>
