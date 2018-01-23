package dbLayer;

import bean.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Dao dedicato alla classe foto
 *
 * @author Marcello
 */
public class fotoDAO {

    /**
     * Funzione che ottiene un oggetto Foto partendo dall'Id
     *
     * @param idFoto l'id della foto
     * @return un oggetto Foto associato all'id
     */
    public static Foto getFoto(int idFoto) {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto WHERE id_foto=\'" + idFoto + "\'");
            if (rs.next()) {
                Foto foto = new Foto(
                        rs.getInt("id_foto"),
                        rs.getString("link_foto")
                );
                return foto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Funzione che ritorna il numero di record nella tabella foto
     *
     * @return il numero di foto presenti nel database
     */
    public static int numFoto() {
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) as conteggio FROM mayandb.Foto");
            if (rs.next()) {
                return rs.getInt("conteggio");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1000000;//nel db utilizzo 6 cifre, quindi nell'elemento 1000000 ci saranno gli errori
    }

    /**
     * Funzione che inserisce una foto nel db
     *
     * @param fileType il tipo della foto, necessario per calcolare il nuovo
     * nome
     * @return il nome della foto in modo da poterla salvare su disco
     */
    public static String insertFoto(String fileType) {
        //la foto sarà il numero di record +1
        int numeroFoto= numFoto()+1;
        String numeroFormattato = String.format("%06d", numeroFoto);
        String nomeFoto=numeroFormattato+fileType;
        Connection connection = DAOFactoryUsers.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO mayandb.Foto (link_foto) "
                    + "VALUES (?);");
            ps.setString(1, nomeFoto);
            int i = ps.executeUpdate();
            if (i == 1) {
                return nomeFoto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nomeFoto;
    }
}
