package ru.ilka.test.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ilka.catalogue.validator.XmlValidator;

/**
 * Here could be your advertisement +375(29)3880490
 */
public class XmlValidatorTest {
    private XmlValidator xmlValidator;
    boolean expected;

    @Before
    public void init() {
        xmlValidator = new XmlValidator("data/Catalogue.xml", "data/Catalogue.xsd");
        expected = true;
    }

    @Test
    public void validateTest() {
        boolean actual =  xmlValidator.validate();
        Assert.assertEquals(expected, actual);
    }
}
