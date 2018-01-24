/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.servlet.http.HttpSession;

/**
 * bean per gli users
 *
 * @author Michela e Marcello
 */
public class User {

    private int idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String tipo;

    public User(int id, String nome, String cognome, String email, String password, String tipo) {
        this.idUser = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    public User(int id, String nome, String cognome, String email, String tipo) {
        this.idUser = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.tipo = tipo;
    }

    /**
     * Funzione che crea un oggetto utente estrapolandone i dati dalla sessione
     * @param sessione la HttpSession attuale
     */
    public User(HttpSession sessione) {
        this.idUser = (int) sessione.getAttribute("userId");
        this.nome = (String) sessione.getAttribute("userName");
        this.cognome = (String) sessione.getAttribute("userSurname");
        this.email = (String) sessione.getAttribute("userEmail");
        this.tipo = (String) sessione.getAttribute("userType");
    }

    public User(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    /**
     * Funzione che aggiorna i dati della sessione
     * @param sessione la sessione dove salvare le cose
     */
    public void setSession(HttpSession sessione) {
        sessione.setAttribute("userId", this.idUser);
        sessione.setAttribute("userType", this.tipo);
        sessione.setAttribute("userName", this.nome);
        sessione.setAttribute("userSurname", this.cognome);
        sessione.setAttribute("userEmail", this.email);
    }

    @Override
    public String toString() {
        return "UserId: " + idUser;
    }

    public boolean isNull() {
        return this.toString() == null;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
