<%-- 
    Document   : login
    Created on : 22-gen-2018, 20.04.15
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Styles/login.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>  
        <link href="Stylesmobile/login.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <title>Mayan - Login</title>
        <script src="JavaScript/lib/jquery.getUrlParam.js"></script>
        <script src="JavaScript/login.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="toast">
                <div class="contenitorebottoni"></div>
                <div id="login"><button id="loginb" type="button" onclick="showLogin()">Login</button></div>
                <div id="signin"><button id="signinb" type="button" onclick="showSignin()">Registrati</button></div>
                <div id="contentLogin">
                    <form action="checkLogin" method="post">
                        <div class="contlogin">
                        <h1>Login</h1>
                        <label>Indirizzo email: </label><br>
                        <input type="email" name="email" required><br>
                        <label>Password: </label><br>
                        <input type="password" name="password" required><br>
                        <label id="messaggioErroreLogIn">Nome utente o password errata<br></label>
                        <input type="submit" value="Log in">
                        <button type="button" onclick="window.location = './index.jsp';">Torna indietro</button><br>
                        <a href="#" onclick="showForgot()">Ho dimenticato la mia password</a>
                        </div>
                    </form>
                </div>
                <div id="contentForgot">
                    <form action="updatePassword" onsubmit="return validaPasswordForgot()" method="post">
                        <div class="contlogin">
                        <h1>Password dimenticata</h1>
                        <label>Indirizzo email: </label><br>
                        <input type="email" name="email" required><br>
                        <label>Nuova password: </label><br>
                        <input type="password" name="password" id="passwordForgot" required><br>
                        <label>Conferma nuova password: </label><br>
                        <input type="password" name="password" id="confermaForgot" required><br>
                        <label id="messaggioErroreForgot">Le password non combaciano<br></label>
                        <input type="hidden" name="place" value="forgot">
                        <input type="submit" value="Reimposta password">
                        <button type="button" onclick="window.location = './index.jsp';">Torna indietro</button>
                        </div>
                    </form>
                </div>
                <div id="contentSignin">
                    <form action="checkSignin" onsubmit="return validaPassword()" method="post"><br>
                        <div class="contlogin">
                        <h1>Signin</h1>
                        <label>Inserisci il tuo nome: </label><br>
                        <input type="text" name="nome" required><br>
                        <label>Inserisci il tuo cognome: </label><br>
                        <input type="text" name="cognome" required><br>
                        <label>Inserisci il tuo indirizzo email: </label><br>
                        <input type="email" name="email" required><br>
                        <label>Scegli la tua password: </label><br>
                        <input type="password" name="password" id="password" required><br>
                        <label>Conferma la tua password: </label><br>
                        <input type="password" name="conferma" id="conferma" required><br>
                        <label id="messaggioErroreSignIn">Le password non combaciano<br></label>
                        <input type="checkbox" name="trattamento" required>
                        <label>Consenti il trattamento dei dati </label><br>
                        <input type="submit" value="Registrati">
                        <button type="button" onclick="window.location = './index.jsp';">Torna indietro</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
</html>
