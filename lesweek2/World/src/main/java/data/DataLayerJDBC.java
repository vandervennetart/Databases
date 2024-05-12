package data;

import logica.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evertjan.jacobs
 */
public class DataLayerJDBC {

    private final String dbUrl = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String pass = "Azerty123";
    private final Connection con;

    public DataLayerJDBC(String dbName) throws SQLException {
        createDatabase(dbName);
        this.con = getConnection(dbName);
        this.con.setAutoCommit(false);
    }

    private void createDatabase(String dbName) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //create database
            conn = getConnection("");
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+dbName;
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

        Connection conn = DriverManager.getConnection(dbUrl + dbName + "?allowMultiQueries=true&serverTimezone=UTC", login, pass);
        return conn;

    }

    public void closeConnection() throws SQLException {
        this.con.close();
    }

    public void dropTable() {
        execute(Configuratie.QUERY_VERWIJDER_LANDEN);
    }

    public void defineTable() {
        execute(Configuratie.QUERY_TABELDEFINITIE);
    }

    public void addData() {
        execute(Configuratie.DATA);
    }

    private void execute(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(data.DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_ALLE_LANDEN);
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String continent = rs.getString("continent");
                String region = rs.getString("region");
                float surfaceArea = rs.getFloat("surface_area");
                int indepYear = rs.getInt("indep_year");
                int population = rs.getInt("population");
                float lifeExpectancy = rs.getFloat("life_expectancy");
                float gnp = rs.getFloat("gnp");
                float gnpOld = rs.getFloat("gnp_old");
                String localName = rs.getString("local_name");
                String governmentForm = rs.getString("government_form");
                String headOfState = rs.getString("head_of_state");
                int capital = rs.getInt("capital");

                Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital);
                countries.add(country);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries;
    }

    public List<Country> getCountryAndHeadOfState() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_LANDEN_ANTARCTICA);
            while (rs.next()) {
                String name = rs.getString("name");
                String headofstate = rs.getString("head_of_state");
                Country country = new Country(name, headofstate);
                countries.add(country);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries;
    }

    public List<Country> getTop10VanEuropa() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_TOP10);
            while (rs.next()) {
                String name = rs.getString("name");
                float surfaceArea = rs.getFloat("surface_area");
                Country country = new Country(name, surfaceArea);
                countries.add(country);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries;
    }

    public List<String> getLandenEindigendOpLand() {
        List<String> countries = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_EINDIGT_OP_LAND);
            while (rs.next()) {
                String name = rs.getString("name");
                countries.add(name);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countries;
    }

    public List<Country> getInwonersPerLand() {
        List<Country> countries = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_LAND_PER_CONTINENT);
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String population = rs.getString("population");
                countries.add(new Country(code, name, Integer.parseInt(population)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return countries;
    }

    public int getAantalLanden() {
        return getValue(Configuratie.QUERY_AANTAL_LANDEN);
    }

    public int getTotaleBevolking() {
        return getValue(Configuratie.QUERY_TOTALE_BEVOLKING);
    }

    public int getVerschilLevensverwachting() {
        return getValue(Configuratie.QUERY_VERSCHIL_LEVENSVERWACHTING);
    }

    public List<Country> getOnafhankelijkeLanden() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Configuratie.QUERY_ONAFHANKELIJK);
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String continent = rs.getString("continent");
                String region = rs.getString("region");
                float surfaceArea = rs.getFloat("surface_area");
                int indepYear = rs.getInt("indep_year");
                int population = rs.getInt("population");
                float lifeExpectancy = rs.getFloat("life_expectancy");
                float gnp = rs.getFloat("gnp");
                float gnpOld = rs.getFloat("gnp_old");
                String localName = rs.getString("local_name");
                String governmentForm = rs.getString("government_form");
                String headOfState = rs.getString("head_of_state");
                int capital = rs.getInt("capital");

                Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital);
                countries.add(country);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries;
    }

    public int getValue(String query) {
        int aantal = -1;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                aantal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aantal;
    }

}
