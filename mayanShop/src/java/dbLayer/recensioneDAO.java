/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbLayer;

import bean.recensioneBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Michela
 */
public class recensioneDAO {
    public recensioneBean getRecensione(int idRec){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione WHERE id_recensione=" + idRec + ";");
            
            if(rs.next()){
                recensioneBean recensione = new recensioneBean(
                        rs.getInt("id_recensione"),
                        rs.getString("tipo"),
                        rs.getString("testo"),
                        rs.getDouble("stelline"),
                        rs.getInt("id_risp_rec"),
                        rs.getInt("id_user")
                );
                return recensione;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<recensioneBean> getRecensioneByUser(int idUser){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<recensioneBean> lista = new ArrayList<recensioneBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione WHERE id_user=" + idUser + ";");
            
            while(rs.next()){
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
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<recensioneBean> getRecenzioneByItem(int idItem){
        Connection connection = DAOFactoryUsers.getConnection();
        
        try {
            ArrayList<recensioneBean> lista = new ArrayList<recensioneBean>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mayandb.Recensione, mayandb.Link_Rec_Item WHERE Link_Rec_Item.id_recensione=Recensione.id_recensione and id_item=" + idItem + ";");
            
            while(rs.next()){
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
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
