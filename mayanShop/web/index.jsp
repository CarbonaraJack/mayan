<%--
    Document   : index
    Created on : 18-set-2017, 16.10.47
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" media='only screen and (min-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/index.css" media='only screen and (max-width: 1700px)' rel="stylesheet" type="text/css" />
        <link href="Styles/header.css" media='only screen and (min-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 1700px)' rel="stylesheet" type="text/css" />
        <link href="Styles/visLista.css" media='only screen and (min-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/visLista.css" media='only screen and (max-width: 1700px)' rel="stylesheet" type="text/css" />
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
        String listaVis = (String) session.getAttribute("listaVis");
        String listaAcq = (String) session.getAttribute("listaAcq");
    %>
    <script>
        var oggettiVis = <%= listaVis%>;
        var oggettiAcq = <%= listaAcq%>;
    </script>
    <script src="JavaScript/index.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="sidebar">c</div>
            <div class="main">
                <h2>Prodotti più visualizzati:</h2>
                <div class="containerItem" id="containerItemVis">
                    
                </div>
                <h2>Prodotti più acquistati:</h2>
                <div class="containerItem" id="containerItemAcq">
                    
                </div>
            </div>
        </div>
    </body>
</html>
