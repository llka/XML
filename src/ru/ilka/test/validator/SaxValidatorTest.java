package ru.ilka.test.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ilka.catalogue.handler.CatalogueErrorHandler;
import ru.ilka.catalogue.validator.SaxValidator;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class SaxValidatorTest {
    private SaxValidator saxValidator;
    private CatalogueErrorHandler catalogueErrorHandler;

    @Before
    public void init() {
        catalogueErrorHandler = new CatalogueErrorHandler();
        saxValidator = new SaxValidator("data/Catalogue.xml", "data/Catalogue.xsd",catalogueErrorHandler);

    }

    @Test
    public void validateTest() {
        boolean actual =  saxValidator.validate();
        Assert.assertTrue(actual);
    }
}
