/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.cittaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Citta WHERE id_citta=" + idCitta + ";");

            if (rs.next()) {
                cittaBean citta = new cittaBean(
                        rs.getInt("id_citta"),
                        rs.getString("citta"),
                        rs.getString("regione"),
                        rs.getString("stato")
                );
                return citta;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Citta (citta,regione,stato) "
                    + "VALUES (?,?,?);");
            ps.setString(1, citta.getCitta());
            ps.setString(2, citta.getRegione());
            ps.setString(3, citta.getStato());
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
     * Funzione che trova l'id di una città, e se non lo trova si occupa di
     * inserirla
     *
     * @param citta la città da cui trovare l'id
     * @return -1 se sql da errori, l'id altrimenti
     */
    public static int findIdCitta(cittaBean citta) {
        Connection connection = DAOFactoryUsers.getConnection();

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
                        return findIdCitta(citta);
                    }else{
                        return -1;

                    }
                } else {
                    return rs.getInt("id_citta");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * funzione che fornisce una lista di regioni in cui è presente l'oggetto specificato
     * @param idItem id dell'oggetto di cui si vogliono trovare le regioni
     * @return una lista di String se vengono trovate le regioni dell'item, null altrimenti
     */
    public static ArrayList<String> getRegioniByItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<String> regioni = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT(regione) FROM mayandb.Link_Negozio_Item, mayandb.Negozio, mayandb.Location, mayandb.Citta WHERE Link_Negozio_Item.id_negozio=Negozio.id_negozio and Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and Link_Negozio_Item.id_item=" + idItem + ";");

            while(rs.next()){
                regioni.add(rs.getString("regione"));
            }
            return regioni;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
