/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class DBSegnalazione {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBSegnalazione(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "webuser", "public");
            st = con.createStatement();
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);    
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public String getVenditore (String idN){
        String idV="";
        String query = "CALL mayandb.getVenditore ("+ idN+")";
        
        try {
            rs = st.executeQuery(query);
            if(rs.next()){
                idV = rs.getString("id_proprietario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idV;
    }
    
    public boolean insertSegnalazione(String testo, int idVenditore, int userId, int idTransazione){
        boolean isDone = false;
        String query = "CALL mayandb.insertReclamo("+testo+","+idVenditore+","+userId+","+idTransazione+");";
        
        try {
            int num = st.executeUpdate(query);
            if(num == 1){
                isDone = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isDone;
    }
}
