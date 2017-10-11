
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>


<%
    //String query = request.getParameter("q");  
    //System.out.println("Connessione DB");
    DBConnector db = new DBConnector();

    //List<String> items = db.getItems(query);
    //System.out.println("Ottenimento lista");
    List<String> items = db.getItemList();

    Iterator<String> it = items.iterator();

    while(it.hasNext()) {
        String item = (String)it.next();
        //System.out.println(item);
        out.println(item);
    }	

%>