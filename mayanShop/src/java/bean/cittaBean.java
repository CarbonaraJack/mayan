/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * bean per la città
 * @author Michela
 */
public class cittaBean {
    private int idCitta;
    private String citta;
    private String regione;
    private String stato;
    
    /**
     * costruttore completo per la città
     * @param idCitta
     * @param citta
     * @param regione
     * @param stato 
     */
    public cittaBean(int idCitta, String citta, String regione, String stato){
        this.idCitta = idCitta;
        this.citta = citta;
        this.regione = regione;
        this.stato = stato;
    }
    
    /**
     * costruttore piccolo
     * @param citta
     * @param regione
     * @param stato 
     */
    public cittaBean(String citta, String regione, String stato){
        this.citta = citta;
        this.regione = regione;
        this.stato = stato;
    }
    
    /**
     * Costruttore che costrusce un oggetto città partendo da un Json pulito
     * come si deve
     * @param json il json con l'oggetto
     */
    public cittaBean(String json){
        JsonElement cittaElement = new JsonParser().parse(json);
        JsonObject cittaObject = cittaElement.getAsJsonObject();
        this.citta = cittaObject.get("citta").getAsString();
        this.regione = cittaObject.get("regione").getAsString();
        this.stato = cittaObject.get("stato").getAsString();
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
