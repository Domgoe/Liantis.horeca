package kg.liantis.horeca.config.loaddata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HorecaJsonModel {

    @JsonProperty("json_featuretype")
    private String json_featuretype;
    @JsonProperty("Naam")
    private String Naam;
    @JsonProperty("Formule")
    private String Formule;
    @JsonProperty("Straat")
    private String Straat;
    @JsonProperty("HuisNr")
    private int HuisNr;
    @JsonProperty("HuisNrToev")
    private String HuisNrToev;
    @JsonProperty("Postcode")
    private String Postcode;
    @JsonProperty("Deelgemeente")
    private String Deelgemeente;
    @JsonProperty("Gemeente")
    private String Gemeente;
    @JsonProperty("Branche")
    private String Branche;
    @JsonProperty("Check_dat")
    private String Check_dat;
    @JsonProperty("Winkelgebied")
    private String Winkelgebied;
    @JsonProperty("Subcentrum")
    private String Subcentrum;
    @JsonProperty("StrnmEnHuisnr")
    private String StrnmEnHuisnr;
    @JsonProperty("Aangemaakt op")
    private String Aangemaakt_op;
    @JsonProperty("json_ogc_wkt_crs")
    private String json_ogc_wkt_crs;
    @JsonProperty("json_geometry")
    private GeometryJsonModel GeometryJsonModel;

    public String getJson_featuretype() {
        return json_featuretype;
    }

    public void setJson_featuretype(String json_featuretype) {
        this.json_featuretype = json_featuretype;
    }

    public String getNaam() {
        return Naam;
    }

    public void setNaam(String naam) {
        Naam = naam;
    }

    public String getFormule() {
        return Formule;
    }

    public void setFormule(String formule) {
        Formule = formule;
    }

    public String getStraat() {
        return Straat;
    }

    public void setStraat(String straat) {
        Straat = straat;
    }

    public int getHuisNr() {
        return HuisNr;
    }

    public void setHuisNr(int huisNr) {
        HuisNr = huisNr;
    }

    public String getHuisNrToev() {
        return HuisNrToev;
    }

    public void setHuisNrToev(String huisNrToev) {
        HuisNrToev = huisNrToev;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getDeelgemeente() {
        return Deelgemeente;
    }

    public void setDeelgemeente(String deelgemeente) {
        Deelgemeente = deelgemeente;
    }

    public String getGemeente() {
        return Gemeente;
    }

    public void setGemeente(String gemeente) {
        Gemeente = gemeente;
    }

    public String getBranche() {
        return Branche;
    }

    public void setBranche(String branche) {
        Branche = branche;
    }

    public String getCheck_dat() {
        return Check_dat;
    }

    public void setCheck_dat(String check_dat) {
        Check_dat = check_dat;
    }

    public String getWinkelgebied() {
        return Winkelgebied;
    }

    public void setWinkelgebied(String winkelgebied) {
        Winkelgebied = winkelgebied;
    }

    public String getSubcentrum() {
        return Subcentrum;
    }

    public void setSubcentrum(String subcentrum) {
        Subcentrum = subcentrum;
    }

    public String getStrnmEnHuisnr() {
        return StrnmEnHuisnr;
    }

    public void setStrnmEnHuisnr(String strnmEnHuisnr) {
        StrnmEnHuisnr = strnmEnHuisnr;
    }

    public String getAangemaakt_op() {
        return Aangemaakt_op;
    }

    public void setAangemaakt_op(String aangemaakt_op) {
        Aangemaakt_op = aangemaakt_op;
    }

    public String getJson_ogc_wkt_crs() {
        return json_ogc_wkt_crs;
    }

    public void setJson_ogc_wkt_crs(String json_ogc_wkt_crs) {
        this.json_ogc_wkt_crs = json_ogc_wkt_crs;
    }

    public GeometryJsonModel getGeometryJsonModel() {
        return GeometryJsonModel;
    }

    public void setGeometryJsonModel(GeometryJsonModel geometryJsonModel) {
        GeometryJsonModel = geometryJsonModel;
    }
}
