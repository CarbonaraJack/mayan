package dbLayer;

import bean.Foto;
import bean.itemBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO dedicato alla classe itemBean
 *
 * @author Michela
 */
public class itemDAO {

    /**
     * funzione che ottiene un item a partire dall'id specificato
     *
     * @param idItem id dell'item che si vuole ottenere
     * @return un oggetto itemBean, null se fallisce
     */
    public static itemBean getItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        itemBean item = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE id_item=" + idItem + ";");

            if (rs.next()) {
                item = new itemBean(
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
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

    /**
     * funzione che fornisce gli oggetti in cui nel nome è presente il parametro
     * q per la ricerca
     *
     * @param q parametro di ricerca
     * @return lista di itemBean per la ricerca, null se fallisce la ricerca
     */
    public static ArrayList<itemBean> getItemsRicerca(String q) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE Item.nome LIKE '%" + q + "%' ORDER BY prezzo_minimo DESC;");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                if (item.getIdThumbnail() > 0) {
                    Foto foto = dbLayer.fotoDAO.getFoto(item.getIdThumbnail());
                    if (foto != null) {
                        item.setImmagine(foto.getLinkFoto());
                    }
                }
                item.setRegioni(dbLayer.cittaDAO.getRegioniByItem(item.getIdItem()));
                item.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(item.getIdItem()));
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * funzione che fornisce gli oggetti in cui nel produttore è presente il
     * parametro q per la ricerca
     *
     * @param q parametro di ricerca
     * @return lista di itemBean per la ricerca, null se fallisce la ricerca
     */
    public static ArrayList<itemBean> getItemsRicercaProduttori(String q) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE Item.produttore LIKE '%" + q + "%' ORDER BY prezzo_minimo DESC;");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                if (item.getIdThumbnail() > 0) {
                    Foto foto = dbLayer.fotoDAO.getFoto(item.getIdThumbnail());
                    if (foto != null) {
                        item.setImmagine(foto.getLinkFoto());
                    }
                }
                item.setRegioni(dbLayer.cittaDAO.getRegioniByItem(item.getIdItem()));
                item.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(item.getIdItem()));
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * funzione che fornisce gli oggetti in cui nela città o nella regione è
     * presente il parametro q per la ricerca
     *
     * @param q parametro di ricerca
     * @return lista di itemBean per la ricerca, null se fallisce la ricerca
     */
    public static ArrayList<itemBean> getItemsRicercaZone(String q) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct(Item.id_item), Item.nome, produttore, categoria, thumbnail, prezzo_minimo, voto_medio FROM mayandb.Item, mayandb.Location, mayandb.Citta, mayandb.Link_Negozio_Item, mayandb.Negozio WHERE Item.id_item=Link_Negozio_Item.id_item and Link_Negozio_Item.id_negozio=Negozio.id_negozio and Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and (Citta.citta LIKE '%" + q + "%' or Citta.regione LIKE '%" + q + "%' ORDER BY prezzo_minimo DESC);");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                if (item.getIdThumbnail() > 0) {
                    Foto foto = dbLayer.fotoDAO.getFoto(item.getIdThumbnail());
                    if (foto != null) {
                        item.setImmagine(foto.getLinkFoto());
                    }
                }
                //item.setRegioni(dbLayer.cittaDAO.getRegioniByItem(item.getIdItem()));
                //item.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(item.getIdItem()));
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * ottiene i primi limit item ordinati in modo decrescente secondo il campo
     * orderBy
     *
     * @param orderBy campo su cui effettuare l'ordinamento
     * @param limit limite di item da ottenere
     * @return lista di oggetti itemBean, null se fallisce
     */
    public static ArrayList<itemBean> getItemsIndex(String orderBy, String limit) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item, mayandb.Foto WHERE Item.thumbnail=Foto.id_foto ORDER BY " + orderBy + " DESC LIMIT " + limit + ";");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                if (item.getIdThumbnail() > 0) {
                    Foto foto = dbLayer.fotoDAO.getFoto(item.getIdThumbnail());
                    item.setImmagine(foto.getLinkFoto());
                }
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che ritorna il numero di items presenti nel db
     *
     * @return -1 se sql da un problema, il numero di items altrimenti
     */
    public static int getNumItems() {
        Connection connection = DAOFactoryUsers.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT count(*) as conteggio FROM mayandb.Item;");

            while (rs.next()) {
                res = rs.getInt("conteggio");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return res;
    }

    /**
     * Funzione che ritorna l'id di un oggetto matchando nome, produttore,
     * categoria e descrizione
     *
     * @param oggetto l'item da cercare
     * @return l'id dell'item se lo trovo, -1 altrimenti
     */
    public static int getIdItem(itemBean oggetto) {
        Connection connection = DAOFactoryUsers.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_item FROM mayandb.Item WHERE "
                    + "nome=\'" + oggetto.getNome()
                    + "\' AND produttore = \'" + oggetto.getProduttore()
                    + "\' AND descr_item =\'" + oggetto.getDescrizione()
                    + "\' AND categoria = \'" + oggetto.getCategoria() + "\';");

            while (rs.next()) {
                res = rs.getInt("id_item");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che ritorna una pagina dell'editor di items
     *
     * @param pagina il numero di pagina che devo visualizzare
     * @return l'ArrayList di items da visualizzare
     */
    public static ArrayList<itemBean> getLightItemsOffset(int pagina) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_item,nome,produttore,categoria FROM mayandb.Item"
                    + " LIMIT 15 OFFSET " + ((pagina - 1) * 15) + ";");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria")
                );
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che ritorna i risultati della ricerca di un item nell'editor
     * items
     *
     * @param ricerca la stringa da cercare
     * @return l'ArrayList con gli items da stampare
     */
    public static ArrayList<itemBean> getLightItemsRicerca(String ricerca) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_item,nome,produttore,categoria FROM mayandb.Item"
                    + " WHERE nome LIKE \'%" + ricerca + "%\';");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getString("produttore"),
                        rs.getString("categoria")
                );
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che ritorna tutte le categorie esistenti fino ad adesso
     *
     * @return un ArrayList con le categorie
     */
    public static ArrayList<String> getCategorie() {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> categorie = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT DISTINCT categoria FROM mayandb.Item;");

            while (rs.next()) {
                categorie.add(rs.getString("categoria"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categorie;
    }

    /**
     * Funzione che torna una lista di produttori già immessi nel db
     *
     * @return l'array list con i produttori
     */
    public static ArrayList<String> getProduttori() {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> produttori = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT DISTINCT produttore FROM mayandb.Item;");

            while (rs.next()) {
                produttori.add(rs.getString("produttore"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produttori;
    }

    /**
     * ottiene il numero di volte che l'oggetto specificato è stato acquistato
     *
     * @param idItem id dell'item di cui si vuole ottenere il tot_acquistato
     * @return un intero che corrisponde a tot_acquistato, -1 se fallisce
     */
    public static int getTotAcquistato(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Item WHERE id_item=" + idItem + ";");
            if (rs.next()) {
                res = rs.getInt("tot_acquistato");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static ArrayList<itemBean> getItemsForNegozi(int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<itemBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Item.id_item, nome, prezzo_minimo, voto_medio, thumbnail FROM mayandb.Item, mayandb.Link_Negozio_Item WHERE Item.id_item=Link_Negozio_Item.id_item and Link_Negozio_Item.id_negozio=" + idNegozio + ";");

            while (rs.next()) {
                itemBean item = new itemBean(
                        rs.getInt("id_item"),
                        rs.getString("nome"),
                        rs.getInt("thumbnail"),
                        rs.getDouble("prezzo_minimo"),
                        rs.getDouble("voto_medio")
                );
                lista.add(item);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che indica se la thumbnail di un item è nulla
     *
     * @param idItem l'id dell'item da cercare
     * @return true se la thumbnail è nulla, false altrimenti
     */
    public static boolean isThumbNull(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT count(*) as conteggio FROM mayandb.Item "
                    + "WHERE id_item=" + idItem + " AND thumbnail is null;");

            while (rs.next()) {
                if (rs.getInt("conteggio") == 1) {
                    res = true;
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * aggiorna il numero di visualizzazioni dell'item specificato
     *
     * @param idItem id dell'item da aggiornare
     * @param numVisualizzazioni numero di visualizzazioni dell'item da inserire
     */
    public static void updateVisualizzazioni(int idItem, int numVisualizzazioni) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            String query = "UPDATE mayandb.Item SET tot_visualizzazioni=" + Integer.toString(numVisualizzazioni) + " WHERE id_item=" + Integer.toString(idItem) + ";";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * aggiorna il numero di acquisti dell'item specificato
     *
     * @param idItem id dell'item da aggiornare
     * @param numAcquistati numero di acquisti dell'item da inserire
     */
    public static boolean updateAcquistati(int idItem, int numAcquistati) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            String query = "UPDATE mayandb.Item SET tot_acquistato=" + Integer.toString(numAcquistati) + " WHERE id_item=" + Integer.toString(idItem) + ";";
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
     * Funzione che inserisce un oggetto nel database
     *
     * @param oggetto l'oggetto da inserire
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean insertItem(itemBean oggetto) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Item "
                    + "(nome,produttore,descr_item, categoria, "
                    + "tot_acquistato,tot_visualizzazioni)"
                    + " VALUES (?, ?, ?, ?, 0,0);");
            ps.setString(1, oggetto.getNome());
            ps.setString(2, oggetto.getProduttore());
            ps.setString(3, oggetto.getDescrizione());
            ps.setString(4, oggetto.getCategoria());
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
     * Funzione che aggiorna i valori di un item
     *
     * @param oggetto l'item da aggiornare
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean updateItem(itemBean oggetto) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Item SET nome=?,produttore=?, "
                    + "descr_item=?, categoria=? WHERE id_item=?;");
            ps.setString(1, oggetto.getNome());
            ps.setString(2, oggetto.getProduttore());
            ps.setString(3, oggetto.getDescrizione());
            ps.setString(4, oggetto.getCategoria());
            ps.setInt(5, oggetto.getIdItem());
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
     * Funzione che aggiorna la thumbnail di un item
     *
     * @param idItem l'id dell'item da aggiornare
     * @param idFoto l'id della thumbnail da settare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean updateThumb(int idItem, int idFoto) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE mayandb.Item SET thumbnail=? WHERE id_item=?;");
            ps.setInt(1, idFoto);
            ps.setInt(2, idItem);
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
     * Funzione che reimposta la thumbnail di un item con la prima immagine che
     * è associata a quell'item. Se non ci sono immagini associate imposta la
     * thumbnail a null. Utile per quando si cancella una foto per non lasciare
     * references broken alla thumbail dell'item.
     *
     * @param idItem l'id dell'item da fixare
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean fixThumb(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.fixThumb (?);");
            ps.setInt(1, idItem);
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
     * Funzione che imposta il nuovo prezzo minimo all'item prendendo il min
     * dalla tabella Link_Negozio_Item
     *
     * @param idItem l'item da aggiornare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean fixPrezzoMinimo(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.aggiornaPrezzoMinimo (?);");
            ps.setInt(1, idItem);
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
     * funzione che ritorna la lista dei nomi degli oggetti che corrispondono
     * alla stringa passata come parametro
     *
     * @param query stringa da verificare se è contenuta nei nomi degli item
     * @return lista di String, null se la ricerca fallisce
     */
    public static List<String> getData(String query) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> lista = new ArrayList<>();
        try {
            query = query.toLowerCase();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome FROM mayandb.Item;");

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

    /**
     * funzione che ritorna la lista dei produttori degli oggetti che
     * corrispondono alla stringa passata come parametro
     *
     * @param query stringa da verificare se è contenuta nei produttori degli
     * item
     * @return lista di String, null se la ricerca fallisce
     */
    public static List<String> getDataProduttori(String query) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> lista = new ArrayList<>();
        try {
            query = query.toLowerCase();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(produttore) FROM mayandb.Item;");

            while (rs.next()) {
                String match = rs.getString("produttore");
                match = match.toLowerCase();
                if (match.contains(query)) {
                    lista.add(rs.getString("produttore"));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
