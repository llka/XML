package ru.ilka.catalogue.publication;

import ru.ilka.catalogue.publication.type.*;

import java.time.LocalDate;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class Magazine extends SerialPublication {
    private Paper paper;
    private Cover cover;

    public Magazine(String title, TopicType topic, PeriodType period, int pages, ColorType color, String issn, PublNumber number, Paper paper, Cover cover) {
        super(title, topic, period, pages, color, issn, number);
        this.paper = paper;
        this.cover = cover;
    }

    public Magazine(Paper paper, Cover cover ) {
        this.paper = paper;
        this.cover = cover;
    }

    public Magazine() {
        super();
    }

    public Magazine(SerialPublication serialPublication, Paper paper, Cover cover) {
        super(serialPublication);
        this.paper = paper;
        this.cover = cover;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }


        Magazine magazine = (Magazine) o;

        if (paper != magazine.paper) return false;
        return cover == magazine.cover;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + paper.hashCode();
        result = 31 * result + cover.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Magazine{" +
                "paper=" + paper +
                ", cover=" + cover +
                '}';
    }
}
