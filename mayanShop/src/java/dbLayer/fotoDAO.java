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
        Foto foto = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto WHERE id_foto=\'" + idFoto + "\'");
            if (rs.next()) {
                foto = new Foto(
                        rs.getInt("id_foto"),
                        rs.getString("link_foto")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return foto;
    }

    /**
     * Funzione che ottiene un oggetto Foto partendo dal nome
     *
     * @param nomeFoto il nome della foto
     * @return un oggetto Foto associato all'id
     */
    public static Foto getFoto(String nomeFoto) {
        Connection connection = DAOFactoryUsers.getConnection();
        Foto foto = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto WHERE link_foto=\'" + nomeFoto + "\'");
            if (rs.next()) {
                foto = new Foto(
                        rs.getInt("id_foto"),
                        rs.getString("link_foto")
                );
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return foto;
    }

    /**
     * Funzione che ritorna il numero di record nella tabella foto
     *
     * @return il numero di foto presenti nel database
     */
    public static int numFoto() {
        Connection connection = DAOFactoryUsers.getConnection();
        int res = 1000000;
        //nel db utilizzo 6 cifre, quindi nell'elemento
        //1000000 ci saranno gli errori
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) as conteggio FROM mayandb.Foto");
            if (rs.next()) {
                res = rs.getInt("conteggio");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nomeFoto;
    }

    /**
     * funzione che ottiene una lista di foto a partire da un negozio
     *
     * @param idNegozio id del negozio di cui si vogliono ottenere foto
     * @return lista di oggetti Foto per il negozio specificato, null se
     * fallisce
     */
    public static ArrayList<Foto> getFotoNegozio(int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<Foto> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto, mayandb.Link_Negozio_Foto WHERE Foto.id_foto=Link_Negozio_Foto.id_foto and id_negozio=" + idNegozio + ";");

            while (rs.next()) {
                lista.add(new Foto(rs.getInt("id_foto"), rs.getString("link_foto")));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * funzione che ottiene una lista di foto a partire da un item
     *
     * @param idItem id dell'item di cui si vogliono ottenere foto
     * @return lista di oggetti Foto per l'item specificato, null se fallisce
     */
    public static ArrayList<Foto> getFotoItem(int idItem) {
        Connection connection = DAOFactoryUsers.getConnection();
        ArrayList<Foto> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM mayandb.Foto, mayandb.Link_Item_Foto WHERE Foto.id_foto=Link_Item_Foto.id_foto and id_item=" + idItem + ";");

            while (rs.next()) {
                lista.add(new Foto(rs.getInt("id_foto"), rs.getString("link_foto")));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Funzione che cancella una foto dal database
     *
     * @param foto la foto da cancellare
     * @return true se viene cancellata, false altrimenti
     */
    public static boolean deleteFoto(Foto foto) {
        Connection connection = DAOFactoryUsers.getConnection();
        boolean res = false;
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(
                    "DELETE FROM mayandb.Foto WHERE id_foto="
                    + foto.getIdFoto()
                    + ";");
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
     * funzione che fornisce una foto per il negozio specificato
     *
     * @param idNegozio id del negozio di cui si vuole trovare una foto
     * @return una String se viene trovata una foto per il negozio specificato,
     * null altrimenti
     */
    public static String getOneFotoNegozio(int idNegozio) {
        Connection connection = DAOFactoryUsers.getConnection();
        String res = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT link_foto FROM mayandb.Foto, mayandb.Link_Negozio_Foto WHERE Foto.id_foto=Link_Negozio_Foto.id_foto and id_negozio=" + idNegozio + " LIMIT 1;");

            if (rs.next()) {
                res = rs.getString("link_foto");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
