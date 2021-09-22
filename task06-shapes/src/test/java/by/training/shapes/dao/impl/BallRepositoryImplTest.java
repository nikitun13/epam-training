package by.training.shapes.dao.impl;

import by.training.shapes.dao.specification.*;
import by.training.shapes.dao.storage.BallStorageImpl;
import by.training.shapes.entity.Ball;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.training.shapes.entity.VolumetricShape.Point;
import static org.junit.jupiter.api.Assertions.*;

class BallRepositoryImplTest {

    private final BallRepositoryImpl repository = new BallRepositoryImpl();
    private final BallStorageImpl storage = BallStorageImpl.getInstance();

    private final Ball FIRST_TEST_BALL = new Ball(100, 0.5, new Point(0d, 0d, 0d));
    private final Ball SECOND_TEST_BALL = new Ball(101, 43.34, new Point(-10d, 20d, -30d));
    private final Ball THIRD_TEST_BALL = new Ball(102, 50d, new Point(0.342d, -0.3423d, 432.452d));

    @Test
    @Tag("add")
    void addNewEntityToEmptyStorage() {
        Ball ball = new Ball(1.5, new Point(-34.5, 56.3, -0.123));
        repository.add(ball);
        List<Ball> expected = List.of(ball);
        List<Ball> actual = storage.getALlValues();
        assertEquals(expected, actual, "must add a new entity");
    }

    @Test
    @Tag("add")
    void addNewEntityToNotEmptyStorage() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        Ball ball = new Ball(1.5, new Point(-34.5, 56.3, -0.123));

        repository.add(ball);

        List<Ball> expected = List.of(FIRST_TEST_BALL, SECOND_TEST_BALL, THIRD_TEST_BALL, ball);
        List<Ball> actual = storage.getALlValues();
        assertTrue(actual.size() == expected.size()
                        && expected.containsAll(actual)
                        && actual.containsAll(expected),
                "must add a new entity");
    }

    @Test
    @Tag("addAll")
    void addAllToEmptyStorage() {
        Ball ball = new Ball(1.5, new Point(-34.5, 56.3, -0.123));
        List<Ball> entities = List.of(FIRST_TEST_BALL, SECOND_TEST_BALL, THIRD_TEST_BALL, ball);
        List<Ball> expected = List.copyOf(entities);

        repository.addAll(entities);

        List<Ball> actual = storage.getALlValues();
        assertTrue(actual.size() == expected.size()
                        && expected.containsAll(actual)
                        && actual.containsAll(expected),
                "must add new entities");
    }

    @Test
    @Tag("addAll")
    void addAllToNotEmptyStorage() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        Ball ball = new Ball(1.5, new Point(-34.5, 56.3, -0.123));
        List<Ball> entities = List.of(THIRD_TEST_BALL, ball);
        List<Ball> expected = List.of(FIRST_TEST_BALL, SECOND_TEST_BALL, THIRD_TEST_BALL, ball);

        repository.addAll(entities);

        List<Ball> actual = storage.getALlValues();
        assertTrue(actual.size() == expected.size()
                        && expected.containsAll(actual)
                        && actual.containsAll(expected),
                "must add new entities");
    }

    @Test
    @Tag("findById")
    void findByIdIfIdExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);

        Optional<Ball> optionalBall = repository.findById(SECOND_TEST_BALL.getId());
        Ball actual = optionalBall.orElseThrow();

        assertEquals(SECOND_TEST_BALL, actual, "must return ball associated with given id");
    }

    @Test
    @Tag("findById")
    void returnEmptyOptionalForFindByIdIfIdNotExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        int invalidId = 5;

        Optional<Ball> optionalBall = repository.findById(invalidId);

        assertTrue(optionalBall.isEmpty());
    }

    @Test
    @Tag("update")
    void updateIfEntityExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);

        THIRD_TEST_BALL.setRadius(1000d);

        boolean actual = repository.update(THIRD_TEST_BALL);

        assertTrue(actual, "entity must be updated");
    }

    @Test
    @Tag("update")
    void notUpdatedAndAddedIfEntityNotExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);

        THIRD_TEST_BALL.setRadius(1000d);

        boolean actual = repository.update(THIRD_TEST_BALL);

        assertFalse(actual, "entity mustn't be updated");
    }

    @Test
    @Tag("remove")
    void removeSuccessfullyIfEntityExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        List<Ball> expected = List.of(SECOND_TEST_BALL);

        repository.remove(FIRST_TEST_BALL);
        repository.remove(THIRD_TEST_BALL);
        List<Ball> actual = storage.getALlValues();

        assertEquals(expected, actual, "entities must be removed");
    }

    @Test
    @Tag("remove")
    void removeUnsuccessfullyIfEntityNotExists() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);

        boolean actual = repository.remove(SECOND_TEST_BALL);

        assertFalse(actual, "entity can't be removed if not added");
    }

    @Test
    @Tag("getAll")
    void getAll() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        List<Ball> expected = storage.getALlValues();

        List<Ball> actual = repository.getAll();

        assertTrue(actual.size() == expected.size()
                        && expected.containsAll(actual)
                        && actual.containsAll(expected),
                "must return same lists");
    }

    @Test
    @Tag("findBySpecification")
    void findBySpecificationIfSomeElementsMatch() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        Specification<Ball> findByRadiusBetweenBallSpecification
                = new FindByRadiusBetweenBallSpecification(40d, 50d);
        Specification<Ball> findByXIsGreaterThenBallSpecification
                = new FindByXIsGreaterThenBallSpecification(-15d);
        Specification<Ball> ballAndSpecification
                = new AndSpecification<>(findByRadiusBetweenBallSpecification, findByXIsGreaterThenBallSpecification);
        Specification<Ball> finalSpecification
                = new AndSpecification<>(ballAndSpecification, new SortByRadiusDescBallSpecification());
        List<Ball> expected = List.of(THIRD_TEST_BALL, SECOND_TEST_BALL);

        List<Ball> actual = repository.findBySpecification(finalSpecification);

        assertEquals(expected, actual, "must return elements by given specification");
    }

    @Test
    @Tag("findBySpecification")
    void returnEmptyListIFFindBySpecificationNotMatchesAnything() {
        storage.add(FIRST_TEST_BALL.getId(), FIRST_TEST_BALL);
        storage.add(SECOND_TEST_BALL.getId(), SECOND_TEST_BALL);
        storage.add(THIRD_TEST_BALL.getId(), THIRD_TEST_BALL);
        Specification<Ball> findByRadiusBetweenBallSpecification
                = new FindByRadiusBetweenBallSpecification(40d, 50d);
        Specification<Ball> findByXIsGreaterThenBallSpecification
                = new FindByXIsGreaterThenBallSpecification(5d);
        Specification<Ball> ballAndSpecification
                = new AndSpecification<>(findByRadiusBetweenBallSpecification, findByXIsGreaterThenBallSpecification);
        Specification<Ball> finalSpecification
                = new AndSpecification<>(ballAndSpecification, new SortByRadiusDescBallSpecification());
        List<Ball> expected = Collections.emptyList();

        List<Ball> actual = repository.findBySpecification(finalSpecification);

        assertEquals(expected, actual, "must return empty list");
    }

    @AfterEach
    void tearDown() {
        storage.removeAll();
    }
}
