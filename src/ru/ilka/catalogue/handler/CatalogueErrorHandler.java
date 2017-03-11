package ru.ilka.catalogue.handler;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class CatalogueErrorHandler extends DefaultHandler {
    static Logger logger = Logger.getLogger(CatalogueErrorHandler.class);

    public CatalogueErrorHandler(){
    }

    public void warning(SAXParseException e) {
        logger.warn(getLineAddress(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        logger.error(getLineAddress(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        logger.fatal(getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

}
