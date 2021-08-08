package by.training.arrays.entity;

import by.training.arrays.entity.exception.MatrixException;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {

    private final int[][] elements;

    public Matrix(int[][] elements) {
        Objects.requireNonNull(elements);
        if (!isValidSize(elements)) {
            throw new MatrixException("Invalid array of arrays");
        }
        this.elements = elements;
    }

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

    public void setElement(int rowIndex, int columnIndex, int value) {
        if (checkRange(rowIndex, columnIndex)) {
            elements[rowIndex][columnIndex] = value;
        } else {
            throw new MatrixException(String.format(
                    "Indexes %d and %d are out of range for matrix %dx%d",
                    rowIndex, columnIndex, getNumberOfRows(), getNumberOfColumns())
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
        final String BLANK = " ";
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
        return isValidSize(rows, columns) &&
                rows < getNumberOfRows() &&
                columns < getNumberOfColumns();
    }

    private static boolean isValidSize(int rows, int columns) {
        return rows > 0 && columns > 0;
    }

    private static boolean isValidSize(int[][] rows) {
        if (rows.length < 1) {
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
