package dbLayer;

import bean.userBean;
import bean.itemBean;
import bean.itemNegozioBean;
import bean.locationBean;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO dedicato alla classe itemNegozioBean
 *
 * @author marcello
 */
public class itemNegozioDAO {

    /**
     * Funzione che ottiene la lista di negozi da un admin in formato utile per
     * il link con gli utenti
     *
     * @param utente l'admin dei negozi
     * @return una array list con i negozi
     */
    private static ArrayList<itemNegozioBean> getNegoziFromAdmin(userBean utente) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<itemNegozioBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_negozio, nome FROM mayandb.Negozio "
                    + "WHERE id_proprietario="
                    + utente.getIdUser()
                    + ";");

            while (rs.next()) {
                itemNegozioBean itemNeg = new itemNegozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome")
                );
                lista.add(itemNeg);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    /**
     * Funzione che popola i dati di una lista di itemNegozioBean con i valori
     * corretti degli stocks
     *
     * @param lista la lista da popolare
     * @param utente il proprietario dei negozi
     * @param oggetto l'item da cercare
     * @return true se la funzione va a buon fine, false altrimenti
     */
    private static boolean getStocksNegozi(ArrayList<itemNegozioBean> lista, userBean utente, itemBean oggetto) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "CALL mayandb.getItemsNegoziProprietario ("
                    + utente.getIdUser()
                    + ","
                    + oggetto.getIdItem()
                    + ");");

            while (rs.next()) {
                for (itemNegozioBean elem : lista) {
                    if (rs.getInt("id_negozio") == elem.getIdNegozio()) {
                        elem.inserisciStock(
                                rs.getInt("id_item"),
                                rs.getInt("num_stock"),
                                rs.getDouble("prezzo"));
                    }
                }
            }
            res = true;
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return res;
    }

    /**
     * Funzione che dato un utente ed un item ottiene dal database una lista di
     * negozi associati a quell'utente e gli stock che ha presente in ogni
     * negozio per quell'item. Se non ha stock in negozio per un determinato
     * item ad esso si associano id -1 e quantità e prezzo 0
     *
     * @param utente l'utente negoziante
     * @param item l'item da cercare
     * @return un ArrayList con i negozi e gli items, null se trova un errore
     */
    public static ArrayList<itemNegozioBean> getNegoziStocks(userBean utente, itemBean item) {
        ArrayList<itemNegozioBean> lista = getNegoziFromAdmin(utente);
        if (item != null) {
            if (getStocksNegozi(lista, utente, item)) {
                return lista;
            } else {
                return null;
            }
        } else {
            return lista;
        }
    }

    /**
     * Funzione che aggiorna i valori dello stock di un negozio per un item. La
     * funzione si assicura anche di differenziare tra inserimento e update di
     * record nella tabella Link_Item_Negozio
     *
     * @param idItem l'item da aggiungere/modificare in stock
     * @param idNegozio il negozio da modificare
     * @param popolato flag che indica se va aggiornato un record esistente o se
     * ne va inserito uno nuovo
     * @param prezzo il prezzo assegnato all'item da mettere in stock
     * @param stock il numero di items da mettere in stock
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean aggiornaStocks(
            int idItem, int idNegozio,
            boolean popolato, double prezzo, int stock) {
        boolean flag = true;
        if (popolato) {
            //aggiorna lo stock
            flag = flag && updateItemNegozio(idNegozio, idItem, prezzo, stock);
        } else if (!(prezzo == 0.0 && stock == 0)) {
            //inserisco lo stock solo se devo
            flag = flag && insertItemNegozio(idNegozio, idItem, prezzo, stock);
        }
        // nel caso in cui il record non sia popolato e il prezzo e stock
        // siano uguali a zero non mi interessa cambiare le cose
        return flag;
    }

    /**
     * Funzione che aggiorna un record della tabella Link_Negozio_Item
     *
     * @param idNegozio l'id del negozio
     * @param idItem l'id dell'item
     * @param prezzo il prezzo dell'item
     * @param stock il numero di stock dell'item in quel negozio
     * @return true se l'operazione va a buon termine, false altrimenti
     */
    private static boolean updateItemNegozio(int idNegozio, int idItem, double prezzo, int stock) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Link_Negozio_Item SET "
                    + "num_stock=?, "
                    + "prezzo=? "
                    + "WHERE id_negozio=? AND "
                    + " id_item=?;");
            ps.setInt(1, stock);
            ps.setDouble(2, prezzo);
            ps.setInt(3, idNegozio);
            ps.setInt(4, idItem);
            int i = ps.executeUpdate();
            if (i == 1) {
                res = true;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che inserisce un nuovo record nella tabella Link_Negozio_Item
     *
     * @param idNegozio l'id del negozio
     * @param idItem l'id dell'item
     * @param prezzo il prezzo dell'item
     * @param stock il numero di stock dell'item in negozio
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    private static boolean insertItemNegozio(int idNegozio, int idItem, double prezzo, int stock) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Link_Negozio_Item "
                    + "(id_item,id_negozio,num_stock, prezzo)"
                    + " VALUES (?, ?, ?, ?);");
            ps.setInt(1, idItem);
            ps.setInt(2, idNegozio);
            ps.setInt(3, stock);
            ps.setDouble(4, prezzo);
            int i = ps.executeUpdate();
            if (i == 1) {
                res = true;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * ottiene una lista di negozi a partire dall'item specificato
     *
     * @param idItem
     * @return una lista di oggetti itemNegozioBean, null se fallisce
     */
    public static ArrayList<itemNegozioBean> getNegoziByItem(int idItem) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<itemNegozioBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_item, Negozio.id_negozio, num_stock, prezzo, nome, tipo, id_location FROM mayandb.Negozio, mayandb.Link_Negozio_Item WHERE num_stock>0 and Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + idItem + " ORDER BY prezzo DESC;");

            while (rs.next()) {
                itemNegozioBean negozio = new itemNegozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getInt("num_stock"),
                        rs.getDouble("prezzo"),
                        rs.getString("tipo"),
                        rs.getInt("id_location")
                );
                if(negozio.getIdLocation()>0){
                    locationBean loc= dbLayer.locationDAO.getLocation(negozio.getIdLocation());
                    negozio.setLocation(loc);
                }
                lista.add(negozio);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
