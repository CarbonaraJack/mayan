<%-- 
    Document   : visNegozio
    Created on : 30-dic-2017, 17.24.03
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<link href="Styles/index.css" rel="stylesheet" type="text/css"/>--%>
        <link href="Styles/header.css" media='only screen and (min-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 1700px)' rel="stylesheet" type="text/css" />
        <link href="Styles/visNegozio.css" media='only screen and (min-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/visNegozio.css" media='only screen and (max-width: 1700px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>  
        
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
        <style>
            #map {
                height: 400px;
                width: 100%;
            }
        </style> 
    </head>
    <%
        String resNegozio = (String) session.getAttribute("negozio");
    %>
    <script>
        var negozio = <%= resNegozio%>;
    </script>
    <script src="JavaScript/visNegozio.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <div class="containerNegozio" id="containerNegozio">

                </div>
            </div>
        </div>
            <div id="map"></div>
            <script>
              function initMap() {
                
                var latit = negozio.location.latitudine;
                var long = negozio.location.longitudine;
                console.log(latit);
                console.log(long);
                //var uluru = {lat: latit, lng: long};
                var map = new google.maps.Map(document.getElementById('map'), {
                  zoom: 16,
                  center: {lat: negozio.location.latitudine, lng: negozio.location.longitudine}
                });
                var marker = new google.maps.Marker({
                  position: {lat: negozio.location.latitudine, lng: negozio.location.longitudine},
                  map: map
                });
              }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBxKncpuPiaS35fUHbuPP0v0Szp_N71_k4&callback=initMap">
            </script>
    </body>
</html>
