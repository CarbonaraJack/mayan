
package bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Bean per la tabella messaggio
 *
 * @author Marcello
 */
public class messaggioBean {

    private int id_messaggio;
    private String tipo;
    private String descrizione;
    private String stato;
    private int id_risposta;
    private int id_destinatario;
    private int id_mittente;
    private int id_transazione;
    private int letto;
    private String nome_mittente;
    private String nome_destinatario;

    public messaggioBean(int idMessaggio, String tipo, String descrizione, String stato, int idRisposta, int idDestinatario, int idMittente, int idTransazione, int letto) {
        this.id_messaggio = idMessaggio;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.id_risposta = idRisposta;
        this.id_destinatario = idDestinatario;
        this.id_mittente = idMittente;
        this.id_transazione = idTransazione;
        this.letto = letto;
    }

    public messaggioBean(int idMessaggio, String tipo, String descrizione, String stato, int idRisposta, int idDestinatario, int idMittente, int idTransazione, int letto, String nomeMittente, String nomeDestinatario) {
        this.id_messaggio = idMessaggio;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.id_risposta = idRisposta;
        this.id_destinatario = idDestinatario;
        this.id_mittente = idMittente;
        this.id_transazione = idTransazione;
        this.letto = letto;
        this.nome_mittente = nomeMittente;
        this.nome_destinatario = nomeDestinatario;
    }

    public messaggioBean(String json) {
        JsonElement messageElement = new JsonParser().parse(json);
        JsonObject messageObject = messageElement.getAsJsonObject();
        this.id_messaggio = messageObject.get("idMessaggio").getAsInt();
        this.tipo = messageObject.get("tipo").getAsString();
        this.descrizione = messageObject.get("descrizione").getAsString();
        this.stato = messageObject.get("stato").getAsString();
        this.id_risposta = messageObject.get("idRisposta").getAsInt();
        this.id_destinatario = messageObject.get("idDestinatario").getAsInt();
        this.id_mittente = messageObject.get("idMittente").getAsInt();
        this.id_transazione = messageObject.get("idTransazione").getAsInt();
        this.letto = messageObject.get("letto").getAsInt();
        this.nome_mittente = messageObject.get("nomeMittente").getAsString();
        this.nome_destinatario = messageObject.get("nomeDestinatario").getAsString();

    }

    public messaggioBean() {
    }

    public int getId_messaggio() {
        return id_messaggio;
    }

    public void setId_messaggio(int id_messaggio) {
        this.id_messaggio = id_messaggio;
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

    public String getNome_mittente() {
        return nome_mittente;
    }

    public void setNome_mittente(String nome_mittente) {
        this.nome_mittente = nome_mittente;
    }

    public String getNome_destinatario() {
        return nome_destinatario;
    }

    public void setNome_destinatario(String nome_destinatario) {
        this.nome_destinatario = nome_destinatario;
    }

    
}