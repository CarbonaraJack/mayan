package dbLayer;

import bean.User;
import bean.itemBean;
import bean.itemNegozioBean;
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
    private static ArrayList<itemNegozioBean> getNegoziFromAdmin(User utente) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<>();
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
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
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
    private static boolean getStocksNegozi(ArrayList<itemNegozioBean> lista, User utente, itemBean oggetto) {
        Connection connection = DAOFactoryUsers.getConnection();
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
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
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
    public static ArrayList<itemNegozioBean> getNegoziStocks(User utente, itemBean item) {
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
}
