package dbLayer;

import bean.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marcello
 */
public class userDAO {

    public User getUser(String email) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.user WHERE email=\'" + email + "\'");
            if (rs.next()) {
                User user = new User(
                            rs.getInt("id_user"),
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            email,
                            rs.getString("password"),
                            rs.getString("salt"),
                            rs.getString("tipo"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean isAvailable(String email){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) as conteggio FROM mayandb.user WHERE email=\'" + email + "\'");
            if (rs.next()) {
                if(rs.getInt("conteggio")==0){
                    return true;
                }
                else return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
