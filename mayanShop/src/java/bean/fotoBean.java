package bean;

/**
 * bean per le Foto
 *
 * @author Marcello
 */
public class fotoBean {

    private int idFoto;
    private String linkFoto;

    public fotoBean(int idFoto, String linkFoto) {
        this.idFoto = idFoto;
        this.linkFoto = linkFoto;
    }

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
