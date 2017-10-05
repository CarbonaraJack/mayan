<%-- 
    Document   : index
    Created on : 18-set-2017, 16.10.47
    Author     : Michela
--%>

<%@page import="bean.itemBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet" %>
<%@page import="bean.ConnectionProvider,bean.listaItems,bean.itemBean" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="Styles/index.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/header.css" rel="stylesheet" type="text/css"/>
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
                    <%--<%
                        listaItems lista = (listaItems) request.getAttribute("listaItemsBean");
                        if (!lista.isEmpty()) {
                            for (itemBean item : lista.getItems()) {
                                out.println("<div class='item'>");
                                out.println("<img class='imgItem' src='img/000001.jpg'></br>");
                                out.println(item.getNome() + "</br>");
                                out.println(item.getCategoria() + "</br>");
                                out.println("</div>");
                            }
                        }
                        else {
                            out.println("<div>è vuoto</div>");
                        }
                    %>
                    <jsp:useBean id="listaItems" class="bean.listaItems" scope="session"  />--%>
                    <%
                        try {
                            Connection con = ConnectionProvider.getCon();

                            PreparedStatement ps = con.prepareStatement("select * from Item");
                            ResultSet rs = ps.executeQuery();
                            //request.setAttribute(name, o);
                            
                            while (rs.next()) {
                                /*itemBean item = new itemBean();
                                item.setNome(rs.getString("nome"));
                                item.setCategoria(rs.getString("categoria"));
                                listaItems.setItems(item);*/
                                for (int i = 0; i < 10; i++) {
                                    //out.println("<div class='item' id='" + listaItems.getIndex() + "'>");
                                    out.println("<div class='item'>");
                                    out.println("<img class='imgItem' src='img/000001.jpg'></br>");
                                    out.println(rs.getString("nome") + "</br>");
                                    out.println(rs.getString("categoria") + "</br>");
                                    out.println("</div>");
                                }
                            }
                        } catch (Exception e) {
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
