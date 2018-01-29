package bean;

/**
 * Classe che getisce gli acquisti
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
