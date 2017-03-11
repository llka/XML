package ru.ilka.catalogue.publication;

import ru.ilka.catalogue.publication.type.ColorType;
import ru.ilka.catalogue.publication.type.PeriodType;
import ru.ilka.catalogue.publication.type.TopicType;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class SerialPublication {
    private String title;
    private TopicType topic;
    private PeriodType period;
    private int pages;
    private ColorType color;
    private String issn; // International standard serial number
    private PublNumber number;

    public SerialPublication(String title, TopicType topic, PeriodType period, int pages, ColorType color, String issn, PublNumber number) {
        this.title = title;
        this.topic = topic;
        this.period = period;
        this.pages = pages;
        this.color = color;
        this.issn = issn;
        this.number = number;
    }

    public SerialPublication() {
        this.number = new PublNumber();
    }

    public SerialPublication(SerialPublication serialPublication) {
        this.title = serialPublication.title;
        this.topic = serialPublication.topic;
        this.period = serialPublication.period;
        this.pages = serialPublication.pages;
        this.color = serialPublication.color;
        this.issn = serialPublication.issn;
        this.number = serialPublication.number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicType getTopic() {
        return topic;
    }

    public void setTopic(TopicType topic) {
        this.topic = topic;
    }

    public PeriodType getPeriod() {
        return period;
    }

    public void setPeriod(PeriodType period) {
        this.period = period;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public PublNumber getNumber() {
        return number;
    }

    public void setNumber(PublNumber number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SerialPublication that = (SerialPublication) o;

        if (pages != that.pages) return false;
        if (issn != that.issn) return false;
        if (!title.equals(that.title)) return false;
        if (topic != that.topic) return false;
        if (period != that.period) return false;
        if (color != that.color) return false;
        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + period.hashCode();
        result = 31 * result + pages;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + number.hashCode();
        return result;
    }

    @Override
    public String
    toString() {
        return "SerialPublication{" +
                "topic=" + topic +
                ", title='" + title + '\'' +
                '}';
    }
}
