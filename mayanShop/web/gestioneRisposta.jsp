<%-- 
    Document   : gestioneMessaggio
    Created on : 28-gen-2018, 22.03.08
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
        <script src="JavaScript/gestioneRisposta.js"></script> 
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String messaggio = (String) session.getAttribute("messaggio");
    %>
    <script>
        var messaggio = <%= messaggio %>
    </script>
    <body>
    <div class="container">
        <%@include file="JSPAssets/header.jsp" %>
    </div>
    <div class="main">
        <h1>Gestione Messaggio</h1>
        <div class="tabMessaggio" id="tabMessaggio">
        </div> 
        
    </div>                 
    </body>
</html>
