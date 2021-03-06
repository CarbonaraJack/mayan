package dbLayer;

import bean.locationBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO dedicato alla classe locationBean
 *
 * @author Michela
 */
public class locationDAO {

    /**
     * ottiene una location a partire dall'id specificato
     *
     * @param idLoc id della location da ottenere
     * @return un oggetto locationBean, null altrimenti
     */
    public static locationBean getLocation(int idLoc) {
        Connection connection = DAOFactory.getConnection();
        locationBean location = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Location WHERE id_location=" + idLoc + ";");

            if (rs.next()) {
                location = new locationBean(
                        rs.getInt("id_location"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine"),
                        rs.getString("via"),
                        rs.getInt("id_citta")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return location;
    }

    /**
     * Funzione che aggiorna una location
     *
     * @param location la location da aggiornare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean updateLocation(locationBean location) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Location SET "
                    + "latitudine=?, longitudine=?,id_citta=?,via=? WHERE id_location=?;");
            ps.setString(1, location.getLatitudine());
            ps.setString(2, location.getLongitudine());
            ps.setInt(3, location.getIdCitta());
            ps.setString(4, location.getVia());
            ps.setInt(5, location.getIdLocation());
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
     * Funzione che inserisce una nuova location nel database
     *
     * @param location la location da inserire
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean insertLocation(locationBean location) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Location "
                    + "(latitudine, longitudine, id_citta, via) VALUES "
                    + "(?,?,?,?);");
            ps.setString(1, location.getLatitudine());
            ps.setString(2, location.getLongitudine());
            ps.setInt(3, location.getIdCitta());
            ps.setString(4, location.getVia());
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
     * funzione che cerca l'id di una data location
     *
     * @param location location di cui si vuole trovare l'id
     * @return id della location specificata, -1 se non viene trovata
     */
    public static int findIdLocation(locationBean location) {
        Connection connection = DAOFactory.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT *,count(*) as conteggio FROM mayandb.Location "
                    + "WHERE latitudine=\'"
                    + location.getLatitudine()
                    + "\' AND longitudine=\'"
                    + location.getLongitudine()
                    + "\'AND id_citta="
                    + location.getIdCitta()
                    + " AND via=\'"
                    + location.getVia()
                    + "\';"
            );
            if (rs.next()) {
                if (rs.getInt("conteggio") == 0) {

                    //la location non è nel db
                    if (insertLocation(location)) {
                        //richiamo la funzione
                        res = findIdLocation(location);
                    }
                } else {
                    res = rs.getInt("id_location");
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
