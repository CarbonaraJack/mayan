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
public class itemBean {
    private int idItem;
    private String nome;
    private String produttore;
    private String descrizione;
    private String categoria;
    private String immagine;
    private double prezzo;
    private double voto;
    private int totAcquistato;
    private int numVisualizzazioni;
    
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
    
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
    public String getDescrizione(){
        return this.descrizione;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public String getCategoria(){
        return this.categoria;
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
    
    public void setVoto (double voto){
        this.voto = voto;
    }
    public double getVoto (){
        return this.voto;
    }
    
    public void setTotAcquistato(int totAcquistato){
        this.totAcquistato = totAcquistato;
    }
    public int getTotAcquistato(){
        return this.totAcquistato;
    }
    
    public void setNumVisualizzazioni(int numVisualizzazioni){
        this.numVisualizzazioni = numVisualizzazioni;
    }
    public int getNumVisualizzazioni(){
        return this.numVisualizzazioni;
    }
}