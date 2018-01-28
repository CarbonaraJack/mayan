package dbLayer;

import bean.Foto;
import bean.User;
import bean.itemBean;
import bean.negozioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO dedicato alla tabella Link_Foto_Item
 *
 * @author marcello
 */
public class fotoItemDAO {

    /**
     * Funzione che assegna una foto ad un item
     *
     * @param foto la foto da assegnare
     * @param idItem l'id dell'item
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean linkFotoItem(Foto foto, int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Link_Item_Foto VALUES (?,?);");
            ps.setInt(1, idItem);
            ps.setInt(2, foto.getIdFoto());
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
     * Funzione che linka una foto ad un item controllando che la thumbnail non
     * rimanga spopolata
     *
     * @param foto la foto da aggiungere
     * @param idItem l'item da linkare
     * @return true se la foto viene caricata, a prescindere dall'edit thumb
     */
    public static boolean linkFotoItemCompleto(Foto foto, int idItem) {
        boolean res = linkFotoItem(foto, idItem);
        //Se la foto impostata come thumbnail è nulla allora di default la nuova
        //foto sarà la nuova thumbnail
        if (dbLayer.itemDAO.isThumbNull(idItem)) {
            dbLayer.itemDAO.updateThumb(idItem, foto.getIdFoto());
        }
        return res;
    }

    /**
     * Funzione che scollega una foto dall'item
     *
     * @param foto
     * @param idItem
     * @return true se lo scollegamento va a buon termine, false altrimenti
     */
    public static boolean unlinkFotoItem(Foto foto, int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(
                    "DELETE FROM mayandb.Link_Item_Foto WHERE id_item="
                    + idItem
                    + " AND id_foto="
                    + foto.getIdFoto()
                    + ";");
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Funzione che cancella dal database una foto associata ad un item
     *
     * @param foto la foto da cancellare
     * @param idItem l'id dell'item da cancellare
     * @return true se l'operazione va a buon fine, false altrimenti
     */
    public static boolean deleteFotoItem(Foto foto, int idItem) {
        // mi prendo l'oggetto
        itemBean oggetto = dbLayer.itemDAO.getItem(idItem);
        //inizializzo il valore di ritorno
        boolean res = true;
        //scollego la foto e l'item
        res = res && unlinkFotoItem(foto, idItem);
        if (res) {
            //proseguo solo se ho scollegato con successo
            //se la foto che sto cancellando è la thumbnail allora fixo la thumb
            //nell'item
            if (oggetto.getIdThumbnail() == foto.getIdFoto()) {
                res = res && dbLayer.itemDAO.fixThumb(idItem);
            }
            //per evitare dead links cancello la foto solo se tutte le
            //operazioni precedenti sono andate a buon fine
            if (res) {
                res = res && dbLayer.fotoDAO.deleteFoto(foto);
            }
        }
        //Se una quasiasi delle operazioni non va a buon fine res diventa false,
        //quindi lo uso come flag di ritorno
        return res;
    }
}
