package by.training.information.controller;

import by.training.information.entity.Symbol;
import by.training.information.entity.TextComponent;
import by.training.information.service.*;
import by.training.information.view.View;
import by.training.information.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code ControllerImpl} is a class
 * that implements {@link Controller}.
 *
 * @author Nikita Romanov
 */
public class ControllerImpl implements Controller {

    private static final Logger log
            = LogManager.getLogger(ControllerImpl.class);

    private final View view;
    private final TextService textService;
    private final TextSortingService textSortingService;
    private final ProxyExpressionsInTextService proxyExpressionsInTextService;
    private final String pathToFile;
    private final Symbol symbolForSorting;

    public ControllerImpl(String pathToFile, Symbol symbolForSorting) {
        view = ViewFactory.getInstance().getView();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        textService = serviceFactory.getTextService();
        textSortingService = serviceFactory.getTextSortingService();
        proxyExpressionsInTextService = serviceFactory.getProxyExpressionsInTextService();
        this.pathToFile = pathToFile;
        this.symbolForSorting = symbolForSorting;
    }

    @Override
    public void run() {
        try {
            TextComponent inputText = textService.readTextFromFile(pathToFile);
            view.println("Input text:");
            view.println(inputText.collect());

            TextComponent newText = proxyExpressionsInTextService.proxyExpressions(inputText);
            view.println("Collect with calculating expressions:");
            view.println(newText.collect());

            TextComponent sortedParagraphsByNumberOfSentences
                    = textSortingService.sortParagraphsByNumberOfSentences(inputText);
            view.println("Paragraphs sorted by number of sentences:");
            view.println(sortedParagraphsByNumberOfSentences.collect());

            TextComponent sortedWordsInSentenceByLength
                    = textSortingService.sortWordsInSentenceByLength(inputText);
            view.println("Words in the sentence sorted by length:");
            view.println(sortedWordsInSentenceByLength.collect());

            TextComponent sortedLexemes
                    = textSortingService.sortLexemesInTextByNumberOfGivenSymbolDescThenByAlphabet(
                    inputText, symbolForSorting);
            view.println("All lexemes in the text sorted by number of given symbol and then by alphabet:");
            view.println(sortedLexemes.collect());
        } catch (ServiceException e) {
            log.error(e);
            view.println("Exception occurred!");
        }
    }
}
