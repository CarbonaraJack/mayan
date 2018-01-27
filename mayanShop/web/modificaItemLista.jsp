<%--
    Document   : modifica items (visualizzatore lista)
    Created on : 26-gen-2018, 17.15.54
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
        <link href="Styles/modificaItemLista.css" media='only screen and (min-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="Stylesmobile/modificaItemLista.css" media='only screen and (max-width: 480px)' rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="Styles/jquery.autocomplete.css" rel="stylesheet" type="text/css"/> 

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
        <script src="JavaScript/lib/jquery.autocomplete.js"></script>         

        <title>Mayan - Gestione oggetti</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%
            int userId = 0;
            String userType = null;
            String listaItems = null;
            String mode = null;
            int pagina = 1;
            int numeroPagine = 0;
            String ricerca = null;
            if (session.getAttribute("userId") != null) {
                userId = (int) session.getAttribute("userId");
                userType = (String) session.getAttribute("userType");
                if (userType.equals("venditore")||userType.equals("amministratore")) {
                    listaItems = (String) session.getAttribute("listaItemsEditList");
                    pagina = (int) session.getAttribute("paginaEditList");
                    numeroPagine = (int) session.getAttribute("numeroPagineEditList");
                    mode = (String) session.getAttribute("modeEditList");
                    ricerca = (String) session.getAttribute("ricercaEditList");
                }
            }
        %>
        <script>
            var userId = <%= userId%>;
            var userType = "<%= userType%>";
            var listaItems = <%= listaItems%>;
            var mode = "<%= mode%>";
            console.log(mode);
        </script>

        <script src="JavaScript/lib/jquery.getUrlParam.js"></script>
        <!--<link href="Styles/modificaItemLista.css" rel="stylesheet" type="text/css"/>-->
        <script src="JavaScript/modificaItemLista.js"></script>

    </head>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div id="containerSelettoreItem">
                <div>
                    <h3>Seleziona l'item da modificare</h3>
                    <div id="divRicerca">
                        <form action="editItemList" method="post">
                            <label>
                                Se vuoi inserire un nuovo item esegui prima una 
                                ricerca con il nome per assicurarti di non creare
                                doppioni.
                            </label><br>
                            <input type="text" name="nomeItem" id="searchInput"
                                   onclick="select();"
                                   <%
                                       if(mode.equals("post")){
                                           out.print("value=\"");
                                           out.print(ricerca);
                                           out.print("\"");
                                       }
                                   %>
                                   />
                            <input type="submit" value="Cerca"/>
                        </form>
                    </div>
                    <div id="selettoreItem">
                        <div class="selettoreRigaHead">
                            <div class="selettoreNome"> Nome:</div>
                            <div class="selettoreProduttore"> Produttore:</div>
                            <div class="selettoreCategoria"> Categoria:</div>
                            <div class="cellaModifica"> Modifica:</div>
                        </div>
                    </div>
                    <div id="selettorePagina">
                        <%
                            if(pagina!=1){
                                out.print("<button onclick=\'window.location=\"./editItemList?page=");
                                out.print(pagina-1);
                                out.print("\";\' >");
                                out.print("<");
                                out.print("</button>");
                            }
                        %>
                        <label>Pagina <%= pagina%> di <%= numeroPagine%></label>
                        <%
                            if(pagina!=numeroPagine){
                                out.print("<button onclick=\'window.location=\"./editItemList?page=");
                                out.print(pagina+1);
                                out.print("\";\' >");
                                out.print(">");
                                out.print("</button>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
