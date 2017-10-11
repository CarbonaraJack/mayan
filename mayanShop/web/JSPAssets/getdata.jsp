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

    // carica il file di classe del driver 
    // per il collegamento al database con il ponte Odbc
    Class.forName("sun.mysql.jdbc.Driver");

    // apre la connessione con il database "miodb"
    dbconn = DriverManager.getConnection(connectionURL,"thomas","thomas1*");

    // manda in esecuzione l'istruzione SQL
    Statement statement = dbconn.createStatement();

    ResultSet rs = statement.executeQuery("SELECT nome FROM Item");

    List<String> results = new ArrayList<>();
    while (rs.next()){
        String s = rs.getString("nome"); //completare con il campo del db
        results.add(s);
    }

    String[] str = new String[results.size()];
           Iterator it = results.iterator();

           int i = 0;
           while(it.hasNext())
           {
               String p = (String)it.next();
               str[i] = p;
               i++;
           }

        //jQuery related start
           String query = (String)request.getParameter("q");

           int cnt=1;
           for(int j=0;j<str.length;j++)
           {
               if(str[j].toUpperCase().startsWith(query.toUpperCase()))
               {
                  out.print(str[j]+"\n");
                  if(cnt>=5)// 5=How many results have to show while we are typing(auto suggestions)
                  break;
                  cnt++;
                }
           }
        //jQuery related end

    rs.close();
    statement.close();
    dbconn.close();

}
    catch(Exception e){
    e.printStackTrace();
}
%>