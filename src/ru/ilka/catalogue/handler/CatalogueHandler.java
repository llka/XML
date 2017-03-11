package ru.ilka.catalogue.handler;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ilka.catalogue.publication.Magazine;
import ru.ilka.catalogue.publication.Newspaper;
import ru.ilka.catalogue.publication.SerialPublication;
import ru.ilka.catalogue.publication.type.*;

import java.util.*;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class CatalogueHandler extends DefaultHandler {
    static Logger logger = Logger.getLogger(CatalogueHandler.class);
    private static final int ATTR_BEST_LENGTH = 2;
    static final int TITLE_POSITION = 0;

    private List<SerialPublication> catalogue;
    private SerialPublication current;
    private CatalogueEnum currentEnum = CatalogueEnum.EMPTY_TAG;

    public CatalogueHandler() {
        catalogue = new ArrayList<>();
    }

    public List<SerialPublication> getCatalogue() {
        return catalogue;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        CatalogueEnum characteristic = CatalogueEnum.valueOf(localName.toUpperCase());
        switch (characteristic) {
            case NEWSPAPER:
                logger.debug("newspaper");
                current = new Newspaper();
                current.setTitle(attrs.getValue(TITLE_POSITION));
                if(attrs.getLength() == ATTR_BEST_LENGTH) {
                    current.setTopic(TopicType.valueOf(attrs.getValue(CatalogueEnum.TOPIC.getValue())));
                    logger.debug("newspaper topic = " + TopicType.valueOf(attrs.getValue(CatalogueEnum.TOPIC.getValue())));
                }else {
                    current.setTopic(TopicType.SPORTS);
                }
                break;
            case MAGAZINE:
                current = new Magazine();
                logger.debug("magazine");
                current.setTitle(attrs.getValue(TITLE_POSITION));
                if(attrs.getLength() == ATTR_BEST_LENGTH) {
                    current.setTopic(TopicType.valueOf(attrs.getValue(CatalogueEnum.TOPIC.getValue())));
                    logger.debug("magazine topic = " + TopicType.valueOf(attrs.getValue(CatalogueEnum.TOPIC.getValue())));
                }
                else {
                    current.setTopic(TopicType.SPORTS);
                }
                break;
            case PAPERS:
            case NUMBER:
                currentEnum = CatalogueEnum.EMPTY_TAG;
                break;
            default:
                CatalogueEnum temp = CatalogueEnum.valueOf(localName.toUpperCase());
                //if(withText.contains(temp)) {
                    currentEnum = temp;
                //}
                logger.debug("currentEnum = temp = " + currentEnum);

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if("newspaper".equals(localName) || "magazine".equals(localName)) {
            catalogue.add(current);
            logger.debug(current);
        }
        currentEnum = CatalogueEnum.EMPTY_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        logger.debug("currentEnum = " + currentEnum.getValue());
        if(currentEnum != CatalogueEnum.EMPTY_TAG) {
            switch(currentEnum) {
                case PERIOD:
                    logger.debug("period");
                    current.setPeriod(PeriodType.valueOf(s.toUpperCase()));
                    break;
                case PAGES:
                    current.setPages(Integer.parseInt(s));
                    break;
                case COLOR:
                    current.setColor(ColorType.valueOf(s.toUpperCase()));
                    break;
                case ISSN:
                    current.setIssn(s);
                    break;
                case OVERALL:
                    current.getNumber().setOverall(Integer.parseInt(s));
                    break;
                case CURRENT:
                    current.getNumber().setCurrent(Integer.parseInt(s));
                    break;
                case GEOGRAPHIC:
                    ((Newspaper) current).setGeographic(GeographicalScope.valueOf(s.toUpperCase()));
                    break;
                case INDEX:
                    ((Newspaper) current).setIndex(Integer.parseInt(s));
                    break;
                case PAPER:
                    ((Magazine) current).setPaper(Paper.valueOf(s.toUpperCase()));
                    break;
                case COVER:
                    ((Magazine) current).setCover(Cover.valueOf(s.toUpperCase()));
                    break;
                default:
                    throw new SAXException("Tag was not recognized");
            }
        }
        //currentEnum = CatalogueEnum.EMPTY_TAG;
    }
}
