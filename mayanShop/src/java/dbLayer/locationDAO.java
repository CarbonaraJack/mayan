/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.locationBean;
import bean.negozioBean;
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
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Location WHERE id_location=" + idLoc + ";");

            if (rs.next()) {
                locationBean location = new locationBean(
                        rs.getInt("id_location"),
                        rs.getFloat("latitudine"),
                        rs.getFloat("longitudine"),
                        rs.getString("via"),
                        rs.getInt("id_citta")
                );
                return location;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Funzione che aggiorna una location
     *
     * @param location la location da aggiornare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean updateLocation(locationBean location) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Location SET "
                    + "latitudine=?, longitudine=?,id_citta=?,via=? WHERE id_location=?;");
            ps.setFloat(1, location.getLatitudine());
            ps.setFloat(2, location.getLongitudine());
            ps.setInt(3, location.getIdCitta());
            ps.setString(4, location.getVia());
            ps.setInt(5, location.getIdLocation());
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
     * Funzione che inserisce una nuova location nel database
     *
     * @param location la location da inserire
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean insertLocation(locationBean location) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Location "
                    + "(latitudine, longitudine, id_citta, via) VALUES "
                    + "(?,?,?,?);");
            ps.setFloat(1, location.getLatitudine());
            ps.setFloat(2, location.getLongitudine());
            ps.setInt(3, location.getIdCitta());
            ps.setString(4, location.getVia());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static int findIdLocation(locationBean location) {
        Connection connection = DAOFactoryUsers.getConnection();

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
                        return findIdLocation(location);
                    } else {
                        return -1;

                    }
                } else {
                    return rs.getInt("id_location");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
