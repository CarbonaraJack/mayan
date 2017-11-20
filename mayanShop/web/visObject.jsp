<%-- 
    Document   : visObject
    Created on : 19-nov-2017, 17.26.39
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        Gson gson = new Gson();
        String resItems = (String) session.getAttribute("listaItems");
    %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var oggetti = <%= resItems%>;
        var url_string = document.URL;
        var url = new URL(url_string);
        var i = url.searchParams.get("index");

        $(document).ready(function () {
            var s = "<div class='itemBox' id='item" + oggetti[i].idItem + "'>";
            s = s + "<div class='itemImageContainer'>" + "<a href=''>" + "<img class='itemImage' src='img/000001.jpg'/></a></div>";
            s = s + "<div class='itemName'><a href=''>" + oggetti[i].nome + "</a></div>";
            s = s + "<div class='itemAuthor'>" + oggetti[i].produttore + "</div>";
            s = s + "<div class='itemPrice'>" + oggetti[i].prezzo + "€</div>";

            s = s + "<div class='itemStars'>";
            var stars = oggetti[i].voto;
            for (var j = 1; j <= 5; j++) {
                if (j <= stars) {
                    s = s + "<span class='fa fa-star checked'></span>";
                } else {
                    s = s + "<span class='fa fa-star'></span>";
                }
            }
            s = s + "</div>";

            s = s + "</div>";
            $("#containerItem").append(s);

        });
    </script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="sidebar">gdx</div>
            <div class="main">
                <div class="containerItem" id="containerItem">

                </div>
            </div>
        </div>
    </body>
</html>
