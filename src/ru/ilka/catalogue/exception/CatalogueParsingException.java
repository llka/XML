package ru.ilka.catalogue.exception;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class CatalogueParsingException extends Exception {
    public CatalogueParsingException() {
    }

    public CatalogueParsingException(String message) {
        super(message);
    }

    public CatalogueParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatalogueParsingException(Throwable cause) {
        super(cause);
    }
}
