package by.training.xml.controller;

import by.training.xml.entity.Gem;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.entity.VisualParameters;
import by.training.xml.service.ServiceException;
import by.training.xml.service.ServiceFactory;
import by.training.xml.service.XmlService;
import by.training.xml.view.View;
import by.training.xml.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class {@code ControllerImpl} is a class
 * that implements {@link Controller}.
 *
 * @author Nikita Romanov
 */
public class ControllerImpl implements Controller {

    private static final Logger log =
            LogManager.getLogger(ControllerImpl.class);

    private static final String INPUT_ICON = ">";
    private static final String FIRST_MENU_ELEMENT = "1 - Load Gems XML";
    private static final String SECOND_MENU_ELEMENT = "0 - Exit";
    private static final String USER_TYPED_LOGGER_MESSAGE = "user typed: {}";

    private boolean enable = true;
    private final View view = ViewFactory.getInstance().getView();
    private final XmlService<Gem> gemXmlService = ServiceFactory.getInstance().getGemXmlService();

    @Override
    public void run() {
        log.info("Entering an infinite loop");
        while (enable) {
            printMenu();
            view.print(INPUT_ICON);
            String request = view.read();
            log.debug(USER_TYPED_LOGGER_MESSAGE, request);
            if (request.equals("1")) {
                loadXMLInteraction();
            } else if (request.equals("0")) {
                switchEnableFlag();
            } else {
                view.println("Error, invalid input");
            }
        }
        log.info("Exited from the infinite loop");
    }

    private void printMenu() {
        view.printNewLine();
        view.println(FIRST_MENU_ELEMENT);
        view.println(SECOND_MENU_ELEMENT);
    }

    private void switchEnableFlag() {
        enable = !enable;
    }

    private void loadXMLInteraction() {
        view.println("Enter path to gems XML");
        view.print(INPUT_ICON);
        String path = view.read();
        log.debug(USER_TYPED_LOGGER_MESSAGE, path);
        view.println("Choose parser (dom, sax, stax)");
        view.print(INPUT_ICON);
        String parserName = view.read();
        log.debug(USER_TYPED_LOGGER_MESSAGE, parserName);
        try {
            List<Gem> gems = gemXmlService.findEntities(path, parserName);
            printGems(gems);
        } catch (ServiceException e) {
            log.error(e);
            view.println("Exception occurred");
        }
    }

    private void printGems(List<Gem> gems) {
        final String leftAlignFormat = "| %-4d | %-14s | %-12s | %-14s | %-6s | %-12d | %-13d | %-8.2f | %-10s | %-20s |";
        final String border = "+------+----------------+--------------+----------------+--------+--------------+---------------+----------+------------+----------------------+";
        view.println(border);
        view.println("| id   | name           | preciousness | origin         | color  | transparency | facets-number | value    | date       | laboratory           |");
        view.println(border);
        for (Gem gem : gems) {
            long id = gem.getId();
            String name = gem.getName();
            String preciousness = gem.getPreciousness().name().toLowerCase();
            String origin = gem.getOrigin();

            VisualParameters parameters = gem.getParameters();
            String color = parameters.getColor().name().toLowerCase();
            int transparency = parameters.getTransparency();
            int facetsNumber = parameters.getFacetsNumber();

            double value = gem.getValue();
            String date = gem.getDate().toString();
            String laboratory = "-";
            if (gem instanceof SyntheticGem syntheticGem) {
                laboratory = syntheticGem.getLaboratory();
            }
            view.println(leftAlignFormat
                    .formatted(id, name, preciousness, origin, color,
                            transparency, facetsNumber, value, date, laboratory)
            );
        }
        view.println(border);
    }
}
