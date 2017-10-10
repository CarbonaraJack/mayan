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
public class userBean {
    private int idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }
    public int getIdUser(){
        return this.idUser;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    public String getCognome(){
        return this.cognome;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
}
