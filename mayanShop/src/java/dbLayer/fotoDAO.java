package dbLayer;

import bean.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dao dedicato alla classe foto
 *
 * @author Marcello e Michela
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
        int numeroFoto = numFoto() + 1;
        String numeroFormattato = String.format("%06d", numeroFoto);
        String nomeFoto = numeroFormattato + fileType;
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

    /**
     * funzione che ottiene una lista di foto a partire da un negozio
     * @param idNegozio id del negozio di cui si vogliono ottenere foto
     * @return lista di oggetti Foto per il negozio specificato, null se fallisce
     */
    public static ArrayList<Foto> getFotoNegozio(int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<Foto> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto, mayandb.Link_Negozio_Foto WHERE Foto.id_foto=Link_Negozio_Foto.id_foto and id_negozio=" + idNegozio + ";");

            while (rs.next()) {
                lista.add(new Foto(rs.getInt("id_foto"),rs.getString("link_foto")));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * funzione che ottiene una lista di foto a partire da un item
     * @param idItem id dell'item di cui si vogliono ottenere foto
     * @return lista di oggetti Foto per l'item specificato, null se fallisce
     */
    public static ArrayList<Foto> getFotoItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();

        try {
            ArrayList<Foto> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto, mayandb.Link_Item_Foto WHERE Foto.id_foto=Link_Item_Foto.id_foto and id_item=" + idItem + ";");

            while (rs.next()) {
                lista.add(new Foto(rs.getInt("id_foto"), rs.getString("link_foto")));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
