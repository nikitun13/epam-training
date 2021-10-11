package by.training.xml.service.impl;

import by.training.xml.entity.Gem;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.entity.VisualParameters;
import by.training.xml.service.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.testng.Assert.fail;

public class GemXmlServiceImplTest {

    private GemXmlServiceImpl service;

    @BeforeClass
    public void setUp() {
        service = new GemXmlServiceImpl();
    }

    @DataProvider
    public static Object[][] positiveDataForFindEntities() {
        Gem diamond = new Gem(1, "Diamond", Gem.Preciousness.PRECIOUS, "Australia", new VisualParameters(VisualParameters.Color.WHITE, 40, 10), 5d, LocalDate.parse("2021-10-08"));
        Gem emerald = new SyntheticGem(16, "Emerald", Gem.Preciousness.PRECIOUS, "Switzerland", new VisualParameters(VisualParameters.Color.GREEN, 50, 6), 5d, LocalDate.parse("2021-10-13"), "SSEF Lab");
        Gem secondEmerald = new Gem(2, "Emerald", Gem.Preciousness.PRECIOUS, "Brazil", new VisualParameters(VisualParameters.Color.GREEN, 10, 5), 3.5, LocalDate.parse("2021-10-05"));
        return new Object[][]{
                {"data/valid/07-gems.xml", "dom", List.of(diamond)},
                {"data/valid/09-gems.xml", "dom", List.of(emerald)},
                {"data/valid/03-gems.xml", "dom", Collections.emptyList()},
                {"data/valid/10-gems.xml", "dom", List.of(diamond, emerald)},
                {"data/valid/11-gems.xml", "dom", List.of(diamond, emerald, secondEmerald)},
                {"data/valid/12-gems.xml", "dom", List.of(secondEmerald)},
                {"data/valid/07-gems.xml", "sax", List.of(diamond)},
                {"data/valid/09-gems.xml", "sax", List.of(emerald)},
                {"data/valid/03-gems.xml", "sax", Collections.emptyList()},
                {"data/valid/10-gems.xml", "sax", List.of(diamond, emerald)},
                {"data/valid/11-gems.xml", "sax", List.of(diamond, emerald, secondEmerald)},
                {"data/valid/12-gems.xml", "sax", List.of(secondEmerald)},
                {"data/valid/07-gems.xml", "stax", List.of(diamond)},
                {"data/valid/09-gems.xml", "stax", List.of(emerald)},
                {"data/valid/03-gems.xml", "stax", Collections.emptyList()},
                {"data/valid/10-gems.xml", "stax", List.of(diamond, emerald)},
                {"data/valid/11-gems.xml", "stax", List.of(diamond, emerald, secondEmerald)},
                {"data/valid/12-gems.xml", "stax", List.of(secondEmerald)}
        };
    }

    @DataProvider
    public static Iterator<Object[]> negativeDataForFindEntities() throws IOException {
        Path dir = Path.of("src", "test", "resources", "data", "invalid");
        String replace = "src\\test\\resources\\";
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
        return StreamSupport.stream(directoryStream.spliterator(), false)
                .map(path -> new Object[]{path.toString().replace(replace, ""), "dom"})
                .iterator();
    }

    @DataProvider
    public static Object[][] negativeInputForFindEntities() {
        return new Object[][]{
                {"data/valid/07-gems.xml", null},
                {"data/valid/09-gems.xml", "fsaafsaf"},
                {"data/valid/03-gems.xml", "DOM"},
                {"data/valid/10-gems.xml", "doma"},
                {"data/valid/11-gems.xml", "domm"},
                {"data/valid/12-gems.xml", ""},
                {"data/valid/07-gems.xml", "saxx"},
                {"data/valid/09-gems.xml", "              "},
                {"data/valid/03-gems.xml", "x"},
                {"data/valid/10-gems.xml", "null"},
                {"data/valid/11-gems.xml", "st"},
                {"data/valid/12-gems.xml", "sat"},
                {"data/valid/07-gems.xml", "st"},
                {"data/valid/09-gems.xml", "ax"},
                {null, "stax"},
                {null, "dom"},
                {null, "sax"},
                {"null", "null"},
                {"null", "stax"},
                {"null", "dom"},
                {"null", "sax"},
                {null, null},
                {"invalid/path.xml", "sax"},
                {"invalid/path.xml", "dom"},
                {"invalid/path.xml", "stax"}
        };
    }

    @Test(description = "test positive data for findEntities method",
            dataProvider = "positiveDataForFindEntities")
    public void testPositiveDataForFindEntities(String path, String parserName, List<Gem> expected) throws ServiceException {
        List<Gem> actual = service.findEntities(path, parserName);

        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test negative data for findEntities method",
            dataProvider = "negativeDataForFindEntities",
            expectedExceptions = ServiceException.class)
    public void testNegativeDataForFindEntities(String path, String parserName) throws ServiceException {
        service.findEntities(path, parserName);
        fail("must throw " + ServiceException.class.getName());
    }

    @Test(description = "test negative input for findEntities method",
            dataProvider = "negativeInputForFindEntities",
            expectedExceptions = ServiceException.class)
    public void testNegativeInputForFindEntities(String path, String parserName) throws ServiceException {
        service.findEntities(path, parserName);
        fail("must throw " + ServiceException.class.getName());
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
