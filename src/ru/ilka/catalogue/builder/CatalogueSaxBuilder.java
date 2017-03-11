package ru.ilka.catalogue.builder;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import ru.ilka.catalogue.handler.CatalogueHandler;
import ru.ilka.catalogue.publication.SerialPublication;

import java.io.IOException;
import java.util.List;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class CatalogueSaxBuilder {
    static Logger logger = Logger.getLogger(CatalogueSaxBuilder.class);

    private List<SerialPublication> catalogue;
    private CatalogueHandler catalogueHandler;
    private XMLReader reader;

    public CatalogueSaxBuilder()  {

        catalogueHandler = new CatalogueHandler();

        try {
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            logger.error("Error while creating XMLReader" + e);
        }
        reader.setContentHandler(catalogueHandler);
    }

    public List<SerialPublication> getCatalogue() {
        return catalogue;
    }

    public void buildCatalogue(String fileName) {
        try{
            reader.parse(fileName);
        } catch(SAXException e) {
            logger.error("sax parsing err " + e);
        } catch(IOException e) {
            logger.error("I/Î: " + e);
        }
        catalogue = catalogueHandler.getCatalogue();
    }
}
