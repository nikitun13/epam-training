package by.training.shapes.dao.mapper;

import by.training.shapes.entity.Ball;
import by.training.shapes.entity.VolumetricShape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The class {@code BallMapper} is a class
 * that implements {@link Mapper}.
 *
 * @author Nikita Romanov
 * @see Mapper
 */
public final class BallMapper implements Mapper<Ball> {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallMapper.class);
    /**
     * Regex for line separation.
     */
    private static final String SEPARATOR_REGEX = "\\s+";
    /**
     * Number of arguments for creating new entity.
     */
    private static final int VALID_NUMBER_OF_ARGS = 4;
    /**
     * Singleton instance.
     */
    private static BallMapper instance;

    private BallMapper() {
    }

    @Override
    public boolean isValidLine(final String line) {
        log.debug("received line: {}", line);
        if (line == null) {
            return false;
        }
        final String doubleValueRegex = "[+-]?\\d+(\\.\\d+)?";
        String[] args = line.split(SEPARATOR_REGEX);
        String arrayToString = Arrays.toString(args);
        log.debug("args: {}, size: {}", arrayToString, args.length);
        if (args.length != VALID_NUMBER_OF_ARGS) {
            return false;
        }
        for (String arg : args) {
            if (!arg.matches(doubleValueRegex)) {
                log.debug("arg {} doesn't match doubleValueRegex", arg);
                return false;
            }
        }
        if (args[0].startsWith("-")) {
            log.debug("radius is negative: {}", args[0]);
            return false;
        }
        return true;
    }

    @Override
    public Ball mapFrom(final String line) {
        String[] args = line.split(SEPARATOR_REGEX);
        double[] doubleArgs = Arrays.stream(args)
                .mapToDouble(Double::parseDouble)
                .toArray();
        String arrayToString = Arrays.toString(doubleArgs);
        log.debug("double args: {}, size: {}",
                arrayToString, doubleArgs.length
        );

        final int firstArg = 0;
        final int secondArg = 1;
        final int thirdArg = 2;
        final int fourthArg = 3;

        double radius = doubleArgs[firstArg];
        log.debug("radius: {}", radius);
        double x = doubleArgs[secondArg];
        log.debug("x: {}", x);
        double y = doubleArgs[thirdArg];
        log.debug("y: {}", y);
        double z = doubleArgs[fourthArg];
        log.debug("z: {}", z);

        VolumetricShape.Point centerPoint = new VolumetricShape.Point(x, y, z);
        log.debug("center point: {}", centerPoint);
        Ball result = new Ball(radius, centerPoint);
        log.debug("result: {}", result);
        return result;
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static BallMapper getInstance() {
        if (instance == null) {
            instance = new BallMapper();
        }
        return instance;
    }
}
