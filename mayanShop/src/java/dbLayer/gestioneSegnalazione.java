package dbLayer;

import bean.messaggioBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class gestioneSegnalazione {

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
            Logger.getLogger(gestioneSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isDone;
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
            Logger.getLogger(gestioneSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
        }
        isDone = setChiusa(idR);
        return isDone;
    }

    public static boolean setChiusa(int idM) {
        boolean isDone = false;
        Connection connection = DAOFactoryUsers.getConnection();
        messaggioBean m = dbLayer.messaggioDAO.getMessage(idM);
        while (m.getIdRisposta() != -1) {
            String query = "UPDATE SET stato='chiusa' WHERE id_risposta='" + idM + "';";
            try {
                Statement st = connection.createStatement();
                int i = st.executeUpdate(query);
                if (i == 1) {
                    isDone = true;
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(gestioneSegnalazione.class.getName()).log(Level.SEVERE, null, ex);
            }

            isDone = setChiusa(m.getIdRisposta());
        }
        return isDone;
    }
}
