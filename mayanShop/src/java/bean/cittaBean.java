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
public class cittaBean {
    private int idCitta;
    private String citta;
    private String regione;
    private String stato;
    
    public cittaBean(int idCitta, String citta, String regione, String stato){
        this.idCitta = idCitta;
        this.citta = citta;
        this.regione = regione;
        this.stato = stato;
    }
    
    public cittaBean(){}
    
    public void setIdCitta(int idCitta){
        this.idCitta = idCitta;
    }
    public int getIdCitta(){
        return this.idCitta;
    }
    
    public void setCitta(String citta){
        this.citta = citta;
    }
    public String getCitta(){
        return this.citta;
    }
    
    public void setRegione(String regione){
        this.regione = regione;
    }
    public String getRegione(){
        return this.regione;
    }
    
    public void setStato(String stato){
        this.stato = stato;
    }
    public String getStato(){
        return this.stato;
    }
}
