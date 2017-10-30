<%-- 
    Document   : visLista
    Created on : 10-ott-2017, 13.49.01
    Author     : Michela
--%>

<%@page import="bean.itemBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet, java.util.ArrayList" %>
<%@page import="bean.ConnectionProvider,bean.itemBean" %>
<%@page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
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

        $(document).ready(function () {
            for (var i = 0; i < oggetti.length; i++) {
                var s = "<div class='itemBox'>";
                s = s + "<div class='itemImageContainer'>" + "<a href=''>" + "<img class='itemImage' src='img/000001.jpg'/></a></div>";
                s = s + "<div class='itemName'><a href=''>" + oggetti[i].nome + "</a></div>";
                s = s + "<div class='itemAuthor'>" + oggetti[i].produttore + "</div>";
                s = s + "<div class='itemStars'>" + oggetti[i].voto + "</div>";
                s = s + "<div class='itemPrice'>" + oggetti[i].prezzo + "</div>";
                s = s + "</div>";
                $("#containerItem").append(s);
            }
        });
    </script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="sidebar"></div>
            <div class="main">
                <div class="containerItem" id="containerItem">

                </div>
            </div>
        </div>
    </body>
</html>
