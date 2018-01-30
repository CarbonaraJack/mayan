package bean;

/**
 * bean per i negozi di un item
 *
 * @author Michela
 */
public class itemNegozioBean {

    private int idNegozio;
    private int idItem;
    private String nomeNegozio;
    private int numStock;
    private double prezzo;
    private String tipoNegozio;
    private int idLocation;
    private locationBean location = new locationBean();
    private cittaBean citta = new cittaBean();

    /**
     * Costruttore completo
     *
     * @param idNegozio
     * @param idItem
     * @param nomeNegozio
     * @param numStock
     * @param prezzo
     * @param tipoNegozio
     */
    public itemNegozioBean(int idNegozio, int idItem, String nomeNegozio, int numStock, double prezzo, String tipoNegozio) {
        this.idNegozio = idNegozio;
        this.idItem = idItem;
        this.nomeNegozio = nomeNegozio;
        this.numStock = numStock;
        this.prezzo = prezzo;
        this.tipoNegozio = tipoNegozio;
    }

    /**
     * Costruttore per la ricerca item
     *
     * @param idNegozio
     * @param nomeNegozio
     * @param numStock
     * @param prezzo
     * @param tipoNegozio
     */
    public itemNegozioBean(int idNegozio, String nomeNegozio, int numStock, double prezzo, String tipoNegozio) {
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;
        this.numStock = numStock;
        this.prezzo = prezzo;
        this.tipoNegozio = tipoNegozio;
    }

    /**
     * Costrutore senza id (inserimento)
     *
     * @param idNegozio
     * @param nomeNegozio
     * @param numStock
     * @param prezzo
     * @param tipoNegozio
     * @param idLocation
     * @param citta
     */
    public itemNegozioBean(int idNegozio, String nomeNegozio, int numStock, double prezzo, String tipoNegozio, int idLocation, int citta) {
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;
        this.numStock = numStock;
        this.prezzo = prezzo;
        this.tipoNegozio = tipoNegozio;
        this.idLocation = idLocation;
        this.citta.setIdCitta(citta);
    }

    /**
     * Costruttore light per la mappa
     *
     * @param idNegozio
     * @param idLocation
     * @param latitudine
     * @param longitudine
     */
    public itemNegozioBean(int idNegozio, int idLocation, String latitudine, String longitudine) {
        this.idNegozio = idNegozio;
        this.idLocation = idLocation;
        this.location.setLatitudine(latitudine);
        this.location.setLongitudine(longitudine);
    }

    /**
     * Costruttore superLight
     *
     * @param idNegozio
     * @param nomeNegozio
     */
    public itemNegozioBean(int idNegozio, String nomeNegozio) {
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;
        //inizializzo i valori come se non ci fosse stock in negozio
        this.idItem = -1;
        this.numStock = 0;
        this.prezzo = 0.0;
    }

    public itemNegozioBean() {
    }

    public void inserisciStock(int idItem, int numStock, double prezzo) {
        this.idItem = idItem;
        this.numStock = numStock;
        this.prezzo = prezzo;
    }

    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    public int getIdNegozio() {
        return this.idNegozio;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdItem() {
        return this.idItem;
    }

    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    public String getNomeNegozio() {
        return this.nomeNegozio;
    }

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    public int getNumStock() {
        return this.numStock;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getPrezzo() {
        return this.prezzo;
    }

    public void setTipoNegozio(String tipoNegozio) {
        this.tipoNegozio = tipoNegozio;
    }

    public String getTipoNegozio() {
        return this.tipoNegozio;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public int getIdLocation() {
        return this.idLocation;
    }

    public void setLocation(locationBean location) {
        this.location = location;
    }

    public locationBean getLocation() {
        return this.location;
    }

    public void setLatitudine(String latitudine) {
        this.location.setLatitudine(latitudine);
    }

    public void setLongitudine(String longitudine) {
        this.location.setLongitudine(longitudine);
    }

    public void setCitta(cittaBean citta) {
        this.citta = citta;
    }

    public cittaBean getCitta() {
        return this.citta;
    }

    public int getIdCitta() {
        return this.citta.getIdCitta();
    }
}
