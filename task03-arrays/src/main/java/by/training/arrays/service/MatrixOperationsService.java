package by.training.arrays.service;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.exception.ServiceException;

/**
 * Describes the interface of a service
 * that performs various operations on {@link Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixOperationsService {

    /**
     * Multiplies two {@code Matrix}.
     *
     * @param leftMatrix  left {@code matrix} for multiplication.
     * @param rightMatrix right {@code matrix} for multiplication.
     * @return {@code matrix} product.
     * @throws ServiceException     if the number of columns of the left {@code matrix}
     *                              is not the same as the number of rows of the right {@code matrix}.
     * @throws NullPointerException if any of two {@code matrix} is {@code null}.
     */
    Matrix multiplyTwoMatrix(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Multiplies a {@code matrix} by a {@code coefficient}.
     *
     * @param coefficient the {@code coefficient} by which the matrix is multiplied.
     * @param matrix      {@code matrix} for multiplication.
     * @return product of the {@code coefficient} and the {@code matrix}.
     * @throws NullPointerException if the {@code matrix} is {@code null}.
     */
    Matrix multiplyByCoefficient(int coefficient, Matrix matrix);

    /**
     * Adds two {@code matrix}.
     *
     * @param leftMatrix  left {@code matrix} for sum.
     * @param rightMatrix right {@code matrix} for sum.
     * @return the sum of two {@code matrix}.
     * @throws ServiceException     if the size of the {@code leftMatrix}
     *                              is not the same as the size of the {@code rightMatrix}.
     * @throws NullPointerException if any of two {@code matrix} is {@code null}.
     */
    Matrix add(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Subtracts two {@code matrix}.
     *
     * @param leftMatrix  left {@code matrix} to subtract.
     * @param rightMatrix right {@code matrix} to subtract.
     * @return the difference of two {@code matrix}.
     * @throws ServiceException     if the size of the {@code leftMatrix}
     *                              is not the same as the size of the {@code rightMatrix}.
     * @throws NullPointerException if any of two {@code matrix} is {@code null}.
     */
    Matrix subtract(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Transpose the {@code matrix}.
     *
     * @param matrix the {@code matrix} for transposition.
     * @return transposed {@code Matrix}.
     * @throws NullPointerException if the {@code matrix} is {@code null}.
     */
    Matrix transpose(Matrix matrix);
}
