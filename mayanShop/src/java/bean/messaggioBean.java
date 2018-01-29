package bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Thomas
 */
public class messaggioBean {

    private String tipo;
    private String descrizione;
    private String stato;
    private int idRisposta;
    private int idMessaggio;
    private int idDestinatario;
    private int idMittente;
    private int idTransazione;
    private int letto;
    private String nomeDestinatario;
    private String nomeMittente;

    public messaggioBean(int id_messaggio, String tipo, String descrizione, String stato, int id_risposta, int id_destinatario, int id_mittente, int id_transazione, int letto, String nomeDestinatario, String nomeMittente) {
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.idRisposta = id_risposta;
        this.idMessaggio = id_messaggio;
        this.idDestinatario = id_destinatario;
        this.idMittente = id_mittente;
        this.idTransazione = id_transazione;
        this.letto = letto;
        this.nomeDestinatario = nomeDestinatario;
        this.nomeMittente = nomeMittente;
    }

    public messaggioBean(String tipo, String descrizione, String stato, int id_risposta, int id_destinatario, int id_mittente, int id_transazione, int letto, String nomeDestinatario, String nomeMittente) {
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.idRisposta = id_risposta;
        this.idDestinatario = id_destinatario;
        this.idMittente = id_mittente;
        this.idTransazione = id_transazione;
        this.letto = letto;
        this.nomeDestinatario = nomeDestinatario;
        this.nomeMittente = nomeMittente;
    }

    public messaggioBean(String json) {
        JsonElement messageElement = new JsonParser().parse(json);
        JsonObject messageObject = messageElement.getAsJsonObject();
        this.idMessaggio = messageObject.get("idMessaggio").getAsInt();
        this.tipo = messageObject.get("tipo").getAsString();
        this.descrizione = messageObject.get("descrizione").getAsString();
        this.stato = messageObject.get("stato").getAsString();
        this.idRisposta = messageObject.get("idRisposta").getAsInt();
        this.idDestinatario = messageObject.get("idDestinatario").getAsInt();
        this.idMittente = messageObject.get("idMittente").getAsInt();
        this.idTransazione = messageObject.get("idTransazione").getAsInt();
        this.letto = messageObject.get("letto").getAsInt();
        this.nomeMittente = messageObject.get("nomeMittente").getAsString();
        this.nomeDestinatario = messageObject.get("nomeDestinatario").getAsString();

    }

    public messaggioBean() {
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public int getIdMessaggio() {
        return idMessaggio;
    }

    public int getIdMittente() {
        return idMittente;
    }

    public int getIdRisposta() {
        return idRisposta;
    }

    public int getIdTransazione() {
        return idTransazione;
    }

    public int getLetto() {
        return letto;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public String getNomeMittente() {
        return nomeMittente;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public void setIdMessaggio(int idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public void setIdMittente(int idMittente) {
        this.idMittente = idMittente;
    }

    public void setIdRisposta(int idRisposta) {
        this.idRisposta = idRisposta;
    }

    public void setIdTransazione(int idTransazione) {
        this.idTransazione = idTransazione;
    }

    public void setLetto(int letto) {
        this.letto = letto;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public void setNomeMittente(String nomeMittente) {
        this.nomeMittente = nomeMittente;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
