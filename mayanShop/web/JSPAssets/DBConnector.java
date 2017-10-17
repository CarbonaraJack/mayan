/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class DBConnector {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "thomas", "thomas1*");
            st = con.createStatement();
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);    
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public List<String> getItems(String query){
        
        List<String> results = new ArrayList<>();
        
        try {            
            String s = "select nome from Item where nome like " + query;
            rs = st.executeQuery(s);
            while(rs.next()){
                String i = rs.getString("nome");
                results.add(i);                
            }
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return results;
    }
    
    public List<String> getItemList(){
        
        List<String> results = new ArrayList<>();
        
        try {            
            String s = "select * from Item";
            rs = st.executeQuery(s);
            System.out.println("Risultati:");
            while(rs.next()){
                String i = rs.getString("nome");
                System.out.println(i);
                results.add(i);                
            }
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return results;
    }


}