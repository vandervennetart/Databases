import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import data.Datalaag;
import logica.Bezoeker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kristien.vanassche
 * @version 10-03-2020
 */
public class ConfiguratieTestDeel1 {
    private static Datalaag dl;

    @BeforeAll
    public static void setup() {
        try {
            String dbName = "festival";
            System.out.println("Create database " + dbName);
            dl = new Datalaag(dbName);
            if (dl != null) {
                System.out.println("Drop table bezoekers");
                dl.dropTabel();
                System.out.println("Q2. Define table bezoekers");
                dl.defineTabel();
                System.out.println("Q3. Fill table bezoekers");
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

    @Test
    public void testFilledTable() {
        System.out.println("Q0: testFilledTable");
        assertNotEquals(null, dl);
        assertEquals(18, dl.readBezoekers().size());
    }

    @Test
    public void testAantalBezoekers() {
        System.out.println("Q10: testAantalBezoekers");
        assertEquals(5, dl.getAantalBezoekers()); //van België
        assertEquals(18, dl.readBezoekers().size());
    }

    @Test
    public void testAantalBezoekersMetPostcode() {
        System.out.println("Q11: testAantalBezoekersMetPostcode");
        assertEquals(2, dl.getAantalBezoekersMetPostcode()); //van België (postcode start met 3)
        assertEquals(18, dl.readBezoekers().size());
    }

    @Test
    public void testSomPostcodes() {
        System.out.println("Q9: testSomPostcodes");
        assertEquals(334772, dl.getSomPostcodes());
    }

    @Test
    public void testWijzigFamilienaam() {
        System.out.println("Q4: testWijzigFamilienaam");
        Bezoeker res;

        res = (Bezoeker) dl.readBezoekers().stream().filter(f -> f.getId() == 4).collect(Collectors.toList()).get(0);
        assertEquals("Wilms", res.getNaam());
        dl.updateBezoeker();
        res = (Bezoeker) dl.readBezoekers().stream().filter(f -> f.getId() == 4).collect(Collectors.toList()).get(0);
        assertEquals("Wilmots", res.getNaam());
    }

    @Test
    public void testWijzigStraat() {
        System.out.println("Q5: testWijzigStraat");
        Bezoeker res;

        res = (Bezoeker) dl.readBezoekers().stream().filter(f -> f.getNaam().equals("Van gogh")).collect(Collectors.toList()).get(0);
        assertEquals("rue van gogh 12", res.getStraat());
        dl.updateStraat();
        res = (Bezoeker) dl.readBezoekers().stream().filter(f -> f.getNaam().equals("Van gogh")).collect(Collectors.toList()).get(0);
        assertEquals("Rue van Gogh 12a", res.getStraat());
    }

    @Test
    public void testGeefNaamVoornaamLand() {
        System.out.println("Q8: testGeefNaamVoornaamLand");

        List<Bezoeker> res;
        res = dl.readBezoekers();

        int i = (int) (Math.random() * res.size());
        String naam = res.get(i).getNaam();
        String straat = res.get(i).getStraat();

        assertNotEquals(null, straat);

        res = dl.readBezoekersInfo();
        assertEquals(naam, res.get(i).getNaam());
        assertEquals(null, res.get(i).getStraat());
    }

    @Test
    public void testGeefAantalPerLand() {
        System.out.println("Q12: testGeefAantalPerLand");
        LinkedHashMap<String, Integer> res = dl.readAantalBezoekersPerLand();
        assertEquals(7, res.size());

        assertEquals(5, res.get("BE"));
        assertEquals(3, res.get("CH"));
        assertEquals(1, res.get("DE"));
        assertEquals(2, res.get("ES"));
        assertEquals(2, res.get("FR"));
        assertEquals(2, res.get("IT"));
        assertEquals(3, res.get("NL"));

        //resultaat correct en in juiste volgorde?
        Object[] values = res.values().toArray();
        Object[] volgordeValues = new Object[]{5, 3, 3, 2, 2, 2, 1};
        assertArrayEquals(volgordeValues, values);

        Object[] keys = res.keySet().toArray();
        assertEquals("BE", keys[0]);
        assertTrue("CH-NL".contains((String) keys[1]));
        assertTrue("CH-NL".contains((String) keys[2]));
        assertTrue("FR-IT-ES".contains((String) keys[3]));
        assertTrue("FR-IT-ES".contains((String) keys[4]));
        assertTrue("FR-IT-ES".contains((String) keys[5]));
        assertEquals("DE", keys[6]);
    }
}
