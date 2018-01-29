package dbLayer;

import bean.cittaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO dedicato alla classe cittaBean
 *
 * @author Michela
 */
public class cittaDAO {

    /**
     * funzione che ottiene una citta a partire dall'id della città
     *
     * @param idCitta
     * @return un oggetto cittaBean, null se fallisce
     */
    public static cittaBean getCitta(int idCitta) {
        Connection connection = DAOFactoryUsers.getConnection();
        cittaBean citta = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Citta WHERE id_citta=" + idCitta + ";");

            if (rs.next()) {
                citta = new cittaBean(
                        rs.getInt("id_citta"),
                        rs.getString("citta"),
                        rs.getString("regione"),
                        rs.getString("stato")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return citta;
    }

    /**
     * Funzione che inserisce una nuova città
     *
     * @param citta la città da inserire
     * @return true se l'inserimento viene eseguito con successo, false
     * altrimenti
     */
    public static boolean insertCitta(cittaBean citta) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Citta (citta,regione,stato) "
                    + "VALUES (?,?,?);");
            ps.setString(1, citta.getCitta());
            ps.setString(2, citta.getRegione());
            ps.setString(3, citta.getStato());
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
     * Funzione che trova l'id di una città, e se non lo trova si occupa di
     * inserirla
     *
     * @param citta la città da cui trovare l'id
     * @return -1 se sql da errori, l'id altrimenti
     */
    public static int findIdCitta(cittaBean citta) {
        Connection connection = DAOFactoryUsers.getConnection();
        int res = -1;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT *,count(*) as conteggio FROM mayandb.Citta "
                    + "WHERE regione=\'"
                    + citta.getRegione()
                    + "\' AND citta = \'"
                    + citta.getCitta()
                    + "\' AND stato = \'"
                    + citta.getStato()
                    + "\';");
            if (rs.next()) {
                if (rs.getInt("conteggio") == 0) {
                    //la città non è nel db
                    if (insertCitta(citta)) {
                        //richiamo la funzione
                        res = findIdCitta(citta);
                    }
                } else {
                    res = rs.getInt("id_citta");
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * funzione che fornisce una lista di regioni in cui è presente l'oggetto
     * specificato
     *
     * @param idItem id dell'oggetto di cui si vogliono trovare le regioni
     * @return una lista di String se vengono trovate le regioni dell'item, null
     * altrimenti
     */
    public static ArrayList<String> getRegioniByItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> regioni = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(regione) FROM mayandb.Link_Negozio_Item, mayandb.Negozio, mayandb.Location, mayandb.Citta WHERE Link_Negozio_Item.id_negozio=Negozio.id_negozio and Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and Link_Negozio_Item.id_item=" + idItem + ";");

            while (rs.next()) {
                regioni.add(rs.getString("regione"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return regioni;
    }

    /**
     * funzione che ritorna la lista delle città e delle regioni che
     * corrispondono alla stringa passata come parametro
     *
     * @param query stringa da verificare se è contenuta nei produttori degli
     * item
     * @return lista di String, null se la ricerca fallisce
     */
    public static List<String> getDataZone(String query) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> lista = new ArrayList<String>();
        try {
            query = query.toLowerCase();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(citta) FROM mayandb.Citta;");
            while (rs.next()) {
                String match = rs.getString("citta");
                match = match.toLowerCase();
                if (match.contains(query)) {
                    lista.add(rs.getString("citta"));
                }
            }
            rs = stmt.executeQuery("SELECT DISTINCT(regione) FROM mayandb.Citta;");
            while (rs.next()) {
                String match = rs.getString("regione");
                match = match.toLowerCase();
                if (match.contains(query)) {
                    lista.add(rs.getString("regione"));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
