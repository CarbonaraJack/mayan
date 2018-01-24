/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.itemNegozioBean;
import bean.negozioBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Michela
 */
public class negozioDAO {
    public static negozioBean getNegozio(int idNeg){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Negozio WHERE id_negozio=" + idNeg + ";");
            
            if(rs.next()){
                negozioBean negozio = new negozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getString("web_link"),
                        rs.getDouble("valutazione_media"),
                        rs.getString("orari"),
                        rs.getString("tipo"),
                        rs.getInt("num_warning"),
                        rs.getInt("id_location")
                );
                return negozio;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<negozioBean> getNegoziByLocation(int idLocation){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<negozioBean> lista = new ArrayList<negozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Negozio WHERE id_location=" + idLocation + ";");
            
            while(rs.next()){
                negozioBean negozio = new negozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getString("web_link"),
                        rs.getDouble("valutazione_media"),
                        rs.getString("orari"),
                        rs.getString("tipo"),
                        rs.getInt("num_warning"),
                        rs.getInt("id_location")
                );
                lista.add(negozio);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<itemNegozioBean> getNegoziByItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<itemNegozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_item, Negozio.id_negozio, num_stock, prezzo, nome, tipo FROM mayandb.Negozio, mayandb.Link_Negozio_Item WHERE Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + idItem + ";");
            
            while(rs.next()){
                itemNegozioBean negozio = new itemNegozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getInt("num_stock"),
                        rs.getDouble("prezzo"),
                        rs.getString("tipo")
                );
                lista.add(negozio);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateNumStock(int idItem, int idNegozio, int numStock){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            String query = "UPDATE mayandb.Link_Negozio_Item SET num_stock=" + Integer.toString(numStock) + " WHERE id_item=" + Integer.toString(idItem) + " and id_negozio=" + Integer.toString(idNegozio) + ";";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static int getNumStock(int idItem, int idNegozio){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<itemNegozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Link_Negozio_Item WHERE id_item=" + idItem + " and id_negozio=" + Integer.toString(idNegozio) + ";");
            
            while(rs.next()){
                return rs.getInt("num_stock");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
