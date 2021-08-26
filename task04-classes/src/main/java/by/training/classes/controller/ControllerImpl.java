package by.training.classes.controller;

import by.training.classes.controller.command.Command;
import by.training.classes.controller.command.CommandProvider;
import by.training.classes.controller.command.result.CommandResult;
import by.training.classes.controller.command.result.CommandStatus;
import by.training.classes.view.View;
import by.training.classes.view.ViewFactory;
import by.training.classes.view.manager.Language;
import by.training.classes.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

/**
 * The class {@code ControllerImpl} is a class
 * that implements {@link Controller}.
 *
 * @author Nikita Romanov
 */
public class ControllerImpl implements Controller {

    private static final Logger logger =
            LogManager.getLogger(ControllerImpl.class);

    private static final String INPUT_ICON = ">";
    private static final String SEPARATOR_REGEX = "\\s+";
    private static final String WHITE_SPACE = " ";

    public static final String[] EMPTY_PARAMS = new String[0];

    private boolean enable = true;
    private final CommandProvider commandProvider =
            CommandProvider.getInstance();
    private final View view = ViewFactory.getInstance().getView();

    @Override
    public void run() {
        firstInteraction();
        logger.info("Entering an infinite loop");
        while (enable) {
            view.print(INPUT_ICON);
            String request = view.read();
            logger.debug("user typed: {}", request);
            if (request.isBlank()) {
                continue;
            }
            executeCommand(request);
        }
        logger.info("Exited from the infinite loop");
    }

    private void firstInteraction() {
        view.println(TextManager.getText("menu.firstMessage"));
        view.print(INPUT_ICON);
        String lang = view.read();
        logger.debug("user typed: {}", lang);
        String request = CommandProvider.CHANGE_LANGUAGE_COMMAND + WHITE_SPACE;
        if (Language.isContains(lang.toUpperCase())) {
            request += lang;
        } else {
            request += Language.EN;
        }
        executeCommand(request);
        view.println(TextManager.getText("menu.help"));
    }

    private void executeCommand(String request) {
        SimpleEntry<String, String[]> entry = parseRequest(request);
        String commandName = entry.getKey();
        String[] params = entry.getValue();
        Command command = commandProvider.getCommand(commandName);
        CommandResult result = command.execute(params);
        handleResult(result);
    }

    private SimpleEntry<String, String[]> parseRequest(String request) {
        logger.debug("received request: {}", request);
        String newRequest = request.strip().toLowerCase();
        String commandName;
        String[] params;
        int spaceIndex = newRequest.indexOf(WHITE_SPACE);
        if (spaceIndex != -1) {
            commandName = newRequest.substring(0, spaceIndex);
            params = newRequest
                    .substring(spaceIndex + 1)
                    .strip()
                    .split(SEPARATOR_REGEX);
        } else {
            commandName = newRequest;
            params = EMPTY_PARAMS;
        }
        String paramsToString = Arrays.toString(params);
        logger.debug("parse result: "
                        + "commandName = {}, params = {}, params.length = {}",
                commandName, paramsToString, params.length
        );
        return new SimpleEntry<>(commandName, params);
    }

    private void handleResult(CommandResult commandResult) {
        CommandStatus status = commandResult.getStatus();
        switch (status) {
            case OK -> view.println(
                    TextManager.getText("result")
            );
            case INVALID_NUMBER_OF_PARAMETERS -> view.println(
                    TextManager.getText("error.invalidNumberOfParameters")
            );
            case INVALID_INPUT_PARAMETERS -> view.println(
                    TextManager.getText("error.invalidParameters")
            );
            case ERROR -> view.println(
                    TextManager.getText("error.error")
            );
            case EXIT -> switchEnableFlag();
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
        commandResult.getMessage().ifPresent(view::println);
    }

    private void switchEnableFlag() {
        enable = !enable;
    }
}
