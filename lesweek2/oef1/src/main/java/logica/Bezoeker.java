package logica;

/**
 *
 * @author evertjan.jacobs
 */
public class Bezoeker {

    private int id;
    private String naam;
    private String voornaam;
    private String straat;
    private String gemeente;
    private String postcode;
    private String land;

    public Bezoeker(int id, String naam, String voornaam, String straat, String gemeente, String postcode, String land) {
        this.id = id;
        this.naam = naam;
        this.voornaam = voornaam;
        this.straat = straat;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.land = land;
    }

    public Bezoeker(String naam, String voornaam, String land) {
        this(-1,naam, voornaam, null, null, null, land);
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getStraat() {
        return straat;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getLand() {
        return land;
    }
}
