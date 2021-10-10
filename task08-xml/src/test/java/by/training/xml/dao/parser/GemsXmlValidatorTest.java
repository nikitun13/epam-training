package by.training.xml.dao.parser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GemsXmlValidatorTest {

    private final GemsXmlValidator validator = GemsXmlValidator.getInstance();

    @DataProvider(name = "validDataForIsValid")
    public static Iterator<Object[]> createValidDataForIsValid() throws IOException {
        Path dir = Path.of("src", "test", "resources", "data", "valid");
        String replace = "src\\test\\resources\\";
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
        return StreamSupport.stream(directoryStream.spliterator(), false)
                .map(path -> new Object[]{path.toString().replace(replace, "")})
                .iterator();

    }

    @DataProvider(name = "invalidDataForIsValid")
    public static Iterator<Object[]> createInvalidDataForIsValid() throws IOException {
        Path dir = Path.of("src", "test", "resources", "data", "invalid");
        String replace = "src\\test\\resources\\";
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
        return StreamSupport.stream(directoryStream.spliterator(), false)
                .map(path -> new Object[]{path.toString().replace(replace, "")})
                .iterator();
    }

    @Test(description = "test valid XMLs for isValid method",
            dataProvider = "validDataForIsValid")
    public void testValidDataForIsValid(String path) {
        boolean actual = validator.isValid(path);

        assertTrue(actual, "must be valid for XML: " + path);
    }

    @Test(description = "test invalid XMLs for isValid method",
            dataProvider = "invalidDataForIsValid")
    public void testInvalidDataForIsValid(String path) {
        boolean actual = validator.isValid(path);

        assertFalse(actual, "must be invalid for XML: " + path);
    }
}
