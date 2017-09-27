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
        <title>JSP Asset Test</title>
    </head>
    <body>
        <jsp:include page="/JSPAssets/header.jsp"/>
        <div>
            <%
                try {
                    Connection con = ConnectionProvider.getCon();

                    PreparedStatement ps = con.prepareStatement("select * from item");
                    ResultSet rs = ps.executeQuery();
                    
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Nome</th>");
                    out.println("<th>Descrizione</th>");
                    out.println("<th>Categoria</th>");
                    out.println("</tr>");
                    
                    while(rs.next()){
                        out.println("<tr>");
                        out.println("<td>"+rs.getString("nome")+"</td>");
                        out.println("<td>"+rs.getString("descr_item")+"</td>");
                        out.println("<td>"+rs.getString("categoria")+"</td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</table>");                    
                } catch (Exception e) {
                }
            %>
        </div>
    </body>
</html>
