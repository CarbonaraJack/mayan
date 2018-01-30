<%-- 
    Document   : notifiche
    Created on : 24-gen-2018, 13.13.38
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>
        <%
        String listaMessaggi = (String) session.getAttribute("listaMessaggi");
        %>
            <script>
        var lista = <%= listaMessaggi %>
        </script>   
        <script src="JavaScript/controlloNotifiche.js"></script> 
        
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    
    <body>
    <div class="container">
        <%@include file="JSPAssets/header.jsp" %>
    </div>
    <div class="main">
        <h1>Notifiche</h1>
        <div class="tabNotifiche" id="tabNotifiche">
        </div> 
        
    </div>                 
    </body>
</html>
