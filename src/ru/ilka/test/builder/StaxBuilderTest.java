package ru.ilka.test.builder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ilka.catalogue.builder.CatalogueStaxBuilder;
import ru.ilka.catalogue.publication.*;
import ru.ilka.catalogue.publication.type.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class StaxBuilderTest {
    private List<SerialPublication> expectedPublications;
    private List<SerialPublication> actualPublications;
    private ArrayList<String> expectedTitles;
    private ArrayList<String> actualTitles;
    private ArrayList<TopicType> expectedTopics;
    private ArrayList<TopicType> actualTopics;

    @Before
    public void init() {
        PublNumber number = new PublNumber(1,1);
        expectedPublications = new ArrayList<>();
        expectedPublications.add(new Newspaper("1",TopicType.WILDLIFE, PeriodType.DAILY,1, ColorType.COLORED,
                "O111-1111",number, GeographicalScope.INTERNATIONAL,111111));
        expectedPublications.add(new Magazine("2", TopicType.SPORTS,PeriodType.MONTHLY, 1, ColorType.COLORED,
                "O111-2222", number, Paper.GLOSSY, Cover.HARDBACK));
        expectedPublications.add(new Magazine("3", TopicType.SPORTS,PeriodType.MONTHLY, 1, ColorType.COLORED,
                "O111-3333", number, Paper.GLOSSY, Cover.HARDBACK));
        expectedTitles = new ArrayList<>();
        expectedPublications.forEach(publication -> expectedTitles.add(publication.getTitle()));
        expectedTopics = new ArrayList<>();
        expectedPublications.forEach(publication -> expectedTopics.add(publication.getTopic()));
        actualTitles = new ArrayList<>();
        actualTopics = new ArrayList<>();
    }

    @Test
    public void testStaxParserFirsAttr() {
        CatalogueStaxBuilder catalogueStaxBuilder = new CatalogueStaxBuilder();
        catalogueStaxBuilder.buildCatalogue("data/test.xml");
        actualPublications = catalogueStaxBuilder.getCatalogue();
        actualPublications.forEach(publication -> actualTitles.add(publication.getTitle()));

        Assert.assertEquals(expectedTitles, actualTitles);
    }

    @Test
    public void testStaxParserSecondAttr() {
        CatalogueStaxBuilder catalogueStaxBuilder = new CatalogueStaxBuilder();
        catalogueStaxBuilder.buildCatalogue("data/test.xml");
        actualPublications = catalogueStaxBuilder.getCatalogue();
        actualPublications.forEach(publication -> actualTopics.add(publication.getTopic()));

        Assert.assertEquals(expectedTopics, actualTopics);
    }

    @Test
    public void testStaxParser() {
        CatalogueStaxBuilder catalogueStaxBuilder = new CatalogueStaxBuilder();
        catalogueStaxBuilder.buildCatalogue("data/test.xml");
        actualPublications = catalogueStaxBuilder.getCatalogue();
        Assert.assertEquals(expectedPublications, actualPublications);
    }
}
