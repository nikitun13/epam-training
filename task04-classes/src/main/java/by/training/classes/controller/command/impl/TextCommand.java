package by.training.classes.controller.command.impl;

import by.training.classes.controller.command.Command;
import by.training.classes.controller.command.result.CommandResult;
import by.training.classes.controller.command.result.CommandStatus;
import by.training.classes.entity.Sentence;
import by.training.classes.entity.Text;
import by.training.classes.service.*;
import by.training.classes.view.View;
import by.training.classes.view.ViewFactory;
import by.training.classes.view.manager.TextManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * The class {@code TextCommand} is a class
 * that implements {@link Command}.<br/>
 * Executed when the command 'text' is received.
 * This command let users edit text.
 *
 * @author Nikita Romanov
 */
public class TextCommand implements Command {

    private static final Logger logger =
            LogManager.getLogger(TextCommand.class);
    private static final String INT_REGEXP = "[-+]?\\d+";
    private static final String ERROR_KEY = "error.error";

    private final TextService textService;
    private final TextEditorService textEditorService;
    private final TextCreatorService textCreatorService;
    private final SentenceCreatorService sentenceCreatorService;
    private final View view;

    public TextCommand() {
        ServiceFactory factory = ServiceFactory.getInstance();
        textService = factory.getTextService();
        textEditorService = factory.getTextEditorService();
        textCreatorService = factory.getTextCreatorService();
        sentenceCreatorService = factory.getSentenceCreatorService();
        view = ViewFactory.getInstance().getView();
    }

    @Override
    public CommandResult execute(String[] params) {
        String arrayStatus = Arrays.toString(params);
        logger.debug("received params: {}", arrayStatus);
        CommandResult result = switch (params.length) {
            case 1 -> createNewText(params[0]);
            case 2 -> loadTextFromFile(params[0], params[1]);
            default -> {
                logger.error("Invalid number of parameters");
                yield new CommandResult(
                        CommandStatus.INVALID_NUMBER_OF_PARAMETERS
                );
            }
        };
        logger.debug("result: {}", result);
        return result;
    }

    private CommandResult createNewText(String command) {
        if (!command.equals("new")) {
            logger.error("invalid command param: {}", command);
            return new CommandResult(CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("text.unknownParam") + command
            );
        }
        view.println(TextManager.getText("text.enterHeader"));
        String headerValue = view.read();
        view.println(TextManager.getText("text.enterBody"));
        String bodyValue = view.read();
        String textValue = headerValue + "\n\n" + bodyValue;
        try {
            Text text = textCreatorService.createText(textValue);
            launchEditorInteraction(text, null);
        } catch (ServiceException e) {
            logger.error("invalid textValue", e);
            return new CommandResult(CommandStatus.ERROR);
        }
        return new CommandResult(CommandStatus.OK,
                TextManager.getText("text.done")
        );
    }

    private CommandResult loadTextFromFile(String command, String pathValue) {
        if (!command.equals("load")) {
            logger.error("invalid command param: {}", command);
            return new CommandResult(CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("text.unknownParam") + command
            );
        }
        try {
            Path path = Path.of(pathValue);
            Text text = textService.readText(path);
            launchEditorInteraction(text, path);
        } catch (InvalidPathException e) {
            logger.error("invalid pathValue", e);
            return new CommandResult(CommandStatus.INVALID_INPUT_PARAMETERS,
                    TextManager.getText("text.invalidPath")
            );
        } catch (ServiceException e) {
            logger.error(e);
            return new CommandResult(CommandStatus.ERROR);
        }
        return new CommandResult(CommandStatus.OK,
                TextManager.getText("text.done")
        );
    }

    private void launchEditorInteraction(Text text, Path path) {
        boolean enable = true;
        Path currentPath = path;
        while (enable) {
            printOptions();
            view.print(">");
            String stringOptionNumber = view.read();
            if (!stringOptionNumber.matches(INT_REGEXP)) {
                logger.error("invalid integer input: {}", stringOptionNumber);
                view.println(TextManager.getText(ERROR_KEY));
                continue;
            }
            int optionNumber = Integer.parseInt(stringOptionNumber);
            switch (optionNumber) {
                case 1 -> displayText(text);
                case 2 -> addSentence(text);
                case 3 -> removeSentence(text);
                case 4 -> changeHeader(text);
                case 5 -> saveToCurrentPath(text, currentPath);
                case 6 -> currentPath = saveAs(text, currentPath);
                case 7 -> deleteCurrentFile(currentPath);
                case 0 -> enable = false;
                default -> {
                    logger.error("unknown option number: {}", optionNumber);
                    view.println(TextManager.getText("editor.unknownOption"));
                }
            }
        }
    }

