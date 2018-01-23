<%-- 
    Document   : testAsset
    Created on : 18-set-2017, 16.21.04
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="../Styles/index.css" rel="stylesheet" type="text/css"/>
        
        <link href="../Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="../JS/jquery.autocomplete.js"></script>
        
        <title>JSP Asset Test</title>
    </head>
    <body>
        <jsp:include page="/JSPAssets/header.jsp"/>
        <div>
            <%--<form name="search" action="../controlloItems?ric=true&objS=false" method="GET">
                <input type="submit" value="Vai"/>
            </form>--%>
            <a href="../controlloItems?ric=true&objS=false&idOgg=-1">
                <button>Vai</button>
            </a>
        </div>
    </body>
</html>
