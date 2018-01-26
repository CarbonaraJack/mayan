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
 * DAO dedicato alla tabella Link_Foto_Negozio
 *
 * @author marcello
 */
public class fotoNegozioDAO {

    /**
     * Funzione che assegna una foto al negozio
     *
     * @param foto la foto da assegnare
     * @param negozio il negozio
     * @return true se va a buon fine, false altrimenti
     */
    public static boolean linkFotoNegozio(Foto foto, negozioBean negozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Link_Negozio_Foto VALUES (?,?);");
            ps.setInt(1, negozio.getIdNegozio());
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
     * Funzione che indica se una foto aziendale appartiene ad un utente
     *
     * @param utente l'utente
     * @param foto la foto
     * @return true se l'utente è proprietario, false altrimenti
     */
    public static boolean isOwnerFoto(User utente, Foto foto) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "CALL mayandb.proprietarioFotoNegozio("
                    + utente.getIdUser()
                    + ","
                    + foto.getIdFoto() + ");");
            if (rs.next()) {
                if (rs.getInt("conteggio") == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * Funzione che trova i negozi collegati ad una foto
     * @param foto la foto
     * @return i negozi associati alla foto oppure NULL
     */
    public static ArrayList<negozioBean> getNegozi(Foto foto) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            ArrayList<negozioBean> listaNegozi = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "CALL mayandb.negozioFromFoto("
                    + foto.getIdFoto() + ");");
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
                listaNegozi.add(negozio);
            }
            return listaNegozi;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Funzione che scollega una foto dal negozio
     * @param foto
     * @param negozio
     * @return true se lo scollegamento va a buon termine, false altrimenti
     */
    public static boolean unlinkFotoNegozio(Foto foto, negozioBean negozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(
                    "DELETE FROM mayandb.Link_Negozio_Foto WHERE id_negozio="
                    + negozio.getIdNegozio()
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
}
