package ru.ilka.catalogue.builder;

import org.apache.log4j.Logger;
import ru.ilka.catalogue.exception.CatalogueParsingException;
import ru.ilka.catalogue.handler.CatalogueEnum;
import ru.ilka.catalogue.publication.Magazine;
import ru.ilka.catalogue.publication.Newspaper;
import ru.ilka.catalogue.publication.SerialPublication;
import ru.ilka.catalogue.publication.type.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class CatalogueStaxBuilder {
    static Logger logger = Logger.getLogger(CatalogueStaxBuilder.class);
    private static final int ATTR_BEST_LENGTH = 2;

    private List<SerialPublication> catalogue;
    private XMLInputFactory inputFactory;

    public CatalogueStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        catalogue = new ArrayList<>();
    }

    public List<SerialPublication> getCatalogue(){
        return catalogue;
    }

    public void buildCatalogue(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    logger.debug("name = " + name );
                    if (CatalogueEnum.NEWSPAPER.getValue().equals(name)) {
                        SerialPublication publication = buildPublication(new Newspaper(), reader);
                        catalogue.add(publication);
                    } else if (CatalogueEnum.MAGAZINE.getValue().equals(name)) {
                        SerialPublication publication = buildPublication(new Magazine(), reader);
                        catalogue.add(publication);
                    }
                }
            }
        } catch (CatalogueParsingException e) {
            logger.warn(e);
        } catch (XMLStreamException e) {
            logger.error("", e);
        } catch (FileNotFoundException e) {
            logger.fatal(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.fatal("", e);
            }
        }
    }

    private SerialPublication buildPublication(SerialPublication publication, XMLStreamReader reader) throws XMLStreamException, CatalogueParsingException {
        String name;

        while (reader.hasNext()) {
            int type = reader.getEventType();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    logger.debug("name START_ELEMENT = " + name);
                    switch (CatalogueEnum.valueOf(name.toUpperCase())) {
                        case NEWSPAPER:
                        case MAGAZINE:
                            publication.setTitle(reader.getAttributeValue(null, CatalogueEnum.TITLE.getValue()));
                            if(reader.getAttributeCount() == ATTR_BEST_LENGTH){
                                publication.setTopic(TopicType.valueOf(reader.getAttributeValue(null,CatalogueEnum.TOPIC.getValue()).toUpperCase()));
                            }else {
                                publication.setTopic(TopicType.SPORTS);
                            }
                            break;
                        case PERIOD:
                            publication.setPeriod(PeriodType.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case PAGES:
                            publication.setPages(Integer.parseInt(getElementValue(reader)));
                            break;
                        case COLOR:
                            publication.setColor(ColorType.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case ISSN:
                            publication.setIssn(getElementValue(reader));
                            break;
                        case NUMBER:
                            break;
                        case OVERALL:
                            publication.getNumber().setOverall(Integer.parseInt(getElementValue(reader)));
                            break;
                        case CURRENT:
                            publication.getNumber().setCurrent(Integer.parseInt(getElementValue(reader)));
                            break;
                        case GEOGRAPHIC:
                            ((Newspaper) publication).setGeographic(GeographicalScope.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case INDEX:
                            ((Newspaper) publication).setIndex(Integer.parseInt(getElementValue(reader)));
                            break;
                        case PAPER:
                            ((Magazine) publication).setPaper(Paper.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case COVER:
                            ((Magazine) publication).setCover(Cover.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        default:
                            throw new CatalogueParsingException("Tag was not recognized");
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(CatalogueEnum.valueOf(name.toUpperCase()) == CatalogueEnum.NEWSPAPER
                            || CatalogueEnum.valueOf(name.toUpperCase()) == CatalogueEnum.MAGAZINE) {
                        return publication;
                    }
                    break;
            }
            reader.next();
        }
        throw new XMLStreamException("Unknown element");
    }

    private String getElementValue(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
