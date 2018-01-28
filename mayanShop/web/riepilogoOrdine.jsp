<%--
    Document   : riepilogoOrdine
    Created on : 21-gen-2018, 16.48.56
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" media='only screen and (min-width: 530px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 530px)' rel="stylesheet" type="text/css" />
        <link href="Styles/riepilogoOrdine.css" media='only screen and (min-width: 530px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/riepilogoOrdine.css" media='only screen and (max-width: 530px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">

        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script src="JavaScript/lib/jquery.autocomplete.js"></script> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mayan - Riepilogo ordine</title>
    </head>
    <%
        String resItems = (String) session.getAttribute("carrello");

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("./index");
        }
    %>
    <script>
        var oggetti = <%= resItems%>;
    </script>
    <script src="JavaScript/riepilogoOrdine.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <h1>Riepilogo ordine</h1>
                <form name="search" action="./controlloCarrello" method="POST">
                    <div class="containerRiepilogo" id="containerRiepilogo">
                        <div class="indirizzo" id="indirizzo">
                            <h2>Indirizzo di consegna: </h2>
                            <label>Nome e cognome: </label><br>
                            <input type="text" name="nomeCognome" required><br>
                            <label>Indirizzo: </label><br>
                            <input type="text" name="indirizzo" required><br>
                            <label>Città: </label><br>
                            <input type="text" name="citta" required><br>
                            <label>Provincia: </label><br>
                            <input type="text" name="provincia" required><br>
                            <label>CAP: </label><br>
                            <input type="text" name="cap" required><br>
                            <label>Paese: </label><br>
                            <input type="text" name="paese" required><br>
                            <label>Numero di telefono: </label><br>
                            <input type="text" name="numTel" required><br>
                        </div>
                        <div class="pagamento" id="pagamento">
                            <h2>Modalità di pagamento:</h2>
                            <label>Numero carta di credito: </label><br>
                            <input type="text" name="numCarta" required><br>
                            <label>Intestatario carta: </label><br>
                            <input type="text" name="intestatario" required><br>
                            <label>Data di scadenza: </label><br>
                            <input type="text" name="scadenza" required><br>
                        </div>
                        <div class="articoli" id="articoli">
                            Articoli:
                        </div>
                    </div>
                    <input type="submit" value="Conferma"/>
                    <br><br><br><br>
                </form>
            </div>
        </div>
        <div class="footer"></div>
    </body>
</html>
