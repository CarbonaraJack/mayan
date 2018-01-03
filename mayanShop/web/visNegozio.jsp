<%-- 
    Document   : visNegozio
    Created on : 30-dic-2017, 17.24.03
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<link href="Styles/index.css" rel="stylesheet" type="text/css"/>--%>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/visObject.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String resNegozio = (String) session.getAttribute("negozio");
    %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var negozio = <%= resNegozio%>;
    </script>
    <script src="JavaScript/visNegozio.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <div class="containerNegozio" id="containerNegozio">

                </div>
            </div>
        </div>
    </body>
</html>
