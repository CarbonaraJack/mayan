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
    
Connection dbconn = null;
String connectionURL = "jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/";

// carica il file di classe del driver 
// per il collegamento al database con il ponte Odbc
Class.forName("sun.mysql.jdbc.Driver");

// apre la connessione con il database "miodb"
dbconn = DriverManager.getConnection(connectionURL,"admin","pwd");

// manda in esecuzione l'istruzione SQL
Statement statement = dbconn.createStatement();


String query = "SELECT "; //Query da completare!
ResultSet rs = statement.executeQuery(query);

List<String> results = new ArrayList<String>();
while (rs.next()){
    String s = rs.getString(""); //completare con il campo del db
    results.add(s);
}

Iterator<String> iterator = results.iterator();
	while(iterator.hasNext()) {
		String result = (String)iterator.next();
		out.println(result);
	}
%>