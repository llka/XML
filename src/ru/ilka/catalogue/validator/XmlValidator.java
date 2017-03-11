package ru.ilka.catalogue.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ru.ilka.catalogue.handler.CatalogueErrorHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class XmlValidator {
    static Logger logger = Logger.getLogger(XmlValidator.class);

    private String fileName;
    private String schemaName;

    public XmlValidator(String filename, String schemaname) {
        this.fileName = filename;
        this.schemaName = schemaname;
    }

    public boolean validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        File schemaLocation = new File(schemaName);

        Schema schema;
        try {
            schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();

            Source source = new StreamSource(fileName);
            CatalogueErrorHandler catalogueErrorHandler = new CatalogueErrorHandler();
            validator.setErrorHandler(catalogueErrorHandler);
            validator.validate(source);
            logger.info(fileName + " is valid.");
            return true;
        } catch (SAXException e) {
            logger.error("validation of " + fileName + " is not valid", e);
        } catch (IOException e) {
            logger.error("wrong" + fileName, e);
        }
        logger.info(fileName + " is not valid.");
        return false;
    }
}
