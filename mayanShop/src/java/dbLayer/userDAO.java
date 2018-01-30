package dbLayer;

import bean.userBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 * Dao dedicato alla classe utente
 *
 * @author Marcello
 */
public class userDAO {

    /**
     * Funzione che ottiene un oggetto User partendo dalla mail
     *
     * @param email la mail
     * @return un oggetto User associato alla mail
     */
    public static userBean getUser(String email) {
        Connection connection = DAOFactory.getConnection();
        userBean user = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.User WHERE email=\'" + email + "\'");
            if (rs.next()) {
                user = new userBean(
                        rs.getInt("id_user"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        email,
                        rs.getString("password"),
                        rs.getString("tipo"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Funzione che inserisce un utente nel database
     *
     * @param utente l'utente da inserire
     * @return true se la funzione ha successo, false altrimenti
     */
    public static boolean insertUser(userBean utente) {
        String passwordSha;
        passwordSha = new DigestUtils(MessageDigestAlgorithms.SHA_1)
                .digestAsHex(utente.getPassword());
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.User (tipo,nome,cognome,email,password) "
                    + "VALUES (\'registrato\',?,?,?,?);");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, passwordSha);
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
     * Funzione che aggiorna le informazioni di un utente
     *
     * @param utente l'utente al quale aggiornare le informazioni
     * @return true se funziona, false altrimenti
     */
    public static boolean updateInfo(userBean utente) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.User SET User.nome=?, User.cognome=?, User.email=? WHERE id_user=?");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setInt(4, utente.getIdUser());
            int i = ps.executeUpdate();
            if (i == 1) {
                res = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che codifica una password in SHA_1 e la aggiorna nel database
     *
     * @param utente l'utente al quale modificare la password
     * @param password la password da codificare
     * @return true se funziona, false altrimenti
     */
    public static boolean updatePassword(userBean utente, String password) {
        String passwordSha;
        passwordSha = new DigestUtils(MessageDigestAlgorithms.SHA_1)
                .digestAsHex(password);
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE mayandb.User SET User.password=? WHERE id_user=?");
            ps.setString(1, passwordSha);
            ps.setInt(2, utente.getIdUser());
            int i = ps.executeUpdate();
            if (i == 1) {
                res = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static boolean isPasswordCorrect(userBean utente, String password) {
        String passwordSha = null;
        passwordSha = new DigestUtils(MessageDigestAlgorithms.SHA_1).digestAsHex(password);
        return utente.getPassword().equals(passwordSha);
    }

    /**
     * Funzione che indica se l'email inserita è libera
     *
     * @param email la mail da controllare
     * @return true se non esiste un utente con quella mail, false altrimenti
     */
    public static boolean isAvailable(String email) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) as conteggio FROM mayandb.User WHERE email=\'" + email + "\'");
            if (rs.next()) {
                if (rs.getInt("conteggio") == 0) {
                    res = true;
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * ottiene il nome dell'utente specificato
     *
     * @param userId id dell'utente di cui si vuole sapere il nome
     * @return un String contenente il nome, null se fallisce
     */
    public static String getNome(int userId) {
        Connection connection = DAOFactory.getConnection();
        String res = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome FROM mayandb.User WHERE id_user=" + userId + ";");
            if (rs.next()) {
                res = rs.getString("nome");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * ottiene il cognome dell'utente specificato
     *
     * @param userId id dell'utente di cui si vuole sapere il cognome
     * @return un String contenente il cognome, null se fallisce
     */
    public static String getCognome(int userId) {
        Connection connection = DAOFactory.getConnection();
        String res = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cognome FROM mayandb.User WHERE id_user=" + userId + ";");
            if (rs.next()) {
                res = rs.getString("cognome");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
