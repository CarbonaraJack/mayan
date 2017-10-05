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
    private String descrizione;
    private String categoria;
    private double prezzo;
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
    
    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }
    public double getPrezzo() {
        return this.prezzo;
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
