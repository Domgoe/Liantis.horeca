package kg.liantis.horeca.config.loaddata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GeometryJsonModel {
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    ArrayList<Object> coordinates = new ArrayList <Object> ();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Object> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Object> coordinates) {
        this.coordinates = coordinates;
    }
}
