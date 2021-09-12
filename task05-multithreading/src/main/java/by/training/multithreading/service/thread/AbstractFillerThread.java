package by.training.multithreading.service.thread;

import by.training.multithreading.entity.Matrix;

public abstract class AbstractFillerThread extends Thread {

    protected final int value;
    protected final Matrix matrix;

    protected AbstractFillerThread(int value, Matrix matrix) {
        this.value = value;
        this.matrix = matrix;
    }

    protected AbstractFillerThread(String name, int value, Matrix matrix) {
        super(name);
        this.value = value;
        this.matrix = matrix;
    }

    @Override
    public abstract void run();

    protected int findIndexOfEmptyDiagonalElement(int startIndex) {
        for (int i = startIndex; i < matrix.getNumberOfRows(); ++i) {
            if (matrix.getElement(i, i) == 0) {
                return i;
            }
        }
        return -1;
    }
}
