<%-- 
    Document   : segnala
    Created on : 27-gen-2018, 13.48.15
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/carrello.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">

        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%@include file="JSPAssets/header.jsp" %>
        <h1>Cosa vuoi segnalare?</h1>
        <form action="inviaSegnalazione" method="POST">
        Il messaggio verrà visualizzato dal venditore e da un amministratore<br>
        <textarea id="testo" name="testo"></textarea>
        <input type="submit"  value="Invia"/>
        </form>
    </body>
</html>
