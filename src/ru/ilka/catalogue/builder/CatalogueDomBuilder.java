package ru.ilka.catalogue.builder;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.ilka.catalogue.handler.CatalogueEnum;
import ru.ilka.catalogue.publication.Magazine;
import ru.ilka.catalogue.publication.Newspaper;
import ru.ilka.catalogue.publication.SerialPublication;
import ru.ilka.catalogue.publication.type.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Here could be your advertisement +375(29)3880490
 *
 */
public class CatalogueDomBuilder {
    static Logger logger = Logger.getLogger(CatalogueDomBuilder.class);
    private static final int ATTR_BEST_LENGTH = 2;

    private List<SerialPublication> catalogue;
    private DocumentBuilder documentBuilder;

    public CatalogueDomBuilder() {
        catalogue = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.fatal(e);
        }
    }

    public List<SerialPublication> getCatalogue() {
        return catalogue;
    }

    public void buildCatalogue(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element rootElement = document.getDocumentElement();

            NodeList newspaperList = rootElement.getElementsByTagName(CatalogueEnum.NEWSPAPER.getValue());
            NodeList magazineList = rootElement.getElementsByTagName(CatalogueEnum.MAGAZINE.getValue());
            Element newspaperElement, magazineElement;
            for (int i = 0; i < newspaperList.getLength(); i++) {
                newspaperElement = (Element) newspaperList.item(i);
                catalogue.add(buildNewspaper(newspaperElement));
            }

            for (int i = 0; i < magazineList.getLength(); i++) {
                magazineElement = (Element) magazineList.item(i);
                catalogue.add(buildMagazine(magazineElement));
            }
        } catch (IOException e) {
            logger.fatal("",e);
        } catch (SAXException e) {
            logger.error("Error in document parsing", e);
        }
    }

    public Newspaper buildNewspaper(Element publicationElement)  {
        Newspaper newspaper = (Newspaper) buildPublication(new Newspaper(), publicationElement);
        newspaper.setGeographic(GeographicalScope.valueOf(getElementValue(publicationElement, CatalogueEnum.GEOGRAPHIC.getValue()).toUpperCase()));
        newspaper.setIndex(Integer.parseInt(getElementValue(publicationElement, CatalogueEnum.INDEX.getValue())));
        return newspaper;
    }

    public Magazine buildMagazine(Element pulicationElement) {
        Magazine magazine = (Magazine) buildPublication(new Magazine(), pulicationElement);
        magazine.setPaper(Paper.valueOf(getElementValue(pulicationElement, CatalogueEnum.PAPER.getValue()).toUpperCase()));
        magazine.setCover(Cover.valueOf(getElementValue(pulicationElement, CatalogueEnum.COVER.getValue()).toUpperCase()));
        return magazine;
    }

    public SerialPublication buildPublication(SerialPublication publication, Element publicationElement) {
        publication.setTitle(publicationElement.getAttribute(CatalogueEnum.TITLE.getValue()));

        if(publicationElement.getAttributes().getLength() == ATTR_BEST_LENGTH){
            publication.setTopic(TopicType.valueOf(publicationElement.getAttribute(CatalogueEnum.TOPIC.getValue()).toUpperCase()));
        }else {
            publication.setTopic(TopicType.SPORTS);
        }

        publication.setPeriod(PeriodType.valueOf(getElementValue(publicationElement, CatalogueEnum.PERIOD.getValue()).toUpperCase()));
        publication.setPages(Integer.parseInt(getElementValue(publicationElement, CatalogueEnum.PAGES.getValue())));
        publication.setColor(ColorType.valueOf(getElementValue(publicationElement, CatalogueEnum.COLOR.getValue()).toUpperCase()));
        publication.setIssn(getElementValue(publicationElement, CatalogueEnum.ISSN.getValue()));

        Element number = (Element) publicationElement.getElementsByTagName(CatalogueEnum.NUMBER.getValue()).item(0);
        publication.getNumber().setOverall(Integer.parseInt(getElementValue(number, CatalogueEnum.OVERALL.getValue())));
        publication.getNumber().setCurrent(Integer.parseInt(getElementValue(number, CatalogueEnum.CURRENT.getValue())));

        return publication;
    }

    private static String getElementValue(Element parentElement, String tagName) {
        return parentElement.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
