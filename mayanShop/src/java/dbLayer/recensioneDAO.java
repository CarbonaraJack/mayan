package dbLayer;

import bean.recensioneBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        recensioneBean recensione = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione WHERE id_recensione=" + idRec + ";");

            if (rs.next()) {
                recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recensione;
    }

    /**
     * ottiene una lista di recensioni a partire dall'id di un utente
     *
     * @param idUser id dell'utente di cui si vogliono cercare le recensioni
     * @return una lista di oggetti recensioneBean, null se fallisce
     */
    public static ArrayList<recensioneBean> getRecensioneByUser(int idUser) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<recensioneBean> lista = new ArrayList<>();
        try {
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * ottiene una lista di recensioni a partire dall'id di un item
     *
     * @param idItem id dell'item di cui si vogliono cercare le recensioni
     * @return una lista di oggetti recensioneBean, null se fallisce
     */
    public static ArrayList<recensioneBean> getRecensioneByItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<recensioneBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Recensione.*, Link_Rec_Item.*, User.nome, User.cognome FROM mayandb.Recensione, mayandb.Link_Rec_Item, mayandb.User WHERE Link_Rec_Item.id_recensione=Recensione.id_recensione and Recensione.id_user=User.id_user and Recensione.tipo='recensione' id_item=" + idItem + ";");

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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che inserisce una recensione nel database
     *
     * @param recensione la recensione da inserire
     * @return true se va a buon termine, false altrimenti
     */
    public static boolean insertRecensione(recensioneBean recensione) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.insertRecensione (?,?,?);");
            ps.setString(1, recensione.getTesto());
            ps.setDouble(2, recensione.getStelline());
            ps.setInt(3, recensione.getIdAutore());
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
     * Funzione che inserisce una risposta ad una recensione
     *
     * @param recensione la risposta da inserire
     * @return true se va a buon termine, false altrimenti
     */
    public static boolean insertRisposta(recensioneBean recensione) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "CALL mayandb.insertRispostaRecensione (?,?,?);");
            ps.setString(1, recensione.getTesto());
            ps.setInt(2, recensione.getIdRispRec());
            ps.setInt(3, recensione.getIdAutore());
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
     * Funzione che aggiorna il voto medio di un item
     *
     * @param idItem l'item da aggiornare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean updateVotoItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    " CALL mayandb.aggiornaVotoItem(?);");
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
     * Funzione che aggiorna il voto medio di un negozio
     *
     * @param idNegozio l'id del negozio da aggiornare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean updateVotoNegozio(int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    " CALL mayandb.aggiornaVotoNegozio(?);");
            ps.setInt(1, idNegozio);
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
     * Funzione che aggiorna l'id di una recensione dopo aver eseguito
     * l'inserimento
     *
     * @param recensione la recensione di cui trovare l'id
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean getIdRec(recensioneBean recensione) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM mayandb.Recensione WHERE testo=\'"
                    + recensione.getTesto()
                    + "\' AND stelline=\'"
                    + recensione.getStelline()
                    + "\' AND id_user=\'"
                    + recensione.getIdAutore()
                    + "\'AND tipo=\'recensione\';");

            while (rs.next()) {
                res = true;
                recensione.setIdRecensione(rs.getInt("id_recensione"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che aggiorna l'id di una risposta dopo aver eseguito
     * l'inserimento
     *
     * @param recensione la recensione di tipo risposta di cui trovare l'id
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean getIdRis(recensioneBean recensione) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM mayandb.Recensione WHERE testo=\'"
                    + recensione.getTesto()
                    + "\' AND id_risp_rec=\'"
                    + recensione.getIdRispRec()
                    + "\' AND id_user=\'"
                    + recensione.getIdAutore()
                    + "\' AND tipo=\'risposta\';");

            while (rs.next()) {
                res = true;
                recensione.setIdRecensione(rs.getInt("id_recensione"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Funzione che linka una recensione ad un negozio
     *
     * @param recensione la recensione da linkare
     * @param idNegozio il negozio da linkare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean linkRecensioneNegozio(recensioneBean recensione, int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Link_Rec_Negozio (id_negozio, id_recensione) VALUES (?,?);");
            ps.setInt(1, idNegozio);
            ps.setInt(2, recensione.getIdRecensione());
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
     * Funzione che linka una recensione ad un item
     *
     * @param recensione la recensione da linkare
     * @param idItem l'item da linkare
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean linkRecensioneItem(recensioneBean recensione, int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Link_Rec_Item (id_item, id_recensione) VALUES (?,?);");
            ps.setInt(1, idItem);
            ps.setInt(2, recensione.getIdRecensione());
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
     * Funzione che inserisce una recensione nel database e la linka alla
     * tabella corretta
     * @param recensione la recensione da inserire
     * @param mode la modalità con cui voglio inserire l'oggetto 
     * (negozio o item)
     * @param id l'id del negozio o dell'item a cui linkare la recensione
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean proceduraInserimento(recensioneBean recensione, String mode, int id) {
        boolean res = false;
        res = insertRecensione(recensione);
        if (res) {
            res = res && getIdRec(recensione);
            if (mode.equals("item") && res) {
                //eseguo l'inserimento di una recensione in un item
                res = res && linkRecensioneItem(recensione, id);
                //aggiorno il voto medio
                updateVotoItem(id);
            } else if (mode.equals("negozio") & res) {
                //eseguo l'inserimento di una recensione in un negozio
                res = res && linkRecensioneNegozio(recensione, id);
                //aggiorno il voto medio
                updateVotoNegozio(id);
            }
        }
        return res;
    }
    
    public static ArrayList<recensioneBean> getRecensioniItemByUser (int userId){
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<recensioneBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Recensione.*, Link_Rec_Item.id_item FROM mayandb.Link_Rec_Item, mayandb.Recensione, mayandb.Link_Negozio_Item, mayandb.Negozio WHERE Link_Rec_Item.id_recensione=Recensione.id_recensione and Link_Rec_Item.id_item=Link_Negozio_Item.id_item and Link_Negozio_Item.id_negozio=Negozio.id_negozio and Recensione.tipo='recensione' and Negozio.id_proprietario="+Integer.toString(userId)+" and Recensione.id_recensione NOT IN (SELECT id_risp_rec FROM mayandb.Recensione WHERE Recensione.id_risp_rec is not null);");

            while (rs.next()) {
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
                recensione.setIdItem(rs.getInt("id_item"));
                recensione.setNomeItem(dbLayer.itemDAO.getNomeItem(recensione.getIdItem()));
                recensione.setNomeAutore(dbLayer.userDAO.getNome(recensione.getIdAutore()));
                recensione.setCognomeAutore(dbLayer.userDAO.getCognome(recensione.getIdAutore()));
                lista.add(recensione);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<recensioneBean> getRecensioniNegoziByUser (int userId){
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<recensioneBean> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Recensione.*, Negozio.id_negozio, Negozio.nome FROM mayandb.Link_Rec_Negozio, mayandb.Negozio, mayandb.Recensione WHERE Link_Rec_Negozio.id_recensione=Recensione.id_recensione and Link_Rec_Negozio.id_negozio=Negozio.id_negozio and Recensione.tipo='recensione' and Negozio.id_proprietario="+Integer.toString(userId)+" and Recensione.id_recensione NOT IN (SELECT id_risp_rec FROM mayandb.Recensione WHERE Recensione.id_risp_rec is not null);");

            while (rs.next()) {
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
                recensione.setIdNegozio(rs.getInt("id_negozio"));
                recensione.setNomeNegozio(rs.getString("nome"));
                recensione.setNomeAutore(dbLayer.userDAO.getNome(recensione.getIdAutore()));
                recensione.setCognomeAutore(dbLayer.userDAO.getCognome(recensione.getIdAutore()));
                lista.add(recensione);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
