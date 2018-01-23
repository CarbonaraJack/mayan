<%--
    Document   : header
    Created on : 18-set-2017, 16.07.14
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="containerHeader">
    <div class="titolo">mayan</div>
    <div class="searchbar">
        <form name="search" action="search" method="POST">
            <input id="item" name="item"/>
            <input type="submit" value="Cerca"/>
        </form>
    </div>
    <div class="barra">
        <div><a href="./carrello.jsp" class="link">Carrello</a></div>
        <%
            if (session.getAttribute("userId") == null) {
                out.print("<div><a href=\"./login.jsp\" class=\"link\">Login</a></div>");
            }else{
                out.print("<div><a href=\"./logout\" class=\"link\">Logout</a></div>");
            }
        %>
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
