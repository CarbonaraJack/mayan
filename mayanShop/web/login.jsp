<%-- 
    Document   : login
    Created on : 22-gen-2018, 20.04.15
    Author     : jack
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Styles/login.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <title>Mayan - Login</title>
    </head>
    <script src="JavaScript/login.js"></script>
    <body>
        <div class="container">
            <div class="toast">
                <div id="login" onclick="showLogin()">Login</div>
                <div id="signin" onclick="showSignin()">Registrati</div>
                <div id="contentLogin">
                    <form action="checkLogin" method="post">
                        <label>Indirizzo email: </label><br>
                        <input type="text" name="email" required><br>
                        <label>Password: </label><br>
                        <input type="password" name="password" required><br>
                        <input type="submit">
                    </form>
               </div>
                <div id="contentSignin">
                    <form action="checkSignin" method="post"><br>
                        <label>Inserisci il tuo nome: </label><br>
                        <input type="text" name="nome" required><br>
                        <label>Inserisci il tuo cognome: </label><br>
                        <input type="text" name="nome" required><br>
                        <label>Inserisci il tuo indirizzo email: </label><br>
                        <input type="text" name="email" required><br>
                        <label>Scegli la tua password: </label><br>
                        <input type="password" name="password" required><br>
                        <label>Conferma la tua password: </label><br>
                        <input type="password" name="conferma" required><br>
                        <input type="checkbox" name="trattamento" required>
                        <label>Consenti il trattamento dei dati </label><br>
                        <input type="submit">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
