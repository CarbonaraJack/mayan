package bean;

/**
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
    
    public itemNegozioBean(int idNegozio, int idItem, String nomeNegozio, int numStock, double prezzo, String tipoNegozio){
        this.idNegozio = idNegozio;
        this.idItem = idItem;
        this.nomeNegozio = nomeNegozio;
        this.numStock = numStock;
        this.prezzo = prezzo;
        this.tipoNegozio = tipoNegozio;
    }
    
    public itemNegozioBean(int idNegozio, String nomeNegozio){
        this.idNegozio = idNegozio;
        this.nomeNegozio = nomeNegozio;
        //inizializzo i valori come se non ci fosse stock in negozio
        this.idItem=-1;
        this.numStock=0;
        this.prezzo=0.0;
    }
    
    public itemNegozioBean(){}
    
    public void inserisciStock(int idItem,int numStock, double prezzo){
        this.idItem=idItem;
        this.numStock=idItem;
        this.prezzo=prezzo;
    }
    
    public void setIdNegozio(int idNegozio){
        this.idNegozio = idNegozio;
    }
    public int getIdNegozio(){
        return this.idNegozio;
    }
    
    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }
    public String getNomeNegozio() {
        return this.nomeNegozio;
    }
    
    public void setNumStock(int numStock){
        this.numStock = numStock;
    }
    public int getNumStock(){
        return this.numStock;
    }
    
    public void setPrezzo(double prezzo){
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
}