    private void printOptions() {
        view.println(TextManager.getText("editor.choose"));
        view.println(TextManager.getText("editor.viewText"));
        view.println(TextManager.getText("editor.addSentence"));
        view.println(TextManager.getText("editor.removeSentence"));
        view.println(TextManager.getText("editor.changeHeader"));
        view.println(TextManager.getText("editor.save"));
        view.println(TextManager.getText("editor.saveAs"));
        view.println(TextManager.getText("editor.deleteFile"));
        view.println(TextManager.getText("editor.exit"));
    }

    private void displayText(Text text) {
        logger.debug("display text was chosen");
        view.println(TextManager.getText("editor.text"));
        view.println(text.toString());
        view.printNewLine();
    }

    private void addSentence(Text text) {
        logger.debug("add sentence was chosen");
        view.println(TextManager.getText("editor.typeSentence"));
        Sentence newSentence = getSentenceFromUser();
        if (newSentence == null) {
            return;
        }
        view.println(TextManager.getText("editor.typePositionToAdd"));
        Integer position = getPositionFromUser();
        if (position == null) {
            return;
        }
        try {
            textEditorService.addSentence(text, position, newSentence);
        } catch (ServiceException e) {
            logger.error("invalid index", e);
            view.println(TextManager.getText("editor.invalidPosition"));
        }
    }

    private void removeSentence(Text text) {
        logger.debug("remove sentence was chosen");
        view.println(TextManager.getText("editor.typePositionToRemove"));
        Integer position = getPositionFromUser();
        if (position == null) {
            return;
        }
        try {
            textEditorService.removeSentence(text, position);
        } catch (ServiceException e) {
            logger.error("invalid index", e);
            view.println(TextManager.getText("editor.invalidPosition"));
        }
    }

    private void changeHeader(Text text) {
        logger.debug("change header was chosen");
        view.println(TextManager.getText("editor.typeNewHeader"));
        Sentence newHeader = getSentenceFromUser();
        if (newHeader == null) {
            return;
        }
        try {
            textEditorService.changeHeader(text, newHeader);
        } catch (ServiceException e) {
            logger.error("unknown exception", e);
            view.println(TextManager.getText(ERROR_KEY));
        }
    }

    private void saveToCurrentPath(Text text, Path path) {
        logger.debug("save to current path was chosen");
        if (path == null) {
            view.println(TextManager.getText("editor.choosePath"));
            return;
        }
        try {
            textService.saveText(path, text);
        } catch (ServiceException e) {
            logger.error("save exception occurred", e);
            view.println(TextManager.getText(ERROR_KEY));
        }

    }

    private Path saveAs(Text text, Path currentPath) {
        logger.debug("'save as' was chosen");
        view.println(TextManager.getText("editor.typePath"));
        view.print(">");
        String pathValue = view.read();
        try {
            Path path = Path.of(pathValue);
            saveToCurrentPath(text, path);
            return path;
        } catch (InvalidPathException e) {
            logger.error("invalid pathValue", e);
            view.println(TextManager.getText("text.invalidPath"));
            return currentPath;
        }
    }

    private void deleteCurrentFile(Path path) {
        logger.debug("delete current file was chosen");
        if (path == null) {
            view.println(TextManager.getText("editor.choosePath"));
            return;
        }
        try {
            textService.deleteText(path);
        } catch (ServiceException e) {
            logger.error("delete exception occurred", e);
            view.println(TextManager.getText(ERROR_KEY));
        }
    }

    private Integer getPositionFromUser() {
        view.print(">");
        String stringPosition = view.read();
        if (!stringPosition.matches(INT_REGEXP)) {
            logger.error("invalid integer input: {}", stringPosition);
            view.println(TextManager.getText(ERROR_KEY));
            return null;
        }
        return Integer.valueOf(stringPosition);
    }

    private Sentence getSentenceFromUser() {
        view.print(">");
        String newSentenceValue = view.read();
        if (newSentenceValue.charAt(newSentenceValue.length() - 1) == '.') {
            newSentenceValue = newSentenceValue.substring(0, newSentenceValue.length() - 1);
        }
        try {
            return sentenceCreatorService.createSentence(newSentenceValue);
        } catch (ServiceException e) {
            logger.error("invalid sentence", e);
            view.println(TextManager.getText("editor.invalidSentence"));
            return null;
        }
    }
}
