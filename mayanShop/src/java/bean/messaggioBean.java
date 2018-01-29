package bean;

/**
 * Bean per la tabella Messaggio
 *
 * @author Marcello
 */
public class messaggioBean {

    private int idMessaggio;
    private String tipo;
    private String descrizione;
    private String stato;
    private int idRisposta;
    private int idDestinatario;
    private int idMittente;
    private int idTransazione;
    private int letto;

    public messaggioBean(int idMessaggio, String tipo, String descrizione, String stato, int idRisposta, int idDestinatario, int idMittente, int idTransazione, int letto) {
        this.idMessaggio = idMessaggio;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.stato = stato;
        this.idRisposta = idRisposta;
        this.idDestinatario = idDestinatario;
        this.idMittente = idMittente;
        this.idTransazione = idTransazione;
        this.letto = letto;
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

    public String getTipo() {
        return tipo;
    }

    public String getStato() {
        return stato;
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

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setLetto(int letto) {
        this.letto = letto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
