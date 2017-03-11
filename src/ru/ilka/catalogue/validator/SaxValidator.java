package ru.ilka.catalogue.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ilka.catalogue.handler.CatalogueErrorHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class SaxValidator {
    static Logger logger = Logger.getLogger(SaxValidator.class);

    private String fileName;
    private String schemaName;
    private DefaultHandler errorHandler;

    public SaxValidator(String fileName, String schemaName, DefaultHandler errorHandler) {
        this.fileName = fileName;
        this.schemaName = schemaName;
        this.errorHandler = errorHandler;
    }

    public boolean validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        Schema schema;
        try {
            schema = factory.newSchema(schemaLocation);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(fileName, errorHandler);
            return true;
        } catch (ParserConfigurationException e) {
            logger.error("SaxValidator err " + e);
        } catch (IOException e) {
            logger.fatal("SaxValidator io err " +e);
        } catch (SAXException e) {
            logger.error("SaxValidator err " +e);
        }
        return false;
    }

}
