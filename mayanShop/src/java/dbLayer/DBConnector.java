package dbLayer;

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
    private List<String> negozi;
    private int totalNegozi;
    private List<String> produttori;
    private int totalProduttori;
    private List<String> zone;
    private int totalZone;
    
    public DBConnector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "webuser", "public");
            st = con.createStatement();
            
            items = this.getItemList();
            totalItems = items.size();
            
            negozi = this.getNegoziList();
            totalNegozi = negozi.size();
            
            produttori = this.getProduttoriList();
            totalProduttori = produttori.size();
            
            zone = this.getZoneList();
            totalZone = zone.size();
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
            while(rs.next()){
                String i = rs.getString("nome");
                items.add(i);                
            }
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return items;
    }
    
    public List<String> getNegoziList(){
        negozi = new ArrayList<>();
        try {            
            String s = "select * from Negozio";
            rs = st.executeQuery(s);
            while(rs.next()){
                String i = rs.getString("nome");
                negozi.add(i);                
            } 
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return negozi;
    }
    
    public List<String> getProduttoriList(){
        produttori = new ArrayList<>();
        try {            
            String s = "select distinct(produttore) from Item";
            rs = st.executeQuery(s);
            while(rs.next()){
                String i = rs.getString("produttore");
                produttori.add(i);                
            } 
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return produttori;
    }
    
    public List<String> getZoneList(){
        zone = new ArrayList<>();
        try {            
            String s = "select distinct(citta) from Citta;";
            rs = st.executeQuery(s);
            while(rs.next()){
                String i = rs.getString("citta");
                zone.add(i);                
            }
            s = "select distinct(regione) from Citta;";
            rs = st.executeQuery(s);
            while(rs.next()){
                String i = rs.getString("regione");
                zone.add(i);                
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return zone;
    }
    
    public List<String> getData(String query) {
		String item = null;
		query = query.toLowerCase();
		List<String> matched = new ArrayList<String>();
		for(int i=0; i<totalItems; i++) {
			item = items.get(i).toLowerCase();
			if(item.contains(query)) {
				matched.add(items.get(i));
			}
		}
		return matched;
	}
    
    public List<String> getDataNegozi(String query) {
	String negozio = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<totalNegozi; i++) {
            negozio = negozi.get(i).toLowerCase();
            if(negozio.contains(query)) {
                matched.add(negozi.get(i));
            }
	}
	return matched;
    }

    public List<String> getDataProduttori(String query) {
	String negozio = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<totalProduttori; i++) {
            negozio = produttori.get(i).toLowerCase();
            if(negozio.contains(query)) {
                matched.add(produttori.get(i));
            }
	}
	return matched;
    }
    
    public List<String> getDataZone(String query) {
	String negozio = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<totalZone; i++) {
            negozio = zone.get(i).toLowerCase();
            if(negozio.contains(query)) {
                matched.add(zone.get(i));
            }
	}
	return matched;
    }
}
