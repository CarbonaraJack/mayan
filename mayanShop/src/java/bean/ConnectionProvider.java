package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import static bean.Provider.*;

/**
 *
 * @author Michela
 */
public class ConnectionProvider {

    static Connection con = null;

    static {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
        }
    }

    public static Connection getCon() {
        return con;
    }
}
