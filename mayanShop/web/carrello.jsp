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
                <div class="tabItems" id="tabItems">
                </div>
                 
                <%
                    if (session.getAttribute("userId") == null)
                    {
                %>
                <div>Effettuare il login per proseguire con l'acquisto</div>
                <form name="search" action="./riepilogoOrdine.jsp" method="POST">
                    <input type="submit" value="Acquista" disabled/>
                </form>
                <%
                    }
                %>
                <%--
                <%
                    } else if ((resCarrello.equals("null")) || (resCarrello.length() <= 0)) {
                %>
                <form name="search" action="./riepilogoOrdine.jsp" method="POST">
                    <input type="submit" value="Acquista" disabled/>
                </form>
                <%
                    } else {
                %>
                <form name="search" action="./riepilogoOrdine.jsp" method="POST">
                    <input type="submit" value="Acquista"/>
                </form>
                <%
                    }
                %>--%>
            </div>
        </div>
    </body>
</html>
