<%-- 
    Document   : getdata.jsp
    Created on : 1-ott-2017, 16.11.01
    Author     : Thomas
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 

<%
try{ 
    Connection dbconn = null;
    String connectionURL = "jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb";
    Class.forName("com.mysql.jdbc.Driver");
    dbconn = DriverManager.getConnection(connectionURL,"thomas","thomas1*");

    // manda in esecuzione l'istruzione SQL
    Statement statement = dbconn.createStatement();

    String query = (String)request.getParameter("q");
    ResultSet rs = statement.executeQuery("SELECT nome FROM Item WHERE nome LIKE " + query);

    List<String> results = new ArrayList<>();
    while (rs.next()){
        String s = rs.getString("nome"); //completare con il campo del db
        results.add(s);
    }

    
    Iterator it = results.iterator();

           while(it.hasNext())
           {
               String p = (String)it.next();
               out.println(p);
               
           }

}
    catch(Exception e){
    e.printStackTrace();
}
%>