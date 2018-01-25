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
public class locationBean {
    private int idLocation;
    private Float latitudine;
    private Float longitudine;
    private String via;
    private int idCitta;
    
    public locationBean(int idLocation, Float latitudine, Float longitudine, String via, int idCitta){
        this.idLocation = idLocation;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.via = via;
        this.idCitta = idCitta;
    }
    
    public locationBean(){}
    
    public void setIdLocation(int idLocation){
        this.idLocation = idLocation;
    }
    public int getIdLocation() {
        return this.idLocation;
    }
    
    public void setLatitudine(Float latitudine){
        this.latitudine = latitudine;
    }
    public Float getLatitudine(){
        return this.latitudine;
    }
    
    public void setLongitudine(Float longitudine){
        this.longitudine = longitudine;
    }
    public Float getLongitudine(){
        return this.longitudine;
    }
    
    public void setVia(String via){
        this.via = via;
    }
    public String getVia(){
        return this.via;
    }
    
    public void setIdCitta(int idCitta){
        this.idCitta = idCitta;
    }
    public int getIdCitta(){
        return this.idCitta;
    }
}
