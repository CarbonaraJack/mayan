/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;

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
    private String numWarning;
    private ArrayList<String> foto = new ArrayList<>();
    private String latitudine;
    private String longitudine;
    private String via;
    private String citta;
    private String regione;
    private String stato;
    
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
    
    public void setNumWarning(String numWarning){
        this.numWarning = numWarning;
    }
    public String getNumWarning(){
        return this.numWarning;
    }
    
    public void setFoto(String foto) {
        this.foto.add(foto);
    }
    public void setFoto(ArrayList<String> foto) {
        this.foto.addAll(foto);
    }
    public ArrayList<String> getFoto() {
        return this.foto;
    }
    
    public void setLatitudine(String latitudine){
        this.latitudine = latitudine;
    }
    public String getLatitudine(){
        return this.latitudine;
    }
    
    public void setLongitudine(String longitudine){
        this.longitudine = longitudine;
    }
    public String getLongitudine(){
        return this.longitudine;
    }
    
    public void setVia(String via){
        this.via = via;
    }
    public String getVia(){
        return this.via;
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
