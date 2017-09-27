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
public class negozioBean {
    private int idNegozio;
    private String nome;
    private String descrizione;
    private String webLink;
    private double valutazioneMedia;
    private String orari;
    private String tipo;
    private int numWarning;
    
    public void setIdNegozio(int idNegozio){
        this.idNegozio = idNegozio;
    }
    public int getIdNegozio(){
        return this.idNegozio;
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
    
    public void setWebLink(String webLink){
        this.webLink = webLink;
    }
    public String getWebLink(){
        return this.webLink;
    }
    
    public void setOrari(String orari){
        this.orari = orari;
    }
    public String getOrari(){
        return this.orari;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public String getTipo(){
        return this.tipo;
    }
    
    public void setValutazioneMedia(double valutazioneMedia){
        this.valutazioneMedia = valutazioneMedia;
    }
    public double getValutazioneMedia(){
        return this.valutazioneMedia;
    }
    
    public void setNumWarning(int numWarning){
        this.numWarning = numWarning;
    }
    public int getNumWarning(){
        return this.numWarning;
    }
}
