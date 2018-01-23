package dbLayer;

import bean.User;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 *Dao dedicato alla classe utente
 * @author Marcello
 */
public class userDAO {
    /**
     * Funzione che ottiene un oggetto User partendo dalla mail
     * @param email la mail
     * @return un oggetto User associato alla mail
     */
    public static User getUser(String email) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.User WHERE email=\'" + email + "\'");
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id_user"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        email,
                        rs.getString("password"),
                        rs.getString("tipo"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Funzione che codifica una password in SHA_1 e la aggiorna nel database
     * @param utente l'utente al quale modificare la password
     * @param password la password da codificare
     * @return true se funziona, false altrimenti
     */
    public static boolean updatePassword(User utente, String password) {
        String passwordSha = null;
        passwordSha = new DigestUtils(MessageDigestAlgorithms.SHA_1).digestAsHex(password);
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE mayandb.User SET User.password=? WHERE id_user=?");
            ps.setString(1, passwordSha);
            ps.setInt(2, utente.getIdUser());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return false;
    }
    /**
     * Funzione che indica se l'email inserita è libera
     * @param email la mail da controllare
     * @return true se non esiste un utente con quella mail, false altrimenti
     */
    public static boolean isAvailable(String email) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) as conteggio FROM mayandb.User WHERE email=\'" + email + "\'");
            if (rs.next()) {
                if (rs.getInt("conteggio") == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
