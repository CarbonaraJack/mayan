<%-- 
    Document   : signIn
    Created on : 12-ott-2017, 9.25.39
    Author     : jack
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mayan - Registrazione nuovo utente</title>
    </head>
    <body>
        <div class="signInCard">
            <div> Registrazione nuovo utente </div>
            <form action="../JSPAssets/signinHandler.jsp" method="POST">
                <div>
                    <input placeholder="Nome" name="name" >
                </div>
                <div>
                    <input placeholder="Cognome" name="surname" >
                </div>
                <div>
                    <input type="email" placeholder="Email" name="mail" >
                </div>
                <div>
                    <input type="password" placeholder="Password" name="password1">
                </div>
                <div>
                    <input type="password" placeholder="Conferma password" name="password2">
                </div>
                <div>
                    <input type="submit" value="Log in">
                </div>
            </form>

        </div>
    </body>
</html>
