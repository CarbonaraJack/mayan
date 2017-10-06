/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Michela
 */
public interface Provider {
    String DRIVER="com.mysql.jdbc.Driver";
    
    /*String CONNECTION_URL="jdbc:mysql://mayandatabase.c147tajn45vc.us-east-2.rds.amazonaws.com/mayandb?";
    String USERNAME="webuser";
    String PASSWORD="public";*/
    
    // stringa di connessione per db locale
    String CONNECTION_URL="jdbc:mysql://localhost/mayandb?";
    String USERNAME="root";
    String PASSWORD="root";
}
