package bean;

/**
 * Classe che gestisce gli acquisti
 *
 * @author Thomas
 */
public class acquistoBean {

    private int idTransazione;
    private int quantità;
    private int prezzo;
    private String dataora;
    private int idItem;
    private String nomeItem;
    private String linkFoto;
    private int idUser;
    private int idNegozio;
    private String nomeNegozio;

    /**
     * Costruttore completo per la classe acquistoBean
     *
     * @param idTransazione l'id della transazione
     * @param quantità la quantità di oggetti acquistati
     * @param prezzo il prezzo
     * @param dataora la data e l'ora dell'acquisto
     * @param idItem l'id dell'item acquistato
     * @param nomeItem il nome dell'item acquistato
     * @param linkFoto l'url della foto
     * @param idUser l'id dell'utente che ha acquistato
     * @param idNegozio l'id del negozio venditore
     * @param nomeNegozio il nome del negozio venditore
     */
    public acquistoBean(int idTransazione, int quantità, int prezzo, String dataora, int idItem, String nomeItem, String linkFoto, int idUser, int idNegozio, String nomeNegozio) {
        this.idTransazione = idTransazione;
        this.quantità = quantità;
        this.prezzo = prezzo;
        this.dataora = dataora;
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.linkFoto = linkFoto;
        this.idUser = idUser;
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;

    }

    public int getIdTransazione() {
        return idTransazione;
    }

    public void setIdTransazione(int idTransazione) {
        this.idTransazione = idTransazione;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getDataora() {
        return dataora;
    }

    public void setDataora(String dataora) {
        this.dataora = dataora;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdNegozio() {
        return idNegozio;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }
}
