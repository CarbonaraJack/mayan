<%-- 
    Document   : header
    Created on : 18-set-2017, 16.07.14
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="containerHeader">
    <div class="titolo">mayan</div>
    <div class="searchbar">
        <form name="search" action="search" method="POST">
            <input type="text" id="testo" name="testo" class="searchText" placeholder="Cerca..."/>
            <%--<script>
		$("#testo").autocomplete("getdata.jsp");
            </script>--%>
            <input type="submit" value="Cerca" class="searchSubmit"/>
        </form>
    </div>
    <div class="barra">
        <div><a href="./carrello.jsp" class="link">Carrello</a></div>
        <div><a>Login</a></div>
    </div>
</div>

