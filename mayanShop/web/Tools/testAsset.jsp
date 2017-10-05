<%-- 
    Document   : testAsset
    Created on : 18-set-2017, 16.21.04
    Author     : Michela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet" %>
<%@page import="bean.ConnectionProvider" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../Styles/header.css" rel="stylesheet" type="text/css"/>
        <link href="../Styles/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Asset Test</title>
    </head>
    <body>
        <jsp:include page="/JSPAssets/header.jsp"/>
        <div>
            <form name="search" action="../controlloItems" method="GET">
                <input name="testo"/>
                <input type="submit" value="Vai"/>
            </form>
        <%
            try {
                Connection con = ConnectionProvider.getCon();

                PreparedStatement ps = con.prepareStatement("select * from Item");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    for (int i = 0; i < 10; i++) {
                        out.println("<div>");
                        out.println(rs.getString("nome") + "</br>");
                        out.println(rs.getString("categoria") + "</br>");
                        out.println("</div>");
                    }
                }
            } catch (Exception e) {
            }
        %>
        </div>
    </body>
</html>
