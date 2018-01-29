/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.messaggioBean;
import static dbLayer.messaggioDAO.findUserInf;
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
public class NotificationChecker{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    int id;
    
    public NotificationChecker(int id){
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
    
    public void update(){
        String query = "UPDATE messaggio SET letto=1 WHERE letto=0 AND id_destinatario='" + this.id + "';";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<messaggioBean> getMessaggi(boolean admin){
        
        ArrayList<messaggioBean> res = new ArrayList<>();
        
        try {
            
            //Cerco tutti i messaggi indirizzati a me
            String query = "SELECT id_messaggio, tipo, id_destinatario, id_mittente, id_transazione, letto FROM Messaggio WHERE id_destinatario='" + this.id + "' ";
            if (admin){
                query += "OR tipo='anomalia' ";
            }
            query += "ORDER BY id_messaggio;";
            try {
                rs = st.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            boolean isEmpty = !rs.first();
            if(!isEmpty){
                //Creo il codice per ogni elemento
                while(rs.next()){
                    
                    messaggioBean m = new messaggioBean();
                    m.setTipo(rs.getString("tipo"));
                    m.setDescrizione(rs.getString("descrizione"));
                    m.setStato(rs.getString("stato"));
                    m.setIdRisposta(rs.getInt("id_risposta"));
                    m.setIdDestinatario(rs.getInt("id_destinatario"));
                    m.setIdMittente(rs.getInt("id_mittente"));
                    m.setIdTransazione(rs.getInt("id_transazione"));
                    m.setLetto(rs.getInt("letto"));
                    m.setNomeDestinatario(rs.getString(findUserInf(rs.getString("id_destinatario")).get(0)));
                    m.setNomeMittente(rs.getString(findUserInf(rs.getString("id_mittente")).get(0)));
                    res.add(m);
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int getUnreadCounter(boolean admin){
        String query = "SELECT id_messaggio FROM Messaggio WHERE letto='0' AND (id_destinatario='" +this.id+ "'";
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
            boolean isEmpty = !rs.first();
            if(!isEmpty){
                while(rs.next()){
                    count++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
//    
}

