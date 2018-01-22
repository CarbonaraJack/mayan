/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *  bean per gli users
 * @author Michela e Marcello
 */
public class User {
    private int idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String tipo;
    
    public User(int id, String nome, String cognome, String email, String password, String tipo){
        this.idUser=id;
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
        this.password=password;
        this.tipo=tipo;
    }
    public User(){
    }
    
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
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return this.tipo;
    }
}
