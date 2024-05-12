package presentatie;

import data.DataLayerJDBC;
import logica.Country;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * World: Gui
 *
 * @author evertjan.jacobs
 * @version 12/03/2021
 */
public class Gui {

    DataLayerJDBC dl;

    private JPanel pane;
    private JTable jTableWorld;
    private JButton jButtonToonAantalLanden;
    private JTextField jTextFieldAantalLanden;
    private JButton jButtonToonStaatshoofd;
    private JButton jButtonToonTop10;
    private JButton jButtonToonEindigendOpLand;
    private JButton jButtonToonTotaleBevolking;
    private JButton jButtonToonVerschilLevensverwachting;
    private JButton jButtonToonPerContinent;
    private JButton jButtonToonAfhankelijk;
    private JTextField jTextFieldTotaleBevolking;
    private JTextField jTextFieldVerschilLevensverwachting;

    public Gui() {
        initComponents();
        try {
            dl = new DataLayerJDBC("world");
            dl.dropTable();
            dl.defineTable();
            dl.addData();
            createHeaderCountries();
            List<Country> countries = dl.getCountries();

            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();
            model.setRowCount(0);

            for (Country country : countries) {
                //"code", "name", "continent", "region", "surfaceArea", "indepYear", "population", "lifeExpectancy", "gnp", "gnpOld", "localName", "governmentForm", "headOfState", "capital"
                String[] row = {country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getSurfaceArea() + "", country.getIndepYear() + "", country.getPopulation() + "", country.getLifeExpectancy() + "", country.getGnp() + "", country.getGnpOld() + "", country.getLocalName(), country.getGovernmentForm(), country.getHeadOfState(), country.getCapital() + ""};
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        jButtonToonAantalLanden.addActionListener(e -> {
            int aantalLanden = dl.getAantalLanden();
            jTextFieldAantalLanden.setText(Integer.toString(aantalLanden));
        });
        jButtonToonStaatshoofd.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();

            String[] header = {"land", "staatshoofd"};
            createHeader(model, header);

            List<Country> countriesAndHeadOfState = dl.getCountryAndHeadOfState();

            for (Country country : countriesAndHeadOfState) {
                String[] row = {country.getName(), country.getHeadOfState()};
                model.addRow(row);
            }
        });
        jButtonToonTop10.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();

            String[] header = {"naam", "oppervlakte"};
            createHeader(model, header);

            List<Country> top10Countries = dl.getTop10VanEuropa();
            model.setRowCount(0);

            for (Country country : top10Countries) {
                String[] row = {country.getName(), Float.toString(country.getSurfaceArea())};
                model.addRow(row);
            }
        });
        jButtonToonEindigendOpLand.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();

            String[] header = {"naam"};
            createHeader(model, header);

            List<String> countries = dl.getLandenEindigendOpLand();
            model.setRowCount(0);

            for (String country : countries) {
                String[] row = {country};
                model.addRow(row);
            }
        });
        jButtonToonTotaleBevolking.addActionListener(e -> {
            int totaleBevolking = dl.getTotaleBevolking();
            jTextFieldTotaleBevolking.setText(Integer.toString(totaleBevolking));
        });
        jButtonToonVerschilLevensverwachting.addActionListener(e -> {
            int verschilLevensverwachting = dl.getVerschilLevensverwachting();
            jTextFieldVerschilLevensverwachting.setText(Integer.toString(verschilLevensverwachting));
        });
        jButtonToonPerContinent.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();
            String[] header = {"code", "naam", "aantal"};
            createHeader(model, header);

            List<Country> inwonersPerLand = dl.getInwonersPerLand();
            model.setRowCount(0);

            for (Country country : inwonersPerLand) {
                String[] row = {country.getCode(), country.getName(), Integer.toString(country.getPopulation())};
                model.addRow(row);
            }
        });
        jButtonToonAfhankelijk.addActionListener(e -> {
            createHeaderCountries();
            List<Country> countries = dl.getOnafhankelijkeLanden();

            DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();
            model.setRowCount(0);

            for (Country country : countries) {
                //"code", "name", "continent", "region", "surfaceArea", "indepYear", "population", "lifeExpectancy", "gnp", "gnpOld", "localName", "governmentForm", "headOfState", "capital"
                String[] row = {country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getSurfaceArea() + "", country.getIndepYear() + "", country.getPopulation() + "", country.getLifeExpectancy() + "", country.getGnp() + "", country.getGnpOld() + "", country.getLocalName(), country.getGovernmentForm(), country.getHeadOfState(), country.getCapital() + ""};
                model.addRow(row);
            }            });
    }

    private void createHeader(DefaultTableModel model, String[] header) {
        model.setColumnCount(0);
        for (String naamKolom : header) {
            model.addColumn(naamKolom);
        }
        model.setRowCount(0);
    }

    private void createHeaderCountries() {
        DefaultTableModel model = (DefaultTableModel) jTableWorld.getModel();
        model.setColumnCount(0);

        String[] header = {"code", "name", "continent", "region", "surface_area", "indep_year", "population", "life_expectancy", "gnp", "gnp_old", "local_name", "government_form", "head_of_state", "capital"};

        for (String naamKolom : header) {
            model.addColumn(naamKolom);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Gui().pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
