package kg.liantis.horeca.domain;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;

@Embeddable
public class Geometry {

    private String geoType;

    @Embedded
    private Coordinate coordinates ;

    public Geometry() {
    }

    public Geometry(String geoType, Coordinate coordinates) {
        this.geoType = geoType;
        this.coordinates = coordinates;
    }

    public String getGeoType() {
        return geoType;
    }

    public void setGeoType(String geoType) {
        this.geoType = geoType;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }
}
