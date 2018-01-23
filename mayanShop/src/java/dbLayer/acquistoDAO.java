/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Michela
 */
public class acquistoDAO {
    public static boolean insertAcquisto(int quantità, double prezzo, Date dataora, int idItem, int idUser, int idVenditore){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO mayandb.Acquisto (quantità,prezzo,dataora,id_item,id_user,id_venditore) "+
                "VALUES (?,?,?,?,?,?);");
            ps.setString(1, String.valueOf(quantità));
            ps.setString(2, String.valueOf(prezzo));
            ps.setString(3, String.valueOf(dataora));
            ps.setString(4, String.valueOf(idItem));
            ps.setString(5, String.valueOf(idUser));
            ps.setString(6, String.valueOf(idVenditore));
            int b = ps.executeUpdate();
            if (b == 1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
