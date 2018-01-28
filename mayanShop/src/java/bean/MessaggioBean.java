/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Thomas
 */
public class MessaggioBean {
    private String tipo;
    private String descrizione;
    private String stato;
    private int id_risposta;
    private int id_destinatario;
    private int id_mittente;
    private int id_transazione;
    private int letto;
    private String nomeDestinatario;
    private String nomeMittente;
    
    public MessaggioBean(String tipo, String descrizione, String stato, int id_risposta, int id_destinatario, int id_mittente, int id_transazione, int letto, String nomeDestinatario, String nomeMittente) {
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.id_risposta = id_risposta;
        this.id_destinatario = id_destinatario;
        this.id_mittente = id_mittente;
        this.id_transazione = id_transazione;
        this.letto = letto;
        this.nomeDestinatario = nomeDestinatario;
        this.nomeMittente = nomeMittente;
    }

    public MessaggioBean() {
    }
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getId_risposta() {
        return id_risposta;
    }

    public void setId_risposta(int id_risposta) {
        this.id_risposta = id_risposta;
    }

    public int getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(int id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public int getId_mittente() {
        return id_mittente;
    }

    public void setId_mittente(int id_mittente) {
        this.id_mittente = id_mittente;
    }

    public int getId_transazione() {
        return id_transazione;
    }

    public void setId_transazione(int id_transazione) {
        this.id_transazione = id_transazione;
    }

    public int getLetto() {
        return letto;
    }

    public void setLetto(int letto) {
        this.letto = letto;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getNomeMittente() {
        return nomeMittente;
    }

    public void setNomeMittente(String nomeMittente) {
        this.nomeMittente = nomeMittente;
    }
    
    
}
