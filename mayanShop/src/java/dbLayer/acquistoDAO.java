package dbLayer;

import bean.acquistoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Michela
 */
public class acquistoDAO {

    /**
     * funzione che aggiunge un acquisto
     *
     * @param quantità
     * @param prezzo
     * @param dataora
     * @param idItem
     * @param idUser
     * @param idNegozio
     * @return true se l'inserimento va a buon fine, false se l'inserimento
     * fallisce
     */
    public static boolean insertAcquisto(int quantità, double prezzo, Date dataora, int idItem, int idUser, int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Acquisto (quantità,prezzo,dataora,id_item,id_user,id_negozio) VALUES (?,?,\'" + dateFormat.format(dataora) + "\',?,?,?);");
            ps.setString(1, String.valueOf(quantità));
            ps.setString(2, String.valueOf(prezzo));
            ps.setString(3, String.valueOf(idItem));
            ps.setString(4, String.valueOf(idUser));
            ps.setString(5, String.valueOf(idNegozio));
            int b = ps.executeUpdate();
            if (b == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static ArrayList<acquistoBean> getListaAcquisti(String userId) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ArrayList<acquistoBean> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("CALL mayandb.getListaAcquisti (" + userId + ");");
            while (rs.next()) {
                int idTransazione = rs.getInt("a.id_transazione");
                int quantità = rs.getInt("a.quantità");
                int prezzo = rs.getInt("a.prezzo");
                String dataora = rs.getString("a.dataora");
                int idItem = rs.getInt("l.id_item");
                String nomeItem = rs.getString("o.nome");
                String linkFoto = rs.getString("linkFoto");
                int idUser = rs.getInt("a.id_user");
                int idNegozio = rs.getInt("n.id_negozio");
                String nomeNegozio = rs.getString("n.nome");
                acquistoBean a = new acquistoBean(idTransazione, quantità, prezzo, dataora, idItem, nomeItem, linkFoto, idUser, idNegozio, nomeNegozio);
                lista.add(a);
            }
            return lista;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
