<%-- 
    Document   : DisplayObject
    Created on : Oct 3, 2017, 2:35:25 PM
    Author     : Francy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<link href="Styles/index.css" rel="stylesheet" type="text/css"/>--%>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/visOggetto.css" rel="stylesheet" type="text/css"/>
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
        String resItem = (String) session.getAttribute("item");
    %>
    <script>
        var oggetto = <%= resItem%>;
    </script>
    <script src="JavaScript/visOggetto.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <div class="containerItem" id="containerItem">

                </div>
            </div>
        </div>
    </body>
</html>

