package logica;

/**
 *
 * @author evertjan.jacobs
 */
public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private float surfaceArea;
    private int indepYear;
    private int population;
    private float lifeExpectancy;
    private float gnp;
    private float gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private int capital;

    public Country(String code, String name, String continent, String region, float surfaceArea, int indepYear, int population, float lifeExpectancy, float gnp, float gnpOld, String localName, String governmentForm, String headOfState, int capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
    }

    public Country(String name, String headOfState) {
        this.name = name;
        this.headOfState = headOfState;
    }

    public Country(String name, float surfaceArea) {
        this.name = name;
        this.surfaceArea = surfaceArea;
    }

    public Country(String code, String name, int population) {
        this.code = code;
        this.name = name;
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public float getSurfaceArea() {
        return surfaceArea;
    }

    public int getIndepYear() {
        return indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public float getGnp() {
        return gnp;
    }

    public float getGnpOld() {
        return gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public int getCapital() {
        return capital;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Country)) return false;
        Country c = (Country)o;
        return this.name.equals(c.name) && this.headOfState.equals(c.headOfState);
    }    
}
