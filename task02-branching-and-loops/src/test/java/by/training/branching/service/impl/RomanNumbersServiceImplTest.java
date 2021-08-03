package by.training.branching.service.impl;

import by.training.branching.entity.RomanInteger;
import by.training.branching.service.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class RomanNumbersServiceImplTest {

    private RomanNumbersServiceImpl service;

    @DataProvider(name = "positiveDataForFromRoman")
    public static Iterator<Object[]> createPositiveDataForFromRoman() throws IOException {
        Path path = Path.of("src", "test", "java", "resources", "inputDataForRomanNumbersServiceTest.txt");
        return Files.lines(path)
                .map(line -> line.split("\\s+"))
                .map(array -> new Object[]{new RomanInteger(array[1]), Integer.parseInt(array[0])})
                .iterator();
    }

    @DataProvider(name = "negativeDataForFromRoman")
    public static Object[][] createNegativeDataForFromRoman() {
        return new Object[][]{
                {new RomanInteger("")},
                {new RomanInteger(null)},
                {new RomanInteger("null")},
                {new RomanInteger("10")},
                {new RomanInteger("17")},
                {new RomanInteger("1")},
                {new RomanInteger("Roman Number")},
                {new RomanInteger("Number")},
                {new RomanInteger("XXXX")},
                {new RomanInteger("IIII")},
                {new RomanInteger("IIV")},
                {new RomanInteger("IIIV")},
                {new RomanInteger("MMMM")},
                {new RomanInteger("VMMM")},
                {new RomanInteger("MMXM")},
                {new RomanInteger("VVI")},
                {new RomanInteger("XVVI")},
                {new RomanInteger("XV4")},
                {new RomanInteger("2V")},
                {new RomanInteger("V1I")},
                {new RomanInteger("KV")},
                {new RomanInteger("MK")},
                {new RomanInteger("MDXVIIII")},
                {new RomanInteger("MDXXVVIII")},
                {new RomanInteger("MMMDD")},
                {new RomanInteger("MMMMDCL")},
                {new RomanInteger("CCCC")},
                {new RomanInteger("FFF")},
                {new RomanInteger("x")},
                {new RomanInteger("i")},
                {new RomanInteger("m")},
                {new RomanInteger("v")},
                {new RomanInteger("c")},
                {new RomanInteger("d")},
                {new RomanInteger("xi")},
                {new RomanInteger("xvi")},
                {new RomanInteger("xxx")},
                {new RomanInteger("mmm")},
                {new RomanInteger("mmmm")},
                {new RomanInteger("mmmcmxcix")},
                {new RomanInteger("mmMcmXcIx")},
                {new RomanInteger("MMMcmXcIx")},
                {new RomanInteger("MMMCMXCIx")},
                {new RomanInteger("MMMCmXCIX")},
                {new RomanInteger("MMmCMXCIX")},
                {new RomanInteger("MMmCMXcIx")}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new RomanNumbersServiceImpl();
    }

    @Test(description = "test positive scenario fromRoman method",
            dataProvider = "positiveDataForFromRoman")
    public void testPositiveScenarioFromRoman(RomanInteger romanNumber, int expected) {
        int actual = service.fromRoman(romanNumber);
        assertEquals(actual, expected,
                String.format("Roman %s is arabic %d", romanNumber, expected)
        );
    }

    @Test(description = "test negative scenario fromRoman method",
            dataProvider = "negativeDataForFromRoman",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFromRoman(RomanInteger romanNumber) {
        service.fromRoman(romanNumber);
        fail(String.format("Must throw %s for invalid roman number %s",
                        ServiceException.class.getName(), romanNumber
                )
        );
    }

    @Test(description = "test null scenario fromRoman method",
            expectedExceptions = NullPointerException.class)
    public void testNullScenarioFromRoman() {
        service.fromRoman(null);
        fail(String.format("Must throw %s for null on input",
                        NullPointerException.class.getName()
                )
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
