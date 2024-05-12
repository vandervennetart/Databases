package data;

/**
 *
 * @author evertjan.jacobs
 */
public class Configuratie {

    public static String QUERY_MAAK_DATABASE = "CREATE DATABASE IF NOT EXISTS ";
    public static String QUERY_VERWIJDER_TABEL = "DROP TABLE IF EXISTS bezoekers;";
    public static String QUERY_ALLE_BEZOEKERS = "SELECT * FROM bezoekers;";
    public static String QUERY_POSTCODETYPE = "DESCRIBE bezoekers postcode;";

    //Stel de CREATE TABLE instructie op voor bovenstaande bezoekers tabel. Denk na over de kolomtypes die je kiest en de attributen die je zal gebruiken.
    //Zorg ervoor dat het datatype van de kolom postcode een string is. Later zullen we dit wijzigen naar een datatype integer.
    public static String QUERY_TABELDEFINITIE = "create table bezoekers (\n" +
            "\tid int not null auto_increment,\n" +
            "\tnaam varchar(10) not null,\n" +
            "    voornaam varchar(10) not null,\n" +
            "    straat_en_nummer varchar(25) not null,\n" +
            "    gemeente varchar(15) not null,\n" +
            "    postcode varchar(5) not null,\n" +
            "    land char(2) not null,\n" +
            "    primary key (id)\n" +
            ");";

    //Voeg alle data toe aan de tabel met INSERT instructies.
    public static String QUERY_DATA = "insert into bezoekers (id, naam, voornaam, straat_en_nummer, gemeente, postcode, land) \n" +
            "values\n" +
            "\t(null,\"Peters\",\"Jan\",\"Pierstraat 5\",\"Aartselaar\",\"2630\",\"BE\"),\n" +
            "\t(2,\"Degrootte\",\"Nick\",\"Palmboomstraat 4\",\"Diest\",\"3290\",\"BE\"),\n" +
            "\t(3,\"Deridder\",\"Eva\",\"Pelserstraat 17\",\"Maaseik\",\"3680\",\"BE\"),\n" +
            "\t(4,\"Wilms\",\"Frederik\",\"Molendreef 121\",\"Waasmunster\",\"9250\",\"BE\"),\n" +
            "\t(5,\"Moons\",\"Anja\",\"Rue des Brasseurs 13\",\"Mons\",\"7000\",\"BE\"),\n" +
            "\t(6,\"Van gogh\",\"Sara\",\"rue van gogh 12\",\"Paris\",\"75012\",\"FR\"),\n" +
            "\t(7,\"Richard\",\"Pierre\",\"Rue de Soleil\",\"Saint-Tropez\",\"83990\",\"FR\"),\n" +
            "\t(8,\"Romer\",\"Linda\",\"Ahornstrasse 7\",\"Berlin\",\"12624\",\"DE\"),\n" +
            "\t(9,\"Lindt\",\"Aurelie\",\"18 Rue Richmont\",\"Geneve\",\"1202\",\"CH\"),\n" +
            "\t(10,\"Borger\",\"Bjorn\",\"Langstrasse 500\",\"Zurich\",\"8005\",\"CH\"),\n" +
            "\t(11,\"Herman\",\"Fries\",\"Avenue de Cour 20\",\"Lausanne\",\"1007\",\"CH\"),\n" +
            "\t(12,\"Zoetemelk\",\"Joop\",\"Delftlandlaan 19\",\"Amsterdam\",\"1062\",\"NL\"),\n" +
            "\t(13,\"De Loenen\",\"Belinda\",\"Kleine Gracht 24\",\"Maastricht\",\"6222\",\"NL\"),\n" +
            "\t(14,\"Santos\",\"Carlos\",\"Plaza de Torros 6\",\"Granada\",\"18010\",\"ES\"),\n" +
            "\t(15,\"Witteveen\",\"Kees\",\"Hoefkade 9\",\"Den Haag\",\"2526\",\"NL\"),\n" +
            "\t(16,\"Contador\",\"Jorge\",\"Paseo de Sancha 17\",\"Malaga\",\"29016\",\"ES\"),\n" +
            "\t(17,\"Castello\",\"Isabella\",\"Frezzaria 128 San Marco\",\"Venetie\",\"30124\",\"IT\"),\n" +
            "\t(18,\"Corce\",\"Paolo\",\"Via del porto 6\",\"Bologna\",\"40122\",\"IT\");";

    //Wijzig de familienaam van de bezoeker met id 4 naar Wilmots.
    public static String QUERY_WIJZIG_FAMILIENAAM = "update bezoekers set naam = \"Wilmots\" where id = 4;";

    //Wijzig de straat van Sara Van gogh naar Rue van Gogh 12a.
    public static String QUERY_WIJZIG_STRAAT = "update bezoekers set straat_en_nummer = \"Rue van Gogh 12a\" where naam = \"Van gogh\" and voornaam = \"Sara\";";

    //Wijzig het datatype van de postcode naar integer (in dit geval int).
    public static String QUERY_WIJZIG_DATATYPE = "alter table bezoekers change postcode postcode int;";

    //Verwijder met een DELETE instructie alle bezoekers uit Duitsland.
    public static String QUERY_VERWIJDER_BEZOEKERS = "delete from bezoekers where land = \"DE\";";

    //Geef de naam, voornaam en het land van alle bezoekers in de tabel.
    public static String QUERY_BEPERKTE_INFO_ALLE_BEZOEKERS = "select naam, voornaam, land from bezoekers;";

    //Wat is de som van alle postcodes in de tabel bezoekers?
    public static String QUERY_SOM_POSTCODES = "select sum(postcode) from bezoekers;";

    //Geef het aantal bezoekers (dus een getal!) uit België.
    public static String QUERY_AANTAL_BEZOEKERS = "select count(*) from bezoekers where land = \"BE\";";

    //Geef het aantal bezoekers uit België waarvan de postcode begint met een 3.
    public static String QUERY_AANTAL_BEZOEKERS_MET_POSTCODE = "select count(postcode) from bezoekers where postcode like \"3%\" and land = \"BE\";";

    //Geef per land het aantal bezoekers. Orden de landen volgens het aantal bezoekers. Neem als alias "aantal" voor het aantal bezoekers. TIP: gebruik hiervoor GROUP BY!
    public static String QUERY_AANTAL_BEZOEKERS_PER_LAND = "select land, count(*) as aantal from bezoekers group by land order by aantal desc";
}
