package data;

import logica.Bezoeker;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evertjan.jacobs
 */
public class Datalaag {

    private static final String LOGIN = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String PASS = "Azerty123";
    private final Connection CON;


    public Datalaag(String dbName) throws SQLException {
        createDatabase(dbName);
        this.CON = getConnection(dbName);
        this.CON.setAutoCommit(false);
    }

    private void createDatabase(String dbName) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //create database
            conn = getConnection("");
            stmt = conn.createStatement();
            String sql = Configuratie.QUERY_MAAK_DATABASE + dbName;
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private Connection getConnection(String dbName) throws SQLException {
        return DriverManager.getConnection(DB_URL + dbName+ "?allowMultiQueries=true&serverTimezone=UTC", LOGIN, PASS);
    }

    public void closeConnection() throws SQLException {
        this.CON.close();
    }

    public void dropTabel() {
        execute(Configuratie.QUERY_VERWIJDER_TABEL);
    }

    public void defineTabel() {
        execute(Configuratie.QUERY_TABELDEFINITIE);
    }

    public void changeTabel() {
        execute(Configuratie.QUERY_WIJZIG_DATATYPE);
    }

    public void addData() {
        execute(Configuratie.QUERY_DATA);
    }

    public void updateBezoeker() {
        execute(Configuratie.QUERY_WIJZIG_FAMILIENAAM);
    }

    public void updateStraat() {
        execute(Configuratie.QUERY_WIJZIG_STRAAT);
    }

    public void deleteBezoekers() {
        execute(Configuratie.QUERY_VERWIJDER_BEZOEKERS);
    }

    public int getSomPostcodes() {
        int som = 0;
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_SOM_POSTCODES);
            if (rs.next()) {
                som = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return som;
    }

    public int getAantalBezoekers() {
        int aantal = 0;
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_AANTAL_BEZOEKERS);
            if (rs.next()) {
                aantal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aantal;
    }

    public int getAantalBezoekersMetPostcode() {
        int aantal = 0;
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_AANTAL_BEZOEKERS_MET_POSTCODE);
            if (rs.next()) {
                aantal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aantal;
    }

    public List<Bezoeker> readBezoekers() {
        List<Bezoeker> bezoekers = new ArrayList<>();
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_ALLE_BEZOEKERS);
            while (rs.next()) {
                int bezoekerId = rs.getInt("id");
                String naam = rs.getString("naam");
                String voornaam = rs.getString("voornaam");
                String straat = rs.getString("straat_en_nummer");
                String gemeente = rs.getString("gemeente");
                String postcode = rs.getString("postcode");
                String land = rs.getString("land");
                Bezoeker bezoeker = new Bezoeker(bezoekerId, naam, voornaam, straat, gemeente, postcode, land);
                bezoekers.add(bezoeker);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bezoekers;
    }

    public List<Bezoeker> readBezoekersInfo() {
        List<Bezoeker> bezoekers = new ArrayList<>();
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_BEPERKTE_INFO_ALLE_BEZOEKERS);
            while (rs.next()) {
                String naam = rs.getString("naam");
                String voornaam = rs.getString("voornaam");
                String land = rs.getString("land");
                Bezoeker bezoeker = new Bezoeker(naam, voornaam, land);
                bezoekers.add(bezoeker);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bezoekers;
    }

    public LinkedHashMap<String, Integer> readAantalBezoekersPerLand() {
        LinkedHashMap<String, Integer> bezoekerPerLand = new LinkedHashMap<>();
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_AANTAL_BEZOEKERS_PER_LAND);
            while (rs.next()) {
                String naam = rs.getString("land");
                int aantal = rs.getInt("aantal");
                bezoekerPerLand.put(naam, aantal);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bezoekerPerLand;
    }

    public String describe() {
        String description = "";
        try {
            Statement stmt = CON.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_POSTCODETYPE);
            if (rs.next()) {
                description = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return description;
    }

    private void execute(String query) {
        try {
            Statement stmt = CON.createStatement();
            stmt.executeUpdate(query);
            CON.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Datalaag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
