
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
    private String nomeMittente;
    private String nomeDestinatario;

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
        this.nomeMittente = nomeMittente;
        this.nomeDestinatario = nomeDestinatario;
    }

    public messaggioBean(String json) {
        JsonElement messageElement = new JsonParser().parse(json);
        JsonObject messageObject = messageElement.getAsJsonObject();
        this.id_messaggio = messageObject.get("id_messaggio").getAsInt();
        this.tipo = messageObject.get("tipo").getAsString();
        this.descrizione = messageObject.get("descrizione").getAsString();
        this.stato = messageObject.get("stato").getAsString();
        this.id_risposta = messageObject.get("id_risposta").getAsInt();
        this.id_destinatario = messageObject.get("id_destinatario").getAsInt();
        this.id_mittente = messageObject.get("id_mittente").getAsInt();
        this.id_transazione = messageObject.get("id_transazione").getAsInt();
        this.letto = messageObject.get("letto").getAsInt();
        this.nomeMittente = messageObject.get("nomeMittente").getAsString();
        this.nomeDestinatario = messageObject.get("nomeDestinatario").getAsString();

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

    public String getNomeMittente() {
        return nomeMittente;
    }

    public void setNomeMittente(String nomeMittente) {
        this.nomeMittente = nomeMittente;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    
}