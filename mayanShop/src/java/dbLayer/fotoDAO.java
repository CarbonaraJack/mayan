/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Michela
 */
public class fotoDAO {
    public static String getFoto(int idFoto){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto WHERE id_foto=" + idFoto + ";");
            
            if(rs.next()){
                return rs.getString("link_foto");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<String> getFotoNegozio(int idNegozio){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<String> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto, mayandb.Link_Negozio_Foto WHERE Foto.id_foto=Link_Negozio_Foto.id_foto and id_negozio=" + idNegozio + ";");
            
            while(rs.next()){
                lista.add(rs.getString("link_foto"));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<String> getFotoItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<String> lista = new ArrayList<>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Foto, mayandb.Link_Item_Foto WHERE Foto.id_foto=Link_Item_Foto.id_foto and id_item=" + idItem + ";");
            
            while(rs.next()){
                lista.add(rs.getString("link_foto"));
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
