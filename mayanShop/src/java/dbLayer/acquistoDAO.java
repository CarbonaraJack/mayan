package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Michela
 */
public class acquistoDAO {

    /**
     * funzione che aggiunge un acquisto
     *
     * @param quantità
     * @param prezzo
     * @param dataora
     * @param idItem
     * @param idUser
     * @param idNegozio
     * @return true se l'inserimento va a buon fine, false se l'inserimento
     * fallisce
     */
    public static boolean insertAcquisto(int quantità, double prezzo, Date dataora, int idItem, int idUser, int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Acquisto (quantità,prezzo,dataora,id_item,id_user,id_negozio) VALUES (?,?,\'" + dateFormat.format(dataora) + "\',?,?,?);");
            ps.setString(1, String.valueOf(quantità));
            ps.setString(2, String.valueOf(prezzo));
            ps.setString(3, String.valueOf(idItem));
            ps.setString(4, String.valueOf(idUser));
            ps.setString(5, String.valueOf(idNegozio));
            int b = ps.executeUpdate();
            if (b == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
