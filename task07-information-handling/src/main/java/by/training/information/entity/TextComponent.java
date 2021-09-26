package by.training.information.entity;

/**
 * Describes interface of a text component entity.
 *
 * @author Nikita Romanov
 */
public interface TextComponent {

    /**
     * Collects values from children and combines them.
     * Or returns its value if it is a leaf.
     *
     * @return combined value of children.
     */
    String collect();
}
