package dbLayer;

import bean.userBean;
import bean.itemNegozioBean;
import bean.locationBean;
import bean.negozioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        Connection connection = DAOFactory.getConnection();
        negozioBean negozio = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Negozio WHERE id_negozio=" + idNeg + ";");

            if (rs.next()) {
                String stringIdLocation = rs.getString("id_location");
                int idLoc = -1;
                if (stringIdLocation != null) {
                    idLoc = Integer.parseInt(stringIdLocation);
                }
                negozio = new negozioBean(
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
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return negozio;
    }

    /**
     * ottiene una lista di negozi a partire dalla location specificata
     *
     * @param idLocation id della location
     * @return una lista di oggetti negozioBean, null se fallisce
     */
    public static ArrayList<negozioBean> getNegoziByLocation(int idLocation) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<negozioBean> lista = new ArrayList<>();
        try {
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * ottiene una lista di negozi appartenenti ad un determinato utente
     *
     * @param utente l'utente amministratore
     * @return una lista di oggetti itemNegozioBean, null se fallisce
     */
    public static ArrayList<negozioBean> getNegoziByAdmin(userBean utente) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<negozioBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM mayandb.Negozio WHERE id_proprietario=\'" + utente.getIdUser() + "\';");
            while (rs.next()) {
                String stringIdLocation = rs.getString("id_location");
                int idLoc = -1;
                if (stringIdLocation != null) {
                    idLoc = Integer.parseInt(stringIdLocation);
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
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
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            String query = "UPDATE mayandb.Link_Negozio_Item SET num_stock=" + Integer.toString(numStock) + " WHERE id_item=" + Integer.toString(idItem) + " and id_negozio=" + Integer.toString(idNegozio) + ";";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            res = true;
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
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
        Connection connection = DAOFactory.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Link_Negozio_Item WHERE id_item=" + idItem + " and id_negozio=" + Integer.toString(idNegozio) + ";");

            if (rs.next()) {
                res = rs.getInt("num_stock");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che aggiorna le informazioni di un negozio
     *
     * @param negozio il negozio da aggiornare
     * @return true se funziona, false altrimenti
     */
    public static boolean updateInfo(negozioBean negozio) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
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
                res = true;
            }
            connection.close();
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return res;
    }

    /**
     * Funzione che aggiorna la location di un negozio
     *
     * @param negozio il negozio da modificare
     * @return true se va a buon termine, false altrimenti
     */
    public static boolean updateLocation(negozioBean negozio) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Negozio SET id_location=? WHERE id_negozio=?;");
            ps.setInt(1, negozio.getIdLocation());
            ps.setInt(2, negozio.getIdNegozio());
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
     * Funzione che indica se l'utente è il proprietario del negozio che voglio
     * modificare
     *
     * @param utente l'utente attuale
     * @param negozio il negozio da modificare
     * @return true se l'utente è il proprietario, false altrimenti
     */
    public static boolean isMyShop(userBean utente, negozioBean negozio) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
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
                res = rs.getInt("conteggio") == 1;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * funzione che ritorna i negozi in cui è presente l'oggetto specificato
     * (lista da inserire negli oggetti della ricerca per l'ordinamento e il
     * filtro secondo la distanza)
     *
     * @param idItem id dell'oggetto
     * @return lista di itemNegozioBean, null se fallisce
     */
    public static ArrayList<itemNegozioBean> getNegoziByItemRicerca(int idItem) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<itemNegozioBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_item, Negozio.id_negozio, Negozio.id_location, latitudine, longitudine FROM mayandb.Negozio, mayandb.Link_Negozio_Item, mayandb.Location WHERE Negozio.id_location=Location.id_location and Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + idItem + ";");

            while (rs.next()) {
                itemNegozioBean negozio = new itemNegozioBean(
                        rs.getInt("id_negozio"),
                        rs.getInt("id_location"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine")
                );
                //negozio.setLocation(dbLayer.locationDAO.getLocation(negozio.getIdLocation()));
                lista.add(negozio);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che inserisce un negozio impostandone il proprietario
     *
     * @param utente il proprietario del negozio
     * @param negozio il negozio da inserire
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean insertNegozio(userBean utente, negozioBean negozio) {
        Connection connection = DAOFactory.getConnection();
        boolean res = false;
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
                res = true;
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * funzione che ritorna una lista di negozi in cui nel nome è presente il
     * parametro q per la ricerca
     *
     * @param q parametro della ricerca
     * @return lista di negozioBean, null se fallisce la ricerca
     */
    public static ArrayList<negozioBean> getNegoziRicerca(String q) {
        Connection connection = DAOFactory.getConnection();
        ArrayList<negozioBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_negozio, nome, valutazione_media, Negozio.id_location, Location.id_citta, citta, regione, Negozio.tipo, latitudine, longitudine FROM mayandb.Negozio, mayandb.Citta, mayandb.Location WHERE Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and nome LIKE '%" + q + "%' ORDER BY nome;");

            while (rs.next()) {
                negozioBean negozio = new negozioBean(
                        rs.getInt("id_negozio"),
                        rs.getString("nome"),
                        rs.getDouble("valutazione_media"),
                        rs.getString("tipo"),
                        rs.getInt("id_location"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine"),
                        rs.getInt("id_citta"),
                        rs.getString("citta"),
                        rs.getString("regione")
                );
                negozio.setFoto(dbLayer.fotoDAO.getOneFotoNegozio(negozio.getIdNegozio()));
                lista.add(negozio);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * funzione che ritorna la tipologia del negozio specificato
     *
     * @param idNegozio id del negozio di cui si vuole sapere la tipologia
     * @return String contenente la tipologia del negozio, null se fallisce
     */
    public static String getTipologiaNegozio(int idNegozio) {
        Connection connection = DAOFactory.getConnection();
        String res=null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tipo FROM mayandb.Negozio WHERE id_negozio=" + idNegozio + ";");

            if (rs.next()) {
                res= rs.getString("tipo");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * funzione che ritorna la lista dei nomi dei negozi che corrispondono alla
     * stringa passata come parametro
     *
     * @param query stringa da verificare se è contenuta nei nomi dei negozi
     * @return lista di String, null se la ricerca fallisce
     */
    public static List<String> getDataNegozi(String query) {
        Connection connection = DAOFactory.getConnection();
            ArrayList<String> lista = new ArrayList<>();
        try {
            query = query.toLowerCase();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(nome) FROM mayandb.Negozio;");

            while (rs.next()) {
                String match = rs.getString("nome");
                match = match.toLowerCase();
                if (match.contains(query)) {
                    lista.add(rs.getString("nome"));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
