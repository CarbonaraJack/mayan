/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.locationBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO dedicato alla classe locationBean
 * @author Michela
 */
public class locationDAO {
    /**
     * ottiene una location a partire dall'id specificato
     * @param idLoc id della location da ottenere
     * @return un oggetto locationBean, null altrimenti
     */
    public static locationBean getLocation(int idLoc){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Location WHERE id_location=" + idLoc + ";");
            
            if(rs.next()){
                locationBean location = new locationBean(
                        rs.getInt("id_location"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine"),
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
    
    public static int getIdCittaByLocation(int idLoc){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_citta FROM mayandb.Location WHERE id_location=" + idLoc + ";");
            
            if(rs.next()){
                return rs.getInt("id_citta");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
