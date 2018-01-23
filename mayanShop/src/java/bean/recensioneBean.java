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
public class recensioneBean {
    private int idRecensione;
    private String tipo;
    private String testo;
    private double stelline;
    private int idAutore;
    private String nomeAutore;
    private String cognomeAutore;
    
    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }
    public int getIdRecensione() {
        return this.idRecensione;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTesto(String testo) {
        this.testo = testo;
    }
    public String getTesto(){
        return this.testo;
    }
    
    public void setStelline(double stelline) {
        this.stelline = stelline;
    }
    public double getStelline() {
        return this.stelline;
    }
    
    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }
    public int getIdAutore() {
        return this.idAutore;
    }
    
    public void setNomeAutore(String nomeAutore) {
        this.nomeAutore = nomeAutore;
    }
    public String getNomeAutore() {
        return this.nomeAutore;
    }
    
    public void setCognomeAutore (String cognomeAutore) {
        this.cognomeAutore = cognomeAutore;
    }
    public String getCognomeAutore() {
        return this.cognomeAutore;
    }
}
