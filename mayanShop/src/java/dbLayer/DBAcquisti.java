/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.Acquisto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class DBAcquisti {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    //private String userId;
    
    
    public DBAcquisti(){
        //this.userId = id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb", "webuser", "public");
            st = con.createStatement();
            
            //this.acquisti = this.getListaAcquisti(id);
            //this.numeroAcquisti = acquisti.size();
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);    
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
  
    public ArrayList<Acquisto> getListaAcquisti(String userId){
                
        ArrayList<Acquisto> lista = new ArrayList<>();
                   
        String s = "CALL mayandb.getListaAcquisti ("+userId+");";
        
         try {   
            rs = st.executeQuery(s);             
            while(rs.next()){
                int idTransazione = rs.getInt("a.id_transazione");
                int quantità = rs.getInt("a.quantità");
                int prezzo = rs.getInt("a.prezzo");
                String dataora = rs.getString("a.dataora");
                int idItem = rs.getInt("l.id_item");
                String nomeItem = rs.getString("o.nome");
                String linkFoto = rs.getString("linkFoto");
                int idUser = rs.getInt("a.id_user");
                int idNegozio = rs.getInt("n.id_negozio");
                String nomeNegozio = rs.getString("n.nome");
                Acquisto a = new Acquisto(idTransazione, quantità, prezzo, dataora, idItem, nomeItem, linkFoto, idUser, idNegozio, nomeNegozio);
                lista.add(a);
                
            } 
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return lista;
    }
    
}
