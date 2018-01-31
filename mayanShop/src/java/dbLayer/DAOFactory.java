package dbLayer;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory per i DAO lato utente
 *
 * @author MarcelloGecchele
 */
public class DAOFactory {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mayandb";
    private static final String USERNAME = "webuser";
    private static final String PASSWORD = "public";

    /**
     * Mi connetto ad un database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella connessione al database", e);
        }
    }
}
