package bean;

/**
 *  bean per le Foto
 * @author Marcello
 */
public class Foto {
    private int idFoto;
    private String linkFoto;
    
    public Foto(int idFoto, String linkFoto){
        this.idFoto=idFoto;
        this.linkFoto=linkFoto;
    }
    public Foto(){
    }
    @Override
    public String toString(){
        return "idFoto: "+idFoto;
    }
    public boolean isNull(){
        return this.toString()==null;
    }
    
    public void setIdFoto(int idFoto){
        this.idFoto = idFoto;
    }
    public int getIdFoto(){
        return this.idFoto;
    }
    
    public void setLinkFoto(String linkFoto){
        this.linkFoto = linkFoto;
    }
    public String getLinkFoto(){
        return this.linkFoto;
    }
}
