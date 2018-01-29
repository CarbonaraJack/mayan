package dbLayer;

import bean.messaggioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe per interfacciarsi con la tabella messaggio
 *
 * @author Thomas
 */
public class messaggioDAO {

    /**
     * Funzione che ottiene il venditore dato l'id di un negozio
     *
     * @param idNegozio l'id del negozio
     * @return l'id del venditore
     */
    public static String getVenditore(String idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "CALL mayandb.getVenditore (" + idNegozio + ")");

            if (rs.next()) {
                return rs.getString("id_proprietario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Funzione che inserisce una segnalazione nel database
     *
     * @param testo il testo della segnalazione
     * @param idVenditore l'id del venditore
     * @param userId l'id del segnalante
     * @param idTransazione l'id della transazione
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean insertSegnalazione(String testo, int idVenditore, int userId, int idTransazione) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.insertReclamo(?,?,?,?);");
            ps.setString(1, testo);
            ps.setInt(2, idVenditore);
            ps.setInt(3, userId);
            ps.setInt(4, idTransazione);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static messaggioBean getMessage(int idM){
        Connection connection = DAOFactoryUsers.getConnection();
        messaggioBean m = new messaggioBean();
        String query = "SELECT * FROM Messaggio WHERE id_messaggio='" + idM + "';";        
        try {
            Statement st =  connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return new messaggioBean(
                        
                        rs.getInt("id_messaggio"),
                        rs.getString("tipo"), 
                        rs.getString("descrizione"), 
                        rs.getString("stato"), 
                        rs.getInt("id_risposta"),  
                        rs.getInt("id_destinatario"), 
                        rs.getInt("id_mittente"), 
                        rs.getInt("id_transazione"), 
                        rs.getInt("letto"), 
                        rs.getString(findUserInf(rs.getString("id_mittente")).get(0)),
                        rs.getString(findUserInf(rs.getString("id_destinatario")).get(0)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

        
    public static ArrayList<String> findUserInf(String id){
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> res = new ArrayList<>();
        String query="SELECT nome,cognome,tipo FROM User WHERE id_user='"+ id +"';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                res.add(rs.getString("nome") + " " + rs.getString("cognome"));
                res.add(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
}
