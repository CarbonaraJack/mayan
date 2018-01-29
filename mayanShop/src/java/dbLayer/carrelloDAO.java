package dbLayer;

import bean.carrelloBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO per la gestione del carrello
 *
 * @author Michela
 */
public class carrelloDAO {

    /**
     * funziona che ottiene l'item specificato nel negozio specificato per il
     * carrello
     *
     * @param idItem
     * @param idNeg
     * @return l'oggetto specificato, null se fallisce
     */
    public static carrelloBean getItemCarrello(int idItem, int idNeg) {
        Connection connection = DAOFactoryUsers.getConnection();
        carrelloBean carrello = null;
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT Item.id_item, Item.nome as nomeItem, produttore, Negozio.nome as nomeNegozio, Link_Negozio_Item.prezzo, Link_Negozio_Item.num_stock, Foto.link_foto, Negozio.id_negozio, Negozio.id_proprietario ";
            query = query + "FROM mayandb.Item, mayandb.Link_Negozio_Item, mayandb.Negozio, mayandb.Foto ";
            query = query + "WHERE Item.id_item=Link_Negozio_Item.id_item and Negozio.id_negozio=Link_Negozio_Item.id_negozio and Item.thumbnail=Foto.id_foto and Link_Negozio_Item.id_negozio=" + idNeg + " and Item.id_item=" + idItem + ";";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                carrello = new carrelloBean(
                        rs.getInt("id_item"),
                        rs.getString("nomeItem"),
                        rs.getString("produttore"),
                        rs.getInt("id_negozio"),
                        rs.getString("nomeNegozio"),
                        rs.getString("link_foto"),
                        rs.getDouble("prezzo"),
                        rs.getInt("id_proprietario")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carrello;
    }
}
