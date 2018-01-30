<%-- 
    Document   : notifiche
    Created on : 24-gen-2018, 13.13.38
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/notifiche.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>         

        <title>Mayan - Notifiche</title>
        <%
            String userType = null;
            String listaMessaggi = (String) session.getAttribute("listaMessaggi");
            if (session.getAttribute("userType") != null) {
                userType = (String) session.getAttribute("userType");
            }
        %>
        <script>
            var lista = <%= listaMessaggi%>;
            var userType = "<%= userType%>";
            if (userType == null) {
                window.location = "./alert.jsp?mode=restricted";
            }
            if (lista == null) {
                window.location = "./showNotifiche";
            }
        </script>   
        <script src="JavaScript/controlloNotifiche.js"></script> 

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>


    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <h3>Lista notifiche</h3>
                <div class="tabNotifiche" id="tabNotifiche">
                    <div class="rigaTit tipo">Tipo</div>
                    <div class="rigaTit mittente">Mittente</div>
                    <div class="rigaTit descBreve">Messaggio</div>
                </div> 
            </div>       
        </div>
        <div id="bgFader">
            <div id="containerPopup">
                <div id="contenutoPopup">
                    <span id="chiudi">&times;</span>
                    <div id="formRisposta">
                        <form action="./inviaSegnalazione" onsubmit="return valida();" method="post">
                            <h3> Rispondi alla segnalazione</h3>
                            <input type="hidden" id="idMessaggio" name="idMessaggio"/>
                            <input type="hidden" id="idTransazione" name="idTransazione"/>
                            <input type="hidden" id="idDestinatario" name="idDestinatario"/>
                            <input type="hidden" id="idMittente" name="idMittente"/>
                            <div id="adminCheck">
                                <input type="checkbox" id="checkbox" name="close"/><label>Chiudi segnalazione</label>
                            </div>
                            <textarea id="testoForm" name="testo" required></textarea>
                            <input type="submit" id="submit" value="Invia"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
