<%-- 
    Document   : header
    Created on : 18-set-2017, 16.07.14
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="containerH">
    <div class="titolo">mayan</div>
    <div class="searchbar">
        <form name="search" action="search" method="POST">
            <input type="text" id="testo" name="testo"/>
            <script>
		$("#testo").autocomplete("getdata.jsp");
            </script>
            <input type="submit" value="Cerca"/>
        </form>
    </div>
    <div class="barra">
        <div><a>Carrello</a></div>
        <div><a>Login</a></div>
    </div>
</div>
