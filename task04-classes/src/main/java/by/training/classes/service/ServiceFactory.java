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

    private WordCreatorService wordCreatorService;
    private SentenceCreatorService sentenceCreatorService;
    private TextService textService;
    private TextCreatorService textCreatorService;
    private TextEditorService textEditorService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public TextService getTextService() {
        if (textService == null) {
            textService = new TextServiceImpl();
        }
        return textService;
    }

    public TextEditorService getTextEditorService() {
        if (textEditorService == null) {
            textEditorService = new TextEditorServiceImpl();
        }
        return textEditorService;
    }

    public TextCreatorService getTextCreatorService() {
        if (textCreatorService == null) {
            textCreatorService = new TextCreatorServiceImpl();
        }
        return textCreatorService;
    }

    public SentenceCreatorService getSentenceCreatorService() {
        if (sentenceCreatorService == null) {
            sentenceCreatorService = new SentenceCreatorServiceImpl();
        }
        return sentenceCreatorService;
    }

    public WordCreatorService getWordCreatorService() {
        if (wordCreatorService == null) {
            wordCreatorService = new WordCreatorServiceImpl();
        }
        return wordCreatorService;
    }
}
