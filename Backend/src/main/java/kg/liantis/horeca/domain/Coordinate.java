package kg.liantis.horeca.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate() {
        this(0,0);
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
