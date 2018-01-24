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
        <div><a href="./carrello.jsp" class="link"  id="barraCarrello">Carrello</a></div>
        <%
            if (session.getAttribute("userId") == null) {
                out.print("<div><a href=\"./login.jsp?mode=signin\" class=\"link\">Registrati</a></div>");
                out.print("<div><a href=\"./login.jsp?mode=login\" class=\"link\">Login</a></div>");
            }else{
                out.print("<div><a href=\"./logout\" class=\"link\">Logout</a></div>");
            }
            
            String carrelloCont = (String) session.getAttribute("contCarrello");
        %>
        <script>
            var carrelloCont = <%= carrelloCont%>;
        </script>
        <script src="JavaScript/header.js"></script>
    </div>
</div>

<!--
<script>
         $(function() {
            var availableTutorials  =  [
               "ActionScript",
               "Bootstrap",
               "C",
               "C++",
            ];
            $( "#item" ).autocomplete({
               source: availableTutorials
            });
         });
</script>
-->


<script>
    $(function () {
        $("#item").autocomplete("JSPAssets/getdata.jsp");
    });
</script>
