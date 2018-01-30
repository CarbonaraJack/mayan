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
        Connection connection = DAOFactory.getConnection();
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
        Connection connection = DAOFactory.getConnection();
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
    
    /**
     * Funzione che ritorna un Messaggio dato l'id dello stesso
     * 
     * @param idM id del messaggio
     * @return il messaggio
     */    
    
    public static messaggioBean getMessage(int idM) {
        Connection connection = DAOFactory.getConnection();
        messaggioBean m = new messaggioBean();
        String query = "SELECT * FROM Messaggio WHERE id_messaggio='" + idM + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                //La query ritorna una sola riga, quindi ne inserisco i parametri nel messaggio
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
                        findUserInf(rs.getString("id_mittente")).get(0), //prendo il nome del mittente
                        findUserInf(rs.getString("id_destinatario")).get(0)); //prendo il nome del destinatario
                        
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    /**
     * Fornisce nome e tipo dell'utente dato l'id
     * 
     * @param id dell'utente
     * @return arraylist con nome e tipo
     */
    public static ArrayList<String> findUserInf(String id) {
        Connection connection = DAOFactory.getConnection();
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

        Connection connection = DAOFactory.getConnection();
        String query = "UPDATE Messaggio SET letto='1' WHERE id_messaggio='" + idMessaggio + "';";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ritorna un arraylist contentente la lista di messaggi che l'utente deve leggere
     * Inoltre, l'amministratore visualizzerà tutte le notifiche delle segnalazioni
     * 
     * @param admin boolean che indica se l'utente è un admin o no
     * @param userId id dell'utente
     * @return l'arraylist contenente i messaggi
     */
    public static ArrayList<messaggioBean> getMessaggeList(boolean admin, int userId) {

        ArrayList<messaggioBean> res = new ArrayList<>();
        Connection connection = DAOFactory.getConnection();
        ResultSet rs;

        try {

            String query = "SELECT * FROM Messaggio WHERE ";
            //Se l'utente è admin, seleziono tutte le segnalazioni
            if (admin) {
                query += "id_mittente!='" + userId + "' ";
            } else { //altrimenti solo i messaggi in cui sono il destinatario
                query += "id_destinatario='" + userId + "' ";
            }
            query += "ORDER BY id_messaggio DESC;";
            Statement st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            //scorro il ResultSet e aggiungo i messaggi all'array
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

    /**
     * Ritorno il numero di segnalazioni non lette dall'utente
     * 
     * @param admin boolean che indica se l'utente è un amministratore o no
     * @param idM id dell'utente
     * @return il numero di notifiche
     */
    public static int getUnreadCounter(boolean admin, int idM) {

        Connection connection = DAOFactory.getConnection();
        String query = "SELECT count(id_messaggio) as num FROM Messaggio WHERE ";
        //se sono admin, ricevo tutte le notifiche
        if (admin) {
            query += "id_mittente!='" + idM + "' AND stato='aperta'";
        } else { //altrimenti, solo le notifiche destinate a me
            query += "letto='0' AND id_destinatario='" + idM + "'";
        }
        query += ";";

        int count = 0;

        try {
            Statement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            //salvo il valore ottenuto dalla query
            while (rs.next()) {
                count = rs.getInt("num");
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    /**
     * Metodo che gestisce l'inserimento di una risoluzione dall'admin ad una segnalazione
     * il metodo chiamerà una funzione ricorsiva per la chiusura dei messaggi precedenti, in
     * modo che non sia più possibile interagire con essi
     * 
     * @param text la risposta
     * @param idM l'id del mittente
     * @param idD l'id del destinatario
     * @param idT lid della transazione
     * @param idR l'id della risposta
     * @return boolean utilizzato per la gestione di eventuali errori nella mancata
     * mancata riuscita della query
     */
    public static boolean rifSegnalazione(String text, int idM, int idD, int idT, int idR) {
        boolean isDone = false;
        Connection connection = DAOFactory.getConnection();

        String query = "INSERT INTO Messaggio (tipo, descrizione, stato, id_risposta, id_destinatario, id_mittente, id_transazione, letto)"
                + "VALUES ('risoluzione', '" + text + "', 'chiusa', '" + idR + "', '" + idD + "', '" + idM + "', '" + idT + "', '0');";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);
            //controllo se la query è andata a buon fine
            if (i == 1) {
                isDone = true;
            }
            connection.close();
        } catch (SQLException ex) { 
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //richiamo la funzione ricorsiva setChiusa(idR) per la chiusura ad albero 
        //delle risposte
        isDone = setChiusa(idR);
        return isDone; //Ritorno il parametro per la gestione dell'errore
    }

    /**
     * Funzione ricorsiva per la chiusura ad albero dei messaggi collegati al messaggio
     * di cui viene fornito l'id
     * 
     * @param idM l'id del messaggio da chiudere
     * @return boolean per la gestione degli errori della query
     */
    public static boolean setChiusa(int idM) {
        boolean isDone = false;
        Connection connection = DAOFactory.getConnection();
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
    
    /**
     * Funzione che inserisce la risposta dell'amministratore mantenendo aperto 
     * il messaggio ad eventuale risposte
     * 
     * @param text la risposta
     * @param idM id del mittente
     * @param idD id del destinatario
     * @param idT id della transazione
     * @param idR id della risposta
     * @return boolean per la gestione degli errori
     */
    public static boolean risSegnalazione(String text, int idM, int idD, int idT, int idR) {
        boolean isDone = false;
        Connection connection = DAOFactory.getConnection();

        String query = "INSERT INTO Messaggio (tipo, descrizione, stato, id_risposta, id_destinatario, id_mittente, id_transazione, letto)"
                + "VALUES ('risposta', '" + text + "', 'aperta', '" + idR + "', '" + idD + "', '" + idM + "', '" + idT + "', '0');";
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(query);

            //Controllo che la query abbia funzionante
            if (i == 1) {
                isDone = true;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isDone;
    }
    
    /**
     * Metodo che controlla se l'utente ha già creato una segnalazione per un prodotto
     * 
     * @param idTransazione l'id della transazione
     * @param idUtente l'id dell'utente
     * @return intero per la gestione del risultato della query
     */
    public static int checkSegnalazione(int idTransazione, int idUtente){
        Connection connection = DAOFactory.getConnection();
        int isSent = 0;
        String query = "CALL mayandb.checkSegnalazione('"+ idTransazione +"', '" + idUtente + "');";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            //scorro il valore
            while (rs.next()){
                int i = rs.getInt("num");
                //se il risultato del count della query è > 0, setto il risultato ad 1
                if(i>0){
                    isSent = 1;
                }
            }                       
            
        } catch (SQLException ex) {
            Logger.getLogger(messaggioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSent;
    }
}
