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
 * DAO dedicato alla classe itemBean
 * @author Michela
 */
public class itemDAO {

    /**
     * funzione che ottiene un item a partire dall'id specificato
     * @param idItem id dell'item che si vuole ottenere
     * @return un oggetto itemBean, null se fallisce
     */
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

    /**
     * ottiene tutti gli item
     * @return lista di oggetti itemBean, null se fallisce
     */
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

    /**
     * ottiene la lista di tutti gli item compresi di foto d'anteprima
     * @return una lista di oggetti itemBean, null se fallisce
     */
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
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<itemBean> getItemsRicerca(String q){
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<itemBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto and Item.nome LIKE '%" + q + "%';");

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
                //item.setNegozi(dbLayer.negozioDAO.getNegoziByItem(item.getIdItem()));
                item.setRegioni(dbLayer.cittaDAO.getRegioniByItem(item.getIdItem()));
                lista.add(item);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * ottiene i primi limit item ordinati in modo decrescente secondo il campo orderBy
     * @param orderBy campo su cui effettuare l'ordinamento
     * @param limit limite di item da ottenere
     * @return lista di oggetti itemBean, null se fallisce
     */
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

    /**
     * aggiorna il numero di visualizzazioni dell'item specificato
     * @param idItem id dell'item da aggiornare
     * @param numVisualizzazioni numero di visualizzazioni dell'item da inserire
     */
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

    /**
     * aggiorna il numero di acquisti dell'item specificato
     * @param idItem id dell'item da aggiornare
     * @param numAcquistati numero di acquisti dell'item da inserire
     */
    public static boolean updateAcquistati(int idItem, int numAcquistati){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            String query = "UPDATE mayandb.Item SET tot_acquistato=" + Integer.toString(numAcquistati) + " WHERE id_item=" + Integer.toString(idItem) + ";";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * ottiene il numero di volte che l'oggetto specificato è stato acquistato
     * @param idItem id dell'item di cui si vuole ottenere il tot_acquistato
     * @return un intero che corrisponde a tot_acquistato, -1 se fallisce
     */
    public static int getTotAcquistato(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE id_item=" + idItem + ";");
            if(rs.next()){
                return rs.getInt("tot_acquistato");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
