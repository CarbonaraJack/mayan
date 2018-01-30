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
        String res = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "CALL mayandb.getVenditore (" + idNegozio + ")");

            if (rs.next()) {
                res = rs.getString("id_proprietario");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return res;
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
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.insertReclamo(?,?,?,?);");
            ps.setString(1, testo);
            ps.setInt(2, idVenditore);
            ps.setInt(3, userId);
            ps.setInt(4, idTransazione);
            int i = ps.executeUpdate();
            if (i == 1) {
                res = true;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static messaggioBean getMessage(int idM) {
        Connection connection = DAOFactoryUsers.getConnection();
        messaggioBean m = new messaggioBean();
        String query = "SELECT * FROM Messaggio WHERE id_messaggio='" + idM + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                m = new messaggioBean(
                        rs.getInt("id_messaggio"),
                        rs.getString("tipo"),
                        rs.getString("descrizione"),
                        rs.getString("stato"),
                        rs.getInt("id_risposta"),
                        rs.getInt("id_destinatario"),
                        rs.getInt("id_mittente"),
                        rs.getInt("id_transazione"),
                        rs.getInt("letto"),
                        findUserInf(rs.getString("id_mittente")).get(0),
                        findUserInf(rs.getString("id_destinatario")).get(0));
                        
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public static ArrayList<String> findUserInf(String id) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> res = new ArrayList<>();
        String query = "SELECT nome,cognome,tipo FROM User WHERE id_user='" + id + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                res.add(rs.getString("nome") + " " + rs.getString("cognome"));
                res.add(rs.getString("tipo"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    public static void update(int idMessaggio) {

        Connection connection = DAOFactoryUsers.getConnection();
        String query = "UPDATE Messaggio SET letto='1' WHERE id_messaggio='" + idMessaggio + "';";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<messaggioBean> getMessaggeList(boolean admin, int userId) {

        ArrayList<messaggioBean> res = new ArrayList<>();
        Connection connection = DAOFactoryUsers.getConnection();
        ResultSet rs;

        try {

            //Cerco tutti i messaggi indirizzati a me
            String query = "SELECT * FROM Messaggio WHERE ";
            if (admin) {
                query += "id_destinatario!='" + userId + "' AND id_mittente!='" + userId + "' AND tipo='anomalia' ";
            } else {
                query += "id_destinatario='" + userId + "' ";
            }
            query += "ORDER BY id_messaggio DESC;";
            Statement st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                messaggioBean m = new messaggioBean();
                    m.setId_messaggio(rs.getInt("id_messaggio"));
                    m.setTipo(rs.getString("tipo"));
                    m.setDescrizione(rs.getString("descrizione"));
                    m.setStato(rs.getString("stato"));
                    m.setId_risposta(rs.getInt("id_risposta"));
                    m.setId_destinatario(rs.getInt("id_destinatario"));
                    m.setId_mittente(rs.getInt("id_mittente"));
                    m.setId_transazione(rs.getInt("id_transazione"));
                    m.setLetto(rs.getInt("letto"));
                    m.setNomeDestinatario(findUserInf(rs.getString("id_destinatario")).get(0));
                    m.setNomeMittente(findUserInf(rs.getString("id_mittente")).get(0));
                    res.add(m);
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static int getUnreadCounter(boolean admin, int idM) {

        Connection connection = DAOFactoryUsers.getConnection();
        String query = "SELECT count(id_messaggio) as num FROM Messaggio WHERE ";
        if (admin) {
            query += "id_mittente!='" + idM + "' AND tipo='anomalia' AND stato='aperta'";
        } else {
            query += "letto='0' AND id_destinatario='" + idM + "'";
        }
        query += ";";

        int count = 0;

        try {
            Statement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                count = rs.getInt("num");
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    public static boolean rifSegnalazione(String text, int idM, int idD, int idT, int idR) {
        boolean isDone = false;
        Connection connection = DAOFactoryUsers.getConnection();

        String query = "INSERT INTO Messaggio (tipo, descrizione, stato, id_risposta, id_destinatario, id_mittente, id_transazione, letto)"
                + "VALUES ('risoluzione', '" + text + "', 'chiusa', '" + idR + "', '" + idD + "', '" + idM + "', '" + idT + "', '0');";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            if (i == 1) {
                isDone = true;
            }
            connection.close();
        } catch (SQLException ex) { 
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        isDone = setChiusa(idR);
        return isDone;
    }

    public static boolean setChiusa(int idM) {
        boolean isDone = false;
        Connection connection = DAOFactoryUsers.getConnection();
        messaggioBean m = dbLayer.messaggioDAO.getMessage(idM);
        String query = "UPDATE Messaggio SET stato='chiusa' WHERE id_messaggio='" + idM + "';";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            if (i == 1) {
                isDone = true;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }            
        if(m.getId_risposta() != 0) {isDone = setChiusa(m.getId_risposta());}
        return isDone;
    }
    
    public static boolean risSegnalazione(String text, int idM, int idD, int idT, int idR) {
        boolean isDone = false;
        Connection connection = DAOFactoryUsers.getConnection();

        String query = "INSERT INTO Messaggio (tipo, descrizione, stato, id_risposta, id_destinatario, id_mittente, id_transazione, letto)"
                + "VALUES ('risposta', '" + text + "', 'aperta', '" + idR + "', '" + idD + "', '" + idM + "', '" + idT + "', '0');";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);

            if (i == 1) {
                isDone = true;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isDone;
    }
    
}
