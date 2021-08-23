package by.training.classes.service;

import by.training.classes.service.impl.*;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final TextService textService = new TextServiceImpl();
    private final TextEditorService textEditorService = new TextEditorServiceImpl();
    private final TextCreatorService textCreatorService = new TextCreatorServiceImpl();
    private final SentenceCreatorService sentenceCreatorService = new SentenceCreatorServiceImpl();
    private final WordCreatorService wordCreatorService = new WordCreatorServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public TextService getTextService() {
        return textService;
    }

    public TextEditorService getTextEditorService() {
        return textEditorService;
    }

    public TextCreatorService getTextCreatorService() {
        return textCreatorService;
    }

    public SentenceCreatorService getSentenceCreatorService() {
        return sentenceCreatorService;
    }

    public WordCreatorService getWordCreatorService() {
        return wordCreatorService;
    }
}
