package it.unicam.cs.AlfonsoAntognozzi.model;

/***
 * This interface is used to outline the basic behaviors of any figure
 *
 * @param <P> Position of the shape
 * @param <C> Condition of the shape
 * @param <R> type of Robot
 */
public interface IShape <P,C,R> {
    P getShapePosition();
    C getShapeCondition();
    /*
    This method is used to check if the given robot collides with the figure
     */
    boolean checkCollision(R robot);

}
