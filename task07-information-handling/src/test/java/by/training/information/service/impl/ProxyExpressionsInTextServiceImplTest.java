package by.training.information.service.impl;

import by.training.information.entity.TextComponent;
import by.training.information.service.ServiceException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProxyExpressionsInTextServiceImplTest {

    private ProxyExpressionsInTextServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new ProxyExpressionsInTextServiceImpl();
    }

    @Test
    @Tag("proxyExpressions")
    void shouldReplaceExpressionsToProxy() throws ServiceException {
        TextComponent text = new TextServiceImpl().readTextFromFile("src/test/resources/text/text.txt");
        String collect = text.collect();
        collect = collect.replace("30>>>3", "3");
        collect = collect.replace("13<<2", "52");
        collect = collect.replace("5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", "5");
        collect = collect.replace("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", "78");
        collect = collect.replace("(8^5|1&2<<(2|5>>2&71))|1200", "1213");
        String expected = collect.replace("~6&9|(3&4)", "9");

        TextComponent component = service.proxyExpressions(text);
        String actual = component.collect();

        assertEquals(expected, actual);
    }

    @AfterAll
    void tearDown() {
        service = null;
    }
}
