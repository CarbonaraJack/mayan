<%--
    Document   : header
    Created on : 18-set-2017, 16.07.14
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="containerHeader">
    <div class="titolo"><a href="./index" class="link">mayan</a></div>
    <div class="searchbar">
        <form name="search" action="search" method="POST">
            <input id="item" name="item"/>
            <input type="submit" value="Cerca"/>
        </form>
    </div>
    <div class="barra">
        <div><a href="./carrello.jsp" class="link">Carrello</a></div>
        <div><a>Login</a></div>
    </div>
</div>

<script>
         $(function() {
            $( "#item" ).autocomplete("JSPAssets/getdata.jsp");
         });
</script>
