package by.training.xml.dao.parser;

import by.training.xml.dao.DaoException;
import by.training.xml.dao.parser.impl.GemsDomBuilder;
import by.training.xml.dao.parser.impl.GemsSaxBuilder;
import by.training.xml.dao.parser.impl.GemsStaxBuilder;
import by.training.xml.entity.Gem;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.entity.VisualParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class GemsBuilderTest {

    private GemsBuilder builder;

    public GemsBuilderTest() {
    }

    @Factory(dataProvider = "builderImplementations")
    public GemsBuilderTest(GemsBuilder builder) {
        this.builder = builder;
    }

    @DataProvider(name = "builderImplementations")
    public Object[][] createBuilderImplementations() {
        return new Object[][]{
                {new GemsDomBuilder()},
                {new GemsSaxBuilder()},
                {new GemsStaxBuilder()}
        };
    }

    @DataProvider
    public static Object[][] validDataForBuild() {
        Gem diamond = new Gem(1, "Diamond", Gem.Preciousness.PRECIOUS, "Australia", new VisualParameters(VisualParameters.Color.WHITE, 40, 10), 5d, LocalDate.parse("2021-10-08"));
        Gem emerald = new SyntheticGem(16, "Emerald", Gem.Preciousness.PRECIOUS, "Switzerland", new VisualParameters(VisualParameters.Color.GREEN, 50, 6), 5d, LocalDate.parse("2021-10-13"), "SSEF Lab");
        Gem secondEmerald = new Gem(2, "Emerald", Gem.Preciousness.PRECIOUS, "Brazil", new VisualParameters(VisualParameters.Color.GREEN, 10, 5), 3.5, LocalDate.parse("2021-10-05"));
        return new Object[][]{
                {"data/valid/07-gems.xml", List.of(diamond)},
                {"data/valid/09-gems.xml", List.of(emerald)},
                {"data/valid/03-gems.xml", Collections.emptyList()},
                {"data/valid/10-gems.xml", List.of(diamond, emerald)},
                {"data/valid/11-gems.xml", List.of(diamond, emerald, secondEmerald)},
                {"data/valid/12-gems.xml", List.of(secondEmerald)},
        };
    }

    @DataProvider
    public static Iterator<Object[]> invalidDataForBuild() throws IOException {
        Path dir = Path.of("src", "test", "resources", "data", "invalid");
        String replace = "src\\test\\resources\\";
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
        return StreamSupport.stream(directoryStream.spliterator(), false)
                .map(path -> new Object[]{path.toString().replace(replace, "")})
                .iterator();
    }

    @Test(description = "test build method if valid data received",
            dataProvider = "validDataForBuild")
    public void testValidDataForBuild(String path, List<Gem> expected) throws DaoException {
        List<Gem> actual = builder.build(path);

        assertEquals(actual, expected, "must create list: " + expected);
    }

    @Test(description = "test build method if invalid data received",
            dataProvider = "invalidDataForBuild",
            expectedExceptions = DaoException.class)
    public void testInvalidDataForBuild(String path) throws DaoException {
        builder.build(path);
        fail("must throw " + DaoException.class.getName());
    }

    @Test(description = "test wrong path for build method",
            expectedExceptions = DaoException.class)
    public void testWrongPathForBuild() throws DaoException {
        builder.build("invalid/path.xml");
        fail("must throw " + DaoException.class.getName());
    }
}
