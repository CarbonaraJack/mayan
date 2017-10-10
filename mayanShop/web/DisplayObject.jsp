<%-- 
    Document   : DisplayObject
    Created on : Oct 3, 2017, 2:35:25 PM
    Author     : Francy
--%>
<%@include file="index.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.sql.*" %>
<link href="Styles/DisplayObject.css" rel="stylesheet" type="text/css"/>
<%
try {
  Connection dbconn = null;
  Class.forName("com.mysql.jdbc.Driver");
  dbconn = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb?","webuser","public");
  PreparedStatement ps = dbconn.prepareStatement("select * from Item");
  ResultSet rs = ps.executeQuery();
  
  out.println("<div class='griglia'>");
  while(rs.next()){
  out.println("<h2 class='nome'>"+rs.getString("nome")+"</h2>");
  out.println("<img class='immagine' src='img/000001.jpg'></img><br>");
  out.println("<div class='descrizione'>"+rs.getString("descr_item"));
  out.println("<form>");
  out.println("<br><input type='submit' value='Review'/><br>");
  out.println("<br><input type='submit' value='Compra"+" "+rs.getString("prezzo_minimo")+"€'"+"/></div>");
  out.println("</form>");
  }
  
  out.println("</div>");
  out.println("<div class='review'><h2>Recensioni</h2></div>");
  
  PreparedStatement ps2 = dbconn.prepareStatement("select * from Recensione r,User u Where r.id_user=u.id_user");
  ResultSet rs2 = ps2.executeQuery();
  while(rs2.next()){
	out.println(rs2.getString("nome")+" : <br>");
	out.println(rs2.getString("testo"));
	out.println("<br> Stelline : ");
	out.println(rs2.getFloat("stelline"));
  }
  
  
  }  catch (Exception e) {
                }
  %>

