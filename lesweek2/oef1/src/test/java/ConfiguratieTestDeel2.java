
import java.sql.SQLException;
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
public class ConfiguratieTestDeel2 {
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
    public void testWijzigType() {
        System.out.println("Q6: testWijzigType");
        assertFalse(dl.describe().contains("int"));
        dl.changeTabel();
        assertTrue(dl.describe().contains("int"));
    }

    @Test
    public void testVerwijder() {
        System.out.println("Q7: testVerwijder");
        List<Bezoeker> res;

        assertEquals(18, dl.readBezoekers().size());
        assertEquals(334772, dl.getSomPostcodes());

        res = dl.readBezoekers().stream().filter(f -> f.getLand().equals("DE")).collect(Collectors.toList());
        assertEquals(1, res.size());

        dl.deleteBezoekers();

        assertEquals(17, dl.readBezoekers().size());
        assertEquals(322148, dl.getSomPostcodes());

        res = dl.readBezoekers().stream().filter(f -> f.getLand().equals("DE")).collect(Collectors. toList());
        assertEquals(0, res.size());
    }
}
