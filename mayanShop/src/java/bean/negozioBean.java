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
    private int numWarning;
    private ArrayList<String> foto = new ArrayList<>();
    private int idLocation;
    /*private String latitudine;
    private String longitudine;
    private String via;*/
    private locationBean location = new locationBean();
    /*private int idCitta;
    private String citta;
    private String regione;
    private String stato;*/
    private cittaBean citta = new cittaBean();
    private int idVenditore;
    private ArrayList<itemBean> items = new ArrayList<>();
    
    public negozioBean(){}
    
    public negozioBean(int idNegozio, String nome, String descrizione, String webLink, double valutazioneMedia, String orari, String tipo, int numWarning, int idLocation){
        this.idNegozio = idNegozio;
        this.nome = nome;
        this.descrizione = descrizione;
        this.webLink = webLink;
        this.valutazioneMedia = valutazioneMedia;
        this.orari = orari;
        this.tipo = tipo;
        this.numWarning = numWarning;
        this.idLocation = idLocation;
    }
    
    public negozioBean(int idNegozio, String nome, double valutazioneMedia, String tipo, int idLocation, String latitudine, String longitudine, int idCitta, String citta, String regione){
        this.idNegozio = idNegozio;
        this.nome = nome;
        this.valutazioneMedia = valutazioneMedia;
        this.tipo = tipo;
        this.idLocation = idLocation;
        this.location.setIdLocation(idLocation);
        this.location.setLatitudine(latitudine);
        this.location.setLongitudine(longitudine);
        this.citta.setIdCitta(idCitta);
        this.citta.setCitta(citta);
        this.citta.setRegione(regione);
    }
    
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
    
    public void setFoto(String foto) {
        this.foto.add(foto);
    }
    public void setFoto(ArrayList<Foto> foto) {
        for(Foto f:foto){
            this.foto.add(f.getLinkFoto());
        }
    }
    public ArrayList<String> getFoto() {
        return this.foto;
    }
    
    public void setLocation(locationBean location){
        this.location = location;
    }
    public locationBean getLocation(){
        return this.location;
    }
    
    public int getIdCitta(){
        return this.location.getIdCitta();
    }
    
    public void setCitta(cittaBean citta){
        this.citta = citta;
    }
    public cittaBean getCitta(){
        return this.citta;
    }
    
    public void setIdLocation(int idLocation){
        this.idLocation = idLocation;
    }
    public int getIdLocation() {
        return this.idLocation;
    }
    
    public void setIdVenditore(int idVenditore){
        this.idVenditore = idVenditore;
    }
    public int getIdVenditore(){
        return this.idVenditore;
    }
    
    public void setItems(ArrayList<itemBean> items){
        this.items.addAll(items);
    }
    public void setItems(itemBean item){
        this.items.add(item);
    }
    public ArrayList<itemBean> getItems(){
        return this.items;
    }
    
    /*public void setLatitudine(String latitudine){
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
    }*/
    
    /*public void setIdCitta(int idCitta){
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
    }*/
}
