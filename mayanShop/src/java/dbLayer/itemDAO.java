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
                item.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(item.getIdItem()));
                lista.add(item);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<itemBean> getItemsRicercaProduttori(String q){
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<itemBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto and Item.produttore LIKE '%" + q + "%';");

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
                item.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(item.getIdItem()));
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
     * Funzione che ritorna il numero di items presenti nel db
     * @return -1 se sql da un problema, il numero di items altrimenti
     */
    public static int getNumItems(){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT count(*) as conteggio FROM mayandb.Item;");

            while(rs.next()) {
                return rs.getInt("conteggio");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }
    
    /**
     * Funzione che ritorna una pagina dell'editor di items
     * @param pagina il numero di pagina che devo visualizzare
     * @return l'ArrayList di items da visualizzare
     */
    public static ArrayList<itemBean> getLightItemsOffset(int pagina){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<itemBean> lista = new ArrayList<itemBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_item,nome,produttore,categoria FROM mayandb.Item"
                            +" LIMIT 15 OFFSET "+((pagina-1)*15)+";");

            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria")
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
     * Funzione che ritorna i risultati della ricerca di un item nell'editor
     * items
     * @param ricerca la stringa da cercare
     * @return l'ArrayList con gli items da stampare
     */
    public static ArrayList<itemBean> getLightItemsRicerca(String ricerca){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<itemBean> lista = new ArrayList<itemBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_item,nome,produttore,categoria FROM mayandb.Item"
                            +" WHERE nome LIKE \'%"+ricerca+"%\';");

            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria")
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
    
    public static ArrayList<itemBean> getItemsForNegozi(int idNegozio){
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<itemBean> lista = new ArrayList<itemBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Item.id_item, nome, prezzo_minimo, voto_medio, thumbnail, link_foto FROM mayandb.Item, mayandb.Link_Negozio_Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto and Item.id_item=Link_Negozio_Item.id_item and Link_Negozio_Item.id_negozio=" + idNegozio + ";");

            while(rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
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
}
