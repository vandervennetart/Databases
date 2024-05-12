package logica;

import javax.swing.*;
import java.util.Objects;

public class Emoticon implements Comparable{
    private String code;
    private String naam;
    private ImageIcon afbeelding;

    public Emoticon(String naam, String code) {
        this.code = code;
        this.naam = naam;
    }
    public Emoticon(String naam, String code, ImageIcon afbeelding) {
        this(code, naam);
        this.afbeelding = afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getNaam() {
        return naam;
    }

    public String getCode() {
        return code;
    }

    public ImageIcon getAfbeelding() {
        return afbeelding;
    }

    @Override
    public String toString() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emoticon emoticon)) return false;

        return Objects.equals(naam, emoticon.naam);
    }



    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Emoticon a)) throw new IllegalArgumentException();
        return naam.compareTo(a.getNaam());
    }
}
