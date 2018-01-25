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
                if (userType.equals("venditore")) {
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
            <div id="containerSelettoreNegozi">
                <div>
                    <h3>Seleziona il negozio da modificare</h3>
                    <div id="selettoreNegozi">
                        <div class="selettoreRigaHead">
                            <div class="selettoreNome"> Nome:</div>
                            <div class="selettoreVia"> Via:</div>
                            <div class="selettoreCitta"> Città:</div>
                            <div class="selettoreTipo"> Tipo:</div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="editorNegozi">
                <div>
                    <h3>Modifica le informazioni del negozio</h3>
                    <div id="editorInfo">
                        <form action="aggiornaNegozio" onsubmit='return validateForm("nuovo");' id="editForm" method="post">
                            <label>Nome negozio: </label><br>
                            <input type="hidden" name="idNegozio" value="nuovo" id="editIdSelector"/>
                            <input type="text" id="editName" name="nome" required/><br>
                            <label>Link negozio: </label><br>                        
                            <input type="text" id="editLink" name="url"/><br>
                            <label>Tipo negozio: </label><br>          
                            <select id="editType" name="tipo" required>
                                <option value="online">Online</option>
                                <option value="fisico">Fisico</option>
                            </select><br>
                            <label>Descrizione negozio: </label><br>
                            <textarea id="editDesc" name="descrizione"></textarea><br>
                            <label>Orari negozio: </label><br>
                            <textarea id="editHour" name="orario"></textarea><br>
                            <label id="editMessage"/><br></label>
                            <input type="submit" value="Aggiorna negozio" id="editSubmit">
                        </form>
                    </div>
                </div>
            </div>
            <div id="editorLocation">
                <div>
                    <h3 id="titoloLocation">Inserisci l'indirizzo della tua attività</h3>
                    <div id="mappaGoogle"></div>
                </div>
            </div>
            <div id="editorFoto">
                <div>
                    <h3>Inserisci o cambia la foto negozio</h3>
                    <div id="managerFoto">
                        <div id="visualizzatoreFoto">
                            <div id="stampatoreFoto">
                                <img id="foto">
                            </div>
                            <div id="bottoneCancellaFoto">
                                <form method="post" action="./cancellaFoto">
                                    <input type="hidden" id="idCancellaFoto" 
                                           name="idFoto" value=""/>
                                    <input type="hidden" name="mode" value="negozio"/>
                                    <input type="submit" value="Cancella foto"/>
                                </form>
                            </div>
                            <div id="selettoreFoto">
                            </div>
                        </div>
                        <div id="visualizzatoreFotoVuoto">
                            <div>Nessuna foto da visualizzare</div>
                        </div>
                        <div id="uploaderFoto">
                            <form method="post" action="./uploadFile" enctype="multipart/form-data">
                                <label>Seleziona le immagini da caricare</label><br>
                                <label>Puoi selezionare un numero qualsiasi di immagini.</label><br>
                                <label>Dimensioni massime per immagine: 5MB</label><br>
                                <label>Dimensioni massime caricamento: 20MB</label><br>
                                <input type="hidden" name="source" value="negozio"/>
                                <input type="hidden" id="uploadId" name="idNegozio" value=""/>
                                <input type="file" name="file" id="fileChooser" accept="image/*" multiple><br>
                                <input type="submit" value="Upload" />
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
