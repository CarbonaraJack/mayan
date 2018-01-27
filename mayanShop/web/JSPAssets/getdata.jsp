
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="dbLayer.DBConnector" %>


<%
    //String query = request.getParameter("q");  
    //System.out.println("Connessione DB");
    DBConnector db = new DBConnector();
    
    String query = request.getParameter("q");
    String select = request.getParameter("select");
    System.out.println("ottengo " + select);
    //List<String> items = db.getItems(query);
    //System.out.println("Ottenimento lista");
    List<String> items;
    if(select.equals("negozi")){
        items = db.getDataNegozi(query);
    } else if (select.equals("produttori")) {
        items = db.getDataProduttori(query);
    } else if (select.equals("zone")){
        items = db.getDataZone(query);
    } else {
        items = db.getData(query);
    }
    
    Iterator<String> it = items.iterator();

    int count = 0;
    while(it.hasNext() && count < 5) {
        String item = (String)it.next();
        out.println(item);
        count++;
    }	

%>