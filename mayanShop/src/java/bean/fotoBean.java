package bean;

/**
 * bean per le Foto
 *
 * @author Marcello
 */
public class fotoBean {

    private int idFoto;
    private String linkFoto;

    /**
     * Costruttore completo della classe fotoBean
     *
     * @param idFoto l'id della foto
     * @param linkFoto l'url della foto
     */
    public fotoBean(int idFoto, String linkFoto) {
        this.idFoto = idFoto;
        this.linkFoto = linkFoto;
    }

    /**
     * costruttore vuoto
     */
    public fotoBean() {
    }

    @Override
    public String toString() {
        return "idFoto: " + idFoto;
    }

    public boolean isNull() {
        return this.toString() == null;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdFoto() {
        return this.idFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public String getLinkFoto() {
        return this.linkFoto;
    }
}
