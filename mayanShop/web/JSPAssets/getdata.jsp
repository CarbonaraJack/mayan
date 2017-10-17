
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="databaseconnection.DBConnector" %>


<%
    //String query = request.getParameter("q");  
    //System.out.println("Connessione DB");
    DBConnector db = new DBConnector();
    
    String query = request.getParameter("q");

    //List<String> items = db.getItems(query);
    //System.out.println("Ottenimento lista");
    List<String> items = db.getData(query);

    Iterator<String> it = items.iterator();

    while(it.hasNext()) {
        String item = (String)it.next();
        //System.out.println(item);
        out.println(item);
    }	

%>