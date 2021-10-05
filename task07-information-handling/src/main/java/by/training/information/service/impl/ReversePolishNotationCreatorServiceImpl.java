package by.training.information.service.impl;

import by.training.information.entity.Operator;
import by.training.information.entity.ReversePolishNotation;
import by.training.information.service.ReversePolishNotationCreatorService;
import by.training.information.service.ServiceException;
import by.training.information.service.validator.ExpressionValidator;
import by.training.information.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code TextServiceImpl}
 * is a class that implements
 * {@link ReversePolishNotationCreatorService}.
 *
 * @author Nikita Romanov
 * @see ReversePolishNotationCreatorService
 */
public class ReversePolishNotationCreatorServiceImpl
        implements ReversePolishNotationCreatorService {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(
            ReversePolishNotationCreatorServiceImpl.class);

    private static final Pattern EXPRESSION_ELEMENTS_PATTERN
            = Pattern.compile("\\d+|<{2}|>{2,3}|[()~^&|]");
    private static final String INTEGER_REGEX = "[-+]?\\d+";

    /**
     * Validator.
     */
    private final Validator<String> validator
            = ExpressionValidator.getInstance();

    @Override
    public ReversePolishNotation createNotation(final String expression)
            throws ServiceException {
        if (!validator.isValid(expression)) {
            throw new ServiceException("invalid expression: " + expression);
        }
        log.debug("received expression: {}", expression);
        List<String> items = parse(expression);
        log.debug("parsed expression: {}", items);
        Deque<String> result = new ArrayDeque<>();
        Deque<Operator> operators = new ArrayDeque<>();
        for (String item : items) {
            log.debug("item: {}", item);
            if (item.matches(INTEGER_REGEX)) {
                log.debug("pushing as number");
                result.addLast(item);
            } else {
                Operator operator = Operator.getOperatorBySymbol(item)
                        .orElseThrow(ServiceException::new);
                if (operator.equals(Operator.LEFT_BRACKET)) {
                    operators.addLast(operator);
                } else if (operator.equals(Operator.RIGHT_BRACKET)) {
                    pushItemsInsideBracket(result, operators);
                } else {
                    pushOperator(operator, operators, result);
                }
            }
        }
        operators.stream()
                .map(Operator::getSymbol)
                .forEach(result::addLast);
        ReversePolishNotation notation = new ReversePolishNotation(result);
        log.debug("result notation: {}", notation);
        return notation;
    }

    private List<String> parse(final String expression) {
        Matcher matcher = EXPRESSION_ELEMENTS_PATTERN.matcher(expression);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    private void pushOperator(final Operator operator,
                              final Deque<Operator> operators,
                              final Deque<String> resultStack) {
        while (!operators.isEmpty()
                && checkPriority(operators.getLast(), operator)) {
            String last = operators.removeLast().getSymbol();
            log.debug("pushing to result: {}", last);
            resultStack.addLast(last);
        }
        operators.addLast(operator);
    }

    private boolean checkPriority(final Operator last, final Operator current) {
        return last.getPrecedence() >= current.getPrecedence();
    }

    private void pushItemsInsideBracket(final Deque<String> resultStack,
                                        final Deque<Operator> operators) {
        Operator last = operators.removeLast();
        while (!last.equals(Operator.LEFT_BRACKET)) {
            log.debug("pushing to result: {}", last.getSymbol());
            resultStack.addLast(last.getSymbol());
            last = operators.removeLast();
        }
    }
}
