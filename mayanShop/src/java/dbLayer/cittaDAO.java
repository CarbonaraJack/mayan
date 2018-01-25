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
import java.util.ArrayList;

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
    
    public static ArrayList<String> getRegioniByItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> regioni = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(regione) FROM mayandb.Link_Negozio_Item, mayandb.Negozio, mayandb.Location, mayandb.Citta WHERE Link_Negozio_Item.id_negozio=Negozio.id_negozio and Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and Link_Negozio_Item.id_item=" + idItem + ";");
            
            while(rs.next()){
                regioni.add(rs.getString("regione"));
            }
            return regioni;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
