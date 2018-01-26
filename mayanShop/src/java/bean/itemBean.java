/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import bean.Foto;

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
    private int idThumbnail;
    private String immagine;
    private double prezzoMinimo;
    private double voto;
    private int totAcquistato;
    private int numVisualizzazioni;
    private ArrayList<String> foto = new ArrayList<>();
    private ArrayList<itemNegozioBean> negozi = new ArrayList<>();
    private ArrayList<recensioneBean> recensioni = new ArrayList<>();
    
    public itemBean(int idItem, String nome, String produttore, String descrizione, String categoria, int idThumbnail, double prezzoMinimo, double voto, int totAcquistato, int numVisualizzazioni){
        this.idItem = idItem;
        this.nome = nome;
        this.produttore = produttore;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.idThumbnail = idThumbnail;
        this.prezzoMinimo = prezzoMinimo;
        this.voto = voto;
        this.totAcquistato = totAcquistato;
        this.numVisualizzazioni = numVisualizzazioni;
    }
    
    public itemBean(int idItem, String nome, String produttore, String categoria, int idThumbnail, String immagine, double prezzoMinimo, double voto){
        this.idItem = idItem;
        this.nome = nome;
        this.produttore = produttore;
        this.categoria = categoria;
        this.idThumbnail = idThumbnail;
        this.immagine = immagine;
        this.prezzoMinimo = prezzoMinimo;
        this.voto = voto;
    }
    /**
     * Costruttore ligt utile per avere oggetti più piccoli quando stampo la
     * lista di items da modificare nel lato aziendale
     * @param idItem
     * @param nome
     * @param produttore
     * @param categoria 
     */
    public itemBean(int idItem, String nome, String produttore, String categoria){
        this.idItem = idItem;
        this.nome= nome;
        this.produttore= produttore;
        this.categoria= categoria;
    }
    
    public itemBean(){}
    
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
    
    public void setIdThumbnail(int idThumbnail){
        this.idThumbnail = idThumbnail;
    }
    public int getIdThumbnail(){
        return this.idThumbnail;
    }
    
    public void setImmagine(String immagine){
        this.immagine = immagine;
    }
    public String getImmagine(){
        return this.immagine;
    }
    
    public void setPrezzoMinimo(double prezzoMinimo){
        this.prezzoMinimo = prezzoMinimo;
    }
    public double getPrezzo() {
        return this.prezzoMinimo;
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
    
    public void setNegozi(itemNegozioBean negozio) {
        this.negozi.add(negozio);
    }
    public void setNegozi(ArrayList<itemNegozioBean> negozi) {
        if(negozi != null){
            this.negozi.addAll(negozi);
        }
    }
    public ArrayList<itemNegozioBean> getNegozi() {
        return this.negozi;
    }
    
    public void setRecensioni(recensioneBean recensioni) {
        this.recensioni.add(recensioni);
    }
    public void setRecensioni(ArrayList<recensioneBean> recensioni) {
        this.recensioni.addAll(recensioni);
    }
    public ArrayList<recensioneBean> getRecensioni() {
        return this.recensioni;
    }
}
