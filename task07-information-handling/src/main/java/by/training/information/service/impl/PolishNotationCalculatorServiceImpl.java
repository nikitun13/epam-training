package by.training.information.service.impl;

import by.training.information.entity.Operator;
import by.training.information.entity.ReversePolishNotation;
import by.training.information.entity.interpreter.Number;
import by.training.information.entity.interpreter.*;
import by.training.information.service.PolishNotationCalculatorService;
import by.training.information.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PolishNotationCalculatorServiceImpl
        implements PolishNotationCalculatorService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(PolishNotationCalculatorServiceImpl.class);
    private static final String INTEGER_REGEX = "[-+]?\\d+";

    @Override
    public int calculateExpression(final ReversePolishNotation polishNotation)
            throws ServiceException {
        log.debug("received notation: {}", polishNotation);
        if (polishNotation == null) {
            throw new ServiceException("notation can't be null");
        }
        Item root = createTree(polishNotation);
        return root.interpret();
    }

    private Item createTree(final ReversePolishNotation polishNotation)
            throws ServiceException {
        Item node;
        String currentItem = polishNotation.pop();
        log.debug("current item: {}", currentItem);
        if (currentItem.matches(INTEGER_REGEX)) {
            log.debug("create leaf as Number");
            node = new Number(Integer.parseInt(currentItem));
        } else {
            Operation operation = createOperation(currentItem);
            if (operation.getRight() == null) {
                operation.setRight(createTree(polishNotation));
            }
            if (operation.getLeft() == null) {
                operation.setLeft(createTree(polishNotation));
            }
            node = operation;
        }
        return node;
    }

    private Operation createOperation(final String operationSymbol)
            throws ServiceException {
        Operator operator = Operator.getOperatorBySymbol(operationSymbol)
                .orElseThrow(ServiceException::new);
        log.debug("create as {}", operator);
        return switch (operator) {
            case BITWISE_UNARY_NOT -> new UnaryOperation(a -> ~a);
            case SIGNED_RIGHT_SHIFT -> new BinaryOperation((a, b) -> a >> b);
            case SIGNED_LEFT_SHIFT -> new BinaryOperation((a, b) -> a << b);
            case UNSIGNED_RIGHT_SHIFT -> new BinaryOperation((a, b) -> a >>> b);
            case BITWISE_AND -> new BinaryOperation((a, b) -> a & b);
            case BITWISE_EXCLUSIVE_OR -> new BinaryOperation((a, b) -> a ^ b);
            case BITWISE_OR -> new BinaryOperation((a, b) -> a | b);
            default -> throw new ServiceException("unknown operation");
        };
    }
}
