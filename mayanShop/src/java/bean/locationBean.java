package bean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * bean per la location
 *
 * @author Michela
 */
public class locationBean {

    private int idLocation;
    private String latitudine;
    private String longitudine;
    private String via;
    private int idCitta;

    /**
     * costruttore completo per la location
     *
     * @param idLocation
     * @param latitudine
     * @param longitudine
     * @param via
     * @param idCitta
     */
    public locationBean(int idLocation, String latitudine, String longitudine, String via, int idCitta) {
        this.idLocation = idLocation;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.via = via;
        this.idCitta = idCitta;
    }

    public locationBean(String latitudine, String longitudine, String via) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.via = via;
    }

    /**
     * Costruttore di un locationBean partendo da una Stringa JSON pulita come
     * si deve
     *
     * @param json la stringa JSON pulita di caratteri speciali
     */
    public locationBean(String json) {
        JsonElement locationElement = new JsonParser().parse(json);
        JsonObject locationObject = locationElement.getAsJsonObject();
        this.latitudine = locationObject.get("latitudine").getAsString();
        this.longitudine = locationObject.get("longitudine").getAsString();
        this.via = locationObject.get("via").getAsString();
    }

    public locationBean() {
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public int getIdLocation() {
        return this.idLocation;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLatitudine() {
        return this.latitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLongitudine() {
        return this.longitudine;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getVia() {
        return this.via;
    }

    public void setIdCitta(int idCitta) {
        this.idCitta = idCitta;
    }

    public int getIdCitta() {
        return this.idCitta;
    }
}
