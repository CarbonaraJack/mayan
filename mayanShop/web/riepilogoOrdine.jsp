<%-- 
    Document   : riepilogoOrdine
    Created on : 21-gen-2018, 16.48.56
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/riepilogoOrdine.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JS/jquery.autocomplete.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String resItems = (String) session.getAttribute("carrello");
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
                            Indirizzo di consegna: 
                        </div>
                        <div class="pagamento" id="pagamento">
                            Modalità di pagamento: 
                        </div>
                        <div class="articoli" id="articoli">
                            Articoli:
                        </div>
                    </div>
                    <input type="submit" value="Conferma"/>
                </form>
            </div>
        </div>
    </body>
</html>
