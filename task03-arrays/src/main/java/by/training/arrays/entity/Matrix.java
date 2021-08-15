package by.training.arrays.entity;

import by.training.arrays.entity.exception.MatrixException;

import java.util.Arrays;
import java.util.Objects;

/**
 * The class {@code Matrix} represents entity
 * of a matrix.<br>
 * Contains array of arrays with elements.
 * Provides access to them through methods.
 *
 * @author Nikita Romanov
 */
public class Matrix {

    private static final String BLANK = " ";

    /**
     * Array of arrays with elements.
     */
    private final int[][] elements;

    /**
     * Constructor of {@code Matrix}.<br/>
     * Creates object using array of arrays
     * with elements.
     *
     * @param elements array of arrays with elements.
     * @throws NullPointerException if {@code elements} is null.
     * @throws MatrixException      if arrays in the array
     *                              have different size
     *                              or size of array is less than 1.
     */
    public Matrix(int[][] elements) {
        Objects.requireNonNull(elements);
        Arrays.stream(elements).forEach(Objects::requireNonNull);
        if (!isValidArray(elements)) {
            throw new MatrixException("Invalid array of arrays");
        }
        this.elements = elements.clone();
    }

    /**
     * Constructor of {@code Matrix}.<br/>
     * Creates object using {@code rows} and {@code columns}.
     *
     * @param rows    number of rows.
     * @param columns number of columns.
     * @throws MatrixException if {@code rows}
     *                         or {@code columns} is less than 1.
     */
    public Matrix(int rows, int columns) {
        if (!isValidSize(rows, columns)) {
            throw new MatrixException(String.format("Matrix %dx%d can't exist",
                    rows, columns)
            );
        }
        elements = new int[rows][columns];
    }

    public int getNumberOfRows() {
        return elements.length;
    }

    public int getNumberOfColumns() {
        return elements[0].length;
    }

    /**
     * Returns element of {@code Matrix} with
     * indexes {@code rowIndex} and {@code columnIndex}.
     *
     * @param rowIndex    index of row.
     * @param columnIndex index of column.
     * @return element with given indexes.
     * @throws MatrixException if no such element with given indexes.
     */
    public int getElement(int rowIndex, int columnIndex) {
        if (checkRange(rowIndex, columnIndex)) {
            return elements[rowIndex][columnIndex];
        } else {
            throw new MatrixException(String.format(
                    "No such element with indexes: %d and %d",
                    rowIndex, columnIndex)
            );
        }
    }

    /**
     * Sets element to the position with
     * indexes {@code rowIndex} and {@code columnIndex}.
     *
     * @param rowIndex    index of row.
     * @param columnIndex index of column.
     * @param value       value to set to the position.
     * @throws MatrixException if indexes are out of range.
     */
    public void setElement(int rowIndex, int columnIndex, int value) {
        if (checkRange(rowIndex, columnIndex)) {
            elements[rowIndex][columnIndex] = value;
        } else {
            throw new MatrixException(String.format(
                    "Indexes %d and %d are out of range for matrix %dx%d",
                    rowIndex, columnIndex,
                    getNumberOfRows(), getNumberOfColumns())
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(elements, matrix.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(elements);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : "
                + getNumberOfRows() + "x"
                + getNumberOfColumns() + "\n");
        for (int[] row : elements) {
            for (int value : row) {
                s.append(value).append(BLANK);
            }
            s.append("\n");
        }
        return s.toString();
    }

    private boolean checkRange(int rows, int columns) {
        return rows >= 0 &&
                columns >= 0 &&
                rows < getNumberOfRows() &&
                columns < getNumberOfColumns();
    }

    private static boolean isValidSize(int rows, int columns) {
        return rows > 0 && columns > 0;
    }

    private static boolean isValidArray(int[][] rows) {
        if (rows.length < 1 || rows[0].length < 1) {
            return false;
        }
        int columnsNumber = rows[0].length;
        for (int i = 1; i < rows.length; i++) {
            int[] row = rows[i];
            if (columnsNumber != row.length) {
                return false;
            }
        }
        return true;
    }
}
