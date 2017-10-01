<%-- 
    Document   : getdata.jsp
    Created on : 1-ott-2017, 16.11.01
    Author     : Thomas
--%>


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

%>