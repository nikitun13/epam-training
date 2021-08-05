package by.training.branching.controller;

import by.training.branching.controller.command.Command;
import by.training.branching.controller.command.provider.CommandProvider;
import by.training.branching.controller.command.result.CommandResult;
import by.training.branching.controller.command.result.CommandStatus;
import by.training.branching.view.View;
import by.training.branching.view.manager.TextManager;

/**
 * The class {@code ControllerImpl} is a class that implements {@link Controller}.
 *
 * @author Nikita Romanov
 */
public class ControllerImpl implements Controller {

    private static final String DELIMITER = " ";
    private static final String EMPTY_PARAMETERS_LINE = "";
    private static final String INPUT_ICON = ">";

    private boolean enable = true;
    private final CommandProvider commandProvider = CommandProvider.getInstance();
    private final View view;

    public ControllerImpl(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        firstInteraction();
        while (enable) {
            view.print(INPUT_ICON);
            String request = view.read().toLowerCase();
            if (request.isBlank()) {
                continue;
            }
            responseHandler(request);
        }
    }

    private void firstInteraction() {
        view.println(TextManager.getText("menu.firstMessage"));
        view.print(INPUT_ICON);
        String lang = view.read();
        executeCommand(CommandProvider.CHANGE_LANGUAGE_COMMAND + DELIMITER + lang);
        view.println(TextManager.getText("menu.help"));
    }

    private void responseHandler(String request) {
        CommandResult result = executeCommand(request);
        CommandStatus status = result.getStatus();
        if (status == CommandStatus.EXIT) {
            enable = false;
        }
        view.println(result.getMessage());
    }

    private CommandResult executeCommand(String request) {
        CommandResult result;
        Command command;
        int spaceIndex = request.indexOf(DELIMITER);
        if (spaceIndex != -1) {
            String commandName = request.substring(0, spaceIndex);
            command = commandProvider.getCommand(commandName);
            result = command.execute(request.substring(spaceIndex + 1));
        } else {
            command = commandProvider.getCommand(request);
            result = command.execute(EMPTY_PARAMETERS_LINE);
        }
        return result;
    }
}
