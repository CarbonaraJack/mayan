/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.cittaBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO dedicato alla classe cittaBean
 * @author Michela
 */
public class cittaDAO {
    /**
     * funzione che ottiene una citta a partire dall'id della città
     * @param idCitta
     * @return un oggetto cittaBean, null se fallisce
     */
    public static cittaBean getCitta(int idCitta){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Citta WHERE id_citta=" + idCitta + ";");
            
            if(rs.next()){
                cittaBean citta = new cittaBean(
                        rs.getInt("id_citta"),
                        rs.getString("citta"),
                        rs.getString("regione"),
                        rs.getString("stato")
                );
                return citta;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
