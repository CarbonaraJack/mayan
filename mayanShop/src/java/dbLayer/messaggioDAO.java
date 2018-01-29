package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
