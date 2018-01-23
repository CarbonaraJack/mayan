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
public class carrelloBean {
    private int idItem;
    private String nome;
    private String produttore;
    private String venditore;
    private String immagine;
    private double prezzo;
    private int quantita;
    private int idVenditore;
    
    public void setIdItem(int idItem){
        this.idItem = idItem;
    }
    public int getIdItem(){
        return this.idItem;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    
    public void setProduttore(String produttore){
        this.produttore = produttore;
    }
    public String getProduttore(){
        return this.produttore;
    }
    
    public void setVenditore(String venditore){
        this.venditore = venditore;
    }
    public String getVenditore(){
        return this.venditore;
    }
    
    public void setImmagine(String immagine){
        this.immagine = immagine;
    }
    public String getImmagine(){
        return this.immagine;
    }
    
    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }
    public double getPrezzo() {
        return this.prezzo;
    }
    
    public void setQuantita(int quantita){
        this.quantita = quantita;
    }
    public int getQuanita(){
        return this.quantita;
    }
    
    public void setIdVenditore(int idVenditore){
        this.idVenditore = idVenditore;
    }
    public int getIdVenditore(){
        return this.idVenditore;
    }
}
