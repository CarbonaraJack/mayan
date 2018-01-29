<%--
    Document   : profilo
    Created on : 24-gen-2018, 14.20.32
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/index.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/index.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>         

        <title>Mayan - Gestione profilo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%
            int userId = 0;
            String userName = null;
            String userSurname = null;
            String userEmail = null;
            if (session.getAttribute("userId") != null) {
                userId = (int) session.getAttribute("userId");
                userName = (String) session.getAttribute("userName");
                userSurname = (String) session.getAttribute("userSurname");
                userEmail = (String) session.getAttribute("userEmail");
            }
        %>
        <script>
            var userId = <%= userId%>;
        </script>

        <script src="JavaScript/lib/jquery.getUrlParam.js"></script>
        <link href="Styles/profilo.css" rel="stylesheet" type="text/css"/>
        <script src="JavaScript/profilo.js"></script>
    </head>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="editorProfilo">
                <div>
                    <h2>Modifica il tuo profilo</h2>
                    <form action="updateProfilo" method="post">
                        <label>Nome:</label><br>
                        <input type="text" name="nome" required value="<%= userName%>" onclick="this.select();"/><br>
                        <label>Cognome:</label><br>
                        <input type="text" name="cognome" required value="<%= userSurname%>" onclick="this.select();"/><br>
                        <label> Indirizzo mail:</label><br>
                        <input type="text" name="email" required value="<%= userEmail%>" onclick="this.select();"/><br>
                        <label id="updateMessage"></label><br>
                        <input type="submit" value="Aggiorna informazioni"/>
                    </form>
                </div>
            </div>
            <div class="editorPassword">
                <div>
                    <h2>Modifica la tua password</h2>
                    <form action="updatePassword" onsubmit="return validaPassword()" method="post">
                        <label>Nuova password:</label><br>
                        <input type="password" name="password" required id="password" onclick="this.select();"/><br>
                        <label>Conferma nuova password:</label><br>
                        <input type="password" name="password" required id="conferma" onclick="this.select();"/><br>
                        <input type="hidden" name="place" value="reset">
                        <label id="passwordMessage"></label><br>
                        <input type="submit" value="Aggiorna password"/>
                    </form>
                </div>
            </div> 
        </div>
        <div class="footer"><div class="footertext"> Mayan&reg;</div></div>
    </body>
</html>
