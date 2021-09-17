package by.training.shapes.service;

import by.training.shapes.entity.VolumetricShape;


/**
 * Describes the interface of a service
 * that does typical calculations for {@link VolumetricShape}.
 *
 * @param <T> concrete type of the {@code volumetric shape}.
 * @author Nikita Romanov
 * @see VolumetricShape
 */
public interface VolumetricShapeCalculator<T extends VolumetricShape> {

    /**
     * Calculates surface area of the {@code shape}.
     *
     * @param shape volumetric shape for calculation.
     * @return surface area of the {@code shape}.
     */
    double calculateSurfaceArea(T shape);

    /**
     * Calculates volume of the {@code shape}.
     *
     * @param shape volumetric shape for calculation.
     * @return volume of the {@code shape}.
     */
    double calculateVolume(T shape);
}
