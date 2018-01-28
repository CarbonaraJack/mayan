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
        <%
            int userId=-1;         
            if (session.getAttribute("userId") == null) {                
                out.print("<div><a href=\"./login.jsp?mode=signin\" class=\"link\">Registrati</a></div>");
                out.print("<div><a href=\"./login.jsp?mode=login\" class=\"link\">Login</a></div>");
            }else{
                userId = (Integer) session.getAttribute("userId");
                out.print("<div><a href=\"./showNotifiche\" class=\"link\" id=\"count\">Notifiche</a></div>");
                out.print("<div><a href=\"./logout\" class=\"link\">Logout</a></div>");
            }
        %>
    </div>
</div>

<script>
    $(function () {
        $("#item").autocomplete("JSPAssets/getdata.jsp");
    });
</script>
<script>
    var id = <%=userId%>;
</script>
<script src="JavaScript/contaNotifiche.js"> </script>
