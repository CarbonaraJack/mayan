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
            <input type="submit" value="Cerca" class="headerBarButton" id="searchButton"/>
        </form>
    </div>
    <div class="barra">
        <%
            if (session.getAttribute("userId") != null) {
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./profilo.jsp\';\" class=\"headerBarButton\">"
                        + session.getAttribute("userName")
                        + " "
                        + session.getAttribute("userSurname")
                        + "</button></div>");
            }
        %>
        <div><button type="button" onclick="window.location = './carrello.jsp';" class="headerBarButton" id="barraCarrello">Carrello</button></div>
        <%
            if (session.getAttribute("userType") != null) {
                if (session.getAttribute("userType").equals("venditore")) {
                    out.print("<div><button type=\"button\" onclick=\"window.location=\'./modificaNegozi.jsp\';\" class=\"headerBarButton\">Negozi</button></div>");

                }
            }
            if (session.getAttribute("userId") == null) {
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./login.jsp?mode=signin\';\" class=\"headerBarButton\">Registrati</button></div>");
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./login.jsp?mode=login\';\" class=\"headerBarButton\">Login</button></div>");
            } else {
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./logout\';\" class=\"headerBarButton\">Logout</button></div>");
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
