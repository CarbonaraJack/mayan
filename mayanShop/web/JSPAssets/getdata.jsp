
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>


<%
    String query = request.getParameter("q");
    String select = request.getParameter("select");
    
    List<String> items;
    if (select.equals("negozi")) {
        items = dbLayer.negozioDAO.getDataNegozi(query);
    } else if (select.equals("produttori")) {
        items = dbLayer.itemDAO.getDataProduttori(query);
    } else if (select.equals("zone")) {
        items = dbLayer.cittaDAO.getDataZone(query);
    } else {
        items = dbLayer.itemDAO.getData(query);
    }

    Iterator<String> it = items.iterator();

    int count = 0;
    while (it.hasNext() && count < 5) {
        String item = (String) it.next();
        out.println(item);
        count++;
    }

%>