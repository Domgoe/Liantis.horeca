package kg.liantis.horeca.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Objects;

@Entity
public class Horeca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String naam;

    private String formule;

    private String straat;

    private int huisNr;

    private String huisNrToev;

    private String postcode;

    private String deelgemeente;

    private String gemeente;

    private String branche;

    private Date checkDate;

    private String winkelgebied;

    private String subcentrum;

    private String strnmEnHuisnr;

    private Date aangemaaktOp;

    private String json_ogc_wkt_crs;

    @Min(0)
    @Max(5)
    private int rating;

    @Embedded
    private Geometry geometry;

    public Horeca() {  }

    public Horeca(String type, String naam, String formule, String straat, int huisNr, String huisNrToev ,String postcode,
                  String deelgemeente, String gemeente, String branche, Date checkDate, String winkelgebied,
                  String subcentrum, String strnmEnHuisnr, Date aangemaaktOp, String json_ogc_wkt_crs,
                  Geometry geometry) {
        this.type = type;
        this.naam = naam;
        this.formule = formule;
        this.straat = straat;
        this.huisNr = huisNr;
        this.huisNrToev = huisNrToev;
        this.postcode = postcode;
        this.deelgemeente = deelgemeente;
        this.gemeente = gemeente;
        this.branche = branche;
        this.checkDate = checkDate;
        this.winkelgebied = winkelgebied;
        this.subcentrum = subcentrum;
        this.strnmEnHuisnr = strnmEnHuisnr;
        this.aangemaaktOp = aangemaaktOp;
        this.json_ogc_wkt_crs = json_ogc_wkt_crs;
        this.geometry = geometry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(int huisNr) {
        this.huisNr = huisNr;
    }

    public String getHuisNrToev() {
        return huisNrToev;
    }

    public void setHuisNrToev(String huisNrToev) {
        this.huisNrToev = huisNrToev;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDeelgemeente() {
        return deelgemeente;
    }

    public void setDeelgemeente(String deelgemeente) {
        this.deelgemeente = deelgemeente;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getWinkelgebied() {
        return winkelgebied;
    }

    public void setWinkelgebied(String winkelgebied) {
        this.winkelgebied = winkelgebied;
    }

    public String getSubcentrum() {
        return subcentrum;
    }

    public void setSubcentrum(String subcentrum) {
        this.subcentrum = subcentrum;
    }

    public String getStrnmEnHuisnr() {
        return strnmEnHuisnr;
    }

    public void setStrnmEnHuisnr(String strnmEnHuisnr) {
        this.strnmEnHuisnr = strnmEnHuisnr;
    }

    public Date getAangemaaktOp() {
        return aangemaaktOp;
    }

    public void setAangemaaktOp(Date aangemaaktOp) {
        this.aangemaaktOp = aangemaaktOp;
    }

    public String getJson_ogc_wkt_crs() {
        return json_ogc_wkt_crs;
    }

    public void setJson_ogc_wkt_crs(String json_ogc_wkt_crs) {
        this.json_ogc_wkt_crs = json_ogc_wkt_crs;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horeca horeca = (Horeca) o;
        return Objects.equals(id, horeca.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
