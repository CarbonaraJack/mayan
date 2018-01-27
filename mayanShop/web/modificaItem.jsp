<%--
    Document   : modifica item
    Created on : 26-gen-2018, 23.11.47
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/footer.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/index.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/index.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/header.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/header.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css" />
        <link href="Styles/modificaItem.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/modificaItem.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
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
            String parametri = null;
            String mode = null;
            if (session.getAttribute("userId") != null) {
                userId = (int) session.getAttribute("userId");
                userType = (String) session.getAttribute("userType");
                if (userType.equals("venditore") || userType.equals("amministratore")) {
                    parametri = (String) session.getAttribute("par_EditItem");
                    mode = (String) session.getAttribute("mode_EditItem");
                }
            }
        %>
        <script>
            var userId = <%= userId%>;
            var userType = "<%= userType%>";
            var parametri = <%= parametri%>;
            var mode = "<%= mode%>";
        </script>

        <script src="JavaScript/modificaItem.js"></script>
    </head>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div id="editorItem">
                <div>
                    <h3>Inserisci un nuovo oggetto</h3>
                    <div id="editorInfo">
                        <form action="inserisciItem" onsubmit='return validaForm();' id="editForm" method="post">
                            <label>Nome: </label><br>
                            <input type="text" id="editName" name="nome" required/><br>
                            <label>Produttore: </label><br>          
                            <select id="editProd" name="produttore" required
                                    onchange="nuovoProd();">                                
                            </select><br>
                            <div id="nuovoProdDiv">
                                <label>Nome nuovo produttore: </label><br>
                                <input type="text" id="nuovoProdNome" name="nuovoProduttore"/><br>
                            </div>
                                <label>Categoria: </label><br>      
                            <select id="editCat" name="categoria" required
                                    onchange="nuovaCat();">
                            </select><br>
                            <div id="nuovaCatDiv">
                                <label>Nome nuova categoria: </label><br>
                                <input type="text" id="nuovaCatNome" name="nuovaCategoria"/><br>
                            </div>
                            <label>Descrizione : </label><br>
                            <textarea id="editDesc" name="descrizione"></textarea><br>
                            <label id="editMessage"> Nulla da modificare!</label><br>
                            <input type="submit" value="Inserisci oggetto"/>
                        </form>
                    </div>
                </div>
            </div>
            <div id="editorStock">
                <div>
                    
                </div>
            </div>
            <div id="editorFoto">
                <div>
                    <h3>Inserisci o cambia le foto all'item</h3>
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
