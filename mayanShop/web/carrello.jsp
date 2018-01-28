<%--
    Document   : carrello.jsp
    Created on : 6-nov-2017, 10.26.48
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/carrello.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/carrello.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">

        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String resCarrello = (String) session.getAttribute("carrello");
    %>
    <script>
        var carrello = <%= resCarrello%>;
    </script>
    <script src="JavaScript/visCarrello.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <h1>Carrello</h1>
                <div class="containerCarrello" id="containerCarrello">
                    <form name="search" action="./riepilogoOrdine" method="POST">
                        <div class="tabItems" id="tabItems">
                        </div>
                        <%                        if (session.getAttribute("userId") == null) {
                        %>
                        <div>Effettuare il login per proseguire con l'acquisto</div>
                        <br>
                        <input type="submit" value="Acquista" disabled/>
                        <%
                        } else if ((resCarrello == null) || (resCarrello.isEmpty())) {
                        %>
                        <input type="submit" value="Acquista" disabled/>
                        <%
                        } else {
                        %>
                        <input type="submit" value="Acquista"/>
                        <%
                            }
                        %>
                        <a href="./controlloCarrello?del=all">
                            <button type="button" class="svuotaCarr">Svuota carrello</button>
                        </a>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer"></div>
    </body>
</html>
