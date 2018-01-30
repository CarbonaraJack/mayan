package bean;

/**
 * bean per il carrello
 *
 * @author Michela
 */
public class carrelloBean {

    private int idItem;
    private String nome;
    private String produttore;
    private String venditore;
    private String immagine;
    private double prezzo;
    private int quantita;
    private int idVenditore;
    private int idUserVenditore;
    private String tipologiaVenditore;

    /**
     * costruttore completo per il carrello
     *
     * @param idItem
     * @param nome
     * @param produttore
     * @param idVenditore
     * @param venditore
     * @param immagine
     * @param prezzo
     * @param idUserVenditore
     */
    public carrelloBean(int idItem, String nome, String produttore, int idVenditore, String venditore, String immagine, double prezzo, int idUserVenditore) {
        this.idItem = idItem;
        this.nome = nome;
        this.produttore = produttore;
        this.prezzo = prezzo;
        this.idVenditore = idVenditore;
        this.venditore = venditore;
        this.immagine = immagine;
        this.prezzo = prezzo;
        this.idUserVenditore = idUserVenditore;
    }

    /**
     * costruttore vuoto del carrello
     */
    public carrelloBean() {
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdItem() {
        return this.idItem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public String getProduttore() {
        return this.produttore;
    }

    public void setVenditore(String venditore) {
        this.venditore = venditore;
    }

    public String getVenditore() {
        return this.venditore;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getImmagine() {
        return this.immagine;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getPrezzo() {
        return this.prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getQuantita() {
        return this.quantita;
    }

    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    public int getIdVenditore() {
        return this.idVenditore;
    }

    public void setIdUserVenditore(int idUserVenditore) {
        this.idUserVenditore = idUserVenditore;
    }

    public int getIdUserVenditore() {
        return this.idUserVenditore;
    }

    public void setTipologiaVenditore(String tipologiaVenditore) {
        this.tipologiaVenditore = tipologiaVenditore;
    }

    public String getTipologiaVenditore() {
        return this.tipologiaVenditore;
    }
}
