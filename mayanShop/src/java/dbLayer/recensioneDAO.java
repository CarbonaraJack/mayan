package dbLayer;

import bean.recensioneBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO dedicato alla classe recensioneBean
 *
 * @author Michela
 */
public class recensioneDAO {

    /**
     * ottiene una recensione a partire dall'id della recensione
     *
     * @param idRec ide della recensione cercata
     * @return un oggetto recensioneBean, null se fallisce
     */
    public static recensioneBean getRecensione(int idRec) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione WHERE id_recensione=" + idRec + ";");

            if (rs.next()) {
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
                return recensione;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * ottiene una lista di recensioni a partire dall'id di un utente
     *
     * @param idUser id dell'utente di cui si vogliono cercare le recensioni
     * @return una lista di oggetti recensioneBean, null se fallisce
     */
    public static ArrayList<recensioneBean> getRecensioneByUser(int idUser) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<recensioneBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione WHERE id_user=" + idUser + ";");

            while (rs.next()) {
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
                lista.add(recensione);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * ottiene una lista di recensioni a partire dall'id di un item
     *
     * @param idItem id dell'item di cui si vogliono cercare le recensioni
     * @return una lista di oggetti recensioneBean, null se fallisce
     */
    public static ArrayList<recensioneBean> getRecenzioneByItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<recensioneBean> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Recensione.*, Link_Rec_Item.*, User.nome, User.cognome FROM mayandb.Recensione, mayandb.Link_Rec_Item, mayandb.User WHERE Link_Rec_Item.id_recensione=Recensione.id_recensione and Recensione.id_user=User.id_user and id_item=" + idItem + ";");

            while (rs.next()) {
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user"),
                        rs.getString("nome"),
                        rs.getString("cognome")
                );
                lista.add(recensione);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
