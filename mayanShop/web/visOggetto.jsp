<%-- 
    Document   : DisplayObject
    Created on : Oct 3, 2017, 2:35:25 PM
    Author     : Francy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <%--<link href="Styles/index.css" rel="stylesheet" type="text/css"/>--%>
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/visOggetto.css" media='only screen and (min-width: 1060px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/visOggetto.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/visOggetto_1.css" media='only screen and (max-width: 1060px) and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>  
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        String resItem = (String) session.getAttribute("item");
    %>
    <script>
        var oggetto = <%= resItem%>;
    </script>
    <script src="JavaScript/visOggetto.js"></script>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="main">
                <div class="containerItem" id="containerItem">
                    <div id="editorFoto">
                        <div>
                            <div id="managerFoto">
                                <div id="visualizzatoreFoto">
                                    <div id="stampatoreFoto">
                                        <img id="foto">
                                    </div>
                                    <div id="selettoreFoto">
                                    </div>
                                </div>
                                <div id="visualizzatoreFotoVuoto">
                                    <div>Nessuna foto da visualizzare</div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="mappa"  id="mySidmap">
            <button onclick="closeMap()">CHIUDI</button>
            <div id="map"></div>
            <script>
                console.log("<df");
                function initMap() {

                    /*var latit = negozio.location.latitudine;
                     var long = negozio.location.longitudine;
                     console.log(latit);
                     console.log(long);*/
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 16,
                        center: {lat: parseFloat(negozio.location.latitudine), lng: parseFloat(negozio.location.longitudine)}
                    });
                    var marker = new google.maps.Marker({
                        position: {lat: parseFloat(negozio.location.latitudine), lng: parseFloat(negozio.location.longitudine)},
                        map: map
                    });
                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBxKncpuPiaS35fUHbuPP0v0Szp_N71_k4&callback=initMap">
            </script>
        </div>
        <div class="showmappa"><button onclick="openMap()">MAPPA</button></div>
        <div class="footer"></div>
        <script>
            function openMap() {
                document.getElementById("mySidmap").style.height = "400px";
            }

            function closeMap() {
                document.getElementById("mySidmap").style.height = "0";
            }
        </script>
        
        
        
        <div class="footer"></div>
    </body>
</html>

