package it.unicam.cs.AlfonsoAntognozzi.model;

/**
 * This interface is used to outline the basic behaviors of any figure
 *
 * @param <P> Position of the shape
 * @param <C> Condition of the shape
 * @param <R> type of Robot
 */
public interface IShape <P,C,R> {

    /**
     * Method used to get the position of the shape
     *
     * @return the position of the shape
     */
    P getShapePosition();

    /**
     * Method used to get the condition of the shape
     *
     * @return the condition of the shape
     */
    C getShapeCondition();

    /**
     * This method is used to check if the given robot collides with the shape
     *
     * @param robot robot that has to check if it collides with the shape
     * @return True if the robot collides, false otherwise
     */
    boolean checkCollision(R robot);

}
