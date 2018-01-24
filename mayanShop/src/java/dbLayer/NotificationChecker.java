/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class NotificationChecker implements Serializable {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    String id="";
    
    public NotificationChecker(String id){
        this.id = id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "thomas", "thomas1*");
            st = con.createStatement();
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);    
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public boolean isAdmin(){
        boolean isAdmin = false;
        String query = "SELECT tipo FROM user WHERE id_user='" + this.id +"';";
        try {
            rs=st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){
                if (rs.getString("tipo") == "amministratore"){
                    isAdmin = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdmin;
    }
    
    public void update(){
        String query = "UPDATE messaggio SET letto=1 WHERE letto=0 AND id_destinatario='" + this.id + "';";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMessaggi(String output, boolean admin) throws SQLException{
        String query = "SELECT * FROM messaggio ORDER BY id_messaggio WHERE id_destinatario='" + this.id + "'";
        if (admin){
            query += " OR tipo='anomalia'";
        }
        query += ";";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean isEmpty = !rs.first();
        if(!isEmpty){
            while(rs.next()){
                output = output + "<li>\n" +
                "    <a href=\"#\">\n" +
                "     <strong>" + rs.getString("tipo") + " - " + rs.getString("id_mittente") + "</strong><br />\n" +
                "     <small><em>" + rs.getString("descrizione") + "</em></small>\n" +
                "    </a>\n" +
                "   </li>\n" +
                "   <li class=\"divider\"></li>";
            }
        } else {
            output = output + "<li><a href=\"#\" class=\"text-bold text-italic\">No Notification Found</a></li>";
        }
        
        return output;
    }
    
    public int getUnread(boolean admin){
        String query = "SELECT * FROM messaggio WHERE letto=0 AND (id_destinatario='" +this.id+ "'";
        if(admin){
            query += " OR tipo='anomalia'";
        }
        query += ");";
        
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        try {
            while(rs.next()){
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}

