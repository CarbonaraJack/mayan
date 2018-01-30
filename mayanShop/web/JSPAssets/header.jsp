<%--
    Document   : header
    Created on : 18-set-2017, 16.07.14
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="containerHeader">
    <div class="titolo"><a href="./index.jsp" class="link"><img src="img/Logo.jpg" height="50px"/></a></div>
    <div class="searchbar">
        <form name="search" action="./ricerca" method="POST">
            <select id="select" name="select">
                <option value="oggetti">Oggetti</option>
                <option value="negozi">Negozi</option>
                <option value="produttori">Produttori</option>
                <option value="zone">Zona</option>
            </select>
            <input id="item" name="item"/>
            <input type="submit" value="Cerca" class="headerBarButton" id="searchButton"/>
        </form>
    </div>
    <div class="sidebarmobile"><button type="button" onclick= "openButton()">MENU</button></div>
    <div class="barra" id="bottoni">
        <div class="chiusurabottoni"><button type="button" onclick= "closeButton()">CHIUDI</button></div>
        <%
            int ID_UTENTE = -1;
            if (session.getAttribute("userId") != null) {
                ID_UTENTE = (Integer) session.getAttribute("userId");
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./profilo.jsp\';\" class=\"headerBarButton\">"
                        + session.getAttribute("userName")
                        + " "
                        + session.getAttribute("userSurname")
                        + "</button></div>");
                out.print("<div><button type=\"button\" onclick=\"window.location=\'./showNotifiche\';\" class=\"headerBarButton\" id=\"count\">Notifiche</button></div>");
                if (!(session.getAttribute("userType").equals("amministratore"))){
                    out.print("<div><button type=\"button\" onclick=\"window.location=\'./controlloAcquisti\';\" class=\"headerBarButton\">I Miei Ordini</button></div>");
                }
            }
        %>
        <div><button type="button" onclick="window.location = './carrello.jsp';" class="headerBarButton" id="barraCarrello">Carrello</button></div>
        <%
            if (session.getAttribute("userType") != null) {
                if (session.getAttribute("userType").equals("venditore")) {
                    out.print("<div><button type=\"button\" onclick=\"window.location=\'./editNegozio\';\" class=\"headerBarButton\">Negozi</button></div>");
                    out.print("<div><button type=\"button\" onclick=\"window.location=\'./editItemList\';\" class=\"headerBarButton\">Oggetti</button></div>");
                    out.print("<div><button type=\"button\" onclick=\"window.location=\'./recensioniList\';\" class=\"headerBarButton\">Recensioni</button></div>");

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

<script>
            $(function () {
                $("#item").autocomplete("JSPAssets/getdata.jsp");
            });
</script>
<script>
    var id = <%= ID_UTENTE%>;
</script>
<script src="JavaScript/contaNotifiche.js"></script>
<script>
    function openButton() {
        document.getElementById("bottoni").style.width = "200px";
    }

    function closeButton() {
        document.getElementById("bottoni").style.width = "0";
    }

</script>
