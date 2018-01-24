<%--
    Document   : modifica negozi
    Created on : 24-gen-2018, 14.20.32
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>         

        <title>Mayan - Gestione negozi</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%
            int userId = 0;
            String userType = null;
            String listaNegozi = null;
            if (session.getAttribute("userId") != null) {
                userId = (int) session.getAttribute("userId");
                userType = (String) session.getAttribute("userType");
                if(userType.equals("venditore")){
                    listaNegozi = (String) session.getAttribute("listaNegozi");
                }
            }
        %>
        <script>
            var userId = <%= userId%>;
            var userType = "<%= userType%>"; 
            var listaNegozi = <%= listaNegozi%>
        </script>
        
        <script src="JavaScript/lib/jquery.getUrlParam.js"></script>
        <link href="Styles/modificaNegozi.css" rel="stylesheet" type="text/css"/>
        <script src="JavaScript/modificaNegozi.js"></script>
    </head>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="editorNegozi">
               a
            </div> 
        </div>
    </div>
</body>
</html>
