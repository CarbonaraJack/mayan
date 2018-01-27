<%-- 
    Document   : alert
    Created on : 23-gen-2018, 12.41.46
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Styles/alert.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/alert.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <title>Mayan - alert</title>
    </head>
    <script src="JavaScript/lib/jquery.getUrlParam.js"></script>
    <script src="JavaScript/alert.js"></script>
    <body>
        <div class="container">
            <div class="toast">
                <div id="content">
                    <label id="messaggio">
                        Non dovresti essere qui...
                    </label><br>
                    <a id="redirect" href="./index.jsp">
                        Se il reindirizzamento automatico non funziona clicca qui.
                    </a>
                    <input type="hidden" id="userName" name="userName" value="${sessionScope.userName}"/>
                </div>
            </div>
        </div>
                <div class="footer"></div>
    </body>
</html>
