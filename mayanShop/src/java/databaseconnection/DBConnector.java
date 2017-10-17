package databaseconnection;

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
    private List<String> items;
    private int totalItems;
    
    public DBConnector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "thomas", "thomas1*");
            st = con.createStatement();
            
            items = this.getItemList();
            totalItems = items.size();
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);    
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    public List<String> getItemList(){
        
        items = new ArrayList<>();
        
        try {            
            String s = "select * from Item";
            rs = st.executeQuery(s);
            System.out.println("Risultati:");
            while(rs.next()){
                String i = rs.getString("nome");
                System.out.print(i);
                items.add(i);                
            }
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return items;
    }
    
    public List<String> getData(String query) {
		String item = null;
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		for(int i=0; i<totalItems; i++) {
			item = items.get(i).toLowerCase();
			if(item.startsWith(query)) {
				matched.add(items.get(i));
			}
		}
		return matched;
	}

}
