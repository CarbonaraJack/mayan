/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class gestioneSegnalazione {
 
    public boolean risSegnalazione(String text, int idM, int idD, int idT, int idR){
        boolean isDone=false;
        Connection connection = DAOFactoryUsers.getConnection();
        
        String query = "INSERT INTO Messaggio (tipo, descrizione, stato, id_risposta, id_destinatario, id_mittente, id_transazione, letto)" +
                    "VALUES ('anomalia', '" + text + "', 'aperta', '"+ idR +"', '" + idD + "', '" + idM + "', '" + idT+ "', '0');";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            
            if(i==1){
                isDone = true;                       
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(gestioneSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isDone;
    } 
    
}
