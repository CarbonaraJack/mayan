<%-- 
    Document   : recensioniList
    Created on : 30-gen-2018, 16.15.40
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <%--<link href="Styles/index.css" rel="stylesheet" type="text/css"/>--%>
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/recensioniList.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <%--NON C'è ANCORA<link href="Stylesmobile/recensioniList.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>--%>
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
        String listaRecItem = (String) session.getAttribute("listaRecItem");
        String listaRecNeg = (String) session.getAttribute("listaRecNeg");
    %>
    <script>
        var recensioniItem = <%= listaRecItem%>;
        var recensioniNegozi = <%= listaRecNeg%>;
    </script>
    <script src="JavaScript/recensioniList.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <div class="containerList" id="containerList">
                    Recensioni visualizzate:<br>
                    <button class="active" id="btnItem" onclick="setItem()">Item</button>
                    <button id="btnNegozi" onclick="setNegozi()">Negozi</button>
                    <div class="tabList" id="tabList">
                    </div>
                </div>
            </div>
        </div>
        <div class="footer"></div>
    </body>
</html>
