package ru.ilka.catalogue.publication;

import ru.ilka.catalogue.publication.type.ColorType;
import ru.ilka.catalogue.publication.type.GeographicalScope;
import ru.ilka.catalogue.publication.type.PeriodType;
import ru.ilka.catalogue.publication.type.TopicType;

import java.time.LocalDate;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class Newspaper extends SerialPublication {
    private GeographicalScope geographic;
    private int index;

    public Newspaper(String title, TopicType topic,PeriodType period, int pages,
                     ColorType color, String  issn, PublNumber number, GeographicalScope geographic, int index) {
        super(title, topic, period, pages, color, issn, number);
        this.geographic = geographic;
        this.index = index;
    }

    public Newspaper() {
        super();
    }

    public Newspaper(GeographicalScope geographic, int index) {
        this.geographic = geographic;
        this.index = index;
    }

    public Newspaper(SerialPublication serialPublication, GeographicalScope geographic, int index) {
        super(serialPublication);
        this.geographic = geographic;
        this.index = index;
    }

    public GeographicalScope getGeographic() {
        return geographic;
    }

    public void setGeographic(GeographicalScope geographic) {
        this.geographic = geographic;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Newspaper newspaper = (Newspaper) o;

        if (index != newspaper.index) return false;
        return geographic == newspaper.geographic;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + geographic.hashCode();
        result = 31 * result + index;
        return result;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Newspaper{" +
                "title " + getTitle() +
                ", geographic= " + geographic +
                ", index= " + index +
                '}';
    }
}
