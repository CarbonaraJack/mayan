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
        String idReq = request.getParameter("item");
    %>
    <script>
        var oggetto = <%= resItem%>;
        var idRequest = <%= idReq%>;
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
                var map, infoWindow;
                function initMap() {
                    map = new google.maps.Map(document.getElementById('map'), {
                        center: {lat: -34.397, lng: 150.644},
                        zoom: 6
                    });
                    infoWindow = new google.maps.InfoWindow;

                    // Try HTML5 geolocation.
                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(function (position) {
                            var pos = {
                                lat: position.coords.latitude,
                                lng: position.coords.longitude
                            };

                            /*infoWindow.setPosition(pos);*/
                            infoWindow.open(map);
                            map.setCenter(pos);
                        }, function () {
                            handleLocationError(true, infoWindow, map.getCenter());
                        });
                        for (var i = 0; i < oggetto.negozi.length; i++ ){
                            console.log("sdf");
                            var marker = new google.maps.Marker({
                                position: new google.maps.LatLng(oggetto.negozi[i].location.latitudine, oggetto.negozi[i].location.longitudine),
                                map: map
                            });
                            (function (marker, i) {
                                // add click event
                                google.maps.event.addListener(marker, 'click', function () {
                                    infowindow = new google.maps.InfoWindow({
                                        content: 'Hello, World!!'
                                    });
                                    infowindow.open(map, marker);
                                });
                            })(marker, i);
                        }
                    } else {
                        // Browser doesn't support Geolocation
                        handleLocationError(false, infoWindow, map.getCenter());
                    }
                }

                
                function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                    infoWindow.setPosition(pos);
                    infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
                    infoWindow.open(map);
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



        <div class="footer"><div class="footertext"> Mayan&reg;</div></div>
    </body>
</html>

