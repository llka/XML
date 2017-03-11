package ru.ilka.catalogue.publication;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class PublNumber {
    private int overall;
    private int current;

    public PublNumber(int overall, int thisYear) {
        this.overall = overall;
        this.current = thisYear;
    }

    public PublNumber(){

    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int thisYear) {
        this.current = thisYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PublNumber number = (PublNumber) o;

        if (overall != number.overall) return false;
        return current == number.current;

    }

    @Override
    public int hashCode() {
        int result = overall;
        result = 31 * result + current;
        return result;
    }
}
