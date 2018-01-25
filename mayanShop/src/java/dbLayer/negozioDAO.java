/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.User;
import bean.itemNegozioBean;
import bean.negozioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO dedicato alla classe negozioBean
 *
 * @author Michela
 */
public class negozioDAO {

    /**
     * ottiene un negozio a partire dall'id specificato
     *
     * @param idNeg id del negozio da ottenre
     * @return un oggetto negozioBean, null se fallisce
     */
    public static negozioBean getNegozio(int idNeg) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Negozio WHERE id_negozio=" + idNeg + ";");

            if (rs.next()) {
                String stringIdLocation = rs.getString("id_location");
                int idLoc =-1;
                if(stringIdLocation!=null){
                    idLoc= Integer.parseInt(stringIdLocation);
                }
                negozioBean negozio = new negozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getString("web_link"),
                        rs.getDouble("valutazione_media"),
                        rs.getString("orari"),
                        rs.getString("tipo"),
                        rs.getInt("num_warning"),
                        idLoc
                );
                return negozio;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * ottiene una lista di negozi a partire dalla location specificata
     *
     * @param idLocation id della location
     * @return una lista di oggetti negozioBean, null se fallisce
     */
    public static ArrayList<negozioBean> getNegoziByLocation(int idLocation) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<negozioBean> lista = new ArrayList<negozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Negozio WHERE id_location=" + idLocation + ";");

            while (rs.next()) {
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

    /**
     * ottiene una lista di negozi appartenenti ad un determinato utente
     *
     * @param utente l'utente amministratore
     * @return una lista di oggetti itemNegozioBean, null se fallisce
     */
    public static ArrayList<negozioBean> getNegoziByAdmin(User utente) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<negozioBean> lista = new ArrayList<negozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM mayandb.Negozio WHERE id_proprietario=\'" + utente.getIdUser() + "\';");
            while (rs.next()) {
                String stringIdLocation = rs.getString("id_location");
                int idLoc =-1;
                if(stringIdLocation!=null){
                    idLoc= Integer.parseInt(stringIdLocation);
                }
                negozioBean negozio = new negozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getString("web_link"),
                        rs.getDouble("valutazione_media"),
                        rs.getString("orari"),
                        rs.getString("tipo"),
                        rs.getInt("num_warning"),
                        idLoc
                );
                lista.add(negozio);
            }
            for (negozioBean negozio : lista) {
                //completo i negozi delle informazioni mancanti
                if (negozio.getIdLocation() != -1) {
                    negozio.setLocation(dbLayer.locationDAO.getLocation(negozio.getIdLocation()));
                    negozio.setCitta(dbLayer.cittaDAO.getCitta(negozio.getIdCitta()));
                }
                negozio.setFoto(dbLayer.fotoDAO.getFotoNegozio(negozio.getIdNegozio()));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * ottiene una lista di negozi a partire dall'item specificato
     *
     * @param idItem
     * @return una lista di oggetti itemNegozioBean, null se fallisce
     */
    public static ArrayList<itemNegozioBean> getNegoziByItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<itemNegozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_item, Negozio.id_negozio, num_stock, prezzo, nome, tipo FROM mayandb.Negozio, mayandb.Link_Negozio_Item WHERE Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + idItem + ";");

            while (rs.next()) {
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

    /**
     * aggiorna il numero di item disponibili per l'item specificato nel negozio
     * specificato
     *
     * @param idItem
     * @param idNegozio
     * @param numStock numero di item presenti nel negozio
     * @return true se aggiorna con successo, false se fallisce l'aggiornamento
     */
    public static boolean updateNumStock(int idItem, int idNegozio, int numStock) {
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

    /**
     * ottiene il numero di item disponibili nel negozio specificato per l'item
     * specificato
     *
     * @param idItem
     * @param idNegozio
     * @return un int che indica il numero di item rimanenti nel negozio, -1 se
     * fallisce
     */
    public static int getNumStock(int idItem, int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<itemNegozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Link_Negozio_Item WHERE id_item=" + idItem + " and id_negozio=" + Integer.toString(idNegozio) + ";");

            while (rs.next()) {
                return rs.getInt("num_stock");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Funzione che aggiorna le informazioni di un negozio
     *
     * @param negozio il negozio da aggiornare
     * @return true se funziona, false altrimenti
     */
    public static boolean updateInfo(negozioBean negozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Negozio SET nome=?, tipo=?, descrizione=?,"
                    + " web_link=?, orari=? WHERE id_negozio=?;");
            ps.setString(1, negozio.getNome());
            ps.setString(2, negozio.getTipo());
            ps.setString(3, negozio.getDescrizione());
            ps.setString(4, negozio.getWebLink());
            ps.setString(5, negozio.getOrari());
            ps.setInt(6, negozio.getIdNegozio());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return false;
    }

    /**
     * Funzione che indica se l'utente è il proprietario del negozio che voglio
     * modificare
     *
     * @param utente l'utente attuale
     * @param negozio il negozio da modificare
     * @return true se l'utente è il proprietario, false altrimenti
     */
    public static boolean isMyShop(User utente, negozioBean negozio) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<itemNegozioBean> lista = new ArrayList<itemNegozioBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT count(*) AS conteggio FROM mayandb.Negozio "
                    + "WHERE id_proprietario="
                    + utente.getIdUser()
                    + " AND id_negozio="
                    + negozio.getIdNegozio()
                    + ";");

            while (rs.next()) {
                //se sono il proprietario del negozio il risultato sarà sempre 1
                return rs.getInt("conteggio") == 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean insertNegozio(User utente, negozioBean negozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Negozio "
                    + "(nome,tipo,descrizione,web_link,orari,id_proprietario,num_warning) "
                    + "VALUES (?,?,?,?,?,?,0);");
            ps.setString(1, negozio.getNome());
            ps.setString(2, negozio.getTipo());
            ps.setString(3, negozio.getDescrizione());
            ps.setString(4, negozio.getWebLink());
            ps.setString(5, negozio.getOrari());
            ps.setInt(6, utente.getIdUser());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
