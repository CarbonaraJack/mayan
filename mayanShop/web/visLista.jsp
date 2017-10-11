<%-- 
    Document   : visLista
    Created on : 10-ott-2017, 13.49.01
    Author     : Michela
--%>

<%@page import="bean.itemBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet, java.util.ArrayList" %>
<%@page import="bean.ConnectionProvider,bean.itemBean" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700&amp;subset=latin-ext" rel="stylesheet">
        <title>mayan</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container">
            <%@include file="JSPAssets/header.jsp" %>
            <div class="sidebar">c</div>
            <div class="main">
                <div class="containerItem">
                    <%
                        ArrayList<itemBean> lista = (ArrayList<itemBean>) request.getAttribute("listaItemBean");
                        String s = "";
                        if (!lista.isEmpty()) {
                            for (itemBean item : lista) {
                                s = "JSPAssets/printItem.jsp?i=000001.jpg&n=" + item.getNome() + "&s=" + item.getVoto() + "&p=" + item.getPrezzo();
                                s = s + "&a=" + item.getProduttore() + "&id=" + item.getIdItem();
                    %>
                    <jsp:include page="<%=s%>"/>
                    <%}
                        } else {
                            out.println("<div>è vuoto</div>");
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
