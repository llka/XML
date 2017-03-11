package ru.ilka.catalogue.handler;

/**
 * Here could be your advertisement +375(29)3880490
 */
public enum CatalogueEnum {
    PAPERS("papers"),
    NEWSPAPER("newspaper"),
    MAGAZINE("magazine"),
    TITLE("title"),
    TOPIC("topic"),
    PERIOD("period"),
    PAGES("pages"),
    COLOR("color"),
    ISSN("issn"),
    NUMBER("number"),
    OVERALL("overall"),
    CURRENT("current"),
    GEOGRAPHIC("geographic"),
    INDEX("index"),
    PAPER("paper"),
    COVER("cover"),
    EMPTY_TAG("");

    private String value;

    private CatalogueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
