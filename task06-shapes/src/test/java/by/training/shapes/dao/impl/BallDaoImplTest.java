package by.training.shapes.dao.impl;

import by.training.shapes.dao.DaoException;
import by.training.shapes.entity.Ball;
import by.training.shapes.entity.VolumetricShape;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BallDaoImplTest {

    private BallDaoImpl dao;

    @BeforeAll
    void setUp() {
        dao = new BallDaoImpl();
    }

    @ParameterizedTest
    @MethodSource("positiveDataForGetAllFromFile")
    @Tag("getAllFromFile")
    void testPositiveDataForGetAllFromFile(Path path, List<Ball> expected) throws DaoException {
        List<Ball> actual = dao.getAllFromFile(path);
        assertEquals(expected, actual,
                () -> "must return %s from path: %s".formatted(expected, path)
        );
    }

    @Test
    @Tag("getAllFromFile")
    void testNegativeDataForGetAllFromFile() {
        Path path = Path.of("invalid", "path", "to", "data.txt");
        assertThrows(DaoException.class,
                () -> dao.getAllFromFile(path)
        );
    }

    @AfterAll
    void tearDown() {
        dao = null;
    }

    public Stream<Arguments> positiveDataForGetAllFromFile() throws IOException {
        Path parent = Path.of("src/test/resources/ball");
        Spliterator<Path> spliterator = Files.newDirectoryStream(parent).spliterator();
        List<Ball> list = List.of(
                new Ball(1d, new VolumetricShape.Point(2d, 3d, 4d)),
                new Ball(1.4, new VolumetricShape.Point(2.4, 3.5, 6.7)),
                new Ball(0.4, new VolumetricShape.Point(1d, 0.2, 3d)),
                new Ball(0d, new VolumetricShape.Point(0.1, 0.01, 0.001)),
                new Ball(0d, new VolumetricShape.Point(0d, 0d, 0d)),
                new Ball(1d, new VolumetricShape.Point(0d, 0d, 0d)),
                new Ball(43.543, new VolumetricShape.Point(-32.432, -0.4325, -4.2354532)),
                new Ball(4536, new VolumetricShape.Point(-234, 4325.3425, -3426.2345)),
                new Ball(1.542, new VolumetricShape.Point(2.342, -3.3245, 4.3255)),
                new Ball(1.542, new VolumetricShape.Point(-2.342, 3.3245, -4.3255)),
                new Ball(100, new VolumetricShape.Point(1000000000, -432532524, -0.32454352345243))
        );
        Queue<List<Ball>> ballQueue = new ArrayDeque<>(
                List.of(
                        Collections.emptyList(),
                        list,
                        list,
                        Collections.emptyList(),
                        Collections.emptyList(),
                        list,
                        list,
                        list
                )
        );
        return StreamSupport.stream(spliterator, false)
                .map(path -> Arguments.of(path, ballQueue.remove()));
    }
}
