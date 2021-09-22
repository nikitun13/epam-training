package by.training.shapes.controller.command.impl;

import by.training.shapes.controller.command.Command;
import by.training.shapes.controller.command.result.CommandResult;
import by.training.shapes.controller.command.result.CommandStatus;
import by.training.shapes.dao.specification.AndSpecification;
import by.training.shapes.dao.specification.FindByRadiusBetweenBallSpecification;
import by.training.shapes.dao.specification.SortByRadiusDescBallSpecification;
import by.training.shapes.entity.Ball;
import by.training.shapes.entity.BallRegistrar;
import by.training.shapes.entity.Plane;
import by.training.shapes.entity.VolumetricShape.Point;
import by.training.shapes.service.*;
import by.training.shapes.view.View;
import by.training.shapes.view.ViewFactory;
import by.training.shapes.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The class {@code BallCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'ball' is received.
 * This command provides interaction with {@link Ball}.
 *
 * @author Nikita Romanov
 */
public class BallCommand implements Command {

    private static final Logger log =
            LogManager.getLogger(BallCommand.class);
    private static final String INT_REGEX = "[-+]?\\d+";
    private static final String DOUBLE_VALUE_REGEX = "[+-]?\\d+(\\.\\d+)?";
    private static final String INPUT_ICON = ">";
    private static final String BALL_INVALID_INPUT = "ball.invalidInput";
    private static final String ERROR = "error.error";

    private final View view = ViewFactory.getInstance().getView();
    private final CommandResult positiveResult
            = new CommandResult(CommandStatus.OK,
            TextManager.getText("ball.done"));
    private final CommandResult negativeResult
            = new CommandResult(CommandStatus.INVALID_NUMBER_OF_PARAMETERS);
    private final RegistrarService<Ball, BallRegistrar> ballRegistrarService;
    private final BallCalculatorService ballCalculatorService;
    private final BallConditionCheckerService ballConditionCheckerService;
    private final EntityService<Ball> ballService;
    private boolean enable;


    public BallCommand() {
        ServiceFactory factory = ServiceFactory.getInstance();
        ballRegistrarService = factory.getBallRegistrarService();
        ballCalculatorService = factory.getBallCalculatorService();
        ballConditionCheckerService = factory.getBallConditionCheckerService();
        ballService = factory.getBallService();
    }

    @Override
    public CommandResult execute(final String[] params) {
        if (params.length == 0) {
            enable = true;
            while (enable) {
                showBallMenu();
                view.print(INPUT_ICON);
                String input = view.read();
                if (input.matches(INT_REGEX)) {
                    switch (Integer.parseInt(input)) {
                        case 0 -> disable();
                        case 1 -> showAllBalls();
                        case 2 -> findBalls();
                        case 3 -> createNewBall();
                        case 4 -> loadBallsFromFile();
                        case 5 -> chooseBall();
                        default -> printInvalidInput();
                    }
                } else {
                    printInvalidInput();
                }
            }
            return positiveResult;
        }
        return negativeResult;
    }

    private void printInvalidInput() {
        view.println(TextManager.getText(BALL_INVALID_INPUT));
    }

    private void disable() {
        enable = false;
    }

    private void showBallMenu() {
        view.printNewLine();
        view.println(TextManager.getText("ball.menu.choose"));
        view.println("1) " + TextManager.getText("ball.menu.showAllBalls"));
        view.println("2) " + TextManager.getText("ball.menu.findBalls"));
        view.println("3) " + TextManager.getText("ball.menu.createNewBall"));
        view.println("4) " + TextManager.getText("ball.menu.loadBallsFromFile"));
        view.println("5) " + TextManager.getText("ball.menu.chooseBall"));
        view.println("0) " + TextManager.getText("ball.menu.exit"));
    }

    private void showAllBalls() {
        view.println(TextManager.getText("ball.allBalls"));
        List<Ball> balls = ballService.getAll();
        printBalls(balls);
    }

    private void printBalls(final List<Ball> balls) {
        for (int i = 0; i < balls.size(); ++i) {
            Ball currentBall = balls.get(i);
            view.println((i + 1) + ") " + ballToString(currentBall));
        }
    }

    private void findBalls() {
        view.println(TextManager.getText("ball.find.minValue"));
        view.print(INPUT_ICON);
        String input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double minRadius = Double.parseDouble(input);
        view.println(TextManager.getText("ball.find.maxValue"));
        view.print(INPUT_ICON);
        input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double maxRadius = Double.parseDouble(input);
        FindByRadiusBetweenBallSpecification specification
                = new FindByRadiusBetweenBallSpecification(
                minRadius, maxRadius);
        AndSpecification<Ball> finalSpecification
                = new AndSpecification<>(specification,
                new SortByRadiusDescBallSpecification()
        );
        try {
            List<Ball> entities
                    = ballService.findBySpecification(finalSpecification);
            view.println(TextManager.getText("ball.find"));
            printBalls(entities);
        } catch (ServiceException e) {
            view.println(TextManager.getText(ERROR));
            log.error(e);
        }
    }

    private String ballToString(final Ball ball) {
        int id = ball.getId();
        double radius = ball.getRadius();
        Point centerPoint = ball.getCenterPoint();
        double x = centerPoint.getX();
        double y = centerPoint.getY();
        double z = centerPoint.getZ();
        return "Ball: id = %d, radius = %f, center: x = %f, y = %f, z = %f"
                .formatted(id, radius, x, y, z);
    }

    private void createNewBall() {
        view.println(TextManager.getText("ball.enterRadius"));
        view.print(INPUT_ICON);
        String input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double radius = Double.parseDouble(input);
        view.println(TextManager.getText("ball.enterX"));
        view.print(INPUT_ICON);
        input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double x = Double.parseDouble(input);
        view.println(TextManager.getText("ball.enterY"));
        view.print(INPUT_ICON);
        input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double y = Double.parseDouble(input);
        view.println(TextManager.getText("ball.enterZ"));
        view.print(INPUT_ICON);
        input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double z = Double.parseDouble(input);

        Ball ball = new Ball(radius, new Point(x, y, z));

        try {
            ballService.add(ball);
        } catch (ServiceException e) {
            view.println(TextManager.getText("ball.invalidBall"));
            log.error(e);
        }
    }

    private void loadBallsFromFile() {
        view.println(TextManager.getText("ball.enterPath"));
        view.print(INPUT_ICON);
        String input = view.read();
        try {
            ballService.addEntitiesFromFile(input);
            view.println(TextManager.getText("ball.successfullyAdded"));
        } catch (ServiceException e) {
            view.println(TextManager.getText(ERROR));
            log.error(e);
        }
    }

    private void chooseBall() {
        view.println(TextManager.getText("ball.enterBallId"));
        view.print(INPUT_ICON);
        String input = view.read();
        if (!input.matches(INT_REGEX)) {
            printInvalidInput();
            return;
        }
        Optional<Ball> maybeBall
                = ballService.findById(Integer.parseInt(input));
        if (maybeBall.isPresent()) {
            Ball ball = maybeBall.get();
            boolean flag = true;
            while (flag) {
                showBallInteractionMenu();
                view.print(INPUT_ICON);
                input = view.read();
                if (input.matches(INT_REGEX)) {
                    switch (Integer.parseInt(input)) {
                        case 0 -> flag = false;
                        case 1 -> showBallStatistics(ball);
                        case 2 -> updateBall(ball);
                        case 3 -> calcRatio(ball);
                        default -> printInvalidInput();
                    }
                } else {
                    printInvalidInput();
                }
            }
        } else {
            view.println(TextManager.getText("ball.noBall"));
        }
    }

    private void showBallInteractionMenu() {
        view.printNewLine();
        view.println(TextManager.getText("ball.menu.choose"));
        view.println("1) "
                + TextManager.getText("ball.interaction.showBallStatistic")
        );
        view.println("2) "
                + TextManager.getText("ball.interaction.updateBall")
        );
        view.println("3) "
                + TextManager.getText("ball.interaction.calcRatioOfParts")
        );
        view.println("0) "
                + TextManager.getText("ball.interaction.back")
        );
    }

    private void showBallStatistics(final Ball ball) {
        try {
            view.println(TextManager.getText("ball.statistics")
                    + " " + ballToString(ball)
            );
            Optional<BallRegistrar> maybeRegistrar
                    = ballRegistrarService.getRegistrar(ball);
            if (maybeRegistrar.isPresent()) {
                BallRegistrar registrar = maybeRegistrar.get();
                view.println(TextManager.getText("ball.statistics.volume")
                        + " " + registrar.getVolume()
                );
                view.println(TextManager.getText("ball.statistics.area")
                        + " " + registrar.getSurfaceArea()
                );
            } else {
                log.error("no registrar for entity: {}", ball);
            }
            boolean isBall = ballConditionCheckerService.isBall(ball);
            view.println(TextManager.getText("ball.statistics.isBall")
                    + " " + isBall
            );
            boolean touchAnyPlane
                    = ballConditionCheckerService.checkTouchAnyPlane(ball);
            view.println(TextManager.getText("ball.statistics.doesTouch")
                    + " " + touchAnyPlane
            );
        } catch (ServiceException e) {
            view.println(TextManager.getText(ERROR));
            log.error(e);
        }
    }

    private void updateBall(final Ball ball) {
        view.println(TextManager.getText("ball.enterRadius"));
        view.print(INPUT_ICON);
        String input = view.read();
        if (!input.matches(DOUBLE_VALUE_REGEX)) {
            printInvalidInput();
            return;
        }
        double radius = Double.parseDouble(input);
        ball.setRadius(radius);
        try {
            ballService.update(ball);
            view.println(TextManager.getText("ball.update"));
        } catch (ServiceException e) {
            view.println(TextManager.getText(ERROR));
            log.error(e);
        }
    }

    private void calcRatio(final Ball ball) {
        calcRatioHelper(ball, Plane.XY_PLANE);
        calcRatioHelper(ball, Plane.XZ_PLANE);
        calcRatioHelper(ball, Plane.YZ_PLANE);
    }

    private void calcRatioHelper(final Ball ball, final Plane plane) {
        try {
            double ratio = ballCalculatorService
                    .calculateRatioOfPartsDissectedByPlane(ball, plane);
            view.print(TextManager.getText("ball.ratio"));
            view.print(" " + plane + ": ");
            view.println(String.valueOf(ratio));
        } catch (ServiceException e) {
            view.println(TextManager.getText("ball.ratio.dissect")
                    + " " + plane
            );
            log.error(e);
        }
    }
}
