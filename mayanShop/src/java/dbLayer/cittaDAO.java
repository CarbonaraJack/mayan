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
 *
 * @author Michela
 */
public class cittaDAO {
    public static cittaBean getCitta(int idCitta){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Citta WHERE id_citta=" + idCitta + ";");
            
            if(rs.next()){
                cittaBean citta = new cittaBean(
                        rs.getInt("id_location"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine"),
                        rs.getString("via")
                );
                return citta;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}