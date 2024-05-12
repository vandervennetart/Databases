package data;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import logica.Country;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author evertjan.jacobs & kristien.vanassche
 * @version 17-03-2020
 */
public class ConfiguratieTestDeel1 {

    private static DataLayerJDBC dl;

    @BeforeAll
    public static void setup() {
        try {
            String dbName = "world";
            System.out.println("Create database " + dbName);
            dl = new DataLayerJDBC(dbName);
            if (dl != null) {
                System.out.println("Drop table countries");
                dl.dropTable();
                System.out.println("Define table countries");
                dl.defineTable();
                System.out.println("Fill table countries");
                dl.addData();
                System.out.println("Start querying...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguratieTestDeel1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDown() {
        try {
            dl.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguratieTestDeel1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of getCountries method, of class DataLayerJDBC.
     */
    @Test
    public void testGetCountries() {
        System.out.println("getCountries");
        DataLayerJDBC instance = dl;
        int expResult = 239;
        List<Country> result = instance.getCountries();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getCountryAndHeadOfState method, of class DataLayerJDBC.
     */
    @Test
    public void testGetCountryAndHeadOfState() {
        System.out.println("getCountryAndHeadOfState");
        DataLayerJDBC instance = dl;
        int expResult = 5;
        List<Country> result = instance.getCountryAndHeadOfState();
        assertEquals(expResult, result.size());
        assertTrue(result.contains(new Country("Bouvet Island", "Harald V")));
    }

    /**
     * Test of getTop10VanEuropa method, of class DataLayerJDBC.
     */
    @Test
    public void testGetTop10VanEuropa() {
        System.out.println("getTop10VanEuropa");
        DataLayerJDBC instance = dl;
        int expResult = 10;
        List<Country> result = instance.getTop10VanEuropa();
        assertEquals(expResult, result.size());
        Country first = result.get(0);
        assertTrue(first.getName().equals("Russian Federation") && first.getSurfaceArea() == 17075400);
        Country last = result.get(result.size()-1);
        assertTrue(last.getName().equals("Italy") && last.getSurfaceArea() == 301316);        
    }

    /**
     * Test of getLandenEindigendOpLand method, of class DataLayerJDBC.
     */
    @Test
    public void testGetLandenEindigendOpLand() {
        System.out.println("getLandenEindigendOpLand");
        DataLayerJDBC instance = dl;
        List<String> result = instance.getLandenEindigendOpLand();
        assertEquals(12, result.size());
        assertTrue(result.get(0).equals("Bouvet Island"));
        assertTrue(result.get(result.size()-1).equals("Thailand"));         
    }

    /**
     * Test of getInwonersPerLand method, of class DataLayerJDBC.
     */
    @Test
    public void testGetInwonersPerLand() {
        System.out.println("getInwonersPerLand");
        DataLayerJDBC instance = dl;
        int expResult = 239;
        List<Country> result = instance.getInwonersPerLand();
        assertEquals(expResult, result.size());
        Country eerste = result.get(0);
        assertTrue(eerste.getCode().equals("CHN") && eerste.getName().equals("China") && eerste.getPopulation() == 1277558000);
        Country tiende = result.get(9);
        assertTrue(tiende.getCode().equals("TUR") && tiende.getName().equals("Turkey") && tiende.getPopulation() == 66591000);
        Country plaats65 = result.get(64);
        assertTrue(plaats65.getCode().equals("BEL") && plaats65.getName().equals("Belgium") && plaats65.getPopulation() == 10239000);
        assertTrue(result.get(result.size()-6).getCode().equals("FLK") );
    }

    /**
     * Test of getAantalLanden method, of class DataLayerJDBC.
     */
    @Test
    public void testGetAantalLanden() {
        System.out.println("getAantalLanden");
        DataLayerJDBC instance = dl;
        int expResult = 5;
        int result = instance.getAantalLanden();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotaleBevolking method, of class DataLayerJDBC.
     */
    @Test
    public void testGetTotaleBevolking() {
        System.out.println("getTotaleBevolking");
        DataLayerJDBC instance = dl;
        int expResult = 36350;
        int result = instance.getTotaleBevolking();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVerschilLevensverwachting method, of class DataLayerJDBC.
     */
    @Test
    public void testGetVerschilLevensverwachting() {
        System.out.println("getVerschilLevensverwachting");
        DataLayerJDBC instance = dl;
        int expResult = 46;
        int result = instance.getVerschilLevensverwachting();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOnafhankelijkeLanden method, of class DataLayerJDBC.
     */
    @Test
    public void testGetOnafhankelijkeLanden() {
        System.out.println("getOnafhankelijkeLanden");
        DataLayerJDBC instance = dl;
        int expResult = 1;
        List<Country> result = instance.getOnafhankelijkeLanden();
        assertEquals(expResult, result.size());
        Country c = result.get(0);
        assertTrue(c.getCode().equals("NLD") && c.getName().equals("Netherlands") && c.getContinent().equals("Europe") && c.getRegion().equals("Western Europe")
                    && c.getLocalName().equals("Nederland") && c.getCapital() == 5);
        assertTrue(Configuratie.QUERY_ONAFHANKELIJK.toLowerCase().contains("indep_year"));
    }
}
