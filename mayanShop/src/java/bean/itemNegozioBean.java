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
public class itemNegozioBean {
    private int idNegozio;
    private String nomeNegozio;
    private int numStock;
    private double prezzo;
    private String tipoNegozio;
    
    public itemNegozioBean(int idNegozio, String nomeNegozio, int numStock, double prezzo, String tipoNegozio){
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;
        this.numStock = numStock;
        this.prezzo = prezzo;
        this.tipoNegozio = tipoNegozio;
    }
    
    public itemNegozioBean(){}
    
    public void setIdNegozio(int idNegozio){
        this.idNegozio = idNegozio;
    }
    public int getIdNegozio(){
        return this.idNegozio;
    }
    
    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }
    public String getNomeNegozio() {
        return this.nomeNegozio;
    }
    
    public void setNumStock(int numStock){
        this.numStock = numStock;
    }
    public int getNumStock(){
        return this.numStock;
    }
    
    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }
    public double getPrezzo() {
        return this.prezzo;
    }
    
    public void setTipoNegozio(String tipoNegozio) {
        this.tipoNegozio = tipoNegozio;
    }
    public String getTipoNegozio() {
        return this.tipoNegozio;
    }
}
