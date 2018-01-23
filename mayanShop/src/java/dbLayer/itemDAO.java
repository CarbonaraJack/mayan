/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.itemBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Michela
 */
public class itemDAO {
    
    public static itemBean getItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE id_item=" + idItem + ";");
            
            if(rs.next()){
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("descr_item"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio"),
                        rs.getInt("tot_acquistato"),
                        rs.getInt("tot_visualizzazioni")
                );
                return item;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<itemBean> getItems(){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<itemBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item;");
            
            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("descr_item"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio"),
                        rs.getInt("tot_acquistato"),
                        rs.getInt("tot_visualizzazioni")
                );
                lista.add(item);
                return lista;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static ArrayList<itemBean> getItemsRicerca(){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<itemBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto;");
            
            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getString("link_foto"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                lista.add(item);
                return lista;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<itemBean> getItemsIndex(String orderBy, String limit){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<itemBean> lista = new ArrayList<itemBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto ORDER BY " + orderBy + " DESC LIMIT " + limit + ";");
            
            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getString("link_foto"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                lista.add(item);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static void updateVisualizzazioni(int idItem, int numVisualizzazioni){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            String query = "UPDATE mayandb.Item SET tot_visualizzazioni=" + Integer.toString(numVisualizzazioni) + " WHERE id_item=" + Integer.toString(idItem) + ";";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
